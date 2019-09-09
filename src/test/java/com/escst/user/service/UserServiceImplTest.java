package com.escst.user.service;

import com.escst.person.entity.PersonEntity;
import com.escst.person.mapper.PersonMapper;
import com.escst.user.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.escst.commons.utils.MD5Util;
import com.escst.user.entity.UserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml","classpath:/spring/spring-mvc.xml"})
public class UserServiceImplTest {
	
	@Autowired
	private UserService userService;


	@Test
	public void test01_queryUserById(){
		String id = "8f406b9a-eaad-11e6-9ead-507b9d5bb0f1";
		UserEntity user = userService.queryUserById(id);
		System.out.println(user.getUserName());
	}
	

	@Test
	public void test03_getUserPassword() {
		String useName = "admin";
		String password = "a";
		try {
			String encodePassword = MD5Util.md5Encode(useName + password);
			System.out.println(encodePassword);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PersonMapper personMapper;



	@Test
	public  void updatePerson(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("orgId","a1fdcf82c33e49aeb2e4471a7f2c1eb6");
		map.put("userName","sfjc");
		List<UserEntity> list = userMapper.selectByUser(map);
		for(UserEntity entity : list){
			PersonEntity personEntity = new PersonEntity();
			personEntity.setName(entity.getName());
			personEntity.setMobile(entity.getUserName());
			personEntity.setUserId(entity.getId());
			personMapper.update(personEntity);
		}
	}
}
