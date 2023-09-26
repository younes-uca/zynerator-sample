package ma.zs.generator.engine.service.facade;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import ma.zs.generator.engine.bean.*;
import ma.zs.generator.project.config.ProjectConfig;

/**
 * @author Zouani
 */
public interface FreeMarkerService {
	Configuration initConfiguration() throws IOException;

	void generateFile(List<Pojo> pojos, String templateName, String fileName, String extension, String outputDirectory,
			String templatePath, ProjectConfig config, List<RoleConfig> roleConfigs)
			throws IOException, TemplateException;

	void generateFileWithOnePojo(Pojo pojo, String templateName, String templatePath, String generatedFileName,
			String outputDirectory, ProjectConfig config,RoleConfig roleConfigs) throws IOException, TemplateException;

	void generateFileWithOnePojoWithProcess(Pojo pojo, DomainProcess process,String templateName, String templatePath, String generatedFileName,
			String outputDirectory, ProjectConfig config,RoleConfig roleConfigs) throws IOException, TemplateException;

	void generateFileWithOnePojo(Pojo pojo, String fileName, String generatedFolder, String template)
			throws IOException, TemplateException;

	void generateFileWithProcess(Pojo pojo, DomainProcess domainProcess, String templateName, String templatePath, String generatedFileName, String outputDirectory, ProjectConfig config)
			throws IOException, TemplateException;

	void generateFile(List<Pojo> pojos, String fileName, String generatedFolder, String template)
			throws IOException, TemplateException;

	void generateFileWithPermissions(List<Permission> permissions, String roleName, String templateName,
			String templatePath, String generatedFileName, String outputDirectory, ProjectConfig config)
			throws IOException, TemplateException;

	void genereteFileAuthorities(List<RoleConfig> roleConfigs, String templateName, String fileName, String extensions,
			String outputDirectory, String templatePath, ProjectConfig config) throws IOException, TemplateException;


	void generateFileRouting(List<Pojo> pojos, RoleConfig roleConfig, String templateName, String fileName, String extensions, String outputDirectory, String templatePath, Set<SubModule> subModules) throws IOException, TemplateException;

	void generateFileRoutingSubModule(Set<SubModule> subModules, SubModule subModule, RoleConfig roleConfig, String templateName, String fileName, String extensions, String outputDirectory, String templatePath) throws IOException, TemplateException;

	void generateFileLoginOrRegister(List<Pojo> pojos, RoleConfig roleConfig, String templateName, String fileName, String extensions, String outputDirectory, String templatePath) throws IOException, TemplateException;

}
