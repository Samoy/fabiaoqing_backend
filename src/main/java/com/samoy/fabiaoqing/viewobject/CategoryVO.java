package com.samoy.fabiaoqing.viewobject;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 表情包类别VO
 *
 * @author Samoy
 * @date 2019-05-23
 */
@ToString
@Data
public class CategoryVO {
    private String objectId;
    private String name;
    private List<CategoryVO> categoryVOList;
}
