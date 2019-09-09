package com.escst.user.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.escst.menu.entity.MenuEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.constant.Globals;
import com.escst.commons.manager.ClientManager;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.Client;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.IpUtil;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.menu.service.MenuService;
import com.escst.organization.entity.OrgEntity;
import com.escst.organization.service.OrgService;
import com.escst.projectCompany.service.ProjectCompanyService;
import com.escst.role.bean.RoleQueryBean;
import com.escst.role.service.RoleService;
import com.escst.user.entity.UserEntity;
import com.escst.user.service.LogService;
import com.escst.user.service.UserService;
import com.escst.user.vo.UserVo;

/**
 * @author caozx
 * @desc 用户处理控制器
 * @date 2017年2月14日 下午2:05:38
 */
@Controller
@RequestMapping("user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private LogService userLoginLogService;

	@Autowired
	private OrgService orgService;

	@Autowired
	private ProjectCompanyService projectCompanyService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	/**
	 * * @desc 检查用户名
	 *
	 * @return
	 * @author caozx
	 * @date 2017年2月14日 下午2:05:38
	 */
	@RequestMapping("checkUser")
	@ResponseBody
	public ReturnJson checkUser(HttpServletRequest request, @RequestBody UserEntity user) {
		ReturnJson returnJson = null;
		try {
			HttpSession session = ContextUtils.getSession();
			/** 检查用户是否存在 */
			UserEntity u = userService.checkUserExits(user);
			if (u == null) {
				returnJson = ReturnJson.fail("用户名或者密码错误！");
			} else {
				Client client = new Client();
				client.setIp(IpUtil.getIpAddr(request));
				client.setLogindatetime(new Date());
				client.setUserEntity(u);
				System.out.println(session.getId());
				ClientManager.getInstance().addClinet(session.getId(), client);
				// 添加登录日志
				String message = "用户: " + u.getUserName() + "登录成功";
				userLoginLogService.add(message, Globals.Log_Type_LOGIN, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			logger.error("检查用户名出现异常：" + e.getMessage(), e);
			returnJson = ReturnJson.fail("检查用户名出现异常：" + e.getMessage());
		}
		return returnJson;
	}

	@RequestMapping("list")
	public ModelAndView list(Model model) {
		// 查询登录用户信息
		UserEntity user = ContextUtils.getCurrentUser();
		model.addAttribute("orgId", user.getOrgId());
		model.addAttribute("userId", user.getId());

		return new ModelAndView("/user/userList");
	}

	@RequestMapping("fetchStartPreviewParam")
	public ModelAndView fetchStartPreviewParam(Model model, String orgId) {
		OrgEntity org = orgService.queryById(orgId);
		if (StringUtils.isNotBlank(org.getConstructionId())) {
			model.addAttribute("constructionId", org.getConstructionId());
		}
		model.addAttribute("orgId", orgId);
		return new ModelAndView("user/userTreeList");
	}

	/**
	 * 用户列表
	 *
	 * @param entity
	 * @return
	 */
	@RequestMapping("userList")
	@ResponseBody
	public ReturnJson userList(UserEntity entity) {
		ReturnJson returnJson = null;
		try {
			PageVo pageVo = userService.queryUser(entity);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			logger.error("查询用户列表异常", e);
			returnJson = ReturnJson.fail("查询用户列表异常：" + e.getMessage());
		}
		return returnJson;
	}

	/**
	 * 
	 * @desc 消息推送专用 
	 * @param entity
	 * @return 
	 * @author niejing
	 * @date 2018年2月7日 下午2:53:30
	 */
	@RequestMapping("userList2")
	@ResponseBody
	public ReturnJson userList2(UserEntity entity) {
		ReturnJson returnJson = null;
		try {
			//只查询有效用户
			entity.setStatus(1);
			OrgEntity org = orgService.queryById(entity.getOrgId());
			if (org!=null && StringUtils.isNotBlank(org.getConstructionId())) {
				entity.setConstructionId(org.getConstructionId());
			}
			PageVo pageVo = userService.queryUser(entity);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			logger.error("查询用户列表异常", e);
			returnJson = ReturnJson.fail("查询用户列表异常：" + e.getMessage());
		}
		return returnJson;
	}
	
	/**
	 * 新增用户，如果是工地管理员需要新增一条机构数据
	 *
	 * @return
	 */
	@RequestMapping("toAddUser")
	public ModelAndView toAddUser(Model model, String orgId) {
		OrgEntity org = orgService.queryById(orgId);
		List<Map<String, Object>> companyList = projectCompanyService.listByConstructionId(org.getConstructionId(),
				ContextUtils.getCurrentUserId());

		model.addAttribute("org", org);
		model.addAttribute("companyList", companyList);
		return new ModelAndView("/user/addUser");
	}

	@RequestMapping("addUser")
	@ResponseBody
	public ReturnJson addUser(UserEntity bean) {
		ReturnJson returnJson = null;
		try {
			userService.addUser(bean);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			logger.error("查询用户列表异常", e);
			returnJson = ReturnJson.fail("插入用户异常：" + e.getMessage());
		}
		return returnJson;
	}

	/**
	 * 新增用户，如果是工地管理员需要新增一条机构数据
	 *
	 * @return
	 */
	@RequestMapping("toEdit")
	public ModelAndView toEdit(Model model, String id, String orgId) {
		UserEntity entity = userService.queryUserById(id);
		model.addAttribute("entity", entity);
		return new ModelAndView("/user/edit");
	}

	@RequestMapping("edit")
	@ResponseBody
	public ReturnJson edit(UserEntity bean) {
		ReturnJson returnJson = null;
		try {
			userService.edit(bean);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			logger.error("查询用户列表异常", e);
			returnJson = ReturnJson.fail("插入用户异常：" + e.getMessage());
		}
		return returnJson;
	}

	/**
	 * @param model
	 * @param orgId
	 * @param userId
	 * @return
	 * @desc 跳转到分配角色页面
	 * @author
	 * @date 2017年7月11日 上午8:56:42
	 */
	@RequestMapping("toDistribution")
	public ModelAndView toDistribution(Model model, String orgId, String userId) {
		// 查询该用户已有的角色
		List<String> checkedIds = userService.queryRoleIdById(userId);
		model.addAttribute("checkedIds", checkedIds);

		model.addAttribute("orgId", orgId);
		model.addAttribute("userId", userId);
		return new ModelAndView("/user/distribution");
	}

	/**
	 * @param bean
	 * @return
	 * @desc 分配角色
	 * @author niejing
	 * @date 2017年7月11日 上午8:57:14
	 */
	@RequestMapping("distribution")
	@ResponseBody
	public ReturnJson distribution(RoleQueryBean bean) {
		ReturnJson returnJson = null;
		PageVo pageVo = null;
		try {
			OrgEntity org = orgService.queryById(bean.getOrgId());
			if (StringUtils.isNotBlank(org.getConstructionId())) {
				bean.setConstructionId(org.getConstructionId());
			}
			pageVo = roleService.queryRoleByCondition(bean);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			logger.error("根据组织ID查询角色列表异常", e);
			returnJson = ReturnJson.fail("根据组织ID查询角色列表异常");
		}
		return returnJson;
	}

	@RequestMapping("addRoleForUser")
	@ResponseBody
	public ReturnJson addRoleForUser(String userId, String[] idList) {
		logger.info("addRoleForUser start idList is {}", idList);
		ReturnJson returnJson = null;
		try {
			userService.saveUserRole(userId, idList);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			logger.error("保存用户角色信息异常", e);
			returnJson = ReturnJson.fail("保存用户角色信息异常");
		}
		return returnJson;
	}

	@RequestMapping("fetchAllMenuNodeList")
	@ResponseBody
	public ReturnJson fetchAllMenuNodeList(MenuEntity entity) {
		ReturnJson returnJson = new ReturnJson();
		try {
			List<TreeEntity> list = menuService.queryAllMenuList(entity);
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("获取树节点信息异常：");
			logger.error("获取树节点信息异常：" + e.getMessage(), e);
		}
		return returnJson;
	}

	/**
	 * @param orgId 机构id
	 * @return com.escst.commons.vo.ReturnJson
	 * @desc 根据机构id查询菜单集合
	 * @author jincheng
	 * @date 2018/1/17 14:24
	 */
	@RequestMapping("fetchMenuNodeList")
	@ResponseBody
	public ReturnJson fetchMenuNodeList(String orgId) {
		ReturnJson returnJson = new ReturnJson();
		try {
			List<TreeEntity> list = menuService.queryMenuList(orgId);
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("获取树节点信息异常：");
			logger.error("获取树节点信息异常：" + e.getMessage(), e);
		}
		return returnJson;
	}

	/**
	 * @param orgid
	 *            机构id
	 * @return com.escst.commons.vo.ReturnJson
	 * @desc 根据机构id, 查询菜单集合(录入角色时调用)
	 * @author jincheng
	 * @date 2018/1/17 14:24
	 */
	@RequestMapping("fetchOrgMenuNodeList")
	@ResponseBody
	public ReturnJson fetchOrgMenuNodeList(String orgId) {
		ReturnJson returnJson = new ReturnJson();
		try {
			List<TreeEntity> list = menuService.queryOrgMenuList(orgId);
			if (CollectionUtils.isEmpty(list)) {
				// 机构被删除
				returnJson = ReturnJson.fail("该机构没有权限，或者已失效");
			} else {
				returnJson = ReturnJson.success(list);
			}
		} catch (Exception e) {
			returnJson = ReturnJson.fail("获取树节点信息异常：");
			logger.error("获取树节点信息异常：" + e.getMessage(), e);
		}
		return returnJson;
	}

	@RequestMapping("checkUserName")
	@ResponseBody
	public ReturnJson checkUserName(String userName) {
		ReturnJson returnJson = null;
		try {
			UserEntity entity = this.userService.checkUserName(userName);
			if (entity != null) {
				returnJson = ReturnJson.success(0);
			} else {
				returnJson = ReturnJson.success(1);
			}
		} catch (Exception e) {
			returnJson = ReturnJson.fail("通过userName查询user信息异常");
			logger.error("通过userName查询user信息异常", e);
		}
		return returnJson;
	}

	/**
	 * @param id
	 * @return
	 * @desc 将 用户状态改为无效
	 * @author niejing
	 * @date 2017年9月8日 下午2:03:25
	 */
	@RequestMapping("delete")
	@ResponseBody
	public ReturnJson delete(String id) {
		ReturnJson returnJson = null;
		try {
			userService.delete(id);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			returnJson = ReturnJson.fail("修改用户状态异常");
			logger.error("修改用户状态异常", e);
		}
		return returnJson;
	}

	/**
	 * @param userId
	 * @return
	 * @desc 重置用户密码
	 * @author zhouwei
	 * @date 2017年10月31日 下午1:48:09
	 */
	@RequestMapping("resetPwd")
	@ResponseBody
	public ReturnJson resetPwd(String userId) {
		ReturnJson returnJson = null;
		try {
			userService.resetPassword(userId);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			returnJson = ReturnJson.fail(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return returnJson;
	}

	/**
	 * @param vo
	 * @return
	 * @desc 修改密码
	 * @author dwj
	 * @date 2018/1/12 9:20
	 */
	@RequestMapping("updatePwd")
	@ResponseBody
	public ReturnJson updatePwd(UserVo vo) {
		ReturnJson returnJson = null;
		try {
			String userId = ContextUtils.getCurrentUserId();
			vo.setId(userId);
			userService.updatePassWord(vo);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			returnJson = ReturnJson.fail("修改密码异常" + e.getMessage());
			logger.error("系统异常", e.getMessage());
		}
		return returnJson;
	}
}
