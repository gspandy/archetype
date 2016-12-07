#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz.service.impl;

import com.github.pagehelper.PageHelper;
import ${package}.biz.service.MessageService;
import ${package}.biz.service.UserService;
import ${package}.common.util.Collections3;
import ${package}.mapper.MessageMapper;
import ${package}.model.app.ShiroUser;
import ${package}.model.app.vo.Message;
import ${package}.model.app.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/6
 */
@Service
public class MessageServiceImpl extends BaseService<Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<Message> searchMessages(int pageNum, int pageSize, String title, String type) {
        PageHelper.startPage(pageNum, pageSize);
        return messageMapper.searchMessage(0L, title, type, null);
    }

    @Override
    public List<Message> findMessagesByUserId(Long userId, int pageNum, int pageSize, String type, String isRead) {
        return messageMapper.searchMessage(userId, null, type, isRead);
    }

    @Override
    public void saveMessageWithReceiverUsers(Message message, String receiverUsers) {
        super.insertSelective(message);

        ShiroUser user = userService.getShiroUser();

        if (StringUtils.isEmpty(receiverUsers)) {
            List<User> users = userService.findAllUsers();
            messageMapper.insertMessageUsers(message.getId(), user.getId(), Collections3.extractToList(users, "id"));
        } else {
            String userIds[] = receiverUsers.split(",");
            messageMapper.insertMessageUsers(message.getId(), user.getId(), Arrays.asList(userIds));
        }
    }

    @Override
    public Message findMessageById(Long id) {
        ShiroUser user = userService.getShiroUser();

        return messageMapper.selectMessageById(user.getId(), id);
    }

    @Override
    public void updateMessageForRead(String ids) {
        ShiroUser user = userService.getShiroUser();

        messageMapper.updateMessageForRead(user.getId(), Arrays.asList(ids.split(",")));
    }
}
