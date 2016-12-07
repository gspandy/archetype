#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller.admin;

import com.github.pagehelper.PageInfo;
import ${package}.biz.service.MenuService;
import ${package}.biz.service.RoleService;
import ${package}.common.util.Collections3;
import ${package}.model.app.dto.response.CommonResponse;
import ${package}.model.app.vo.Menu;
import ${package}.model.app.vo.Role;
import ${package}.model.constants.AppConstants;
import ${package}.model.constants.CommonErrors;
import ${package}.model.constants.ResponseState;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
@Controller
@RequestMapping("admin/sys/role")
public class AdminSysRoleController {

    private static final String PATH_ROOT = "admin/sys/role/";
    private static final String PATH_LIST = PATH_ROOT + "list";
    private static final String PATH_TABLE_TR = PATH_ROOT + "table-tr";
    private static final String PATH_FORM_MODAL = PATH_ROOT + "form-modal";
    private static final String PATH_DETAIL_MODAL = PATH_ROOT + "detail-modal";
    private static final String PATH_MENUS_MODAL = PATH_ROOT + "menus-modal";

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 角色管理
     *
     * @param pageNum
     * @param code
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "code", required = false, defaultValue = "") String code,
                        @RequestParam(value = "name", required = false, defaultValue = "") String name,
                        Model model) {
        List<Role> roles = roleService.searchRoles(pageNum, AppConstants.PAGE_SIZE, code, name);
        PageInfo<Role> page = new PageInfo(roles);

        model.addAttribute("page", page);
        return PATH_LIST;
    }

    /**
     * 添加角色
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String create(Model model) {
        model.addAttribute("role", new Role());
        return PATH_FORM_MODAL;
    }

    /**
     * 保存角色
     *
     * @param role
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public CommonResponse save(@ModelAttribute("role") @Valid Role role, BindingResult result) {
        CommonResponse response = new CommonResponse();
        if (!result.hasErrors()) {
            roleService.saveRole(role);
            response.setResponseState(ResponseState.SUCCESS);
            response.setErrCode(CommonErrors.SUCCESS.getErrCode());
            response.setErrMsg(CommonErrors.SUCCESS.getErrMsg());
        } else {
            response.setResponseState(ResponseState.FAILURE);
            response.setErrCode(CommonErrors.UNKNOW_EXCEPTION.getErrCode());
            response.setErrMsg(CommonErrors.UNKNOW_EXCEPTION.getErrMsg());
        }

        return response;
    }

    /**
     * 编辑角色
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String create(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", roleService.findRoleById(id));
        return PATH_FORM_MODAL;
    }

    /**
     * 更新角色
     *
     * @param role
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public CommonResponse update(@ModelAttribute("role") @Valid Role role, BindingResult result) {
        CommonResponse response = new CommonResponse();
        if (!result.hasErrors()) {
            roleService.updateRole(role);
            response.setResponseState(ResponseState.SUCCESS);
            response.setErrCode(CommonErrors.SUCCESS.getErrCode());
            response.setErrMsg(CommonErrors.SUCCESS.getErrMsg());
        } else {
            response.setResponseState(ResponseState.FAILURE);
            response.setErrCode(CommonErrors.UNKNOW_EXCEPTION.getErrCode());
            response.setErrMsg(CommonErrors.UNKNOW_EXCEPTION.getErrMsg());
        }

        return response;
    }

    /**
     * 删除/恢复
     *
     * @param id
     * @param isDeleted
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/{isDeleted:${symbol_escape}${symbol_escape}bundelete${symbol_escape}${symbol_escape}b|${symbol_escape}${symbol_escape}bdelete${symbol_escape}${symbol_escape}b}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String delete(@PathVariable("id") Long id, @PathVariable("isDeleted") String isDeleted, Model model) {
        Role role = roleService.findRoleById(id);
        role.setIsDeleted((byte) (isDeleted.equals("delete") ? 1 : 0));
        roleService.updateRole(role);

        model.addAttribute("role", role);
        return PATH_TABLE_TR;
    }

    /**
     * 角色详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", roleService.findRoleById(id));
        return PATH_DETAIL_MODAL;
    }

    /**
     * 修改角色的控制台菜单
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/am", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String adminMenus(@PathVariable("id") Long id, Model model) {
        Role role = roleService.findRoleById(id);
        List<Menu> role_menus = menuService.findAdminMenus4Role(role.getCode());
        if (role_menus != null) {
            role_menus = Collections3.extractToList(role_menus, "code");
        }

        List<Menu> all_menus = menuService.findAllAdminMenus();

        model.addAttribute("role_menus", role_menus);
        model.addAttribute("all_menus", all_menus);
        model.addAttribute("type", AppConstants.MENU_TYPE_ADMIN);
        model.addAttribute("roleId", id);
        return PATH_MENUS_MODAL;
    }

    /**
     * 修改角色的工作台菜单
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/dm", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String dashboardMenus(@PathVariable("id") Long id, Model model) {
        Role role = roleService.findRoleById(id);
        List<Menu> role_menus = menuService.findDashboardMenus4Role(role.getCode());
        if (role_menus != null) {
            role_menus = Collections3.extractToList(role_menus, "code");
        }

        List<Menu> all_menus = menuService.findAllDashboardMenus();

        model.addAttribute("role_menus", role_menus);
        model.addAttribute("all_menus", all_menus);
        model.addAttribute("type", AppConstants.MENU_TYPE_DASHBOARD);
        model.addAttribute("roleId", id);
        return PATH_MENUS_MODAL;
    }

    /**
     * 更新角色菜单
     *
     * @param id
     * @param menus
     * @param type
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/menus", method = RequestMethod.POST)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    @ResponseBody
    public CommonResponse menus(@PathVariable Long id, @RequestParam(value = "menus") String menus, @RequestParam(value = "type") String type) {
        CommonResponse response = new CommonResponse();
        Role role = roleService.findRoleById(id);

        List<Menu> role_menus;
        if (AppConstants.MENU_TYPE_ADMIN.equals(type)) {
            role_menus = menuService.findAdminMenus4Role(role.getCode());
        } else {
            role_menus = menuService.findDashboardMenus4Role(role.getCode());
        }

        roleService.updateRoleMenus(role.getCode(), role_menus, menus);

        response.setResponseState(ResponseState.SUCCESS);
        response.setErrCode(CommonErrors.SUCCESS.getErrCode());
        response.setErrMsg(CommonErrors.SUCCESS.getErrMsg());
        return response;
    }
}
