package com.escst.commons.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @author dwj
 * @desc
 * @date 11:21 2018/5/28
 */
public class Base64Img {

    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }



    /**
    * @desc 将base64编码字符串转换为图片
    * @param imgStr path
    * @return
    * @author dwj
    * @date 2018/5/28 11:25
    */
    public static String generateImage(String imgStr, String path){
        if (StringUtils.isBlank(imgStr)) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return path;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String strImg = getImageStr("F:/tupian/123.png");
        System.out.println(strImg);
        String path = generateImage(strImg, "F:/tupian/456.png");
        System.out.println(path);
    }

}
