/*
 *jiji java
 */
package com.demo.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class GenericAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    /*@Autowired
    private ObjectMapper objectMapper;*/


    /*@Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication auth)
            throws ServletException, IOException {
        System.out.println("GenericAuthenticationSuccessHandler userName is :" + auth.getName());
        request.getSession(true);
    }*/

    //从定向
    //private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        super.onAuthenticationSuccess(request, response, authentication);
        /*handle(request, response, authentication);
        clearAuthenticationAttributes(request);*/
    }

    @Override
    protected void handle(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug("错误信息:Response has already been committed. Unable to redirect to "
                    + targetUrl);
            return;
        }
        //response.setContentType("application/json;charset=UTF-8");
        // 把authentication对象转成 json 格式 字符串 通过 response 以application/json;charset=UTF-8 格式写到响应里面去
        //response.getWriter().write(objectMapper.writeValueAsString(authentication));
        //redirectStrategy.sendRedirect(request, response, targetUrl);
        //getRedirectStrategy().sendRedirect(request,response,targetUrl);
        //super.onAuthenticationSuccess(request, response, authentication);
    }

    protected String determineTargetUrl(Authentication authentication) {
        boolean isUser = true;
        Collection<? extends GrantedAuthority> authorities = authentication
                .getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
                break;
            }
        }
        if (isUser) {
            return "/haerbin";
        } else {
            logger.error("security错误信息:");
            throw new IllegalStateException();
        }
    }

}
