package com.samoy.fabiaoqing.controller;

import com.samoy.fabiaoqing.response.ApiResult;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.FeedbackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * FeedbackController
 *
 * @author Samoy
 * @date 2019/12/6
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    @PostMapping("/submit")
    public ApiResult addFeedback(@RequestParam String content) {
        Boolean result = feedbackService.addFeedback(content);
        return result ? ApiResult.success(null) : ApiResult.failure(ResponseEnum.UNKNOWN_ERROR.getCode(), "反馈失败");
    }
}
