package com.xhx.java.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FeignClient {


    /**
     * remote name
     * @return
     */
    @AliasFor(value = "name")
    String value() default "";

    @AliasFor(value = "value")
    String name() default "";

    /**
     * remote url
     * @return
     */
    String url() default "";
}
