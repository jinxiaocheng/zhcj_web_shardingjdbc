package com.escst.commons.utils;

/**
 * @author caozx
 * @desc
 * @date 2018/11/16 17:09
 */
public class DataFormatUtils {

    /**
     * @desc   编号增加1后转换成四位数字符串
     * @param
     * @return
     * @author caozx
     * @date 2018/11/16 15:49
     */
    public static String increase(String value) {
        Integer newNo = Integer.valueOf(value) + 1;
        String newOrderNo = String.format("%04d",newNo);
        return newOrderNo;
    }
}
