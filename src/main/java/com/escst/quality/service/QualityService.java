package com.escst.quality.service;

import com.escst.commons.excel.XssfExcelHelper;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.file.service.FileService;
import com.escst.inspection.vo.CheckItemsVO;
import com.escst.projectStructure.entity.ProjectStructureEntity;
import com.escst.projectStructure.entity.ProjectStructureExcelEntity;
import com.escst.quality.entity.InspectionExcelEntity;
import com.escst.quality.mapper.QualityMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jincheng
 * @desc 质量相关
 * @date 2018-7-3 14:09
 */
@Service
public class QualityService {

    private static final String EXCEL = "excel";

    @Autowired
    private QualityMapper qulityMapper;
    @Autowired
    private FileService fileService;


    /**
     * 检查部位数据导入
     *
     * @param file
     * @return
     */
    public String importDataFromExcel(MultipartFile file, String constructionId) {
        String result = "";
        try {
            if (file == null) {
                throw new EscstException("文件不存在！");
            }
            // 上传文件，并存到文件数据库表
//			FileEntity entity = fileService.uploadTempFile(EXCEL, file);
            // 文件的存放路径
//			String savePath = entity.getPath();
            String filePath = fileService.uploadTempFile(EXCEL, file);
//			filePath = ContextUtils.getSession().getServletContext().getRealPath("/") + File.separator + "resources" + File.separator
//					+ savePath;
            // 读excel文件
            XssfExcelHelper xssfExcelHelper = XssfExcelHelper.getInstance(filePath);
            List<InspectionExcelEntity> list = xssfExcelHelper.readExcel(InspectionExcelEntity.class, 0, true);
            result = checkImportDataFromExcel(list, constructionId);
        } catch (Exception e) {
            throw new EscstException("导入检查部位信息出现异常：" + e.getMessage(), e);
        }
        return result;
    }

    /**
     * 校验数据并存到数据库
     *
     * @param list
     * @return
     */
    @Transactional
    private String checkImportDataFromExcel(List<InspectionExcelEntity> list, String constructionId) {
        String result = "";
        List<CheckItemsVO> checkItemsVOList = new ArrayList<CheckItemsVO>();
        if (!CollectionUtils.isEmpty(list)) {
            String tempId = "";
            String twoId = "";
            String threeId = "";
            String fourId = "";
            String fiveId = "";
            String sexId = "";
            int sort = 0;
            for (InspectionExcelEntity excelEntity : list) {
                if (StringUtils.isBlank(excelEntity.getLevel()) || StringUtils.isBlank(excelEntity.getName())) {
                    continue;
                }
                sort += 10;
                CheckItemsVO entity = new CheckItemsVO();
                String id = UuidUtils.getUuid();
                entity.setId(id);
                // 如果为一级结构则设置父id为1
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "1")) {
                    entity.setParentId("0");
                    tempId = id;
                }
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "2")) {
                    entity.setParentId(tempId);
                    twoId = id;
                }
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "3")) {
                    entity.setParentId(twoId);
                    threeId = id;
                }
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "4")) {
                    entity.setParentId(threeId);
                    fourId = id;
                }
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "5")) {
                    entity.setParentId(fourId);
                    fiveId = id;
                }
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "6")) {
                    entity.setParentId(fiveId);
                    sexId = id;
                }
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "7")) {
                    entity.setParentId(sexId);
                }
                entity.setType(1);
                entity.setSortNum(sort);
                entity.setConstructionId(constructionId);
                entity.setName(excelEntity.getName());
                entity.setCreateBy(ContextUtils.getCurrentUserId());
                entity.setCreateTime(new Date());
                checkItemsVOList.add(entity);
            }

            // 批量新增检查部位数据
            if (!CollectionUtils.isEmpty(checkItemsVOList)) {
                qulityMapper.batchInsert(checkItemsVOList);
            }
        } else {
            result = "excel文件中没有数据!";
        }
        return result;
    }


}
