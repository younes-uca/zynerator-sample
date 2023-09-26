package ma.zs.generator.project.service.facade;

import ma.zs.generator.project.bean.Message;

import java.util.List;

public interface MessageService {

    /**
     * find all Message in database
     *
     * @return List<Message> , If database is empty return  null.
     */
    List<Message> findAll();


    /**
     * find Message from database by id (id)
     *
     * @param id - id of Message
     * @return the founded  Message , If no Message were
     * found in database return  null.
     */
    Message findById(Long id);

    void deleteById(Long id);

    List<Message> findBySenderUserName(String userName);

    int deleteBySenderUserName(String userName);

    List<Message> findBySenderId(Long id);

    int deleteBySenderId(Long id);

    List<Message> findByReceiverUserName(String userName);

    int deleteByReceiverUserName(String userName);

    List<Message> findByReceiverId(Long id);

    int deleteByReceiverId(Long id);

    /**
     * save Message in database
     *
     * @param message - Message to be saved
     * @return the saved Message, If the Message can't be saved return null.
     */
    Message save(Message message);

    /**
     * save list Message in database
     *
     * @param messages - list of Message to be saved
     * @return the saved Message list
     */
    List<Message> save(List<Message> messages);

    /**
     * update Message in database
     *
     * @param message - Message to be updated
     * @return the updated Message, If the Message can't be updated return null.
     */
    Message update(Message message);

    /**
     * delete Message from database
     *
     * @param message - Message to be deleted
     * @return 1 if Message deleted successfully, If the Message can't be deleted return negative int
     */
    int delete(Message message);


}
