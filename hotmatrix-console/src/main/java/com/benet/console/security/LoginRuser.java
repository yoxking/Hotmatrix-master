package com.benet.console.security;

import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.system.domain.SysRuserinfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class LoginRuser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String ruserId;

    /**
     * 登陆时间
     */
    private Date loginTime;

    /**
     * 权限列表
     */
    private Set<String> permits;

    /**
     * 用户信息
     */
    private SysRuserinfo user;


    public LoginRuser()
    {
        this.ruserId= UuidUtils.shortUUID();
        this.loginTime= DateUtils.getNowDate();
        this.user = null;
        this.permits = null;
    }

    public LoginRuser(SysRuserinfo user, Set<String> permits)
    {
        this.ruserId= UuidUtils.shortUUID();
        this.loginTime= DateUtils.getNowDate();
        this.user = user;
        this.permits = permits;
    }

    public String getRuserId() {
        return ruserId;
    }

    public void setRuserId(String ruserId) {
        this.ruserId = ruserId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Set<String> getPermits() {
        return permits;
    }

    public void setPermits(Set<String> permits) {
        this.permits = permits;
    }

    public SysRuserinfo getUser() {
        return user;
    }

    public void setUser(SysRuserinfo user) {
        this.user = user;
    }
}
