package com.samoy.fabiaoqing.service.impl;

import com.samoy.fabiaoqing.dao.PackageDAO;
import com.samoy.fabiaoqing.domainobject.PackageDO;
import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.dto.PackageDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ResponseEnum;
import com.samoy.fabiaoqing.service.EmoticonService;
import com.samoy.fabiaoqing.service.PackageService;
import org.springframework.beans.BeanUtils;
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
public class PackageServiceImpl implements PackageService {

    @Resource
    private PackageDAO packageDAO;
    @Resource
    private EmoticonService emoticonService;

    @Override
    public List<PackageDTO> findAll(String parentId) throws BusinessException {
        List<PackageDO> packageDOList = packageDAO.selectByParentId(parentId);
        if (CollectionUtils.isEmpty(packageDOList)) {
            throw new BusinessException(ResponseEnum.PACKAGE_NOT_FOUND);
        }
        return packageDOList.stream().map(this::convertDOToDTO).collect(Collectors.toList());
    }

    private PackageDTO convertDOToDTO(PackageDO packageDO) {
        PackageDTO packageDTO = new PackageDTO();
        BeanUtils.copyProperties(packageDO, packageDTO);
        List<EmoticonDTO> emoticonDTOList = new ArrayList<>();
        try {
            emoticonDTOList = emoticonService.findByParentId(packageDO.getObjectId());
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        packageDTO.setEmoticonDTOList(emoticonDTOList);
        return packageDTO;
    }
}
