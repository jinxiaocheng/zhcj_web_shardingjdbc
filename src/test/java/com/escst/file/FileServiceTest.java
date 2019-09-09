package com.escst.file;

import com.alibaba.fastjson.JSONObject;
import com.escst.file.entity.FileEntity;
import com.escst.file.mapper.FileMapper;
import com.escst.file.service.FileService;
import com.escst.file.vo.FilePathVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年8月24日 下午2:19:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", 
									"classpath:/spring/spring-mvc.xml" })
public class FileServiceTest {

	@Autowired
	private FileService service;
	
	@Autowired
	private FileMapper fileMapper;

	@Test
	public void test01_getPicturePathMapByBusinessIds() {
		List<String> params = new ArrayList<String>();
		params.add("7ab5bf57fb34439da23cfb81be7927f1");
		params.add("0444f36697384cf09e0ea86a55e10240");
		Map<String, List<FilePathVO>> map = service.getPicturePathMapByBusinessIds(params);
		System.out.println(JSONObject.toJSONString(map));
	}
	
	@Test
	public void test02_queryFilePath() {
		String id = "7ab5bf57fb34439da23cfb81be7927f1";
		List<FilePathVO> list = service.queryFilePath(id);
		System.out.println(JSONObject.toJSONString(list));
	}
	@Test
	public void test03_queryFilePathByBusinessId() {
		String id = "7ab5bf57fb34439da23cfb81be7927f1";
		List<String> list = service.queryFilePathByBusinessId(id);
		System.out.println(JSONObject.toJSONString(list));
	}
	
	@Test
	public void test() throws UnsupportedEncodingException {
		//查询某工地下所有人员的图片
		String constructionId = "67b63245bec94eb39a84121c116c960c";
		List<FileEntity> list = fileMapper.queryListByConstructionId(constructionId);
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		List<FileEntity> fileList = new ArrayList<FileEntity>();
		for (FileEntity entity : list) {
			String id = entity.getId();
			String path = entity.getPath();
			//截取文件名
			String filename = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
			//文件中文名转码
			filename = URLEncoder.encode(filename, "UTF-8");
			path = path.substring(0, path.lastIndexOf("/")) + "/" + filename + ".jpg";
			entity.setPath(path);
			fileList.add(entity);
		}
		
		if (CollectionUtils.isEmpty(fileList)) {
			return;
		}
		//批量更新文件路径
		fileMapper.batchUpdatePath(fileList);
		
	}

	@Test
	public void uploadRelativePath() {
		String material = service.getUploadRelativePath("material");
		System.out.println(material);
	}


}
