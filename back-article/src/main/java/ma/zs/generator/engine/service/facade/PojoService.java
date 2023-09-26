package ma.zs.generator.engine.service.facade;

import ma.zs.generator.engine.bean.MenuRole;
import ma.zs.generator.engine.bean.Permission;
import ma.zs.generator.engine.bean.Pojo;
import ma.zs.generator.engine.bean.RoleConfig;

import java.util.List;

/**
 * @author Zouani
 */
public interface PojoService {


    void preparePackageBacks(List<Pojo> pojos, RoleConfig r);

    List<Pojo> prepare(List<Pojo> pojos);

    List<Pojo> validatePojos(List<Pojo> pojos);
    List<Permission> validatePojosForPermissions(List<Permission> permissions);

    List<Permission> constructPermissions(RoleConfig r, List<Pojo> pojos);

    void prepareSubModulesAndPackage(List<Pojo> pojos, RoleConfig r);

    void prepareSubModules(List<Pojo> pojos, RoleConfig r);

}
