package com.escst.commons.utils;

import java.util.UUID;

/**
 * @author caozx
 * @desc uuid生成工具类
 * @date 2017-02-15
 */
public class UuidUtils {

    public static String getUuid(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
    }
}
