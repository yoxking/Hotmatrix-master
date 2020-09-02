package com.benet.collect.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 问卷信息对象 cct_paperinfo
 * 
 * @author yoxking
 * @date 2020-08-31
 */
public class CctPaperinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 问卷id */
    @Excel(name = "问卷id")
    private String paperNo;

    /** 问卷名称 */
    @Excel(name = "问卷名称")
    private String paperTitle;

    /** 问卷描述 */
    @Excel(name = "问卷描述")
    private String paperDesc;

    /** 问卷类别（1:手动生成，2：随机生成） */
    @Excel(name = "问卷类别", readConverterExp = "1=:手动生成，2：随机生成")
    private String paperType;

    /** 问卷类型 */
    @Excel(name = "问卷类型")
    private String classNo;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNo;

    /** 问卷总得分 */
    @Excel(name = "问卷总得分")
    private Long paperScore;

    /** 生成试题规则 */
    @Excel(name = "生成试题规则")
    private String rulesQuests;

    /** 问卷试题集 */
    @Excel(name = "问卷试题集")
    private String paperQuests;

    /** 测评对象集 */
    @Excel(name = "测评对象集")
    private String paperRusers;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enditDate;

    /** 测评时长 */
    @Excel(name = "测评时长")
    private Integer duration;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private String checkState;

    /** 分支编号 */
    @Excel(name = "分支编号")
    private String branchNo;

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
    public void setPaperNo(String paperNo) 
    {
        this.paperNo = paperNo;
    }

    public String getPaperNo() 
    {
        return paperNo;
    }
    public void setPaperTitle(String paperTitle) 
    {
        this.paperTitle = paperTitle;
    }

    public String getPaperTitle() 
    {
        return paperTitle;
    }
    public void setPaperDesc(String paperDesc) 
    {
        this.paperDesc = paperDesc;
    }

    public String getPaperDesc() 
    {
        return paperDesc;
    }
    public void setPaperType(String paperType) 
    {
        this.paperType = paperType;
    }

    public String getPaperType() 
    {
        return paperType;
    }
    public void setClassNo(String classNo) 
    {
        this.classNo = classNo;
    }

    public String getClassNo() 
    {
        return classNo;
    }
    public void setOrderNo(Integer orderNo) 
    {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() 
    {
        return orderNo;
    }
    public void setPaperScore(Long paperScore) 
    {
        this.paperScore = paperScore;
    }

    public Long getPaperScore() 
    {
        return paperScore;
    }
    public void setRulesQuests(String rulesQuests) 
    {
        this.rulesQuests = rulesQuests;
    }

    public String getRulesQuests() 
    {
        return rulesQuests;
    }
    public void setPaperQuests(String paperQuests) 
    {
        this.paperQuests = paperQuests;
    }

    public String getPaperQuests() 
    {
        return paperQuests;
    }
    public void setPaperRusers(String paperRusers) 
    {
        this.paperRusers = paperRusers;
    }

    public String getPaperRusers() 
    {
        return paperRusers;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setEnditDate(Date enditDate) 
    {
        this.enditDate = enditDate;
    }

    public Date getEnditDate() 
    {
        return enditDate;
    }
    public void setDuration(Integer duration) 
    {
        this.duration = duration;
    }

    public Integer getDuration() 
    {
        return duration;
    }
    public void setCheckState(String checkState) 
    {
        this.checkState = checkState;
    }

    public String getCheckState() 
    {
        return checkState;
    }
    public void setBranchNo(String branchNo) 
    {
        this.branchNo = branchNo;
    }

    public String getBranchNo() 
    {
        return branchNo;
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
            .append("paperNo", getPaperNo())
            .append("paperTitle", getPaperTitle())
            .append("paperDesc", getPaperDesc())
            .append("paperType", getPaperType())
            .append("classNo", getClassNo())
            .append("orderNo", getOrderNo())
            .append("paperScore", getPaperScore())
            .append("rulesQuests", getRulesQuests())
            .append("paperQuests", getPaperQuests())
            .append("paperRusers", getPaperRusers())
            .append("startDate", getStartDate())
            .append("enditDate", getEnditDate())
            .append("duration", getDuration())
            .append("checkState", getCheckState())
            .append("branchNo", getBranchNo())
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
