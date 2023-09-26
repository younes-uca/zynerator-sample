package ma.zs.generator.engine.bean;

import java.util.List;

/**
 * @author MoiseGui
 */
public class Permission {
    private Long id;
    private String name;
    private List<RoleConfig> roles;
    private Pojo pojo;

    public Permission() {
    }

    public Permission(String name,Pojo pojo) {
        this.name = name;
        this.pojo = pojo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoleConfig> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleConfig> roles) {
        this.roles = roles;
    }

    public Pojo getPojo() {
        return pojo;
    }

    public void setPojo(Pojo pojo) {
        this.pojo = pojo;
    }
}
