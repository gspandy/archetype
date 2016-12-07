#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * antx工具类
 *
 * @author kangyonggan
 * @since 2016/12/6
 */
@Component
@Log4j2
public class AntxUtil {

    private static Properties props;

    @Value("${symbol_dollar}{antx.path}")
    void load(String antxPath) {
        props = new Properties();
        try {
            props.load(new FileInputStream(antxPath));
        } catch (IOException e) {
            log.error("加载antx文件失败,antz.path=" + antxPath, e);
        }
    }

    /**
     * 获取antx的值，默认值""
     *
     * @param name
     * @return
     */
    public static String getProperties(String name) {
        return getPropertiesOrDefault(name, "");
    }

    /**
     * 获取antx的值
     *
     * @param name
     * @param defaultValue 默认值
     * @return
     */
    public static String getPropertiesOrDefault(String name, String defaultValue) {
        return props.getProperty(name, defaultValue);
    }

}
