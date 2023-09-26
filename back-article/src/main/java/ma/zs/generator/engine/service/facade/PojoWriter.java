/**
 *
 */
package ma.zs.generator.engine.service.facade;

import freemarker.template.TemplateException;
import ma.zs.generator.engine.bean.Pojo;

import java.io.IOException;
import java.util.List;

/**
 * @author Ouiam
 *
 */
public interface PojoWriter {

    String generatedFileFolder = System.getProperty("user.home");
    String templatePath = "src/main/resources/pojos/export";

    byte[] exportPojoFile(List<Pojo> pojos, String fileType) throws IOException, TemplateException;
}
