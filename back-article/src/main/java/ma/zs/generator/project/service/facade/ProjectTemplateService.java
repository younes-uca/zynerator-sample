package ma.zs.generator.project.service.facade;

import ma.zs.generator.project.bean.Node;
import ma.zs.generator.project.bean.ProjectTemplate;
import ma.zs.generator.project.enumeration.CATEGORY;

import java.io.File;
import java.util.List;

/**
 * @author Qada
 */
public interface ProjectTemplateService {

    int save(ProjectTemplate projectTemplate);

    ProjectTemplate findByNameAndTechnologieName(String templateName, String technologieName);

    List<ProjectTemplate> findByTechnologieCategory(CATEGORY category);

    List<ProjectTemplate> findByTechnologieName(String name);

    List<ProjectTemplate> findAll();

    int deleteByNameAndTechnologieName(String templateName, String technologieName);

    int deleteByTechnologieName(String technologieName);

    public List<Node> createTreeOfFolder(File folder);

    List<Node> getStructureOfTemplate(Long id);

}
