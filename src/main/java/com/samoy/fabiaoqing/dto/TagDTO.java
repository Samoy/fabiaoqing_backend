package com.samoy.fabiaoqing.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * TagDTO
 *
 * @author Samoy
 * @date 2019-07-14
 */
@Data
@ToString
public class TagDTO {
    @NotBlank(message = "标签id不能为空")
    private String objectId;
    @NotBlank(message = "标签名称不能为空")
    private String name;
}
