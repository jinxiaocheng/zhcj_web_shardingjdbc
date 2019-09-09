package com.escst.file.vo;

/**
 * @author caozx
 * @desc
 * @date 2017/8/10 14:03
 */
public class FilePathVO {

    private String id;

    /**文件路径**/
    private String filePath;

    /**文件缩微图路径**/
    private String smallFilePath;

    /**文件后缀名**/
    private String fileSuffix;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSmallFilePath() {
        return smallFilePath;
    }

    public void setSmallFilePath(String smallFilePath) {
        this.smallFilePath = smallFilePath;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
