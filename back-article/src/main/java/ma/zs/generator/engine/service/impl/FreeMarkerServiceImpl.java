package ma.zs.generator.engine.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import ma.zs.generator.engine.bean.*;
import ma.zs.generator.engine.service.facade.FreeMarkerService;
import ma.zs.generator.project.config.ProjectConfig;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Ouiam
 */
@Service
public class FreeMarkerServiceImpl implements FreeMarkerService {
    @Override
    public Configuration initConfiguration() throws IOException {
        Configuration config = new Configuration(Configuration.VERSION_2_3_28);
        config.setDefaultEncoding("UTF-8");
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        config.setLogTemplateExceptions(false);
        config.setWrapUncheckedExceptions(true);
        return config;
    }


    @Override
    public void generateFile(List<Pojo> pojos, String templateName, String fileName, String extensions,
                             String outputDirectory, String templatePath, ProjectConfig config, List<RoleConfig> roleConfigs)
            throws IOException, TemplateException {
        Configuration configuration = initConfiguration();
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        Template template = configuration.getTemplate(templateName);
        freemarkerDataModel.put("pojos", pojos);
        freemarkerDataModel.put("config", config);
        freemarkerDataModel.put("roles", roleConfigs);
        File javaSourceFile = new File(outputDirectory, fileName + "." + extensions);
        Writer javaSourceFileWriter = new FileWriter(javaSourceFile);
        template.process(freemarkerDataModel, javaSourceFileWriter);
        javaSourceFileWriter.close();
    }

    @Override
    public void generateFileWithOnePojo(Pojo pojo, String templateName, String templatePath, String generatedFileName,
                                        String outputDirectory, ProjectConfig config, RoleConfig roleConfig)
            throws IOException, TemplateException {
        Configuration configuration = initConfiguration();
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        Template template = configuration.getTemplate(templateName);
        freemarkerDataModel.put("pojo", pojo);
        freemarkerDataModel.put("config", config);
        freemarkerDataModel.put("role", roleConfig);
        File javaSourceFile = new File(outputDirectory, generatedFileName);

        Writer javaSourceFileWriter = new FileWriter(javaSourceFile);
        template.process(freemarkerDataModel, javaSourceFileWriter);
        javaSourceFileWriter.close();
    }

    @Override
    public void generateFileWithOnePojoWithProcess(Pojo pojo, DomainProcess process,String templateName, String templatePath, String generatedFileName,
                                        String outputDirectory, ProjectConfig config, RoleConfig roleConfig)
            throws IOException, TemplateException {
        Configuration configuration = initConfiguration();
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        Template template = configuration.getTemplate(templateName);
        freemarkerDataModel.put("pojo", pojo);
        freemarkerDataModel.put("config", config);
        freemarkerDataModel.put("role", roleConfig);
        freemarkerDataModel.put("process", process);
        File javaSourceFile = new File(outputDirectory, generatedFileName);

        Writer javaSourceFileWriter = new FileWriter(javaSourceFile);
        template.process(freemarkerDataModel, javaSourceFileWriter);
        javaSourceFileWriter.close();
    }
    @Override
    public void generateFileWithOnePojo(Pojo pojo, String fileName, String generatedFolder, String template)
            throws IOException, TemplateException {

        Configuration configuration = initConfiguration();
        Template t = new Template("template", new StringReader(template), configuration);
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        freemarkerDataModel.put("pojo", pojo);
        File javaSourceFile = new File(generatedFolder, fileName);
        Writer javaSourceFileWriter = new FileWriter(javaSourceFile);
        t.process(freemarkerDataModel, javaSourceFileWriter);
        javaSourceFileWriter.close();
    }

    @Override
    public void generateFileWithPermissions(List<Permission> permissions, String roleName, String templateName,
                                            String templatePath, String generatedFileName, String outputDirectory, ProjectConfig config)
            throws IOException, TemplateException {
        Configuration configuration = initConfiguration();
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        Template template = configuration.getTemplate(templateName);
        freemarkerDataModel.put("config", config);
        freemarkerDataModel.put("roleName", roleName);
        freemarkerDataModel.put("permissions", permissions);
        File javaSourceFile = new File(outputDirectory, generatedFileName);
        Writer javaSourceFileWriter = new FileWriter(javaSourceFile);
        template.process(freemarkerDataModel, javaSourceFileWriter);
        javaSourceFileWriter.close();
    }
    @Override
    public void generateFileWithProcess(Pojo pojo, DomainProcess domainProcess, String templateName, String templatePath, String generatedFileName, String outputDirectory, ProjectConfig config)
            throws IOException, TemplateException {
        Configuration configuration = initConfiguration();
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        Template template = configuration.getTemplate(templateName);
        freemarkerDataModel.put("config", config);
        freemarkerDataModel.put("process", domainProcess);
        freemarkerDataModel.put("pojo", pojo);
        File javaSourceFileInput = new File(outputDirectory, generatedFileName);
        Writer javaSourceFileWriterInput = new FileWriter(javaSourceFileInput);
        template.process(freemarkerDataModel, javaSourceFileWriterInput);
        javaSourceFileWriterInput.close();
    }


    @Override
    public void generateFile(List<Pojo> pojos, String fileName, String generatedFolder, String template)
            throws IOException, TemplateException {
        Configuration configuration = initConfiguration();
        Template t = new Template("template", new StringReader(template), configuration);
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        freemarkerDataModel.put("pojos", pojos);
        File javaSourceFile = new File(generatedFolder, fileName);
        Writer javaSourceFileWriter = new FileWriter(javaSourceFile);
        t.process(freemarkerDataModel, javaSourceFileWriter);
        javaSourceFileWriter.close();

    }

    @Override
    public void genereteFileAuthorities(List<RoleConfig> roleConfigs, String templateName, String fileName,
                                        String extensions, String outputDirectory, String templatePath, ProjectConfig config)
            throws IOException, TemplateException {
        Configuration configuration = initConfiguration();
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        Template template = configuration.getTemplate(templateName);
        freemarkerDataModel.put("roles", roleConfigs);
        freemarkerDataModel.put("config", config);

        File javaSourceFile = new File(outputDirectory, fileName + "." + extensions);
        Writer javaSourceFileWriter = new FileWriter(javaSourceFile);
        template.process(freemarkerDataModel, javaSourceFileWriter);
        javaSourceFileWriter.close();
    }



    @Override
    public void generateFileRouting(List<Pojo> pojos, RoleConfig roleConfig, String templateName, String fileName, String extensions, String outputDirectory, String templatePath, Set<SubModule> subModules) throws IOException, TemplateException {
        Configuration configuration = initConfiguration();
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        Template template = configuration.getTemplate(templateName);
        freemarkerDataModel.put("role", roleConfig);
        freemarkerDataModel.put("subModules", subModules);
        File parentFile = new File(outputDirectory);
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        File javaSourceFile = new File(outputDirectory, fileName + "." + extensions);
        Writer javaSourceFileWriter = new FileWriter(javaSourceFile);
        template.process(freemarkerDataModel, javaSourceFileWriter);
        javaSourceFileWriter.close();
    }
    @Override
    public void generateFileRoutingSubModule(Set<SubModule> subModules, SubModule subModule, RoleConfig roleConfig, String templateName, String fileName, String extensions, String outputDirectory, String templatePath) throws IOException, TemplateException {
        Configuration configuration = initConfiguration();
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        Template template = configuration.getTemplate(templateName);
        freemarkerDataModel.put("role", roleConfig);
        freemarkerDataModel.put("subModule", subModule);
        freemarkerDataModel.put("subModules", subModules);

        File javaSourceFile = new File(outputDirectory, fileName + "." + extensions);
        Writer javaSourceFileWriter = new FileWriter(javaSourceFile);
        template.process(freemarkerDataModel, javaSourceFileWriter);
        javaSourceFileWriter.close();
    }
    @Override
    public void generateFileLoginOrRegister(List<Pojo> pojos,RoleConfig roleConfig, String templateName, String fileName, String extensions, String outputDirectory, String templatePath) throws IOException, TemplateException {
        Configuration configuration = initConfiguration();
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        Template template = configuration.getTemplate(templateName);
        freemarkerDataModel.put("role", roleConfig);
        freemarkerDataModel.put("pojos", pojos);

        File javaSourceFile = new File(outputDirectory, fileName + "." + extensions);
        Writer javaSourceFileWriter = new FileWriter(javaSourceFile);
        template.process(freemarkerDataModel, javaSourceFileWriter);
        javaSourceFileWriter.close();
    }



}
