#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller.admin;

import com.github.pagehelper.PageInfo;
import ${package}.biz.service.RoleService;
import ${package}.biz.service.UserService;
import ${package}.common.util.Collections3;
import ${package}.model.app.dto.response.CommonResponse;
import ${package}.model.app.vo.Role;
import ${package}.model.app.vo.User;
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
@RequestMapping("admin/sys/user")
public class AdminSysUserController {

    private static final String PATH_ROOT = "admin/sys/user/";
    private static final String PATH_LIST = PATH_ROOT + "list";
    private static final String PATH_TABLE_TR = PATH_ROOT + "table-tr";
    private static final String PATH_FORM_MODAL = PATH_ROOT + "form-modal";
    private static final String PATH_DETAIL_MODAL = PATH_ROOT + "detail-modal";
    private static final String PATH_PASSWORD_MODAL = PATH_ROOT + "password-modal";
    private static final String PATH_ROLES_MODAL = PATH_ROOT + "roles-modal";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 用户管理
     *
     * @param pageNum
     * @param username
     * @param nickName
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_USER")
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "username", required = false, defaultValue = "") String username,
                        @RequestParam(value = "nickName", required = false, defaultValue = "") String nickName,
                        Model model) {
        List<User> users = userService.searchUsers(pageNum, AppConstants.PAGE_SIZE, username, nickName);
        PageInfo<User> page = new PageInfo(users);

        model.addAttribute("page", page);
        return PATH_LIST;
    }

    /**
     * 添加用户
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_USER")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return PATH_FORM_MODAL;
    }

    /**
     * 保存用户
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("ADMIN_SYS_USER")
    public CommonResponse save(@ModelAttribute("user") @Valid User user, BindingResult result) {
        CommonResponse response = new CommonResponse();
        if (!result.hasErrors()) {
            userService.saveUserWithDefaultRole(user);
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
     * 编辑用户
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_USER")
    public String create(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return PATH_FORM_MODAL;
    }

    /**
     * 更新用户
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("ADMIN_SYS_USER")
    public CommonResponse update(@ModelAttribute("user") @Valid User user, BindingResult result) {
        CommonResponse response = new CommonResponse();
        if (!result.hasErrors()) {
            userService.updateUser(user);
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
     * 锁定/解锁
     *
     * @param id
     * @param isLocked
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/{isLocked:${symbol_escape}${symbol_escape}bunlock${symbol_escape}${symbol_escape}b|${symbol_escape}${symbol_escape}block${symbol_escape}${symbol_escape}b}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @RequiresPermissions("ADMIN_SYS_USER")
    public String lock(@PathVariable("id") Long id, @PathVariable("isLocked") String isLocked, Model model) {
        User user = userService.findUserById(id);
        user.setIsLocked((byte) (isLocked.equals("unlock") ? 0 : 1));
        userService.updateUser(user);

        model.addAttribute("user", user);
        return PATH_TABLE_TR;
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
    @RequiresPermissions("ADMIN_SYS_USER")
    public String delete(@PathVariable("id") Long id, @PathVariable("isDeleted") String isDeleted, Model model) {
        User user = userService.findUserById(id);
        user.setIsDeleted((byte) (isDeleted.equals("delete") ? 1 : 0));
        userService.updateUser(user);

        model.addAttribute("user", user);
        return PATH_TABLE_TR;
    }

    /**
     * 用户详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return PATH_DETAIL_MODAL;
    }

    /**
     * 修改密码
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/password", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_USER")
    public String password(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return PATH_PASSWORD_MODAL;
    }

    /**
     * 修改密码
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "password", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("ADMIN_SYS_USER")
    public CommonResponse password(@ModelAttribute("user") @Valid User user, BindingResult result) {
        CommonResponse response = new CommonResponse();
        if (!result.hasErrors()) {
            userService.updateUserPassword(user);
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
     * 设置角色
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/roles", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_USER")
    public String roles(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        List<Role> user_roles = roleService.findRolesByUserId(user.getId());
        user_roles = Collections3.extractToList(user_roles, "code");
        List<Role> all_roles = roleService.findAllRoles();

        model.addAttribute("userId", id);
        model.addAttribute("user_roles", user_roles);
        model.addAttribute("all_roles", all_roles);
        return PATH_ROLES_MODAL;
    }

    /**
     * 保存角色
     *
     * @param id
     * @param roles
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/roles", method = RequestMethod.POST)
    @RequiresPermissions("ADMIN_SYS_USER")
    @ResponseBody
    public CommonResponse updateUserRoles(@PathVariable(value = "id") Long id,
                                          @RequestParam(value = "roles", defaultValue = "") String roles) {
        CommonResponse response = new CommonResponse();

        User user = userService.findUserById(id);
        userService.updateUserRoles(user.getId(), roles);

        response.setResponseState(ResponseState.SUCCESS);
        response.setErrCode(CommonErrors.SUCCESS.getErrCode());
        response.setErrMsg(CommonErrors.SUCCESS.getErrMsg());

        return response;
    }

}
