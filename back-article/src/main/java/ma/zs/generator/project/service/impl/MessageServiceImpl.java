package ma.zs.generator.project.service.impl;

import ma.zs.generator.project.bean.Message;
import ma.zs.generator.project.bean.User;
import ma.zs.generator.project.dao.MessageDao;
import ma.zs.generator.project.service.facade.MessageService;
import ma.zs.generator.project.service.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserService userService;


    @Override
    public List<Message> findAll() {
        return messageDao.findAll();
    }

    @Override
    public List<Message> findBySenderUserName(String userName) {
        return messageDao.findBySenderUsername(userName);
    }

    @Override
    @Transactional
    public int deleteBySenderUserName(String userName) {
        return messageDao.deleteBySenderUsername(userName);
    }

    @Override
    public List<Message> findBySenderId(Long id) {
        return messageDao.findBySenderId(id);

    }

    @Override
    @Transactional
    public int deleteBySenderId(Long id) {
        return messageDao.deleteBySenderId(id);

    }

    @Override
    public List<Message> findByReceiverUserName(String userName) {
        return messageDao.findByReceiverUsername(userName);
    }

    @Override
    @Transactional
    public int deleteByReceiverUserName(String userName) {
        return messageDao.deleteByReceiverUsername(userName);
    }

    @Override
    public List<Message> findByReceiverId(Long id) {
        return messageDao.findByReceiverId(id);

    }

    @Override
    @Transactional
    public int deleteByReceiverId(Long id) {
        return messageDao.deleteByReceiverId(id);

    }


    @Override
    public Message findById(Long id) {
        if (id == null)
            return null;
        return messageDao.getOne(id);
    }

    @Transactional
    public void deleteById(Long id) {
        messageDao.deleteById(id);
    }

    @Override
    public Message save(Message message) {

        if (message.getSender() != null) {
            User from = (User) userService.loadUserByUsername(message.getSender().getUsername());
            if (from == null)
                return null;
            else
                message.setSender(from);
        }

        if (message.getReceiver() != null) {
            User to = (User) userService.loadUserByUsername(message.getReceiver().getUsername());
            if (to == null)
                return null;
            else
                message.setReceiver(to);
        }
        message.setDate(new Date());
        Message savedMessage = messageDao.save(message);
        return savedMessage;
    }

    @Override
    public List<Message> save(List<Message> messages) {
        List<Message> list = new ArrayList<Message>();
        for (Message message : messages) {
            list.add(save(message));
        }
        return list;
    }


    @Override
    public Message update(Message message) {

        if (message.getId() == null)
            return null;
        Message foundedMessage = findById(message.getId());
        if (foundedMessage == null)
            return null;

        return messageDao.save(message);

    }

    @Override
    @Transactional
    public int delete(Message message) {

        if (message.getId() == null)
            return -1;
        Message foundedMessage = findById(message.getId());
        if (foundedMessage == null)
            return -1;
        messageDao.delete(foundedMessage);
        return 1;
    }


}
