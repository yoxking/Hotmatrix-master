package com.benet.wkflow.domain;

import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;

import java.util.Date;

/**
 * 工作流程按钮对象 flw_flowbutton
 * 
 * @author yoxking
 * @date 2020-05-17
 */
public class FlwFlowbutton extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 按钮id */
    @Excel(name = "按钮id")
    private String btnNo;

    /** 按钮标题 */
    @Excel(name = "按钮标题")
    private String btnTitle;

    /** 按钮图标 */
    @Excel(name = "按钮图标")
    private String btnIcon;

    /** 按钮脚本 */
    @Excel(name = "按钮脚本")
    private String btnScript;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNo;

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
    public void setBtnNo(String btnNo) 
    {
        this.btnNo = btnNo;
    }

    public String getBtnNo() 
    {
        return btnNo;
    }
    public void setBtnTitle(String btnTitle) 
    {
        this.btnTitle = btnTitle;
    }

    public String getBtnTitle() 
    {
        return btnTitle;
    }
    public void setBtnIcon(String btnIcon) 
    {
        this.btnIcon = btnIcon;
    }

    public String getBtnIcon() 
    {
        return btnIcon;
    }
    public void setBtnScript(String btnScript) 
    {
        this.btnScript = btnScript;
    }

    public String getBtnScript() 
    {
        return btnScript;
    }
    public void setOrderNo(Integer orderNo) 
    {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() 
    {
        return orderNo;
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
            .append("btnNo", getBtnNo())
            .append("btnTitle", getBtnTitle())
            .append("btnIcon", getBtnIcon())
            .append("btnScript", getBtnScript())
            .append("orderNo", getOrderNo())
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
