package com.samoy.fabiaoqing.service.impl;

import com.samoy.fabiaoqing.dao.EmoticonDAO;
import com.samoy.fabiaoqing.domainobject.EmoticonDO;
import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.EmoticonService;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
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
    @Resource
    private EmoticonDAO emoticonDAO;

    @Override
    public List<EmoticonDTO> findByParentId(String parentId) throws BusinessException {
        List<EmoticonDO> emoticonDOList = emoticonDAO.selectByParentId(parentId);
        if (CollectionUtils.isEmpty(emoticonDOList)) {
            throw new BusinessException(ResponseEnum.EMOTICON_NOT_FOUNT);
        }
        return emoticonDOList.stream()
                .map(MyBeanUtils::convertEmoticonDOToDTO).collect(Collectors.toList());
    }

    @Override
    public EmoticonDTO findByObjectId(String objectId) throws BusinessException {
        List<EmoticonDO> emoticonDOList = emoticonDAO.selectByObjectId(objectId);
        if (CollectionUtils.isEmpty(emoticonDOList)) {
            throw new BusinessException(ResponseEnum.EMOTICON_NOT_FOUNT);
        }
        return MyBeanUtils.convertEmoticonDOToDTO(emoticonDOList.get(0));
    }
}
