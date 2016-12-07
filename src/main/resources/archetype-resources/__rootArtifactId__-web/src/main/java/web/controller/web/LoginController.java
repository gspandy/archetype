#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller.web;

import ${package}.biz.service.UserService;
import ${package}.model.app.vo.User;
import ${package}.model.constants.CommonErrors;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
@Controller
@RequestMapping("/")
@Log4j2
public class LoginController {

    private static final String PATH_ROOT = "web/login/";
    private static final String PATH_INDEX = PATH_ROOT + "/index";

    @Autowired
    private UserService userService;

    /**
     * 登录界面
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return PATH_INDEX;
    }

    /**
     * 登录
     *
     * @param user
     * @param captcha
     * @param request
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "captcha") String captcha, User user, HttpServletRequest request, Model model) {
        log.info("登录的用户名：{}", user.getUsername());

        HttpSession session = request.getSession();
        String realCaptcha = (String) session.getAttribute(CaptchaController.KEY_CAPTCHA);
        log.info("session中的验证码为：{}", realCaptcha);
        log.info("用户上送的验证码为：{}", captcha);

        // TODO 开发环境不验
//        if (!captcha.equalsIgnoreCase(realCaptcha)) {
//            String errMsg = "验证码不正确或已失效";
//            model.addAttribute("errMsg", errMsg);
//            model.addAttribute("user", user);
//            log.info(errMsg);
//            return PATH_INDEX;
//        }
        log.info("验证码正确");

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        final Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            String errMsg = CommonErrors.USERNAME_UNKNOW.getErrMsg();
            model.addAttribute("errMsg", errMsg);
            model.addAttribute("user", user);
            log.info(errMsg);
            return PATH_INDEX;
        } catch (IncorrectCredentialsException ice) {
            String errMsg = CommonErrors.USERNAME_PASSWORD_ERROR.getErrMsg();
            model.addAttribute("errMsg", errMsg);
            model.addAttribute("user", user);
            log.info(errMsg);
            return PATH_INDEX;
        } catch (LockedAccountException lae) {
            String errMsg = CommonErrors.USER_LOCKED.getErrMsg();
            model.addAttribute("errMsg", errMsg);
            model.addAttribute("user", user);
            log.info(errMsg);
            return PATH_INDEX;
        } catch (DisabledAccountException dae) {
            String errMsg = CommonErrors.USER_IS_DELETED.getErrMsg();
            model.addAttribute("errMsg", errMsg);
            model.addAttribute("user", user);
            log.info(errMsg);
            return PATH_INDEX;
        } catch (Exception e) {
            String errMsg = CommonErrors.UNKNOW_EXCEPTION.getErrMsg();
            model.addAttribute("errMsg", errMsg);
            model.addAttribute("user", user);
            log.info(errMsg);
            return PATH_INDEX;
        }

        // 更新最近登录时间
        userService.updateUserLoginTime(user);

        String redirectUrl = "/dashboard";

        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        // 获取之前访问的URL
        if (savedRequest != null && savedRequest.getRequestUrl() != null) {
            redirectUrl = savedRequest.getRequestUrl();
        }

        // 清除验证码
        session.removeAttribute(CaptchaController.KEY_CAPTCHA);

        log.info("登录成功， 重定向到：{}", redirectUrl);
        return String.format("redirect:%s", redirectUrl);
    }

    /**
     * 注销
     *
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        final Subject subject = SecurityUtils.getSubject();
        log.info("logout {}", subject.getPrincipal());
        subject.logout();
        return "redirect:/login";
    }
}
