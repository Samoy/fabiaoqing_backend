package com.samoy.fabiaoqing.service.impl;

import com.samoy.fabiaoqing.dto.PackageDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.service.PackageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class PackageServiceImplTest {

    @Resource
    private PackageService packageService;

    @Test
    public void findAll() throws BusinessException {
        List<PackageDTO> packageDTOList = packageService.findAll("f4483e8dcfd85ba9");
        assertNotNull(packageDTOList);
        if (!CollectionUtils.isEmpty(packageDTOList)) {
            log.info(packageDTOList.get(0).toString());
        }
    }
}