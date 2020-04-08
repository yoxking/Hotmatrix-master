

-- ----------------------------
-- 1、分支信息表
-- ----------------------------
drop table if exists sys_branchinfo;
create table sys_branchinfo (
  id				bigint(20)      not null auto_increment    comment 'id',
  branch_no         varchar(20)     not null 				   comment '分支id',
  branch_name       varchar(200)    default ''                 comment '分支名称',
  branch_code       varchar(50)     default ''                 comment '分支编码',
  branch_type       char(2)         default '00'         	   comment '分支类型',
  order_no          int(4)          default 0                  comment '显示顺序', 
  master            varchar(50)     default ''               comment '负责人',
  telephone         varchar(11)     default ''               comment '联系电话',
  email             varchar(50)     default ''               comment '邮箱',
  summary           varchar(2000)   default ''               comment '简介',
  check_state       char(1)         default '1'                comment '状态（1正常 0停用）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)    default ''                 comment '更新者',
  app_code          varchar(256)    default ''                 comment '应用编码',
  version           bigint(20)      default 0                  comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_branchno (`branch_no`)
) engine=innodb auto_increment=100 comment = '分支信息表';

-- ----------------------------
-- 2、部门信息表
-- ----------------------------
drop table if exists sys_department;
create table sys_department (
  id				bigint(20)      not null auto_increment    comment 'id',
  dept_no           varchar(20)     not null 				   comment '部门id',
  dept_name         varchar(200)    default ''                 comment '部门名称',
  parent_no         varchar(20)     default '0'  		       comment '父部门id',
  order_no          int(4)          default 0                  comment '显示顺序',
  ancestors         varchar(50)     default ''                 comment '祖级列表',
  leader            varchar(50)     default ''               comment '负责人',
  telephone         varchar(11)     default ''               comment '联系电话',
  email             varchar(50)     default ''               comment '邮箱',
  check_state       char(1)         default '1'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_deptno (`dept_no`)
) engine=innodb auto_increment=100 comment = '部门信息表';


-- ----------------------------
-- 3、机构信息表
-- ----------------------------
drop table if exists sys_organizinfo;
create table sys_organizinfo (
  id				bigint(20)      not null auto_increment    comment 'id',
  organiz_no        varchar(20)     not null 				   comment '部门id',
  organiz_name      varchar(200)    default ''                 comment '部门名称',
  parent_no         varchar(20)     default '0'  		       comment '父部门id',
  order_no          int(4)          default 0                  comment '显示顺序',
  check_state       char(1)         default '0'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_organizno (`organiz_no`)
) engine=innodb auto_increment=100 comment = '机构信息表';
 

-- ----------------------------
-- 4、系统用户信息表
-- ----------------------------
drop table if exists sys_suserinfo;
create table sys_suserinfo (
  id				bigint(20)      not null auto_increment    comment 'id',
  user_no           varchar(20)     not null                   comment '用户ID',
  dept_no           varchar(20)     default ''    	           comment '部门ID',
  orgz_no           varchar(20)     default ''  	             comment '机构ID',
  login_name        varchar(50)     default ''                  comment '登录账号',
  password          varchar(256)     default ''                 comment '密码',
  user_cnname       varchar(200)    default ''                   comment '中文用户昵称',
  user_enname       varchar(200)    default ''                  comment '英文用户昵称',
  user_type         varchar(2)      default '00'               comment '用户类型（00系统用户）',
  telephone         varchar(11)     default ''                 comment '手机号码',
  email             varchar(50)     default ''                 comment '用户邮箱',
  sex               char(1)         default '0'                comment '用户性别（0女 1男  2未知）',
  avatar            varchar(100)    default ''                 comment '头像路径',
  salt              varchar(20)     default ''                 comment '盐加密',
  login_ip          varchar(50)     default ''                 comment '最后登陆IP',
  login_date        datetime                                   comment '最后登陆时间',
  check_state       char(1)         default '0'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_suser_no (`user_no`)
) engine=innodb auto_increment=100 comment = '系统用户信息表';


-- ----------------------------
-- 5、注册用户信息表
-- ----------------------------
drop table if exists sys_ruserinfo;
create table sys_ruserinfo (
  id				bigint(20)      not null auto_increment    comment 'id',
  user_no           varchar(20)     not null                   comment '用户ID',
  dept_no           varchar(20)     default ''    	           comment '部门ID',
  orgz_no           varchar(20)     default ''  	             comment '机构ID',
  login_name        varchar(50)     default ''                  comment '登录账号',
  password          varchar(256)     default ''                 comment '密码',
  user_cnname       varchar(200)    default ''                   comment '中文用户昵称',
  user_enname       varchar(200)    default ''                  comment '英文用户昵称',
  user_type         varchar(2)      default '00'               comment '用户类型（00系统用户）',
  telephone         varchar(11)     default ''                 comment '手机号码',
  email             varchar(50)     default ''                 comment '用户邮箱',
  sex               char(1)         default '0'                comment '用户性别（0女 1男  2未知）',
  avatar            varchar(100)    default ''                 comment '头像路径',
  salt              varchar(20)     default ''                 comment '盐加密',
  login_ip          varchar(50)     default ''                 comment '最后登陆IP',
  login_date        datetime                                   comment '最后登陆时间',
  check_state       char(1)         default '0'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_ruser_no (`user_no`)
) engine=innodb auto_increment=100 comment = '注册用户信息表';


-- ----------------------------
-- 6、岗位信息表
-- ----------------------------
drop table if exists sys_postinfo;
create table sys_postinfo
(
  id            	bigint(20)      not null auto_increment    		comment 'ID',
  post_no       	varchar(20)     not null                   		comment '岗位ID',
  post_name     	varchar(200)    default ''                    	comment '岗位名称',
  post_code     	varchar(50)     default ''                  		comment '岗位编码',
  order_no      	int(4)          default 0                   		comment '显示顺序',
  check_state       char(1)         default '0'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_postno (`post_no`)
) engine=innodb auto_increment=100 comment = '岗位信息表';
 
-- ----------------------------
-- 7、角色信息表
-- ----------------------------
drop table if exists sys_roleinfo;
create table sys_roleinfo (
  id                bigint(20)      not null auto_increment    comment 'ID',
  role_no           varchar(20)     not null                   comment '角色ID',
  role_name         varchar(50)     default ''                   comment '角色名称',
  role_code         varchar(100)    default ''                   comment '角色权限字符串',
  order_no          int(4)          default 0                   comment '显示顺序',
  data_scope        char(1)         default '1'                comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  check_state       char(1)         default '0'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_roleno (`role_no`)
) engine=innodb auto_increment=100 comment = '角色信息表';
 
 
-- ----------------------------
-- 8、菜单权限表
-- ----------------------------
drop table if exists sys_permitinfo;
create table sys_permitinfo (
  id                bigint(20)      not null auto_increment      comment 'ID',
  permit_no         varchar(20)      not null                  comment '菜单ID',
  permit_name       varchar(50)     default ''                   comment '菜单名称',
  permit_type       char(1)         default ''                 comment '菜单类型（M目录 C菜单 F按钮）',
  parent_no         varchar(20)      default '0' 		       comment '父菜单ID',
  order_no          int(4)          default 0                  comment '显示顺序',
  url               varchar(200)    default '#'                comment '请求地址',
  icon              varchar(200)    default '#'                comment '菜单图标',
  target            varchar(20)     default ''                 comment '打开方式（menuItem页签 menuBlank新窗口）',
  permits           varchar(200)    default ''              comment '权限标识',
  visible           char(1)         default '0'                  comment '显示状态（0显示 1隐藏）',
  check_state       char(1)         default '0'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_permitno (`permit_no`)
) engine=innodb auto_increment=100 comment = '菜单权限表';
 

-- ----------------------------
-- 9、用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_suserrole;
create table sys_suserrole (
  id                bigint(20)      not null auto_increment    comment 'ID',
  user_no           varchar(20)		not null 					comment '用户ID',
  role_no           varchar(20) 	not null 					comment '角色ID', 
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key(id)
) engine=innodb auto_increment=100 comment = '用户和角色关联表';
 


-- ----------------------------
-- 10、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_rolepermit;
create table sys_rolepermit (
  id                bigint(20)      not null auto_increment    comment 'ID',
  role_no   		varchar(20) 	not null 					comment '角色ID',
  permit_no   		varchar(20) 	not null 					comment '菜单ID', 
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号', 
  primary key(id)
) engine=innodb auto_increment=100 comment = '角色和菜单关联表';
 

-- ----------------------------
-- 11、角色和部门关联表  角色1-N部门
-- ----------------------------
drop table if exists sys_roledept;
create table sys_roledept (
  id                bigint(20)      not null auto_increment    comment 'ID',
  role_no   		varchar(20) 	not null 					comment '角色ID',
  dept_no   		varchar(20) 	not null 					comment '部门ID', 
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key(id)
) engine=innodb auto_increment=100 comment = '角色和部门关联表';
 

-- ----------------------------
-- 12、用户与岗位关联表  用户1-N岗位
-- ----------------------------
drop table if exists sys_suserpost;
create table sys_suserpost
(
  id                bigint(20)      not null auto_increment    comment 'ID',
  user_no   		varchar(20) 	not null 					comment '用户ID',
  post_no   		varchar(20) 	not null 					comment '岗位ID', 
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id)
) engine=innodb auto_increment=100 comment = '用户与岗位关联表';
 


-- ----------------------------
-- 13、操作日志记录
-- ----------------------------
drop table if exists sys_operatelogs;
create table sys_operatelogs (
  id                bigint(20)      not null auto_increment    comment 'ID',
  oplog_no          varchar(20)      not null     				comment '日志主键', 
  oplog_type        char(1)         default '1'                  comment '业务类型（0其它 1新增 2修改 3删除）',
  title             varchar(50)     default ''                 comment '模块标题',
  method_name       varchar(100)    default ''                 comment '方法名称',
  request_type      varchar(10)     default ''                 comment '请求方式',
  opert_type        char(1)         default '1'                  comment '操作类别（0其它 1后台用户 2手机端用户）',
  opert_name         varchar(50)     default ''                 comment '操作人员',
  dept_name         varchar(50)     default ''                 comment '部门名称',
  opert_url          varchar(255)    default ''                 comment '请求URL',
  opert_ip           varchar(50)     default ''                 comment '主机地址',
  opert_location     varchar(255)    default ''                 comment '操作地点',
  opert_params        varchar(2000)   default ''                 comment '请求参数',
  json_result       varchar(2000)   default ''                 comment '返回参数',
  opert_status      char(1)         default '1'                  comment '操作状态（0正常 1异常）',
  error_msg         varchar(2000)   default ''                 comment '错误消息',
  opert_time         datetime                                   comment '操作时间',
  check_state       char(1)         default '1'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_oplogno (`oplog_no`)
) engine=innodb auto_increment=100 comment = '操作日志记录';


-- ----------------------------
-- 14、字典类型表
-- ----------------------------
drop table if exists sys_dictclass;
create table sys_dictclass
(
  id                bigint(20)      not null auto_increment    comment 'ID',
  class_no          varchar(20)      not null     				comment '字典主键',
  class_name        varchar(100)    default ''                 comment '字典名称',
  dict_type         varchar(20)    default ''                 comment '字典类型', 
  order_no          int(4)          default 0                  comment '显示顺序',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_dclass_no (`class_no`)
) engine=innodb auto_increment=100 comment = '字典类型表';
 

-- ----------------------------
-- 15、字典数据表
-- ----------------------------
drop table if exists sys_dictinfo;
create table sys_dictinfo
(
  id                bigint(20)      not null auto_increment    comment 'ID',
  dict_no          varchar(20)      not null     				comment '字典主键',
  dict_code        varchar(50)      default '' 				    comment '字典编码',
  dict_type        char(2)     		default '00'                 comment '字典类别',
  class_no          varchar(20)     default ''     				comment '字典类型',
  order_no         int(4)          default 0                  comment '字典排序',
  dict_label       varchar(100)    default ''                 comment '字典标签',
  dict_value       varchar(100)    default ''                 comment '字典键值',
  css_class        varchar(100)    default ''               comment '样式属性（其他样式扩展）',
  list_class       varchar(100)    default ''               comment '表格回显样式',
  is_default       char(1)         default 'N'                comment '是否默认（Y是 N否）',
  check_state       char(1)         default '1'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_dictno (`dict_no`)
) engine=innodb auto_increment=100 comment = '字典数据表';
 


-- ----------------------------
-- 16、参数配置表
-- ----------------------------
drop table if exists sys_configinfo;
create table sys_configinfo (
  id                bigint(20)      not null auto_increment    comment 'ID',
  config_no         varchar(20)     not null    				 comment '参数主键',
  config_name       varchar(100)    default ''                 comment '参数名称',
  config_key        varchar(100)    default ''                 comment '参数键名',
  config_value      varchar(500)    default ''                 comment '参数键值',
  config_type       char(1)         default 'N'                comment '系统内置（Y是 N否）',
  check_state       char(1)         default '1'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_configno (`config_no`)
) engine=innodb auto_increment=100 comment = '参数配置表';
 
 


-- ----------------------------
-- 17、定时任务调度表
-- ----------------------------
drop table if exists sys_taskinfo;
create table sys_taskinfo (
  id                bigint(20)      not null auto_increment    comment 'ID',
  task_no             varchar(20)    not null 	   				 comment '任务ID',
  task_name            varchar(64)   default ''                 comment '任务名称',
  task_group           varchar(64)   default 'DEFAULT'          comment '任务组名',
  invoke_target       varchar(500)  default ''                   comment '调用目标字符串',
  cron_expression     varchar(500)  default ''                 comment 'cron执行表达式',
  misfire_policy      char(1)       default '3'                comment '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  concurrent          char(1)       default '1'                comment '是否并发执行（0允许 1禁止）',
  check_state       char(1)         default '1'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_taskno (`task_no`)
) engine=innodb auto_increment=100 comment = '定时任务调度表';
 

-- ----------------------------
-- 18、定时任务调度日志表
-- ----------------------------
drop table if exists sys_tasklogs;
create table sys_tasklogs (
  id                bigint(20)      not null auto_increment    comment 'ID',
  task_logno          varchar(20)     not null                 comment '任务日志ID',
  task_name            varchar(64)    default ''                   comment '任务名称',
  task_group           varchar(64)    default ''                    comment '任务组名',
  invoke_target       varchar(500)   default ''                    comment '调用目标字符串',
  task_message         varchar(500)    default ''                 comment '日志信息',
  result_status       char(1)        default '0'                comment '执行状态（0正常 1失败）',
  exception_info      varchar(2000)  default ''                 comment '异常信息',
  check_state       char(1)         default '1'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_tasklogno (`task_logno`)
) engine=innodb auto_increment=100 comment = '定时任务调度日志表';


-- ----------------------------
-- 19、内容类型表
-- ----------------------------
drop table if exists sys_contzclass;
create table sys_contzclass (
  id                bigint(20)      not null auto_increment    comment 'ID',
  class_no        varchar(20)       not null                   comment '类型ID',
  class_name      varchar(200)      default ''                   comment '类型名称',  
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_classno (`class_no`)
) engine=innodb auto_increment=100 comment = '内容类型表';

-- ----------------------------
-- 20、内容信息表
-- ----------------------------
drop table if exists sys_contentinfo;
create table sys_contentinfo (
  id               bigint(20)      not null auto_increment    comment 'ID',
  contz_no         varchar(20)     not null   				  comment '内容ID',
  class_no         varchar(20)     default ''   				  comment '类型ID',
  title            varchar(500)    default ''                   comment '标题', 
  author           varchar(50)     default ''                   comment '作者',
  pubdate 	       datetime                                   comment '发布时间',
  poster 	       varchar(500)    default ''                            comment '海报',
  abstracts 	    varchar(2000)   default ''                            comment '简介',
  ncontents         text                                       comment '正文内容',
  hit_count         int(8)         default 0                    comment '点击次数',
  check_state       char(1)         default '1'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_contzno (`contz_no`)
) engine=innodb auto_increment=100 comment = '内容信息表';
 

-- ----------------------------
-- 21、消息信息表
-- ----------------------------
drop table if exists sys_messageinfo;
create table sys_messageinfo (
  id                bigint(20)      	not null auto_increment    comment 'ID',
  mssg_no           varchar(20)          not null     			comment '消息id',
  mssg_type         char(2)		        default '00'      			comment '消息类型',
  mreceiver         varchar(20)         default ''     			comment '接收人',
  msender           varchar(20)         default ''      			comment '发送人', 
  mtitle      		varchar(500)     default ''                   comment '消息标题',  
  mcontent    		varchar(2000)   default ''               comment '消息内容',
  attachfile    	varchar(2000)   default ''               comment '附件',
  read_state        char(1)         default '1'  				comment '阅读状态（1已阅 0未阅）',
  receive_time 	    datetime                                   comment '接收时间', 
  send_time 	    datetime                                   comment '发送时间', 
  check_state       char(1)         default '1'                comment '状态（1正常 0停用）',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_mssgno (`mssg_no`)
) engine=innodb auto_increment=100 comment = '消息信息表';
 
 
-- ----------------------------
-- 22、代码生成业务表
-- ----------------------------
drop table if exists sys_tableinfo;
create table sys_tableinfo (
  id                bigint(20)      not null auto_increment    comment 'ID',
  table_no          varchar(20)      not null     				comment '编号',
  table_name        varchar(200)    default ''                 comment '表名称',
  table_remark      varchar(500)    default ''                 comment '表描述',
  class_name        varchar(100)    default ''                 comment '实体类名称',
  tpl_category      varchar(200)    default 'crud'             comment '使用的模板（crud单表操作 tree树表操作）',
  package_name      varchar(100)     default ''                          comment '生成包路径',
  module_name       varchar(30)     default ''                           comment '生成模块名',
  business_name     varchar(30)     default ''                           comment '生成业务名',
  function_name     varchar(50)    default ''                            comment '生成功能名',
  function_author   varchar(50)     default ''                           comment '生成功能作者',
  options           varchar(1000)    default ''                          comment '其它生成选项',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_tableno (`table_no`)
) engine=innodb auto_increment=100 comment = '代码生成业务表';


-- ----------------------------
-- 23、代码生成业务表字段
-- ----------------------------
drop table if exists sys_tablefield;
create table sys_tablefield (
  id                bigint(20)      not null auto_increment    comment 'ID',
  field_no         varchar(20)      not null     				comment '字段编号',
  table_no          varchar(20)      default ''                          comment '归属表编号',
  field_name       varchar(200)      default ''                         comment '字段名称',
  field_remark     varchar(500)     default ''                          comment '字段描述',
  field_type       varchar(100)     default ''                          comment '字段类型',
  java_type         varchar(500)     default ''                          comment 'JAVA类型',
  java_field        varchar(200)     default ''                          comment 'JAVA字段名',
  is_pk             char(1)         default '0'                           comment '是否主键（1是）',
  is_increment      char(1)         default '0'                           comment '是否自增（1是）',
  is_required       char(1)         default '0'                           comment '是否必填（1是）',
  is_insert         char(1)         default '0'                           comment '是否为插入字段（1是）',
  is_edit           char(1)         default '0'                           comment '是否编辑字段（1是）',
  is_list           char(1)         default '0'                           comment '是否列表字段（1是）',
  is_query          char(1)          default '0'                          comment '是否查询字段（1是）',
  query_type        varchar(200)    default 'EQ'               comment '查询方式（等于、不等于、大于、小于、范围）',
  html_type         varchar(200)     default ''                          comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  dict_type         varchar(200)    default ''                 comment '字典类型',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_fieldno (`field_no`)
) engine=innodb auto_increment=100 comment = '代码生成业务表字段';


-- ----------------------------
-- 24、应用类型表
-- ----------------------------
drop table if exists sys_appclass;
create table sys_appclass
(
  id                bigint(20)      not null auto_increment    comment 'ID',
  class_no          varchar(20)      not null     				comment '类型id',
  class_name        varchar(100)    default ''                 comment '类型名称',  
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_aclassno (`class_no`)
) engine=innodb auto_increment=100 comment = '应用类型表';

-- ----------------------------
-- 25、应用信息表
-- ----------------------------
drop table if exists sys_appinfo;
create table sys_appinfo (
  id				bigint(20)      not null auto_increment    comment 'id',
  app_no            varchar(20)     not null 					   comment '分支编号',
  app_cnname         varchar(200)    default ''                 comment '应用中文名称',
  app_enname         varchar(200)    default ''                 comment '应用英文名称',
  app_code         varchar(256)     default ''                comment '应用编码',
  class_no         varchar(20)         default '00'       		  comment '类型编号',
  order_no          int(4)          default 0                  comment '显示顺序', 
  app_url            varchar(200)     default ''                comment 'Url',
  app_ver           varchar(50)     default ''               comment '版本', 
  email             varchar(50)     default ''                comment '邮箱',
  summary           varchar(500)     default ''                comment '简介',
  edog_no         varchar(50)     default '0'               comment '加密狗id', 
  edog_type         char(2)       default '00'               comment '加密狗类型', 
  regist_date      datetime                                   comment '注册时间', 
  active_date      datetime                                   comment '有效时间', 
  active_count      int             default 0                   comment '授权数量', 
  active_code      varchar(128)     default ''               comment '授权码', 
  check_state       char(1)         default '1'                comment '状态（1正常 0停用）', 
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者', 
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_appno (`app_no`)
) engine=innodb auto_increment=100 comment = '应用信息表';



-- ----------------------------
-- 26、系统访问记录
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor (
  id			 bigint(20)      not null auto_increment    comment 'id',
  login_no        bigint(20)     not null    				comment '访问ID',
  login_name     varchar(50)    default ''                comment '登录账号',
  ipaddr         varchar(50)    default ''                comment '登录IP地址',
  location 		varchar(255)   default ''                comment '登录地点',
  browser        varchar(50)    default ''                comment '浏览器类型',
  os             varchar(50)    default ''                comment '操作系统',
  status         char(1)        default '0'               comment '登录状态（0成功 1失败）',
  message            varchar(255)   default ''                comment '提示消息',
  login_time     datetime                                 comment '访问时间',
  branch_no         varchar(20)     default ''				   comment '分支编号',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)     default ''                comment '更新者',
  app_code          varchar(256)     default ''                comment '应用编码',
  version           bigint(20)       default 0                 comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_loginno (`login_no`)
) engine=innodb auto_increment=100 comment = '系统访问记录';
