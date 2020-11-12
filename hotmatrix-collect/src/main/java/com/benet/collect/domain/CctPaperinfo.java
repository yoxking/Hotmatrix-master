package com.benet.collect.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 问卷信息对象 cct_paperinfo
 * 
 * @author yoxking
 * @date 2020-11-10
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

    /** 问卷图片 */
    @Excel(name = "问卷图片")
    private String paperPoster;

    /** 问卷描述 */
    @Excel(name = "问卷描述")
    private String paperDesc;

    /** 问卷类别（1:固定问卷，2：随机问卷） */
    @Excel(name = "问卷类别", readConverterExp = "1=:固定问卷，2：随机问卷")
    private String paperType;

    /** 问卷来源 */
    @Excel(name = "问卷来源")
    private String dataFrom;

    /** 浏览权限（1:公开，2:私有） */
    @Excel(name = "浏览权限", readConverterExp = "1=:公开，2:私有")
    private String viewType;

    /** 问卷类型 */
    @Excel(name = "问卷类型")
    private String classNo;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNo;

    /** 试题数量 */
    @Excel(name = "试题数量")
    private Integer questNums;

    /** 问卷试题集 */
    @Excel(name = "问卷试题集")
    private String paperQuests;

    /** 问卷总得分 */
    @Excel(name = "问卷总得分")
    private Long paperTscore;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private String checkState;

    /** 分支 */
    @Excel(name = "分支")
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
    public void setPaperPoster(String paperPoster) 
    {
        this.paperPoster = paperPoster;
    }

    public String getPaperPoster() 
    {
        return paperPoster;
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
    public void setDataFrom(String dataFrom) 
    {
        this.dataFrom = dataFrom;
    }

    public String getDataFrom() 
    {
        return dataFrom;
    }
    public void setViewType(String viewType) 
    {
        this.viewType = viewType;
    }

    public String getViewType() 
    {
        return viewType;
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
    public void setQuestNums(Integer questNums) 
    {
        this.questNums = questNums;
    }

    public Integer getQuestNums() 
    {
        return questNums;
    }
    public void setPaperQuests(String paperQuests) 
    {
        this.paperQuests = paperQuests;
    }

    public String getPaperQuests() 
    {
        return paperQuests;
    }
    public void setPaperTscore(Long paperTscore) 
    {
        this.paperTscore = paperTscore;
    }

    public Long getPaperTscore() 
    {
        return paperTscore;
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
            .append("paperPoster", getPaperPoster())
            .append("paperDesc", getPaperDesc())
            .append("paperType", getPaperType())
            .append("dataFrom", getDataFrom())
            .append("viewType", getViewType())
            .append("classNo", getClassNo())
            .append("orderNo", getOrderNo())
            .append("questNums", getQuestNums())
            .append("paperQuests", getPaperQuests())
            .append("paperTscore", getPaperTscore())
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
