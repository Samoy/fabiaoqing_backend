package com.samoy.fabiaoqing.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * SmsUtilsTest
 *
 * @author Samoy
 * @date 2019/10/17
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class SmsUtilsTest {

    @Resource
    private SmsUtils smsUtils;

    @Test
    public void sendSms() {
        String result = smsUtils.sendSms("123456");
        log.info("结果:{}", result);
    }
}
