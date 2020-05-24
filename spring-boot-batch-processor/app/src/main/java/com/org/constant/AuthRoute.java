package com.org.constant;

import com.org.controller.AuthController;

/**
 * @author abdul.rehman4
 * @version 1.0
 * @since v1.0
 * {@link AuthRoute} Contant routes for @{@link AuthController}
 */
public interface AuthRoute extends BASERoute{
    String TEST = "/auth/test";
    String REGISTER = "/auth/register";
    String ACCESS_TOKEN = "/auth/get/accesstoken";
    String REFRESH_TOKEN = "/auth/get/refreshtoken";
}
