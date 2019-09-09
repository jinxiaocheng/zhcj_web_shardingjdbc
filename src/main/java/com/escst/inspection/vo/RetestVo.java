package com.escst.inspection.vo;

import java.util.List;

/**
 * @desc 
 * @author niejing
 * @date 2018年1月4日 上午11:34:14
 */
public class RetestVo {
	//创建时间
	private String createDate;
	//提交复检内容
	private List<String> reviewContent;
	//复查情况
	private String reviewResult;
	//备注
	private String remark;
	
	private String title;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public List<String> getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(List<String> reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getReviewResult() {
		return reviewResult;
	}
	public void setReviewResult(String reviewResult) {
		this.reviewResult = reviewResult;
	}
	
}
