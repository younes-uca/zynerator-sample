package ma.zs.generator.engine.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amine & oussama
 */

public class JsonUtil {
    public static <T> List<T> cvrtJson(String jsontext, Class<T> clazz) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        List<T> list = (List<T>) mapper.readValue(jsontext, listType);
        return list;
    }
}
