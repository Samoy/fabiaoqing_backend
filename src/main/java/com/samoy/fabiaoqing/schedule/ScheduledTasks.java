package com.samoy.fabiaoqing.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Set;

/**
 * ScheduledTasks
 *
 * @author Samoy
 * @date 2019/9/4
 */
@Component
@Slf4j
public class ScheduledTasks {

    /**
     * 可清空的缓存键通配符
     */
    private static final String CLEARABLE_KEY = "clearable_*";
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 定时删除所有可清空的Redis缓存，每天凌晨三点执行
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void deleteClearableKeys() {
        log.warn("开始清空redis缓存:{}", redisTemplate.keys(CLEARABLE_KEY));
        Set<String> clearableKeys = redisTemplate.keys(CLEARABLE_KEY);
        redisTemplate.delete(Objects.requireNonNull(clearableKeys));
        log.warn("已清空redis缓存:{}", redisTemplate.keys(CLEARABLE_KEY));
    }
}
