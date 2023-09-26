package ma.zs.generator.engine.bean;

import java.util.ArrayList;
import java.util.List;

public class DomainProcess {
    private String name;
    private String mapping;
    private List<String> roles;

    public DomainProcess(String name, String mapping) {
        this.name = name;
        this.mapping = mapping;
    }


    public DomainProcess(String name) {
        this.name = name;
    }

    public DomainProcess() {

    }

    public List<String> getRoles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }
}
