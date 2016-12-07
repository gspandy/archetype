#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model.constants;

import lombok.Getter;

/**
 * 响应状态
 *
 * @author kangyonggan
 * @since 2016/12/3
 */
public enum ResponseState {

    SUCCESS("Y", "成功"),
    FAILURE("F", "失败"),
    EXCEPTION("E", "异常"),
    IN_HANDLE("I", "处理中");

    /**
     * 状态码
     */
    @Getter
    private final String code;

    /**
     * 状态描述
     */
    @Getter
    private final String desc;

    ResponseState(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
