package com.escst.commons.manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.Client;
import com.escst.commons.utils.ContextUtils;
import com.escst.user.entity.UserEntity;

/**
 * 对在线用户的管理
 * @author caozx
 * @date 2017-02-14
 * @version 1.0
 */
public class ClientManager {
	
	private static ClientManager instance = new ClientManager();
	
	private ClientManager(){
		
	}
	
	public static ClientManager getInstance(){
		return instance;
	}
	
	private Map<String,Client> map = new HashMap<String, Client>();
	
	/**
	 * 
	 * @param sessionId
	 * @param client
	 */
	public void addClinet(String sessionId,Client client){
		map.put(sessionId, client);
	}
	/**
	 * sessionId
	 */
	public void removeClinet(String sessionId){
		map.remove(sessionId);
	}
	/**
	 * 
	 * @param sessionId
	 * @return
	 */
	public Client getClient(String sessionId){
		return map.get(sessionId);
	}
	/**
	 * 
	 * @return
	 */
	public Collection<Client> getAllClient(){
		return map.values();
	}

	/**
	 * @desc 得到用户信息
	 * @return 
	 * @author zhouwei
	 * @date 2017年4月24日 下午7:59:01
	 */
	public static final UserEntity getUserEntity() {
		HttpSession session = ContextUtils.getSession();
		if (ClientManager.getInstance().getClient(session.getId()) == null) {
			throw new EscstException("用户会话已失效,请重新登录系统");
		}
		return ClientManager.getInstance().getClient(session.getId()).getUserEntity();
	}
	
	/**
	 * @desc 得到当前用户的ID
	 * @return 
	 * @author zhouwei
	 * @date 2017年4月24日 下午8:02:33
	 */
	public static final String getUserId() {
		UserEntity entity = getUserEntity();
		return entity.getId();
	}
}
