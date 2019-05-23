package com.samoy.fabiaoqing.viewobject;

import lombok.Data;
import lombok.ToString;

/**
 * 表情包VO
 *
 * @author Samoy
 * @date 2019-05-23
 */
@ToString
@Data
public class EmoticonVO {
    private String objectId;
    private String name;
    private String url;
    private GroupVO group;
}
