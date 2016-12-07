#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz.service;

import ${package}.model.app.vo.Message;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/6
 */
public interface MessageService {

    /**
     * 搜索消息
     *
     * @param pageNum
     * @param pageSize
     * @param title
     * @param type
     * @return
     */
    List<Message> searchMessages(int pageNum, int pageSize, String title, String type);

    /**
     * 查询用户的消息
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param type
     * @param isRead
     * @return
     */
    List<Message> findMessagesByUserId(Long userId, int pageNum, int pageSize, String type, String isRead);

    /**
     * 推送消息
     *
     * @param message
     * @param receiverUsers
     */
    void saveMessageWithReceiverUsers(Message message, String receiverUsers);

    /**
     * 查找消息
     *
     * @param id
     * @return
     */
    Message findMessageById(Long id);

    /**
     * 标记为已读
     *
     * @param ids
     */
    void updateMessageForRead(String ids);
}
