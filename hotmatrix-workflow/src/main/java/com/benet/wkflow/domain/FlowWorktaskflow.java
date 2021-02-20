package com.benet.wkflow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 任务实例对象 flow_worktaskflow
 * 
 * @author yoxking
 * @date 2021-02-20
 */
public class FlowWorktaskflow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 任务id */
    @Excel(name = "任务id")
    private String tflowNo;

    /** 流程实例id */
    @Excel(name = "流程实例id")
    private String pflowNo;

    /** 流程id */
    @Excel(name = "流程id")
    private String processNo;

    /** 流程版本号 */
    @Excel(name = "流程版本号")
    private Long processVersion;

    /** 业务类别 */
    @Excel(name = "业务类别")
    private String bizType;

    /** 任务id */
    @Excel(name = "任务id")
    private String taskId;

    /** 环节id */
    @Excel(name = "环节id")
    private String activityId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 显示名称 */
    @Excel(name = "显示名称")
    private String displayName;

    /** 任务类型,FormTask,ToolTask,SubflowTask */
    @Excel(name = "任务类型,FormTask,ToolTask,SubflowTask")
    private String taskType;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private String taskState;

    /** 是否挂起 */
    @Excel(name = "是否挂起")
    private String suspended;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 启动时间 */
    @Excel(name = "启动时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startedTime;

    /** 到期时间 */
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expiredTime;

    /** 终止时间 */
    @Excel(name = "终止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 任务分配策略,取值ALL,ANY */
    @Excel(name = "任务分配策略,取值ALL,ANY")
    private String assignStrategy;

    /** 前驱activity的id */
    @Excel(name = "前驱activity的id")
    private String fromActivityId;

    /** 后续activity的id */
    @Excel(name = "后续activity的id")
    private String toActivityId;

    /** 步数 */
    @Excel(name = "步数")
    private Long stepNumber;

    /** 是否可以被撤销 */
    @Excel(name = "是否可以被撤销")
    private String canWithdrawn;

    /** 状态：0 保存 1 编译 2作废 */
    @Excel(name = "状态：0 保存 1 编译 2作废")
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
    public void setTflowNo(String tflowNo) 
    {
        this.tflowNo = tflowNo;
    }

    public String getTflowNo() 
    {
        return tflowNo;
    }
    public void setPflowNo(String pflowNo) 
    {
        this.pflowNo = pflowNo;
    }

    public String getPflowNo() 
    {
        return pflowNo;
    }
    public void setProcessNo(String processNo) 
    {
        this.processNo = processNo;
    }

    public String getProcessNo() 
    {
        return processNo;
    }
    public void setProcessVersion(Long processVersion) 
    {
        this.processVersion = processVersion;
    }

    public Long getProcessVersion() 
    {
        return processVersion;
    }
    public void setBizType(String bizType) 
    {
        this.bizType = bizType;
    }

    public String getBizType() 
    {
        return bizType;
    }
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setActivityId(String activityId) 
    {
        this.activityId = activityId;
    }

    public String getActivityId() 
    {
        return activityId;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }
    public void setDisplayName(String displayName) 
    {
        this.displayName = displayName;
    }

    public String getDisplayName() 
    {
        return displayName;
    }
    public void setTaskType(String taskType) 
    {
        this.taskType = taskType;
    }

    public String getTaskType() 
    {
        return taskType;
    }
    public void setTaskState(String taskState) 
    {
        this.taskState = taskState;
    }

    public String getTaskState() 
    {
        return taskState;
    }
    public void setSuspended(String suspended) 
    {
        this.suspended = suspended;
    }

    public String getSuspended() 
    {
        return suspended;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setStartedTime(Date startedTime) 
    {
        this.startedTime = startedTime;
    }

    public Date getStartedTime() 
    {
        return startedTime;
    }
    public void setExpiredTime(Date expiredTime) 
    {
        this.expiredTime = expiredTime;
    }

    public Date getExpiredTime() 
    {
        return expiredTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setAssignStrategy(String assignStrategy) 
    {
        this.assignStrategy = assignStrategy;
    }

    public String getAssignStrategy() 
    {
        return assignStrategy;
    }
    public void setFromActivityId(String fromActivityId) 
    {
        this.fromActivityId = fromActivityId;
    }

    public String getFromActivityId() 
    {
        return fromActivityId;
    }
    public void setToActivityId(String toActivityId) 
    {
        this.toActivityId = toActivityId;
    }

    public String getToActivityId() 
    {
        return toActivityId;
    }
    public void setStepNumber(Long stepNumber) 
    {
        this.stepNumber = stepNumber;
    }

    public Long getStepNumber() 
    {
        return stepNumber;
    }
    public void setCanWithdrawn(String canWithdrawn) 
    {
        this.canWithdrawn = canWithdrawn;
    }

    public String getCanWithdrawn() 
    {
        return canWithdrawn;
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
            .append("tflowNo", getTflowNo())
            .append("pflowNo", getPflowNo())
            .append("processNo", getProcessNo())
            .append("processVersion", getProcessVersion())
            .append("bizType", getBizType())
            .append("taskId", getTaskId())
            .append("activityId", getActivityId())
            .append("taskName", getTaskName())
            .append("displayName", getDisplayName())
            .append("taskType", getTaskType())
            .append("taskState", getTaskState())
            .append("suspended", getSuspended())
            .append("createdTime", getCreatedTime())
            .append("startedTime", getStartedTime())
            .append("expiredTime", getExpiredTime())
            .append("endTime", getEndTime())
            .append("assignStrategy", getAssignStrategy())
            .append("fromActivityId", getFromActivityId())
            .append("toActivityId", getToActivityId())
            .append("stepNumber", getStepNumber())
            .append("canWithdrawn", getCanWithdrawn())
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
