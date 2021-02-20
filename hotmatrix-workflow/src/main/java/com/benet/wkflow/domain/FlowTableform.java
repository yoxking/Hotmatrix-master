package com.benet.wkflow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 单设计对象 flow_tableform
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public class FlowTableform extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 表单id */
    @Excel(name = "表单id")
    private String formNo;

    /** 表单名称 */
    @Excel(name = "表单名称")
    private String formName;

    /** 表单分类 */
    @Excel(name = "表单分类")
    private String formType;

    /** 表单HTML */
    @Excel(name = "表单HTML")
    private String formHtml;

    /** 数据源编号 */
    @Excel(name = "数据源编号")
    private String dtsrcNo;

    /** 表名 */
    @Excel(name = "表名")
    private String tableName;

    /** 子表json */
    @Excel(name = "子表json")
    private String subtbJson;

    /** 事件json */
    @Excel(name = "事件json")
    private String eventJson;

    /** 属性json */
    @Excel(name = "属性json")
    private String attribute;

    /** 有效提示 */
    @Excel(name = "有效提示")
    private String validTip;

    /** 状态：0 保存 1 编译 2作废 */
    @Excel(name = "状态：0 保存 1 编译 2作废")
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
    public void setFormNo(String formNo) 
    {
        this.formNo = formNo;
    }

    public String getFormNo() 
    {
        return formNo;
    }
    public void setFormName(String formName) 
    {
        this.formName = formName;
    }

    public String getFormName() 
    {
        return formName;
    }
    public void setFormType(String formType) 
    {
        this.formType = formType;
    }

    public String getFormType() 
    {
        return formType;
    }
    public void setFormHtml(String formHtml) 
    {
        this.formHtml = formHtml;
    }

    public String getFormHtml() 
    {
        return formHtml;
    }
    public void setDtsrcNo(String dtsrcNo) 
    {
        this.dtsrcNo = dtsrcNo;
    }

    public String getDtsrcNo() 
    {
        return dtsrcNo;
    }
    public void setTableName(String tableName) 
    {
        this.tableName = tableName;
    }

    public String getTableName() 
    {
        return tableName;
    }
    public void setSubtbJson(String subtbJson) 
    {
        this.subtbJson = subtbJson;
    }

    public String getSubtbJson() 
    {
        return subtbJson;
    }
    public void setEventJson(String eventJson) 
    {
        this.eventJson = eventJson;
    }

    public String getEventJson() 
    {
        return eventJson;
    }
    public void setAttribute(String attribute) 
    {
        this.attribute = attribute;
    }

    public String getAttribute() 
    {
        return attribute;
    }
    public void setValidTip(String validTip) 
    {
        this.validTip = validTip;
    }

    public String getValidTip() 
    {
        return validTip;
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
            .append("formNo", getFormNo())
            .append("formName", getFormName())
            .append("formType", getFormType())
            .append("formHtml", getFormHtml())
            .append("dtsrcNo", getDtsrcNo())
            .append("tableName", getTableName())
            .append("subtbJson", getSubtbJson())
            .append("eventJson", getEventJson())
            .append("attribute", getAttribute())
            .append("validTip", getValidTip())
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
