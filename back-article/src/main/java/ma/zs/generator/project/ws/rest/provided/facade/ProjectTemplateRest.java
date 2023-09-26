package ma.zs.generator.project.ws.rest.provided.facade;

import ma.zs.generator.project.bean.Node;
import ma.zs.generator.project.bean.ProjectTemplate;
import ma.zs.generator.project.enumeration.CATEGORY;
import ma.zs.generator.project.service.facade.ProjectTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qada
 */
@RestController
@RequestMapping("generator/template")
public class ProjectTemplateRest {

    @Autowired
    private ProjectTemplateService projectTemplateService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/")
    public int save(@RequestBody ProjectTemplate projectTemplate) {
        return projectTemplateService.save(projectTemplate);
    }

    @GetMapping("/name/{templateName}/technologie/name/{technologieName}")
    public ProjectTemplate findByNameAndTechnologieName(@PathVariable String templateName, @PathVariable String technologieName) {
        return projectTemplateService.findByNameAndTechnologieName(templateName, technologieName);
    }

    @GetMapping("/category/{category}")
    public List<ProjectTemplate> findByTechnologieCategory(@PathVariable CATEGORY category) {
        return projectTemplateService.findByTechnologieCategory(category);
    }

    @GetMapping("/technologie/name/{name}")
    public List<ProjectTemplate> findByTechnologieName(@PathVariable String name) {
        return projectTemplateService.findByTechnologieName(name);
    }

    @GetMapping("/")
    public List<ProjectTemplate> findAll() {
        return projectTemplateService.findAll();
    }

    @GetMapping("/structure/{id}")
    public List<Node> getStructureOfTemplate(@PathVariable Long id) {
        return this.projectTemplateService.getStructureOfTemplate(id);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/name/{templateName}/technologie/name/{technologieName}")
    public int deleteByNameAndTechnologieName(@PathVariable String templateName, @PathVariable String technologieName) {
        return projectTemplateService.deleteByNameAndTechnologieName(templateName, technologieName);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/technologie/name/{technologieName}")
    public int deleteByTechnologieName(@PathVariable String technologieName) {
        return projectTemplateService.deleteByTechnologieName(technologieName);
    }
}
