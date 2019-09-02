package com.samoy.fabiaoqing.service.impl;

import com.samoy.fabiaoqing.dao.UserDAO;
import com.samoy.fabiaoqing.dto.UserDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.UserService;
import com.samoy.fabiaoqing.util.JwtTokenUtil;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * UserServiceImpl
 *
 * @author Samoy
 * @date 2019/9/2
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;

    @Override
    public String register(UserDTO userDTO) throws BusinessException {
        if (userDAO.selectByTelephone(userDTO.getTelephone()) != null) {
            throw new BusinessException(ResponseEnum.TELEPHONE_EXISTS);
        }
        int result = userDAO.insertSelective(MyBeanUtils.convertUserDTOToDO(userDTO));
        return result == 1 ? genToken(userDTO.getObjectId(), userDTO.getPassword()) : null;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 生成token
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    private String genToken(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

}
