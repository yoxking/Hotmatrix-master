/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.25-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

drop table if exists cct_questclass;
create table cct_questclass (
  id				bigint(20)      not null auto_increment    comment 'id',
  class_no          varchar(20)     not null 				   comment '类型id',
  class_name        varchar(200)    default ''                 comment '类型名称',
  parent_no         varchar(20)     default '1' 			   comment '上级类型',
  order_no          int(4)          default 0                  comment '显示顺序', 
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
  UNIQUE INDEX idx_classno (`class_no`)
) engine=innodb auto_increment=100 comment = '测题类型表'; 

drop table if exists cct_questinfo;
create table cct_questinfo (
  id				bigint(20)      not null auto_increment    comment 'id',
  quest_no          varchar(20)     not null 				   comment '试题id',
  quest_title       varchar(200)    default ''                 comment '试题名称',
  quest_type        char(1)         default '0'         	   comment '试题类别',
  quest_desc        varchar(2000)   default ''                 comment '试题描述',
  class_no          varchar(20)     default ''                 comment '试题类型',
  order_no          int(4)          default 0                  comment '显示顺序', 
  quest_score       double          default 0                  comment '试题总分',
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
  UNIQUE INDEX idx_questno (`quest_no`)
) engine=innodb auto_increment=100 comment = '测评题库表'; 

drop table if exists cct_questopts;
create table cct_questopts (
  id				bigint(20)      not null auto_increment    comment 'id',
  opt_no            varchar(20)     not null 				   comment '选项id',
  opt_title         varchar(200)    default ''                 comment '选项名称',
  opt_desc          varchar(2000)   default ''         	       comment '选项描述',
  quest_no          varchar(20)     default ''                 comment '试题编号',
  order_no          int(4)          default 0                  comment '显示顺序', 
  opt_score         double          default 0                  comment '选项得分',
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
  UNIQUE INDEX idx_optno (`opt_no`)
) engine=innodb auto_increment=100 comment = '测题选项表'; 

drop table if exists cct_paperclass;
create table cct_paperclass (
  id				bigint(20)      not null auto_increment    comment 'id',
  class_no          varchar(20)     not null 				   comment '类型id',
  class_name        varchar(200)    default ''                 comment '类型名称',
  parent_no         varchar(20)     default '0' 			   comment '上级类型',
  order_no          int(4)          default 0                  comment '显示顺序', 
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
  UNIQUE INDEX idx_classno (`class_no`)
) engine=innodb auto_increment=100 comment = '问卷类型表'; 

drop table if exists cct_paperinfo;
create table cct_paperinfo (
  id				bigint(20)      not null auto_increment    comment 'id',
  paper_no          varchar(20)     not null 				   comment '问卷id',
  paper_title       varchar(200)    default ''                 comment '问卷名称', 
  paper_desc        varchar(2000)   default ''                comment '问卷描述',
  paper_type        char(1)         default '1'         	  comment '问卷类别（1:手动生成，2：随机生成）',
  class_no          varchar(20)     default '' 				   comment '问卷类型',
  order_no          int(4)          default 0                  comment '显示顺序', 
  paper_score       double          default 0                  comment '问卷总得分',
  rules_quests      varchar(2000)   default ''                  comment '生成试题规则',
  paper_quests      varchar(2000)   default ''                  comment '问卷试题集',
  paper_rusers      varchar(2000)   default ''                 comment '测评对象集',
  start_date 	    datetime                                   comment '开始时间',
  endit_date 	    datetime                                   comment '结束时间',
  duration          int(4)          default 0                  comment '测评时长',
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
  UNIQUE INDEX idx_paperno (`paper_no`)
) engine=innodb auto_increment=100 comment = '问卷信息表'; 

drop table if exists cct_paperflows;
create table cct_paperflows (
  id				bigint(20)      not null auto_increment    comment 'id',
  pflow_no         varchar(20)     not null 				   comment '结果id',
  paper_no       varchar(20)    default ''                     comment '问卷编号',
  ruser_no       varchar(20)     default ''                    comment '人员编号',
  quest_no       varchar(20)     default ''                    comment '试题编号',
  opt_no          varchar(20)     default ''                   comment '答案编号',
  opt_score       double     default 0                         comment '答案得分',
  start_time 	    datetime                                   comment '开始时间',
  endit_time 	    datetime                                   comment '结束时间',
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
  UNIQUE INDEX idx_pflowno (`pflow_no`)
) engine=innodb auto_increment=100 comment = '测评结果表'; 