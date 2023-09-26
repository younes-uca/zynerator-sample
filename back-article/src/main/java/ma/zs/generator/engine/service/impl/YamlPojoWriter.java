/**
 *
 */
package ma.zs.generator.engine.service.impl;

import freemarker.template.TemplateException;
import ma.zs.generator.engine.bean.Pojo;
import ma.zs.generator.engine.bean.RoleConfig;
import ma.zs.generator.engine.service.facade.FreeMarkerService;
import ma.zs.generator.engine.service.facade.PojoWriter;
import ma.zs.generator.engine.service.util.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ouiam
 *
 */
@Service("yamlWriter")
public class YamlPojoWriter implements PojoWriter {
    @Autowired
    private FreeMarkerService freeMarkerService;

    private String templateName = "YamlPojos.yaml.ftl";

    @Override
    public byte[] exportPojoFile(List<Pojo> pojos, String fileType)
            throws IOException, TemplateException {
        freeMarkerService.generateFile(pojos, templateName, "YamlPojos", fileType, generatedFileFolder, templatePath, null,new ArrayList<RoleConfig>());
        return ZipUtil.convertZipToByteArray(new File(generatedFileFolder + "//YamlPojos" + "." + fileType));
    }
}
