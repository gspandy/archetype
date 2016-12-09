package ${package}.biz.annotation;

import java.lang.annotation.*;

/**
 * 缓存注解
 *
 * @author kangyonggan
 * @since 2016/12/8
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {

    /**
     * 默认生成策略：前缀（antx中定义） + 包名 + 类名 + 方法名 + 参数
     *
     * @return
     */
    String key() default "";

    /**
     * 过期时间（单位：秒）， 默认永不过期
     *
     * @return
     */
    long expire() default -1L;

}
