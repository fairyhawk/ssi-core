package com.yizhilu.os.ssicore;

import com.yizhilu.os.ssicore.util.Security.PurseSecurityUtils;

/**
 * @ClassName  com.yizhilu.os.ssicore.A
 * @description
 * @author : qinggang.liu bis@foxmail.com
 * @Create Date : 2014-8-2 下午2:21:49
 */
public class A {
    public static void main(String[] args) {
        try {
            String customerKey = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//com.yizhilu.os.ssicore.util.StringUtils.getRandomString(60);
            String password = PurseSecurityUtils.secrect("password", customerKey);
            System.out.println("111:"+PurseSecurityUtils.decryption(password, customerKey));
            String pas = "uid:10987";
            String jiamihou =PurseSecurityUtils.encryptToHex(pas); //Des3Encryption.encryptToHex(publickey, pas);
            System.out.println("22:"+jiamihou);
            System.out.println(PurseSecurityUtils.decryptFromHex(jiamihou));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
