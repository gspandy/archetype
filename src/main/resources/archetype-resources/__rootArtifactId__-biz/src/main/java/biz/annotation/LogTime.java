package ${package}.biz.annotation;

import java.lang.annotation.*;

/**
 * @author kangyonggan
 * @since 2016/12/8
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogTime {
}
