package com.benet.wkflow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 流程实例对象 flow_processflow
 * 
 * @author yoxking
 * @date 2021-02-04
 */
public class FlowProcessflow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 流程实例id */
    @Excel(name = "流程实例id")
    private String pflowNo;

    /** 流程id */
    @Excel(name = "流程id")
    private String processNo;

    /** 流程版本号 */
    @Excel(name = "流程版本号")
    private Long processVersion;

    /** 流程名称 */
    @Excel(name = "流程名称")
    private String processName;

    /** 流程显示名称 */
    @Excel(name = "流程显示名称")
    private String displayName;

    /** 流程实例状态 */
    @Excel(name = "流程实例状态")
    private String pflowState;

    /** 是否被挂起 */
    @Excel(name = "是否被挂起")
    private String suspended;

    /** 创建者Id */
    @Excel(name = "创建者Id")
    private String creatorNo;

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

    /** 父流程实例Id */
    @Excel(name = "父流程实例Id")
    private String parentPflowNo;

    /** 父任务实例id */
    @Excel(name = "父任务实例id")
    private String parentTflowNo;

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
    public void setProcessName(String processName) 
    {
        this.processName = processName;
    }

    public String getProcessName() 
    {
        return processName;
    }
    public void setDisplayName(String displayName) 
    {
        this.displayName = displayName;
    }

    public String getDisplayName() 
    {
        return displayName;
    }
    public void setPflowState(String pflowState) 
    {
        this.pflowState = pflowState;
    }

    public String getPflowState() 
    {
        return pflowState;
    }
    public void setSuspended(String suspended) 
    {
        this.suspended = suspended;
    }

    public String getSuspended() 
    {
        return suspended;
    }
    public void setCreatorNo(String creatorNo) 
    {
        this.creatorNo = creatorNo;
    }

    public String getCreatorNo() 
    {
        return creatorNo;
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
    public void setParentPflowNo(String parentPflowNo) 
    {
        this.parentPflowNo = parentPflowNo;
    }

    public String getParentPflowNo() 
    {
        return parentPflowNo;
    }
    public void setParentTflowNo(String parentTflowNo) 
    {
        this.parentTflowNo = parentTflowNo;
    }

    public String getParentTflowNo() 
    {
        return parentTflowNo;
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
            .append("pflowNo", getPflowNo())
            .append("processNo", getProcessNo())
            .append("processVersion", getProcessVersion())
            .append("processName", getProcessName())
            .append("displayName", getDisplayName())
            .append("pflowState", getPflowState())
            .append("suspended", getSuspended())
            .append("creatorNo", getCreatorNo())
            .append("createdTime", getCreatedTime())
            .append("startedTime", getStartedTime())
            .append("expiredTime", getExpiredTime())
            .append("endTime", getEndTime())
            .append("parentPflowNo", getParentPflowNo())
            .append("parentTflowNo", getParentTflowNo())
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
