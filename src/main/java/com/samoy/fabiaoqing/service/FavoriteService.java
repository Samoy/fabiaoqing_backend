package com.samoy.fabiaoqing.service;

import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.expection.BusinessException;

import java.util.List;

/**
 * FavoriteService
 *
 * @author Samoy
 * @date 2019-11-20
 */
public interface FavoriteService {
    /**
     * 添加收藏
     *
     * @param userId     用户id
     * @param emoticonId 表情id
     * @return 是否收藏成功
     * @throws BusinessException 业务异常
     */
    Boolean addFavorite(String userId, String emoticonId) throws BusinessException;

    /**
     * 删除收藏
     *
     * @param userId     用户id
     * @param emoticonId 表情id
     * @return 是否删除成功
     */
    Boolean deleteFavorite(String userId, String emoticonId);

    /**
     * 获取收藏列表
     *
     * @param userId   用户id
     * @param page     页面
     * @param pageSize 每页条数
     * @return 列表
     */
    List<EmoticonDTO> listFavorite(String userId, Integer page, Integer pageSize);
}
