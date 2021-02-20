package com.benet.wkflow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 单字段对象 flow_tabcolumn
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public class FlowTabcolumn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String columnNo;

    /** 表单编号 */
    @Excel(name = "表单编号")
    private String tbformNo;

    /** 列名 */
    @Excel(name = "列名")
    private String columnName;

    /** 列编码 */
    @Excel(name = "列编码")
    private String columnCode;

    /** 类型 */
    @Excel(name = "类型")
    private String columnType;

    /** 说明 */
    @Excel(name = "说明")
    private String columnComment;

    /** 长度 */
    @Excel(name = "长度")
    private Integer dataLength;

    /** 默认值 */
    @Excel(name = "默认值")
    private String dataDefault;

    /** 是否主键 */
    @Excel(name = "是否主键")
    private String isPrimkey;

    /** 是否自增 */
    @Excel(name = "是否自增")
    private String isIncrement;

    /** 是否必填 */
    @Excel(name = "是否必填")
    private String isRequired;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNo;

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
    public void setColumnNo(String columnNo) 
    {
        this.columnNo = columnNo;
    }

    public String getColumnNo() 
    {
        return columnNo;
    }
    public void setTbformNo(String tbformNo) 
    {
        this.tbformNo = tbformNo;
    }

    public String getTbformNo() 
    {
        return tbformNo;
    }
    public void setColumnName(String columnName) 
    {
        this.columnName = columnName;
    }

    public String getColumnName() 
    {
        return columnName;
    }
    public void setColumnCode(String columnCode) 
    {
        this.columnCode = columnCode;
    }

    public String getColumnCode() 
    {
        return columnCode;
    }
    public void setColumnType(String columnType) 
    {
        this.columnType = columnType;
    }

    public String getColumnType() 
    {
        return columnType;
    }
    public void setColumnComment(String columnComment) 
    {
        this.columnComment = columnComment;
    }

    public String getColumnComment() 
    {
        return columnComment;
    }
    public void setDataLength(Integer dataLength) 
    {
        this.dataLength = dataLength;
    }

    public Integer getDataLength() 
    {
        return dataLength;
    }
    public void setDataDefault(String dataDefault) 
    {
        this.dataDefault = dataDefault;
    }

    public String getDataDefault() 
    {
        return dataDefault;
    }
    public void setIsPrimkey(String isPrimkey) 
    {
        this.isPrimkey = isPrimkey;
    }

    public String getIsPrimkey() 
    {
        return isPrimkey;
    }
    public void setIsIncrement(String isIncrement) 
    {
        this.isIncrement = isIncrement;
    }

    public String getIsIncrement() 
    {
        return isIncrement;
    }
    public void setIsRequired(String isRequired) 
    {
        this.isRequired = isRequired;
    }

    public String getIsRequired() 
    {
        return isRequired;
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
            .append("columnNo", getColumnNo())
            .append("tbformNo", getTbformNo())
            .append("columnName", getColumnName())
            .append("columnCode", getColumnCode())
            .append("columnType", getColumnType())
            .append("columnComment", getColumnComment())
            .append("dataLength", getDataLength())
            .append("dataDefault", getDataDefault())
            .append("isPrimkey", getIsPrimkey())
            .append("isIncrement", getIsIncrement())
            .append("isRequired", getIsRequired())
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
