package ma.zs.generator.project.service.facade;

import ma.zs.generator.project.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {


    public int saveWithRoles(User user);

    public String authentificate(String login, String pass);


}
