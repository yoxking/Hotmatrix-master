package com.benet.framework.security;

import java.util.Collection;
import java.util.Set;

import com.benet.system.domain.SysSuserinfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 登录用户身份权限
 * 
 * @author yoxking
 */
public class LoginUser implements UserDetails
{
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String token;


    /**
     * 登陆时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddress;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permits;

    /**
     * 用户信息
     */
    private SysSuserinfo user;

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public LoginUser()
    {
    }

    public LoginUser(SysSuserinfo user, Set<String> permits)
    {
        this.user = user;
        this.permits = permits;
    }

    @JsonIgnore
    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    public String getUserno()
    {
        return user.getUserNo();
    }

    @Override
    public String getUsername()
    {
        return user.getLoginName();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     * 
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     * 
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     * 
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public Long getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Long loginTime)
    {
        this.loginTime = loginTime;
    }

    public String getIpaddress()
    {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress)
    {
        this.ipaddress = ipaddress;
    }

    public String getLoginLocation()
    {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation)
    {
        this.loginLocation = loginLocation;
    }

    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public Long getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Long expireTime)
    {
        this.expireTime = expireTime;
    }

    public Set<String> getPermits()
    {
        return permits;
    }

    public void setPermits(Set<String> permits)
    {
        this.permits = permits;
    }

    public SysSuserinfo getUser()
    {
        return user;
    }

    public void setUser(SysSuserinfo user)
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }
}
