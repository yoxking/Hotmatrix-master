package com.benet.process.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;
import com.benet.common.core.domain.BaseEntity;

/**
 * 物品包信息对象 cssd_goodsinfo
 * 
 * @author yoxking
 * @date 2020-12-21
 */
public class CssdGoodsinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 物品id */
    @Excel(name = "物品id")
    private String goodsNo;

    /** 物品中文名称 */
    @Excel(name = "物品中文名称")
    private String goodsCname;

    /** 物品英文名称 */
    @Excel(name = "物品英文名称")
    private String goodsEname;

    /** 物品图片 */
    @Excel(name = "物品图片")
    private String goodsImage;

    /** 系统类型 */
    @Excel(name = "系统类型")
    private String goodsType;

    /** 物品类型 */
    @Excel(name = "物品类型")
    private String goodsClass;

    /** 默认科室 */
    @Excel(name = "默认科室")
    private String depteNo;

    /** 包装方式 */
    @Excel(name = "包装方式")
    private String packeClass;

    /** 清洗方式 */
    @Excel(name = "清洗方式")
    private String cleanType;

    /** 清洗类型/程序 */
    @Excel(name = "清洗类型/程序")
    private String cleanClass;

    /** 灭菌方式 */
    @Excel(name = "灭菌方式")
    private String steryType;

    /** 灭菌类型/程序 */
    @Excel(name = "灭菌类型/程序")
    private String steryClass;

    /** 打印机编号 */
    @Excel(name = "打印机编号")
    private String printNo;

    /** 打印样式 */
    @Excel(name = "打印样式")
    private String printStyle;

    /** 器械列表 */
    @Excel(name = "器械列表")
    private String equipTlist;

    /** 器械合计价格 */
    @Excel(name = "器械合计价格")
    private Integer equipTprice;

    /** 器械数量 */
    @Excel(name = "器械数量")
    private Integer equipTnum;

    /** 过期天数 */
    @Excel(name = "过期天数")
    private Integer expireDays;

    /** 优先/加急 */
    @Excel(name = "优先/加急")
    private Integer priority;

    /** 是否有植入物 */
    @Excel(name = "是否有植入物")
    private String hardflag;

    /** 是否借包发放 */
    @Excel(name = "是否借包发放")
    private String lendflag;

    /** 物品状态值 */
    @Excel(name = "物品状态值")
    private Integer goodsState;

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
    public void setGoodsNo(String goodsNo) 
    {
        this.goodsNo = goodsNo;
    }

    public String getGoodsNo() 
    {
        return goodsNo;
    }
    public void setGoodsCname(String goodsCname) 
    {
        this.goodsCname = goodsCname;
    }

    public String getGoodsCname() 
    {
        return goodsCname;
    }
    public void setGoodsEname(String goodsEname) 
    {
        this.goodsEname = goodsEname;
    }

    public String getGoodsEname() 
    {
        return goodsEname;
    }
    public void setGoodsImage(String goodsImage) 
    {
        this.goodsImage = goodsImage;
    }

    public String getGoodsImage() 
    {
        return goodsImage;
    }
    public void setGoodsType(String goodsType) 
    {
        this.goodsType = goodsType;
    }

    public String getGoodsType() 
    {
        return goodsType;
    }
    public void setGoodsClass(String goodsClass) 
    {
        this.goodsClass = goodsClass;
    }

    public String getGoodsClass() 
    {
        return goodsClass;
    }
    public void setDepteNo(String depteNo) 
    {
        this.depteNo = depteNo;
    }

    public String getDepteNo() 
    {
        return depteNo;
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
    public void setEquipTlist(String equipTlist) 
    {
        this.equipTlist = equipTlist;
    }

    public String getEquipTlist() 
    {
        return equipTlist;
    }
    public void setEquipTprice(Integer equipTprice) 
    {
        this.equipTprice = equipTprice;
    }

    public Integer getEquipTprice() 
    {
        return equipTprice;
    }
    public void setEquipTnum(Integer equipTnum) 
    {
        this.equipTnum = equipTnum;
    }

    public Integer getEquipTnum() 
    {
        return equipTnum;
    }
    public void setExpireDays(Integer expireDays) 
    {
        this.expireDays = expireDays;
    }

    public Integer getExpireDays() 
    {
        return expireDays;
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
    public void setGoodsState(Integer goodsState) 
    {
        this.goodsState = goodsState;
    }

    public Integer getGoodsState() 
    {
        return goodsState;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsNo", getGoodsNo())
            .append("goodsCname", getGoodsCname())
            .append("goodsEname", getGoodsEname())
            .append("goodsImage", getGoodsImage())
            .append("goodsType", getGoodsType())
            .append("goodsClass", getGoodsClass())
            .append("depteNo", getDepteNo())
            .append("packeClass", getPackeClass())
            .append("cleanType", getCleanType())
            .append("cleanClass", getCleanClass())
            .append("steryType", getSteryType())
            .append("steryClass", getSteryClass())
            .append("printNo", getPrintNo())
            .append("printStyle", getPrintStyle())
            .append("equipTlist", getEquipTlist())
            .append("equipTprice", getEquipTprice())
            .append("equipTnum", getEquipTnum())
            .append("expireDays", getExpireDays())
            .append("priority", getPriority())
            .append("hardflag", getHardflag())
            .append("lendflag", getLendflag())
            .append("goodsState", getGoodsState())
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
