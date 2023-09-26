package ma.zs.generator.project.service.impl;

import ma.zs.generator.project.bean.Node;
import ma.zs.generator.project.bean.ProjectTemplate;
import ma.zs.generator.project.bean.Technologie;
import ma.zs.generator.project.dao.ProjectTemplateDao;
import ma.zs.generator.project.enumeration.CATEGORY;
import ma.zs.generator.project.service.facade.ProjectTemplateService;
import ma.zs.generator.project.service.facade.TechnologieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Qada
 */
@Service
public class ProjectTemplateServiceImpl implements ProjectTemplateService {

    @Autowired
    private ProjectTemplateDao projectTemplateDao;

    @Autowired
    private TechnologieService technologieService;

    @Override
    public int save(ProjectTemplate projectTemplate) {
        if (projectTemplate.getName() == null || projectTemplate.getTechnologie() == null)
            return -1;
        ProjectTemplate foundedTemplate = findByNameAndTechnologieName(projectTemplate.getName(),
                projectTemplate.getTechnologie().getName());
        if (foundedTemplate != null)
            return -2;
        else {
            Technologie technologie = technologieService.findByName(projectTemplate.getTechnologie().getName());
            if (technologie == null)
                return -3;
            projectTemplate.setTechnologie(technologie);
            projectTemplateDao.save(projectTemplate);
            return 1;
        }
    }

    @Override
    public ProjectTemplate findByNameAndTechnologieName(String templateName, String technologieName) {
        return projectTemplateDao.findByNameAndTechnologieName(templateName, technologieName);
    }

    @Override
    public List<ProjectTemplate> findByTechnologieCategory(CATEGORY category) {
        return projectTemplateDao.findByTechnologieCategory(category);
    }

    @Override
    public List<ProjectTemplate> findByTechnologieName(String name) {
        return projectTemplateDao.findByTechnologieName(name);
    }

    @Override
    public List<ProjectTemplate> findAll() {
        return projectTemplateDao.findAll();
    }

    @Override
    @Transactional
    public int deleteByNameAndTechnologieName(String templateName, String technologieName) {
        ProjectTemplate fProjectTemplate = findByNameAndTechnologieName(templateName, technologieName);
        if (fProjectTemplate == null)
            return -1;
        else if (fProjectTemplate.getTechnologie().getDefaultTemplate().getId() == fProjectTemplate.getId())
            return -2;
        else {
            projectTemplateDao.deleteById(fProjectTemplate.getId());
            return 1;
        }
    }

    @Override
    @Transactional
    public int deleteByTechnologieName(String technologieName) {

        List<ProjectTemplate> templates = findByTechnologieName(technologieName);
        if (templates == null || templates.size() == 0)
            return -1;
        else {
            for (ProjectTemplate projectTemplate : templates) {
                if (projectTemplate.getTechnologie().getDefaultTemplate().getId() != projectTemplate.getId()) {
                    projectTemplateDao.deleteById(projectTemplate.getId());
                }
            }
            return 1;
        }
    }

    @Override
    public List<Node> getStructureOfTemplate(Long id) {
        ProjectTemplate fProjectTemplate = projectTemplateDao.getOne(id);
        if (fProjectTemplate != null) {
            File folder = new File(fProjectTemplate.getPath());
            if (folder.exists())
                return createTreeOfFolder(folder);
            else
                return null;

        } else
            return null;

    }

    @Override
    public List<Node> createTreeOfFolder(File folder) {
        List<Node> nodes = new ArrayList<Node>();
        List<File> childs = Arrays.asList(folder.listFiles());
        Node node;
        for (File file : childs) {
            node = new Node();
            node.setLabel(file.getName());
            if (file.isDirectory()) {
                node.setExpandedIcon("pi pi-folder-open");
                node.setCollapsedIcon("pi pi-folder");
                node.setChildren(createTreeOfFolder(file));
            } else {
                node.setIcon("pi pi-file");
            }
            nodes.add(node);
        }
        return nodes;
    }


}
