package com.escst.commons.vo;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年8月16日 下午6:55:35
 */
public class PageAuthVO extends BaseAuthVO {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -4178923047365376862L;

	/**页号,默认显示第一页**/
	private Integer page;
	
	/**默认每页显示10条数据**/
    private Integer rowNum;
    
    /**每页第一条记录的索引**/
    private int startIndex = 0;

	public Integer getPage() {
		if (page == null) {
			page = BigDecimal.ONE.intValue();
		}
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRowNum() {
		if (rowNum == null) {
			rowNum = BigDecimal.TEN.intValue();
		}
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public int getStartIndex() {
		if (page == null) {
			page = BigInteger.ONE.intValue();
		}
		if (rowNum == null) {
			rowNum = BigInteger.TEN.intValue();
		}
		startIndex = (page.intValue() - 1) * rowNum.intValue();
		return startIndex;
	}
}
