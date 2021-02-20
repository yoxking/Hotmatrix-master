package com.benet.wkflow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 工作流对象 flow_workflows
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public class FlowWorkflows extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 工作流id */
    @Excel(name = "工作流id")
    private String flowNo;

    /** 工作流名称 */
    @Excel(name = "工作流名称")
    private String flowName;

    /** 工作流分类 */
    @Excel(name = "工作流分类")
    private String flowType;

    /** 管理人员 */
    @Excel(name = "管理人员")
    private String flowManager;

    /** 实例管理人员 */
    @Excel(name = "实例管理人员")
    private String instanceBy;

    /** 设计时JSON */
    @Excel(name = "设计时JSON")
    private String designJson;

    /** 运行时JSON */
    @Excel(name = "运行时JSON")
    private String runingJson;

    /** 安装日期 */
    @Excel(name = "安装日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date installDate;

    /** 安装人员 */
    @Excel(name = "安装人员")
    private String installUser;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
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
    public void setFlowType(String flowType) 
    {
        this.flowType = flowType;
    }

    public String getFlowType() 
    {
        return flowType;
    }
    public void setFlowManager(String flowManager) 
    {
        this.flowManager = flowManager;
    }

    public String getFlowManager() 
    {
        return flowManager;
    }
    public void setInstanceBy(String instanceBy) 
    {
        this.instanceBy = instanceBy;
    }

    public String getInstanceBy() 
    {
        return instanceBy;
    }
    public void setDesignJson(String designJson) 
    {
        this.designJson = designJson;
    }

    public String getDesignJson() 
    {
        return designJson;
    }
    public void setRuningJson(String runingJson) 
    {
        this.runingJson = runingJson;
    }

    public String getRuningJson() 
    {
        return runingJson;
    }
    public void setInstallDate(Date installDate) 
    {
        this.installDate = installDate;
    }

    public Date getInstallDate() 
    {
        return installDate;
    }
    public void setInstallUser(String installUser) 
    {
        this.installUser = installUser;
    }

    public String getInstallUser() 
    {
        return installUser;
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
            .append("flowNo", getFlowNo())
            .append("flowName", getFlowName())
            .append("flowType", getFlowType())
            .append("flowManager", getFlowManager())
            .append("instanceBy", getInstanceBy())
            .append("designJson", getDesignJson())
            .append("runingJson", getRuningJson())
            .append("installDate", getInstallDate())
            .append("installUser", getInstallUser())
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
