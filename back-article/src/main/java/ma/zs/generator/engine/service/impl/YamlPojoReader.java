package ma.zs.generator.engine.service.impl;

import ma.zs.generator.engine.bean.Field;
import ma.zs.generator.engine.bean.Pojo;
import ma.zs.generator.engine.bean.PojoConfig;
import ma.zs.generator.engine.service.facade.PojoReader;
import ma.zs.generator.engine.service.facade.PojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * @author Ouiam & Zouani
 */
@Service
public class YamlPojoReader implements PojoReader {
    @Autowired
    PojoService pojoService;

    public List<Pojo> getPojosFile(MultipartFile file) throws IOException {
        Path temp = Paths.get("src\\main\\resources\\pojos\\file.yaml");
        file.transferTo(temp);
        final File yamlFile = new File(temp.toString());
        Map<String, Map<String, String>> yamlPojos = parseYaml(new FileReader(yamlFile));
        System.out.println("yamlPojos = " + yamlPojos);
        List<Pojo> pojos = linkPojoToField(yamlPojos);
        pojoService.prepare(pojos);
        return pojos;
    }


    private Map<String, Map<String, String>> parseYaml(Reader reader) {
        Yaml yaml = new Yaml();
        // key: pojoName , Map : fieldName, fieldType
        Map<String, Map<String, String>> yamlPojos = (Map<String, Map<String, String>>) yaml.load(reader);
        return yamlPojos;
    }

    private List<Pojo> linkPojoToField(Map<String, Map<String, String>> yamlPojos) {
        if (yamlPojos == null)
            yamlPojos = new HashMap<>();
        final Map<String, List<Field>> pojoField = yamlPojos.entrySet().stream()
                .collect(toMap(this::pojoName, this::fields));
        return pojoField.entrySet().stream().map(e -> new Pojo(e.getKey(), e.getValue())).collect(toList());
    }

    private String pojoName(Map.Entry<String, Map<String, String>> yamlOuterMapEntry) {
        return yamlOuterMapEntry.getKey();
    }

    private List<Field> fields(Map.Entry<String, Map<String, String>> yamlOuterMapEntry) {
        Map<String, String> yamlFields = yamlOuterMapEntry.getValue();
        if (yamlFields == null)
            return new ArrayList<>();
        return yamlFields.entrySet().stream().map(e -> new Field(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

}
