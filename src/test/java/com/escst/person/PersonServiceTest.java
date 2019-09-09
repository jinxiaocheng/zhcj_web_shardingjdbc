package com.escst.person;

import com.escst.commons.utils.HttpClientUtils;
import com.escst.excelimport.service.PersonExcelImportSyncService;
import com.escst.excelimport.vo.UpdatePersonVo;
import com.escst.file.service.FileService;
import com.escst.person.entity.ConstructionPersonEntity;
import com.escst.person.mapper.PersonMapper;

import com.escst.person.vo.PersonSyncVo;
import com.escst.projectCompany.enums.CompanyTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.StringUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.file.entity.FileEntity;
import com.escst.file.mapper.FileMapper;
import com.escst.person.entity.PersonEntity;
import com.escst.person.service.PersonService;
import com.escst.person.vo.PersonQueryVO;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年11月3日 上午9:21:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class PersonServiceTest {

	@Autowired
	private PersonService service;
	
	@Test
	public void test01_queryConstructionPersonList(){
		PersonQueryVO personQueryVO = new PersonQueryVO();
		personQueryVO.setConstructionId("123464e35841e6f601584cd30ac10189");
		PageVo pageVo = service.queryConstructionPersonList(personQueryVO);
		System.out.println(JSONObject.toJSONString(pageVo));
	}
	
	@Test
	public void test02_getPersonById(){
		String personId = "905a1d8c65d34885aeeef940f4ae2d05";
		PersonEntity entity = service.getPersonById(personId);
		System.out.println(JSONObject.toJSONString(entity));
	}

	@Test
	public void test03_getPersonByIdCard(){
		PersonEntity entity = service.getPersonByIdCard();
		System.out.println(JSONObject.toJSONString(entity));
	}

	@Test
	public void listTeam() {
		String personCompanyId = "55a9882b3f234a6cbb842e5a2a5f8b2e";
		List<PersonEntity> listTeam = service.listTeam(personCompanyId);

	}
	
	@Autowired
	private FileMapper fileMapper;

	@Autowired
	private FileService fileService;

	@Autowired
	private PersonMapper personMapper;
	
	
	@Test
	public void test() {
		Map<String,String> map = new HashMap<String,String>();
		String path = "upload/person/201806";
		String constructionId = "67b63245bec94eb39a84121c116c960c";
		List<FileEntity> list = new ArrayList<FileEntity>();
    	//获取所有文件路径
    	File file = new File("E:/pic");
    	if (file.isDirectory()) {
    		String[] directories = file.list();
    		for (String direpath : directories) {
    			//也是目录，名称劳务一队、劳务二队
    			File f = new File("E:/pic/" + direpath);
    			//获取目录名称
    			String dirName = f.getName();
    			File[] fileAtr = f.listFiles();
    			for (File fi : fileAtr) {
    				String name = fi.getName();
    				String filepath = fi.getAbsolutePath();
    				filepath = filepath.replaceAll(dirName, convertDirName(dirName));
    				filepath = filepath.replaceAll("\\\\", "/");
    				filepath = filepath.replaceAll("E:/pic", path);
    				System.out.println(filepath);
    				
    				String id = UuidUtils.getUuid();
    				FileEntity entity = new FileEntity();
    				entity.setId(id);
    				entity.setName(name);
    				entity.setPath(filepath);
    				entity.setType(".jpg");
    				entity.setCreateTime(DateUtils.getCurrentDate());
    				list.add(entity);
    				String filePath = convertDirName(dirName) + "_" + name;
    				map.put(id, filePath);
    			}
    		}
    	}
    	
    	//批量添加到文件表
    	fileMapper.batchInsert(list);
    	//批量更新人员头像id
    	batchUpdatePerson(constructionId,map);
	}
	
	private void batchUpdatePerson(String constructionId,Map<String,String> map) {
		if (map.isEmpty()) {
			return;
		}
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Set<String> set = map.keySet();
		for (String fileId : set) {
			String filePath = map.get(fileId);
			String[] strArr = filePath.split("_");
			String dirName = strArr[0];
			String name = strArr[1].substring(0, strArr[1].indexOf("."));
			//通过工地id，公司和姓名查询人员信息
			String companyId = convertCompayId(dirName);
			PersonEntity entity = new PersonEntity();
			entity.setConstructionId(constructionId);
			entity.setProjectCompanyId(companyId);
			entity.setName(name);
			String personId = personMapper.selectPersonId(entity);
			if (StringUtils.isBlank(personId)) {
				continue;
			}
			Map<String,String> paramMap = new HashMap<String, String>();
			paramMap.put("personId", personId);
			paramMap.put("headFileId", fileId);
			list.add(paramMap);
		}
		
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		//批量更新头像字段
		personMapper.batchUpdateHeadId(list);
	}

	private String convertDirName(String dirName) {
		if ("中国化学工程第六建设有限公司".equals(dirName)) {
			return "zhlj";
		} else if ("劳务一队".equals(dirName)) {
			return "labor1";
		} else if ("劳务二队".equals(dirName)) {
			return "labor2";
		} else if ("劳务三队".equals(dirName)) {
			return "labor3";
		} else if ("劳务四队".equals(dirName)) {
			return "labor4";
		} else if ("劳务五队".equals(dirName)) {
			return "labor5";
		} else if ("劳务六队".equals(dirName)) {
			return "labor6";
		} else if ("劳务七队".equals(dirName)) {
			return "labor7";
		} else if ("鄂州市新航程基础设施建设有限公司".equals(dirName)) {
			return "owner";
		} else if ("北京东方华太建设监理有限公司".equals(dirName)) {
			return "supervisor";
		}
		return "";
	}
	
	private String convertCompayId(String dirName) {
		if ("zhlj".equals(dirName)) {
			return "1b43284f640911e8b0a0c81f66fba165";
		} else if ("labor1".equals(dirName)) {
			return "7c693dfce9d74039a944b41ff2ca022b";
		} else if ("labor2".equals(dirName)) {
			return "5570ee5711024125bce25be17ecfa77d";
		} else if ("labor3".equals(dirName)) {
			return "825f7a8f9a6b4b5b9825f6b40bafb4ea";
		} else if ("labor4".equals(dirName)) {
			return "1061600bae3c4d688ebcb9015c9e68ca";
		} else if ("labor5".equals(dirName)) {
			return "84489728aada4a2fbefaf8480216138e";
		} else if ("labor6".equals(dirName)) {
			return "3e0c7a32ea56430fb7a0f89b20944395";
		} else if ("labor7".equals(dirName)) {
			return "1c7037c9533411e8b0a0c81f66fba165";
		} else if ("owner".equals(dirName)) {
			return "ef8cf03713d24888ac1ef22dce5ec949";
		} else if ("supervisor".equals(dirName)) {
			return "456000a84c6b40f288e7459dbb69c279";
		}
		return "";
	}



}
