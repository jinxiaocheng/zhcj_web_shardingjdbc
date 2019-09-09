package com.escst.commons.core;

import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.ResourceUtil;
import com.escst.user.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author caozx
 * @desc
 * @date 2017/8/30 16:17
 */
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    /**登录接口路径**/
    private String excludeUrls;

    /**资源文件路径**/
    private String resourceUrls;

    /**对外视频页面url**/
    private String escstVideoPreviewUrls;

    public String getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(String excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    public String getResourceUrls() {
        return resourceUrls;
    }

    public void setResourceUrls(String resourceUrls) {
        this.resourceUrls = resourceUrls;
    }

    public String getEscstVideoPreviewUrls() {
        return escstVideoPreviewUrls;
    }

    public void setEscstVideoPreviewUrls(String escstVideoPreviewUrls) {
        this.escstVideoPreviewUrls = escstVideoPreviewUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestPath = ResourceUtil.getRequestPath(request);
        //如果是登录接口，则跳过
        if( (requestPath.indexOf(excludeUrls) != -1) || (requestPath.indexOf(resourceUrls) != -1) || (requestPath.indexOf(escstVideoPreviewUrls) != -1)) {
            return true;
        } else {
            UserEntity userEntity = ContextUtils.getCurrentUser();
            if(userEntity == null) {
                logger.info("用户信息已失效，请重新登录！");
                String url = request.getContextPath() + "/logout";
                response.sendRedirect(url);
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
