package com.benet.wkflow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 流程任务对象 flow_flowtaske
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public class FlowFlowtaske extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 任务id */
    @Excel(name = "任务id")
    private String taskNo;

    /** 任务类型 0常规 1指派 2委托 3转交 4退回 5抄送 6前加签 7并签 8后加签 */
    @Excel(name = "任务类型 0常规 1指派 2委托 3转交 4退回 5抄送 6前加签 7并签 8后加签")
    private Long taskType;

    /** 任务标题 */
    @Excel(name = "任务标题")
    private String taskTitle;

    /** 流程ID */
    @Excel(name = "流程ID")
    private String flowNo;

    /** 流程名称 */
    @Excel(name = "流程名称")
    private String flowName;

    /** 上一任务ID */
    @Excel(name = "上一任务ID")
    private String prevNo;

    /** 上一步骤ID */
    @Excel(name = "上一步骤ID")
    private String prevStepno;

    /** 步骤ID */
    @Excel(name = "步骤ID")
    private String stepNo;

    /** 步骤名称 */
    @Excel(name = "步骤名称")
    private String stepName;

    /** 一个步骤内的处理顺序(选择人员顺序处理时的处理顺序) */
    @Excel(name = "一个步骤内的处理顺序(选择人员顺序处理时的处理顺序)")
    private Long stepSort;

    /** 对应业务表主键值 */
    @Excel(name = "对应业务表主键值")
    private String instanceNo;

    /** 分组ID */
    @Excel(name = "分组ID")
    private String groupNo;

    /** 任务顺序 */
    @Excel(name = "任务顺序")
    private Long orderNo;

    /** 发送人ID(如果是兼职岗位R_关系表ID) */
    @Excel(name = "发送人ID(如果是兼职岗位R_关系表ID)")
    private String sendBy;

    /** 发送人姓名 */
    @Excel(name = "发送人姓名")
    private String senderName;

    /** 接收人ID(如果是兼职岗位R_关系表ID) */
    @Excel(name = "接收人ID(如果是兼职岗位R_关系表ID)")
    private String receiveBy;

    /** 接收人所在机构ID（如果是兼职人员的情况下这里有值） */
    @Excel(name = "接收人所在机构ID", readConverterExp = "如=果是兼职人员的情况下这里有值")
    private String receiveOrgzno;

    /** 接收人姓名 */
    @Excel(name = "接收人姓名")
    private String receiverName;

    /** 接收时间 */
    @Excel(name = "接收时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiveTime;

    /** 打开时间 */
    @Excel(name = "打开时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date openingTime;

    /** 要求完成时间 */
    @Excel(name = "要求完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completedTime1;

    /** 实际完成时间 */
    @Excel(name = "实际完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completedTime2;

    /** 处理意见 */
    @Excel(name = "处理意见")
    private String operateNote;

    /** 子流程实例分组ID */
    @Excel(name = "子流程实例分组ID")
    private String subflowGroupno;

    /** 是否签章 */
    @Excel(name = "是否签章")
    private Long isSign;

    /** 是否超时自动提交 0否 1是 */
    @Excel(name = "是否超时自动提交 0否 1是")
    private Long isAutosubmit;

    /** 附件 */
    @Excel(name = "附件")
    private String attachFiles;

    /** 处理类型 -1等待中 0未处理 1处理中 2已完成 3已退回 4他人已处理 5他人已退回 6已转交 7已委托 8已阅知 */
    @Excel(name = "处理类型 -1等待中 0未处理 1处理中 2已完成 3已退回 4他人已处理 5他人已退回 6已转交 7已委托 8已阅知")
    private Long executeType;

    /** 如果是委托任务，这里记录委托人员ID */
    @Excel(name = "如果是委托任务，这里记录委托人员ID")
    private String entrustUserno;

    /**  */
    @Excel(name = "")
    private Long otherType;

    /** 任务状态 0等待中 1未处理 2已处理 */
    @Excel(name = "任务状态 0等待中 1未处理 2已处理")
    private String checkState;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    /** 删除标志（1代表存在 0代表删除） */
    @Excel(name = "删除标志", readConverterExp = "1=代表存在,0=代表删除")
    private String deleteFlag;

    /** 更新者 */
    @Excel(name = "更新者")
    private String comments;

    /** 应用编码 */
    @Excel(name = "应用编码")
    private String appCode;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTaskNo(String taskNo) 
    {
        this.taskNo = taskNo;
    }

    public String getTaskNo() 
    {
        return taskNo;
    }
    public void setTaskType(Long taskType) 
    {
        this.taskType = taskType;
    }

    public Long getTaskType() 
    {
        return taskType;
    }
    public void setTaskTitle(String taskTitle) 
    {
        this.taskTitle = taskTitle;
    }

    public String getTaskTitle() 
    {
        return taskTitle;
    }
    public void setFlowNo(String flowNo) 
    {
        this.flowNo = flowNo;
    }

    public String getFlowNo() 
    {
        return flowNo;
    }
    public void setFlowName(String flowName) 
    {
        this.flowName = flowName;
    }

    public String getFlowName() 
    {
        return flowName;
    }
    public void setPrevNo(String prevNo) 
    {
        this.prevNo = prevNo;
    }

    public String getPrevNo() 
    {
        return prevNo;
    }
    public void setPrevStepno(String prevStepno) 
    {
        this.prevStepno = prevStepno;
    }

    public String getPrevStepno() 
    {
        return prevStepno;
    }
    public void setStepNo(String stepNo) 
    {
        this.stepNo = stepNo;
    }

    public String getStepNo() 
    {
        return stepNo;
    }
    public void setStepName(String stepName) 
    {
        this.stepName = stepName;
    }

    public String getStepName() 
    {
        return stepName;
    }
    public void setStepSort(Long stepSort) 
    {
        this.stepSort = stepSort;
    }

    public Long getStepSort() 
    {
        return stepSort;
    }
    public void setInstanceNo(String instanceNo) 
    {
        this.instanceNo = instanceNo;
    }

    public String getInstanceNo() 
    {
        return instanceNo;
    }
    public void setGroupNo(String groupNo) 
    {
        this.groupNo = groupNo;
    }

    public String getGroupNo() 
    {
        return groupNo;
    }
    public void setOrderNo(Long orderNo) 
    {
        this.orderNo = orderNo;
    }

    public Long getOrderNo() 
    {
        return orderNo;
    }
    public void setSendBy(String sendBy) 
    {
        this.sendBy = sendBy;
    }

    public String getSendBy() 
    {
        return sendBy;
    }
    public void setSenderName(String senderName) 
    {
        this.senderName = senderName;
    }

    public String getSenderName() 
    {
        return senderName;
    }
    public void setReceiveBy(String receiveBy) 
    {
        this.receiveBy = receiveBy;
    }

    public String getReceiveBy() 
    {
        return receiveBy;
    }
    public void setReceiveOrgzno(String receiveOrgzno) 
    {
        this.receiveOrgzno = receiveOrgzno;
    }

    public String getReceiveOrgzno() 
    {
        return receiveOrgzno;
    }
    public void setReceiverName(String receiverName) 
    {
        this.receiverName = receiverName;
    }

    public String getReceiverName() 
    {
        return receiverName;
    }
    public void setReceiveTime(Date receiveTime) 
    {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime() 
    {
        return receiveTime;
    }
    public void setOpeningTime(Date openingTime) 
    {
        this.openingTime = openingTime;
    }

    public Date getOpeningTime() 
    {
        return openingTime;
    }
    public void setCompletedTime1(Date completedTime1) 
    {
        this.completedTime1 = completedTime1;
    }

    public Date getCompletedTime1() 
    {
        return completedTime1;
    }
    public void setCompletedTime2(Date completedTime2) 
    {
        this.completedTime2 = completedTime2;
    }

    public Date getCompletedTime2() 
    {
        return completedTime2;
    }
    public void setOperateNote(String operateNote) 
    {
        this.operateNote = operateNote;
    }

    public String getOperateNote() 
    {
        return operateNote;
    }
    public void setSubflowGroupno(String subflowGroupno) 
    {
        this.subflowGroupno = subflowGroupno;
    }

    public String getSubflowGroupno() 
    {
        return subflowGroupno;
    }
    public void setIsSign(Long isSign) 
    {
        this.isSign = isSign;
    }

    public Long getIsSign() 
    {
        return isSign;
    }
    public void setIsAutosubmit(Long isAutosubmit) 
    {
        this.isAutosubmit = isAutosubmit;
    }

    public Long getIsAutosubmit() 
    {
        return isAutosubmit;
    }
    public void setAttachFiles(String attachFiles) 
    {
        this.attachFiles = attachFiles;
    }

    public String getAttachFiles() 
    {
        return attachFiles;
    }
    public void setExecuteType(Long executeType) 
    {
        this.executeType = executeType;
    }

    public Long getExecuteType() 
    {
        return executeType;
    }
    public void setEntrustUserno(String entrustUserno) 
    {
        this.entrustUserno = entrustUserno;
    }

    public String getEntrustUserno() 
    {
        return entrustUserno;
    }
    public void setOtherType(Long otherType) 
    {
        this.otherType = otherType;
    }

    public Long getOtherType() 
    {
        return otherType;
    }
    public void setCheckState(String checkState) 
    {
        this.checkState = checkState;
    }

    public String getCheckState() 
    {
        return checkState;
    }
    public void setCreateBy(String createBy) 
    {
        this.createBy = createBy;
    }

    public String getCreateBy() 
    {
        return createBy;
    }
    public void setCreateTime(Date createTime) 
    {
        this.createTime = createTime;
    }

    public Date getCreateTime() 
    {
        return createTime;
    }
    public void setUpdateBy(String updateBy) 
    {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() 
    {
        return updateBy;
    }
    public void setUpdateTime(Date updateTime) 
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() 
    {
        return updateTime;
    }
    public void setDeleteFlag(String deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public String getDeleteFlag() 
    {
        return deleteFlag;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
    }
    public void setAppCode(String appCode) 
    {
        this.appCode = appCode;
    }

    public String getAppCode() 
    {
        return appCode;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskNo", getTaskNo())
            .append("taskType", getTaskType())
            .append("taskTitle", getTaskTitle())
            .append("flowNo", getFlowNo())
            .append("flowName", getFlowName())
            .append("prevNo", getPrevNo())
            .append("prevStepno", getPrevStepno())
            .append("stepNo", getStepNo())
            .append("stepName", getStepName())
            .append("stepSort", getStepSort())
            .append("instanceNo", getInstanceNo())
            .append("groupNo", getGroupNo())
            .append("orderNo", getOrderNo())
            .append("sendBy", getSendBy())
            .append("senderName", getSenderName())
            .append("receiveBy", getReceiveBy())
            .append("receiveOrgzno", getReceiveOrgzno())
            .append("receiverName", getReceiverName())
            .append("receiveTime", getReceiveTime())
            .append("openingTime", getOpeningTime())
            .append("completedTime1", getCompletedTime1())
            .append("completedTime2", getCompletedTime2())
            .append("operateNote", getOperateNote())
            .append("subflowGroupno", getSubflowGroupno())
            .append("isSign", getIsSign())
            .append("isAutosubmit", getIsAutosubmit())
            .append("attachFiles", getAttachFiles())
            .append("executeType", getExecuteType())
            .append("entrustUserno", getEntrustUserno())
            .append("otherType", getOtherType())
            .append("checkState", getCheckState())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deleteFlag", getDeleteFlag())
            .append("comments", getComments())
            .append("appCode", getAppCode())
            .append("version", getVersion())
            .toString();
    }
}
