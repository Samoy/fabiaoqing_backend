package com.samoy.fabiaoqing.service;

import com.samoy.fabiaoqing.dto.PackageDTO;
import com.samoy.fabiaoqing.expection.BusinessException;

import java.util.List;

/**
 * 表情包服务接口
 *
 * @author Samoy
 * @date 2019-05-30
 */
public interface PackageService {
    /**
     * 查询表情包
     *
     * @param parentId 父id,也就是类别id
     * @param page     页码
     * @param pageSize 每页条数
     * @return 表情包列表
     * @throws BusinessException 业务异常
     */
    List<PackageDTO> findAll(String parentId, Integer page, Integer pageSize) throws BusinessException;

    /**
     * 通过关键字查询表情包
     *
     * @param keyword  关键字
     * @param page     页码
     * @param pageSize 每页条数
     * @return 表情包列表
     * @throws BusinessException 业务异常
     */
    List<PackageDTO> findByKeyword(String keyword, Integer page, Integer pageSize) throws BusinessException;
}
