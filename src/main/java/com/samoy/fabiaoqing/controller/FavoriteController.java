package com.samoy.fabiaoqing.controller;

import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ApiResult;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.FavoriteService;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import com.samoy.fabiaoqing.viewobject.EmoticonVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FavoriteController
 *
 * @author Samoy
 * @date 2019/11/20
 */
@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Resource
    private FavoriteService favoriteService;

    @GetMapping("/list")
    public ApiResult listFavorite(@RequestParam String userId,
                                  @RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        List<EmoticonDTO> emoticonDTOS = favoriteService.listFavorite(userId, page, pageSize);
        List<EmoticonVO> emoticonVOS = emoticonDTOS.stream().map(MyBeanUtils::convertEmoticonDTOToVO).collect(Collectors.toList());
        return ApiResult.success(emoticonVOS);
    }

    @PostMapping("/add")
    public ApiResult addFavorite(@RequestParam String userId, @RequestParam String emoticonId) throws BusinessException {
        Boolean success = favoriteService.addFavorite(userId, emoticonId);
        return success ? ApiResult.success("收藏成功", null) : ApiResult.failure(ResponseEnum.FAVORITE_ADD_FAILURE);
    }

    @PostMapping("/delete")
    public ApiResult deleteFavorite(@RequestParam String userId, @RequestParam String emoticonId) {
        Boolean success = favoriteService.deleteFavorite(userId, emoticonId);
        return success ? ApiResult.success("删除成功", null) : ApiResult.failure(ResponseEnum.FAVORITE_DELETE_FAILURE);

    }
}
