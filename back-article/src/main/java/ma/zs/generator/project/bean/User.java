package ma.zs.generator.project.bean;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String githubUrl;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    @OneToMany(mappedBy = "receiver")
    private List<Message> received;
    @OneToMany(mappedBy = "sender")
    private List<Message> sent;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> authorities;

    public User() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGithubUrl() {
        return this.githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public List<Message> getReceived() {
        return this.received;
    }

    public void setReceived(List<Message> received) {
        this.received = received;
    }

    public List<Message> getSent() {
        return this.sent;
    }

    public void setSent(List<Message> sent) {
        this.sent = sent;
    }


    @Override
    public Collection<Role> getAuthorities() {
        if (authorities == null) {
            authorities = new ArrayList<Role>();
        }
        return authorities;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}

