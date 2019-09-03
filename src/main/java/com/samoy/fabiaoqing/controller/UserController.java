package com.samoy.fabiaoqing.controller;

import com.samoy.fabiaoqing.dto.UserDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ApiResult;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.UserService;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import com.samoy.fabiaoqing.viewobject.TokenVO;
import com.samoy.fabiaoqing.viewobject.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * UserController
 *
 * @author Samoy
 * @date 2019/9/2
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping(value = "/register")
    public ApiResult register(@Validated @RequestBody UserDTO userDTO) throws BusinessException {
        TokenVO token = userService.register(userDTO);
        return token != null ? ApiResult.success(token) : ApiResult.failure(ResponseEnum.REGISTER_FAILURE);
    }

    @PostMapping("/login")
    public ApiResult login(@RequestParam String telephone, @RequestParam String password) throws BusinessException {
        TokenVO token = userService.login(telephone, password);
        return ApiResult.success(token);
    }

    @GetMapping("/profile")
    public ApiResult profile(@RequestParam String userId) throws BusinessException {
        UserVO userVO = MyBeanUtils.convertUserDTOToVO(userService.findUserById(userId));
        return ApiResult.success(userVO);
    }

    @PostMapping("/logout")
    public ApiResult logout(@RequestParam String userId) throws BusinessException {
        userService.logout(userId);
        return ApiResult.success("注销成功", null);
    }
}
