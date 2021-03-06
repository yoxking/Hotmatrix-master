package com.benet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;

/**
 * 机构信息对象 sys_organizinfo
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public class SysOrganizinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 部门id */
    @Excel(name = "部门id")
    private String organizNo;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String organizName;

    /** 父部门id */
    @Excel(name = "父部门id")
    private String parentNo;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNo;

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
    public void setOrganizNo(String organizNo) 
    {
        this.organizNo = organizNo;
    }

    public String getOrganizNo() 
    {
        return organizNo;
    }
    public void setOrganizName(String organizName) 
    {
        this.organizName = organizName;
    }

    public String getOrganizName() 
    {
        return organizName;
    }
    public void setParentNo(String parentNo) 
    {
        this.parentNo = parentNo;
    }

    public String getParentNo() 
    {
        return parentNo;
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
            .append("organizNo", getOrganizNo())
            .append("organizName", getOrganizName())
            .append("parentNo", getParentNo())
            .append("orderNo", getOrderNo())
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
