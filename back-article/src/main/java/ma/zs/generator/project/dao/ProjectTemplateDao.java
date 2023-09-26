package ma.zs.generator.project.dao;

import ma.zs.generator.project.bean.ProjectTemplate;
import ma.zs.generator.project.enumeration.CATEGORY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Qada
 */
@Repository
public interface ProjectTemplateDao extends JpaRepository<ProjectTemplate, Long> {

    ProjectTemplate findByNameAndTechnologieName(String templateName, String technologieName);

    List<ProjectTemplate> findByTechnologieCategory(CATEGORY category);

    List<ProjectTemplate> findByTechnologieName(String name);
}
