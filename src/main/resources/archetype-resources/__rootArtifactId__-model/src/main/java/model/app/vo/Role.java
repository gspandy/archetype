#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model.app.vo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
public class Role {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色代码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

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