CREATE TABLE `sys_user_t` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账号主键id',
  `user_no` varchar(45)  NOT NULL COMMENT '账号',
  `user_cn` varchar(45) NOT NULL COMMENT '中文名',
  `user_en` varchar(45)  NOT NULL COMMENT '英文名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `mobile` varchar(11)  DEFAULT NULL COMMENT '手机号码',
  `mobile_validated` char(1) DEFAULT 'N' COMMENT '手机号码是否验证',
  `email` varchar(100)  DEFAULT NULL COMMENT '邮箱',
  `email_validated` char(1)  DEFAULT 'N' COMMENT '邮箱是否验证',
  `user_type` char(1)  NOT NULL COMMENT '账号类型，1：员工；2：注册用户',
  `user_status` char(1)  NOT NULL DEFAULT 'Y' COMMENT '账号状态，Y：正常；L：锁定；D：删除',
  `password_update_time` datetime DEFAULT NULL COMMENT '密码更新时间',
  `password_error_num` int(2) unsigned DEFAULT NULL COMMENT '密码错误次数',
  `lock_time` datetime DEFAULT NULL COMMENT '锁定时间',
  `enable_flag` char(1) NOT NULL DEFAULT 'Y' COMMENT '有效标识，Y/N',
  `create_by` int(20) NOT NULL COMMENT '创建人,sys_user_t.user_id',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` int(20) NOT NULL COMMENT '最后更新人,sys_user_t.user_id',
  `last_update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_no_UNIQUE` (`user_no`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='系统账号表';

CREATE TABLE `sys_user_detail_t` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_sex` char(1) DEFAULT NULL COMMENT '用户性别',
  `user_photo` bigint(20) DEFAULT NULL COMMENT '用户头像id',
  `enable_flag` char(1) NOT NULL DEFAULT 'Y' COMMENT '有效标识，Y/N',
  `create_by` int(20) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` int(20) NOT NULL COMMENT '最后更新人',
  `last_update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户详情表';

CREATE TABLE `sys_app_user_rt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `app_id` bigint(20) NOT NULL COMMENT '应用id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `enable_flag` char(1) NOT NULL DEFAULT 'Y' COMMENT '有效标识，Y/N',
  `create_by` int(20) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` int(20) NOT NULL COMMENT '最后更新人',
  `last_update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_app_user` (`app_id`,`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用用户关系表';

CREATE TABLE `sys_employee_t` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `employee_no` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '员工工号',
  `employee_type` char(1) NOT NULL COMMENT '员工类型，1：全职；2：兼职；3：试用',
  `employee_status` char(1) NOT NULL COMMENT '员工状态，1：在职；2：离职',
  `employee_job` bigint(20) DEFAULT NULL COMMENT '员工职位',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '直属上级',
  `enable_flag` char(1) NOT NULL DEFAULT 'Y' COMMENT '有效标识，Y/N',
  `create_by` int(20) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` int(20) NOT NULL COMMENT '最后更新人',
  `last_update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `employee_no_UNIQUE` (`employee_no`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4 COMMENT='员工表';

CREATE TABLE `sys_role_t` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_code` varchar(50) NOT NULL COMMENT '角色编码',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `role_desc` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `apply_status` char(1) NOT NULL DEFAULT 'Y' COMMENT '是否可被申请',
  `role_owner` bigint(20) NOT NULL COMMENT '角色责任人',
  `app_code` varchar(32)NOT NULL COMMENT '所属应用',
  `enable_flag` char(1)  NOT NULL DEFAULT 'Y' COMMENT '有效标识，Y/N',
  `create_by` bigint(20) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` bigint(20) NOT NULL COMMENT '最后更新人',
  `last_update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_code_UNIQUE` (`role_code`,`app_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='系统角色表';

CREATE TABLE `sys_permission_t` (
  `permission_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键流水号',
  `permission_code` varchar(50) NOT NULL COMMENT '权限code',
  `permission_desc_cn` varchar(200) NOT NULL COMMENT '中文描述',
  `permission_desc_en` varchar(200)NOT NULL COMMENT '英文描述',
  `permission_type` varchar(20) NOT NULL COMMENT '权限类型，resouces：资源；method：方法',
  `parent_code` varchar(50) DEFAULT '' COMMENT '父级编码',
  `permission_source` varchar(20) NOT NULL COMMENT '权限来源，custom:自定义手动添加；annotation:注解扫描',
  `permission_sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序号',
  `app_code` varchar(50) NOT NULL DEFAULT '' COMMENT '所属应用',
  `app_source` varchar(50) DEFAULT NULL COMMENT '数据来源',
  `enable_flag` char(1)  NOT NULL DEFAULT 'Y' COMMENT '有效标识，Y/N',
  `create_by` int(20) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` int(20) NOT NULL COMMENT '最后更新人',
  `last_update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `uk_permission` (`permission_code`,`permission_type`,`parent_code`,`app_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统权限表';

CREATE TABLE `sys_role_permission_rt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  `enable_flag` char(1) NOT NULL DEFAULT 'Y' COMMENT '有效标识，Y/N',
  `create_by` int(20) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` int(20) NOT NULL COMMENT '最后更新人',
  `last_update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ROLE_PERMISSION` (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系表';

CREATE TABLE `sys_group_t` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键流水号',
  `group_code` varchar(100) NOT NULL COMMENT '群组编码',
  `group_name` varchar(200)  NOT NULL COMMENT '群组名称',
  `group_desc` varchar(1000) DEFAULT NULL COMMENT '群组描述',
  `group_owner` bigint(20) NOT NULL COMMENT '群组责任人',
  `apply_status` char(1) NOT NULL DEFAULT 'N' COMMENT '是否可以申请',
  `app_code` varchar(50) NOT NULL COMMENT '所属应用',
  `enable_flag` char(1) NOT NULL DEFAULT 'Y' COMMENT '有效标识，Y/N',
  `create_by` int(20) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` int(20) NOT NULL COMMENT '最后更新人',
  `last_update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `UK_GROUP_CODE` (`group_code`,`app_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '系统群组表';

CREATE TABLE `sys_app_t` (
  `app_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '应用id',
  `app_code` varchar(50) NOT NULL COMMENT '应用编码',
  `app_name` varchar(100) NOT NULL COMMENT '应用名称',
  `app_desc` varchar(500) DEFAULT NULL COMMENT '应用描述',
  `context_path` varchar(50) DEFAULT NULL COMMENT '应用上下文',
  `app_owner` bigint(20) NOT NULL COMMENT '应用责任人',
  `app_type` char(1) NOT NULL COMMENT '应用类型',
  `app_token` varchar(500) DEFAULT NULL COMMENT '应用静态token',
  `enable_flag` char(1)  NOT NULL DEFAULT 'Y' COMMENT '有效标识，Y/N',
  `create_by` bigint(20) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` bigint(20) NOT NULL COMMENT '最后更新人',
  `last_update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `app_code_UNIQUE` (`app_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='系统应用表';