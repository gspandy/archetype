#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz.service.impl;

import com.github.pagehelper.PageHelper;
import ${package}.biz.service.RoleService;
import ${package}.common.util.Collections3;
import ${package}.mapper.RoleMapper;
import ${package}.model.app.vo.Menu;
import ${package}.model.app.vo.Role;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
@Service
@Log4j2
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return roleMapper.selectRoles(userId);
    }

    @Override
    public List<Role> findAllRoles() {
        return super.select(new Role());
    }

    @Override
    public List<Role> searchRoles(int pageNum, int pageSize, String code, String name) {
        Example example = new Example(Role.class);

        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(code)) {
            criteria.andLike("code", String.format("%%%s%%", code));
        }
        if (StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", String.format("%%%s%%", name));
        }

        example.setOrderByClause("id desc");

        PageHelper.startPage(pageNum, pageSize);
        return super.selectByExample(example);
    }

    @Override
    public void saveRole(Role role) {
        super.insertSelective(role);
    }

    @Override
    public Role findRoleById(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public void updateRole(Role role) {
        super.updateByPrimaryKeySelective(role);
    }

    @Override
    public boolean existsRoleCode(String code) {
        Role role = new Role();
        role.setCode(code);

        return roleMapper.selectCount(role) == 1;
    }

    @Override
    public void updateRoleMenus(String code, List<Menu> role_menus, String menuCodes) {
        if (Collections3.isNotEmpty(role_menus)) {
            deleteRoleMenus(code, Collections3.extractToList(role_menus, "code"));
        }

        if (StringUtils.isNotEmpty(menuCodes)) {
            roleMapper.insertRoleMenus(code, Arrays.asList(menuCodes.split(",")));
        }
    }

    /**
     * 删除角色菜单
     *
     * @param code
     * @param menuCodes
     */
    private void deleteRoleMenus(String code, List<String> menuCodes) {
        roleMapper.deleteRoleMenus(code, menuCodes);
    }
}
