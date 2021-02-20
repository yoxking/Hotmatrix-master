package com.benet.collect.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 测评信息对象 coct_examsinfo
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public class CoctExamsinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 测评id */
    @Excel(name = "测评id")
    private String examsNo;

    /** 测评名称 */
    @Excel(name = "测评名称")
    private String examsTitle;

    /** 测评图片 */
    @Excel(name = "测评图片")
    private String examsPoster;

    /** 测评描述 */
    @Excel(name = "测评描述")
    private String examsDesc;

    /** 测评类别 */
    @Excel(name = "测评类别")
    private String examsType;

    /** 数据来源 */
    @Excel(name = "数据来源")
    private String dataFrom;

    /** 浏览权限（1:公开，2:私有） */
    @Excel(name = "浏览权限", readConverterExp = "1=:公开，2:私有")
    private String viewType;

    /** 测评类型 */
    @Excel(name = "测评类型")
    private String classNo;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNo;

    /** 问卷编号 */
    @Excel(name = "问卷编号")
    private String paperNo;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enditTime;

    /** 测试次数 */
    @Excel(name = "测试次数")
    private Integer examsTimes;

    /** 测评设置 */
    @Excel(name = "测评设置")
    private String examsProfile;

    /** 测评二维码 */
    @Excel(name = "测评二维码")
    private String examsQrcode;

    /** 测评时长 */
    @Excel(name = "测评时长")
    private Integer examsDuration;

    /** 合格分数 */
    @Excel(name = "合格分数")
    private Integer examsPassmark;

    /** 测评对象 */
    @Excel(name = "测评对象")
    private String examsRusers;

    /** 测评管理员 */
    @Excel(name = "测评管理员")
    private String examsAdmin;

    /** 测评规则 */
    @Excel(name = "测评规则")
    private String examsRules;

    /** 测评状态（1进行中 0已结束） */
    @Excel(name = "测评状态", readConverterExp = "1=进行中,0=已结束")
    private String examsState;

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
    public void setExamsNo(String examsNo) 
    {
        this.examsNo = examsNo;
    }

    public String getExamsNo() 
    {
        return examsNo;
    }
    public void setExamsTitle(String examsTitle) 
    {
        this.examsTitle = examsTitle;
    }

    public String getExamsTitle() 
    {
        return examsTitle;
    }
    public void setExamsPoster(String examsPoster) 
    {
        this.examsPoster = examsPoster;
    }

    public String getExamsPoster() 
    {
        return examsPoster;
    }
    public void setExamsDesc(String examsDesc) 
    {
        this.examsDesc = examsDesc;
    }

    public String getExamsDesc() 
    {
        return examsDesc;
    }
    public void setExamsType(String examsType) 
    {
        this.examsType = examsType;
    }

    public String getExamsType() 
    {
        return examsType;
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
    public void setPaperNo(String paperNo) 
    {
        this.paperNo = paperNo;
    }

    public String getPaperNo() 
    {
        return paperNo;
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
    public void setExamsTimes(Integer examsTimes) 
    {
        this.examsTimes = examsTimes;
    }

    public Integer getExamsTimes() 
    {
        return examsTimes;
    }
    public void setExamsProfile(String examsProfile) 
    {
        this.examsProfile = examsProfile;
    }

    public String getExamsProfile() 
    {
        return examsProfile;
    }
    public void setExamsQrcode(String examsQrcode) 
    {
        this.examsQrcode = examsQrcode;
    }

    public String getExamsQrcode() 
    {
        return examsQrcode;
    }
    public void setExamsDuration(Integer examsDuration) 
    {
        this.examsDuration = examsDuration;
    }

    public Integer getExamsDuration() 
    {
        return examsDuration;
    }
    public void setExamsPassmark(Integer examsPassmark) 
    {
        this.examsPassmark = examsPassmark;
    }

    public Integer getExamsPassmark() 
    {
        return examsPassmark;
    }
    public void setExamsRusers(String examsRusers) 
    {
        this.examsRusers = examsRusers;
    }

    public String getExamsRusers() 
    {
        return examsRusers;
    }
    public void setExamsAdmin(String examsAdmin) 
    {
        this.examsAdmin = examsAdmin;
    }

    public String getExamsAdmin() 
    {
        return examsAdmin;
    }
    public void setExamsRules(String examsRules) 
    {
        this.examsRules = examsRules;
    }

    public String getExamsRules() 
    {
        return examsRules;
    }
    public void setExamsState(String examsState) 
    {
        this.examsState = examsState;
    }

    public String getExamsState() 
    {
        return examsState;
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
            .append("examsNo", getExamsNo())
            .append("examsTitle", getExamsTitle())
            .append("examsPoster", getExamsPoster())
            .append("examsDesc", getExamsDesc())
            .append("examsType", getExamsType())
            .append("dataFrom", getDataFrom())
            .append("viewType", getViewType())
            .append("classNo", getClassNo())
            .append("orderNo", getOrderNo())
            .append("paperNo", getPaperNo())
            .append("startTime", getStartTime())
            .append("enditTime", getEnditTime())
            .append("examsTimes", getExamsTimes())
            .append("examsProfile", getExamsProfile())
            .append("examsQrcode", getExamsQrcode())
            .append("examsDuration", getExamsDuration())
            .append("examsPassmark", getExamsPassmark())
            .append("examsRusers", getExamsRusers())
            .append("examsAdmin", getExamsAdmin())
            .append("examsRules", getExamsRules())
            .append("examsState", getExamsState())
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
