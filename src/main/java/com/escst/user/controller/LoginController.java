package com.escst.user.controller;

import javax.servlet.http.HttpServletRequest;

import com.escst.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.MD5Util;
import com.escst.commons.vo.ReturnJson;
import com.escst.user.entity.UserEntity;

import java.util.Map;

/**
 * @desc 用户登录控制器
 * @author caozx
 * @date 2017-02-13
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    /**
     * @desc 跳转到登陆页面
     * @author caozx
     * @date 2017-02-13
     * @param request
     * @return
     */
    @RequestMapping("login")
    public ModelAndView home(HttpServletRequest request) {
        String userId = ContextUtils.getCurrentUserId();

        return new ModelAndView("login");
    }

    /**
     * 登陆验证
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("doLogin")
    @ResponseBody
    public ReturnJson doLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam(value = "rememberMe",required = false) boolean rememberMe) throws Exception {
        ReturnJson returnJson = null;
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            password = MD5Util.md5Encode(username+password);
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            try {
                subject.login(token);
                returnJson = ReturnJson.success("登录成功");
            } catch (UnknownAccountException e) {
                logger.error("账号错误",e);
                returnJson = ReturnJson.fail("账号错误");
            } catch (IncorrectCredentialsException e) {
                logger.debug("密码错误",e);
                returnJson = ReturnJson.fail("密码错误");
            } catch (LockedAccountException e) {
                logger.error("账号已被锁定，请与管理员联系",e);
                returnJson = ReturnJson.fail("账号已被锁定，请与管理员联系");
            } catch (AuthenticationException ex) {
                logger.debug("您没有授权");
                returnJson = ReturnJson.fail("您没有授权");
            }
        } else {
            returnJson = ReturnJson.success("登录成功");
        }
        return returnJson;
    }

    /**
     * 登陆成功跳转
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest request,Model model) {
        ModelAndView modelAndView = new ModelAndView("index");
        UserEntity userEntity = ContextUtils.getCurrentUser();
        String id = userEntity.getId();
        Map<String,Object> map = userService.selectOrgInfoById(id);
        String level = String.valueOf((Long)map.get("level"));
        String name = userEntity.getName();
        modelAndView.addObject("name",name);
        modelAndView.addObject("logoName",ContextUtils.getLogoName());
        modelAndView.addObject("level",level);
        modelAndView.addObject("constructionId", map.get("constructionId").toString());
        modelAndView.addObject("areaId", map.get("areaId").toString());
        modelAndView.addObject("constructionName", map.get("constructionName").toString());
        modelAndView.addObject("areaName", map.get("areaName").toString());
        return modelAndView;
    }
}
