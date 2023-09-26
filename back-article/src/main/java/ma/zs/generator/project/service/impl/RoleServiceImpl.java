package ma.zs.generator.project.service.impl;


import ma.zs.generator.project.bean.Role;
import ma.zs.generator.project.dao.RoleDao;
import ma.zs.generator.project.service.facade.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role save(Role role) {
        Role loadedRole = roleDao.findByAuthority(role.getAuthority());
        if (loadedRole == null) {
            roleDao.save(role);
            return role;
        }
        return loadedRole;
    }

}
