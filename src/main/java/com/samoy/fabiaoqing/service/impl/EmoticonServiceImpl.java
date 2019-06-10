package com.samoy.fabiaoqing.service.impl;

import com.samoy.fabiaoqing.dao.EmoticonDAO;
import com.samoy.fabiaoqing.domainobject.EmoticonDO;
import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.EmoticonService;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
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
     * Redis键的前缀，为了区分不同的DO
     */
    private static final String REDIS_PREFIX = "emoticon_";

    @Resource
    private EmoticonDAO emoticonDAO;
    @Resource
    private RedisTemplate<String, List<EmoticonDO>> redisTemplate;

    @Override
    public List<EmoticonDTO> findByParentId(String parentId) throws BusinessException {
        List<EmoticonDO> emoticonDOList = redisTemplate.opsForValue().get(parentId);
        if (CollectionUtils.isEmpty(emoticonDOList)) {
            emoticonDOList = emoticonDAO.selectByParentId(REDIS_PREFIX + parentId);
            if (CollectionUtils.isEmpty(emoticonDOList)) {
                throw new BusinessException(ResponseEnum.EMOTICON_NOT_FOUNT);
            } else {
                redisTemplate.opsForValue().set(REDIS_PREFIX + parentId, emoticonDOList);
            }
        }
        return emoticonDOList.stream()
                .map(MyBeanUtils::convertEmoticonDOToDTO).collect(Collectors.toList());
    }

    @Override
    public EmoticonDTO findByObjectId(String objectId) throws BusinessException {
        List<EmoticonDO> emoticonDOList = redisTemplate.opsForValue().get(REDIS_PREFIX + objectId);
        if (CollectionUtils.isEmpty(emoticonDOList)) {
            emoticonDOList = emoticonDAO.selectByObjectId(REDIS_PREFIX + objectId);
            if (CollectionUtils.isEmpty(emoticonDOList)) {
                throw new BusinessException(ResponseEnum.EMOTICON_NOT_FOUNT);
            } else {
                redisTemplate.opsForValue().set(REDIS_PREFIX + objectId, emoticonDOList);
            }
        }
        return MyBeanUtils.convertEmoticonDOToDTO(emoticonDOList.get(0));
    }
}
