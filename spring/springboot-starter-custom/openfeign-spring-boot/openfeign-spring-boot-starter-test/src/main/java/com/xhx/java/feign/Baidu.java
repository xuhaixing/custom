package com.xhx.java.feign;

import feign.QueryMap;
import feign.RequestLine;

import com.xhx.java.annotation.FeignClient;

import java.util.Map;

/**
 * https://www.baidu.com/sugrec?&json=1&prod=pc&from=pc_web&wd=bai
 */

@FeignClient(value = "baidu", url = "https://www.baidu.com")
public interface Baidu {

  @RequestLine("GET /sugrec")
  Object querySuggest(@QueryMap Map<String, Object> query);


}