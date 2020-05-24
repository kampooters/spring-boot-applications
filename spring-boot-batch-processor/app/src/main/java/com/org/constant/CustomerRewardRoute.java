package com.org.constant;



/**
 * @author abdul.rehman4
 * @version 1.0
 * @since v1.0
 * Constant routes for @{@link com.org.controller.CustomerRewardController}
 */
public interface CustomerRewardRoute extends BASERoute {
    String TEST = "/customer/reward/test";
    String PROCESS_CSV_PATH = "/customer/process/csv/path";
    String PROCESS_CSV_UPLOAD = "/customer/process/csv/upload";
    String GET_REWARD_STATS = "/customer/reward/get";
    String DOWNLOAD_CSV = "/customer/reward/download/csv";
}
