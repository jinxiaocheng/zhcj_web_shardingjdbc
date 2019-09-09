package com.escst.commons.utils;

import java.io.*;

/**
 * @author caozx
 * @desc 序列化反序列化工具
 * @date 2017/9/29 13:13
 */
public class SerializeUtil {

    /**
     * @param obj
     * @return
     * @desc    序列化
     * @author caozx
     * @date 2017/9/29 13:15
     */
    public static byte[] serialize(Object obj) {

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            //序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);

            oos.writeObject(obj);
            byte[] byteArray = baos.toByteArray();
            return byteArray;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param bytes
     * @return
     * @desc 反序列化
     * @author caozx
     * @date 2017/9/29 13:15
     */
    public static Object unSerialize(byte[] bytes) {

        ByteArrayInputStream bais = null;
        try {
            //反序列化为对象
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
