package com.escst.message.controller;

import com.escst.message.vo.NoticeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.message.entity.MessageEntity;
import com.escst.message.service.MessageService;

import java.util.List;

/**
 * @desc
 * @author niejing
 * @date 2018年2月5日 下午4:52:12
 */
@Controller
@RequestMapping("message")
public class MessageController {
	private static Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private MessageService messageService;

	@RequestMapping("list")
	public ModelAndView list(Model model) {

		return new ModelAndView("message/list");
	}

	@RequestMapping("listData")
	@ResponseBody
	public ReturnJson listData(MessageEntity entity) {
		ReturnJson returnJson = null;
		try {
			entity.setCreateBy(ContextUtils.getCurrentUserId());
			PageVo pagevo = messageService.queryList(entity);
			returnJson = ReturnJson.success(pagevo);
		} catch (Exception e) {
			logger.error("查询消息列表异常", e);
			returnJson = ReturnJson.fail("查询消息列表异常");
		}
		return returnJson;
	}

	/**
	* @desc 获取消息总数
	* @param entity
	* @return 
	* @author dwj
	* @date 6/8/2018 14:20
	*/
	@RequestMapping("getCount")
	@ResponseBody
	public ReturnJson getCount(MessageEntity entity) {
		ReturnJson returnJson = null;
		try {
			entity.setCreateBy(ContextUtils.getCurrentUserId());
			int count  = messageService.getCount(entity);
			returnJson = ReturnJson.success(count);
		} catch (Exception e) {
			logger.error("获取消息总数", e);
			returnJson = ReturnJson.fail("系统异常");
		}
		return returnJson;
	}
	
	/**
	 * @desc 跳转消息推送新增页面
	 * @param
	 * @return
	 * @author kz
	 * @date 2018/2/6 11:32
	 */
	@RequestMapping("toAdd")
	public ModelAndView toAdd(Model model) {
		return new ModelAndView("message/add");
	}

	@RequestMapping("batchAdd")
	@ResponseBody
	public ReturnJson batchAdd(@RequestBody MessageEntity entity) {
		ReturnJson returnJson = null;
		try {
			entity.setUserId(ContextUtils.getCurrentUserId());
			messageService.batchAdd(entity);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			logger.error("新增消息列表异常", e);
			returnJson = ReturnJson.fail("新增消息列表异常");
		}
		return returnJson;
	}
	
	/**
	 * @desc 跳转查看接受人页面
	 * @param
	 * @return
	 * @author kz
	 * @date 2018/2/6 11:34
	 */
	@RequestMapping("toView")
	public ModelAndView toView(Model model) {
		return new ModelAndView("message/view");
	}

	@RequestMapping("view")
	@ResponseBody
	public ReturnJson view(Model model, String billId) {
		ReturnJson returnJson = null;
		try{
			PageVo pageVo= messageService.queryUserByBillId(billId);
			returnJson = ReturnJson.success(pageVo);
		}catch(Exception e){
			logger.error("查看接收人异常",e);
			returnJson = ReturnJson.fail("查看接收人异常");
		}
		return returnJson;
	}
	
	/** 消息推送新增页面中选择人员弹出页面
	 * @desc
	 * @param
	 * @return
	 * @author kz
	 * @date 2018/2/6 17:30
	 */
	@RequestMapping("toAddPerson")
	public ModelAndView toAddPerson(Model model) {
		model.addAttribute("userId", ContextUtils.getCurrentUserId());
		return new ModelAndView("message/addPerson");
	}

	/**
	 * @desc 查看消息详情
	 * @param entity
	 * @return
	 * @author dwj
	 * @date 6/8/2018 14:20
	 */
	@RequestMapping("getDeatil")
	@ResponseBody
	public ReturnJson getDeatil(MessageEntity entity) {
		ReturnJson returnJson = null;
		try {
			entity.setCreateBy(ContextUtils.getCurrentUserId());
			MessageEntity messageEntity  = messageService.queryById(entity);
			returnJson = ReturnJson.success(messageEntity);
		} catch (Exception e) {
			logger.error("查看消息详情", e);
			returnJson = ReturnJson.fail("系统异常");
		}
		return returnJson;
	}

	/**
	 * @desc 获取未读消息列表
	 * @param entity
	 * @return
	 * @author dwj
	 * @date 6/8/2018 14:20
	 */
	@RequestMapping("getNotice")
	@ResponseBody
	public ReturnJson getNotice(MessageEntity entity) {
		ReturnJson returnJson = null;
		try {
			entity.setCreateBy(ContextUtils.getCurrentUserId());
			NoticeVo vo  = messageService.selectNotice(entity);
			returnJson = ReturnJson.success(vo);
		} catch (Exception e) {
			logger.error("获取未读消息列表", e);
			returnJson = ReturnJson.fail("系统异常");
		}
		return returnJson;
	}

	/**
	 * @desc 批量修改is_read状态
	 * @param ids
	 * @return
	 * @author dwj
	 * @date 6/8/2018 14:20
	 */
	@RequestMapping("batchUpdate")
	@ResponseBody
	public ReturnJson batchUpdate(String ids) {
		ReturnJson returnJson = null;
		try {
			 messageService.batchUpdate(ids);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			logger.error("批量修改", e);
			returnJson = ReturnJson.fail("系统异常");
		}
		return returnJson;
	}
}
