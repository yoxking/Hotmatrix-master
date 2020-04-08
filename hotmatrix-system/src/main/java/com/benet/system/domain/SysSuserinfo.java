package com.benet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 系统用户信息对象 sys_suserinfo
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public class SysSuserinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userNo;

    /** 部门ID */
    @Excel(name = "部门ID")
    private String deptNo;

    /** 机构ID */
    @Excel(name = "机构ID")
    private String orgzNo;

    /** 登录账号 */
    @Excel(name = "登录账号")
    private String loginName;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 中文用户昵称 */
    @Excel(name = "中文用户昵称")
    private String userCnname;

    /** 英文用户昵称 */
    @Excel(name = "英文用户昵称")
    private String userEnname;

    /** 用户类型（00系统用户） */
    @Excel(name = "用户类型", readConverterExp = "0=0系统用户")
    private String userType;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String telephone;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 用户性别（0女 1男  2未知） */
    @Excel(name = "用户性别", readConverterExp = "0=女,1=男,2=未知")
    private String sex;

    /** 头像路径 */
    @Excel(name = "头像路径")
    private String avatar;

    /** 盐加密 */
    @Excel(name = "盐加密")
    private String salt;

    /** 最后登陆IP */
    @Excel(name = "最后登陆IP")
    private String loginIp;

    /** 最后登陆时间 */
    @Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loginDate;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private String checkState;

    /** 分支编号 */
    @Excel(name = "分支编号")
    private String branchNo;

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

    private List<SysRoleinfo> roles;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserNo(String userNo) 
    {
        this.userNo = userNo;
    }

    public String getUserNo() 
    {
        return userNo;
    }
    public void setDeptNo(String deptNo) 
    {
        this.deptNo = deptNo;
    }

    public String getDeptNo() 
    {
        return deptNo;
    }
    public void setOrgzNo(String orgzNo) 
    {
        this.orgzNo = orgzNo;
    }

    public String getOrgzNo() 
    {
        return orgzNo;
    }
    public void setLoginName(String loginName) 
    {
        this.loginName = loginName;
    }

    public String getLoginName() 
    {
        return loginName;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setUserCnname(String userCnname) 
    {
        this.userCnname = userCnname;
    }

    public String getUserCnname() 
    {
        return userCnname;
    }
    public void setUserEnname(String userEnname) 
    {
        this.userEnname = userEnname;
    }

    public String getUserEnname() 
    {
        return userEnname;
    }
    public void setUserType(String userType) 
    {
        this.userType = userType;
    }

    public String getUserType() 
    {
        return userType;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setSalt(String salt) 
    {
        this.salt = salt;
    }

    public String getSalt() 
    {
        return salt;
    }
    public void setLoginIp(String loginIp) 
    {
        this.loginIp = loginIp;
    }

    public String getLoginIp() 
    {
        return loginIp;
    }
    public void setLoginDate(Date loginDate) 
    {
        this.loginDate = loginDate;
    }

    public Date getLoginDate() 
    {
        return loginDate;
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

    public List<SysRoleinfo> getRoles()
    {
        return roles;
    }

    public void setRoles(List<SysRoleinfo> roles)
    {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userNo", getUserNo())
            .append("deptNo", getDeptNo())
            .append("orgzNo", getOrgzNo())
            .append("loginName", getLoginName())
            .append("password", getPassword())
            .append("userCnname", getUserCnname())
            .append("userEnname", getUserEnname())
            .append("userType", getUserType())
            .append("telephone", getTelephone())
            .append("email", getEmail())
            .append("sex", getSex())
            .append("avatar", getAvatar())
            .append("salt", getSalt())
            .append("loginIp", getLoginIp())
            .append("loginDate", getLoginDate())
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
