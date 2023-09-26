package ma.zs.generator.engine.bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author MoiseGui
 */
public class RoleConfig {
    private Long id;
    private String name;
    private Set<SubModule> subModules;
    private Set<PackaginBack> packaginBacks;

    private List<Permission> permissions;
    private List<MenuRole> menuRoles;

    public Set<PackaginBack> getPackaginBacks() {
        if (packaginBacks == null) {
            packaginBacks = new HashSet<>();
        }
        return packaginBacks;
    }

    public void setPackaginBacks(Set<PackaginBack> packaginBacks) {
        this.packaginBacks = packaginBacks;
    }

    public Set<SubModule> getSubModules() {
        if (subModules == null) {
            subModules = new HashSet<>();
        }
        return subModules;
    }

    public void setSubModules(Set<SubModule> subModules) {
        this.subModules = subModules;
    }

    public List<MenuRole> getMenuRoles() {
		return menuRoles;
	}

	public void setMenuRoles(List<MenuRole> menuRoles) {
		this.menuRoles = menuRoles;
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
