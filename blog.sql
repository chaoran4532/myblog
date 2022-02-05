use blog;
CREATE TABLE `t_user`
(
    `user_id`       INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'primary_key',
    `user_name`     VARCHAR(20)      NOT NULL COMMENT 'username',
    `user_password` VARCHAR(20)      NOT NULL COMMENT 'password',
    PRIMARY KEY (`user_id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE `t_category`
(
    `id`           INT(11)     NOT NULL AUTO_INCREMENT,
    `categoryName` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;



CREATE TABLE `t_tag`
(
    `id`  INT(11)     DEFAULT NULL AUTO_INCREMENT,
    `tag` VARCHAR(30) DEFAULT NULL,
    KEY `id` (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
CREATE TABLE `t_article`
(
    `id`          INT(11)     NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(80) NOT NULL,
    `author_id`   INT(11)     UNSIGNED NOT NULL,
    `category_id` INT(11)     NOT NULL,
    `time`        DATETIME DEFAULT '2021-12-1 00:00:00',
    `star`        INT(11)  DEFAULT '0',
    `comment`     INT(11)  DEFAULT '0',
    `visit`       INT(11)  DEFAULT '0',
    `content`     TEXT,
    PRIMARY KEY (`id`),
    CONSTRAINT `author_id` FOREIGN KEY (`author_id`) REFERENCES `t_user` (`user_id`)ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `t_category` (`id`)ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
# CREATE TABLE `t_article_delet`
# (
#     `id`          INT(11)     NOT NULL AUTO_INCREMENT,
#     `title`       VARCHAR(80) NOT NULL,
#     `author_id`   INT(11)     UNSIGNED NOT NULL,
#     `category_id` INT(11)     NOT NULL,
#     `time`        DATETIME DEFAULT '2021-12-1 00:00:00',
#     `star`        INT(11)  DEFAULT '0',
#     `comment`     INT(11)  DEFAULT '0',
#     `visit`       INT(11)  DEFAULT '0',
#     `content`     TEXT,
#     PRIMARY KEY (`id`),
#     CONSTRAINT `author_id` FOREIGN KEY (`author_id`) REFERENCES `t_user` (`user_id`)ON DELETE CASCADE ON UPDATE NO ACTION,
#     CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `t_category` (`id`)ON DELETE CASCADE ON UPDATE NO ACTION
# ) ENGINE = INNODB
#   DEFAULT CHARSET = utf8;


CREATE TABLE `t_comment`
(
    `id`         INT(11) NOT NULL AUTO_INCREMENT,
    `article_id` INT(11)     DEFAULT NULL,
    `nickname`   VARCHAR(30) DEFAULT NULL,
    `content`    TEXT,
    `time`       DATETIME    DEFAULT '2021-12-1 00:00:00',
    `star`       INT(11)     DEFAULT '0',
    `diss`       INT(11)     DEFAULT '0',
    PRIMARY KEY (`id`),
    KEY `article_id` (`article_id`),
    CONSTRAINT `article_id` FOREIGN KEY (`article_id`) REFERENCES `t_article` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = INNODB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8;
CREATE TABLE `article_tag`
(
    `id`         INT(11) NOT NULL AUTO_INCREMENT,
    `article_id2` INT(11) NOT NULL,
    `tag_id`     INT(11) NOT NULL,
    primary key (`id`),
    CONSTRAINT `article_id2` FOREIGN KEY (`article_id2`) REFERENCES `t_article` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `tag_id` FOREIGN KEY (`tag_id`) REFERENCES `t_tag` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE `t_visitor`
(
    `id`     INT(11) NOT NULL AUTO_INCREMENT,
    `ip`     VARCHAR(50) DEFAULT NULL,
    `time`   VARCHAR(50) DEFAULT NULL,
    `web_ip` VARCHAR(50) DEFAULT NULL,
    `host`   VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 52
  DEFAULT CHARSET = utf8;