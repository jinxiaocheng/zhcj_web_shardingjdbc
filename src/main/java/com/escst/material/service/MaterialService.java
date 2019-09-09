package com.escst.material.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.escst.commons.excel.XssfExcelHelper;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.PinyinUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.file.service.FileService;
import com.escst.material.entity.MaterialEntity;
import com.escst.material.entity.MaterialExcelEntity;
import com.escst.material.entity.MaterialModelEntity;
import com.escst.material.mapper.MaterialMapper;
import com.escst.material.vo.MaterialVo;

/**
 * @desc
 * @author niejing
 * @date 2017年8月31日 上午10:16:30
 */
@Service
@Transactional
public class MaterialService {
	private static final String EXCEL = "excel";

	@Autowired
	private FileService fileService;
	@Autowired
	public MaterialMapper materialMapper;

	public List<MaterialEntity> list() {
		return materialMapper.selectAll();
	}

	public PageVo queryMaterial(MaterialEntity entity) {
		PageVo pageVo = new PageVo();
		try {
			// 查询材料进场总记录数
			int count = materialMapper.selectMaterialCount(entity);
			// 当前页
			pageVo.setCurrentPage(entity.getPage());
			// 总记录数
			pageVo.setTotalRecord(count);
			pageVo.setPageSize(entity.getRowNum());
			// 每页第一条记录的索引
			int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
			entity.setStartIndex(startIndex);
			// 查询数据
			List<Map<String, Object>> list = materialMapper.selectMaterial(entity);
			if (!CollectionUtils.isEmpty(list)) {
				pageVo.setRows(list);
			}
		} catch (Exception e) {
			throw new EscstException("查询材料进场数据异常：" + e.getMessage(), e);
		}
		return pageVo;
	}

	public String add(MaterialVo vo) {
		String materialId = "";
		try {
			//判断材料名称型号单位是否唯一
			String id = this.checkName(vo.getName(), vo.getModelName(), vo.getUnit());
			if (StringUtils.isNotBlank(id)) {
				throw new EscstException("该材料下面存在该型号");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", vo.getName());
			map.put("unit", vo.getUnit());
			//判断材料名称单位是否唯一
			List<MaterialEntity> list = materialMapper.selectByCondition(map);
			if (CollectionUtils.isEmpty(list)) {
				MaterialEntity entity = new MaterialEntity();
				materialId = UuidUtils.getUuid();
				entity.setId(materialId);
				entity.setName(vo.getName());
				entity.setCode(PinyinUtils.getPinYinHeadChar(vo.getName()));
				entity.setUnit(vo.getUnit());
				entity.setCreateTime(new Date());
				entity.setCreateBy(ContextUtils.getCurrentUserId());
				materialMapper.insert(entity);
			} else {
				MaterialEntity entity = list.get(0);
				materialId = entity.getId();
			}
			MaterialModelEntity modelEntity = new MaterialModelEntity();
			modelEntity.setId(UuidUtils.getUuid());
			modelEntity.setMaterialId(materialId);
			modelEntity.setName(vo.getModelName());
			modelEntity.setCreateTime(new Date());
			modelEntity.setCreateBy(ContextUtils.getCurrentUserId());
			materialMapper.insertMaterialModel(modelEntity);

		} catch (EscstException e) {
			throw e;
		} catch (Exception e1) {
			throw new EscstException("保存材料异常", e1);
		}
		return materialId;
	}

	/**
	 * 
	 * @desc 校验材料是否存在 
	 * @param name
	 * @param modelName
	 * @param unit
	 * @return 
	 * @author niejing
	 * @date 2017年12月12日 上午10:44:54
	 */
	public String checkName(String name, String modelName, String unit) {
		String id = "";
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			map.put("modelName", modelName);
			map.put("unit", unit);
			id = materialMapper.selectByMap(map);
		} catch (Exception e) {
			throw new EscstException("根据材料名查询材料异常", e);
		}
		return id;
	}

	public List<Map<String, Object>> queryAllMaterial() {
		return materialMapper.selectMaterial(new MaterialEntity());
	}

	public String importDataFromExcel(MultipartFile file) {
		String result = "";
		try {
			if (file == null) {
				throw new EscstException("文件不存在！");
			}
			// 文件的存放路径
			String filePath = fileService.uploadTempFile(EXCEL, file);
			// 读excel文件
			XssfExcelHelper xssfExcelHelper = XssfExcelHelper.getInstance(filePath);
			List<MaterialExcelEntity> list = xssfExcelHelper.readExcel(MaterialExcelEntity.class, 0, true);
			result = checkImportDataFromExcel(list);

		} catch (Exception e) {
			throw new EscstException("导入材料出现异常：" + e.getMessage(), e);
		}
		return result;
	}

	private String checkImportDataFromExcel(List<MaterialExcelEntity> excelList) {
		String result = "";

		List<MaterialEntity> materiallist = new ArrayList<MaterialEntity>();
		List<MaterialModelEntity> modellist = new ArrayList<MaterialModelEntity>();
		if (!CollectionUtils.isEmpty(excelList)) {
			for (MaterialExcelEntity excelEntity : excelList) {
				//校验材料是否存在
				String id = this.checkName(excelEntity.getName(), excelEntity.getModelName(), excelEntity.getUnit());
				if (StringUtils.isNotBlank(id)) {
					// "该材料下面存在该材料型号"
					continue;
				}
				String materialId = "";
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", excelEntity.getName());
				map.put("unit", excelEntity.getUnit());
				List<MaterialEntity> list = materialMapper.selectByCondition(map);
				MaterialEntity entity = new MaterialEntity();
				if (CollectionUtils.isEmpty(list)) {
					materialId = UuidUtils.getUuid();
					entity.setId(materialId);
					entity.setName(excelEntity.getName());
					entity.setCode(PinyinUtils.getPinYinHeadChar(excelEntity.getName()));
					entity.setUnit(excelEntity.getUnit());
					entity.setCreateTime(new Date());
					entity.setCreateBy(ContextUtils.getCurrentUserId());
					//添加材料列表
					materiallist.add(entity);
				} else {
					entity = list.get(0);
					materialId = entity.getId();
				}
				MaterialModelEntity modelEntity = new MaterialModelEntity();
				modelEntity.setId(UuidUtils.getUuid());
				modelEntity.setMaterialId(materialId);
				modelEntity.setName(excelEntity.getModelName());
				modelEntity.setCreateTime(new Date());
				modelEntity.setCreateBy(ContextUtils.getCurrentUserId());
				//添加材料型号列表
				modellist.add(modelEntity);

			}
			// 批量新增材料
			if (!CollectionUtils.isEmpty(materiallist)) {
				materialMapper.batchInsert(materiallist);
			}
			// 批量新增材料型号
			if (!CollectionUtils.isEmpty(modellist)) {
				materialMapper.batchInsertModel(modellist);
			}
		} else {
			result = "excel文件中没有数据!";
		}
		return result;
	}
	
	/**
	 * @desc  查询材料基础信息
	 * @param entity
	 * @return 
	 * @author zhouwei
	 * @date 2017年12月29日 上午11:07:10
	 */
	public PageVo queryBaseMaterialList(MaterialEntity entity) {
		PageVo pageVo = new PageVo();
		// 当前页
		pageVo.setCurrentPage(entity.getPage());
		pageVo.setPageSize(entity.getRowNum());
		// 每页第一条记录的索引
		int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
		entity.setStartIndex(startIndex);

		// 查询材料进场总记录数
		int count = materialMapper.queryBaseMaterialCount(entity);
		// 总记录数
		pageVo.setTotalRecord(count);
		if (count > 0) {
			// 查询数据
			List<Map<String, Object>> list = materialMapper.queryBaseMaterialList(entity);
			if (!CollectionUtils.isEmpty(list)) {
				pageVo.setRows(list);
			}
		}
		return pageVo;
	}
}
