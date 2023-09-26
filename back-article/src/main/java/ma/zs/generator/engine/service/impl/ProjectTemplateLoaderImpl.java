package ma.zs.generator.engine.service.impl;

import ma.zs.generator.engine.service.facade.ProjectTemplateLoader;
import ma.zs.generator.project.bean.ProjectTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Qada
 */
@Service
public class ProjectTemplateLoaderImpl implements ProjectTemplateLoader {

    @Override
    public File load(ProjectTemplate projectTemplate) {

        return new File(projectTemplate.getPath());
    }

    @Override
    public File loadFromGithub(ProjectTemplate projectTemplate) throws MalformedURLException, IOException {
//        FileUtils.copyURLToFile(
//                new URL(projectTemplate.getPath()),
//                new File(System.getProperty("user.home") + "\\generated\\" + projectTemplate.getName()),
//                40000,
//                40000);
        return null;
    }

    @Override
    public File loadFromFileSystem(ProjectTemplate projectTemplate) {
        return null;
    }
}
