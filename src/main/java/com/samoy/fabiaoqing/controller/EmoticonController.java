package com.samoy.fabiaoqing.controller;

import com.github.pagehelper.PageHelper;
import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ApiResult;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.EmoticonService;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表情Controller
 *
 * @author Samoy
 * @date 2019-05-31
 */
@RestController
@RequestMapping("/emoticon")
public class EmoticonController {

    @Resource
    private EmoticonService emoticonService;

    @GetMapping("/detail")
    public ApiResult getEmoticonById(@RequestParam(name = "id") String objectId) throws BusinessException {
        if (StringUtils.isEmpty(objectId)) {
            throw new BusinessException(ResponseEnum.EMOTICON_ID_EMPTY);
        }
        EmoticonDTO emoticonDTO = emoticonService.findByObjectId(objectId);
        return ApiResult.success(MyBeanUtils.convertEmoticonDTOToVO(emoticonDTO));
    }

    @GetMapping("/search")
    public ApiResult getEmoticonListBySearch(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) throws BusinessException {
        PageHelper.startPage(page, pageSize);
        List<EmoticonDTO> emoticonDTOList = emoticonService.findByKeyword(keyword, page, pageSize);
        return ApiResult.success(emoticonDTOList.stream().map(MyBeanUtils::convertEmoticonDTOToVO).collect(Collectors.toList()));
    }
}
