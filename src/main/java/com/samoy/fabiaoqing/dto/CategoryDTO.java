package com.samoy.fabiaoqing.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 列表传输对象
 *
 * @author Samoy
 * @date 2019-05-29
 */
@ToString
@Data
public class CategoryDTO {
    @NotBlank(message = "类别id不能为空")
    private String objectId;
    @NotBlank(message = "类别名称不能为空")
    private String name;
}
