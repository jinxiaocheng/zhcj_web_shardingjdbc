package com.escst.commons.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

/**
 * 项目参数工具类
 */
public class ResourceUtil {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("properties/sysConfig");

    /**
     * 获得请求路径
     *
     * @param request
     * @return
     */
    public static String getRequestPath(HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        if (requestPath.indexOf("&") > -1) {// 去掉其他参数
            requestPath = requestPath.substring(0, requestPath.indexOf("&"));
        }
        requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
        return requestPath;
    }

    /**
     * 获取配置文件参数
     *
     * @param name
     * @return
     */
    public static final String getConfigByName(String name) {
        String value = "";
        try {
//			value = bundle.getString(name);
            value = new String(bundle.getString(name).getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * @return
     * @desc 获取文件上传请求路径
     * @author caozx
     * @date 2017/11/21 15:44
     */
    public static final String getFileUploadPath() {
        return bundle.getString("file_upload_url") + "uploadFile";
    }


    /**
     * @return
     * @desc 获取消息推送请求路径
     * @author niejing
     * @date 2017年11月28日 下午4:43:16
     */
    public static final String getPushServerUrl() {
        return bundle.getString("push_server_url");
    }

    /**
     * @return
     * @desc 获取消息推送和保存路径
     * @author niejing
     * @date 2017年11月30日 上午10:42:05
     */
    public static final String getPushSaveUrl() {
        return getPushServerUrl() + "save";
    }

    /**
     * @return
     * @desc 获取文件下载请求路径
     * @author caozx
     * @date 2017/11/21 15:44
     */
    public static final String getFileDownloadPath() {
        return getFileServerUrl() + "download";
    }

    public static final String getFileServerUrl() {
        return bundle.getString("file_server_url");
    }

    /**
     * @param
     * @return
     * @desc 获取文件下载请求路径
     * @author dwj
     * @date 2018/3/7 15:07
     */
    public static final String getFileDownloadPathNew() {
        return bundle.getString("file_download_url");
    }

    public static final String getVersion() {
        return bundle.getString("version");
    }

    public static final String gethxgtOrgId() {
        return bundle.getString("hxgtOrgId");
    }

    public static final String getFileSavePath() {
        return bundle.getString("file_save_path");
    }


    public static final String getRiskOperationSyncUrl() {
        return bundle.getString("risk_operation_sync_url");
    }

}
