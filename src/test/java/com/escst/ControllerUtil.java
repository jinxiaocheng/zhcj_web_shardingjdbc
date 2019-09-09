package com.escst;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @desc 
 * @author zhouwei
 * @date 2015年12月24日 上午10:50:37
 */
public class ControllerUtil {

	/**
	 * @desc 
	 * @param mvc
	 * @param url
	 * @param jsonStr json字符串
	 * @author zhouwei
	 * @date 2016年1月3日 上午11:50:23
	 */
	public static void proPost(MockMvc mvc, String url, String jsonStr) {
		try {
			System.out.println("ControllerUtil请求的URL:" + url);
			System.out.println("ControllerUtil请求的参数:" + jsonStr);
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(url);
			request.contentType(MediaType.APPLICATION_JSON);
			request.characterEncoding("UTF-8");
			request.content(jsonStr);
			ResultActions result = mvc.perform(request);
			print(result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @desc 
	 * @param mvc
	 * @param url
	 * @param params get请求的参数
	 * @author zhouwei
	 * @date 2016年1月3日 上午11:51:25
	 */
	public static void proGet(MockMvc mvc, String url, Map<String, String> params) {
		try {
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(url);
			request.contentType(MediaType.APPLICATION_JSON);

			if (params != null && params.size() > 0) {
				Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					request.param(entry.getKey(), entry.getValue());
				}
			}

			ResultActions result = mvc.perform(request);
			print(result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void print(ResultActions result) {
		try {
			// result.andExpect(MockMvcResultMatchers.status().isOk());
			MockHttpServletResponse response = result.andReturn().getResponse();
			response.setCharacterEncoding("UTF-8");
			String rst = response.getContentAsString();
			if (StringUtils.isBlank(rst)) {
				Exception e = result.andReturn().getResolvedException();
				if (e != null) {
					System.out.println("调用出现异常:" + e.getMessage());
				}
				System.out.println("controller返回结果为空:" + result.andReturn().getResponse().getErrorMessage());
			}
			else {
				System.out.println("controller返回结果:" + rst);
			}
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
