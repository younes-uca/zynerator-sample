package ma.zs.generator.project.ws.rest.provided.facade;

import ma.zs.generator.project.bean.Technologie;
import ma.zs.generator.project.enumeration.CATEGORY;
import ma.zs.generator.project.service.facade.TechnologieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qada
 */
@RestController
@RequestMapping("generator/technologie")
public class TechnologieRest {

    @Autowired
    private TechnologieService technologieService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/name/{name}")
    public Technologie findByName(@PathVariable String name) {
        return technologieService.findByName(name);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/")
    public int save(@RequestBody Technologie technologie) {
        return technologieService.save(technologie);
    }

    @GetMapping("/category/{category}")
    public List<Technologie> findByCategory(@PathVariable CATEGORY category) {
        return technologieService.findByCategory(category);
    }

    @GetMapping("/")
    public List<Technologie> findAll() {
        return technologieService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/name/{name}")
    public int deleteByName(@PathVariable String name) {
        return technologieService.deleteByName(name);
    }


}
