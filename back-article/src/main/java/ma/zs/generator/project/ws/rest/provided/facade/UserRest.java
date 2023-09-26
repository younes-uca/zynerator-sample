package ma.zs.generator.project.ws.rest.provided.facade;


import io.swagger.annotations.Api;
import ma.zs.generator.project.bean.User;
import ma.zs.generator.project.service.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@Api("Manages user services")
@RestController
@RequestMapping("generator/user")
public class UserRest {

    @Autowired
    private UserService userService;

    @GetMapping("/username/{username}")
    public UserDetails loadUserByUsername(@PathVariable String username) throws UsernameNotFoundException {
        return userService.loadUserByUsername(username);
    }


    @PostMapping("/")
    public int saveWithRoles(@RequestBody User user) {
        return userService.saveWithRoles(user);
    }

    @PostMapping("authetificate/login/{login}/pass/{pass}")
    public String login(@PathVariable String login, @PathVariable String pass) {
        return userService.authentificate(login, pass);
    }


}
