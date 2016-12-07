#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller.admin;

import com.github.pagehelper.PageInfo;
import ${package}.biz.service.MessageService;
import ${package}.biz.service.UserService;
import ${package}.model.app.dto.response.CommonResponse;
import ${package}.model.app.vo.Message;
import ${package}.model.app.vo.User;
import ${package}.model.constants.AppConstants;
import ${package}.model.constants.CommonErrors;
import ${package}.model.constants.MessageEnum;
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
 * @since 2016/12/6
 */
@Controller
@RequestMapping("admin/message")
public class AdminMessageController {

    private static final String PATH_ROOT = "admin/message/";
    private static final String PATH_LIST = PATH_ROOT + "list";
    private static final String PATH_FORM_MODAL = PATH_ROOT + "form-modal";

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    /**
     * 消息管理
     *
     * @param pageNum
     * @param type
     * @param title
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_MESSAGE")
    public String list(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                       @RequestParam(value = "title", required = false, defaultValue = "") String title,
                       @RequestParam(value = "type", required = false, defaultValue = "") String type,
                       Model model) {
        List<Message> messages = messageService.searchMessages(pageNum, AppConstants.PAGE_SIZE, title, type);
        PageInfo<Message> page = new PageInfo(messages);

        model.addAttribute("page", page);
        model.addAttribute("enum", MessageEnum.values());
        return PATH_LIST;
    }

    /**
     * 推送消息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_MESSAGE")
    public String create(Model model) {
        List<User> users = userService.findAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("enum", MessageEnum.values());
        return PATH_FORM_MODAL;
    }

    /**
     * 推送
     *
     * @param message
     * @param receiverUsers
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("ADMIN_MESSAGE")
    public CommonResponse save(@ModelAttribute("message") @Valid Message message,
                               @RequestParam(value = "receiverUsers", required = false, defaultValue = "") String receiverUsers,
                               BindingResult result) {
        CommonResponse response = new CommonResponse();

        if (!result.hasErrors()) {
            messageService.saveMessageWithReceiverUsers(message, receiverUsers);

            response.setResponseState(ResponseState.SUCCESS);
            response.setErrMsg(CommonErrors.SUCCESS.getErrMsg());
            response.setErrCode(CommonErrors.SUCCESS.getErrCode());
        } else {
            response.setResponseState(ResponseState.FAILURE);
            response.setErrCode(CommonErrors.UNKNOW_EXCEPTION.getErrCode());
            response.setErrMsg(CommonErrors.UNKNOW_EXCEPTION.getErrMsg());
        }

        return response;
    }

}
