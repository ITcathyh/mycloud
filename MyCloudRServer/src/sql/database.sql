﻿CREATE DATABASE mycloud;
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
  points       INT                  DEFAULT 100
  COMMENT '积分',
  ip           VARCHAR(15) NOT NULL
  COMMENT 'IP地址',
  logintime    TIMESTAMP            DEFAULT current_timestamp
  COMMENT '上次登录时间',
  PRIMARY KEY (id),
  INDEX (email),
  INDEX (password)
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
  PRIMARY KEY (id),
  INDEX (invitationcode)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE file (
  id         BIGINT       NOT NULL AUTO_INCREMENT
  COMMENT '动态的id',
  userid     BIGINT       NOT NULL
  COMMENT '上传者id',
  filename   VARCHAR(30)  NOT NULL
  COMMENT '文件名',
  originname VARCHAR(50)  NOT NULL
  COMMENT '原始文件名',
  downloads  INT                   DEFAULT 0
  COMMENT '下载次数',
  size       INT          NOT NULL
  COMMENT '文件大小(单位kb)',
  type       TINYINT               DEFAULT 0
  COMMENT '类别,0其他，1书籍，2课件，3代码',
  summary    VARCHAR(150) NOT NULL
  COMMENT '简介',
  path       VARCHAR(100) NOT NULL
  COMMENT '下载链接',
  tag        VARCHAR(20) COMMENT '标签',
  PRIMARY KEY (id),
  INDEX (userid),
  INDEX (size),
  INDEX (tag),
  INDEX (filename),
  INDEX (summary)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE admin (
  id        BIGINT    AUTO_INCREMENT
  COMMENT '动态的id',
  username  VARCHAR(12) NOT NULL
  COMMENT '账号',
  password  VARCHAR(16) NOT NULL
  COMMENT '密码',
  authority TINYINT   DEFAULT 0
  COMMENT '权限',
  ip        VARCHAR(15)
  COMMENT 'IP地址',
  logintime TIMESTAMP DEFAULT current_timestamp
  COMMENT '上次登录时间',
  PRIMARY KEY (id),
  INDEX (username),
  INDEX (password)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO admin (username, password) VALUES ("1", "1");

CREATE TABLE userrecord (
  id       BIGINT    AUTO_INCREMENT
  COMMENT '动态的id',
  userid   BIGINT  NOT NULL
  COMMENT '用户id',
  tag      VARCHAR(20)
  COMMENT '标签',
  type     TINYINT NOT NULL
  COMMENT '类别,0其他，1书籍，2课件，3代码',
  time     INT       DEFAULT 1
  COMMENT '次数',
  lasttime TIMESTAMP DEFAULT current_timestamp
  COMMENT '最后记录时间',
  PRIMARY KEY (id),
  INDEX (userid),
  INDEX (type),
  INDEX (tag)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE advertisement (
  id       TINYINT AUTO_INCREMENT
  COMMENT '动态的id',
  href     VARCHAR(100) NOT NULL
  COMMENT '跳转页',
  imgpath  VARCHAR(100) NOT NULL
  COMMENT '图片路径',
  deadline BIGINT       NOT NULL
  COMMENT '截止时间',
  INDEX (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE userfile (
  userid BIGINT
  COMMENT '用户id',
  fileid BIGINT
  COMMENT '文件id',
  INDEX (userid),
  INDEX (fileid)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into user(email, password, name, qq, invitationid, ip) values('1', '1', '1', '1', '1', '1');
nsert into file (userid, filename, originname, summary, downloads, size, type,path, tag ) values (1, 'testmehello', ?, 'testmehello', 0, 100, 1, 'null', 'testmehello' )