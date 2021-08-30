package com.xhx.java.annotation;

import com.xhx.java.feign.FeignScannerConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FeignScanRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes feignScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(FeignScan.class.getName(), true));


        registerBeanDefinitions(importingClassMetadata, feignScanAttrs, registry,
                generateBaseBeanName(importingClassMetadata, 0));

    }

    void registerBeanDefinitions(AnnotationMetadata annoMeta, AnnotationAttributes annoAttrs,
                                 BeanDefinitionRegistry registry, String beanName) {

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(FeignScannerConfigurer.class);

        List<String> basepackages = new ArrayList<>();
        if (annoAttrs != null) {
            basepackages.addAll(Arrays.stream(annoAttrs.getStringArray("value")).filter(StringUtils::hasText).collect(Collectors.toList()));
        }

        if (basepackages.isEmpty()) {
            basepackages.add(getDefaultBasePackage(annoMeta));
        }

        builder.addPropertyValue("pageckages", basepackages);

        registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
    }


    private static String generateBaseBeanName(AnnotationMetadata importingClassMetadata, int index) {
        return importingClassMetadata.getClassName() + "#" + FeignScanRegistrar.class.getSimpleName() + "#" + index;
    }

    private static String getDefaultBasePackage(AnnotationMetadata importingClassMetadata) {
        return ClassUtils.getPackageName(importingClassMetadata.getClassName());
    }
}
