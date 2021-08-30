package com.xhx.java.spring;

import com.xhx.java.Application;
import com.xhx.java.feign.Baidu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = Application.class)
public class SpringTest01 {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Baidu baidu;

    @Test
    public void test01(){
        Map<String, Object> query = new HashMap<>();
        query.put("json", 1);
        query.put("prod", "pc");
        query.put("from", "pc_web");
        query.put("wd", "bai");
        System.out.println(baidu.querySuggest(query));
    }


}
