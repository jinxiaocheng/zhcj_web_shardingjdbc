package com.escst.message.service;

import java.util.*;

import com.escst.commons.utils.ResourceUtil;
import com.escst.commons.utils.StringUtils;
import com.escst.menu.mapper.MenuMapper;
import com.escst.message.vo.NoticeVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.MessagePushUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.MessageVo;
import com.escst.commons.vo.PageVo;
import com.escst.message.entity.MessageEntity;
import com.escst.message.mapper.MessageMapper;
import com.escst.organization.entity.OrgEntity;
import com.escst.organization.service.OrgService;
import com.escst.user.entity.UserEntity;
import com.escst.user.mapper.UserMapper;

/**
 * @desc
 * @author niejing
 * @date 2018年2月5日 下午4:52:21
 */
@Service
public class MessageService {

	@Autowired
	private MessageMapper mapper;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrgService orgService;
	@Autowired
	private MenuMapper menuMapper;
	//web端推送标识
	private static final int WEB_TYPE = 0;

	//消息推送Id
	private static final String MESSAGE_ID = ResourceUtil.getConfigByName("message_id");


	public PageVo queryList(MessageEntity entity) {
		PageVo pageVo = new PageVo();
		List<MessageVo> vos = new ArrayList<MessageVo>();
		try {
			entity.setType(0);
			// 查询材料进场总记录数
			Map<String,Object> paraMap = new HashMap<String, Object>();
			paraMap.put("type",0);
			paraMap.put("createBy",entity.getCreateBy());
			int count = mapper.selectCount(paraMap);
			// 当前页
			pageVo.setCurrentPage(entity.getPage());
			// 总记录数
			pageVo.setTotalRecord(count);
			pageVo.setPageSize(entity.getRowNum());
			// 每页第一条记录的索引
			int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
			entity.setStartIndex(startIndex);
			// 查询数据
			List<Map<String,Object>> list = mapper.selectMapList(entity);
			if (!CollectionUtils.isEmpty(list)) {
				for(Map<String,Object> map : list){
					MessageVo vo = new MessageVo();
					String acceptId = (String)map.get("acceptId");
					String id = (String)map.get("id");
					String title =(String)map.get("title");
					String content = (String)map.get("content");
					String createTime = (String)map.get("createTime");
					String billId = (String)map.get("billId");
					long acceptNum = (Long)map.get("acceptNum");
					vo.setTitle(title);
					vo.setCreateTime(createTime);
					vo.setContent(content);
					vo.setBillId(billId);
					vo.setId(id);
			if(acceptId.equals(entity.getCreateBy())){
					vo.setAuth(false);
			}else{
					vo.setAcceptNum((int)acceptNum);
					vo.setAuth(true);
			}
			vos.add(vo);
		}
		pageVo.setRows(vos);
	}
} catch (Exception e) {
		throw new EscstException("查询消息列表异常：" + e.getMessage(), e);
		}
		return pageVo;
		}

public void batchAdd(MessageEntity entity) {
		try {
			String[] acceptIds = entity.getAcceptIds();
			if(acceptIds.length<1){
				return;
			}
			String billId = UuidUtils.getUuid();
//			List<MessageEntity> list = new ArrayList<MessageEntity>();
//			for(int i=0;i<acceptIds.length;i++){
//				MessageEntity en = new MessageEntity();
//				JSONObject jsonArray = JSONObject.parseObject(acceptIds[i]);
//				String acceptId = jsonArray.getString("id");
//				String orgId = jsonArray.getString("constructionId");
//				OrgEntity org = orgService.queryById(orgId);
//				String constructionId = "";
//				if(org!=null){
//					constructionId = org.getConstructionId()!=null?org.getConstructionId():"";
//				}
//				
//				en.setId(UuidUtils.getUuid());
////				en.setAcceptId(acceptId);
//				en.setTitle(entity.getTitle());
//				en.setType(WEB_TYPE);
//				en.setIsRead(entity.getIsRead());
//				en.setCreateTime(new Date());
//				en.setCreateBy(acceptId);
//				en.setContent(entity.getContent());
//				en.setBillId(billId);
//				en.setConstructionId(constructionId);
//				list.add(en);
//				
//			}
//			mapper.batchInsert(list);
			
			//调用发送消息微服务推送消息
			for(int i=0;i<acceptIds.length;i++){
				JSONObject jsonArray = JSONObject.parseObject(acceptIds[i]);
				String acceptId = jsonArray.getString("id");
				String orgId = jsonArray.getString("constructionId");
				OrgEntity org = orgService.queryById(orgId);
				String constructionId = "";
				if(org!=null){
					constructionId = org.getConstructionId()!=null?org.getConstructionId():"";
				}
				
				Set<String> users = new HashSet<String>();
				users.add(acceptId);
				
				MessageVo vo = new MessageVo();
				vo.setUsers(users);
				vo.setConstructionId(constructionId);
				vo.setTitle(entity.getTitle());
				vo.setContent(entity.getContent());
				vo.setType(WEB_TYPE);
				vo.setBillId(billId);
				vo.setCreateBy(entity.getUserId());
				vo.setStatus(WEB_TYPE);//type=1,2,3时才有用，这里随便传值保证不为空
				// 发送消息
				MessagePushUtils.sendMessage(vo);
			}
		} catch (Exception e) {
			throw new EscstException("新增消息异常", e);
		}
	
	}

	/**
	* @desc 查看详情
	* @param entity
	* @return
	* @author dwj
	* @date 10/8/2018 9:46
	*/
	public MessageEntity queryById(MessageEntity entity) {
		MessageEntity messageEntity = new MessageEntity();
		try {
			String createBy = entity.getCreateBy();
			messageEntity = mapper.selectByIdDetail(entity.getId());
			List<String> names = new ArrayList<String>();
			if(!messageEntity.getAcceptId().equals(createBy)){
				String billId = messageEntity.getBillId();
				List<String> userList = mapper.selectByBillId(billId);
				for (String userId : userList) {
					UserEntity map = userMapper.selectUserById(userId);
					String name = map.getName();
					names.add(name);
				}
			}
			messageEntity.setAcceptNames(names);
		} catch (Exception e) {
			throw new EscstException("通过id查询消息异常", e);
		}
		return messageEntity;
	}
	
	/**
	 * 
	 * @desc
	 * @param billId
	 * @return
	 * @author niejing
	 * @date 2018年2月9日 下午1:29:23
	 */
	public PageVo queryUserByBillId(String billId) {
		PageVo pageVo = new PageVo();
		try {
			List<UserEntity> list = new ArrayList<UserEntity>();
			List<String> userList = mapper.selectByBillId(billId);
			for (String userId : userList) {
				UserEntity map = userMapper.selectUserById(userId);
				list.add(map);
			}
			pageVo.setRows(list);
		} catch (Exception e) {
			throw new EscstException("查看接收人异常", e);
		}
		return pageVo;
	}

	public int getCount(MessageEntity entity){
		int count =0;
		try{
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("type",1);
			map.put("createBy",entity.getCreateBy());
			count =  mapper.selectCount(map);
		}catch (Exception e){
			throw new EscstException("查询数量异常"+e.getMessage(),e);
		}
		return count;
	}


	public NoticeVo selectNotice(MessageEntity entity){
		NoticeVo vo = new NoticeVo();
		List<MessageVo> vos = new ArrayList<MessageVo>();
		try{
			Map<String,Object> paraMap = new HashMap<String, Object>();
			paraMap.put("userId",entity.getCreateBy());
			paraMap.put("menuId",MESSAGE_ID);
			String isMenu = menuMapper.selectMenuByUserId(paraMap);
			if(StringUtils.isBlank(isMenu)){
				vo.setMenu(false);
			}else {
				vo.setMenu(true);
			}
			entity.setType(1);
			List<Map<String,Object>> list = mapper.selectMapList(entity);
			for(Map<String,Object> map :list){
				MessageVo messageVo = new MessageVo();
				String id = (String)map.get("id");
				String title = (String)map.get("title");
				String content =title+":"+(String)map.get("content");
				String createBy =(String)map.get("createName");
				String createTime =(String)map.get("createTime");
				String create = (String) map.get("createBy");
				if(!entity.getCreateBy().equals(create)){
					if(content.length() >= 50){
						content = content.substring(0,49)+"...";
					}
					messageVo.setId(id);
					messageVo.setContent(content);
					messageVo.setCreateBy(createBy);
					messageVo.setCreateTime(createTime);
					vos.add(messageVo);
				}
			}
			vo.setVos(vos);
		}catch (Exception e){
			throw new EscstException("获取未读消息列表异常"+e.getMessage(),e);
		}
		return vo;
	}

	public void batchUpdate(String ids){
		try{
			String[] arrIds = ids.split(",");
			List<String> list = Arrays.asList(arrIds);
			mapper.batchUpdate(list);
		}catch (Exception e){
			throw new EscstException("修改消息失败"+e.getMessage(),e);
		}
	}

}
