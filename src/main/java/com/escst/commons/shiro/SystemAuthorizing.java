package com.escst.commons.shiro;

import java.util.List;

import com.escst.commons.utils.ResourceUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.escst.construction.service.ConstructionService;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.organization.entity.OrgEntity;
import com.escst.organization.service.OrgService;
import com.escst.role.entity.RoleEntity;
import com.escst.user.entity.UserEntity;
import com.escst.user.mapper.UserMapper;

/**
 * @author caozx
 * @desc 自定义的指定shiro验证用户登陆的类
 * @date 2017/4/25 13:07
 */
public class SystemAuthorizing extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(SystemAuthorizing.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrgService orgService;
    
    @Autowired
    private ConstructionService constructionService;

    /**
     * 授权查询回调函数，进行鉴权但缓存中无用户的授权信息时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String userName = (String)super.getAvailablePrincipal(principalCollection);
        UserEntity userEntity =  userMapper.selectUserByUserName(userName);
        if(userEntity == null){
            throw new AuthenticationException("用户不存在!");
        }
        //通过用户ID查询该用户所属角色信息
        List<RoleEntity> roleList = userMapper.selectRoleById(userEntity.getId());
        if(CollectionUtils.isEmpty(roleList)) {
            for(RoleEntity roleEntity : roleList) {
                authorizationInfo.addRole(roleEntity.getId());
            }
        }

        //通过用户id查询用户下的所有菜单权限
        List<String> menuIdList = userMapper.selectMenuById(userEntity.getId());
        if(!CollectionUtils.isEmpty(menuIdList)) {
            authorizationInfo.addStringPermissions(menuIdList);
        }
        return authorizationInfo;
    }

    /**
     * shiro登陆认证，用户提交用户名密码，shiro封装令牌，realm通过用户名密码查询返回，
     * shiro自动比较用户名密码是否匹配，进行登陆控制
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        AuthenticationInfo authenticationInfo = null;
        //获取基于用户名和密码的令牌
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        //通过用户名查询用户信息
        String userName = usernamePasswordToken.getUsername();
        UserEntity userEntity =  userMapper.selectUserByUserName(userName);
        if(userEntity == null) {
            throw new UnknownAccountException();
        } else {
            String username = userEntity.getUserName();
            String password = userEntity.getUserPassword();
            String realName = this.getName();
            try {
                authenticationInfo = new SimpleAuthenticationInfo(username,password,realName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //通过用户Id查询用户所属工地信息集合
            String id = userEntity.getId();
            List<SimpleConstructionVO> constructionList = constructionService.queryAuthConstructionByUserId(id);
            userEntity.setConstructionList(constructionList);

            //将用户是否为工地人员放入缓存
            OrgEntity orgEntity = orgService.queryById(userEntity.getOrgId());
            String constructionId = orgEntity.getConstructionId()!=null?orgEntity.getConstructionId():null;
            String logoName = "";
            // add by zhouwei 2017-12-13 如果是工地机构,则找父机构的logo
            if (StringUtils.isBlank(constructionId)) {
            	logoName = orgEntity.getLogoName();
            }
            else {
            	String sysCode = orgEntity.getSysCode();
            	String parentSysCode = sysCode.substring(0, sysCode.lastIndexOf("_"));
            	List<OrgEntity> orgList = orgService.queryParentBySysCode(parentSysCode);
            	for (OrgEntity parent : orgList) {
            		String pSysCode = parent.getSysCode();
            		if (StringUtils.isNotBlank(pSysCode)) {
            			logoName = parent.getLogoName();
            			break;
            		}
            	}
            }
			userEntity.setConstructionId(constructionId);
			this.setSession("currentUserInfo", userEntity);
			this.setSession("logoName", logoName);

			//添加版本号
            String version = ResourceUtil.getVersion();
            this.setSession("v",version);
        }
        return authenticationInfo;
    }

    /**
     * 用户名保存到session对象中
     * @param key
     * @param value
     */
    private void setSession(Object key,Object value) {
        Session session = getSession();
        if(session != null){
        	logger.debug("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            session.setAttribute(key,value);
        }
    }

    /**
     * 获取session对象
     * @return
     */
    private Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if(session == null) {
                session = subject.getSession();
            }
            if(session != null) {
                return session;
            }
        } catch (InvalidSessionException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
