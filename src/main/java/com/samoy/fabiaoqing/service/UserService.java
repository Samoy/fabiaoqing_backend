package com.samoy.fabiaoqing.service;

import com.samoy.fabiaoqing.dto.UserDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.viewobject.TokenVO;
import com.samoy.fabiaoqing.viewobject.UserVO;
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
     * 修改个人资料
     *
     * @param userId      用户id
     * @param avatar      头像
     * @param nickname    昵称
     * @param sex         性别,1为男，0为女
     * @param description 介绍
     * @return UserDTO
     * @throws BusinessException 业务异常
     * @throws IOException       IO异常
     */
    UserDTO updateProfile(String userId, MultipartFile avatar, String nickname, String sex, String description) throws BusinessException, IOException;

    /**
     * 修改手机号
     *
     * @param userId 用户id
     * @param oldTel 旧手机号码
     * @param newTel 新手机号码
     * @param code   新手机验证码
     * @return 是否修改成功
     * @throws BusinessException 业务异常
     */
    Boolean updateTel(String userId, String oldTel, String newTel, String code) throws BusinessException;

    /**
     * 判断该用户是否设置了密码
     *
     * @param userId 用户id
     * @return 是否有密码
     */
    Boolean hasPsd(String userId) throws BusinessException;

    /**
     * 修改密码
     *
     * @param userId 用户id
     * @param oldPsd 旧密码
     * @param newPsd 新密码
     * @return 是否修改成功
     * @throws BusinessException 业务异常
     */
    Boolean updatePsd(String userId, String oldPsd, String newPsd) throws BusinessException;

    /**
     * 重置密码
     *
     * @param telephone 手机号
     * @param code      验证码
     * @param password  密码
     * @return
     * @throws BusinessException
     */
    Boolean resetPsd(String telephone, String code, String password) throws BusinessException;

    /**
     * 退出登录
     *
     * @param userId 用户id
     * @throws BusinessException 业务异常
     */
    void logout(String userId) throws BusinessException;
}
