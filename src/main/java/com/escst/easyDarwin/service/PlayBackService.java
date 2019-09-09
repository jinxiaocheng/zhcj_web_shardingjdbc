package com.escst.easyDarwin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.escst.commons.exception.EscstException;
import com.escst.commons.vo.PageVo;
import com.escst.easyDarwin.entity.PlayBackEntity;
import com.escst.easyDarwin.mapper.PlayBackMapper;

/**
 * @desc
 * @author niejing
 * @date 2018年3月20日 下午3:49:59
 */
@Service
public class PlayBackService {

	@Autowired
	private PlayBackMapper mapper;

	/**
	 * 
	 * @desc
	 * @return
	 * @author niejing
	 * @date 2018年3月20日 下午3:57:43
	 * 
	 *       public List<PlayBackEntity> queryAll(){ List<PlayBackEntity> list =
	 *       new ArrayList<PlayBackEntity>(); try{ list = mapper.selectAll();
	 *       }catch(Exception e){ throw new EscstException("查询回放列表异常",e); }
	 *       return list; }
	 */

	public PageVo queryAll(PlayBackEntity entity) {
		PageVo pageVo = new PageVo();
		try {
			// 根据工地id查询人员总记录数
			int count = mapper.selectCount(entity);
			// 当前页号
			pageVo.setCurrentPage(entity.getPage());
			// 总记录数
			pageVo.setTotalRecord(count);

			pageVo.setPageSize(entity.getRowNum());
			// 每页的第一条记录的索引
			int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
			entity.setStartIndex(startIndex);
			List<PlayBackEntity> list = null;

			if (count > 0) {
				list = mapper.selectList(entity);
			}

			if (!CollectionUtils.isEmpty(list)) {
				pageVo.setRows(list);
			}
		} catch (Exception e) {
			throw new EscstException("查询人员列表信息失败！", e);
		}
		return pageVo;
	}
}
