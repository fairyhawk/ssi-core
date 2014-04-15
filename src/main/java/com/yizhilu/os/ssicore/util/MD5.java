package com.yizhilu.os.ssicore.util;

import java.security.MessageDigest;

/**
 * 
 * @ClassName com.supergenius.sns.util.MD5
 * @description MD5加密
 * @author : qinggang.liu 305050016@qq.com
 * @Create Date : 2013-12-13 下午2:20:50
 */
public class MD5 {
    public final static String getMD5(String s) {

        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
                'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}