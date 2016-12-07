#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz.service;

import ${package}.model.app.vo.Menu;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
public interface MenuService {

    /**
     * 查找工作台菜单
     *
     * @param userId
     * @return
     */
    List<Menu> findDashboardMenus(Long userId);

    /**
     * 查找控制台菜单
     *
     * @param userId
     * @return
     */
    List<Menu> findAdminMenus(Long userId);

    /**
     * 查找用户的所有菜单
     *
     * @param userId
     * @return
     */
    List<Menu> findMenusByUserId(Long userId);

    /**
     * 查找角色的控制台菜单
     *
     * @param code
     * @return
     */
    List<Menu> findAdminMenus4Role(String code);

    /**
     * 查找所有控制台菜单
     *
     * @return
     */
    List<Menu> findAllAdminMenus();

    /**
     * 查找角色的工作台菜单
     *
     * @param code
     * @return
     */
    List<Menu> findDashboardMenus4Role(String code);

    /**
     * 查找所有工作台菜单
     *
     * @return
     */
    List<Menu> findAllDashboardMenus();

    /**
     * 查找菜单
     *
     * @param id
     * @return
     */
    Menu findMenuById(Long id);

    /**
     * 保存菜单
     *
     * @param menu
     */
    void saveMenu(Menu menu);

    /**
     * 更新菜单
     *
     * @param menu
     */
    void updateMenu(Menu menu);

    /**
     * 查找菜单
     *
     * @param code
     * @return
     */
    Menu findMenuByCode(String code);

    /**
     * 删除菜单
     *
     * @param menu
     */
    void deleteMenu(Menu menu);

    /**
     * 校验菜单编码是否存在
     *
     * @param code
     * @return
     */
    boolean existsMenuCode(String code);
}
