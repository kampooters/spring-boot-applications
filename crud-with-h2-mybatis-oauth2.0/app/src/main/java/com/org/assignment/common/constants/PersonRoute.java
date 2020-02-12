package com.org.assignment.common.constants;

/**
 * @author abdul.rehman4
 * @version 1.0
 * @since v1.0
 * Constant routes for @{@link com.org.assignment.controller.PersonController}
 */
public interface PersonRoute extends BASERoute {
    public static String TEST = "/person/test";
    public static String GETALL = "/person/get/all";
    public static String GET_BY_ID = "/person/get/{id}";
    public static String UPDATE = "/person/update";
    public static String DELETE = "/person/delete";
    public static String CREATE = "/person/create";
}
