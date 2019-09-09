package com.escst.news.entity;


import org.springframework.web.multipart.MultipartFile;

import com.escst.commons.entity.BaseEntity;

/**
 * 
 * @desc 
 * @author niejing
 * @date 2017年7月10日 下午1:36:41
 */
public class NewsEntity extends BaseEntity{
    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 5022174338380272905L;
	private String id;
    /**标题*/
    private String title;
    /**内容*/
    private String content;
    /**图标ID*/
    private String fileId;
    /**新闻类型：1:城乡建设，2：海绵城市，3：综合管廊，4：BIM*/
    private Integer type;
    /**新闻链接*/
    private String link;

    private MultipartFile multipartFile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
