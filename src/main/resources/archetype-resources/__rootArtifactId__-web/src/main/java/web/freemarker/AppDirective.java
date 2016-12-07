#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.freemarker;

import ${package}.biz.service.MessageService;
import ${package}.biz.service.UserService;
import ${package}.model.app.vo.Message;
import ${package}.web.shiro.SuperTag;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
@Component
public class AppDirective extends SuperTag {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        Long userId = userService.getShiroUser().getId();
        List<Message> messages = messageService.findMessagesByUserId(userId, 1, 5, null, "0");
        env.setVariable("messages", ObjectWrapper.DEFAULT_WRAPPER.wrap(messages));
        renderBody(env, body);
    }
}
