package com.benet.collect.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 活动信息对象 cct_salonsinfo
 * 
 * @author yoxking
 * @date 2020-10-14
 */
public class CctSalonsinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 活动id */
    @Excel(name = "活动id")
    private String salonNo;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String salonName;

    /** 活动类型 */
    @Excel(name = "活动类型")
    private String classNo;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enditTime;

    /** 活动地址 */
    @Excel(name = "活动地址")
    private String salonAddress;

    /** 预约人员 */
    @Excel(name = "预约人员")
    private String registRusers;

    /** 活动内容 */
    @Excel(name = "活动内容")
    private String salonContent;

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
    public void setSalonNo(String salonNo) 
    {
        this.salonNo = salonNo;
    }

    public String getSalonNo() 
    {
        return salonNo;
    }
    public void setSalonName(String salonName) 
    {
        this.salonName = salonName;
    }

    public String getSalonName() 
    {
        return salonName;
    }
    public void setClassNo(String classNo) 
    {
        this.classNo = classNo;
    }

    public String getClassNo() 
    {
        return classNo;
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
    public void setSalonAddress(String salonAddress) 
    {
        this.salonAddress = salonAddress;
    }

    public String getSalonAddress() 
    {
        return salonAddress;
    }
    public void setRegistRusers(String registRusers) 
    {
        this.registRusers = registRusers;
    }

    public String getRegistRusers() 
    {
        return registRusers;
    }
    public void setSalonContent(String salonContent) 
    {
        this.salonContent = salonContent;
    }

    public String getSalonContent() 
    {
        return salonContent;
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
            .append("salonNo", getSalonNo())
            .append("salonName", getSalonName())
            .append("classNo", getClassNo())
            .append("startTime", getStartTime())
            .append("enditTime", getEnditTime())
            .append("salonAddress", getSalonAddress())
            .append("registRusers", getRegistRusers())
            .append("salonContent", getSalonContent())
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
