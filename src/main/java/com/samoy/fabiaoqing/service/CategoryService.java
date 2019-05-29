package com.samoy.fabiaoqing.service;

import com.samoy.fabiaoqing.dto.CategoryDTO;
import com.samoy.fabiaoqing.expection.BusinessException;

import java.util.List;

/**
 * 类别服务
 *
 * @author Samoy
 * @date 2019-05-29
 */
public interface CategoryService {
    /**
     * 查找所有类别
     *
     * @return 所有类别
     * @throws BusinessException 业务异常
     */
    List<CategoryDTO> findAll() throws BusinessException;
}
