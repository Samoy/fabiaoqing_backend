package com.samoy.fabiaoqing.viewobject;

import lombok.Data;
import lombok.ToString;

/**
 * TokenVO
 *
 * @author Samoy
 * @date 2019/9/3
 */
@Data
@ToString
public class TokenVO {
    private String userId;
    private String token;
}
