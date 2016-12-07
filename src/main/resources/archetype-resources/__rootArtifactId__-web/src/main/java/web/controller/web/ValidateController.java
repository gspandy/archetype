#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller.web;

import ${package}.biz.service.MenuService;
import ${package}.biz.service.RoleService;
import ${package}.biz.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangyonggan
 * @since 2016/12/3
 */
@RestController
@RequestMapping("validate")
public class ValidateController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 校验用户名是否存在
     *
     * @param username
     * @param oldUsername
     * @return
     */
    @RequestMapping(value = "username", method = RequestMethod.GET)
    public boolean validateUsername(@RequestParam("username") String username,
                                    @RequestParam(value = "oldUsername", required = false, defaultValue = "") String oldUsername) {
        if (StringUtils.isNotEmpty(oldUsername) && oldUsername.equals(username)) {
            return false;
        }

        return userService.existsUsername(username);
    }

    /**
     * 校验角色编码是否存在
     *
     * @param code
     * @param oldCode
     * @return
     */
    @RequestMapping(value = "role", method = RequestMethod.GET)
    public boolean validateRoleCode(@RequestParam("code") String code,
                                    @RequestParam(value = "oldCode", required = false, defaultValue = "") String oldCode) {
        if (StringUtils.isNotEmpty(oldCode) && oldCode.equals(code)) {
            return false;
        }

        return roleService.existsRoleCode(code);
    }

    /**
     * 校验菜单编码是否存在
     *
     * @param code
     * @param oldCode
     * @return
     */
    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public boolean validateMenuCode(@RequestParam("code") String code,
                                    @RequestParam(value = "oldCode", required = false, defaultValue = "") String oldCode) {
        if (StringUtils.isNotEmpty(oldCode) && oldCode.equals(code)) {
            return false;
        }

        return menuService.existsMenuCode(code);
    }

}
