package com.samoy.fabiaoqing.service.impl;

import com.samoy.fabiaoqing.dao.FeedbackDAO;
import com.samoy.fabiaoqing.dto.FeedbackDTO;
import com.samoy.fabiaoqing.service.FeedbackService;
import com.samoy.fabiaoqing.util.CommonUtils;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * FeedbackServiceImpl
 *
 * @author Samoy
 * @date 2019/12/6
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackDAO feedbackDAO;

    @Override
    public Boolean addFeedback(String content) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setObjectId(CommonUtils.randomObjectId());
        feedbackDTO.setContent(content);
        return feedbackDAO.insertSelective(MyBeanUtils.convertFeedbackDTOToDO(feedbackDTO)) == 1;
    }
}
