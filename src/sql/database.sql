CREATE DATABASE mycloud;
USE mycloud;

CREATE TABLE user (
  id           BIGINT      NOT NULL AUTO_INCREMENT
  COMMENT '动态的id',
  email        VARCHAR(30) NOT NULL
  COMMENT '邮箱',
  password     VARCHAR(16) NOT NULL
  COMMENT '密码',
  name         VARCHAR(12) NOT NULL
  COMMENT '昵称',
  qq           VARCHAR(15) NOT NULL
  COMMENT 'qq',
  invitationid BIGINT      NOT NULL
  COMMENT '邀请码对应id',
  surplus      INT                  DEFAULT 32505856
  COMMENT '剩余上传空间(单位kb)',
  points       TINYINT              DEFAULT 100
  COMMENT '积分',
  ip           VARCHAR(15) NOT NULL
  COMMENT 'IP地址',
  logintime    TIMESTAMP            DEFAULT current_timestamp
  COMMENT '上次登录时间',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE invitationcode (
  id             BIGINT      NOT NULL AUTO_INCREMENT
  COMMENT '动态的id',
  invitationcode VARCHAR(20) NOT NULL
  COMMENT '邀请码',
  surplus        TINYINT              DEFAULT 30
  COMMENT '剩余激活次数',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE file (
  id        BIGINT       NOT NULL AUTO_INCREMENT
  COMMENT '动态的id',
  userid    BIGINT       NOT NULL
  COMMENT '上传者id',
  filename  VARCHAR(30)  NOT NULL
  COMMENT '文件名',
  originname  VARCHAR(50)  NOT NULL
  COMMENT '原始文件名',
  downloads INT                   DEFAULT 0
  COMMENT '下载次数',
  size      INT          NOT NULL
  COMMENT '文件大小(单位kb)',
  type      TINYINT               DEFAULT 0
  COMMENT '类别,0其他，1书籍，2课件，3代码',
  summary   VARCHAR(150) NOT NULL
  COMMENT '简介',
  path      VARCHAR(100)  NOT NULL
  COMMENT '下载链接',
  tag       VARCHAR(20) COMMENT '标签',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO file (userid, filename, downloads, size, type, summary, path)
VALUES (123, "test", 1, 2144, 1, "asdbb", "books/java");

