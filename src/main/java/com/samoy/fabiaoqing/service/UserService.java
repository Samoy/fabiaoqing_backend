package com.samoy.fabiaoqing.service;

import com.samoy.fabiaoqing.dto.UserDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.viewobject.TokenVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * RegisterService
 *
 * @author Samoy
 * @date 2019/9/2
 */
public interface UserService {
    /**
     * 注册
     *
     * @param userDTO userDTO
     * @return token
     * @throws BusinessException 业务异常
     */
    TokenVO register(UserDTO userDTO) throws BusinessException;

    /**
     * 登录
     *
     * @param telephone 手机号码
     * @param password  密码
     * @return token
     * @throws BusinessException 业务异常
     */
    TokenVO login(String telephone, String password) throws BusinessException;


    /**
     * 通过验证码登录，若用户不存在会直接创建一个新用户
     *
     * @param telephone 手机号码
     * @param code      验证码
     * @return Token
     * @throws BusinessException 业务异常
     */
    TokenVO loginByCode(String telephone, String code) throws BusinessException;

    /**
     * 发送短信验证码
     *
     * @param telephone 手机号
     * @return 验证码
     * @throws BusinessException 业务异常
     */
    String sendSmsCode(String telephone) throws BusinessException;

    /**
     * 通过手机号找到用户
     *
     * @param telephone 手机号码
     * @return 用户DTO
     * @throws BusinessException 业务异常
     */
    UserDTO findUserByTelephone(String telephone) throws BusinessException;

    /**
     * 通过用户id找到用户
     *
     * @param userId userId
     * @return 用户DTO
     * @throws BusinessException 业务异常
     */
    UserDTO findUserById(String userId) throws BusinessException;

    /**
     * 上传用户头像
     *
     * @param file     文件
     * @param objectId 用户id
     * @return 头像url
     * @throws BusinessException 业务异常
     * @throws IOException       IO异常
     */
    String uploadAvatar(String objectId, MultipartFile file) throws BusinessException, IOException;

    /**
     * 退出登录
     *
     * @param userId 用户id
     * @throws BusinessException 业务异常
     */
    void logout(String userId) throws BusinessException;
}
