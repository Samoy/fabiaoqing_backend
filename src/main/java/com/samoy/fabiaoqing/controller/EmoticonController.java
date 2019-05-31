package com.samoy.fabiaoqing.controller;

import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ApiResult;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.EmoticonService;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import com.samoy.fabiaoqing.viewobject.EmoticonVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}
