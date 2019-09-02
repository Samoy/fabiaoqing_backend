package com.samoy.fabiaoqing.service;

import com.samoy.fabiaoqing.domainobject.UserDO;
import com.samoy.fabiaoqing.dto.UserDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import org.springframework.stereotype.Service;

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
    public String register(UserDTO userDTO) throws BusinessException;
}
