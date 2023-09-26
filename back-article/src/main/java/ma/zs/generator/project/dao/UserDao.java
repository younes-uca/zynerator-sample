package ma.zs.generator.project.dao;

import ma.zs.generator.project.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);


}
