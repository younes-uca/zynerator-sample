package ma.zs.generator.engine.service.facade;

import freemarker.template.TemplateException;
import ma.zs.generator.engine.bean.Pojo;
import ma.zs.generator.engine.bean.RoleConfig;
import ma.zs.generator.project.config.ProjectConfig;
import ma.zs.generator.project.config.UserConfig;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Qada
 */
public interface TemplateEngineService {

    void generate(File templateFolder, String generatedFolder, UserConfig userConfig) throws IOException, TemplateException;

    void scanTemplate(File template, String projectTemplatePath,  String outputFolder, UserConfig userConfig) throws IOException, TemplateException;

}
