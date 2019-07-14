package com.samoy.fabiaoqing.service;

import com.samoy.fabiaoqing.dto.TagDTO;
import com.samoy.fabiaoqing.expection.BusinessException;

import java.util.List;

/**
 * TagService
 *
 * @author Samoy
 * @date 2019-07-14
 */
public interface TagService {
    /**
     * 随机查询${count}个标签
     *
     * @param count 查询的个数
     * @return 标签列表
     * @throws BusinessException 业务异常
     */
    List<TagDTO> findRandTags(Integer count) throws BusinessException;
}
