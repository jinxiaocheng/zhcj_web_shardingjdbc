package com.escst.structure;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.construction.entity.ProjectScheduleEntity;
import com.escst.construction.entity.ScheduledPlanEntity;
import com.escst.construction.service.ScheduledPlanService;
import com.escst.construction.vo.ScheduledPlanTree;
import com.escst.projectStructure.entity.ProjectStructureEntity;
import com.escst.projectStructure.service.ProjectStructureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * @author dwj
 * @desc
 * @date 9:21 2018/1/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class ProjectStructureServiceTest {

    @Autowired
    private ProjectStructureService projectStructureService;

    @Autowired
    private ScheduledPlanService scheduledPlanService;


    @Test
    public void test01_queryStructureByConstructionId(){
        String constructionId ="89735769b4a111e7b955c81f66fba166";
        List<TreeEntity> list =  projectStructureService.queryStructureByConstructionId(constructionId);
        System.out.println(JSONObject.toJSONString(list));

    }

    @Test
    public void test02_saveStructure(){

        for(int i =1 ;i<4;i++){
            ProjectStructureEntity entity = new ProjectStructureEntity();
            String  name = Integer.valueOf(i)+"层";
            entity.setParentId("acce70fd788947968b79619993813142");
            entity.setConstructionId("17472c0a727e4abe8f5a1a2e43b45c80");
            entity.setSortNum(i-1);
            entity.setName(name);
            projectStructureService.addProjectStructure(entity);

        }


    }


    @Test
    public void test03_save(){

        String parentId = "3fb1b9fb588947d2865b3b38da1006b1";
        String id = "acce70fd788947968b79619993813142";
        String constructionId = "17472c0a727e4abe8f5a1a2e43b45c80";
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("constructionId",constructionId);
        dataMap.put("parentId",parentId);
        List<String> list = projectStructureService.queryByName(dataMap);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("parentId",id);
        map.put("sortStart",0);
        map.put("sortEnd",3);
        List<ProjectStructureEntity> entities = projectStructureService.queryBySortNum(map);
        for(ProjectStructureEntity entity : entities ){
            for(String s : list){
                ProjectStructureEntity projectStructureEntity = new ProjectStructureEntity();
                projectStructureEntity.setName(s);
                projectStructureEntity.setParentId(entity.getId());
                projectStructureEntity.setConstructionId("17472c0a727e4abe8f5a1a2e43b45c80");
                projectStructureService.addProjectStructure(projectStructureEntity);
            }

        }
    }


    @Test
    public void test04_save(){
        String id = "acce70fd788947968b79619993813142";
        String constructionId ="17472c0a727e4abe8f5a1a2e43b45c80";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("parentId",id);
        map.put("constructionId",constructionId);
        map.put("sortStart",0);
        map.put("sortEnd",3);
        List<ProjectStructureEntity> entities = projectStructureService.queryBySortNum(map);
        for(ProjectStructureEntity entity : entities){
            String a = entity.getId();
            Map<String,Object> dataMap = new HashMap<String,Object>();
            dataMap.put("parentId",a);
            List<ProjectStructureEntity> list = projectStructureService.queryBySortNum(dataMap);
            for(ProjectStructureEntity entity1 : list){
                String name = entity1.getName();
                if(name.equals("建筑节能")){
                    ProjectStructureEntity projectStructureEntity = new ProjectStructureEntity();
                    projectStructureEntity.setParentId(entity1.getId());
                    projectStructureEntity.setConstructionId("17472c0a727e4abe8f5a1a2e43b45c80");
                    projectStructureEntity.setName("围护系统节能");
                    projectStructureService.addProjectStructure(projectStructureEntity);
                }else if (name.equals("建筑电气")){
                     List<String> names = new ArrayList<String>();
                     names.add("防雷及接地");
                     names.add("电气照明");
                for(String e:names){
                    ProjectStructureEntity projectStructureEntity = new ProjectStructureEntity();
                    projectStructureEntity.setParentId(entity1.getId());
                    projectStructureEntity.setConstructionId("17472c0a727e4abe8f5a1a2e43b45c80");
                    projectStructureEntity.setName(e);
                    projectStructureService.addProjectStructure(projectStructureEntity);
                }

                }else if(name.equals("通风与空调")){
                    ProjectStructureEntity projectStructureEntity = new ProjectStructureEntity();
                    projectStructureEntity.setParentId(entity1.getId());
                    projectStructureEntity.setConstructionId("17472c0a727e4abe8f5a1a2e43b45c80");
                    projectStructureEntity.setName("排风系统");
                    projectStructureService.addProjectStructure(projectStructureEntity);
                }else if (name.equals("建筑给水排水及供暖")){
                    List<String> names = new ArrayList<String>();
                    names.add("建筑中水系统及雨水利用系统");
                    names.add("建筑饮用水供应系统");
                    names.add("室外排水管网");
                    names.add("室外给水管网");
                    names.add("卫生器具");
                    names.add("室内排水系统");
                    names.add("室内给水系统");
                    for(String e:names){
                        ProjectStructureEntity projectStructureEntity = new ProjectStructureEntity();
                        projectStructureEntity.setParentId(entity1.getId());
                        projectStructureEntity.setConstructionId("17472c0a727e4abe8f5a1a2e43b45c80");
                        projectStructureEntity.setName(e);
                        projectStructureService.addProjectStructure(projectStructureEntity);
                    }

                }else if (name.equals("建筑装饰装修")){
                    List<String> names = new ArrayList<String>();
                    names.add("细部");
                    names.add("涂饰");
                    names.add("饰面砖");
                    names.add("门窗");
                    names.add("外墙防水");
                    names.add("抹灰");
                    names.add("建筑地面");
                    for(String e:names){
                        ProjectStructureEntity projectStructureEntity = new ProjectStructureEntity();
                        projectStructureEntity.setParentId(entity1.getId());
                        projectStructureEntity.setConstructionId("17472c0a727e4abe8f5a1a2e43b45c80");
                        projectStructureEntity.setName(e);
                        projectStructureService.addProjectStructure(projectStructureEntity);
                    }
                }else if (name.equals("主体工程")){
                    List<String> names = new ArrayList<String>();
                    names.add("砌体结构");
                    names.add("混凝土机构");
                    for(String e:names){
                        ProjectStructureEntity projectStructureEntity = new ProjectStructureEntity();
                        projectStructureEntity.setParentId(entity1.getId());
                        projectStructureEntity.setConstructionId("17472c0a727e4abe8f5a1a2e43b45c80");
                        projectStructureEntity.setName(e);
                        projectStructureService.addProjectStructure(projectStructureEntity);
                    }
                }
                }

            }
        }


}
