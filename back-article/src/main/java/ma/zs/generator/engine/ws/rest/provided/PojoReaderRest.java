/**
 *
 */
package ma.zs.generator.engine.ws.rest.provided;

import ma.zs.generator.engine.bean.Pojo;
import ma.zs.generator.engine.service.impl.YamlPojoReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Ouiam
 *
 */
@RestController
@RequestMapping("generator/pojo")
public class PojoReaderRest {


    @Autowired
    YamlPojoReader yamlService;

    @PostMapping("/uploadFile")
    public List<Pojo> readPojo(@RequestParam("file") MultipartFile file) {
        if (file.getOriginalFilename().endsWith(".yaml")) {
            try {
                return yamlService.getPojosFile(file);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        } else if (file.getOriginalFilename().endsWith(".json")) {

        }
        return null;
    }
}
