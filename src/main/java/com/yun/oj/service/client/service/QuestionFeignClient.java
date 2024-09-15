package com.yun.oj.service.client.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import yun.oj.model.model.entity.Question;
import yun.oj.model.model.entity.QuestionSubmit;
import yun.oj.model.model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: __yun
 * @Date: 2024/09/01/6:55
 * @Description:
 */
@FeignClient(name = "yun-oj-question", path = "/api/question/inner")
public interface QuestionFeignClient {
    @PostMapping("/question_submit/update")
    boolean updateById(@RequestBody QuestionSubmit questionSubmitUpdate);
    @GetMapping("/question/get/id")
    Question getQuestionById(@RequestParam("questionId") Long questionId);
    @GetMapping("/question_submit/get/id")
    QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") Long questionSubmitId);
}
