package com.benet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 内容信息对象 sys_contentinfo
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public class SysContentinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 内容ID */
    @Excel(name = "内容ID")
    private String contzNo;

    /** 类型ID */
    @Excel(name = "类型ID")
    private String classNo;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 发布时间 */
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pubdate;

    /** 海报 */
    @Excel(name = "海报")
    private String poster;

    /** 简介 */
    @Excel(name = "简介")
    private String abstracts;

    /** 正文内容 */
    @Excel(name = "正文内容")
    private String ncontents;

    /** 点击次数 */
    @Excel(name = "点击次数")
    private Integer hitCount;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private String checkState;

    /** 分支编号 */
    @Excel(name = "分支编号")
    private String branchNo;

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
    public void setContzNo(String contzNo) 
    {
        this.contzNo = contzNo;
    }

    public String getContzNo() 
    {
        return contzNo;
    }
    public void setClassNo(String classNo) 
    {
        this.classNo = classNo;
    }

    public String getClassNo() 
    {
        return classNo;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setPubdate(Date pubdate) 
    {
        this.pubdate = pubdate;
    }

    public Date getPubdate() 
    {
        return pubdate;
    }
    public void setPoster(String poster) 
    {
        this.poster = poster;
    }

    public String getPoster() 
    {
        return poster;
    }
    public void setAbstracts(String abstracts)
    {
        this.abstracts = abstracts;
    }

    public String getAbstracts()
    {
        return abstracts;
    }
    public void setNcontent(String ncontents)
    {
        this.ncontents = ncontents;
    }

    public String getNcontents()
    {
        return ncontents;
    }
    public void setHitCount(Integer hitCount) 
    {
        this.hitCount = hitCount;
    }

    public Integer getHitCount() 
    {
        return hitCount;
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
            .append("contzNo", getContzNo())
            .append("classNo", getClassNo())
            .append("title", getTitle())
            .append("author", getAuthor())
            .append("pubdate", getPubdate())
            .append("poster", getPoster())
            .append("abstract", getAbstracts())
            .append("ncontent", getNcontents())
            .append("hitCount", getHitCount())
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
