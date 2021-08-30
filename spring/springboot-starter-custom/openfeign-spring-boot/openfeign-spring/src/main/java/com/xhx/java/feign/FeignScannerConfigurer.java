package com.xhx.java.feign;

import com.xhx.java.annotation.FeignClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class FeignScannerConfigurer implements BeanDefinitionRegistryPostProcessor {


    private String[] pageckages;


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathOpenFeignScanner scanner = new ClassPathOpenFeignScanner(registry);
        //设置扫描条件 - 带FeignClient注解
        scanner.addIncludeFilter(new AnnotationTypeFilter(FeignClient.class));
        scanner.scan(pageckages);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    public void setPageckages(String[] pageckages) {
        this.pageckages = pageckages;
    }

    public String[] getPageckages() {
        return pageckages;
    }
}
