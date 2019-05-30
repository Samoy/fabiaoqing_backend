package com.samoy.fabiaoqing.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 表情包DTO
 *
 * @author Samoy
 * @date 2019-05-30
 */
@Data
@ToString
public class PackageDTO {
    @NotBlank(message = "表情包id不能为空")
    private String objectId;
    @NotBlank(message = "表情包名字不能为空")
    private String name;
    private List<EmoticonDTO> emoticonDTOList;
}
