package com.samoy.fabiaoqing.service;

import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.expection.BusinessException;

import java.util.List;

/**
 * 表情服务接口
 *
 * @author Samoy
 * @date 2019-05-30
 */
public interface EmoticonService {

    /**
     * 通过表情包id查询表情
     *
     * @param parentId 父id，也就是表情包id
     * @return 表情列表
     * @throws BusinessException 业务异常
     */
    List<EmoticonDTO> findByParentId(String parentId) throws BusinessException;

    /**
     * 通过id查询表情
     *
     * @param objectId id
     * @return 表情
     * @throws BusinessException 业务异常
     */
    EmoticonDTO findByObjectId(String objectId) throws BusinessException;

    /**
     * 通过关键字查询表情
     *
     * @param keyword  关键字
     * @param page     页面
     * @param pageSize 每页条数
     * @return 表情列表
     * @throws BusinessException 业务异常
     */
    List<EmoticonDTO> findByKeyword(String keyword, Integer page, Integer pageSize) throws BusinessException;
}
