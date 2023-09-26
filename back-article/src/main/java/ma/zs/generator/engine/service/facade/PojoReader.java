/**
 *
 */
package ma.zs.generator.engine.service.facade;

import ma.zs.generator.engine.bean.Pojo;
import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Ouiam
 *
 */

public interface PojoReader {
    public List<Pojo> getPojosFile(MultipartFile file) throws IOException, ParseException;
}
