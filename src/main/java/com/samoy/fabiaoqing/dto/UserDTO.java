package com.samoy.fabiaoqing.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * UserDTO
 *
 * @author Samoy
 * @date 2019/9/2
 */
@Data
@ToString
public class UserDTO {
    private String objectId;
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱格式不合法")
    private String email;
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "[1][3456789](\\d){9}", message = "手机号码格式不合法")
    private String telephone;
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 16, message = "密码格式不合法")
    private String password;
    private String nickname;
    private String avatar;
    private Boolean sex;
    private String description;
}
