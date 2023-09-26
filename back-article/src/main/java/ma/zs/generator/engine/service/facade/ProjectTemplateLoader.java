package ma.zs.generator.engine.service.facade;

import ma.zs.generator.project.bean.ProjectTemplate;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author Qada
 */
public interface ProjectTemplateLoader {

    File load(ProjectTemplate projectTemplate);

    File loadFromGithub(ProjectTemplate projectTemplate) throws MalformedURLException, IOException;

    File loadFromFileSystem(ProjectTemplate projectTemplate);
}
