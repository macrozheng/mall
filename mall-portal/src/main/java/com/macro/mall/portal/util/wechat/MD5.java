package com.macro.mall.portal.util.wechat;

import java.security.MessageDigest;

public class MD5 {
    private MD5() {
    }

    /*  * 生成 MD5
  *
          * @param data 待处理数据
  * @return MD5结果
  */
    public static String getMessageDigest(String data) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));

            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString().toUpperCase();
    }

}
