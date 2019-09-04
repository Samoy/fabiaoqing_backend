package com.samoy.fabiaoqing.service.impl;

import com.github.pagehelper.PageHelper;
import com.samoy.fabiaoqing.dao.EmoticonDAO;
import com.samoy.fabiaoqing.domainobject.EmoticonDO;
import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.EmoticonService;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 表情服务实现类
 *
 * @author Samoy
 * @date 2019-05-30
 */
@Service
public class EmoticonServiceImpl implements EmoticonService {

    /**
     * Redis键的前缀，为了区分不同的DO,
     * clearable表示该键可被清除
     */
    private static final String REDIS_PREFIX = "clearable_emoticon_";

    @Resource
    private EmoticonDAO emoticonDAO;
    @Resource
    private RedisTemplate<String, List<EmoticonDO>> redisTemplate;

    @Override
    public List<EmoticonDTO> findByParentId(String parentId) throws BusinessException {
        List<EmoticonDO> emoticonDOList = redisTemplate.opsForValue().get(REDIS_PREFIX + parentId);
        if (emoticonDOList == null) {
            emoticonDOList = emoticonDAO.selectByParentId(parentId);
            redisTemplate.opsForValue().set(REDIS_PREFIX + parentId, emoticonDOList);
        }
        if (Objects.isNull(emoticonDOList)) {
            throw new BusinessException(ResponseEnum.EMOTICON_NOT_FOUNT);
        }
        return emoticonDOList.stream()
                .map(MyBeanUtils::convertEmoticonDOToDTO).collect(Collectors.toList());
    }

    @Override
    public EmoticonDTO findByObjectId(String objectId) throws BusinessException {
        List<EmoticonDO> emoticonDOList = redisTemplate.opsForValue().get(REDIS_PREFIX + objectId);
        if (emoticonDOList == null) {
            emoticonDOList = emoticonDAO.selectByObjectId(objectId);
            redisTemplate.opsForValue().set(REDIS_PREFIX + objectId, emoticonDOList);
        }
        if (Objects.isNull(emoticonDOList)) {
            throw new BusinessException(ResponseEnum.EMOTICON_NOT_FOUNT);
        }
        return MyBeanUtils.convertEmoticonDOToDTO(emoticonDOList.get(0));
    }

    @Override
    public List<EmoticonDTO> findByKeyword(String keyword, Integer page, Integer pageSize) throws BusinessException {
        String redisKey = REDIS_PREFIX + "p_" + page + "_s_" + pageSize + "_" + keyword;
        List<EmoticonDO> emoticonDOList = redisTemplate.opsForValue().get(redisKey);
        if (emoticonDOList == null) {
            PageHelper.startPage(page, pageSize);
            emoticonDOList = emoticonDAO.selectByNameLike(keyword);
            redisTemplate.opsForValue().set(redisKey, emoticonDOList);
        }
        if (Objects.isNull(emoticonDOList)) {
            throw new BusinessException(ResponseEnum.EMOTICON_NOT_FOUNT);
        }
        return emoticonDOList.stream()
                .map(MyBeanUtils::convertEmoticonDOToDTO)
                .collect(Collectors.toList());
    }
}
