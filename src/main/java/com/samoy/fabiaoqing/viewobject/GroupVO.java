package com.samoy.fabiaoqing.viewobject;

import lombok.Data;
import lombok.ToString;

/**
 * 表情包分组VO
 *
 * @author Samoy
 * @date 2019-05-23
 */
@ToString
@Data
public class GroupVO {
    private String objectId;
    private String name;
    private Integer count;
    private CategoryVO category;
}
