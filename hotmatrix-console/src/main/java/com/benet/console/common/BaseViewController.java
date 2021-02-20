package com.benet.console.common;

import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.domain.AjaxResult.Type;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.console.security.LoginRuser;
import com.benet.console.utils.ShiroHelper;
import com.benet.console.vmodel.LoginerVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

/**
 * web层通用数据处理
 *
 * @author yoxking
 */
public class BaseViewController {

    public LoginerVo getLoginer(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LoginRuser ruser = ShiroHelper.getLoginRuser();
        LoginerVo loginer=new LoginerVo();

        loginer.setRuserNo(ruser.getUser().getUserNo());
        loginer.setRuserName(ruser.getUser().getLoginName());
        loginer.setRnickName(ruser.getUser().getUserCnname());
        loginer.setRuserAvatar(ruser.getUser().getAvatar());
        loginer.setRuserType(ruser.getUser().getUserType().equals("00")?"用户":"家长");
        loginer.setRuserSex(ruser.getUser().getSex().equals("0")?"女":"男");
        loginer.setRuserMail(ruser.getUser().getEmail());
        loginer.setTelephone(ruser.getUser().getTelephone());
        loginer.setLoginDate(sdf.format(ruser.getUser().getLoginDate()));

        return loginer;
    }

    /**
     * 获取request
     */
    public HttpServletRequest getRequest()
    {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse()
    {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession()
    {
        return getRequest().getSession();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    /**
     * 返回错误码消息
     */
    public AjaxResult error(Type type, String message)
    {
        return new AjaxResult(type, message);
    }
}
