package com.escst.commons.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.escst.commons.exception.EscstException;

/**
 * @desc md5 加密工具类
 * @Author wy
 * @createDate 2017/3/10 10:47
 */
public class MD5Util {

	private static Logger logger = LoggerFactory.getLogger(MD5Util.class);
	
    /**
     *  MD5加密 生成32位md5码
     * @param inStr 加密字符串
     * @return 返回32位md5码
     * @throws Exception 异常
     */
    public static String md5Encode(String inStr) {
        MessageDigest md5 ;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
        	logger.error("MessageDigest.getInstance 异常", e);
            return "";
        }

        byte[] byteArray;
		try {
			byteArray = inStr.getBytes("UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			throw new EscstException("字符串转换为byte数组时异常");
		}
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toLowerCase();//字母小写
    }
    public static void main(String [] args){
//        try {
//            String md5Str = md5Encode("zxw123456");
//            System.out.print(md5Str);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        String a = "2018-01-25 14:56:52";
        System.out.println(a.substring(0,10));
        System.out.println(a.substring(11,16));
    }

}
