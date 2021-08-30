package com.xhx.java.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value = FeignScanRegistrar.class)
public @interface FeignScan {

    /**
     * Base packages to scan for OpenFeign interfaces.
     * @return
     */
    String[] value() default {};

}
