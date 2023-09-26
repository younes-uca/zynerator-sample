package ma.zs.generator.engine.bean;

import java.util.List;

/**
 * @author Qada
 */
public class FileEngineConfig {

    private String nameOrSuffix;
    private String template;
    private String extension;
    private boolean foreachEntities;
    private boolean foreachRolesPermission;
    private List<Pojo> pojos;
    private List<RoleConfig> roleConfigs;


    public FileEngineConfig() {
        super();
    }

    public String getNameOrSuffix() {
        return nameOrSuffix;
    }

    public void setNameOrSuffix(String nameOrSuffix) {
        this.nameOrSuffix = nameOrSuffix;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean isForeachRolesPermission() {
        return foreachRolesPermission;
    }

    public void setForeachRolesPermission(boolean foreachRolesPermission) {
        this.foreachRolesPermission = foreachRolesPermission;
    }

    public boolean isForeachEntities() {
        return foreachEntities;
    }

    public void setForeachEntities(boolean foreachEntities) {
        this.foreachEntities = foreachEntities;
    }

    public List<Pojo> getPojos() {
        return pojos;
    }

    public void setPojos(List<Pojo> pojos) {
        this.pojos = pojos;
    }

    public List<RoleConfig> getRoleConfigs() {
        return roleConfigs;
    }

    public void setRoleConfigs(List<RoleConfig> roleConfigs) {
        this.pojos = pojos;
    }


}
