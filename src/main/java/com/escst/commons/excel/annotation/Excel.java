package com.escst.commons.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author caozx
 * @desc
 * @date 2017/2/28 17:34
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {

    /**
     * 导出时，对应数据库的字段 主要是用户区分每个字段，不能有annocation重名的
     * 导出时的列名 导出排序跟定义了annotation的字段的顺序有关,可以使用a_id,b_id来确实是否使用
     * @return
     */
    public String exportName();


    /**
     * 导出时在excel中每个列的宽 单位为字符，一个汉字=2个字符
     * 如以列名列内容中较合适的长度 例如姓名列6 【姓名一般三个字】 性别列4【男女占1，但是列标题两个汉字】,限制1-255
     * @return
     */
    public int exportFieldWidth() default 10;

    /**
     * 导出时在excel中每个列的高度 单位为字符，一个汉字=2个字符
     * @return
     */
    public int exportFieldHeight() default 10;
}
