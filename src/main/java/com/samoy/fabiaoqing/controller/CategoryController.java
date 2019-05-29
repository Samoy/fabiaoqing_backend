package com.samoy.fabiaoqing.controller;

import com.samoy.fabiaoqing.dto.CategoryDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ApiResult;
import com.samoy.fabiaoqing.service.CategoryService;
import com.samoy.fabiaoqing.viewobject.CategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类别Controller
 *
 * @author Samoy
 * @date 2019-05-23
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public ApiResult list() throws BusinessException {
        List<CategoryDTO> categoryDTOList = categoryService.findAll();
        return ApiResult.success(categoryDTOList.stream().map(this::covertDTOToVO)
                .collect(Collectors.toList()));
    }

    private CategoryVO covertDTOToVO(CategoryDTO categoryDTO) {
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(categoryDTO, categoryVO);
        return categoryVO;
    }
}
