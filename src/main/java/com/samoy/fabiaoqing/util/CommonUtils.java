package com.samoy.fabiaoqing.util;

import org.springframework.util.DigestUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

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

    /**
     * 随机生成用户名
     *
     * @return 用户名后缀
     */
    public static String randomUserId() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(new Random().nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否是纯数字
     *
     * @param source 待检测的字符串
     * @return 是否是纯数字
     */
    public static Boolean isNumericString(String source) {
        return Pattern.matches("^(\\d)+$", source);
    }

    /**
     * 判断输入流是否是图片
     *
     * @param inputStream 输入流
     * @return 布尔值
     */
    public static boolean isImage(InputStream inputStream) {
        if (inputStream == null) {
            return false;
        }
        Image img;
        try {
            img = ImageIO.read(inputStream);
            return !(img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0);
        } catch (Exception e) {
            return false;
        }
    }
}
