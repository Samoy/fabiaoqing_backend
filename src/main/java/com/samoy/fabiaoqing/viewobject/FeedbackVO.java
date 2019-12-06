package com.samoy.fabiaoqing.viewobject;

import lombok.Data;
import lombok.ToString;

/**
 * FeedbackVO
 *
 * @author Samoy
 * @date 2019/12/6
 */
@Data
@ToString
public class FeedbackVO {
    private String objectId;
    private String content;
}
