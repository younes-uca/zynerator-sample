package ma.zs.generator.project.service.impl;

import ma.zs.generator.project.bean.Role;
import ma.zs.generator.project.bean.User;
import ma.zs.generator.project.dao.UserDao;
import ma.zs.generator.project.service.aspect.security.JwtUtil;
import ma.zs.generator.project.service.facade.RoleService;
import ma.zs.generator.project.service.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AuthenticationManager authenticationManager;

    public String authentificate(String login, String pass) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, pass));
        } catch (Exception e) {
            return null;
        }
        UserDetails userDetails = loadUserByUsername(login);
        return JwtUtil.generateToken(userDetails);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }

    @Override
    public int saveWithRoles(User user) {
        if (user == null || user.getUsername() == null || user.getUsername().isEmpty() || user.getPassword() == null
                || user.getPassword().isEmpty()) {
            return -1;
        }
        UserDetails loadedUser = loadUserByUsername(user.getUsername());
        if (loadedUser != null) {
            return -2;
        } else if (user.getAuthorities() != null && !user.getAuthorities().isEmpty()) {
            user.getAuthorities().forEach(r -> {
                Role myRole = roleService.save(r);
                r.setId(myRole.getId());
            });
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);

        return 0;
    }


}
