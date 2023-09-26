package ma.zs.generator.project.dao;

import ma.zs.generator.project.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    public Role findByAuthority(String authority);
}
