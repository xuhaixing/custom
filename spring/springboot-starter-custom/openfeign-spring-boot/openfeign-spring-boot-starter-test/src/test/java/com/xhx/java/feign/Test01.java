package com.xhx.java.feign;


import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Test01 {

    @Test
    public void test01() {
        Baidu target = Feign.builder().decoder(new JacksonDecoder()).encoder(new JacksonEncoder()).target(Baidu.class, "https://www.baidu.com");
        //json=1&prod=pc&from=pc_web&wd=bai
        Map<String, Object> query = new HashMap<>();
        query.put("json", 1);
        query.put("prod", "pc");
        query.put("from", "pc_web");
        query.put("wd", "bai");
        Object o = target.querySuggest(query);
        System.out.println(o.toString());
    }
}
