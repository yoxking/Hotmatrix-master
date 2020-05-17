package com.benet.workflow.domain;

import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import java.util.Date;

/**
 * 流程归档对象 flw_flowarchives
 * 
 * @author yoxking
 * @date 2020-05-17
 */
public class FlwFlowarchives extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 归档id */
    @Excel(name = "归档id")
    private String archvNo;

    /** 流程ID */
    @Excel(name = "流程ID")
    private String flowNo;

    /** 流程名称 */
    @Excel(name = "流程名称")
    private String flowName;

    /** 流程标题 */
    @Excel(name = "流程标题")
    private String flowTitle;

    /** 步骤ID */
    @Excel(name = "步骤ID")
    private String stepNo;

    /** 步骤名称 */
    @Excel(name = "步骤名称")
    private String stepName;

    /** 任务ID */
    @Excel(name = "任务ID")
    private String taskNo;

    /** 组ID */
    @Excel(name = "组ID")
    private String groupNo;

    /** 实例ID */
    @Excel(name = "实例ID")
    private String instanceNo;

    /** 内容 */
    @Excel(name = "内容")
    private String contents;

    /** 归档时间 */
    @Excel(name = "归档时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date writeTime;

    /** 意见 */
    @Excel(name = "意见")
    private String writeNote;

    /** 相关文件 */
    @Excel(name = "相关文件")
    private String rarFiles;

    /** PDF文件 */
    @Excel(name = "PDF文件")
    private String pdfFiles;

    /** 处理人ID */
    @Excel(name = "处理人ID")
    private String userNo;

    /** 处理人姓名 */
    @Excel(name = "处理人姓名")
    private String userName;

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
    public void setArchvNo(String archvNo) 
    {
        this.archvNo = archvNo;
    }

    public String getArchvNo() 
    {
        return archvNo;
    }
    public void setFlowNo(String flowNo) 
    {
        this.flowNo = flowNo;
    }

    public String getFlowNo() 
    {
        return flowNo;
    }
    public void setFlowName(String flowName) 
    {
        this.flowName = flowName;
    }

    public String getFlowName() 
    {
        return flowName;
    }
    public void setFlowTitle(String flowTitle) 
    {
        this.flowTitle = flowTitle;
    }

    public String getFlowTitle() 
    {
        return flowTitle;
    }
    public void setStepNo(String stepNo) 
    {
        this.stepNo = stepNo;
    }

    public String getStepNo() 
    {
        return stepNo;
    }
    public void setStepName(String stepName) 
    {
        this.stepName = stepName;
    }

    public String getStepName() 
    {
        return stepName;
    }
    public void setTaskNo(String taskNo) 
    {
        this.taskNo = taskNo;
    }

    public String getTaskNo() 
    {
        return taskNo;
    }
    public void setGroupNo(String groupNo) 
    {
        this.groupNo = groupNo;
    }

    public String getGroupNo() 
    {
        return groupNo;
    }
    public void setInstanceNo(String instanceNo) 
    {
        this.instanceNo = instanceNo;
    }

    public String getInstanceNo() 
    {
        return instanceNo;
    }
    public void setContents(String contents) 
    {
        this.contents = contents;
    }

    public String getContents() 
    {
        return contents;
    }
    public void setWriteTime(Date writeTime) 
    {
        this.writeTime = writeTime;
    }

    public Date getWriteTime() 
    {
        return writeTime;
    }
    public void setWriteNote(String writeNote) 
    {
        this.writeNote = writeNote;
    }

    public String getWriteNote() 
    {
        return writeNote;
    }
    public void setRarFiles(String rarFiles) 
    {
        this.rarFiles = rarFiles;
    }

    public String getRarFiles() 
    {
        return rarFiles;
    }
    public void setPdfFiles(String pdfFiles) 
    {
        this.pdfFiles = pdfFiles;
    }

    public String getPdfFiles() 
    {
        return pdfFiles;
    }
    public void setUserNo(String userNo) 
    {
        this.userNo = userNo;
    }

    public String getUserNo() 
    {
        return userNo;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
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
            .append("archvNo", getArchvNo())
            .append("flowNo", getFlowNo())
            .append("flowName", getFlowName())
            .append("flowTitle", getFlowTitle())
            .append("stepNo", getStepNo())
            .append("stepName", getStepName())
            .append("taskNo", getTaskNo())
            .append("groupNo", getGroupNo())
            .append("instanceNo", getInstanceNo())
            .append("contents", getContents())
            .append("writeTime", getWriteTime())
            .append("writeNote", getWriteNote())
            .append("rarFiles", getRarFiles())
            .append("pdfFiles", getPdfFiles())
            .append("userNo", getUserNo())
            .append("userName", getUserName())
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
