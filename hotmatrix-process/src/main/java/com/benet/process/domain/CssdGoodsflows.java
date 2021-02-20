package com.benet.process.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 物品操作对象 cssd_goodsflows
 * 
 * @author yoxking
 * @date 2020-12-20
 */
public class CssdGoodsflows extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 流水id */
    @Excel(name = "流水id")
    private String gflowNo;

    /** 物品编号 */
    @Excel(name = "物品编号")
    private String goodsNo;

    /** 物品状态值 */
    @Excel(name = "物品状态值")
    private Integer goodsState;

    /** 操作科室 */
    @Excel(name = "操作科室")
    private String depteNo;

    /** 操作人 */
    @Excel(name = "操作人")
    private String opertNo;

    /** 操作时间 */
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date opertDate;

    /** 审核人 */
    @Excel(name = "审核人")
    private String checkNo;

    /** 审核时间 */
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkDate;

    /** 过期天数 */
    @Excel(name = "过期天数")
    private Integer expireDays;

    /** 失效时间 */
    @Excel(name = "失效时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireDate;

    /** 包装方式 */
    @Excel(name = "包装方式")
    private String packeClass;

    /** 清洗机号机次 */
    @Excel(name = "清洗机号机次")
    private String cleanType;

    /** 清洗程序/类型 */
    @Excel(name = "清洗程序/类型")
    private String cleanClass;

    /** 清洗次数 */
    @Excel(name = "清洗次数")
    private Integer cleanNum;

    /** 灭菌批次 */
    @Excel(name = "灭菌批次")
    private String steryType;

    /** 灭菌程序/类型 */
    @Excel(name = "灭菌程序/类型")
    private String steryClass;

    /** 灭菌次数 */
    @Excel(name = "灭菌次数")
    private Integer steryNum;

    /** 打印机编号 */
    @Excel(name = "打印机编号")
    private String printNo;

    /** 打印样式 */
    @Excel(name = "打印样式")
    private String printStyle;

    /** 优先/加急 */
    @Excel(name = "优先/加急")
    private Integer priority;

    /** 是否有植入物 */
    @Excel(name = "是否有植入物")
    private String hardflag;

    /** 是否借包发放 */
    @Excel(name = "是否借包发放")
    private String lendflag;

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
    public void setGflowNo(String gflowNo) 
    {
        this.gflowNo = gflowNo;
    }

    public String getGflowNo() 
    {
        return gflowNo;
    }
    public void setGoodsNo(String goodsNo) 
    {
        this.goodsNo = goodsNo;
    }

    public String getGoodsNo() 
    {
        return goodsNo;
    }
    public void setGoodsState(Integer goodsState) 
    {
        this.goodsState = goodsState;
    }

    public Integer getGoodsState() 
    {
        return goodsState;
    }
    public void setDepteNo(String depteNo) 
    {
        this.depteNo = depteNo;
    }

    public String getDepteNo() 
    {
        return depteNo;
    }
    public void setOpertNo(String opertNo) 
    {
        this.opertNo = opertNo;
    }

    public String getOpertNo() 
    {
        return opertNo;
    }
    public void setOpertDate(Date opertDate) 
    {
        this.opertDate = opertDate;
    }

    public Date getOpertDate() 
    {
        return opertDate;
    }
    public void setCheckNo(String checkNo) 
    {
        this.checkNo = checkNo;
    }

    public String getCheckNo() 
    {
        return checkNo;
    }
    public void setCheckDate(Date checkDate) 
    {
        this.checkDate = checkDate;
    }

    public Date getCheckDate() 
    {
        return checkDate;
    }
    public void setExpireDays(Integer expireDays) 
    {
        this.expireDays = expireDays;
    }

    public Integer getExpireDays() 
    {
        return expireDays;
    }
    public void setExpireDate(Date expireDate) 
    {
        this.expireDate = expireDate;
    }

    public Date getExpireDate() 
    {
        return expireDate;
    }
    public void setPackeClass(String packeClass) 
    {
        this.packeClass = packeClass;
    }

    public String getPackeClass() 
    {
        return packeClass;
    }
    public void setCleanType(String cleanType) 
    {
        this.cleanType = cleanType;
    }

    public String getCleanType() 
    {
        return cleanType;
    }
    public void setCleanClass(String cleanClass) 
    {
        this.cleanClass = cleanClass;
    }

    public String getCleanClass() 
    {
        return cleanClass;
    }
    public void setCleanNum(Integer cleanNum) 
    {
        this.cleanNum = cleanNum;
    }

    public Integer getCleanNum() 
    {
        return cleanNum;
    }
    public void setSteryType(String steryType) 
    {
        this.steryType = steryType;
    }

    public String getSteryType() 
    {
        return steryType;
    }
    public void setSteryClass(String steryClass) 
    {
        this.steryClass = steryClass;
    }

    public String getSteryClass() 
    {
        return steryClass;
    }
    public void setSteryNum(Integer steryNum) 
    {
        this.steryNum = steryNum;
    }

    public Integer getSteryNum() 
    {
        return steryNum;
    }
    public void setPrintNo(String printNo) 
    {
        this.printNo = printNo;
    }

    public String getPrintNo() 
    {
        return printNo;
    }
    public void setPrintStyle(String printStyle) 
    {
        this.printStyle = printStyle;
    }

    public String getPrintStyle() 
    {
        return printStyle;
    }
    public void setPriority(Integer priority) 
    {
        this.priority = priority;
    }

    public Integer getPriority() 
    {
        return priority;
    }
    public void setHardflag(String hardflag) 
    {
        this.hardflag = hardflag;
    }

    public String getHardflag() 
    {
        return hardflag;
    }
    public void setLendflag(String lendflag) 
    {
        this.lendflag = lendflag;
    }

    public String getLendflag() 
    {
        return lendflag;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("gflowNo", getGflowNo())
            .append("goodsNo", getGoodsNo())
            .append("goodsState", getGoodsState())
            .append("depteNo", getDepteNo())
            .append("opertNo", getOpertNo())
            .append("opertDate", getOpertDate())
            .append("checkNo", getCheckNo())
            .append("checkDate", getCheckDate())
            .append("expireDays", getExpireDays())
            .append("expireDate", getExpireDate())
            .append("packeClass", getPackeClass())
            .append("cleanType", getCleanType())
            .append("cleanClass", getCleanClass())
            .append("cleanNum", getCleanNum())
            .append("steryType", getSteryType())
            .append("steryClass", getSteryClass())
            .append("steryNum", getSteryNum())
            .append("printNo", getPrintNo())
            .append("printStyle", getPrintStyle())
            .append("priority", getPriority())
            .append("hardflag", getHardflag())
            .append("lendflag", getLendflag())
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
