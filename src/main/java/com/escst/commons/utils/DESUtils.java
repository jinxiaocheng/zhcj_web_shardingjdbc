package com.escst.commons.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @author dwj
 * @desc
 * @date 15:01 2018/5/7
 */
public class DESUtils {


    private static Key key;
    private static String KEY_STR="escst@123";

    static{
        try
        {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEY_STR.getBytes());
            generator.init(secureRandom);
            key = generator.generateKey();
            generator=null;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对字符串进行加密，返回BASE64的加密字符串
     * <功能详细描述>
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getEncryptString(String str){
        BASE64Encoder base64Encoder = new BASE64Encoder();
        System.out.println(key);
        try
        {
            byte[] strBytes = str.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            return base64Encoder.encode(encryptStrBytes);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    /**
     * 对BASE64加密字符串进行解密
     * <功能详细描述>
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getDecryptString(String str){
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try
        {
            byte[] strBytes = base64Decoder.decodeBuffer(str);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            return new String(encryptStrBytes,"UTF-8");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }


    public static void main(String[] args)
    {
//        String name ="zhcj_user";
//        String password="escst@123";
        String url ="root";

//        int redisport = 6379;
//        String redisurl1 = "192.168.1.11";
//        String redisurl2 = "115.182.112.8";
//        String redisurl3= "192.168.10.97";
//        String redispassword = "escst@123";

//        String encryname = getEncryptString(name);
//        String encrypassword = getEncryptString(password);
        String encryUrl = getEncryptString(url);
//        String encryredisurl1 = getEncryptString(redisurl1);
//        String encryredisurl2 = getEncryptString(redisurl2);
//        String encryredisurl3 = getEncryptString(redisurl3);
//        String encryredispassword =getEncryptString(redispassword);
//        System.out.println("name>>>>>>>>>>>"+encryname);
//        System.out.println("password>>>>>>>>>>>>"+encrypassword);
        System.out.println("url>>>>>>>>>>"+encryUrl);
//        System.out.println(encryredisurl1);
//        System.out.println(encryredisurl2);
//        System.out.println(encryredisurl3);
//        System.out.println(encryredispassword);
//        System.out.println(getEncryptString("jdbc:mysql://115.182.112.8:3307/zhcj?useUnicode=true&characterEncoding=UTF-8"));


    }

}
