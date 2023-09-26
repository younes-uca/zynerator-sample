package ma.zs.generator.engine.service.impl;

import freemarker.template.TemplateException;
import ma.zs.generator.engine.bean.*;
import ma.zs.generator.engine.service.facade.*;
import ma.zs.generator.engine.service.util.FileUtil;
import ma.zs.generator.engine.service.util.ZipUtil;
import ma.zs.generator.project.bean.GeneratedProject;
import ma.zs.generator.project.bean.ProjectTemplate;
import ma.zs.generator.project.bean.Technologie;
import ma.zs.generator.project.config.UserConfig;
import ma.zs.generator.project.service.facade.GeneratorHistoryService;
import ma.zs.generator.project.service.facade.ProjectTemplateService;
import ma.zs.generator.project.service.facade.TechnologieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author MoiseGui
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    private PojoService pojoService;
    @Autowired
    private ProjectTemplateService projectTemplateService;
    @Autowired
    private ProjectTemplateLoader templateLoader;
    @Autowired
    private TemplateEngineService templateEngine;
    @Autowired
    private TechnologieService technologieService;
    @Autowired
    private FileEngine fileEngine;
    @Autowired
    private GeneratorHistoryService history;

    private String generatedProjectFolder = System.getProperty("user.home") + "\\generated\\" + System.currentTimeMillis();

    @Override
    public GeneratedProject generate(UserConfig userConfig) {
        System.out.println("userConfig = " + userConfig);
        if (userConfig.getPojos() == null || userConfig.getPojos().size() == 0)
            return null;
        userConfig.getRoles().forEach(e -> {
            e.setName(e.getName().toLowerCase());
        });
        userConfig.setPojos(pojoService.validatePojos(userConfig.getPojos()));

        userConfig.getRoles().forEach(r -> r.setPermissions(pojoService.constructPermissions(r, userConfig.getPojos())));

        userConfig.getRoles().forEach(r -> pojoService.validatePojosForPermissions(r.getPermissions()));

        userConfig.getRoles().forEach(r -> pojoService.prepareSubModulesAndPackage(userConfig.getPojos(), r));


        if (userConfig.getPojos().size() == 0)
            return null;

        try {
            File generatedProject = new File(generatedProjectFolder);
            if (generatedProject.exists())
                System.out.println(FileUtil.deleteDirectory(generatedProject));
            if (userConfig.isWantBackend()) {
                List<ConfigurationMs> configurationMss = reconstructMsConfig(userConfig.getPojos());
                for (ConfigurationMs configurationMs : configurationMss) {
                    adaptExternePojos(configurationMs, userConfig);
                    generateBackend(userConfig, configurationMs);
                }
                System.out.println("back end generated");
            }


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        GeneratedProject project = new GeneratedProject();
        project.setName(userConfig.getConfig().projectName);
        project.setTree(projectTemplateService.createTreeOfFolder(new File(generatedProjectFolder)));
        //project.setZip(this.archive(generatedProjectFolder));
        return project;
    }

    private List<ConfigurationMs> reconstructMsConfig(List<Pojo> pojos) {
        List<ConfigurationMs> configurationMss = new ArrayList<>();
        for (Pojo pojo : pojos) {
            if (findByMsName(pojo.getMsName(), configurationMss) == null) {
                configurationMss.add(new ConfigurationMs(pojo.getMsName(), pojo.getMsPackaging(), pojo.getMsUrl(), pojo.isMsSkip()));
            }
        }
        return configurationMss;
    }

    private ConfigurationMs findByMsName(String msName, List<ConfigurationMs> configurationMss) {
        ConfigurationMs configurationMs = null;
        if (configurationMss != null) {
            for (ConfigurationMs myConfigurationMs : configurationMss) {
                if (myConfigurationMs != null && msName.equals(myConfigurationMs.getName())) {
                    return myConfigurationMs;
                }
            }
            return null;
        }
        return configurationMs;
    }

    private void adaptExternePojos(ConfigurationMs configurationMs, UserConfig userConfig) {
        for (Pojo pojo : userConfig.getPojos()) {
            String msName = configurationMs.getName();
            if (msName.equals(pojo.getMsName())) {
                pojo.setMsExterne(false);
            } else {
                pojo.setMsExterne(true);
            }
        }
    }

    private void generateBackend(UserConfig userConfig, ConfigurationMs configurationMs) throws IOException {
        if (userConfig.getBackend() != null && userConfig.getBackend().getTechnologie() != null) {
            ProjectTemplate template = projectTemplateService.findByNameAndTechnologieName(
                    userConfig.getBackend().getName(), userConfig.getBackend().getTechnologie().getName());
            if (template != null) {
                File templateFolder = templateLoader.load(template);
                String generatedBackendFolder = generatedProjectFolder + File.separator + "backend" + (configurationMs.getName() == null ? "" : "-" + configurationMs.getName());
                try {
                    templateEngine.generate(templateFolder, generatedBackendFolder, userConfig);
                    history.save(template);
                } catch (TemplateException e) {
                    System.out.println("error in backend template " + template.getName() + " of "
                            + template.getTechnologie().getName());
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public GeneratedProject generate(FileEngineConfig config) {
        try {
            File generatedFolder = new File(generatedProjectFolder);
            if (generatedFolder.exists())
                System.out.println(FileUtil.deleteDirectory(generatedFolder));

            fileEngine.generate(config, generatedProjectFolder);
            GeneratedProject project = new GeneratedProject();
            project.setName(config.getNameOrSuffix());
            project.setTree(projectTemplateService.createTreeOfFolder(new File(generatedProjectFolder)));
            project.setZip(this.archive(generatedProjectFolder));
            return project;
        } catch (IOException e) {
            e.printStackTrace();

        } catch (TemplateException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
        return null;
    }

    @Override
    public byte[] generate(String backend, String frontend, MultipartFile file) {
        UserConfig config = new UserConfig();

        Technologie backendTech = technologieService.findByName(backend);
        if (backendTech != null) {
            config.setWantBackend(true);
            config.setBackend(backendTech.getDefaultTemplate());
        }
        return generate(config).getZip();
    }


    private byte[] archive(String filePath) {
        File project = new File(filePath);
        ZipUtil.zipIt(project, project.getParent());
        File projectAsZip = new File(filePath + ".zip");
        byte[] byteArray = ZipUtil.convertZipToByteArray2(projectAsZip);

        //projectAsZip.delete();
        return byteArray;
    }

}
