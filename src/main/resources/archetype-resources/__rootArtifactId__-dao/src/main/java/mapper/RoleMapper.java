#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

import ${package}.model.app.vo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends MyMapper<Role> {

    /**
     * 查找用户的角色
     *
     * @param userId
     * @return
     */
    List<Role> selectRoles(Long userId);

    /**
     * 删除用户的所有角色
     *
     * @param userId
     */
    void deleteAllRolesByUserId(Long userId);

    /**
     * 删除角色菜单
     *
     * @param code
     * @param menuCodes
     */
    void deleteRoleMenus(@Param("code") String code, @Param("menuCodes") List<String> menuCodes);

    /**
     * 插入角色菜单
     *
     * @param code
     * @param menuCodes
     */
    void insertRoleMenus(@Param("code") String code, @Param("menuCodes") List<String> menuCodes);
}