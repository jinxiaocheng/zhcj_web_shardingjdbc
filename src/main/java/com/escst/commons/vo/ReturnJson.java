package com.escst.commons.vo;

import java.io.Serializable;

/**
 * @desc   ajax返回
 * @author caozx
 * @date
 */
public class ReturnJson implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int STATUS_SUCESS = 1;   //成功
    private static final int STATUS_FAILURE = 0;  //失败
    public static final int STATUS_CARNO_ISEXIST = 1001;  //卡号已存在

    /**成功码说明*/
    public static final String  SUCCESS_MESSAGE = "成功";
    /**失败码说明*/
    public static final String  FAIL_MESSAGE = "失败";

    /**状态码**/
    private int status;
    /**消息**/
    private String msg;
    /**返回值**/
    private Object value;

    private int count;

    public ReturnJson(){

    }
    public ReturnJson(int status, String msg, Object value) {
        this.status = status;
        this.msg = msg;
        this.value = value;
    }

    /**
     * 创建一个成功标识的返回对象
     * @param object 返回的对象
     * @return
     */
    public static ReturnJson success(Object object) {
        ReturnJson returnJson = new ReturnJson();
        returnJson.setStatus(STATUS_SUCESS);
        returnJson.setMsg(SUCCESS_MESSAGE);
        returnJson.setValue(object);
        return returnJson;
    }

    /**
     * 创建一个成功标识的返回对象
     * @return
     */
    public static ReturnJson success() {
        ReturnJson returnJson = new ReturnJson();
        returnJson.setStatus(STATUS_SUCESS);
        returnJson.setMsg(SUCCESS_MESSAGE);
        return returnJson;
    }


    /**
     * 创建一个成功标识的返回对象
     * @param object 返回的对象
     * @return
     */
    public static ReturnJson success(Object object,int count) {
        ReturnJson returnJson = new ReturnJson();
        returnJson.setStatus(STATUS_SUCESS);
        returnJson.setMsg(SUCCESS_MESSAGE);
        returnJson.setValue(object);
        returnJson.setCount(count);
        return returnJson;
    }

    /**
     * 创建一个失败标识的返回对象
     * @param msg
     * @return
     */
    public static ReturnJson fail(String msg) {
        ReturnJson returnJson = new ReturnJson();
        returnJson.setStatus(STATUS_FAILURE);
        returnJson.setMsg(msg);
        return returnJson;
    }

    /**
     * 创建一个失败标识的返回对象
     * @param msg
     * @return
     */
    public static ReturnJson fail(String msg, int status) {
        ReturnJson returnJson = new ReturnJson();
        returnJson.setStatus(status);
        returnJson.setMsg(msg);
        return returnJson;
    }
    
    /**
     * 创建一个成功标识的返回对象 针对easyUI
     * @param object 返回的对象
     * @return
     */
    public static ReturnJson easyUI(Object object) {
        ReturnJson returnJson = new ReturnJson();
        returnJson.setValue(object);
        return returnJson;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
