#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

import ${package}.model.app.vo.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper extends MyMapper<Message> {

    /**
     * 搜索消息
     *
     * @param userId 接收人ID
     * @param title
     * @param type
     * @param isRead
     * @return
     */
    List<Message> searchMessage(@Param("userId") Long userId, @Param("title") String title,
                                @Param("type") String type, @Param("isRead") String isRead);

    /**
     * 发送消息给用户
     *
     * @param messageId
     * @param senderUserId
     * @param receiverUserIds
     */
    void insertMessageUsers(@Param("messageId") Long messageId, @Param("senderUserId") Long senderUserId,
                            @Param("receiverUserIds") List<String> receiverUserIds);

    /**
     * 查找消息
     *
     * @param userId
     * @param messageId
     * @return
     */
    Message selectMessageById(@Param("userId") Long userId, @Param("messageId") Long messageId);

    /**
     * 更新为已读
     *
     * @param userId
     * @param messageIds
     */
    void updateMessageForRead(@Param("userId") Long userId, @Param("messageIds") List messageIds);
}