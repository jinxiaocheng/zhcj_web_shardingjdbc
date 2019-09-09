package com.escst.highformwork.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.vo.PageVo;
import com.escst.file.service.FileService;
import com.escst.highformwork.entity.HighformworkExcelEntity;
import com.escst.highformwork.mapper.HighformworkWarningMapper;
import com.escst.highformwork.vo.HighformworkFlowVo;
import com.escst.highformwork.vo.HighformworkRealTimeVo;
import com.escst.territory.service.TerritoryService;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jincheng
 * @desc 高支模预警，历史数据相关
 * @date 2018-7-18 16:53
 */
@Service
public class HighformworkWarningService {

    private static final int WARNING = 2;
    private static final int ALARM = 3;
    private static final int CONTROL = 4;

    @Autowired
    private HighformworkWarningMapper highformworkWarningMapper;
    @Autowired
    private FileService fileService;
    @Autowired
    private TerritoryService territoryService;


    /**
     * @param highformworkRealTimeVo
     * @return
     * @desc 根据流水段，获取其中的采集仪
     * @author jincheng
     * @date 2018-7-18 14:28
     */
    public List<HighformworkRealTimeVo> listMeasurePoint(HighformworkRealTimeVo highformworkRealTimeVo) {
        try {
            List<HighformworkRealTimeVo> list = highformworkWarningMapper.listMeasurePoint(highformworkRealTimeVo);
            return list;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "根据流水段，获取其中的测点异常");
        }
    }


    /**
     * @param highformworkRealTimeVo
     * @return
     * @desc 查询测量中的预警/历史数据
     * @author jincheng
     * @date 2018-7-18 15:35
     */
    public PageVo listData(HighformworkRealTimeVo highformworkRealTimeVo) {
        PageVo pageVo = new PageVo();
        List<HighformworkRealTimeVo> voList = new ArrayList<HighformworkRealTimeVo>();
        try {
            int dataCount = highformworkWarningMapper.getDataCount(highformworkRealTimeVo);
            highformworkRealTimeVo.setOffset((highformworkRealTimeVo.getPage() - 1) * highformworkRealTimeVo.getRowNum());
            if (dataCount == 0) {
                return pageVo;
            }
            // 查询测点数据
            voList = highformworkWarningMapper.listData(highformworkRealTimeVo);
            pageVo.setRows(voList);
            pageVo.setTotalRecord(dataCount);
            return pageVo;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询测量中的预警数据异常");
        }
    }


    /**
     * @param type
     * @param flowId
     * @return
     * @desc 查询提醒详情
     * @author niejing
     * @date 2018年7月20日 上午9:02:13
     */
    public List<HighformworkRealTimeVo> alarmDetail(int type, String flowId) {
        List<HighformworkRealTimeVo> list = new ArrayList<HighformworkRealTimeVo>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);
        map.put("flowId", flowId);
        try {
            list = highformworkWarningMapper.selectAlarmDetail(map);
            for (HighformworkRealTimeVo entity : list) {
                String path = fileService.queryFilePathById(entity.getFileId());
                entity.setPath(path);
            }
        } catch (Exception e) {
            throw new EscstException("查询告警详情异常", e);
        }
        return list;
    }


    /**
     * @param vo
     * @return
     * @desc 获取流水段
     * @author jincheng
     * @date 2018-8-2 9:04
     */
    public List<HighformworkFlowVo> listFlow(HighformworkRealTimeVo vo) {
        List<HighformworkFlowVo> list = new ArrayList<HighformworkFlowVo>();
        try {
            list = highformworkWarningMapper.listFlow(vo);
            return list;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "获取流水段异常");
        }
    }


    /**
     * @desc 获取流水段树
     * @author jincheng
     * @date 2018-7-18 14:28
     */
    public List<TreeEntity> listFlowTree(String userId) {
        List<TreeEntity> treeList = new ArrayList<TreeEntity>();
        // 查询出区域工地树
        List<TreeEntity> list = territoryService.queryAreaTreeByUserId(userId);
        for (TreeEntity entity : list) {
            TreeEntity treeEntity = new TreeEntity();
            treeEntity.setId(entity.getId());
            treeEntity.setpId(entity.getpId());
            treeEntity.setName(entity.getName());
            treeEntity.setOpen(true);
            treeList.add(treeEntity);
            if (entity.getId().length() > 5) {
                // 工地下的流水段
                List<HighformworkFlowVo> flowVoList = highformworkWarningMapper.listFlowTree(entity.getId());
                for (HighformworkFlowVo highformworkFlowVo : flowVoList) {
                    TreeEntity tree = new TreeEntity();
                    tree.setId(highformworkFlowVo.getFlowId());
                    tree.setName(highformworkFlowVo.getFlowName());
                    tree.setOpen(false);
                    tree.setpId(entity.getId());
                    treeList.add(tree);
                }
            }
        }
        return treeList;
    }

    /**
     * @desc 导出excel
     * @author jincheng
     * @date 2018/10/10 15:35
     */
    public Workbook exportExcel(HighformworkRealTimeVo highformworkRealTimeVo) {
        ExportParams params = new ExportParams();
        List<HighformworkRealTimeVo> list = (List<HighformworkRealTimeVo>) this.listData(highformworkRealTimeVo).getRows();
        List<HighformworkExcelEntity> voList = new ArrayList<HighformworkExcelEntity>();
        if (CollectionUtils.isEmpty(list)) {
            HighformworkExcelEntity excelEntity = new HighformworkExcelEntity();
            excelEntity.setEquipmentName("无数据");
            excelEntity.setNumber("无数据");
            excelEntity.setCreateTime("无数据");
            excelEntity.setxAngle("无数据");
            excelEntity.setyAngle("无数据");
            excelEntity.setKap("无数据");
            excelEntity.setDisplace("无数据");
            excelEntity.setElectric("无数据");
            excelEntity.setTemperature("无数据");
            voList.add(excelEntity);
        } else {
            for (HighformworkRealTimeVo vo : list) {
                HighformworkExcelEntity excelEntity = new HighformworkExcelEntity();
                excelEntity.setEquipmentName(vo.getEquipmentName());
                excelEntity.setNumber(vo.getNumber());
                excelEntity.setCreateTime(vo.getCreateTime());
                excelEntity.setxAngle(String.valueOf(vo.getxAngle()));
                excelEntity.setyAngle(String.valueOf(vo.getyAngle()));
                excelEntity.setKap(String.valueOf(vo.getKpa()));
                excelEntity.setDisplace(String.valueOf(vo.getDisplace()));
                excelEntity.setElectric(String.valueOf(vo.getElectric()));
                excelEntity.setTemperature(String.valueOf(vo.getTemperature()));
                voList.add(excelEntity);
            }
        }
        Workbook workbook = ExcelExportUtil.exportExcel(params, HighformworkExcelEntity.class, voList);
        return workbook;
    }

    /**
     * @desc 查询流水段的开始、结束时间
     * @author jincheng
     * @date 2018/10/10 15:35
     */
    public HighformworkFlowVo getTime(String flowId) {
        HighformworkFlowVo flowVo = new HighformworkFlowVo();
        try {
            flowVo = highformworkWarningMapper.getTime(flowId);
            return flowVo;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询流水段的开始、结束时间异常", e);
        }
    }
}
