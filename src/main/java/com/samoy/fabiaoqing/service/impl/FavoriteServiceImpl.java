package com.samoy.fabiaoqing.service.impl;

import com.github.pagehelper.PageHelper;
import com.samoy.fabiaoqing.dao.EmoticonDAO;
import com.samoy.fabiaoqing.dao.FavoriteDAO;
import com.samoy.fabiaoqing.domainobject.EmoticonDO;
import com.samoy.fabiaoqing.domainobject.FavoriteDO;
import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.FavoriteService;
import com.samoy.fabiaoqing.util.CommonUtils;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * FavoriteServiceImpl
 * 收藏暂不用redis
 *
 * @author Samoy
 * @date 2019/11/20
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Resource
    private FavoriteDAO favoriteDAO;
    @Resource
    private EmoticonDAO emoticonDAO;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addFavorite(String userId, String emoticonId) throws BusinessException {
        FavoriteDO favoriteDO = new FavoriteDO();
        favoriteDO.setObjectId(CommonUtils.randomObjectId());
        favoriteDO.setUserId(userId);
        favoriteDO.setEmoticonId(emoticonId);
        try {
            return favoriteDAO.insertSelective(favoriteDO) == 1;
        } catch (DuplicateKeyException e) {
            throw new BusinessException(ResponseEnum.FAVORITE_ADD_FAILURE.getCode(), "您已收藏过该表情");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteFavorite(String userId, String emoticonId) {
        return favoriteDAO.deleteByEmoticonId(userId, emoticonId) == 1;
    }

    @Override
    public List<EmoticonDTO> listFavorite(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<FavoriteDO> favoriteDOList = favoriteDAO.selectByUserId(userId);
        if (CollectionUtils.isEmpty(favoriteDOList)) {
            return Collections.emptyList();
        }
        return favoriteDOList.stream().map(item -> {
            List<EmoticonDO> emoticonDOS = emoticonDAO.selectByObjectId(item.getEmoticonId());
            if (CollectionUtils.isEmpty(emoticonDOS)) {
                return null;
            }
            return MyBeanUtils.convertEmoticonDOToDTO(emoticonDOS.get(0));
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
