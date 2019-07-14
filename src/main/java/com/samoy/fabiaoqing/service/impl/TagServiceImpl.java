package com.samoy.fabiaoqing.service.impl;

import com.samoy.fabiaoqing.dao.TagDAO;
import com.samoy.fabiaoqing.domainobject.TagDO;
import com.samoy.fabiaoqing.dto.TagDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.TagService;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TagServiceImpl
 *
 * @author Samoy
 * @date 2019-07-14
 */
@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagDAO tagDAO;

    @Override
    public List<TagDTO> findRandTags(Integer count) throws BusinessException {
        List<TagDO> tagDOS = tagDAO.selectTagsRandom(count);
        if (CollectionUtils.isEmpty(tagDOS)) {
            throw new BusinessException(ResponseEnum.TAG_NOT_FOUND);
        }
        return tagDOS.stream().map(MyBeanUtils::convertTagDOToDTO).collect(Collectors.toList());
    }
}
