#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author kangyonggan
 * @since 2016/11/30
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private static final String PATH_ROOT = "web/default/";
    private static final String PATH_INDEX = PATH_ROOT + "index";

    /**
     * 网站首页
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return PATH_INDEX;
    }

}
