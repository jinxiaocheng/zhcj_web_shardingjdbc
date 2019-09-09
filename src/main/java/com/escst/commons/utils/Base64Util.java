package com.escst.commons.utils;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @author jincheng
 * @desc Base64转文件工具类
 * @date 2018/3/15 12:00
 */
public class Base64Util {

    /**
     * 将base64字符解码保存文件
     *
     * @param base64Code base64Code字符串
     * @param targetPath 图片存储路径
     * @throws Exception
     */
    public static void decoderBase64File(String base64Code, String targetPath)
            throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    /**
     * 创建文件夹
     *
     * @param path
     */
    public static void createFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 文件转base64
     */
    public static String fileToBase64(String path) throws Exception {
        File file = new File(path);
        if (file.exists()) {
            FileInputStream inputFile = new FileInputStream(file);
            byte[] b = new byte[(int) file.length()];
            inputFile.read(b);
            inputFile.close();
//        byte[] b = Files.readAllBytes(Paths.get(path));
            return new BASE64Encoder().encode(b);
        } else {
            return null;
        }
    }

    /**
     * 将网络图片转成Base64码，
     *
     * @param imgURL 图片地址。 例如：http://***.com/271025191524034.jpg
     * @return
     */
    public static String imgBase64(String imgURL) {
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        try {
            // 创建URL
            URL url = new URL(imgURL);
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "fail";//连接失败/链接失效/图片不存在
            }
            InputStream inStream = conn.getInputStream();
            int len = -1;
            while ((len = inStream.read(data)) != -1) {
                outPut.write(data, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outPut.toByteArray());
    }

    public static void main(String[] args) throws Exception {
        decoderBase64File(imgBase64("http://10.10.10.11:86/upload/material/201806/d366f297ab41474eadf32b796fc31054.png"), "F://2.jpg");
    }


}
