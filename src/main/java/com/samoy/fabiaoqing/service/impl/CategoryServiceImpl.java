package com.samoy.fabiaoqing.service.impl;

import com.samoy.fabiaoqing.dao.CategoryDAO;
import com.samoy.fabiaoqing.domainobject.CategoryDO;
import com.samoy.fabiaoqing.dto.CategoryDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.CategoryService;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    /**
     * 由于该业务本身就需要查询表的全部数据，且并发量并不大，几乎等于静态数据
     * 因此无需使用Redis缓存。
     *
     * @return 所有类别数据
     * @throws BusinessException 业务异常
     */
    @Override
    public List<CategoryDTO> findAll() throws BusinessException {
        List<CategoryDO> categoryDOList = categoryDAO.selectAll();
        if (CollectionUtils.isEmpty(categoryDOList)) {
            throw new BusinessException(ResponseEnum.CATEGORY_NOT_FOUNT);
        }
        return categoryDOList.stream().map(MyBeanUtils::convertCategoryDOToDTO)
                .collect(Collectors.toList());
    }
}
