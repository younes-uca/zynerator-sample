package ma.zs.generator.project.ws.rest.provided.vo;

import java.util.Date;


public class MessageVo {

    private Long id;

    private Boolean vu;

    private Date date;
    private String content;
    private UserVo sender;

    private UserVo receiver;

    private String dateMax;
    private String dateMin;

    public MessageVo() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVu() {
        return vu;
    }

    public void setVu(Boolean vu) {
        this.vu = vu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public UserVo getSender() {
        return sender;
    }

    public void setSender(UserVo sender) {
        this.sender = sender;
    }

    public UserVo getReceiver() {
        return receiver;
    }

    public void setReceiver(UserVo receiver) {
        this.receiver = receiver;
    }

    public String getDateMax() {
        return this.dateMax;
    }

    public void setDateMax(String dateMax) {
        this.dateMax = dateMax;
    }

    public String getDateMin() {
        return this.dateMin;
    }

    public void setDateMin(String dateMin) {
        this.dateMin = dateMin;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
