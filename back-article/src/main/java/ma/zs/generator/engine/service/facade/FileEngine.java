package ma.zs.generator.engine.service.facade;

import freemarker.template.TemplateException;
import ma.zs.generator.engine.bean.FileEngineConfig;

import java.io.IOException;

/**
 * @author Qada
 */
public interface FileEngine {


    void generate(FileEngineConfig config, String generatedFolder) throws IOException, TemplateException;
}
