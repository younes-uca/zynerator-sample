package ma.zs.generator.engine.ws.rest.provided;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.zs.generator.engine.bean.FileEngineConfig;
import ma.zs.generator.engine.service.facade.GeneratorService;
import ma.zs.generator.project.bean.GeneratedProject;
import ma.zs.generator.project.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Qada
 */
@Api("Generate Project or files")
@RestController
@RequestMapping("generator")
public class GeneratorRest {

    @Autowired
    private GeneratorService generator;

    @ApiOperation("this method used by our angular app")
    @PostMapping("/")
    public GeneratedProject generate(@RequestBody UserConfig userConfig) {
        return generator.generate(userConfig);
    }

    @ApiOperation("generate project using yaml file")
    @PostMapping("/backend/{backend}/frontend/{frontend}")
    public byte[] generateProjectUsingYaml(@PathVariable String backend, @PathVariable String frontend, @RequestParam("file") MultipartFile file) {
        return generator.generate(backend, frontend, file);
    }


    @PostMapping("/zip")
    public byte[] generateZip(@RequestBody UserConfig userConfig) {
        return generator.generate(userConfig).getZip();
    }

    @PostMapping("/files")
    public GeneratedProject generate(@RequestBody FileEngineConfig config) {
        return generator.generate(config);
    }


}
