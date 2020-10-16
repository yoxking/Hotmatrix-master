package com.benet.collect.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 日记信息对象 cct_blogsinfo
 * 
 * @author yoxking
 * @date 2020-10-14
 */
public class CctBlogsinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 日记id */
    @Excel(name = "日记id")
    private String blogNo;

    /** 日记标题 */
    @Excel(name = "日记标题")
    private String blogTitle;

    /** 日记类型 */
    @Excel(name = "日记类型")
    private String classNo;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private String ruserNo;

    /** 日记内容 */
    @Excel(name = "日记内容")
    private String blogContent;

    /** 点赞次数 */
    @Excel(name = "点赞次数")
    private Integer dolikeHit;

    /** 转发次数 */
    @Excel(name = "转发次数")
    private Integer repostHit;

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
    public void setBlogNo(String blogNo) 
    {
        this.blogNo = blogNo;
    }

    public String getBlogNo() 
    {
        return blogNo;
    }
    public void setBlogTitle(String blogTitle) 
    {
        this.blogTitle = blogTitle;
    }

    public String getBlogTitle() 
    {
        return blogTitle;
    }
    public void setClassNo(String classNo) 
    {
        this.classNo = classNo;
    }

    public String getClassNo() 
    {
        return classNo;
    }
    public void setRuserNo(String ruserNo) 
    {
        this.ruserNo = ruserNo;
    }

    public String getRuserNo() 
    {
        return ruserNo;
    }
    public void setBlogContent(String blogContent) 
    {
        this.blogContent = blogContent;
    }

    public String getBlogContent() 
    {
        return blogContent;
    }
    public void setDolikeHit(Integer dolikeHit) 
    {
        this.dolikeHit = dolikeHit;
    }

    public Integer getDolikeHit() 
    {
        return dolikeHit;
    }
    public void setRepostHit(Integer repostHit) 
    {
        this.repostHit = repostHit;
    }

    public Integer getRepostHit() 
    {
        return repostHit;
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
            .append("blogNo", getBlogNo())
            .append("blogTitle", getBlogTitle())
            .append("classNo", getClassNo())
            .append("ruserNo", getRuserNo())
            .append("blogContent", getBlogContent())
            .append("dolikeHit", getDolikeHit())
            .append("repostHit", getRepostHit())
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
