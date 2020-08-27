package com.benet.wkflow.domain;

import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;

/**
 * 流程委托对象 flw_flowentrust
 * 
 * @author yoxking
 * @date 2020-05-17
 */
public class FlwFlowentrust extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 委托id */
    @Excel(name = "委托id")
    private String entrustNo;

    /** 流程ID，如果为空表示所有流程 */
    @Excel(name = "流程ID，如果为空表示所有流程")
    private String flowNo;

    /** 委托人员ID */
    @Excel(name = "委托人员ID")
    private String fromUserno;

    /** 被委托人ID */
    @Excel(name = "被委托人ID")
    private String toUserno;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enditTime;

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
    public void setEntrustNo(String entrustNo) 
    {
        this.entrustNo = entrustNo;
    }

    public String getEntrustNo() 
    {
        return entrustNo;
    }
    public void setFlowNo(String flowNo) 
    {
        this.flowNo = flowNo;
    }

    public String getFlowNo() 
    {
        return flowNo;
    }
    public void setFromUserno(String fromUserno) 
    {
        this.fromUserno = fromUserno;
    }

    public String getFromUserno() 
    {
        return fromUserno;
    }
    public void setToUserno(String toUserno) 
    {
        this.toUserno = toUserno;
    }

    public String getToUserno() 
    {
        return toUserno;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEnditTime(Date enditTime) 
    {
        this.enditTime = enditTime;
    }

    public Date getEnditTime() 
    {
        return enditTime;
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
            .append("entrustNo", getEntrustNo())
            .append("flowNo", getFlowNo())
            .append("fromUserno", getFromUserno())
            .append("toUserno", getToUserno())
            .append("startTime", getStartTime())
            .append("enditTime", getEnditTime())
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
