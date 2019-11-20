package com.samoy.fabiaoqing.dto;

import lombok.Data;
import lombok.ToString;

/**
 * FavoriteDTO
 *
 * @author Samoy
 * @date 2019/11/20
 */
@Data
@ToString
public class FavoriteDTO {
    private String objectId;
    private String userId;
    private String emoticonId;
}
