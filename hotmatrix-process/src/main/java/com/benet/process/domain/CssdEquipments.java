package com.benet.process.domain;

import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 器械信息对象 cssd_equipments
 * 
 * @author yoxking
 * @date 2020-12-27
 */
public class CssdEquipments extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 器械id */
    @Excel(name = "器械id")
    private String equipNo;

    /** 器械中文名称 */
    @Excel(name = "器械中文名称")
    private String equipCname;

    /** 器械英文名称 */
    @Excel(name = "器械英文名称")
    private String equipEname;

    /** 器械照片 */
    @Excel(name = "器械照片")
    private String equipImage;

    /** 器械类别（1器械物品 2耗材物品 3一次性物品 ） */
    @Excel(name = "器械类别", readConverterExp = "1=器械物品,2=耗材物品,3=一次性物品")
    private String equipType;

    /** 器械规格 */
    @Excel(name = "器械规格")
    private String equipSpec;

    /** 器械类型 */
    @Excel(name = "器械类型")
    private String classNo;

    /** 库房编号 */
    @Excel(name = "库房编号")
    private String storeNo;

    /** 生产商编号 */
    @Excel(name = "生产商编号")
    private String prodNo;

    /** 供应商编号 */
    @Excel(name = "供应商编号")
    private String suppNo;

    /** 期限编号 */
    @Excel(name = "期限编号")
    private String termNo;

    /** 单位编号 */
    @Excel(name = "单位编号")
    private String unitNo;

    /** 单价 */
    @Excel(name = "单价")
    private String unitPrice;

    /** 工作量 */
    @Excel(name = "工作量")
    private String workLoad;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNo;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private String checkState;

    /** 科室编号 */
    @Excel(name = "科室编号")
    private String branchNo;

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
    public void setEquipNo(String equipNo) 
    {
        this.equipNo = equipNo;
    }

    public String getEquipNo() 
    {
        return equipNo;
    }
    public void setEquipCname(String equipCname) 
    {
        this.equipCname = equipCname;
    }

    public String getEquipCname() 
    {
        return equipCname;
    }
    public void setEquipEname(String equipEname) 
    {
        this.equipEname = equipEname;
    }

    public String getEquipEname() 
    {
        return equipEname;
    }
    public void setEquipImage(String equipImage) 
    {
        this.equipImage = equipImage;
    }

    public String getEquipImage() 
    {
        return equipImage;
    }
    public void setEquipType(String equipType) 
    {
        this.equipType = equipType;
    }

    public String getEquipType() 
    {
        return equipType;
    }
    public void setEquipSpec(String equipSpec) 
    {
        this.equipSpec = equipSpec;
    }

    public String getEquipSpec() 
    {
        return equipSpec;
    }
    public void setClassNo(String classNo) 
    {
        this.classNo = classNo;
    }

    public String getClassNo() 
    {
        return classNo;
    }
    public void setStoreNo(String storeNo) 
    {
        this.storeNo = storeNo;
    }

    public String getStoreNo() 
    {
        return storeNo;
    }
    public void setProdNo(String prodNo) 
    {
        this.prodNo = prodNo;
    }

    public String getProdNo() 
    {
        return prodNo;
    }
    public void setSuppNo(String suppNo) 
    {
        this.suppNo = suppNo;
    }

    public String getSuppNo() 
    {
        return suppNo;
    }
    public void setTermNo(String termNo) 
    {
        this.termNo = termNo;
    }

    public String getTermNo() 
    {
        return termNo;
    }
    public void setUnitNo(String unitNo) 
    {
        this.unitNo = unitNo;
    }

    public String getUnitNo() 
    {
        return unitNo;
    }
    public void setUnitPrice(String unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public String getUnitPrice() 
    {
        return unitPrice;
    }
    public void setWorkLoad(String workLoad) 
    {
        this.workLoad = workLoad;
    }

    public String getWorkLoad() 
    {
        return workLoad;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("equipNo", getEquipNo())
            .append("equipCname", getEquipCname())
            .append("equipEname", getEquipEname())
            .append("equipImage", getEquipImage())
            .append("equipType", getEquipType())
            .append("equipSpec", getEquipSpec())
            .append("classNo", getClassNo())
            .append("storeNo", getStoreNo())
            .append("prodNo", getProdNo())
            .append("suppNo", getSuppNo())
            .append("termNo", getTermNo())
            .append("unitNo", getUnitNo())
            .append("unitPrice", getUnitPrice())
            .append("workLoad", getWorkLoad())
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
