package com.benet.process.domain;

import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 出入库操作对象 cssd_stockflows
 * 
 * @author yoxking
 * @date 2020-12-27
 */
public class CssdStockflows extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 流水id */
    @Excel(name = "流水id")
    private String sflowNo;

    /** 类别（11入库 12出库 13盘点 14报损） */
    @Excel(name = "类别", readConverterExp = "1=1入库,1=2出库,1=3盘点,1=4报损")
    private String sflowType;

    /** 库房id */
    @Excel(name = "库房id")
    private String storeNo;

    /** 器材编号 */
    @Excel(name = "器材编号")
    private String equipNo;

    /** 器械类别（1器械物品 2耗材物品 3一次性物品 ） */
    @Excel(name = "器械类别", readConverterExp = "1=器械物品,2=耗材物品,3=一次性物品")
    private String equipType;

    /** 器材数量 */
    @Excel(name = "器材数量")
    private Integer equipNum;

    /** 生产批次号 */
    @Excel(name = "生产批次号")
    private String produceNumber;

    /** 生产日期 */
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date produceDate;

    /** 失效日期 */
    @Excel(name = "失效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireDate;

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
    public void setSflowNo(String sflowNo) 
    {
        this.sflowNo = sflowNo;
    }

    public String getSflowNo() 
    {
        return sflowNo;
    }
    public void setSflowType(String sflowType) 
    {
        this.sflowType = sflowType;
    }

    public String getSflowType() 
    {
        return sflowType;
    }
    public void setStoreNo(String storeNo) 
    {
        this.storeNo = storeNo;
    }

    public String getStoreNo() 
    {
        return storeNo;
    }
    public void setEquipNo(String equipNo) 
    {
        this.equipNo = equipNo;
    }

    public String getEquipNo() 
    {
        return equipNo;
    }
    public void setEquipType(String equipType) 
    {
        this.equipType = equipType;
    }

    public String getEquipType() 
    {
        return equipType;
    }
    public void setEquipNum(Integer equipNum) 
    {
        this.equipNum = equipNum;
    }

    public Integer getEquipNum() 
    {
        return equipNum;
    }
    public void setProduceNumber(String produceNumber) 
    {
        this.produceNumber = produceNumber;
    }

    public String getProduceNumber() 
    {
        return produceNumber;
    }
    public void setProduceDate(Date produceDate) 
    {
        this.produceDate = produceDate;
    }

    public Date getProduceDate() 
    {
        return produceDate;
    }
    public void setExpireDate(Date expireDate) 
    {
        this.expireDate = expireDate;
    }

    public Date getExpireDate() 
    {
        return expireDate;
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
            .append("sflowNo", getSflowNo())
            .append("sflowType", getSflowType())
            .append("storeNo", getStoreNo())
            .append("equipNo", getEquipNo())
            .append("equipType", getEquipType())
            .append("equipNum", getEquipNum())
            .append("produceNumber", getProduceNumber())
            .append("produceDate", getProduceDate())
            .append("expireDate", getExpireDate())
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
