package ma.zs.generator.project.service.impl;

import ma.zs.generator.project.bean.ProjectTemplate;
import ma.zs.generator.project.bean.Technologie;
import ma.zs.generator.project.dao.TechnologieDao;
import ma.zs.generator.project.enumeration.CATEGORY;
import ma.zs.generator.project.service.facade.ProjectTemplateService;
import ma.zs.generator.project.service.facade.TechnologieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * @author Qada
 */
@Service
public class TechnologieServiceImpl implements TechnologieService {

    @Autowired
    private TechnologieDao technologieDao;

    @Autowired
    private ProjectTemplateService templateService;

    @Override
    public Technologie findByName(String name) {
        return technologieDao.findByName(name);
    }

    @Override
    public int save(Technologie technologie) {
        Technologie fondedTechnologie = findByName(technologie.getName());
        if (fondedTechnologie != null)
            return -1;
        else if (technologie.getDefaultTemplate() == null)
            return -2;
        else {

            Technologie savedTechnologie = technologieDao.save(technologie);
            savedTechnologie.getDefaultTemplate().setTechnologie(savedTechnologie);
            templateService.save(savedTechnologie.getDefaultTemplate());
            return 1;
        }
    }

    @Override
    public List<Technologie> findByCategory(CATEGORY category) {
        List<Technologie> technologies = technologieDao.findByCategory(category);
        for (Technologie technologie : technologies) {
            for (ProjectTemplate template : technologie.getTemplates()) {
                String templatePath = template.getPath();
                File templateFolder = new File(templatePath);
                if (templateFolder.exists())
                    template.setTree(templateService.createTreeOfFolder(templateFolder));
            }
        }

        return technologies;
    }

    @Override
    public List<Technologie> findAll() {
        return technologieDao.findAll();
    }

    @Override
    @Transactional
    public int deleteByName(String name) {
        Technologie fondedTechnologie = findByName(name);
        if (fondedTechnologie == null)
            return -1;
        else {
            templateService.deleteByTechnologieName(name);
            technologieDao.deleteById(fondedTechnologie.getId());
            return 1;
        }
    }


}
