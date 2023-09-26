package ma.zs.generator.engine.service.impl;

import freemarker.template.TemplateException;
import ma.zs.generator.engine.bean.*;
import ma.zs.generator.engine.service.facade.FreeMarkerService;
import ma.zs.generator.engine.service.facade.TemplateEngineService;
import ma.zs.generator.engine.service.util.EngineUtil;
import ma.zs.generator.engine.service.util.FileUtil;
import ma.zs.generator.engine.service.util.StringFormatterUtil;
import ma.zs.generator.engine.service.util.StringUtil;
import ma.zs.generator.project.config.ProjectConfig;
import ma.zs.generator.project.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Qada
 */
@Service
public class TemplateEngineServiceImpl implements TemplateEngineService {

    @Autowired
    private FreeMarkerService freeMarkerService;

    @Override
    public void generate(File templateFolder, String generatedFolder, UserConfig userConfig) throws IOException, TemplateException {
        FileUtil.createDirectory(generatedFolder);
        scanTemplate(templateFolder, templateFolder.getPath(), generatedFolder, userConfig);
    }


    @Override
    public void scanTemplate(File template, String projectTemplatePath, String outputFolder, UserConfig userConfig) throws IOException, TemplateException {

        List<File> childs = Arrays.asList(template.listFiles());

        List<Pojo> pojos = userConfig.getPojos();//.stream().filter(p->p.isArchivable()).collect(Collectors.toList()); // ERROR to be del
        ProjectConfig config = userConfig.getConfig();
        List<RoleConfig> roleConfigs = userConfig.getRoles();

        for (File file : childs) {
            if (file.isDirectory()) {
                FileUtil.createDirectory(replaceAllPlaceHoldersInPath(file.getPath(), config)
                        .replace(projectTemplatePath, outputFolder));
                scanTemplate(file, projectTemplatePath, outputFolder, userConfig);

            } else {
                if (EngineUtil.isRoleRoutingSubModule(file.getName())) {//routting
                    for (RoleConfig roleConfig : roleConfigs) {

                        Set<SubModule> subModules = roleConfig.getSubModules();

                        for (SubModule subModule : subModules) {
                            String fileName = EngineUtil.getSuffixOrName(file.getName()).replace("sub-module", subModule.getFolder() + "-" + roleConfig.getName());
                            String extension = EngineUtil.getExtension(file.getName());
                            String outputDirectory = replaceAllPlaceHoldersInPath(
                                    file.getParent().replace(projectTemplatePath, outputFolder), config) + File.separator + (roleConfig.getName()).toLowerCase();

                            String parentWithRoleAndView = outputDirectory + File.separator + "view" + File.separator + subModule.getFolder();
                            if (!subModule.getPojos().isEmpty()) {
                                FileUtil.createDirectory(parentWithRoleAndView);
                                freeMarkerService.generateFileRoutingSubModule(subModules, subModule, roleConfig, file.getName(), fileName, extension, parentWithRoleAndView, file.getParent() + File.separator);
                            }
                        }
                    }
                } else if (EngineUtil.isRoleRouting(file.getName())) {//routting
                    for (RoleConfig roleConfig : roleConfigs) {
                        //appmodule and app-routting
                        String fileName = EngineUtil.getSuffixOrName(file.getName()).replace("rl-rt", roleConfig.getName());
                        String extension = EngineUtil.getExtension(file.getName());
                        String outputDirectory = replaceAllPlaceHoldersInPath(
                                file.getParent().replace(projectTemplatePath, outputFolder), config) + File.separator + (roleConfig.getName()).toLowerCase();

                        freeMarkerService.generateFileRouting(pojos, roleConfig, file.getName(), fileName, extension, outputDirectory, file.getParent() + File.separator, roleConfig.getSubModules());
                    }
                } else if (EngineUtil.isPermissionRole(file.getName())) {//dyialk
                    for (RoleConfig roleConfig : roleConfigs) {
                        generateForAllPermission(file, roleConfig.getPermissions(), projectTemplatePath, outputFolder,
                                config, roleConfig);
                    }
                } else if (EngineUtil.isRestNest(file.getName())) {
                    for (RoleConfig roleConfig : roleConfigs) {
                        String outputDirectory = replaceAllPlaceHoldersInPath(file.getParent().replace(projectTemplatePath, outputFolder), config);
                        String partentWithRole = outputDirectory + File.separator + roleConfig.getName();
                        FileUtil.createDirectory(partentWithRole);
                        String partentWithRoleAndImpl = partentWithRole + File.separator + "ws";
                        FileUtil.createDirectory(partentWithRoleAndImpl);
                        generateFilesInFoldersForRestNest(file, pojos, projectTemplatePath, outputFolder, config,
                                roleConfig, partentWithRoleAndImpl);
                    }
                } else if (EngineUtil.isServiceImplNestJs(file.getName())) {
                    for (RoleConfig roleConfig : roleConfigs) {
                        String outputDirectory = replaceAllPlaceHoldersInPath(file.getParent().replace(projectTemplatePath, outputFolder), config);
                        String partentWithRole = outputDirectory + File.separator + roleConfig.getName();
                        FileUtil.createDirectory(partentWithRole);
                        String partentWithRoleAndImpl = partentWithRole + File.separator + "service";
                        FileUtil.createDirectory(partentWithRoleAndImpl);
                        generateFilesInFoldersForServiceImplNest(file, pojos, projectTemplatePath, outputFolder, config,
                                roleConfig, partentWithRoleAndImpl);
                    }
                } else if (EngineUtil.isServiceImpl(file.getName())) {
                    for (RoleConfig roleConfig : roleConfigs) {
                        String outputDirectory = replaceAllPlaceHoldersInPath(file.getParent().replace(projectTemplatePath, outputFolder), config);
                        String partentWithRole = outputDirectory + File.separator + roleConfig.getName();
                        FileUtil.createDirectory(partentWithRole);
                        String partentWithRoleAndImpl = partentWithRole + File.separator;// + "impl";
                        FileUtil.createDirectory(partentWithRoleAndImpl);
                        generateFilesInFoldersForServiceImpl(file, pojos, projectTemplatePath, outputFolder, config,
                                roleConfig, partentWithRoleAndImpl);
                    }
                } else if (EngineUtil.isService(file.getName()) || EngineUtil.isServiceFront(file.getName())) {
                    for (RoleConfig roleConfig : roleConfigs) {
                        String outputDirectory = replaceAllPlaceHoldersInPath(file.getParent().replace(projectTemplatePath, outputFolder), config);
                        String partentWithRole = outputDirectory + File.separator + roleConfig.getName();
                        FileUtil.createDirectory(partentWithRole);
                        String partentWithRoleAndFacade = partentWithRole + File.separator;//+ "facade";
                        FileUtil.createDirectory(partentWithRoleAndFacade);
                        generateFilesInFoldersForServiceFacade(file, pojos, projectTemplatePath, outputFolder, config,
                                roleConfig, partentWithRoleAndFacade);
                    }
                } else if (EngineUtil.isProcess(file.getName())) {
                    for (RoleConfig roleConfig : roleConfigs) {
                        String outputDirectory = replaceAllPlaceHoldersInPath(file.getParent().replace(projectTemplatePath, outputFolder), config);
                        String partentWithRole = outputDirectory + File.separator + roleConfig.getName();
                        FileUtil.createDirectory(partentWithRole);
                        String partentWithRoleAndImpl = partentWithRole + File.separator;// + "impl";
                        FileUtil.createDirectory(partentWithRoleAndImpl);
                        System.out.println("ouuuuuuut " + partentWithRoleAndImpl);
                        generateFilesInFoldersForProcess(file, pojos, projectTemplatePath, outputFolder, config,
                                roleConfig, partentWithRoleAndImpl);
                    }

                } else if (EngineUtil.isLogin(file.getName())) {//login or register
                    for (RoleConfig roleConfig : roleConfigs) {
                        String fileName = EngineUtil.getSuffixOrName(file.getName()).replace("role", roleConfig.getName());
                        String extension = EngineUtil.getExtension(file.getName());
                        String outputDirectory = replaceAllPlaceHoldersInPath(
                                file.getParent().replace(projectTemplatePath, outputFolder), config) + File.separator
                                + (roleConfig.getName()).toLowerCase() + File.separator + "login-" + roleConfig.getName();
                        FileUtil.createDirectory(outputDirectory);

                        freeMarkerService.generateFileLoginOrRegister(pojos, roleConfig, file.getName(), fileName, extension, outputDirectory, file.getParent() + File.separator);
                    }
                } else if (EngineUtil.isRegister(file.getName())) {//login or register
                    for (RoleConfig roleConfig : roleConfigs) {
                        String fileName = EngineUtil.getSuffixOrName(file.getName()).replace("role", roleConfig.getName());
                        String extension = EngineUtil.getExtension(file.getName());
                        String outputDirectory = replaceAllPlaceHoldersInPath(
                                file.getParent().replace(projectTemplatePath, outputFolder), config) + File.separator
                                + (roleConfig.getName()).toLowerCase() + File.separator + "register-" + roleConfig.getName();
                        FileUtil.createDirectory(outputDirectory);

                        freeMarkerService.generateFileLoginOrRegister(pojos, roleConfig, file.getName(), fileName, extension, outputDirectory, file.getParent() + File.separator);
                    }
                } else if (EngineUtil.isExterne(file.getName())) {
                    String outputDirectory = replaceAllPlaceHoldersInPath(file.getParent().replace(projectTemplatePath, outputFolder), config);
                    FileUtil.createDirectory(outputDirectory);
                    generateFilesInFoldersForExterne(file, pojos, projectTemplatePath, outputFolder, config,
                            null, outputDirectory);
                } else {
                    if (EngineUtil.isAuthorities(file.getName())) {
                        generateAuthorities(file, roleConfigs, projectTemplatePath, outputFolder, config);

                    } else {
                        if (!EngineUtil.isTemplate(file.getName()))
                            copyFile(file, projectTemplatePath, outputFolder, config);
                        else {
                            if (EngineUtil.isTemplateForMany(file.getName())) {
                                generateForAllPojos(file, pojos, projectTemplatePath, outputFolder, config,
                                        roleConfigs);

                            } else if (EngineUtil.isOptional(file.getName()))
                                generateOptional(file, pojos, projectTemplatePath, outputFolder, config, roleConfigs);
                            else {
                                System.out.println("src from scan  *** " + projectTemplatePath);
                                System.out.println("dest *** " + outputFolder);
                                String outputDirectory = replaceAllPlaceHoldersInPath(file.getParent().replace(projectTemplatePath, outputFolder), config);
                                generateOne(file, pojos, projectTemplatePath, outputFolder, config, roleConfigs, outputDirectory);
                            }
                        }
                    }
                }
            }

        }

    }


    private void generateFilesInFoldersForServiceFacade(File file, List<Pojo> pojos, String src, String dest, ProjectConfig config, RoleConfig roleConfig, String outputDirectory) throws TemplateException, IOException {

        String suffix = EngineUtil.getSuffixOrName(file.getName());
        if (EngineUtil.isPlaceHolder(suffix))
            suffix = replacePlaceholderWithTheEquivalentValue(EngineUtil.getPlaceHolder(suffix), config);
        String extensions = EngineUtil.getExtension(file.getName());
        //  String fileParentwithRole = file.getParent() + File.separator + roleConfig.getName()+File.separator+"facade";

        if (EngineUtil.isTemplateForMany(file.getName())) {
            for (Pojo pojo : pojos) {
                if ((pojo.getRoles().isEmpty() || pojo.getRoles().contains(roleConfig.getName()))) {
                    String newOutputDirectory = createOutputWithBackPakage(outputDirectory, pojo);
                    freeMarkerService.generateFileWithOnePojo(pojo, file.getName(), file.getParent() + File.separator,
                            pojo.getName() + StringFormatterUtil.upperCaseTheFirstLetter(roleConfig.getName()) + suffix + "." + extensions, newOutputDirectory, config, roleConfig);
                }
            }
        }
    }

    private void generateFilesInFoldersForExterne(File file, List<Pojo> pojos, String src, String dest, ProjectConfig config, RoleConfig roleConfig, String outputDirectory) throws TemplateException, IOException {
        List<String> distinctMsPackages = extractMsPackages(pojos);
        for (String distinctMsPackage : distinctMsPackages) {
            FileUtil.createDirectory(outputDirectory + File.separator + distinctMsPackage);
        }
        String suffix = file.getName().contains("Dto") ? "Dto" : file.getName().contains("Facade") ? "Required" : "Criteria";
        for (Pojo pojo : pojos) {
            if (pojo.isMsExterne() == true) {// && pojo.isInMsDependency() == true
                String newOutputDirectory = createOutputWithBackPakage(outputDirectory + File.separator + pojo.getMsPackaging(), pojo);
                freeMarkerService.generateFileWithOnePojo(pojo, file.getName(), file.getParent() + File.separator,
                        pojo.getName() + suffix + ".java", newOutputDirectory, config, roleConfig);
            }
        }
    }

    private List<String> extractMsPackages(List<Pojo> pojos) {
        List<String> result = new ArrayList<>();
        for (Pojo pojo : pojos) {
            if (pojo.isMsExterne() == true && result.indexOf(pojo.getMsPackaging()) == -1) {
                result.add(pojo.getMsPackaging());
            }
        }
        return result;
    }

    private void generateFilesInFoldersForServiceImpl(File file, List<Pojo> pojos, String src, String dest, ProjectConfig config, RoleConfig roleConfig, String outputDirectory) throws IOException, TemplateException {
        String suffix = EngineUtil.getSuffixOrName(file.getName());
        if (EngineUtil.isPlaceHolder(suffix))
            suffix = replacePlaceholderWithTheEquivalentValue(EngineUtil.getPlaceHolder(suffix), config);
        String extensions = EngineUtil.getExtension(file.getName());

        if (EngineUtil.isTemplateForMany(file.getName())) {
            for (Pojo pojo : pojos) {
                if ((pojo.getRoles().isEmpty() || pojo.getRoles().contains(roleConfig.getName()))) {
                    String newOutputDirectory = createOutputWithBackPakage(outputDirectory, pojo);
                    freeMarkerService.generateFileWithOnePojo(pojo, file.getName(), file.getParent() + File.separator,
                            pojo.getName() + StringFormatterUtil.upperCaseTheFirstLetter(roleConfig.getName()) + suffix + "." + extensions, newOutputDirectory, config, roleConfig);
                }
            }
        }
    }

    private void generateFilesInFoldersForServiceImplNest(File file, List<Pojo> pojos, String src, String dest, ProjectConfig config, RoleConfig roleConfig, String outputDirectory) throws IOException, TemplateException {
        String suffix = "ServiceImpl";
        String extensions = "ts";
        if (EngineUtil.isTemplateForMany(file.getName())) {
            for (Pojo pojo : pojos) {
                if ((pojo.getRoles().isEmpty() || pojo.getRoles().contains(roleConfig.getName()))) {
                    String newOutputDirectory = createOutputWithBackPakage(outputDirectory, pojo);
                    freeMarkerService.generateFileWithOnePojo(pojo, file.getName(), file.getParent() + File.separator,
                            pojo.getName() + StringFormatterUtil.upperCaseTheFirstLetter(roleConfig.getName()) + suffix + "." + extensions, newOutputDirectory, config, roleConfig);
                }
            }
        }
    }

    private String createOutputWithBackPakage(String outputDirectory, Pojo pojo) throws IOException {
        // String packagingBackName = pojo.getPackagingBack().getName();
        String packagingBackName = pojo.getSubModule().getName();
        String newOutputDirectory = outputDirectory + (packagingBackName == null ? "" : File.separator + packagingBackName);
        FileUtil.createDirectory(newOutputDirectory);
        return newOutputDirectory;
    }

    private void generateFilesInFoldersForRestNest(File file, List<Pojo> pojos, String src, String dest, ProjectConfig config, RoleConfig roleConfig, String outputDirectory) throws IOException, TemplateException {
        String suffix = "Rest";
        String extensions = "ts";
        if (EngineUtil.isTemplateForMany(file.getName())) {
            for (Pojo pojo : pojos) {
                if (pojo.isMsExterne() == false && (pojo.getRoles().isEmpty() || pojo.getRoles().contains(roleConfig.getName()))) {
                    String newOutputDirectory = createOutputWithBackPakage(outputDirectory, pojo);
                    freeMarkerService.generateFileWithOnePojo(pojo, file.getName(), file.getParent() + File.separator,
                            pojo.getName() + StringFormatterUtil.upperCaseTheFirstLetter(roleConfig.getName()) + suffix + "." + extensions, newOutputDirectory, config, roleConfig);
                }
            }
        }
    }

    private void generateFilesInFoldersForProcess(File file, List<Pojo> pojos, String src, String dest, ProjectConfig config, RoleConfig roleConfig, String outputDirectory) throws IOException, TemplateException {
        String suffix = EngineUtil.getSuffixOrName(file.getName());
        if (EngineUtil.isPlaceHolder(suffix))
            suffix = replacePlaceholderWithTheEquivalentValue(EngineUtil.getPlaceHolder(suffix), config);
        String extensions = EngineUtil.getExtension(file.getName());
        for (Pojo pojo : pojos) {
            if (pojo.isMsExterne() == false && (pojo.getDomainProcesses() != null && !pojo.getDomainProcesses().isEmpty())) {
                for (DomainProcess domainProcess : pojo.getDomainProcesses()) {
                    boolean domainProcessInRole = domainProcess.getRoles().contains(roleConfig.getName()) || (pojo.getRoles().isEmpty() && domainProcess.getRoles().isEmpty());
                    if (domainProcessInRole) {
                        String outPutProcessStatic = outputDirectory + File.separator + "process";
                        FileUtil.createDirectory(outPutProcessStatic);
                        String outPutPojo = outPutProcessStatic + File.separator + pojo.getName().toLowerCase();
                        FileUtil.createDirectory(outPutPojo);
                        String outPutPojoProcess = outPutPojo + File.separator + domainProcess.getName().toLowerCase();
                        FileUtil.createDirectory(outPutPojoProcess);
                        String generatedFileName = pojo.getName() + StringFormatterUtil.upperCaseTheFirstLetter(domainProcess.getName()) + StringFormatterUtil.upperCaseTheFirstLetter(roleConfig.getName()) + construcFileEnd(extensions);//+ "." + extensions;
                        freeMarkerService.generateFileWithOnePojoWithProcess(pojo, domainProcess, file.getName(), file.getParent() + File.separator,
                                generatedFileName, outPutPojoProcess, config, roleConfig);
                    }

                }
            }


        }
    }

    private String construcFileEnd(String extension) {
        String res = ".java";
        if (extension != null) {
            if (extension.contains("impl")) {
                res = "ProcessImpl.java";
            } else if (extension.contains("input")) {
                res = "Input.java";
            } else if (extension.contains("output")) {
                res = "Output.java";
            } else if (extension.contains("interface")) {
                res = "Process.java";
            } else if (extension.contains("injector")) {
                res = "Injector.java";
            } else if (extension.contains("converter")) {
                res = "Converter.java";
            }
        }

        return res;
    }


    private void generateAuthorities(File file, List<RoleConfig> roleConfigs, String projectTemplatePath,
                                     String outputFolder, ProjectConfig config) throws IOException, TemplateException {
        String fileName = EngineUtil.getSuffixOrName(file.getName());
        String extension = EngineUtil.getExtension(file.getName());

        String outputDirectory = replaceAllPlaceHoldersInPath(
                file.getParent().replace(projectTemplatePath, outputFolder), config);
        freeMarkerService.genereteFileAuthorities(roleConfigs, file.getName(), fileName, extension, outputDirectory,
                file.getParent() + File.separator, config);

    }

    private void copyFile(File file, String src, String dest, ProjectConfig config) throws IOException {
        System.out.println(
                "copy " + file.getName() + " from " + file.getParent() + " to " + file.getParent().replace(src, dest));

        FileUtil.copyFile(file.getPath(), replaceAllPlaceHoldersInPath(file.getParent().replace(src, dest), config)
                + File.separator + file.getName());

    }


    // file = template
    // src\main\resources\templates\backend\spring\default\src\main\java\{domain}\{groupId}\{projectName}\{ws}\{rest}\{provided}\roles
    // src************ src\main\resources\templates\backend\spring\default
    // des outputFoldel C:\Users\a\generated\1627578176949\backend
    private void generateForAllPojos(File file, List<Pojo> pojos, String src, String dest, ProjectConfig config,
                                     List<RoleConfig> roleConfigs) throws IOException, TemplateException {
        System.out.println("generate all " + file.getName());
        String suffix = EngineUtil.getSuffixOrName(file.getName());
        if (EngineUtil.isPlaceHolder(suffix))
            suffix = replacePlaceholderWithTheEquivalentValue(EngineUtil.getPlaceHolder(suffix), config);
        String extensions = EngineUtil.getExtension(file.getName());

        String outputDirectory = replaceAllPlaceHoldersInPath(file.getParent().replace(src, dest), config);
        System.out.println("out derectory " + dest);
        System.out.println("out derectory " + src);

        if (!EngineUtil.isComponent(file.getName())) {// not a component
            System.out.println("file name = " + file.getName() + "extention  " + suffix + " " + extensions);
            System.out.println("foldeeer service facade " + outputDirectory);
            FileUtil.createDirectory(outputDirectory);
            for (RoleConfig roleConfig : roleConfigs) {
                for (Pojo pojo : pojos) {
                    if (pojo.getRoles().isEmpty() || pojo.getRoles().contains(roleConfig.getName())) {
                        boolean generateFile = false;
                        if (isHistoryTemplate(file.getName())) {
                            if (pojo.isHistory()) {
                                generateFile = true;
                            }
                        } else {
                            generateFile = true;
                        }

                        if (generateFile) {
                            System.out.println("pojo == " + pojo.getName() + " id ==" + (pojo.getId() == null ? "null" : pojo.getId().getName()));
                            String newOutputDirectory = createOutputWithBackPakage(outputDirectory, pojo);
                            freeMarkerService.generateFileWithOnePojo(pojo, file.getName(), file.getParent() + File.separator,
                                    pojo.getName() + suffix + "." + extensions, newOutputDirectory, config, roleConfig);

                        }

                    }
                }
            }
        } else { // cpn
            for (RoleConfig roleConfig : roleConfigs) {
                //appmodule and app-routting
                String parentWithRole = outputDirectory + File.separator + (roleConfig.getName()).toLowerCase();
                FileUtil.createDirectory(parentWithRole);
                String parentWithRoleAndView = parentWithRole + File.separator + "view";
                FileUtil.createDirectory(parentWithRoleAndView);

                for (Pojo pojo : pojos) { //azemi
                    if (pojo.isIgnoreFront() == false) {
                        String parentWithRoleAndViewAndSubModuleFolder = StringUtil.isEmpty(pojo.getSubModule().getFolder()) ? parentWithRoleAndView : parentWithRoleAndView + File.separator + pojo.getSubModule().getFolder();
                        if (StringUtil.isNotEmpty(pojo.getSubModule().getFolder()))
                            FileUtil.createDirectory(parentWithRoleAndViewAndSubModuleFolder);
                        if (pojo.getRoles().isEmpty() || pojo.getRoles().contains(roleConfig.getName())) {
                            generateFilesInFoldersForComponent(pojo, file.getName(), suffix, extensions, parentWithRoleAndViewAndSubModuleFolder,
                                    file.getParent() + File.separator, config, roleConfig);
                        }
                    }
                }

            }

        }

    }

    private boolean isHistoryTemplate(String name) {
        return name.contains("History");
    }


    private void generateForAllPermission(File file, List<Permission> permissions, String src, String dest,
                                          ProjectConfig config, RoleConfig roleConfig) throws IOException, TemplateException {

        String outputDirectory = replaceAllPlaceHoldersInPath(file.getParent().replace(src, dest), config);
        String roleName = roleConfig.getName();
        outputDirectory = outputDirectory + File.separator + roleName;
        FileUtil.createDirectory(outputDirectory);
        List<String> pojoName = new ArrayList<>();

        for (int i = 0; i < permissions.size(); i++) {
            if (!pojoName.contains(permissions.get(i).getPojo().getName())) {
                pojoName.add(permissions.get(i).getPojo().getName());
            }
            for (String nom : pojoName) {
                List<Permission> p = permissions.stream()
                        .filter(permission -> permission.getPojo().getName().equals(nom)).collect(Collectors.toList());
                if (p.get(0).getPojo().isBaseEntityNoTable() == false && (p.get(0).getPojo().getRoles().isEmpty() || p.get(0).getPojo().getRoles().contains(roleName))) {
                    String newOutputDirectory = createOutputWithBackPakage(outputDirectory, p.get(0).getPojo());
                    freeMarkerService.generateFileWithPermissions(p, roleName, file.getName(),
                            file.getParent() + File.separator,
                            StringFormatterUtil.upperCaseTheFirstLetter(nom) + "Rest"
                                    + StringFormatterUtil.upperCaseTheFirstLetter(roleName) + ".java",
                            newOutputDirectory, config);
                }

            }

        }

    }

    private void generateOptional(File file, List<Pojo> pojos, String src, String dest, ProjectConfig config, List<RoleConfig> roleConfigs)
            throws IOException, TemplateException {
        String fileName = file.getName();
        if (isValidOptional(EngineUtil.getCondition(fileName), config)) {
            fileName = EngineUtil.cutCondition(fileName);
            if (EngineUtil.isPlaceHolder(fileName))
                fileName = replacePlaceholderWithTheEquivalentValue(
                        EngineUtil.getPlaceHolder(EngineUtil.getSuffixOrName(fileName)), config);
            else
                fileName = EngineUtil.getSuffixOrName(fileName);
            String extension = EngineUtil.getExtension(EngineUtil.cutCondition(file.getName()).replaceAll("[{}]", ""));
            String outputDirectory = replaceAllPlaceHoldersInPath(file.getParent().replace(src, dest), config);
            System.out.println(" generate " + fileName + "." + extension + " in " + outputDirectory);

            freeMarkerService.generateFile(pojos, file.getName(), fileName, extension, outputDirectory,
                    file.getParent() + File.separator, config, roleConfigs);

        }

    }

    private void generateOne(File file, List<Pojo> pojos, String src, String dest, ProjectConfig config,
                             List<RoleConfig> roleConfig, String outputDirectory) throws IOException, TemplateException {
        String fileName = file.getName();
        if (EngineUtil.isPlaceHolder(fileName))
            fileName = replacePlaceholderWithTheEquivalentValue(
                    EngineUtil.getPlaceHolder(EngineUtil.getSuffixOrName(fileName)), config);
        else
            fileName = EngineUtil.getSuffixOrName(fileName);

        String extension = EngineUtil.getExtension(file.getName().replaceAll("[{}]", ""));
        System.out.println(" generate " + fileName + "." + extension + " in " + outputDirectory);
        freeMarkerService.generateFile(pojos, file.getName(), fileName, extension, outputDirectory,
                file.getParent() + File.separator, config, roleConfig);
    }

    private void generateFilesInFoldersForComponent(Pojo pojo, String templateName, String suffix, String extensions,
                                                    String outputDirectoryWithRole, String templatePath, ProjectConfig config, RoleConfig roleConfig)
            throws IOException, TemplateException {

        String roleToLower = roleConfig.getName().toLowerCase();
        //hada howa li kan String parentWithPojoAndRole = outputDirectoryWithRole + File.separator + StringUtil.formatUrl(pojo.getName()) + "-" + roleToLower;
        String parentWithPojoAndRole = outputDirectoryWithRole + File.separator + StringUtil.formatUrl(pojo.getName());
        FileUtil.createDirectory(parentWithPojoAndRole);
        System.out.println("templateName++++++++++++++++++++++++++" + templateName);
        if (EngineUtil.inFolder(templateName)) {
            //hada howa li kan String pageName = parentWithPojoAndRole + File.separator + EngineUtil.getFolder(templateName) + "-" + roleToLower;
            String pageName = parentWithPojoAndRole + File.separator + EngineUtil.getFolder(templateName);
            FileUtil.createDirectory(pageName);
            System.out.println("for pojo : " + pojo.getName() + " outputDirectory :" + pageName);
            String generatedFileName = StringUtil.formatUrl(pojo.getName()) + "-" + suffix + "-" + roleToLower + "." + extensions;
            if (suffix != null && suffix.equals("index")) {
                generatedFileName = suffix + ".tsx";
            }
            freeMarkerService.generateFileWithOnePojo(pojo, templateName, templatePath, generatedFileName,
                    pageName, config, roleConfig);
        } else {
            if (StringUtil.isEmpty(suffix))
                freeMarkerService.generateFileWithOnePojo(pojo, templateName, templatePath,
                        StringUtil.formatUrl(pojo.getName()) + "-" + StringUtil.formatUrl(roleConfig.getName()) + "." + extensions, parentWithPojoAndRole,
                        config, roleConfig);
            else
                freeMarkerService.generateFileWithOnePojo(pojo, templateName, templatePath, suffix + "." + extensions,
                        parentWithPojoAndRole, config, roleConfig);

        }
    }

    private void generateFilesInFoldersForRest(Pojo pojo, String templateName, String suffix, String extensions,
                                               String outputDirectory, String templatePath, ProjectConfig config, RoleConfig roleConfig)
            throws IOException, TemplateException {

        String roleToLower = roleConfig.getName().toLowerCase();
        String parentWithPojoAndRole = outputDirectory + File.separator + StringUtil.formatUrl(pojo.getName() + "-" + roleToLower);
        FileUtil.createDirectory(parentWithPojoAndRole);
        System.out.println("templateName++++++++++++++++++++++++++" + templateName);
        if (EngineUtil.inFolder(templateName)) {
            // frontend component folder like commandes
            String pageName = parentWithPojoAndRole + File.separator + EngineUtil.getFolder(templateName) + "-" + roleToLower;
            FileUtil.createDirectory(pageName);
            System.out.println("outputDirectory cmptttttt" + pageName);
            freeMarkerService.generateFileWithOnePojo(pojo, templateName, templatePath,
                    StringUtil.formatUrl(pojo.getName()) + "-" + suffix + "-" + roleToLower + "." + extensions,
                    pageName, config, roleConfig);
        } else {
            if (StringUtil.isEmpty(suffix))
                freeMarkerService.generateFileWithOnePojo(pojo, templateName, templatePath,
                        StringUtil.formatUrl(pojo.getName()) + "-" + StringUtil.formatUrl(roleConfig.getName()) + "." + extensions, parentWithPojoAndRole,
                        config, roleConfig);
            else
                freeMarkerService.generateFileWithOnePojo(pojo, templateName, templatePath, suffix + "." + extensions,
                        parentWithPojoAndRole, config, roleConfig);

        }
    }

    private void generateFilesInFolders(Pojo pojo, String templateName, String suffix, String extensions,
                                        String outputDirectory, String templatePath, ProjectConfig config, RoleConfig roleConfig)
            throws IOException, TemplateException {
        String parentWithRole = outputDirectory + File.separator + (roleConfig.getName()).toLowerCase();

        //FileUtil.createDirectory(parentWithRole);

        String roleToLower = roleConfig.getName().toLowerCase();
        String parentWithPojoAndRole = parentWithRole + File.separator + StringUtil.formatUrl(pojo.getName() + "-" + roleToLower);
        FileUtil.createDirectory(parentWithPojoAndRole);
        System.out.println("templateName++++++++++++++++++++++++++" + templateName);
        if (EngineUtil.inFolder(templateName)) {
            // frontend component folder like commandes
            String pageName = parentWithPojoAndRole + File.separator + EngineUtil.getFolder(templateName) + "-" + roleToLower;
            FileUtil.createDirectory(pageName);
            System.out.println("outputDirectory cmptttttt" + pageName);
            freeMarkerService.generateFileWithOnePojo(pojo, templateName, templatePath,
                    StringUtil.formatUrl(pojo.getName()) + "-" + suffix + "-" + roleToLower + "." + extensions,
                    pageName, config, roleConfig);
        } else {
            if (StringUtil.isEmpty(suffix))
                freeMarkerService.generateFileWithOnePojo(pojo, templateName, templatePath,
                        StringUtil.formatUrl(pojo.getName()) + "-" + StringUtil.formatUrl(roleConfig.getName()) + "." + extensions, parentWithPojoAndRole,
                        config, roleConfig);
            else
                freeMarkerService.generateFileWithOnePojo(pojo, templateName, templatePath, suffix + "." + extensions,
                        parentWithPojoAndRole, config, roleConfig);

        }
    }


    private String replaceAllPlaceHoldersInPath(String path, ProjectConfig projectConfig) {
        if (!path.contains("{") || !path.contains("}"))
            return path;
        else {
            String[] names = path.split(File.separator + File.separator);
            String newPath = names[0];
            for (int i = 1; i < names.length; i++) {
                if (EngineUtil.isPlaceHolder(names[i]))
                    newPath += File.separator + replacePlaceholderWithTheEquivalentValue(
                            EngineUtil.getPlaceHolder(names[i]), projectConfig);
                else
                    newPath += File.separator + names[i];

            }
            return newPath;
        }
    }

    private String replacePlaceholderWithTheEquivalentValue(String name, ProjectConfig projectConfig) {
        Class clazz = projectConfig.getClass();
        String fieldValue;

        Field field;
        try {
            field = clazz.getDeclaredField(name);
            fieldValue = (String) field.get(projectConfig);
            return fieldValue;
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return name;
        }

    }

    private boolean isValidOptional(String condition, ProjectConfig projectConfig) {
        Class clazz = projectConfig.getClass();
        boolean fieldValue;

        Field field;
        try {
            field = clazz.getDeclaredField(condition);
            fieldValue = (boolean) field.get(projectConfig);
            return fieldValue;
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }
}
