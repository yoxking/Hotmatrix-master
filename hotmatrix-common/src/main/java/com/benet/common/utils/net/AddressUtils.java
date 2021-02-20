package com.benet.common.utils.net;

import com.benet.common.configure.GlobalConfig;
import com.benet.common.utils.http.HttpUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.json.JsonHelper;
import com.benet.common.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取地址类
 * 
 * @author yoxking
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    public static String getRealAddressByIP(String ip)
    {
        String address = "XX XX";

        // 内网不查询
        if (IpnetUtils.internalIp(ip))
        {
            return "内网IP";
        }
        if (GlobalConfig.isAddressEnabled())
        {
            String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
            if (StringUtils.isEmpty(rspStr))
            {
                log.error("获取地理位置异常 {}", ip);
                return address;
            }
            JsonObject obj;
            try
            {
                obj = JsonHelper.unmarshal(rspStr, JsonObject.class);
                JsonObject data = obj.getObj("data");
                String region = data.getStr("region");
                String city = data.getStr("city");
                address = region + " " + city;
            }
            catch (Exception e)
            {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return address;
    }
}
