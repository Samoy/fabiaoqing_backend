package com.samoy.fabiaoqing.controller;

import com.samoy.fabiaoqing.dto.TagDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ApiResult;
import com.samoy.fabiaoqing.service.TagService;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * TagController
 *
 * @author Samoy
 * @date 2019-07-14
 */
@RestController
@RequestMapping("/tag")
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("/rand_list")
    public ApiResult getRandTags(@RequestParam(defaultValue = "10") Integer count) throws BusinessException {
        List<TagDTO> tagDTOS = tagService.findRandTags(count);
        return ApiResult.success(tagDTOS.stream().map(MyBeanUtils::convertTagDTOToVO));
    }
}
