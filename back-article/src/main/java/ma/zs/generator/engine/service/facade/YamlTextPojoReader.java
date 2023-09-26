package ma.zs.generator.engine.service.facade;

import ma.zs.generator.engine.bean.Pojo;

import java.io.IOException;
import java.util.List;

/**
 * @author Ouiam & Zouani
 */
public interface YamlTextPojoReader {
    public List<Pojo> convert(String yamlAsText) throws IOException;
}
