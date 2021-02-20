package com.benet.wkflow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 流程信息对象 flow_processinfo
 * 
 * @author yoxking
 * @date 2021-02-04
 */
public class FlowProcessinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 流程id */
    @Excel(name = "流程id")
    private String processNo;

    /** 流程的名称 */
    @Excel(name = "流程的名称")
    private String processName;

    /** 显示名称 */
    @Excel(name = "显示名称")
    private String displayName;

    /** 流程定义文件的类型，默认为fpdl */
    @Excel(name = "流程定义文件的类型，默认为fpdl")
    private String processType;

    /** 流程定义内容 */
    @Excel(name = "流程定义内容")
    private String processContent;

    /** 流程版本号 */
    @Excel(name = "流程版本号")
    private Long processVersion;

    /** 流程描述 */
    @Excel(name = "流程描述")
    private String description;

    /** 上载人 */
    @Excel(name = "上载人")
    private String uploadUser;

    /** 上载时间 */
    @Excel(name = "上载时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 发布人 */
    @Excel(name = "发布人")
    private String publishUser;

    /** 发布时间 */
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    /** 发布状态 */
    @Excel(name = "发布状态")
    private String publishState;

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
    public void setProcessNo(String processNo) 
    {
        this.processNo = processNo;
    }

    public String getProcessNo() 
    {
        return processNo;
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
    public void setProcessType(String processType) 
    {
        this.processType = processType;
    }

    public String getProcessType() 
    {
        return processType;
    }
    public void setProcessContent(String processContent) 
    {
        this.processContent = processContent;
    }

    public String getProcessContent() 
    {
        return processContent;
    }
    public void setProcessVersion(Long processVersion) 
    {
        this.processVersion = processVersion;
    }

    public Long getProcessVersion() 
    {
        return processVersion;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setUploadUser(String uploadUser) 
    {
        this.uploadUser = uploadUser;
    }

    public String getUploadUser() 
    {
        return uploadUser;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }
    public void setPublishUser(String publishUser) 
    {
        this.publishUser = publishUser;
    }

    public String getPublishUser() 
    {
        return publishUser;
    }
    public void setPublishTime(Date publishTime) 
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime() 
    {
        return publishTime;
    }
    public void setPublishState(String publishState) 
    {
        this.publishState = publishState;
    }

    public String getPublishState() 
    {
        return publishState;
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
            .append("processNo", getProcessNo())
            .append("processName", getProcessName())
            .append("displayName", getDisplayName())
            .append("processType", getProcessType())
            .append("processContent", getProcessContent())
            .append("processVersion", getProcessVersion())
            .append("description", getDescription())
            .append("uploadUser", getUploadUser())
            .append("uploadTime", getUploadTime())
            .append("publishUser", getPublishUser())
            .append("publishTime", getPublishTime())
            .append("publishState", getPublishState())
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
