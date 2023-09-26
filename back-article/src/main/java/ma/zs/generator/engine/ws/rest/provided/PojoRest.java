package ma.zs.generator.engine.ws.rest.provided;

import ma.zs.generator.engine.bean.Pojo;
import ma.zs.generator.engine.service.facade.PojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ouiam
 */
@RestController
@RequestMapping("generator/pojo")
public class PojoRest {
    @Autowired
    PojoService pojoService;

    @PostMapping("/getPojos")
    public List<Pojo> validatePojos(@RequestBody List<Pojo> pojos) {
        return null;
    }
}
