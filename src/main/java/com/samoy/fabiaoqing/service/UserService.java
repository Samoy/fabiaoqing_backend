package com.samoy.fabiaoqing.service;

import com.samoy.fabiaoqing.dto.UserDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.viewobject.TokenVO;

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
     * 退出登录
     *
     * @param userId 用户id
     * @throws BusinessException 业务异常
     */
    void logout(String userId) throws BusinessException;
}
