#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz.util;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * app.properties工具类
 *
 * @author kangyonggan
 * @since 2016/12/6
 */
@Log4j2
public class AppProperties {

    private static Properties props;

    static {
        try {
            props = new Properties();
            InputStream in = AppProperties.class.getClassLoader().getResourceAsStream("app.properties");
            props.load(in);
        } catch (IOException e) {
            log.error("app.properties加载失败", e);
        }
    }

    private AppProperties() {
    }

    /**
     * 获取properties的值，默认值""
     *
     * @param name
     * @return
     */
    public static String getProperties(String name) {
        return getPropertiesOrDefault(name, "");
    }

    /**
     * 获取properties的值
     *
     * @param name
     * @param defaultValue 默认值
     * @return
     */
    public static String getPropertiesOrDefault(String name, String defaultValue) {
        return props.getProperty(name, defaultValue);
    }

}

