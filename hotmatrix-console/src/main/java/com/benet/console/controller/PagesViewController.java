package com.benet.console.controller;

import com.alibaba.fastjson.JSONObject;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.utils.file.FileUploadUtils;
import com.benet.common.utils.file.FileUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.console.utils.ShiroHelper;
import com.benet.console.vmodel.ResultInfoVo;
import com.benet.system.domain.SysRuserinfo;
import com.benet.system.service.ISysConfiginfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 主页
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/pagesview")
public class PagesViewController {

    @Autowired
    private ISysConfiginfoService configinfoService;

    /**
     * 通用上传请求
     */
    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(MultipartFile file) throws Exception
    {
        ResultInfoVo result=new ResultInfoVo();
        try
        {
            SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
            String siteUrl=configinfoService.getConfigValueByKey(loginUser.getAppCode(),"SiteUrl");

            // 上传文件路径
            String filePath = GlobalConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = siteUrl + fileName;

            result.setCode(0);
            result.setMsg("SUCCESS");
            result.setCount(0);
            result.setData(url);

            //String s="{\"code\": 0,\"msg\": \"SECCESS\",\"data\": {\"avator\": \"/20200327173430124807720.jpg\"}}";

            return JSONObject.toJSONString(result);
        }
        catch (Exception e)
        {
            result.setCode(1);
            result.setMsg("ERROR");
            result.setCount(0);
            result.setData(null);

            return JSONObject.toJSONString(result);
        }
    }

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("/download")
    public void downloadFile(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.isValidFilename(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + "_"+fileName.substring(fileName.indexOf("_") + 1);
            String filePath = GlobalConfig.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
        }
    }
}
