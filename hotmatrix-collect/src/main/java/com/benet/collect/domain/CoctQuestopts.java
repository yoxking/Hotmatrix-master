package com.benet.collect.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 测题选项对象 coct_questopts
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public class CoctQuestopts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 选项id */
    @Excel(name = "选项id")
    private String optsNo;

    /** 选项名称 */
    @Excel(name = "选项名称")
    private String optsTitle;

    /** 选项索引 */
    @Excel(name = "选项索引")
    private String optsIndex;

    /** 选项图片 */
    @Excel(name = "选项图片")
    private String optsImage;

    /** 选项描述 */
    @Excel(name = "选项描述")
    private String optsDesc;

    /** 试题编号 */
    @Excel(name = "试题编号")
    private String questNo;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNo;

    /** 选项得分 */
    @Excel(name = "选项得分")
    private Long optsScore;

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
    public void setOptsNo(String optsNo) 
    {
        this.optsNo = optsNo;
    }

    public String getOptsNo() 
    {
        return optsNo;
    }
    public void setOptsTitle(String optsTitle) 
    {
        this.optsTitle = optsTitle;
    }

    public String getOptsTitle() 
    {
        return optsTitle;
    }
    public void setOptsIndex(String optsIndex) 
    {
        this.optsIndex = optsIndex;
    }

    public String getOptsIndex() 
    {
        return optsIndex;
    }
    public void setOptsImage(String optsImage) 
    {
        this.optsImage = optsImage;
    }

    public String getOptsImage() 
    {
        return optsImage;
    }
    public void setOptsDesc(String optsDesc) 
    {
        this.optsDesc = optsDesc;
    }

    public String getOptsDesc() 
    {
        return optsDesc;
    }
    public void setQuestNo(String questNo) 
    {
        this.questNo = questNo;
    }

    public String getQuestNo() 
    {
        return questNo;
    }
    public void setOrderNo(Integer orderNo) 
    {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() 
    {
        return orderNo;
    }
    public void setOptsScore(Long optsScore) 
    {
        this.optsScore = optsScore;
    }

    public Long getOptsScore() 
    {
        return optsScore;
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
            .append("optsNo", getOptsNo())
            .append("optsTitle", getOptsTitle())
            .append("optsIndex", getOptsIndex())
            .append("optsImage", getOptsImage())
            .append("optsDesc", getOptsDesc())
            .append("questNo", getQuestNo())
            .append("orderNo", getOrderNo())
            .append("optsScore", getOptsScore())
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
