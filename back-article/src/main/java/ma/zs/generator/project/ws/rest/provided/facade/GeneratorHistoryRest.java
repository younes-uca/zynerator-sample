package ma.zs.generator.project.ws.rest.provided.facade;

import ma.zs.generator.project.service.facade.GeneratorHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Qada
 */
@RestController
@RequestMapping("/generator/history")
public class GeneratorHistoryRest {

    @Autowired
    private GeneratorHistoryService generatorHistoryService;


    @GetMapping("/statistics")
    public HashMap<String, Object> statistics() {

        return generatorHistoryService.statistics();

    }

    @GetMapping("/statistics/technologie/{name}")
    public HashMap<String, Object> statisticsOfTechnologie(@PathVariable String name) {
        return generatorHistoryService.statisticsOfTechnologie(name);
    }
}
