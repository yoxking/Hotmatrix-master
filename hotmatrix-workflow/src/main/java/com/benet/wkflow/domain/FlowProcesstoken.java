package com.benet.wkflow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;


/**
 * 流程Token对象 flow_processtoken
 * 
 * @author yoxking
 * @date 2021-02-04
 */
public class FlowProcesstoken extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** token id */
    @Excel(name = "token id")
    private String tokenNo;

    /** 流程实例Id */
    @Excel(name = "流程实例Id")
    private String pflowNo;

    /** token存活 */
    @Excel(name = "token存活")
    private String tokenAlive;

    /** token值 */
    @Excel(name = "token值")
    private Long tokenValue;

    /** 节点Id */
    @Excel(name = "节点Id")
    private String nodeId;

    /** 步数 */
    @Excel(name = "步数")
    private Long stepNumber;

    /** 前驱activity的id */
    @Excel(name = "前驱activity的id")
    private String fromActivityId;

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
    public void setTokenNo(String tokenNo) 
    {
        this.tokenNo = tokenNo;
    }

    public String getTokenNo() 
    {
        return tokenNo;
    }
    public void setPflowNo(String pflowNo) 
    {
        this.pflowNo = pflowNo;
    }

    public String getPflowNo() 
    {
        return pflowNo;
    }
    public void setTokenAlive(String tokenAlive) 
    {
        this.tokenAlive = tokenAlive;
    }

    public String getTokenAlive() 
    {
        return tokenAlive;
    }
    public void setTokenValue(Long tokenValue) 
    {
        this.tokenValue = tokenValue;
    }

    public Long getTokenValue() 
    {
        return tokenValue;
    }
    public void setNodeId(String nodeId) 
    {
        this.nodeId = nodeId;
    }

    public String getNodeId() 
    {
        return nodeId;
    }
    public void setStepNumber(Long stepNumber) 
    {
        this.stepNumber = stepNumber;
    }

    public Long getStepNumber() 
    {
        return stepNumber;
    }
    public void setFromActivityId(String fromActivityId) 
    {
        this.fromActivityId = fromActivityId;
    }

    public String getFromActivityId() 
    {
        return fromActivityId;
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
            .append("tokenNo", getTokenNo())
            .append("pflowNo", getPflowNo())
            .append("tokenAlive", getTokenAlive())
            .append("tokenValue", getTokenValue())
            .append("nodeId", getNodeId())
            .append("stepNumber", getStepNumber())
            .append("fromActivityId", getFromActivityId())
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
