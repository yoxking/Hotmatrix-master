package com.benet.wkflow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 数据源对象 flow_datasource
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public class FlowDatasource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String dtsrcNo;

    /** 数据源名称 */
    @Excel(name = "数据源名称")
    private String dtsrcName;

    /** 类型 */
    @Excel(name = "类型")
    private String dtsrcType;

    /** 地址 */
    @Excel(name = "地址")
    private String dtsrcUrl;

    /** 名称 */
    @Excel(name = "名称")
    private String dbaseName;

    /** 账号 */
    @Excel(name = "账号")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNo;

    /** 分支 */
    @Excel(name = "分支")
    private String branchNo;

    /** 状态 */
    @Excel(name = "状态")
    private String checkState;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新人 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    /** 删除标志 */
    @Excel(name = "删除标志")
    private String deleteFlag;

    /** 备注 */
    @Excel(name = "备注")
    private String comments;

    /** 应用编号 */
    @Excel(name = "应用编号")
    private String appCode;

    /** 版本 */
    @Excel(name = "版本")
    private Long version;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDtsrcNo(String dtsrcNo) 
    {
        this.dtsrcNo = dtsrcNo;
    }

    public String getDtsrcNo() 
    {
        return dtsrcNo;
    }
    public void setDtsrcName(String dtsrcName) 
    {
        this.dtsrcName = dtsrcName;
    }

    public String getDtsrcName() 
    {
        return dtsrcName;
    }
    public void setDtsrcType(String dtsrcType) 
    {
        this.dtsrcType = dtsrcType;
    }

    public String getDtsrcType() 
    {
        return dtsrcType;
    }
    public void setDtsrcUrl(String dtsrcUrl) 
    {
        this.dtsrcUrl = dtsrcUrl;
    }

    public String getDtsrcUrl() 
    {
        return dtsrcUrl;
    }
    public void setDbaseName(String dbaseName) 
    {
        this.dbaseName = dbaseName;
    }

    public String getDbaseName() 
    {
        return dbaseName;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setOrderNo(Integer orderNo) 
    {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() 
    {
        return orderNo;
    }
    public void setBranchNo(String branchNo) 
    {
        this.branchNo = branchNo;
    }

    public String getBranchNo() 
    {
        return branchNo;
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
            .append("dtsrcNo", getDtsrcNo())
            .append("dtsrcName", getDtsrcName())
            .append("dtsrcType", getDtsrcType())
            .append("dtsrcUrl", getDtsrcUrl())
            .append("dbaseName", getDbaseName())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("orderNo", getOrderNo())
            .append("branchNo", getBranchNo())
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
