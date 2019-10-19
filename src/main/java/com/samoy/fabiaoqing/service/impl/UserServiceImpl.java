package com.samoy.fabiaoqing.service.impl;

import com.samoy.fabiaoqing.dao.UserDAO;
import com.samoy.fabiaoqing.domainobject.UserDO;
import com.samoy.fabiaoqing.dto.UserDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.UserService;
import com.samoy.fabiaoqing.util.CommonUtils;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import com.samoy.fabiaoqing.util.OssUtils;
import com.samoy.fabiaoqing.util.SmsUtils;
import com.samoy.fabiaoqing.viewobject.TokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * UserServiceImpl
 *
 * @author Samoy
 * @date 2019/9/2
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String SMS_OK = "ok";

    /**
     * token 30天过期
     */
    private static final long TOKEN_EXPIRE_TIME = 30;

    @Resource
    private UserDAO userDAO;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private OssUtils ossUtils;
    @Resource
    private SmsUtils smsUtils;

    @Override
    public TokenVO register(UserDTO userDTO) throws BusinessException {
        if (userDAO.selectByTelephone(userDTO.getTelephone()) != null) {
            throw new BusinessException(ResponseEnum.TELEPHONE_EXISTS);
        }
        int result = userDAO.insertSelective(MyBeanUtils.convertUserDTOToDO(userDTO));
        return result == 1 ? genToken(userDTO.getObjectId()) : null;
    }

    @Override
    public TokenVO login(String telephone, String password) throws BusinessException {
        if (StringUtils.isEmpty(telephone)) {
            throw new BusinessException(ResponseEnum.TELEPHONE_EMPTY);
        }
        if (StringUtils.isEmpty(password)) {
            throw new BusinessException(ResponseEnum.PASSWORD_EMPTY);
        }
        UserDTO userDTO = findUserByTelephone(telephone);
        if (!Objects.equals(password, userDTO.getPassword())) {
            throw new BusinessException(ResponseEnum.PASSWORD_MISMATCH);
        }
        //删除上次登录的token
        String lastToken = stringRedisTemplate.opsForValue().get(userDTO.getObjectId());
        if (lastToken != null) {
            stringRedisTemplate.delete(lastToken);
        }
        return genToken(userDTO.getObjectId());
    }

    @Override
    public TokenVO loginByCode(String telephone, String code) throws BusinessException {
        if (StringUtils.isEmpty(telephone)) {
            throw new BusinessException(ResponseEnum.TELEPHONE_EMPTY);
        }
        if (StringUtils.isEmpty(code)) {
            throw new BusinessException(ResponseEnum.VERIFY_CODE_EMPTY);
        }
        String smsResult = smsUtils.verifySms(telephone, code).toLowerCase();
        if (SMS_OK.equals(smsResult)) {
            UserDO userDO = userDAO.selectByTelephone(telephone);
            //若该用户不存在，则直接创建一个新用户
            if (userDO == null) {
                UserDTO userDTO = new UserDTO();
                userDTO.setTelephone(telephone);
                userDTO.setPassword("");
                userDTO.setNickname("发表情用户" + CommonUtils.randomUserId());
                return register(userDTO);
            }
            return genToken(userDO.getObjectId());
        } else {
            throw new BusinessException(ResponseEnum.SMS_VERIFY_FAILURE.getCode(), smsResult);
        }
    }

    @Override
    public String sendSmsCode(String telephone) throws BusinessException {
        if (StringUtils.isEmpty(telephone)) {
            throw new BusinessException(ResponseEnum.TELEPHONE_EMPTY);
        }
        String smsResult = smsUtils.sendSms(telephone);
        if (CommonUtils.isNumericString(smsResult)) {
            return smsResult;
        } else {
            throw new BusinessException(ResponseEnum.SMS_SEND_FAILURE.getCode(), smsResult);
        }
    }

    @Override
    public UserDTO findUserByTelephone(String telephone) throws BusinessException {
        UserDO userDO = userDAO.selectByTelephone(telephone);
        if (userDO == null) {
            throw new BusinessException(ResponseEnum.USER_NOT_FOUND);
        }
        return MyBeanUtils.convertUserDOToDTO(userDO);
    }

    @Override
    public UserDTO findUserById(String userId) throws BusinessException {
        UserDO userDO = userDAO.selectByPrimaryKey(userId);
        if (userDO == null) {
            throw new BusinessException(ResponseEnum.USER_NOT_FOUND);
        }
        return MyBeanUtils.convertUserDOToDTO(userDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String uploadAvatar(String objectId, MultipartFile file) throws BusinessException, IOException {
        if (!CommonUtils.isImage(file.getInputStream())) {
            throw new BusinessException(ResponseEnum.NOT_IMAGE);
        }
        String fileUrl = ossUtils.uploadAvatar(file);
        if (StringUtils.isEmpty(fileUrl)) {
            throw new BusinessException(ResponseEnum.FILE_UPLOAD_FAILURE);
        }
        int result = userDAO.updateAvatar(objectId, fileUrl);
        if (result != 1) {
            throw new BusinessException(ResponseEnum.FILE_UPLOAD_FAILURE);
        }
        return fileUrl;
    }

    @Override
    public void logout(String userId) throws BusinessException {
        UserDO userDO = userDAO.selectByPrimaryKey(userId);
        if (userDO == null) {
            throw new BusinessException(ResponseEnum.USER_NOT_FOUND);
        }
        String token = stringRedisTemplate.opsForValue().get(userId);
        if (token != null) {
            stringRedisTemplate.delete(userId);
            stringRedisTemplate.delete(token);
        }
    }

    /**
     * 生成token
     *
     * @param objectId 用户id
     * @return token
     */
    private TokenVO genToken(String objectId) {
        String token = CommonUtils.randomToken();
        //对token进行双向绑定
        stringRedisTemplate.opsForValue().set(objectId, token, TOKEN_EXPIRE_TIME, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(token, objectId, TOKEN_EXPIRE_TIME, TimeUnit.DAYS);
        TokenVO tokenVO = new TokenVO();
        tokenVO.setToken(token);
        tokenVO.setUserId(objectId);
        return tokenVO;
    }

}
