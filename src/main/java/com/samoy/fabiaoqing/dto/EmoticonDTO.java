package com.samoy.fabiaoqing.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 表情DTO
 *
 * @author Samoy
 * @date 2019-05-30
 */
@ToString
@Data
public class EmoticonDTO {
    private String objectId;
    private String name;
    private String url;
}
