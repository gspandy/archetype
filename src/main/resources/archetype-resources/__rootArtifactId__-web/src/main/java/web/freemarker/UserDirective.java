#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.freemarker;

import ${package}.biz.service.UserService;
import ${package}.model.app.ShiroUser;
import ${package}.model.app.vo.User;
import ${package}.web.shiro.SuperTag;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
@Component
public class UserDirective extends SuperTag {

    @Autowired
    private UserService userService;

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        ShiroUser shiroUser = userService.getShiroUser();
        User user = null;
        String result = null;

        if(shiroUser != null) {
            user = userService.findUserByUsername(shiroUser.getUsername());
        }

        if (user != null) {
            String property = getProperty(params);

            if (property == null) {
                result = user.toString();
            } else {
                result = getPrincipalProperty(user, property);
            }
        }

        env.getOut().write(result);
    }

    private String getProperty(Map params) {
        return getParam(params, "property");
    }
}
