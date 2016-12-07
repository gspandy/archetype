#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller.dashboard;

import com.github.pagehelper.PageInfo;
import ${package}.biz.service.MessageService;
import ${package}.biz.service.UserService;
import ${package}.model.app.ShiroUser;
import ${package}.model.app.dto.response.CommonResponse;
import ${package}.model.app.vo.Message;
import ${package}.model.constants.AppConstants;
import ${package}.model.constants.CommonErrors;
import ${package}.model.constants.ResponseState;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/7
 */
@Controller
@RequestMapping("dashboard/message")
public class DashboardMessageController {

    private static final String PATH_ROOT = "dashboard/message/";
    private static final String PATH_INDEX = PATH_ROOT + "index";
    private static final String PATH_DETAIL_MODAL = PATH_ROOT + "detail-modal";

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    /**
     * 我的消息
     *
     * @param pageNum
     * @param type
     * @param isRead
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD_MESSAGE")
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "type", required = false, defaultValue = "") String type,
                        @RequestParam(value = "isRead", required = false, defaultValue = "") String isRead,
                        Model model) {
        ShiroUser user = userService.getShiroUser();
        List<Message> messages = messageService.findMessagesByUserId(user.getId(), pageNum, AppConstants.PAGE_SIZE, type, isRead);
        PageInfo<Message> page = new PageInfo(messages);

        model.addAttribute("page", page);
        return PATH_INDEX;
    }

    /**
     * 消息详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD_MESSAGE")
    public String detail(@PathVariable("id") Long id, Model model) {
        Message message = messageService.findMessageById(id);
        messageService.updateMessageForRead(id + "");

        model.addAttribute("message", message);
        return PATH_DETAIL_MODAL;
    }

    /**
     * 标记为已读
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "read", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("DASHBOARD_MESSAGE")
    public CommonResponse read(@RequestParam("ids") String ids) {
        CommonResponse response = new CommonResponse();
        messageService.updateMessageForRead(ids);

        response.setResponseState(ResponseState.SUCCESS);
        response.setErrCode(CommonErrors.SUCCESS.getErrCode());
        response.setErrMsg(CommonErrors.SUCCESS.getErrMsg());
        return response;
    }

}
