package com.samoy.fabiaoqing.service.impl;

import com.samoy.fabiaoqing.dao.UserDAO;
import com.samoy.fabiaoqing.domainobject.UserDO;
import com.samoy.fabiaoqing.dto.UserDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.UserService;
import com.samoy.fabiaoqing.util.CommonUtils;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import com.samoy.fabiaoqing.viewobject.TokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    /**
     * token 30天过期
     */
    private static final long TOKEN_EXPIRE_TIME = 30;

    @Resource
    private UserDAO userDAO;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
