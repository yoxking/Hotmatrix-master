package com.benet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;

/**
 * 代码生成业务字段对象 sys_tablefield
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public class SysTablefield extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 字段编号 */
    @Excel(name = "字段编号")
    private String fieldNo;

    /** 归属表编号 */
    @Excel(name = "归属表编号")
    private String tableNo;

    /** 字段名称 */
    @Excel(name = "字段名称")
    private String fieldName;

    /** 字段描述 */
    @Excel(name = "字段描述")
    private String fieldRemark;

    /** 字段类型 */
    @Excel(name = "字段类型")
    private String fieldType;

    /** JAVA类型 */
    @Excel(name = "JAVA类型")
    private String javaType;

    /** JAVA字段名 */
    @Excel(name = "JAVA字段名")
    private String javaField;

    /** 是否主键（1是） */
    @Excel(name = "是否主键", readConverterExp = "1=是")
    private String isPk;

    /** 是否自增（1是） */
    @Excel(name = "是否自增", readConverterExp = "1=是")
    private String isIncrement;

    /** 是否必填（1是） */
    @Excel(name = "是否必填", readConverterExp = "1=是")
    private String isRequired;

    /** 是否为插入字段（1是） */
    @Excel(name = "是否为插入字段", readConverterExp = "1=是")
    private String isInsert;

    /** 是否编辑字段（1是） */
    @Excel(name = "是否编辑字段", readConverterExp = "1=是")
    private String isEdit;

    /** 是否列表字段（1是） */
    @Excel(name = "是否列表字段", readConverterExp = "1=是")
    private String isList;

    /** 是否查询字段（1是） */
    @Excel(name = "是否查询字段", readConverterExp = "1=是")
    private String isQuery;

    /** 查询方式（等于、不等于、大于、小于、范围） */
    @Excel(name = "查询方式", readConverterExp = "等=于、不等于、大于、小于、范围")
    private String queryType;

    /** 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件） */
    @Excel(name = "显示类型", readConverterExp = "文=本框、文本域、下拉框、复选框、单选框、日期控件")
    private String htmlType;

    /** 字典类型 */
    @Excel(name = "字典类型")
    private String dictType;

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
    public void setFieldNo(String fieldNo) 
    {
        this.fieldNo = fieldNo;
    }

    public String getFieldNo() 
    {
        return fieldNo;
    }
    public void setTableNo(String tableNo) 
    {
        this.tableNo = tableNo;
    }

    public String getTableNo() 
    {
        return tableNo;
    }
    public void setFieldName(String fieldName) 
    {
        this.fieldName = fieldName;
    }

    public String getFieldName() 
    {
        return fieldName;
    }
    public void setFieldRemark(String fieldRemark) 
    {
        this.fieldRemark = fieldRemark;
    }

    public String getFieldRemark() 
    {
        return fieldRemark;
    }
    public void setFieldType(String fieldType) 
    {
        this.fieldType = fieldType;
    }

    public String getFieldType() 
    {
        return fieldType;
    }
    public void setJavaType(String javaType) 
    {
        this.javaType = javaType;
    }

    public String getJavaType() 
    {
        return javaType;
    }
    public void setJavaField(String javaField) 
    {
        this.javaField = javaField;
    }

    public String getJavaField() 
    {
        return javaField;
    }
    public void setIsPk(String isPk) 
    {
        this.isPk = isPk;
    }

    public String getIsPk() 
    {
        return isPk;
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
    public void setIsInsert(String isInsert) 
    {
        this.isInsert = isInsert;
    }

    public String getIsInsert() 
    {
        return isInsert;
    }
    public void setIsEdit(String isEdit) 
    {
        this.isEdit = isEdit;
    }

    public String getIsEdit() 
    {
        return isEdit;
    }
    public void setIsList(String isList) 
    {
        this.isList = isList;
    }

    public String getIsList() 
    {
        return isList;
    }
    public void setIsQuery(String isQuery) 
    {
        this.isQuery = isQuery;
    }

    public String getIsQuery() 
    {
        return isQuery;
    }
    public void setQueryType(String queryType) 
    {
        this.queryType = queryType;
    }

    public String getQueryType() 
    {
        return queryType;
    }
    public void setHtmlType(String htmlType) 
    {
        this.htmlType = htmlType;
    }

    public String getHtmlType() 
    {
        return htmlType;
    }
    public void setDictType(String dictType) 
    {
        this.dictType = dictType;
    }

    public String getDictType() 
    {
        return dictType;
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
            .append("fieldNo", getFieldNo())
            .append("tableNo", getTableNo())
            .append("fieldName", getFieldName())
            .append("fieldRemark", getFieldRemark())
            .append("fieldType", getFieldType())
            .append("javaType", getJavaType())
            .append("javaField", getJavaField())
            .append("isPk", getIsPk())
            .append("isIncrement", getIsIncrement())
            .append("isRequired", getIsRequired())
            .append("isInsert", getIsInsert())
            .append("isEdit", getIsEdit())
            .append("isList", getIsList())
            .append("isQuery", getIsQuery())
            .append("queryType", getQueryType())
            .append("htmlType", getHtmlType())
            .append("dictType", getDictType())
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
