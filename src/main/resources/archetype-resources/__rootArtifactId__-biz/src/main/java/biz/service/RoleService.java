#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz.service;

import ${package}.model.app.vo.Menu;
import ${package}.model.app.vo.Role;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
public interface RoleService {

    /**
     * 查找用户的角色
     *
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(Long userId);

    /**
     * 查找所有角色
     *
     * @return
     */
    List<Role> findAllRoles();

    /**
     * 搜索角色
     *
     * @param pageNum
     * @param pageSize
     * @param code
     * @param name
     * @return
     */
    List<Role> searchRoles(int pageNum, int pageSize, String code, String name);

    /**
     * 保存角色
     *
     * @param role
     */
    void saveRole(Role role);

    /**
     * 根据ID查找角色
     *
     * @param id
     * @return
     */
    Role findRoleById(Long id);

    /**
     * 更新角色
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 判断是否存在角色编码
     *
     * @param code
     * @return
     */
    boolean existsRoleCode(String code);

    /**
     * 更新角色菜单
     *
     * @param code
     * @param role_menus
     * @param menuCodes
     */
    void updateRoleMenus(String code, List<Menu> role_menus, String menuCodes);
}
