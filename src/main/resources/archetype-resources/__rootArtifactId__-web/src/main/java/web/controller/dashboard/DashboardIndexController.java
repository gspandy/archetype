#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
@Controller
@RequestMapping("dashboard")
public class DashboardIndexController {

    private static final String PATH_ROOT = "dashboard/";
    private static final String PATH_INDEX = PATH_ROOT + "index";

    /**
     * 工作台首页
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return PATH_INDEX;
    }

}
