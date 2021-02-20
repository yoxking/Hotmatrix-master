package com.benet.wkflow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;


/**
 * 流程执行跟踪对象 flow_historytrace
 * 
 * @author yoxking
 * @date 2021-02-04
 */
public class FlowHistorytrace extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 跟踪编号 */
    @Excel(name = "跟踪编号")
    private String traceNo;

    /** 流程实例Id */
    @Excel(name = "流程实例Id")
    private String pflowNo;

    /** 步数 */
    @Excel(name = "步数")
    private Long stepNumber;

    /** 辅助数 */
    @Excel(name = "辅助数")
    private Long minorNumber;

    /** 类别 */
    @Excel(name = "类别")
    private String traceType;

    /** 边Id */
    @Excel(name = "边Id")
    private String edgeId;

    /** 来自结点Id */
    @Excel(name = "来自结点Id")
    private String fromNodeId;

    /** 至结点Id */
    @Excel(name = "至结点Id")
    private String toNodeId;

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
    public void setTraceNo(String traceNo) 
    {
        this.traceNo = traceNo;
    }

    public String getTraceNo() 
    {
        return traceNo;
    }
    public void setPflowNo(String pflowNo) 
    {
        this.pflowNo = pflowNo;
    }

    public String getPflowNo() 
    {
        return pflowNo;
    }
    public void setStepNumber(Long stepNumber) 
    {
        this.stepNumber = stepNumber;
    }

    public Long getStepNumber() 
    {
        return stepNumber;
    }
    public void setMinorNumber(Long minorNumber) 
    {
        this.minorNumber = minorNumber;
    }

    public Long getMinorNumber() 
    {
        return minorNumber;
    }
    public void setTraceType(String traceType) 
    {
        this.traceType = traceType;
    }

    public String getTraceType() 
    {
        return traceType;
    }
    public void setEdgeId(String edgeId) 
    {
        this.edgeId = edgeId;
    }

    public String getEdgeId() 
    {
        return edgeId;
    }
    public void setFromNodeId(String fromNodeId) 
    {
        this.fromNodeId = fromNodeId;
    }

    public String getFromNodeId() 
    {
        return fromNodeId;
    }
    public void setToNodeId(String toNodeId) 
    {
        this.toNodeId = toNodeId;
    }

    public String getToNodeId() 
    {
        return toNodeId;
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
            .append("traceNo", getTraceNo())
            .append("pflowNo", getPflowNo())
            .append("stepNumber", getStepNumber())
            .append("minorNumber", getMinorNumber())
            .append("traceType", getTraceType())
            .append("edgeId", getEdgeId())
            .append("fromNodeId", getFromNodeId())
            .append("toNodeId", getToNodeId())
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
