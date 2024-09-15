package com.yun.oj.service.client.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import yun.oj.model.model.entity.QuestionSubmit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: __yun
 * @Date: 2024/09/01/6:55
 * @Description:
 */
@FeignClient(name = "yun-oj-judge",path = "/api/judge/inner")
public interface JudgeFeignClient {
    @PostMapping("/do")
    QuestionSubmit judge(@RequestBody QuestionSubmit questionSubmit);
}
