package com.benet.wkflow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 工作项对象 flow_workitemflow
 * 
 * @author yoxking
 * @date 2021-02-04
 */
public class FlowWorkitemflow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 表单id */
    @Excel(name = "表单id")
    private String iflowNo;

    /** 任务实例Id */
    @Excel(name = "任务实例Id")
    private String tflowNo;

    /** 状态,0=Initialized ,1=Running, 7=Completed,9=Canceled */
    @Excel(name = "状态,0=Initialized ,1=Running, 7=Completed,9=Canceled")
    private String itemState;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 签收时间 */
    @Excel(name = "签收时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date claimedTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 操作者Id */
    @Excel(name = "操作者Id")
    private String actorNo;

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
    public void setIflowNo(String iflowNo) 
    {
        this.iflowNo = iflowNo;
    }

    public String getIflowNo() 
    {
        return iflowNo;
    }
    public void setTflowNo(String tflowNo) 
    {
        this.tflowNo = tflowNo;
    }

    public String getTflowNo() 
    {
        return tflowNo;
    }
    public void setItemState(String itemState) 
    {
        this.itemState = itemState;
    }

    public String getItemState() 
    {
        return itemState;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setClaimedTime(Date claimedTime) 
    {
        this.claimedTime = claimedTime;
    }

    public Date getClaimedTime() 
    {
        return claimedTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setActorNo(String actorNo) 
    {
        this.actorNo = actorNo;
    }

    public String getActorNo() 
    {
        return actorNo;
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
            .append("iflowNo", getIflowNo())
            .append("tflowNo", getTflowNo())
            .append("itemState", getItemState())
            .append("createdTime", getCreatedTime())
            .append("claimedTime", getClaimedTime())
            .append("endTime", getEndTime())
            .append("actorNo", getActorNo())
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
