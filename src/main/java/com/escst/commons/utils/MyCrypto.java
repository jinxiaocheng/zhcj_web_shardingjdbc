package com.escst.commons.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * md5加密工具类
 * Created by caozx on 2017/2/13.
 */
public class MyCrypto {

    static byte[] vi1Arry = { 0x59, 0x62, 0x7A, 0x52, 0x44, 0x75, 0x6C, 0x34, 0x3F, 0x77, 0x6D, 0x6B, 0x18, 0x25, 0x34, 0x40, 0x56, 0x23, 0x69, 0x62,
            0x6F, 0x49, 0x24, 0x6C, 0x38, 0x23, 0x4D, 0x47, 0x36, 0x68, 0x59, 0x37 };
    static byte[] vi2Arry = { 0x6D, 0x26, 0x6C, 0x47, 0x52, 0x5A, 0x2A, 0x47, 0x4D, 0x6C, 0x4E, 0x51, 0x3B, 0x52, 0x6C, 0x3B, 0x38, 0x59, 0x2E, 0x6D,
            0x6C, 0x07, 0x44, 0x4B, 0x3B, 0x4E, 0x61, 0x5A, 0x58, 0x73, 0x55, 0x7A };

    public static void main(String[] args) {
        System.out.println(new MyCrypto().md5Crypto("123456"));
        System.out.println(MyCrypto.getInstance().md5Crypto("admin123456"));
    }

    private static MyCrypto crypto = null;

    public static MyCrypto getInstance() {
        if (null == crypto) {
            crypto = new MyCrypto();
        }
        return crypto;
    }

    private static String md5(String inStr) {
        MessageDigest md5 = null;
        byte[] byteArray = null;
        byte[] md5Bytes = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        try {
            byteArray = inStr.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xFF;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    private static String Byte2hexString(byte[] b) {
        String ret = "";

        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex;
        }
        return ret;
    }

    public String md5Crypto(String inStr) {
        String md5str = null;
        md5str = md5(inStr);

        byte[] Array1 = md5str.getBytes();
        // Log.e(TAG, "1->"+inStr+"->"+md5str+" "+Array1[0]);
        for (int i = 0; i < Array1.length; i++) {
            Array1[i] = (byte) (Array1[i] + vi1Arry[i]);
        }
        String recString = Byte2hexString(Array1);

        md5str = md5(recString);
        // Log.e(TAG, "2->"+recString+"->"+md5str);
        byte[] Array2 = md5str.getBytes();

        for (int i = 0; i < Array2.length; i++) {
            Array2[i] = (byte) (Array2[i] + vi2Arry[i]);
        }
        String md5ret = Byte2hexString(Array2);

        md5str = md5(md5ret);
        // Log.e(TAG, "ret2->"+md5str);
        return md5str;
    }
}
