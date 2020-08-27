package com.benet.wkflow.domain;

import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;

/**
 * 单字段对象 flw_tabcolumn
 * 
 * @author yoxking
 * @date 2020-05-23
 */
public class FlwTabcolumn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 字段编号 */
    @Excel(name = "字段编号")
    private String columnNo;

    /** 表单编号 */
    @Excel(name = "表单编号")
    private String tbformNo;

    /** 字段名称 */
    @Excel(name = "字段名称")
    private String columnName;

    /** 字段代码 */
    @Excel(name = "字段代码")
    private String columnCode;

    /** 字段类型 */
    @Excel(name = "字段类型")
    private String columnType;

    /** 字段描述 */
    @Excel(name = "字段描述")
    private String columnComment;

    /** 长度 */
    @Excel(name = "长度")
    private Long dataLength;

    /** 默认值 */
    @Excel(name = "默认值")
    private String dataDefault;

    /** 是否主键（1是） */
    @Excel(name = "是否主键", readConverterExp = "1=是")
    private String isPrimkey;

    /** 是否自增（1是） */
    @Excel(name = "是否自增", readConverterExp = "1=是")
    private String isIncrement;

    /** 是否必填（1是） */
    @Excel(name = "是否必填", readConverterExp = "1=是")
    private String isRequired;

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
    public void setDataLength(Long dataLength) 
    {
        this.dataLength = dataLength;
    }

    public Long getDataLength() 
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
