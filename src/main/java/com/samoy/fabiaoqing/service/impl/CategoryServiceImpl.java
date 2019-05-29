package com.samoy.fabiaoqing.service.impl;

import com.samoy.fabiaoqing.dao.CategoryDAO;
import com.samoy.fabiaoqing.domainobject.CategoryDO;
import com.samoy.fabiaoqing.dto.CategoryDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类别服务实现类
 *
 * @author Samoy
 * @date 2019-05-29
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDAO categoryDAO;

    @Override
    public List<CategoryDTO> findAll() throws BusinessException {
        List<CategoryDO> categoryDOList = categoryDAO.selectAll();
        if (categoryDOList == null) {
            throw new BusinessException(ResponseEnum.CATEGORY_NOT_FOUNT);
        }
        return categoryDOList.stream().map(this::covertDOToDTO)
                .collect(Collectors.toList());
    }

    private CategoryDTO covertDOToDTO(CategoryDO categoryDO) {
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(categoryDO, categoryDTO);
        return categoryDTO;
    }
}
