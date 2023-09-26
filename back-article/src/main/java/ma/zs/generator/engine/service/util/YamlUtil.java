package ma.zs.generator.engine.service.util;

import com.fasterxml.jackson.core.JsonParseException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author amine & oussama
 */

public class YamlUtil {
    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public static <T> List<T> getListOfObjects(String yaml, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        List<T> ts = mapper.readValue(yaml, listType);
        return ts;
    }
}
