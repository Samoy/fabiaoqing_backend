package com.samoy.fabiaoqing.service.impl;

import com.samoy.fabiaoqing.dto.TagDTO;
import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * TagServiceImplTest
 *
 * @author Samoy
 * @date 2019-07-14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TagServiceImplTest {

    @Resource
    private TagService tagService;

    @Test
    public void findRandTags() throws BusinessException {
        List<TagDTO> tagDTOS = tagService.findRandTags(10);
        assertEquals(10, tagDTOS.size());
    }
}