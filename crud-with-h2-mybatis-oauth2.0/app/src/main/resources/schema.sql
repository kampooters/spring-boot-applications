
DROP TABLE IF EXISTS `person_table` cascade;
CREATE TABLE person_table (
                            `first_name` varchar(100) DEFAULT NULL,
                            `last_name` varchar(100) DEFAULT NULL,
                            `age` int(11) DEFAULT NULL,
                            `favourite_color` varchar(100) DEFAULT NULL,
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `hobby` cascade;
CREATE TABLE hobby (
                            `name` varchar(100) DEFAULT NULL,
                            `person_id` int(11) DEFAULT NULL,
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `oauth_client` cascade;
CREATE TABLE `oauth_client` (
                              `client_id` varchar(250) NOT NULL,
                              `secret` varchar(250) DEFAULT NULL,
                              `owner` varchar(250) DEFAULT NULL,
                              PRIMARY KEY (`client_id`)
);


DROP TABLE IF EXISTS `oauth_token` cascade;
CREATE TABLE oauth_token (`client_id` VARCHAR(250) NOT NULL,
                           `access_token` VARCHAR(250) NULL,
                           `refresh_token` VARCHAR(250) NULL,
                           `authorize_token` VARCHAR(250) NULL,
                           `callback_url` VARCHAR(250) NULL,
                           `access_token_creation_time` bigint(20) NULL,
                           PRIMARY KEY (`client_id`));




