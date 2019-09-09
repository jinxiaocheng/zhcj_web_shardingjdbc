package com.escst.commons.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.escst.commons.exception.EscstException;
import com.escst.user.entity.UserEntity;

/**
* @Description: 上下文工具类
* @author  administrator
* @date 2012-12-15 下午11:27:39 
*
 */
public class ContextUtils {
	/**
	 * SpringMvc下获取request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;

	}

	public static HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		return response;

	}
	/**
	 * SpringMvc下获取session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}

	/**
	 * 
	 * @desc 获取当前用户信息
	 * @return
	 * @author niejing
	 * @date 2017年7月3日 下午2:06:45
	 */
	public static UserEntity getCurrentUser() {
		// 获取当前用户信息
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		// 查询登录用户信息
		UserEntity currentUserInfo = (UserEntity) session.getAttribute("currentUserInfo");
		return currentUserInfo;
	}
	
	/**
	 * @desc 得到当前用户ID
	 * @return 
	 * @author zhouwei
	 * @date 2017年7月27日 下午3:58:26
	 */
	public static String getCurrentUserId() {
		UserEntity userEntity = getCurrentUser();
		if (userEntity == null) {
			throw new EscstException("用户session已经失效,请重新登录");
		}
		return userEntity.getId();
	}
	

	/**
	 * @desc 得到机构logo名称
	 * @return 
	 * @author zhouwei
	 * @date 2017年12月13日 上午10:57:46
	 */
	public static String getLogoName() {
		// 获取当前用户信息
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		// 查询登录用户信息
		String logoName = (String) session.getAttribute("logoName");
		return StringUtils.isBlank(logoName) ? "index_logo.png" : logoName;
	}
}
