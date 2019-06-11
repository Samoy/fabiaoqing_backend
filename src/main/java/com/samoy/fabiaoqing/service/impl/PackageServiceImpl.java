package com.samoy.fabiaoqing.service.impl;

import com.samoy.fabiaoqing.dao.PackageDAO;
import com.samoy.fabiaoqing.domainobject.PackageDO;
import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.dto.PackageDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.EmoticonService;
import com.samoy.fabiaoqing.service.PackageService;
import com.samoy.fabiaoqing.util.MyBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表情包服务实现类
 *
 * @author Samoy
 * @date 2019-05-30
 */
@Service
@Slf4j
public class PackageServiceImpl implements PackageService {

    /**
     * Redis键的前缀，为了区分不同的DO
     */
    private static final String REDIS_PREFIX = "package_";

    @Resource
    private PackageDAO packageDAO;
    @Resource
    private EmoticonService emoticonService;
    @Resource
    private RedisTemplate<String, List<PackageDO>> redisTemplate;

    @Override
    public List<PackageDTO> findAll(String parentId) throws BusinessException {
        List<PackageDO> packageDOList = redisTemplate.opsForValue().get(REDIS_PREFIX + parentId);
        //当缓存中无数据时，从数据库中查询，无论查出数据是否为空都存入缓存，下次再次查询直接返回缓存中的数据，避免缓存击穿
        if (packageDOList == null) {
            packageDOList = packageDAO.selectByParentId(parentId);
            redisTemplate.opsForValue().set(REDIS_PREFIX + parentId, packageDOList);
        }
        if (CollectionUtils.isEmpty(packageDOList)) {
            throw new BusinessException(ResponseEnum.PACKAGE_NOT_FOUND);
        }
        return packageDOList.stream()
                .map(this::getPackageDTO)
                .filter(packageDTO -> !CollectionUtils.isEmpty(packageDTO.getEmoticonDTOList()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PackageDTO> findByKeyword(String keyword) throws BusinessException {
        List<PackageDO> packageDOList = redisTemplate.opsForValue().get(REDIS_PREFIX + keyword);
        if (packageDOList == null) {
            packageDOList = packageDAO.selectByNameLike(keyword);
            redisTemplate.opsForValue().set(REDIS_PREFIX + keyword, packageDOList);
        }
        if (CollectionUtils.isEmpty(packageDOList)) {
            throw new BusinessException(ResponseEnum.PACKAGE_NOT_FOUND);
        }
        return packageDOList.stream()
                .map(this::getPackageDTO)
                .filter(packageDTO -> !CollectionUtils.isEmpty(packageDTO.getEmoticonDTOList()))
                .collect(Collectors.toList());
    }

    private PackageDTO getPackageDTO(PackageDO packageDO) {
        List<EmoticonDTO> emoticonDTOList = new ArrayList<>();
        try {
            emoticonDTOList = emoticonService.findByParentId(packageDO.getObjectId());
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return MyBeanUtils.convertPackageDOToDTO(packageDO, emoticonDTOList);
    }
}
