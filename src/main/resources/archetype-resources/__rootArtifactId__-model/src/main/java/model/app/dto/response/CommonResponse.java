#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model.app.dto.response;

import ${package}.model.BaseObject;
import ${package}.model.constants.ResponseState;
import lombok.Data;

/**
 * 通用响应
 *
 * @author kangyonggan
 * @since 2016/12/3
 */
@Data
public class CommonResponse extends BaseObject {

    private String errCode;
    private String errMsg;
    private ResponseState responseState;

}
