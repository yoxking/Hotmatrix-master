package com.benet.collect.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 答题结果对象 coct_questflows
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public class CoctQuestflows extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 答题编号 */
    @Excel(name = "答题编号")
    private String qflowNo;

    /** 测评编号 */
    @Excel(name = "测评编号")
    private String mflowNo;

    /** 题型编号 */
    @Excel(name = "题型编号")
    private String questNo;

    /** 答题值 */
    @Excel(name = "答题值")
    private String qtoptValue;

    /** 答题得分 */
    @Excel(name = "答题得分")
    private Long qtoptScore;

    /** 答题标志 */
    @Excel(name = "答题标志")
    private String qtoptFlag;

    /** 答题评论 */
    @Excel(name = "答题评论")
    private String qtoptView;

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
    public void setQflowNo(String qflowNo) 
    {
        this.qflowNo = qflowNo;
    }

    public String getQflowNo() 
    {
        return qflowNo;
    }
    public void setMflowNo(String mflowNo) 
    {
        this.mflowNo = mflowNo;
    }

    public String getMflowNo() 
    {
        return mflowNo;
    }
    public void setQuestNo(String questNo) 
    {
        this.questNo = questNo;
    }

    public String getQuestNo() 
    {
        return questNo;
    }
    public void setQtoptValue(String qtoptValue) 
    {
        this.qtoptValue = qtoptValue;
    }

    public String getQtoptValue() 
    {
        return qtoptValue;
    }
    public void setQtoptScore(Long qtoptScore) 
    {
        this.qtoptScore = qtoptScore;
    }

    public Long getQtoptScore() 
    {
        return qtoptScore;
    }
    public void setQtoptFlag(String qtoptFlag) 
    {
        this.qtoptFlag = qtoptFlag;
    }

    public String getQtoptFlag() 
    {
        return qtoptFlag;
    }
    public void setQtoptView(String qtoptView) 
    {
        this.qtoptView = qtoptView;
    }

    public String getQtoptView() 
    {
        return qtoptView;
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
            .append("qflowNo", getQflowNo())
            .append("mflowNo", getMflowNo())
            .append("questNo", getQuestNo())
            .append("qtoptValue", getQtoptValue())
            .append("qtoptScore", getQtoptScore())
            .append("qtoptFlag", getQtoptFlag())
            .append("qtoptView", getQtoptView())
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
