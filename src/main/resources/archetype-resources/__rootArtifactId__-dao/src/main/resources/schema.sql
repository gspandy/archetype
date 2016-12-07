DROP DATABASE IF EXISTS ${rootArtifactId};

CREATE DATABASE ${rootArtifactId}
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE ${rootArtifactId};

DROP TABLE
IF EXISTS user;

CREATE TABLE user
(
  id            BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  username      VARCHAR(32)                           NOT NULL
  COMMENT '用户名',
  password      VARCHAR(64)                           NOT NULL
  COMMENT '密码',
  salt          VARCHAR(64)                           NOT NULL
  COMMENT '密码盐',
  nick_name     VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '昵称',
  small_avatar  VARCHAR(255)                          NOT NULL                    DEFAULT ''
  COMMENT '小头像',
  medium_avatar VARCHAR(255)                          NOT NULL                    DEFAULT ''
  COMMENT '中头像',
  large_avatar  VARCHAR(255)                          NOT NULL                    DEFAULT ''
  COMMENT '大头像',
  is_locked     TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '是否锁定:{0:未锁定, 1:已锁定}',
  login_time    TIMESTAMP                             NULL                        DEFAULT NULL
  COMMENT '登录时间',
  is_deleted    TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '是否删除:{0:未删除, 1:已删除}',
  created_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '用户表';
CREATE UNIQUE INDEX id_UNIQUE
  ON user (id);
CREATE UNIQUE INDEX username_UNIQUE
  ON user (username);

DROP TABLE
IF EXISTS role;

CREATE TABLE role
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(32)                           NOT NULL
  COMMENT '角色代码',
  name         VARCHAR(32)                           NOT NULL
  COMMENT '角色名称',
  description  VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '角色描述',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '是否删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '角色表';
CREATE UNIQUE INDEX id_UNIQUE
  ON role (id);
CREATE UNIQUE INDEX code_UNIQUE
  ON role (code);

DROP TABLE
IF EXISTS menu;

CREATE TABLE menu
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(32)                           NOT NULL
  COMMENT '菜单代码',
  name         VARCHAR(32)                           NOT NULL
  COMMENT '菜单名称',
  description  VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '菜单描述',
  type         VARCHAR(16)                           NOT NULL
  COMMENT '菜单类型:{dashboard:工作台, admin:控制台}',
  pcode        VARCHAR(32)                           NOT NULL                DEFAULT ''
  COMMENT '父菜单代码',
  url          VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '菜单URL',
  sort         INT(11)                               NOT NULL                DEFAULT 0
  COMMENT '菜单排序(从0开始)',
  icon         VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '菜单图标的样式',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '是否删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '菜单表';
CREATE UNIQUE INDEX id_UNIQUE
  ON menu (id);
CREATE UNIQUE INDEX code_UNIQUE
  ON menu (code);

DROP TABLE
IF EXISTS user_role;

CREATE TABLE user_role
(
  user_id   BIGINT(20)  NOT NULL
  COMMENT '用户ID',
  role_code VARCHAR(32) NOT NULL
  COMMENT '角色代码',
  PRIMARY KEY (user_id, role_code)
)
  COMMENT '用户角色表';

DROP TABLE
IF EXISTS role_menu;

CREATE TABLE role_menu
(
  role_code VARCHAR(32) NOT NULL
  COMMENT '角色ID',
  menu_code VARCHAR(32) NOT NULL
  COMMENT '菜单ID',
  PRIMARY KEY (role_code, menu_code)
)
  COMMENT '角色菜单表';

DROP TABLE
IF EXISTS message;

CREATE TABLE message
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  title        VARCHAR(500)                          NOT NULL                DEFAULT ''
  COMMENT '消息标题',
  content      VARCHAR(500)                          NOT NULL
  COMMENT '消息内容',
  type         VARCHAR(32)                           NOT NULL
  COMMENT '消息类型',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '是否删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '消息表';
CREATE UNIQUE INDEX id_UNIQUE
  ON message (id);

DROP TABLE
IF EXISTS message_user;

CREATE TABLE message_user
(
  id               BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  message_id       BIGINT(20)                            NOT NULL
  COMMENT '消息ID',
  sender_user_id   BIGINT(20)                            NOT NULL
  COMMENT '发送人ID',
  receiver_user_id BIGINT(20)                            NOT NULL
  COMMENT '接收人ID',
  is_read          TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '是否已读:{0:未读, 1:已读}'
)
  COMMENT '消息用户表';

#初始数据
INSERT INTO user
(id, username, password, salt, nick_name)
VALUES
  (1, 'admin', '9606b0029ba4a8c9369f288cced0dc465eb5eabd', '3685072edcf8aad8', '康永敢');

INSERT INTO role
(code, name, description)
VALUES
  ('ROLE_ADMIN', '管理员', '拥有全部权限，可以使用控制台和工作台'),
  ('ROLE_USER', '普通角色', '拥有部分权限，只能使用工作台');

INSERT INTO menu
(code, name, description, type, pcode, url, sort, icon)
VALUES
  ('ADMIN', '控制台', '是所有控制台一级菜单的父菜单，只有管理员才能使用', 'admin', '', 'admin', 0, 'menu-icon fa fa-desktop'),
  ('DASHBOARD', '工作台', '是所有工作台一级菜单的父菜单，所有用户都能使用', 'dashboard', '', 'dashboard', 0, 'menu-icon fa fa-dashboard'),

  ('ADMIN_SYS', '系统', '整个系统的核心控制点', 'admin', 'ADMIN', 'admin/sys', 0, 'menu-icon fa fa-cogs'),
  ('ADMIN_SYS_USER', '用户管理', '管理所有的用户，以及给用户分配角色', 'admin', 'ADMIN_SYS', 'admin/sys/user', 0, ''),
  ('ADMIN_SYS_ROLE', '角色管理', '管理所有的角色，以及给角色分配菜单', 'admin', 'ADMIN_SYS', 'admin/sys/role', 1, ''),
  ('ADMIN_SYS_MENU', '菜单管理', '管理所有的菜单，包括工作台的菜单和控制台的菜单', 'admin', 'ADMIN_SYS', 'admin/sys/menu', 2, ''),
  ('ADMIN_MESSAGE', '消息', '推送系统消息', 'admin', 'ADMIN', 'admin/message', 1, 'menu-icon fa fa-envelope-o'),

  ('DASHBOARD_PROFILE', '我的', '个人相关信息全在此处', 'dashboard', 'DASHBOARD', 'dashboard/profile', 0, 'menu-icon fa fa-user'),
  ('DASHBOARD_PROFILE_AVATAR', '更换头像', '个人的头像', 'dashboard', 'DASHBOARD_PROFILE', 'dashboard/profile/avatar', 0, ''),
  ('DASHBOARD_PROFILE_INFO', '个人资料', '个人的信息资料', 'dashboard', 'DASHBOARD_PROFILE', 'dashboard/profile/info', 1, ''),
  ('DASHBOARD_PROFILE_PASSWORD', '修改密码', '修改密码的地方', 'dashboard', 'DASHBOARD_PROFILE', 'dashboard/profile/password', 2,
   ''),
  ('DASHBOARD_MESSAGE', '消息', '我的所有消息', 'dashboard', 'DASHBOARD', 'dashboard/message', 1, 'menu-icon fa fa-envelope-o');

INSERT INTO user_role VALUES (1, 'ROLE_ADMIN');
INSERT INTO role_menu VALUES
  ('ROLE_USER', 'DASHBOARD'),
  ('ROLE_USER', 'DASHBOARD_PROFILE'),
  ('ROLE_USER', 'DASHBOARD_PROFILE_AVATAR'),
  ('ROLE_USER', 'DASHBOARD_PROFILE_INFO'),
  ('ROLE_USER', 'DASHBOARD_PROFILE_PASSWORD'),
  ('ROLE_USER', 'DASHBOARD_MESSAGE');
INSERT INTO role_menu SELECT
                        'ROLE_ADMIN',
                        code
                      FROM menu;