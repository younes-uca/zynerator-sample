package ma.zs.generator.project.config;

import ma.zs.generator.engine.bean.*;
import ma.zs.generator.project.bean.ProjectTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MoiseGui
 */
public class UserConfig {

    private List<Pojo> pojos;

    private ProjectTemplate backend;
    private boolean wantBackend = true;
    private Admin admin;
    private List<RoleConfig> roles;
    private ProjectConfig config;

    private List<ConfigurationMs> configurationMss;

    public List<ConfigurationMs> getConfigurationMss() {
        return configurationMss;
    }

    public void setConfigurationMss(List<ConfigurationMs> configurationMss) {
        this.configurationMss = configurationMss;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public UserConfig() {
        super();
        this.config = new ProjectConfig();
    }

    public List<Pojo> getPojos() {
        return pojos;
    }

    public void setPojos(List<Pojo> pojos) {
        this.pojos = pojos;
    }

    public ProjectTemplate getBackend() {
        return backend;
    }

    public void setBackend(ProjectTemplate backend) {
        this.backend = backend;
    }

    public boolean isWantBackend() {
        return wantBackend;
    }

    public void setWantBackend(boolean wantBackend) {
        this.wantBackend = wantBackend;
    }

    public ProjectConfig getConfig() {
        return config;
    }

    public void setConfig(ProjectConfig config) {
        this.config = config;
    }

    public List<RoleConfig> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleConfig> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserConfig{" +
                "pojos=" + pojos +
                ", backend=" + backend +
                ", wantBackend=" + wantBackend +
                ", config=" + config +
                '}';
    }
}
