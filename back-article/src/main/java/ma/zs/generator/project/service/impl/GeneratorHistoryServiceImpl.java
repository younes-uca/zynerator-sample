package ma.zs.generator.project.service.impl;

import ma.zs.generator.project.bean.GeneratorHistory;
import ma.zs.generator.project.bean.ProjectTemplate;
import ma.zs.generator.project.dao.GeneratorHistoryDao;
import ma.zs.generator.project.service.facade.GeneratorHistoryService;
import ma.zs.generator.project.service.facade.ProjectTemplateService;
import ma.zs.generator.project.service.facade.TechnologieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Qada
 */
@Service
public class GeneratorHistoryServiceImpl implements GeneratorHistoryService {

    @Autowired
    private ProjectTemplateService templateService;
    @Autowired
    private GeneratorHistoryDao generatorHistoryDao;
    @Autowired
    private TechnologieService technologieService;

    @Override
    public void save(ProjectTemplate projectTemplate) {
        GeneratorHistory history = new GeneratorHistory();
        history.setProjectTemplate(projectTemplate);
        history.setDate(new Date());
        generatorHistoryDao.save(history);
    }


    @Override
    public HashMap<String, Object> statistics() {
        HashMap<String, Object> statistics = new HashMap<String, Object>();
        statistics.put("GeneratedThisWeek", generatedThisWeek());
        statistics.put("GeneratedThisWeekFiles", 12);
        statistics.put("MostGenerated", technologieService.findByName("SPRING"));
        return statistics;
    }

    public Long generatedThisWeek() {
        Date today = new Date();
        Date weekBefore = new Date(today.getTime() - 24 * 86400 * 1000);
        return generatorHistoryDao.countByDateBetween(weekBefore, today);
    }

    @Override
    public HashMap<String, Object> statisticsOfTechnologie(String name) {
        HashMap<String, Object> statistics = new HashMap<String, Object>();
        statistics.put("GeneratedThisWeek", countAll());
        statistics.put("GeneratedThisWeekFiles", this.generatorHistoryDao.countByProjectTemplateTechnologieName(name));
        statistics.put("MostGenerated", technologieService.findByName(name).getDefaultTemplate());
        return statistics;
    }


    @Override
    public Long countAll() {
        return generatorHistoryDao.countAll();
    }


    @Override
    public List<GeneratorHistory> findAll() {
        return generatorHistoryDao.findAll();
    }


}
