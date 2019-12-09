package com.samoy.fabiaoqing.controller;

import com.samoy.fabiaoqing.dto.UserDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ApiResult;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.UserService;
import com.samoy.fabiaoqing.util.CommonUtils;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import com.samoy.fabiaoqing.viewobject.TokenVO;
import com.samoy.fabiaoqing.viewobject.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

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
    public ApiResult register(@Validated @ModelAttribute UserDTO userDTO) throws BusinessException {
        TokenVO token = userService.register(userDTO);
        return token != null ? ApiResult.success(token) : ApiResult.failure(ResponseEnum.REGISTER_FAILURE);
    }

    @PostMapping("/login")
    public ApiResult login(@RequestParam String telephone, @RequestParam String password) throws BusinessException {
        TokenVO token = userService.login(telephone, password);
        return token == null ? ApiResult.failure(ResponseEnum.LOGIN_FAILURE) : ApiResult.success(token);
    }

    @PostMapping("/login_by_code")
    public ApiResult loginByCode(@RequestParam String telephone, @RequestParam String code) throws BusinessException {
        TokenVO token = userService.loginByCode(telephone, code);
        return token == null ? ApiResult.failure(ResponseEnum.LOGIN_FAILURE) : ApiResult.success(token);
    }

    @PostMapping("/send_code")
    public ApiResult sendSmsCode(@RequestParam String telephone) throws BusinessException {
        String sms = userService.sendSmsCode(telephone);
        return ApiResult.success(sms);
    }

    @GetMapping("/profile")
    public ApiResult profile(@RequestParam String userId) throws BusinessException {
        UserVO userVO = MyBeanUtils.convertUserDTOToVO(userService.findUserById(userId));
        return ApiResult.success(userVO);
    }

    @PostMapping("/update_profile")
    public ApiResult updateProfile(@RequestParam String userId,
                                   @RequestParam(required = false) MultipartFile avatar,
                                   @RequestParam(required = false) String nickname,
                                   @RequestParam(required = false) String sex,
                                   @RequestParam(required = false) String description) throws IOException, BusinessException {
        UserDTO userDTO = userService.updateProfile(userId, avatar, nickname, sex, description);
        if (userDTO == null) {
            return ApiResult.failure(ResponseEnum.UPDATE_PROFILE_FAILURE);
        }
        UserVO userVO = MyBeanUtils.convertUserDTOToVO(userDTO);
        return ApiResult.success("修改资料成功", userVO);
    }

    @GetMapping("/find_by_tel")
    public ApiResult findUserByTel(@RequestParam String tel) throws BusinessException {
        if (!CommonUtils.legalTelephone(tel)) {
            throw new BusinessException(ResponseEnum.TELEPHONE_ILLEGAL);
        }
        UserDTO userDTO = userService.findUserByTelephone(tel);
        return userDTO == null ? ApiResult.success("该手机号可用", null) : ApiResult.failure(ResponseEnum.TELEPHONE_EXISTS.getCode(), "新手机号码已被注册");
    }

    @PostMapping("/update_tel")
    public ApiResult updateTel(@RequestParam String userId,
                               @RequestParam String oldTel,
                               @RequestParam String newTel,
                               @RequestParam String code) throws BusinessException {
        Boolean success = userService.updateTel(userId, oldTel, newTel, code);
        return success ? ApiResult.success("修改成功，以后您将用新手机号码登录", null) : ApiResult.failure(ResponseEnum.UPDATE_TEL_FAILURE);
    }

    @PostMapping("/logout")
    public ApiResult logout(@RequestParam String userId) throws BusinessException {
        userService.logout(userId);
        return ApiResult.success("注销成功", null);
    }
}
