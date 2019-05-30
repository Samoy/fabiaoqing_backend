package com.samoy.fabiaoqing.controller;

import com.github.pagehelper.PageHelper;
import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.dto.PackageDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ApiResult;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.EmoticonService;
import com.samoy.fabiaoqing.service.PackageService;
import com.samoy.fabiaoqing.viewobject.EmoticonVO;
import com.samoy.fabiaoqing.viewobject.PackageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
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
public class PackageController {

    /**
     * 列表展示表情包的最大张数
     */
    private static final int MAX_LIST_COUNT = 4;

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
        PageHelper.startPage(page, pageSize);
        List<PackageDTO> packageDTOList = packageService.findAll(categoryId);
        return ApiResult.success(packageDTOList.stream().map(this::convertPackageDTOToVO).collect(Collectors.toList()));
    }

    /**
     * 表情包DTO转VO
     *
     * @param packageDTO 表情包DTO
     * @return 表情包VO
     */
    private PackageVO convertPackageDTOToVO(PackageDTO packageDTO) {
        PackageVO packageVO = new PackageVO();
        BeanUtils.copyProperties(packageDTO, packageVO);
        List<EmoticonDTO> emoticonDTOList = packageDTO.getEmoticonDTOList();
        if (!CollectionUtils.isEmpty(emoticonDTOList) && emoticonDTOList.size() > MAX_LIST_COUNT) {
            packageVO.setList(emoticonDTOList.subList(0, MAX_LIST_COUNT)
                    .stream().map(this::convertEmoticonDTOToVO).collect(Collectors.toList()));
        }
        return packageVO;
    }

    /**
     * 表情DTO转VO
     *
     * @param emoticonDTO 表情DTO
     * @return 表情VO
     */
    private EmoticonVO convertEmoticonDTOToVO(EmoticonDTO emoticonDTO) {
        EmoticonVO emoticonVO = new EmoticonVO();
        BeanUtils.copyProperties(emoticonDTO, emoticonVO);
        return emoticonVO;
    }
}
