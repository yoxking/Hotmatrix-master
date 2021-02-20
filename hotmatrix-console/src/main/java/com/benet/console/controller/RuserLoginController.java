package com.benet.console.controller;

import com.benet.common.core.domain.AjaxResult;
import com.benet.console.common.BaseViewController;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 登录验证
 *
 * @author yoxking
 */
@Controller
public class RuserLoginController extends BaseViewController {

    /**
     * session中的验证码
     */
    private String SHIRO_VERIFY_SESSION = "verifySessionCode";

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    /**
     * 登录
     */
    @GetMapping(value="/login")
    public String login()
    {
        return "login";
    }

    /**
     * 获取验证码
     * @param response
     */
    @GetMapping("/getCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        byte[] verByte = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute(SHIRO_VERIFY_SESSION,createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge,"jpg",jpegOutputStream);
        } catch (IllegalArgumentException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (IOException e){
            e.printStackTrace();
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        verByte = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(verByte);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @PostMapping("/ajaxLogin")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password,String verifyCode, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();

        // 获取session中的验证码
        String verCode = (String) subject.getSession().getAttribute(SHIRO_VERIFY_SESSION);
        if("".equals(verifyCode)||(!verCode.equals(verifyCode))){
            return error("验证码不正确");
        }

        try
        {
            subject.login(token);
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户名或密码错误!";
            return error(msg);
        }
    }

    /**
     * 退出
     */
    @GetMapping(value="/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
