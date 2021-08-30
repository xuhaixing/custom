package com.xhx.java.feign;

import com.xhx.java.annotation.FeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.Set;

public class ClassPathOpenFeignScanner extends ClassPathBeanDefinitionScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassPathOpenFeignScanner.class);

    public ClassPathOpenFeignScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        if (beanDefinitions.isEmpty()) {
            LOGGER.warn("no feign was found in " + Arrays.toString(basePackages));
        } else {
//            processBeanDefinitions(beanDefinitions);
        }

        return beanDefinitions;
    }

//    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
//        BeanDefinitionRegistry registry = getRegistry();
//        for (BeanDefinitionHolder holder : beanDefinitions) {
//            BeanDefinition candidateComponent = holder.getBeanDefinition();
//            if (candidateComponent instanceof AnnotatedBeanDefinition) {
//                AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
//                AnnotationMetadata metadata = beanDefinition.getMetadata();
//
//                AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(FeignClient.class.getName()));
//
//                registerFeignClient(registry, metadata, annotationAttributes);
//
//            }
//        }
//    }

    @Override
    protected void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) {
        BeanDefinition candidateComponent = definitionHolder.getBeanDefinition();
        if (candidateComponent instanceof AnnotatedBeanDefinition) {
            AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
            AnnotationMetadata metadata = beanDefinition.getMetadata();

            AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(FeignClient.class.getName()));

            registerFeignClient(registry, metadata, annotationAttributes);

        }
    }

    private void registerFeignClient(BeanDefinitionRegistry registry, AnnotationMetadata metadata, AnnotationAttributes annotationAttributes) {

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(FeignClientFactoryBean.class);
        beanDefinitionBuilder.addPropertyValue("type", metadata.getClassName());
        beanDefinitionBuilder.addPropertyValue("name", annotationAttributes.getString("name"));
        beanDefinitionBuilder.addPropertyValue("url", annotationAttributes.getString("url"));
        beanDefinitionBuilder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        beanDefinitionBuilder.setScope(BeanDefinition.SCOPE_SINGLETON);

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        beanDefinition.setPrimary(true);

        registry.registerBeanDefinition(metadata.getClassName(), beanDefinitionBuilder.getBeanDefinition());
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

}
