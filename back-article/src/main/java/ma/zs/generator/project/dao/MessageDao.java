package ma.zs.generator.project.dao;

import ma.zs.generator.project.bean.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageDao extends JpaRepository<Message, Long> {


    List<Message> findBySenderUsername(String userName);

    int deleteBySenderUsername(String userName);

    List<Message> findBySenderId(Long id);

    int deleteBySenderId(Long id);

    List<Message> findByReceiverUsername(String userName);

    int deleteByReceiverUsername(String userName);

    List<Message> findByReceiverId(Long id);

    int deleteByReceiverId(Long id);

}
