#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz.service.impl;

import ${package}.biz.service.MenuService;
import ${package}.mapper.MenuMapper;
import ${package}.model.app.vo.Menu;
import ${package}.model.constants.AppConstants;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
@Service
@Log4j2
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findDashboardMenus(Long userId) {
        List<Menu> menus = menuMapper.selectMenus(userId, AppConstants.MENU_TYPE_DASHBOARD);
        List<Menu> wrapList = new ArrayList();

        return recursionList(menus, wrapList, "DASHBOARD", 0L);
    }

    @Override
    public List<Menu> findAdminMenus(Long userId) {
        List<Menu> menus = menuMapper.selectMenus(userId, AppConstants.MENU_TYPE_ADMIN);
        List<Menu> wrapList = new ArrayList();

        return recursionList(menus, wrapList, "ADMIN", 0L);
    }

    @Override
    public List<Menu> findMenusByUserId(Long userId) {
        return menuMapper.selectMenus(userId, AppConstants.MENU_TYPE_ALL);
    }

    @Override
    public List<Menu> findAdminMenus4Role(String code) {
        return menuMapper.selectMenus4Role(code, AppConstants.MENU_TYPE_ADMIN);
    }

    @Override
    public List<Menu> findAllAdminMenus() {
        Menu menu = new Menu();
        menu.setType(AppConstants.MENU_TYPE_ADMIN);
        List<Menu> menus = super.select(menu);

        List<Menu> wrapList = new ArrayList();
        return recursionTreeList(menus, wrapList, "", 0L);
    }

    @Override
    public List<Menu> findDashboardMenus4Role(String code) {
        return menuMapper.selectMenus4Role(code, AppConstants.MENU_TYPE_DASHBOARD);
    }

    @Override
    public List<Menu> findAllDashboardMenus() {
        Menu menu = new Menu();
        menu.setType(AppConstants.MENU_TYPE_DASHBOARD);
        List<Menu> menus = super.select(menu);

        List<Menu> wrapList = new ArrayList();
        return recursionTreeList(menus, wrapList, "", 0L);
    }

    @Override
    public Menu findMenuById(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public void saveMenu(Menu menu) {
        super.insertSelective(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        super.updateByPrimaryKeySelective(menu);
    }

    @Override
    public Menu findMenuByCode(String code) {
        Menu menu = new Menu();
        menu.setCode(code);

        return super.selectOne(menu);
    }

    @Override
    public void deleteMenu(Menu menu) {
        super.deleteByPrimaryKey(menu.getId());
    }

    @Override
    public boolean existsMenuCode(String code) {
        Menu menu = new Menu();
        menu.setCode(code);

        return menuMapper.selectCount(menu) == 1;
    }

    /**
     * 递归找出 parentCode 下边的所有子节点
     *
     * @param from
     * @param toList
     * @param pcode
     * @param pid
     * @return
     */
    private List<Menu> recursionList(List<Menu> from, List<Menu> toList, String pcode, Long pid) {

        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Menu menu = from.get(i);
            if (pcode.equals(menu.getPcode())) {
                List<Menu> leaf = new ArrayList();
                menu.setLeaf(leaf);
                menu.setPid(pid);
                toList.add(menu);
                recursionList(from, leaf, menu.getCode(), menu.getId());
            }
        }
        return toList;
    }

    /**
     * 递归找出 parentCode 下边的所有子节点
     *
     * @param from
     * @param toList
     * @param pcode
     * @param pid
     * @return
     */
    private List<Menu> recursionTreeList(List<Menu> from, List<Menu> toList, String pcode, Long pid) {

        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Menu menu = from.get(i);
            if (pcode.equals(menu.getPcode())) {
                menu.setPid(pid);
                toList.add(menu);
                recursionTreeList(from, toList, menu.getCode(), menu.getId());
            }
        }
        return toList;
    }
}
