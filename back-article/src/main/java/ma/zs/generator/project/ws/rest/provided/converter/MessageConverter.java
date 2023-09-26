package ma.zs.generator.project.ws.rest.provided.converter;

import ma.zs.generator.project.bean.Message;
import ma.zs.generator.project.service.util.StringUtil;
import ma.zs.generator.project.ws.rest.provided.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter extends AbstractConverter<Message, MessageVo> {

    @Autowired
    private UserConverter userConverter;
    private Boolean from;
    private Boolean to;

    public MessageConverter() {
        init(true);
    }

    @Override
    public Message toItem(MessageVo vo) {
        if (vo == null) {
            return null;
        } else {
            Message item = new Message();

            item.setVu(vo.getVu());
            if (vo.getDate() != null)
                item.setDate(vo.getDate());
            if (StringUtil.isNotEmpty(vo.getContent()))
                item.setContent(vo.getContent());
            if (vo.getSender() != null && this.from)
                item.setSender(userConverter.toItem(vo.getSender()));
            if (vo.getReceiver() != null && this.to)
                item.setReceiver(userConverter.toItem(vo.getReceiver()));


            return item;
        }
    }

    @Override
    public MessageVo toVo(Message item) {
        if (item == null) {
            return null;
        } else {
            MessageVo vo = new MessageVo();


            vo.setVu(item.isVu());
            if (item.getDate() != null)
                vo.setDate(item.getDate());
            if (StringUtil.isNotEmpty(item.getContent()))
                vo.setContent(item.getContent());
            if (item.getId() != null)
                vo.setId(item.getId());
            if (item.getSender() != null && this.from) {
                vo.setSender(userConverter.toVo(item.getSender()));
            }
            if (item.getReceiver() != null && this.to) {
                vo.setReceiver(userConverter.toVo(item.getReceiver()));
            }

            return vo;

        }
    }

    public void init(Boolean value) {
        from = value;
        to = value;
    }


    public UserConverter getUserConverter() {
        return this.userConverter;
    }

    public void setUserConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public boolean isSender() {
        return this.from;
    }

    public void setSender(boolean from) {
        this.from = from;
    }

    public boolean isReceiver() {
        return this.to;
    }

    public void setReceiver(boolean to) {
        this.to = to;
    }
}
