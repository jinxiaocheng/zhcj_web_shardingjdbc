package com.escst.material.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.escst.commons.excel.XssfExcelHelper;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.file.service.FileService;
import com.escst.material.bean.MaterialBean;
import com.escst.material.entity.MaterialBudgetEntity;
import com.escst.material.entity.MaterialBudgetExcelEntity;
import com.escst.material.entity.MaterialModelEntity;
import com.escst.material.mapper.MaterialBudgetMapper;
import com.escst.material.mapper.MaterialMapper;
import com.escst.material.vo.MaterialVo;

/**
 * @desc
 * @author niejing
 * @date 2017年8月26日 下午4:35:18
 */
@Service
@Transactional
public class MaterialBudgetService {
	
	private static Logger logger = LoggerFactory.getLogger(MaterialBudgetService.class);
	
	private static final String EXCEL = "excel";

	@Autowired
	private FileService fileService;
	@Autowired
	private MaterialBudgetMapper mapper;

	@Autowired
	private MaterialService materialService;
	@Autowired
	private MaterialMapper materialMapper;
	
	
	public PageVo queryList(MaterialBean entity) {
		PageVo pageVo = new PageVo();
		try {
			// 查询材料预算总记录数
			int count = mapper.selectMapCount(entity);
			// 当前页
			pageVo.setCurrentPage(entity.getPage());
			// 总记录数
			pageVo.setTotalRecord(count);
			pageVo.setPageSize(entity.getRowNum());
			// 每页第一条记录的索引
			int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
			entity.setStartIndex(startIndex);
			// 查询数据
			List<Map<String, Object>> list = mapper.selectMapList(entity);
			if (!CollectionUtils.isEmpty(list)) {
				pageVo.setRows(list);
			}
		} catch (Exception e) {
			throw new EscstException("查询材料预算异常：" + e.getMessage(), e);
		}
		return pageVo;
	}

	public void save(MaterialBudgetEntity entity) {
		try {
			entity.setId(UuidUtils.getUuid());
			entity.setCreateTime(new Date());
			entity.setCreateBy(ContextUtils.getCurrentUserId());
			entity.setModelId(entity.getMaterialId());
			MaterialModelEntity materialModelEntity = materialMapper.selectModeById(entity.getMaterialId());
			entity.setMaterialId(materialModelEntity.getMaterialId());
			mapper.insert(entity);
		} catch (Exception e) {
			throw new EscstException("新增材料预算异常：" + e.getMessage(), e);
		}
	}

	public void update(MaterialBudgetEntity entity) {
		try {
			entity.setUpdateTime(new Date());
			entity.setUpdateBy(ContextUtils.getCurrentUserId());
			mapper.update(entity);
		} catch (Exception e) {
			throw new EscstException("新增材料预算异常：" + e.getMessage(), e);
		}
	}

	public Map<String, Object> queryBudgetById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = mapper.selectBudgetById(id);
		} catch (Exception e) {
			throw new EscstException("根据id查询材料预算异常：" + e.getMessage(), e);
		}
		return map;
	}
	
	public String importDataFromExcel(MultipartFile file, String constructionId) {
		String result = "";
		try {
			if (file == null) {
				throw new EscstException("文件不存在！");
			}
			// 文件的存放路径
			String filePath = fileService.uploadTempFile(EXCEL, file);
			// 读excel文件
			XssfExcelHelper xssfExcelHelper = XssfExcelHelper.getInstance(filePath);
			List<MaterialBudgetExcelEntity> list = xssfExcelHelper.readExcel(MaterialBudgetExcelEntity.class, 0, true);
			result = checkImportDataFromExcel(list,constructionId);

		} catch (Exception e) {
			throw new EscstException("导入材料预算出现异常：" + e.getMessage(), e);
		}
		return result;
	}

	private String checkImportDataFromExcel(List<MaterialBudgetExcelEntity> excelList, String constructionId) {
		String result = "";

		List<MaterialBudgetEntity> budgetList = new ArrayList<MaterialBudgetEntity>();
		if (!CollectionUtils.isEmpty(excelList)) {
			for (MaterialBudgetExcelEntity excelEntity : excelList) {
				MaterialBudgetEntity entity = new MaterialBudgetEntity();
				String materialId = getMaterialId(excelEntity);
				entity.setQuantity(excelEntity.getQuantity());
				entity.setAmount(excelEntity.getAmount());
				entity.setMaterialId(materialId);
				entity.setConstructionId("123464e35841e6f601584cd30ac10189");
				//更新不到数据就新增
				int count = mapper.updateByIds(entity);
				if(count!=0){
					continue;
				}
				entity.setId(UuidUtils.getUuid());
				entity.setCreateTime(new Date());
				entity.setCreateBy(ContextUtils.getCurrentUserId());

				budgetList.add(entity);

			}
			// 批量新增材料预算
			if (!CollectionUtils.isEmpty(budgetList)) {
				mapper.batchInsert(budgetList);
			}
		} else {
			result = "excel文件中没有数据!";
		}
		return result;
	}
	
	/**
	 * 
	 * @desc 如果查询到材料存在则返回该材料ID，否则新增材料并返回材料ID
	 * @param excelEntity
	 * @return 
	 * @author niejing
	 * @date 2017年12月12日 上午11:41:36
	 */
	private String getMaterialId(MaterialBudgetExcelEntity excelEntity){
		String materialId = "";
		materialId = materialService.checkName(excelEntity.getName(), excelEntity.getModelName(), excelEntity.getUnit());
		if(StringUtils.isNoneBlank(materialId)){
			return materialId;
		}
		try{
			MaterialVo vo = new MaterialVo();
			vo.setName(excelEntity.getName());
			vo.setModelName(excelEntity.getModelName());
			vo.setUnit(excelEntity.getUnit());
			materialId = materialService.add(vo);
		}catch(Exception e){
			logger.error("新增材料异常",e);
		}
		return materialId;
	}
	
}
