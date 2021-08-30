package com.xhx.java.feign.annotation;

import com.xhx.java.annotation.FeignScanRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value = FeignScanRegistrar.class)
public @interface EnableFeignClient {

    boolean value() default true;
}
