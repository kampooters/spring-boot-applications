package com.org.assignment.common.constants;

import com.org.assignment.controller.AuthController;

/**
 * @author abdul.rehman4
 * @version 1.0
 * @since v1.0
 * {@link AuthRoute} Contant routes for @{@link AuthController}
 */
public interface AuthRoute extends  BASERoute{
    public static String TEST = "/auth/test";
    public static String REGISTER = "/auth/register";
    public static String REGISTER_OAUTH_CLIENT = "/auth/register/client";
    public static String ACCESS_TOKEN = "/auth/get/accesstoken";
    public static String REFRESH_TOKEN = "/auth/get/refreshtoken";
}
