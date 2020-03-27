package com.benet.common.xss;

import com.benet.common.utils.html.EscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * XSS过滤处理
 * 
 * @author yoxking
 */
public class XssWrapper extends HttpServletRequestWrapper
{
    /**
     * @param request
     */
    public XssWrapper(HttpServletRequest request)
    {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name)
    {
        String[] values = super.getParameterValues(name);
        if (values != null)
        {
            int length = values.length;
            String[] escapseValues = new String[length];
            for (int i = 0; i < length; i++)
            {
                // 防xss攻击和过滤前后空格
                escapseValues[i] = EscapeUtils.clean(values[i]).trim();
            }
            return escapseValues;
        }
        return super.getParameterValues(name);
    }
}