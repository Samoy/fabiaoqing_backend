package com.samoy.fabiaoqing.viewobject;

import lombok.Data;
import lombok.ToString;

/**
 * UserVO
 *
 * @author Samoy
 * @date 2019/9/2
 */
@Data
@ToString
public class UserVO {
    private String objectId;
    private String email;
    private String telephone;
    private String nickname;
    /**
     * true为男性，false为女性
     */
    private Boolean sex;
    private String avatar;
    private String description;
}
