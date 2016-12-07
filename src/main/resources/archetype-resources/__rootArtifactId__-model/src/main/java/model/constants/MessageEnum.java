#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model.constants;

import lombok.Getter;

/**
 * 消息类型
 *
 * @author kangyonggan
 * @since 2016/12/6
 */
public enum MessageEnum {

    SYS_MESSAGE("SYS_MESSAGE", "系统消息"),
    ACTIVITY_MESSAGE("ACTIVITY_MESSAGE", "活动消息"),
    SERVICE_MESSAGE("SERVICE_MESSAGE", "服务消息");

    @Getter
    private final String type;

    @Getter
    private final String name;

    MessageEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

}
