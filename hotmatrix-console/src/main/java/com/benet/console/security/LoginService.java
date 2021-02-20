package com.benet.console.security;

import com.benet.common.exception.user.UserNotExistsException;
import com.benet.common.exception.user.UserPasswordNotMatchException;
import com.benet.common.utils.security.EncDesUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.system.domain.SysRuserinfo;
import com.benet.system.service.ISysRuserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 *
 * @author yoxking
 */
@Component
public class LoginService {

    @Autowired
    private ISysRuserinfoService userService;

    /**
     * 登录
     */
    public LoginRuser userLogin(String username, String password)
    {
        password= EncDesUtils.encryptBasedDes(password);

        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new UserNotExistsException();
        }
            // 查询用户信息
            SysRuserinfo info = userService.getRecordByLoginName(username);
            if(info!=null){

                if(info.getPassword().equals(password)){

                    LoginRuser user=new LoginRuser(info,null);
                    return user;
                }else{
                    throw new UserPasswordNotMatchException();
                }
            }else{
                throw new UserNotExistsException();
            }
    }
}
