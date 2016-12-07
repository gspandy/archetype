#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model.app.vo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
public class Message {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 是否删除:{0:未删除, 1:已删除}
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 发送人ID
     */
    @Transient
    private Long senderUserId;

    /**
     * 发送人
     */
    @Transient
    private String senderNickName;

    /**
     * 发送人小头像
     */
    @Transient
    private String senderAvatar;
}