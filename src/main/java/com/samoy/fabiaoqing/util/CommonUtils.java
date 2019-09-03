package com.samoy.fabiaoqing.util;

import org.springframework.util.DigestUtils;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * CommonUtil
 *
 * @author Samoy
 * @date 2019/9/2
 */
public class CommonUtils {
    /**
     * 生成16位随机字符串
     *
     * @return 16位随机字符串
     */
    public static String randomObjectId() {
        UUID id = UUID.randomUUID();
        String[] idd = id.toString().split("-");
        return idd[0] + idd[1] + idd[2];
    }

    /**
     * 根据当前时间戳生成token
     *
     * @return token
     */
    public static String randomToken() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        byteBuffer.putLong(0, System.currentTimeMillis());
        return DigestUtils.md5DigestAsHex(byteBuffer.array());
    }
}
