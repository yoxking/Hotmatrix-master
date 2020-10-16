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
CREATE TABLE `cct_paperflows` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pflow_no` varchar(20) NOT NULL COMMENT '测评编号',
  `paper_no` varchar(20) NOT NULL DEFAULT '' COMMENT '问卷编号',
  `ruser_no` varchar(20) NOT NULL DEFAULT '' COMMENT '人员编号',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `endit_time` datetime DEFAULT NULL COMMENT '结束时间',
  `pflow_time` varchar(20) DEFAULT '' COMMENT '测评用时',
  `pflow_score` double NOT NULL DEFAULT '0' COMMENT '测评得分',
  `pflow_state` char(20) NOT NULL DEFAULT '0' COMMENT '测评状态',
  `branch_no` varchar(20) DEFAULT '' COMMENT '分支编号',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` char(1) DEFAULT '1' COMMENT '删除标志（1代表存在 0代表删除）',
  `comments` varchar(256) DEFAULT '' COMMENT '备注',
  `app_code` varchar(256) DEFAULT '' COMMENT '应用编码',
  `version` bigint(20) DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_pflowno` (`pflow_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='测评结果表';

DROP TABLE IF EXISTS `cct_questflows`;
CREATE TABLE `cct_questflows` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `qflow_no` varchar(20) NOT NULL COMMENT '答题编号',
  `pflow_no` varchar(20) NOT NULL COMMENT '测评编号',
  `quest_no` varchar(20) NOT NULL COMMENT '题型编号',
  `qtopt_value` varchar(2000) DEFAULT NULL COMMENT '答题值',
  `qtopt_score` double NOT NULL DEFAULT '0' COMMENT '答题得分',
  `qtopt_flag` char(1) NOT NULL DEFAULT '0' COMMENT '答题标志',
  `qtopt_view` varchar(256) DEFAULT NULL COMMENT '答题评论',
  `check_state` char(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `branch_no` varchar(60) DEFAULT NULL COMMENT '分支编号',
  `create_by` varchar(60) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(60) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` char(3) DEFAULT '1' COMMENT '删除标志（1代表存在 0代表删除）',
  `comments` varchar(256) DEFAULT NULL COMMENT '备注',
  `app_code` varchar(256) DEFAULT '' COMMENT '应用编码',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_qflowno` (`qflow_no`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='答题结果表';



drop table if exists cct_blogclass;
create table cct_blogclass (
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
) engine=innodb auto_increment=100 comment = '日记类型表'; 




drop table if exists cct_blogsinfo;
create table cct_blogsinfo (
  id				bigint(20)      not null auto_increment    comment 'id',
  blog_no           varchar(20)     not null 				   comment '日记id',
  blog_title        varchar(200)    default ''                 comment '日记标题',
  class_no          varchar(20)     default '1' 			   comment '日记类型',
  ruser_no          varchar(20)     default '1' 			   comment '用户编号',
  bcontent          varchar(2000)    			   			   comment '日记内容',
  dolike_hit        int(4)          default 0 			   	   comment '点赞次数',
  repost_hit        int(4)          default 0			       comment '转发次数', 
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
  UNIQUE INDEX idx_blogno (`blog_no`)
) engine=innodb auto_increment=100 comment = '日记信息表'; 





drop table if exists cct_registflows;
create table cct_registflows (
  id				bigint(20)      not null auto_increment    comment 'id',
  regist_no          varchar(20)     not null 				   comment '预约id',
  regist_type          int(4)          default 0                  comment '预约类型', 
  regist_time 	    datetime                                   comment '预约时间',
  expert_no        varchar(20)    default ''                 comment '专家编号',
  ruser_no         varchar(20)     default '1' 			   comment '预约人编号',
  data_from         varchar(20)     default '1' 			   comment '数据来源',
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
  UNIQUE INDEX idx_registno (`regist_no`)
) engine=innodb auto_increment=100 comment = '预约信息表'; 



drop table if exists cct_salonclass;
create table cct_salonclass (
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
) engine=innodb auto_increment=100 comment = '沙龙类型表'; 




drop table if exists cct_salonsinfo;
create table cct_salonsinfo (
  id				bigint(20)      not null auto_increment    comment 'id',
  salon_no          varchar(20)     not null 				   comment '沙龙id',
  salon_name        varchar(200)    default ''                 comment '沙龙名称',
  class_no          varchar(20)     default '1' 			   comment '沙龙类型',
  regist_time 	    datetime                                   comment '预约沙龙时间',
  regist_rusers     varchar(2000)    default ''                comment '预约参会人员', 
  salon_content     varchar(2000)    default ''                comment '沙龙内容',
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
  UNIQUE INDEX idx_salonno (`salon_no`)
) engine=innodb auto_increment=100 comment = '沙龙信息表'; 



drop table if exists cct_salonflows;
create table cct_salonflows (
  id				bigint(20)      not null auto_increment    comment 'id',
  sflow_no          varchar(20)     not null 				   comment '过程id',
  salon_no          varchar(20)     not null 				   comment '沙龙id',
  ruser_no          varchar(20)     default '' 			   comment '用户编号',
  sflow_desc        varchar(2000)    default ''                comment '评价内容',
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
  UNIQUE INDEX idx_sflowno (`sflow_no`)
) engine=innodb auto_increment=100 comment = '沙龙过程表'; 


drop table if exists cct_remarkinfo;
create table cct_remarkinfo (
  id				bigint(20)      not null auto_increment    comment 'id',
  remark_no          varchar(20)     not null 				   comment '评论id',
  remark_name        varchar(200)    default ''                 comment '评论名称',
  object_no         varchar(20)     default '' 			   comment '主题编号',
  mark_content      varchar(2000)         default ''                  comment '评论内容', 
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
  UNIQUE INDEX idx_remarkno (`remark_no`)
) engine=innodb auto_increment=100 comment = '评论信息表'; 



drop table if exists cct_reportinfo;
create table cct_reportinfo (
  id				bigint(20)      not null auto_increment    comment 'id',
  report_no          varchar(20)     not null 				   comment '报告id',
  ruser_no         varchar(20)     default '1' 			   comment '用户编号',
  report_type          int(4)          default 0                  comment '报告类型', 
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
  UNIQUE INDEX idx_reportno (`report_no`)
) engine=innodb auto_increment=100 comment = '报告信息表'; 