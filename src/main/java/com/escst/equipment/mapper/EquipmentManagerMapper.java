package com.escst.equipment.mapper;

import java.util.List;
import java.util.Map;

import com.escst.commons.vo.BaseAuthVO;
import org.springframework.stereotype.Repository;

import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.equipment.entity.EquipmentDisassemblyEntity;
import com.escst.equipment.entity.EquipmentInOutRecordEntity;
import com.escst.equipment.entity.EquipmentInspectionEntity;
import com.escst.equipment.entity.EquipmentMaintenanceEntity;
import com.escst.equipment.entity.EquipmentManagerEntity;
import com.escst.equipment.vo.QueryVO;
import com.escst.equipment.vo.SimpleEquipmentVO;

/**
 * @author dwj
 * @desc
 * @date 10:45 2017/3/13
 */
@Repository
public interface EquipmentManagerMapper  {

    /*通过工地ID查询所有设备信息* */
    List<Map<String,Object>> queryList(EquipmentManagerEntity equipmentManagerEntity);



    List<Map<String,Object>> queryByequipemntId(EquipmentManagerEntity equipmentManagerEntity);

    List<Map<String,Object>> queryMaintenanceByequipemntId(EquipmentManagerEntity equipmentManagerEntity);

    /**查询设备总信息*/
    int selectCount(EquipmentManagerEntity equipmentManagerEntity);


    /**查询没有维修设备总信息*/
    int selectCountByDisassembly(EquipmentManagerEntity equipmentManagerEntity);

    /**查询没有安装设备总信息*/
    int selectCountByMaintenance(EquipmentManagerEntity equipmentManagerEntity);

    /**查询设备详细信息*/
    Map<String, Object> queryListDtail(String equipmentId);



    /**通过工地ID查询所有设备进出场信息* */
    List<Map<String,Object>> queryEquipmentInOutRecordList(EquipmentInOutRecordEntity equipmentInOutRecordEntity);

    /**查询设备进出场总信息*/
    int selectEquipmentInOutRecordCount(EquipmentInOutRecordEntity equipmentInOutRecordEntity);

    /**查询设备进出场详细信息*/
    EquipmentInOutRecordEntity queryEquipmentInOutRecordDtail(String equipmentId);


    /**
     * 通过工地ID查询所有设备维修与保养信息
     * */
    List<Map<String,Object>> queryEquipmentMaintenanceList(EquipmentMaintenanceEntity equipmentMaintenanceEntity);

    /**查询设备维修与保养总信息*/
    int selectEquipmentMaintenanceCount(EquipmentMaintenanceEntity equipmentMaintenanceEntity);

    /**查询设备保养与维修详细信息*/
    EquipmentMaintenanceEntity querytEquipmentMaintenanceDtail(String equipmentId);


    /**
     * 查询所有设备拆卸信息
     * */
    List<Map<String,Object>> queryEquipmentDisassemblyList(EquipmentDisassemblyEntity equipmentDisassemblyEntity);

    /**查询设备拆卸总信息*/
    int selectEquipmentDisassemblyCount(EquipmentDisassemblyEntity equipmentDisassemblyEntity);

    /**查询设备拆卸详细信息*/
    EquipmentDisassemblyEntity querytEquipmentDisassemblyDtail(String equipmentId);

    /**查询设备所有巡查信息*/
    List<Map<String,Object>> queryEuipmentInspection(EquipmentInspectionEntity equipmentInspectionEntity);
    /**查询设备巡查总数*/
    int selectEquipmentInspection(EquipmentInspectionEntity equipmentInspectionEntity);
    /**设备巡查详细信息*/
    List<Map<String,Object>> queryEquipmentInspectionDatil(String equipmentId);
    /**
     * 添加工地设备信息
     * */
    public int insertRegister(EquipmentManagerEntity equipmentManagerEntity);

    /**添加设备进出场信息*/
    public int insetEuipmentInOutRecord(EquipmentInOutRecordEntity equipmentInOutRecordEntity);

    public void updateInOutRecord(EquipmentInOutRecordEntity equipmentInOutRecordEntity);

    /**修改设备信息*/
    public void updateEuipmentRegister(EquipmentManagerEntity equipmentManagerEntity);


    /**添加设备维修与保养信息*/
    public int insetEquipmentMaintenance(EquipmentMaintenanceEntity equipmentMaintenanceEntity);

    /**修改设备维修与保养信息*/
    public void updateEquipmentMaintenance(String equipmentId);


    /**添加设备拆卸信息*/
    public int insetEquipmentDisassembly(EquipmentDisassemblyEntity equipmentDisassemblyEntity);

    /**修改设备拆卸信息*/
    public void updateEquipmentDisassembly(String equipmentId);

    /**添加设备巡查信息*/
    public int  insertInspection(EquipmentInspectionEntity equipmentInspectionEntity);

    public List<String> selectAllDeviceSN(QueryVO vo);
    
    /**
     * 通过设备SN查询设备详细信息
     * @desc 
     * @param number
     * @return 
     * @author niejing
     * @date 2017年4月24日 下午5:29:03
     */
    public Map<String, Object> selectByNumber(String number);
    
    /**
	 * @desc 查询有权限查看的工地设备
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月16日 下午6:40:49
	 */
	List<SimpleEquipmentVO> queryAuthEquipmentList(QueryVO queryVO);
	
	/**
	 * @desc 查询有权限查看的工地设备总数
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月18日 下午3:44:03
	 */
	int getAuthEquipmentCount(QueryVO queryVO);
	
	/**
	 * @desc 查询有设备的工地
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年9月21日 上午8:44:44
	 */
	List<SimpleConstructionVO> queryAuthConstructionList(QueryVO queryVO);
	
	/**
	 * @desc 得到有设备的工地的总数
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年9月21日 上午10:54:50
	 */
	int getAuthConstructionCount(QueryVO queryVO);
	
	/**
	 * @desc 通过设备ID得到设备基本信息
	 * @param id
	 * @return 
	 * @author zhouwei
	 * @date 2017年9月21日 下午1:44:14
	 */
	List<SimpleEquipmentVO> querySpecialEquipmentList();
	
	List<EquipmentManagerEntity> querybyConstructionId(String constructionId);
}
