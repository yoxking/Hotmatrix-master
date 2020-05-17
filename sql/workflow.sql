
-- ----------------------------
-- 1、表单设计表
-- ----------------------------
DROP TABLE IF EXISTS `flw_tableform`;
CREATE TABLE `flw_tableform` (
  id					bigint(20)      not null auto_increment    comment 'id',
  form_no         		varchar(20)     not null 				   comment '表单id',
  form_name 					varchar(200) NOT NULL COMMENT '表单名称',
  form_type 			varchar(20) NOT NULL COMMENT '表单分类',
  form_html 			longtext COMMENT '表单HTML',
  subtb_json 			longtext COMMENT '子表json',
  event_json 			longtext COMMENT '事件json',
  attribute 			text COMMENT '属性json',
  check_state       char(1)         default '1'                comment '状态：0 保存 1 编译 2作废',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)    default ''                 comment '更新者',
  app_code          varchar(256)    default ''                 comment '应用编码',
  version           bigint(20)      default 0                  comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_formno (`form_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表单设计表';



-- ----------------------------
-- 1、工作流表
-- ----------------------------
DROP TABLE IF EXISTS `flw_workflow`;
CREATE TABLE `flw_workflow` (
  id						bigint(20)      not null auto_increment    comment 'id',
  flow_no  			       varchar(20)     not null 				   comment '工作流id',
  flow_name					 varchar(200) NOT NULL COMMENT '工作流名称',
  flow_type 				varchar(20) NOT NULL COMMENT '工作流分类',
  flow_manager				 text NOT NULL COMMENT '管理人员',
  instance_by			text NOT NULL COMMENT '实例管理人员',
  design_json			longtext COMMENT '设计时JSON',
  runing_json              longtext COMMENT '运行时JSON',
  install_date				datetime DEFAULT NULL COMMENT '安装日期',
  install_user				char(32) DEFAULT NULL COMMENT '安装人员',
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
  UNIQUE INDEX idx_flowno (`flow_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流表';


-- ----------------------------
-- 1、工作流程归档表
-- ----------------------------
DROP TABLE IF EXISTS `flw_wflowarchives`;
CREATE TABLE `flw_wflowarchives` (
  id				bigint(20)      not null auto_increment    comment 'id',
  archv_no         varchar(20)     not null 				   comment '归档id',
  flow_no		 varchar(20) NOT NULL COMMENT '流程ID',
  flow_name 				text NOT NULL COMMENT '流程名称',
  step_no 				varchar(20) NOT NULL COMMENT '步骤ID',
  step_name 			text NOT NULL COMMENT '步骤名称',
  task_no 				varchar(20) NOT NULL COMMENT '任务ID',
  group_no 				varchar(20) NOT NULL COMMENT '组ID',
  instance_no 				text COMMENT '实例ID',
  flow_title 			text NOT NULL COMMENT '流程标题',
  contents 		 longtext COMMENT '内容',
  write_time 			datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '归档时间',
  write_note		 longtext COMMENT '意见',
  rar_files			 longtext COMMENT '相关文件',
  pdf_files		 varchar(255) DEFAULT NULL COMMENT 'PDF文件',
  user_no			 varchar(20) DEFAULT NULL COMMENT '处理人ID',
  user_name		 varchar(200) DEFAULT NULL COMMENT '处理人姓名',
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
  UNIQUE INDEX idx_archvno (`archv_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- 1、工作流程按钮表
-- ----------------------------
DROP TABLE IF EXISTS `flw_wflowbutton`;
CREATE TABLE `flw_wflowbutton` (
  id				bigint(20)      not null auto_increment    comment 'id',
  btn_no         varchar(20)     not null 				   comment '按钮id',
  btn_title		 varchar(200) NOT NULL COMMENT '按钮标题',
  btn_icon 		varchar(200) DEFAULT NULL COMMENT '按钮图标',
  btn_script	 text COMMENT '按钮脚本',
  btn_remark	 varchar(200) DEFAULT NULL COMMENT '备注说明',
  order_no		 int(4) NOT NULL COMMENT '排序',
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
  UNIQUE INDEX idx_btnno (`btn_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流程按钮表';


-- ----------------------------
-- 1、流程处理意见表
-- ----------------------------
DROP TABLE IF EXISTS `flw_wflownotes`;
CREATE TABLE `flw_wflownotes` (
  id				bigint(20)      not null auto_increment    comment 'id',
  note_no         varchar(20)     not null 				   comment '意见id',
  user_no 		varchar(20) DEFAULT NULL COMMENT '意见使用人',
  add_type 		int(11) NOT NULL COMMENT '类型 0用户添加 1管理员添加',
  contents		 text NOT NULL COMMENT '意见',
  order_no		 int(4) NOT NULL COMMENT '排序',
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
  UNIQUE INDEX idx_noteno (`note_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程处理意见表';



-- ----------------------------
-- 1、流程委托表
-- ----------------------------
DROP TABLE IF EXISTS `flw_wflowentrust`;
CREATE TABLE `flw_wflowentrust` (
  id				bigint(20)      not null auto_increment    comment 'id',
  entrust_no         varchar(20)     not null 				   comment '委托id',
  flow_no		 varchar(20)  DEFAULT NULL COMMENT '流程ID，如果为空表示所有流程',
  from_userno		 varchar(20)  NOT NULL COMMENT '委托人员ID',
  to_userno			varchar(20)  NOT NULL COMMENT '被委托人ID',
  start_time 	datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  endit_time	 datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
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
  UNIQUE INDEX idx_entrustno (`entrust_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程委托表';



-- ----------------------------
-- 1、流程任务表
-- ----------------------------
DROP TABLE IF EXISTS `flw_wflowtaske`;
CREATE TABLE `flw_wflowtaske` (
  id				bigint(20)      not null auto_increment    comment 'id',
  task_no         varchar(20)     not null 				   comment '任务id',
  task_type 		int(11) NOT NULL COMMENT '任务类型 0常规 1指派 2委托 3转交 4退回 5抄送 6前加签 7并签 8后加签',
  task_title 		text NOT NULL COMMENT '任务标题',
  flow_no 			varchar(20) NOT NULL COMMENT '流程ID',
  flow_name			 varchar(200) NOT NULL COMMENT '流程名称',
  prev_no 			varchar(20) NOT NULL COMMENT '上一任务ID',
  prev_stepno 				varchar(20) NOT NULL COMMENT '上一步骤ID',
  step_no 			varchar(20) NOT NULL COMMENT '步骤ID',
  step_name			 varchar(200) NOT NULL COMMENT '步骤名称',
  step_sort				 int(11) NOT NULL COMMENT '一个步骤内的处理顺序(选择人员顺序处理时的处理顺序)',
  instance_no 		text COMMENT '对应业务表主键值',
  group_no			 varchar(20) NOT NULL COMMENT '分组ID',
  order_no 				int(11) NOT NULL COMMENT '任务顺序',
  send_by				varchar(20) NOT NULL COMMENT '发送人ID(如果是兼职岗位R_关系表ID)',
  sender_name 		varchar(50) NOT NULL COMMENT '发送人姓名',
  receive_by			 varchar(20) NOT NULL COMMENT '接收人ID(如果是兼职岗位R_关系表ID)',
  receive_orgzno	 varchar(20) DEFAULT NULL COMMENT '接收人所在机构ID（如果是兼职人员的情况下这里有值）',
  receiver_name 			varchar(50) NOT NULL COMMENT '接收人姓名',
  receive_time			 datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '接收时间',
  opening_time		 datetime DEFAULT NULL COMMENT '打开时间',
  completed_time1			datetime DEFAULT NULL COMMENT '要求完成时间',
  completed_time2		 datetime DEFAULT NULL COMMENT '实际完成时间',
  operate_note		 varchar(200) DEFAULT NULL COMMENT '处理意见',
  subflow_groupno			 text COMMENT '子流程实例分组ID',
  is_sign			 int(11) NOT NULL COMMENT '是否签章', 
  is_autosubmit			int(11) NOT NULL COMMENT '是否超时自动提交 0否 1是',
  attach_files				longtext COMMENT '附件', 
  execute_type			int(11) NOT NULL COMMENT '处理类型 -1等待中 0未处理 1处理中 2已完成 3已退回 4他人已处理 5他人已退回 6已转交 7已委托 8已阅知',
  entrust_userno			varchar(20) DEFAULT NULL COMMENT '如果是委托任务，这里记录委托人员ID',
  other_type	 int(11) NOT NULL DEFAULT '0',
  check_state       char(1)         default '1'                comment '任务状态 0等待中 1未处理 2已处理',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  delete_flag       char(1)         default '1'                comment '删除标志（1代表存在 0代表删除）',
  comments          varchar(256)    default ''                 comment '更新者',
  app_code          varchar(256)    default ''                 comment '应用编码',
  version           bigint(20)      default 0                  comment '版本号',
  primary key (id),
  UNIQUE INDEX idx_taskno (`task_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程任务表';




-- ----------------------------
-- 1、工作组表
-- ----------------------------
DROP TABLE IF EXISTS `flw_workgroup`;
CREATE TABLE `flw_workgroup` (
  id				bigint(20)      not null auto_increment    comment 'id',
  group_no         varchar(20)     not null 				   comment '工作组id',
  group_name	 varchar(200) NOT NULL COMMENT '工作组名称',
  members		 text COMMENT '包括机构',
  users			 longtext COMMENT '包括人员ID', 
  order_no      int(4) NOT NULL COMMENT '排序',
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
  UNIQUE INDEX idx_groupno (`group_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作组表';
