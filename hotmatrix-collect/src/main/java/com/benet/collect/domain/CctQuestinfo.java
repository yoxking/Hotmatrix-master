package com.benet.collect.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 测评题库对象 cct_questinfo
 * 
 * @author yoxking
 * @date 2020-08-31
 */
public class CctQuestinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 试题id */
    @Excel(name = "试题id")
    private String questNo;

    /** 试题名称 */
    @Excel(name = "试题名称")
    private String questTitle;

    /** 试题类别 */
    @Excel(name = "试题类别")
    private String questType;

    /** 试题描述 */
    @Excel(name = "试题描述")
    private String questDesc;

    /** 试题类型 */
    @Excel(name = "试题类型")
    private String classNo;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNo;

    /** 是否必填（1必填 0可选填） */
    @Excel(name = "是否必填", readConverterExp = "1=必填,0=可选填")
    private String questMust;

    /** 试题总分 */
    @Excel(name = "试题总分")
    private Long questScore;

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
    public void setQuestNo(String questNo) 
    {
        this.questNo = questNo;
    }

    public String getQuestNo() 
    {
        return questNo;
    }
    public void setQuestTitle(String questTitle) 
    {
        this.questTitle = questTitle;
    }

    public String getQuestTitle() 
    {
        return questTitle;
    }
    public void setQuestType(String questType) 
    {
        this.questType = questType;
    }

    public String getQuestType() 
    {
        return questType;
    }
    public void setQuestDesc(String questDesc) 
    {
        this.questDesc = questDesc;
    }

    public String getQuestDesc() 
    {
        return questDesc;
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
    public void setQuestMust(String questMust) 
    {
        this.questMust = questMust;
    }

    public String getQuestMust() 
    {
        return questMust;
    }
    public void setQuestScore(Long questScore) 
    {
        this.questScore = questScore;
    }

    public Long getQuestScore() 
    {
        return questScore;
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
            .append("questNo", getQuestNo())
            .append("questTitle", getQuestTitle())
            .append("questType", getQuestType())
            .append("questDesc", getQuestDesc())
            .append("classNo", getClassNo())
            .append("orderNo", getOrderNo())
            .append("questMust", getQuestMust())
            .append("questScore", getQuestScore())
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
