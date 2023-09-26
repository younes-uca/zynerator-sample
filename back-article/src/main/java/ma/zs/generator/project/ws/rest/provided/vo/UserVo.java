package ma.zs.generator.project.ws.rest.provided.vo;

import java.util.List;

public class UserVo {

    private String userName;

    private String password;

    private String githubUrl;


    private List<MessageVo> received;
    private List<MessageVo> sent;


    public UserVo() {
        super();
    }


    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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


    public List<MessageVo> getReceived() {
        return received;
    }


    public void setReceived(List<MessageVo> received) {
        this.received = received;
    }


    public List<MessageVo> getSent() {
        return sent;
    }


    public void setSent(List<MessageVo> sent) {
        this.sent = sent;
    }


}
