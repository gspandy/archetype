#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model.app.vo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
public class User {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 小头像
     */
    @Column(name = "small_avatar")
    private String smallAvatar;

    /**
     * 中头像
     */
    @Column(name = "medium_avatar")
    private String mediumAvatar;

    /**
     * 大头像
     */
    @Column(name = "large_avatar")
    private String largeAvatar;

    /**
     * 是否锁定:{0:未锁定, 1:已锁定}
     */
    @Column(name = "is_locked")
    private Byte isLocked;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

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
}