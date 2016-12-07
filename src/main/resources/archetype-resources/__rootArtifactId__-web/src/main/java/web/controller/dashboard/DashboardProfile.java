#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller.dashboard;

import ${package}.biz.service.UserService;
import ${package}.model.app.ShiroUser;
import ${package}.model.app.vo.User;
import ${package}.web.util.FileUpload;
import ${package}.web.util.Images;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @author kangyonggan
 * @since 2016/12/4
 */
@Controller
@RequestMapping("dashboard/profile")
@Log4j2
public class DashboardProfile {

    private static final String PATH_ROOT = "dashboard/profile/";
    private static final String PATH_PASSWORD = PATH_ROOT + "password";
    private static final String PATH_AVATAR = PATH_ROOT + "avatar";
    private static final String PATH_INFO = PATH_ROOT + "info";

    @Autowired
    private UserService userService;

    /**
     * 修改密码
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "password", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD_PROFILE_PASSWORD")
    public String password(Model model) {
        model.addAttribute("user", userService.getShiroUser());
        return PATH_PASSWORD;
    }

    /**
     * 修改密码
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "password", method = RequestMethod.POST)
    @RequiresPermissions("DASHBOARD_PROFILE_PASSWORD")
    public String password(@ModelAttribute("user") @Valid User user, BindingResult result) {

        if (!result.hasErrors() && StringUtils.isNotEmpty(user.getPassword())) {
            userService.updateUserPassword(user);
        }

        return PATH_PASSWORD;
    }

    /**
     * 个人资料
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD_PROFILE_INFO")
    public String info(Model model) {
        ShiroUser user = userService.getShiroUser();
        model.addAttribute("user", userService.findUserById(user.getId()));
        return PATH_INFO;
    }

    /**
     * 个人资料
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.POST)
    @RequiresPermissions("DASHBOARD_PROFILE_INFO")
    public String info(@ModelAttribute("user") @Valid User user, BindingResult result) {

        if (!result.hasErrors()) {
            userService.updateUser(user);
        }

        return PATH_INFO;
    }

    /**
     * 更换头像
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "avatar", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD_PROFILE_AVATAR")
    public String avatar(Model model) {
        ShiroUser user = userService.getShiroUser();
        model.addAttribute("user", userService.findUserById(user.getId()));
        return PATH_AVATAR;
    }

    /**
     * 更换头像
     *
     * @param avatar
     * @param model
     * @return
     */
    @RequestMapping(value = "avatar", method = RequestMethod.POST)
    @RequiresPermissions("DASHBOARD_PROFILE_AVATAR")
    public String avatar(@RequestParam(value = "avatar") MultipartFile avatar, Model model) {
        try {
            String fileName = FileUpload.upload(avatar);

            User user = new User();
            user.setId(userService.getShiroUser().getId());

            String large = Images.large(fileName);
            user.setLargeAvatar(large);
            String middle = Images.middle(fileName);
            user.setMediumAvatar(middle);
            String small = Images.small(fileName);
            user.setSmallAvatar(small);

            userService.updateUser(user);

            model.addAttribute("user", userService.findUserById(user.getId()));
        } catch (Exception e) {
            log.error("文件上传异常", e);
            return "500";
        }
        return PATH_AVATAR;
    }

}
