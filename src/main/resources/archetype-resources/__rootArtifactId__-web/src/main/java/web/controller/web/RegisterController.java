#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller.web;

import ${package}.biz.service.UserService;
import ${package}.common.util.NameUtil;
import ${package}.model.app.vo.User;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author kangyonggan
 * @since 2016/12/3
 */
@Controller
@RequestMapping("/")
@Log4j2
public class RegisterController {

    private static final String PATH_ROOT = "web/register/";
    private static final String PATH_INDEX = PATH_ROOT + "/index";

    @Autowired
    private UserService userService;

    /**
     * 注册界面
     *
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String login() {
        return PATH_INDEX;
    }

    /**
     * 注册
     *
     * @param user
     * @param result
     * @param captcha
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") @Valid User user, BindingResult result,
                           @RequestParam(value = "captcha") String captcha, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String realCaptcha = (String) session.getAttribute(CaptchaController.KEY_CAPTCHA);

        // 清除验证码
        session.removeAttribute(CaptchaController.KEY_CAPTCHA);

        log.info("session中的验证码为：{}", realCaptcha);
        log.info("用户上送的验证码为：{}", captcha);

        if (!captcha.equalsIgnoreCase(realCaptcha)) {
            model.addAttribute("errMsg", "验证码不正确或已失效");
            model.addAttribute("user", user);
            return PATH_INDEX;
        }

        if (!result.hasErrors()) {
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

                user.setNickName(NameUtil.genName());

                // 保存用户和默认角色
                userService.saveUserWithDefaultRole(user);

                // 登录
                final Subject subject = SecurityUtils.getSubject();
                subject.login(token);

                // 更新最近登录时间
                userService.updateUserLoginTime(user);
                return "redirect:/dashboard";
            } catch (Exception e) {
                log.error("注册成功但登录失败", e);
                return "redirect:/login";
            }
        } else {
            return PATH_INDEX;
        }
    }
}
