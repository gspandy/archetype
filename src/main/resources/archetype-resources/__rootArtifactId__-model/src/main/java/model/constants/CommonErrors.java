#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model.constants;

import lombok.Getter;

/**
 * 通用错误
 * 约定：
 * 错误码都是4为，如8001，第1位表示错误大类，后面3位表示具体错误
 * 0000：成功
 * 9开头：未知类型
 * 8开头：用户相关的错误
 * 7开头：权限相关的错误
 * 1开头：通用错误
 *
 * @author kangyonggan
 * @since 2016/12/3
 */
public enum CommonErrors {

    SUCCESS("0000", "成功"),
    COMMON_EXCEPTION("1111", "服务器内部错误，请联系管理员"),
    PERMISSION_DENIED("7001", "权限不足，无法访问"),
    USER_LOCKED("8001", "用户已被锁定，请联系管理员"),
    USERNAME_UNKNOW("8002", "用户名不存在"),
    USER_IS_DELETED("8003", "用户已被删除，请联系管理员"),
    USERNAME_PASSWORD_ERROR("8003", "用户名或密码错误"),
    UNKNOW_EXCEPTION("9999", "未知错误，请联系管理员");

    /**
     * 错误码
     */
    @Getter
    private final String errCode;

    /**
     * 错误信息
     */
    @Getter
    private final String errMsg;

    CommonErrors(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

}
