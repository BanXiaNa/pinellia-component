package cn.pinellia.wrech.starter.dynamic.config.cente.type.annotations;

import java.lang.annotation.*;

/**
 * @Author BanXia
 * @description: DCCValue注解
 * @Date 2025/12/5 01:09
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface DCCValue {

    String value() default "";

}
