package com.org.filter;


import com.org.constant.BASERoute;
import com.org.auth.auth2.authentication.AuthenticationManager;
import com.org.auth.auth2.authentication.OAuthMetaDataHandlerI;
import com.org.logger.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author abdul.rehman4
 * @version 1.0
 * @since v1.0
 * The all requests are intercepted at this filterand authenticated. If user is not authorized then un-authenticated response
 * is sent back. The authentication and test resources are whitelisted for authentication
 */

@Component
@Order(1)
public class AuthFilter extends LogManager implements Filter  {

    @Value(value = "${spring.application.auth2.enabled}")
    public boolean enabled;

    public AuthFilter(){
        super(AuthFilter.class);
    }

    @Autowired
    OAuthMetaDataHandlerI dbHandler;
    private void init(){
        try{
            AuthenticationManager.init(dbHandler);
        }catch (Exception e){
            error(e.getMessage(),e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

            init();
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
        if(enabled) {
            if (req.getRequestURI().indexOf(BASERoute.BASE_ROUTE) != -1 && req.getRequestURI().indexOf("/test") == -1) {
                if (!req.getRequestURI().contains(BASERoute.BASE_ROUTE + "/auth")) {
                    String accessToken = req.getHeader("Authorization");
                    String clientId = req.getHeader("ClientId");
                    if (Boolean.FALSE.equals(AuthenticationManager.authenticateByAccessToken(clientId, accessToken))) {
                        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token invalid or expired.");
                        return;
                    }
                }
            }
        }
            chain.doFilter(request, response);

    }

}