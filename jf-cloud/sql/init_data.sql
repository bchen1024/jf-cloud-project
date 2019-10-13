alter table sys_user_t AUTO_INCREMENT=1000;
alter table sys_employee_t AUTO_INCREMENT=10000;

-- 插入用户
INSERT INTO `jf-cloud-platform`.`sys_user_t`
(`user_no`,`user_cn`,`user_en`,`password`,`user_type`,`user_status`,`create_by`,`last_update_by`)
VALUES
('admin','管理员','Administrators','S3AOX40LQ1P2CG4EK37GAN2NBLW39O1DM67N1FIZ29KX5FMZ7C','1','Y',1000,1000);

-- 获取最大的用户id
select @userId:=ifnull((user_id),1000) from sys_user_t;

-- 插入角色
INSERT INTO `jf-cloud-platform`.`sys_role_t`
(`role_code`,`role_name`,`role_owner`,`app_code`,`create_by`,`last_update_by`)
VALUES
('superAdmin','超级管理员',@userId,'jf-cloud-platform',@userId,@userId);

-- 插入应用
INSERT INTO `jf-cloud-platform`.`sys_app_t`
(`app_code`,`app_name`,`app_owner`,`app_type`,`create_by`,`last_update_by`)
VALUES
('jf-cloud-platform','JF Cloud管理平台',@userId,'2',@userId,@userId);


-- 插入应用用户
-- 获取最大的角色id
select @roleId:=ifnull((role_id),1) from sys_role_t;
-- 获取最大的应用id
select @appId:=ifnull((app_id),1) from sys_app_t;
INSERT INTO `jf-cloud-platform`.`sys_app_user_rt`
(`app_id`,`role_id`,`user_id`,`create_by`,`last_update_by`)
VALUES
(@appId,@roleId,@userId,@userId,@userId);

-- 插入角色权限关系
INSERT INTO `jf-cloud-platform`.`sys_role_permission_rt`
(`role_id`,`permission_id`,`create_by`,`last_update_by`)
select 1,permission_id,1000,1000 from sys_permission_t;




