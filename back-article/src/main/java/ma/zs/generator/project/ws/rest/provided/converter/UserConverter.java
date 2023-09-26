package ma.zs.generator.project.ws.rest.provided.converter;

import ma.zs.generator.project.bean.User;
import ma.zs.generator.project.service.util.ListUtil;
import ma.zs.generator.project.service.util.StringUtil;
import ma.zs.generator.project.ws.rest.provided.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConverter<User, UserVo> {

    @Autowired
    private MessageConverter messageConverter;
    private Boolean received;
    private Boolean sent;

    public UserConverter() {
        init(true);
    }

    @Override
    public User toItem(UserVo vo) {
        if (vo == null) {
            return null;
        } else {
            User item = new User();
            if (StringUtil.isNotEmpty(vo.getUserName()))
                item.setUsername(vo.getUserName());
            if (StringUtil.isNotEmpty(vo.getPassword()))
                item.setPassword(vo.getPassword());
            if (StringUtil.isNotEmpty(vo.getGithubUrl()))
                item.setGithubUrl(vo.getGithubUrl());


            return item;
        }
    }

    @Override
    public UserVo toVo(User item) {
        if (item == null) {
            return null;
        } else {
            UserVo vo = new UserVo();

            if (StringUtil.isNotEmpty(item.getUsername()))
                vo.setUserName(item.getUsername());

            if (StringUtil.isNotEmpty(item.getGithubUrl()))
                vo.setGithubUrl(item.getGithubUrl());


            if (ListUtil.isNotEmpty(item.getReceived()) && this.received) {

                messageConverter.init(false);
                vo.setReceived(messageConverter.toVo(item.getReceived()));
                messageConverter.init(true);
            }
            if (ListUtil.isNotEmpty(item.getSent()) && this.sent) {

                messageConverter.init(false);
                vo.setSent(messageConverter.toVo(item.getSent()));
                messageConverter.init(true);
            }

            return vo;

        }
    }

    public void init(Boolean value) {
        received = value;
        sent = value;
    }


    public MessageConverter getMessageConverter() {
        return this.messageConverter;
    }

    public void setMessageConverter(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    public Boolean isReceived() {
        return this.received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    public Boolean isSent() {
        return this.sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }
}
