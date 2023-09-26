package ma.zs.generator.project.ws.rest.provided.facade;

import io.swagger.annotations.Api;
import ma.zs.generator.project.bean.Message;
import ma.zs.generator.project.service.facade.MessageService;
import ma.zs.generator.project.ws.rest.provided.converter.MessageConverter;
import ma.zs.generator.project.ws.rest.provided.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Manages message services")
@RestController
@RequestMapping("generated/message")
public class MessageRest {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageConverter messageConverter;

    @PostMapping("/")
    public MessageVo save(@RequestBody MessageVo messageVo) {
        Message message = messageConverter.toItem(messageVo);
        message = messageService.save(message);
        return messageConverter.toVo(message);
    }

    @DeleteMapping("/")
    public int delete(@RequestBody MessageVo messageVo) {
        Message message = messageConverter.toItem(messageVo);
        return messageService.delete(message);
    }

    @PutMapping("/")
    public MessageVo update(@RequestBody MessageVo messageVo) {
        Message message = messageConverter.toItem(messageVo);
        message = messageService.update(message);
        return messageConverter.toVo(message);
    }

    @GetMapping("/")
    public List<MessageVo> findAll() {
        return messageConverter.toVo(messageService.findAll());
    }

    @GetMapping("/id/{id}")
    public MessageVo findById(@PathVariable Long id) {
        return messageConverter.toVo(messageService.findById(id));
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long id) {
        messageService.deleteById(id);
    }


    @GetMapping("/sender/userName/{userName}")
    public List<MessageVo> findBySenderUserName(@PathVariable String userName) {
        return messageConverter.toVo(messageService.findBySenderUserName(userName));
    }

    @DeleteMapping("/sender/userName/{userName}")
    public int deleteBySenderUserName(@PathVariable String userName) {
        return messageService.deleteBySenderUserName(userName);
    }

    @GetMapping("/sender/id/{id}")
    public List<MessageVo> findBySenderId(@PathVariable Long id) {
        return messageConverter.toVo(messageService.findBySenderId(id));
    }

    @DeleteMapping("/sender/id/{id}")
    public int deleteBySenderId(@PathVariable Long id) {
        return messageService.deleteBySenderId(id);
    }

    @GetMapping("/receiver/userName/{userName}")
    public List<MessageVo> findByReceiverUserName(@PathVariable String userName) {
        return messageConverter.toVo(messageService.findByReceiverUserName(userName));
    }

    @DeleteMapping("/receiver/userName/{userName}")
    public int deleteByReceiverUserName(@PathVariable String userName) {
        return messageService.deleteByReceiverUserName(userName);
    }

    @GetMapping("/receiver/id/{id}")
    public List<MessageVo> findByReceiverId(@PathVariable Long id) {
        return messageConverter.toVo(messageService.findByReceiverId(id));
    }

    @DeleteMapping("/receiver/id/{id}")
    public int deleteByReceiverId(@PathVariable Long id) {
        return messageService.deleteByReceiverId(id);
    }


    public MessageConverter getMessageConverter() {
        return messageConverter;
    }

    public void setMessageConverter(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }


}
