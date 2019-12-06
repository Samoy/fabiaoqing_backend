package com.samoy.fabiaoqing.service;

import com.samoy.fabiaoqing.dto.FeedbackDTO;

/**
 * FeedbackService
 *
 * @author Samoy
 * @date 2019/12/6
 */
public interface FeedbackService {
    /***
     * 添加反馈
     * @param content 反馈内容
     * @return 是否添加成功
     */
    Boolean addFeedback(String content);
}
