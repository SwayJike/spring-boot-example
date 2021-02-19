-- 系统验证码
DROP TABLE IF EXISTS sys_captcha;
CREATE TABLE `sys_captcha` (
    `uuid`        CHAR(36)   NOT NULL COMMENT 'uuid',
    `captcha`     VARCHAR(6) NOT NULL COMMENT '验证码',
    `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
    PRIMARY KEY (`uuid`)
) ENGINE = `InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT = '系统验证码';