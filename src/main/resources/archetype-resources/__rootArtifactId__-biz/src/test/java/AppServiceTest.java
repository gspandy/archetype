#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.biz.service.MenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
public class AppServiceTest extends AbstractServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void testFindDashboardMenus() {
        System.out.println(menuService.findDashboardMenus(1L));
    }

    @Test
    public void testFindAdminMenus() {
        System.out.println(menuService.findAdminMenus(1L));
    }

}
