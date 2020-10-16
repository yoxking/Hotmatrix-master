package com.benet.collect.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 预约信息对象 cct_registflows
 * 
 * @author yoxking
 * @date 2020-10-14
 */
public class CctRegistflows extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 预约id */
    @Excel(name = "预约id")
    private String registNo;

    /** 预约类型 */
    @Excel(name = "预约类型")
    private Integer registType;

    /** 预约时间 */
    @Excel(name = "预约时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registTime;

    /** 数据来源 */
    @Excel(name = "数据来源")
    private String dataFrom;

    /** 专家编号 */
    @Excel(name = "专家编号")
    private String expertNo;

    /** 预约人编号 */
    @Excel(name = "预约人编号")
    private String ruserNo;

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
    public void setRegistNo(String registNo) 
    {
        this.registNo = registNo;
    }

    public String getRegistNo() 
    {
        return registNo;
    }
    public void setRegistType(Integer registType) 
    {
        this.registType = registType;
    }

    public Integer getRegistType() 
    {
        return registType;
    }
    public void setRegistTime(Date registTime) 
    {
        this.registTime = registTime;
    }

    public Date getRegistTime() 
    {
        return registTime;
    }
    public void setDataFrom(String dataFrom) 
    {
        this.dataFrom = dataFrom;
    }

    public String getDataFrom() 
    {
        return dataFrom;
    }
    public void setExpertNo(String expertNo) 
    {
        this.expertNo = expertNo;
    }

    public String getExpertNo() 
    {
        return expertNo;
    }
    public void setRuserNo(String ruserNo) 
    {
        this.ruserNo = ruserNo;
    }

    public String getRuserNo() 
    {
        return ruserNo;
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
            .append("registNo", getRegistNo())
            .append("registType", getRegistType())
            .append("registTime", getRegistTime())
            .append("dataFrom", getDataFrom())
            .append("expertNo", getExpertNo())
            .append("ruserNo", getRuserNo())
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
