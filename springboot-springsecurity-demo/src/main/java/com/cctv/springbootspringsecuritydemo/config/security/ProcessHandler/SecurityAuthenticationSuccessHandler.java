package com.cctv.springbootspringsecuritydemo.config.security.ProcessHandler;

import com.cctv.springbootspringsecuritydemo.service.visitor.bo.VisitorBO;
import com.cctv.springbootspringsecuritydemo.service.visitor.svc.VisitorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @Author: Eric
 * @Date: 2020/2/23 19:40
 */
@Component
public class SecurityAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private VisitorService visitorService;

    private RequestCache requestCache = new HttpSessionRequestCache();

    public SecurityAuthenticationSuccessHandler(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取用户权限
//        Collection<? extends GrantedAuthority> authCollection = authentication.getAuthorities();
//        if (authCollection.isEmpty()) {
//            return;
//        }
        // 认证成功后，获取用户信息并添加到session中
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        VisitorBO visitorBO = visitorService.getVisitorBO(userDetails.getUsername());
        request.getSession().setAttribute("user", visitorBO);

        String url = null;
        // 从别的请求页面跳转过来的情况，savedRequest不为空
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            url = savedRequest.getRedirectUrl();
        }

        if (StringUtils.isEmpty(url)) {
            url = "/index";
        }

        getRedirectStrategy().sendRedirect(request, response, url);

        super.onAuthenticationSuccess(request, response, authentication);
    }

}