#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller.admin;

import ${package}.biz.service.MenuService;
import ${package}.model.app.dto.response.CommonResponse;
import ${package}.model.app.vo.Menu;
import ${package}.model.constants.AppConstants;
import ${package}.model.constants.CommonErrors;
import ${package}.model.constants.ResponseState;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("admin/sys/menu")
public class AdminSysMenuController {

    private static final String PATH_ROOT = "admin/sys/menu/";
    private static final String PATH_INDEX = PATH_ROOT + "index";
    private static final String PATH_FORM_MODAL = PATH_ROOT + "form-modal";

    @Autowired
    private MenuService menuService;

    /**
     * 菜单管理
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_MENU")
    public String index(Model model) {
        List<Menu> all_admin_menus = menuService.findAllAdminMenus();
        List<Menu> all_dashboard_menus = menuService.findAllDashboardMenus();

        model.addAttribute("all_admin_menus", all_admin_menus);
        model.addAttribute("all_dashboard_menus", all_dashboard_menus);
        return PATH_INDEX;
    }

    /**
     * 添加菜单
     *
     * @param pcode
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_MENU")
    public String create(@RequestParam(value = "pcode", defaultValue = "") String pcode,
                         Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("parent_menu", menuService.findMenuByCode(pcode));
        return PATH_FORM_MODAL;
    }

    /**
     * 保存菜单
     *
     * @param menu
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @RequiresPermissions("ADMIN_SYS_MENU")
    @ResponseBody
    public CommonResponse save(@ModelAttribute("menu") @Valid Menu menu, BindingResult result) {
        CommonResponse response = new CommonResponse();
        if (result.hasErrors()) {
            response.setResponseState(ResponseState.FAILURE);
            response.setErrCode(CommonErrors.UNKNOW_EXCEPTION.getErrCode());
            response.setErrMsg(CommonErrors.UNKNOW_EXCEPTION.getErrMsg());
        } else {
            menuService.saveMenu(menu);
            response.setResponseState(ResponseState.SUCCESS);
            response.setErrCode(CommonErrors.SUCCESS.getErrCode());
            response.setErrMsg(CommonErrors.SUCCESS.getErrMsg());
        }
        return response;
    }

    /**
     * 编辑菜单
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_MENU")
    public String edit(@PathVariable Long id, Model model) {
        Menu menu = menuService.findMenuById(id);

        model.addAttribute("menu", menu);
        model.addAttribute("parent_menu", menuService.findMenuByCode(menu.getPcode()));
        return PATH_FORM_MODAL;
    }

    /**
     * 更新菜单
     *
     * @param menu
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("ADMIN_SYS_MENU")
    @ResponseBody
    public CommonResponse update(@ModelAttribute("menu") @Valid Menu menu, BindingResult result) {
        CommonResponse response = new CommonResponse();

        if (result.hasErrors()) {
            response.setResponseState(ResponseState.FAILURE);
            response.setErrCode(CommonErrors.UNKNOW_EXCEPTION.getErrCode());
            response.setErrMsg(CommonErrors.UNKNOW_EXCEPTION.getErrMsg());
        } else {
            menuService.updateMenu(menu);
            response.setResponseState(ResponseState.SUCCESS);
            response.setErrCode(CommonErrors.SUCCESS.getErrCode());
            response.setErrMsg(CommonErrors.SUCCESS.getErrMsg());
        }

        return response;
    }

    /**
     * 删除菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @RequiresPermissions("ADMIN_SYS_MENU")
    @ResponseBody
    public CommonResponse delete(@ModelAttribute("menu") Menu menu) {
        CommonResponse response = new CommonResponse();

        menuService.deleteMenu(menu);

        response.setResponseState(ResponseState.SUCCESS);
        response.setErrCode(CommonErrors.SUCCESS.getErrCode());
        response.setErrMsg(CommonErrors.SUCCESS.getErrMsg());
        return response;
    }

}
