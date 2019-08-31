package com.samoy.fabiaoqing.controller;

import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.dto.PackageDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ApiResult;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.EmoticonService;
import com.samoy.fabiaoqing.service.PackageService;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表情包Controller
 *
 * @author Samoy
 * @date 2019-05-30
 */
@RestController
@RequestMapping("/package")
@Slf4j
public class PackageController {

    @Resource
    private PackageService packageService;
    @Resource
    private EmoticonService emoticonService;

    @GetMapping("/list")
    public ApiResult packageList(@RequestParam String categoryId,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "10") int pageSize
    ) throws BusinessException {
        if (StringUtils.isEmpty(categoryId)) {
            throw new BusinessException(ResponseEnum.CATEGORY_ID_EMPTY);
        }
        List<PackageDTO> packageDTOList = packageService.findAll(categoryId, page, pageSize);
        return ApiResult.success(packageDTOList.stream().map(MyBeanUtils::convertPackageDTOToVO).collect(Collectors.toList()));
    }

    @GetMapping("/list/detail")
    public ApiResult packageDetail(@RequestParam(name = "id") String packageId) throws BusinessException {
        if (StringUtils.isEmpty(packageId)) {
            throw new BusinessException(ResponseEnum.PACKAGE_ID_EMPTY);
        }
        List<EmoticonDTO> emoticonDTOList = emoticonService.findByParentId(packageId);
        return ApiResult.success(emoticonDTOList.stream().map(MyBeanUtils::convertEmoticonDTOToVO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/list/search")
    public ApiResult packageSearch(@RequestParam(defaultValue = "") String keyword,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "10") int pageSize
    ) throws BusinessException {
        List<PackageDTO> packageDTOList = packageService.findByKeyword(keyword, page, pageSize);
        return ApiResult.success(packageDTOList.stream().map(MyBeanUtils::convertPackageDTOToVO).collect(Collectors.toList()));
    }

}
