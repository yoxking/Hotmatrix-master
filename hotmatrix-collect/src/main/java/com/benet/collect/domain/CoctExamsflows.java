package com.benet.collect.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 测评结果对象 coct_examsflows
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public class CoctExamsflows extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 流水编号 */
    @Excel(name = "流水编号")
    private String mflowNo;

    /** 测评编号 */
    @Excel(name = "测评编号")
    private String examsNo;

    /** 问卷编号 */
    @Excel(name = "问卷编号")
    private String paperNo;

    /** 人员编号 */
    @Excel(name = "人员编号")
    private String ruserNo;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enditTime;

    /** 测评用时 */
    @Excel(name = "测评用时")
    private Long examsDuration;

    /** 测评得分 */
    @Excel(name = "测评得分")
    private Long examsTscore;

    /** 测评状态 */
    @Excel(name = "测评状态")
    private String checkState;

    /** 状态 */
    @Excel(name = "状态")
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

    /** 备注 */
    @Excel(name = "备注")
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
    public void setMflowNo(String mflowNo) 
    {
        this.mflowNo = mflowNo;
    }

    public String getMflowNo() 
    {
        return mflowNo;
    }
    public void setExamsNo(String examsNo) 
    {
        this.examsNo = examsNo;
    }

    public String getExamsNo() 
    {
        return examsNo;
    }
    public void setPaperNo(String paperNo) 
    {
        this.paperNo = paperNo;
    }

    public String getPaperNo() 
    {
        return paperNo;
    }
    public void setRuserNo(String ruserNo) 
    {
        this.ruserNo = ruserNo;
    }

    public String getRuserNo() 
    {
        return ruserNo;
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
    public void setExamsDuration(Long examsDuration) 
    {
        this.examsDuration = examsDuration;
    }

    public Long getExamsDuration() 
    {
        return examsDuration;
    }
    public void setExamsTscore(Long examsTscore) 
    {
        this.examsTscore = examsTscore;
    }

    public Long getExamsTscore() 
    {
        return examsTscore;
    }
    public void setCheckState(String checkState) 
    {
        this.checkState = checkState;
    }

    public String getCheckState() 
    {
        return checkState;
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
            .append("mflowNo", getMflowNo())
            .append("examsNo", getExamsNo())
            .append("paperNo", getPaperNo())
            .append("ruserNo", getRuserNo())
            .append("startTime", getStartTime())
            .append("enditTime", getEnditTime())
            .append("examsDuration", getExamsDuration())
            .append("examsTscore", getExamsTscore())
<<<<<<< HEAD:hotmatrix-collect/src/main/java/com/benet/collect/domain/CctExamflows.java
=======
            .append("mflowState", getMflowState())
>>>>>>> dev:hotmatrix-collect/src/main/java/com/benet/collect/domain/CoctExamsflows.java
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
