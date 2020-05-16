
-- ----------------------------
-- 1、表单设计表
-- ----------------------------
DROP TABLE IF EXISTS `wfw_workform`;
CREATE TABLE `wfw_workform` (
  id				bigint(20)      not null auto_increment    comment 'id',
  branch_no         varchar(20)     not null 				   comment '分支id',
  `NAME` varchar(200) NOT NULL COMMENT '表单名称',
  `FORMTYPE` char(32) NOT NULL COMMENT '表单分类',
  `CREATEUSERID` char(32) NOT NULL COMMENT '创建人员ID',
  `CREATEUSERNAME` varchar(50) NOT NULL COMMENT '创建人员姓名',
  `CREATETIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `EDITTIME` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `HTML` longtext COMMENT '表单HTML',
  `SUBTABLEJSON` longtext COMMENT '子表json',
  `EVENTJSON` longtext COMMENT '事件json',
  `ATTRIBUTE` text COMMENT '属性json',
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
  UNIQUE INDEX idx_branchno (`branch_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表单设计表';



-- ----------------------------
-- 1、工作流表
-- ----------------------------
DROP TABLE IF EXISTS `wfw_workflow`;
CREATE TABLE `wfw_workflow` (
  id				bigint(20)      not null auto_increment    comment 'id',
  branch_no         varchar(20)     not null 				   comment '分支id',
  `NAME` varchar(200) NOT NULL COMMENT '名称',
  `FLOWTYPE` char(32) NOT NULL COMMENT '分类',
  `MANAGER` text NOT NULL COMMENT '管理人员',
  `INSTANCEMANAGER` text NOT NULL COMMENT '实例管理人员',
  `CREATEDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `CREATEUSER` char(32) NOT NULL COMMENT '创建人员',
  `DESIGNERJSON` longtext COMMENT '设计时JSON',
  `RUNJSON` longtext COMMENT '运行时JSON',
  `INSTALLDATE` datetime DEFAULT NULL COMMENT '安装日期',
  `INSTALLUSER` char(32) DEFAULT NULL COMMENT '安装人员',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流表';


-- ----------------------------
-- 1、工作流程归档表
-- ----------------------------
DROP TABLE IF EXISTS `wfw_wflowarchives`;
CREATE TABLE `wfw_wflowarchives` (
  id				bigint(20)      not null auto_increment    comment 'id',
  branch_no         varchar(20)     not null 				   comment '分支id',
  `FLOWID` char(32) NOT NULL COMMENT '流程ID',
  `STEPID` char(32) NOT NULL COMMENT '步骤ID',
  `FLOWNAME` text NOT NULL COMMENT '流程名称',
  `STEPNAME` text NOT NULL COMMENT '步骤名称',
  `TASKID` char(32) NOT NULL COMMENT '任务ID',
  `GROUPID` char(32) NOT NULL COMMENT '组ID',
  `INSTANCEID` text COMMENT '实例ID',
  `FLOWTITLE` text NOT NULL COMMENT '流程标题',
  `WRITETIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '归档时间',
  `CONTENTS` longtext COMMENT '内容',
  `COMMENTS` longtext COMMENT '意见',
  `FILES` longtext COMMENT '相关文件',
  `PDF` varchar(255) DEFAULT NULL COMMENT 'PDF文件',
  `USERID` char(32) DEFAULT NULL COMMENT '处理人ID',
  `USERNAME` varchar(200) DEFAULT NULL COMMENT '处理人姓名',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- 1、工作流程按钮表
-- ----------------------------
DROP TABLE IF EXISTS `wfw_wflowbutton`;
CREATE TABLE `wfw_wflowbutton` (
  id				bigint(20)      not null auto_increment    comment 'id',
  branch_no         varchar(20)     not null 				   comment '分支id',
  `TITLE` varchar(20) NOT NULL COMMENT '按钮标题',
  `ICO` varchar(200) DEFAULT NULL COMMENT '按钮图标',
  `SCRIPT` text COMMENT '按钮脚本',
  `NOTE` varchar(200) DEFAULT NULL COMMENT '备注说明',
  `SORT` int(11) NOT NULL COMMENT '排序',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流程按钮表';


-- ----------------------------
-- 1、流程处理意见表
-- ----------------------------
DROP TABLE IF EXISTS `wfw_wflowcomment`;
CREATE TABLE `wfw_wflowcomment` (
  id				bigint(20)      not null auto_increment    comment 'id',
  branch_no         varchar(20)     not null 				   comment '分支id',
  `USERID` char(34) DEFAULT NULL COMMENT '意见使用人',
  `ADDTYPE` int(11) NOT NULL COMMENT '类型 0用户添加 1管理员添加',
  `COMMENTS` text NOT NULL COMMENT '意见',
  `SORT` int(11) NOT NULL COMMENT '排序',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程处理意见表';



-- ----------------------------
-- 1、流程委托表
-- ----------------------------
DROP TABLE IF EXISTS `wfw_wflowentrust`;
CREATE TABLE `wfw_wflowentrust` (
  `ID` char(32) NOT NULL,
  `USERID` char(34) NOT NULL COMMENT '人员ID',
  `STARTTIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `ENDTIME` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `FLOWID` char(32) DEFAULT NULL COMMENT '流程ID，如果为空表示所有流程',
  `TOUSERID` char(34) NOT NULL COMMENT '被委托人ID',
  `WRITETIME` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '写入时间',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程委托表';



-- ----------------------------
-- 1、流程任务表
-- ----------------------------
DROP TABLE IF EXISTS `wfw_wflowtask`;
CREATE TABLE `wfw_wflowtask` (
  id				bigint(20)      not null auto_increment    comment 'id',
  branch_no         varchar(20)     not null 				   comment '分支id',
  `PREVID` char(32) NOT NULL COMMENT '上一任务ID',
  `PREVSTEPID` char(32) NOT NULL COMMENT '上一步骤ID',
  `FLOWID` char(32) NOT NULL COMMENT '流程ID',
  `FLOWNAME` varchar(200) NOT NULL COMMENT '流程名称',
  `STEPID` char(32) NOT NULL COMMENT '步骤ID',
  `STEPNAME` varchar(200) NOT NULL COMMENT '步骤名称',
  `INSTANCEID` text COMMENT '对应业务表主键值',
  `GROUPID` char(32) NOT NULL COMMENT '分组ID',
  `TASKTYPE` int(11) NOT NULL COMMENT '任务类型 0常规 1指派 2委托 3转交 4退回 5抄送 6前加签 7并签 8后加签',
  `TITLE` text NOT NULL COMMENT '任务标题',
  `SENDERID` char(32) NOT NULL COMMENT '发送人ID(如果是兼职岗位R_关系表ID)',
  `SENDERNAME` varchar(50) NOT NULL COMMENT '发送人姓名',
  `RECEIVEID` char(32) NOT NULL COMMENT '接收人ID(如果是兼职岗位R_关系表ID)',
  `RECEIVENAME` varchar(50) NOT NULL COMMENT '接收人姓名',
  `RECEIVETIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '接收时间',
  `OPENTIME` datetime DEFAULT NULL COMMENT '打开时间',
  `COMPLETEDTIME` datetime DEFAULT NULL COMMENT '要求完成时间',
  `COMPLETEDTIME1` datetime DEFAULT NULL COMMENT '实际完成时间',
  `COMMENTS` varchar(200) DEFAULT NULL COMMENT '处理意见',
  `ISSIGN` int(11) NOT NULL COMMENT '是否签章',
  `NOTE` varchar(200) DEFAULT NULL COMMENT '备注',
  `SUBFLOWGROUPID` text COMMENT '子流程实例分组ID',
  `ISAUTOSUBMIT` int(11) NOT NULL COMMENT '是否超时自动提交 0否 1是',
  `ATTACHMENT` longtext COMMENT '附件',
  `STATUS` int(11) NOT NULL COMMENT '任务状态 0等待中 1未处理 2已处理',
  `SORT` int(11) NOT NULL COMMENT '任务顺序',
  `EXECUTETYPE` int(11) NOT NULL COMMENT '处理类型 -1等待中 0未处理 1处理中 2已完成 3已退回 4他人已处理 5他人已退回 6已转交 7已委托 8已阅知',
  `RECEIVEORGANIZEID` char(32) DEFAULT NULL COMMENT '接收人所在机构ID（如果是兼职人员的情况下这里有值）',
  `STEPSORT` int(11) NOT NULL COMMENT '一个步骤内的处理顺序(选择人员顺序处理时的处理顺序)',
  `ENTRUSTUSERID` char(32) DEFAULT NULL COMMENT '如果是委托任务，这里记录委托人员ID',
  `OTHERTYPE` int(11) NOT NULL DEFAULT '0',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程任务表';




-- ----------------------------
-- 1、工作组表
-- ----------------------------
DROP TABLE IF EXISTS `wfw_workgroup`;
CREATE TABLE `wfw_workgroup` (
  id				bigint(20)      not null auto_increment    comment 'id',
  branch_no         varchar(20)     not null 				   comment '分支id',
  `NAME` varchar(200) NOT NULL COMMENT '名称',
  `MEMBERS` text COMMENT '包括机构',
  `USERS` longtext COMMENT '包括人员ID',
  `NOTE` varchar(200) DEFAULT NULL COMMENT '备注',
  `SORT` int(11) NOT NULL COMMENT '排序',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作组表';
