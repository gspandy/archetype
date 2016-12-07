#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model.app;

import ${package}.model.BaseObject;
import lombok.Data;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
@Data
public class ShiroUser extends BaseObject {

    private Long id;
    private String username;

    public ShiroUser() {
    }

    public ShiroUser(Long id, String username) {
        this.id = id;
        this.username = username;
    }

}
