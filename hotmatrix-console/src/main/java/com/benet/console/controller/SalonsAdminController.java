package com.benet.console.controller;

import com.alibaba.fastjson.JSONObject;
import com.benet.collect.domain.CoctSalonclass;
import com.benet.collect.domain.CoctSalonsinfo;
import com.benet.collect.service.ICoctSalonclassService;
import com.benet.collect.service.ICoctSalonflowsService;
import com.benet.collect.service.ICoctSalonsinfoService;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.ShiroHelper;
import com.benet.console.vmodel.ItemObjectVo;
import com.benet.console.vmodel.ResultInfoVo;
import com.benet.system.domain.SysRuserinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 主题活动管理
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/adminview")
public class SalonsAdminController extends BaseViewController {
    private String prefix = "adminview";

    @Autowired
    private ICoctSalonflowsService salonflowsService;
    @Autowired
    private ICoctSalonclassService salonclassService;
    @Autowired
    private ICoctSalonsinfoService salonsinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/salons")
    public String salons(ModelMap model)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        model.put("loginer",getLoginer());
        return prefix +"/salons";
    }



    /**
     * 数据
     */
    @GetMapping(value="/getSalonsList")
    @ResponseBody
    public String getSalonsList(@RequestParam(defaultValue = "1",value = "page") Integer pageIndex,
                               @RequestParam(defaultValue = "10",value = "limit") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        String condition="";
        int count=salonsinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CoctSalonsinfo> infoList=salonsinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");

        ResultInfoVo resultInfo=new ResultInfoVo();
        resultInfo.setCode(0);
        resultInfo.setMsg("SUCCESS");
        resultInfo.setCount(count);
        resultInfo.setData(infoList);

        return JSONObject.toJSONString(resultInfo);
    }


    /**
     * 新增
     */
    @GetMapping(value="/salons_edit")
    public String editSalons(ModelMap model)
    {
        model.put("classList",getClassList());
        return prefix +"/salons_edit";
    }

    private List<ItemObjectVo> getClassList(){

        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        List<ItemObjectVo> itemList=new ArrayList<>();
        ItemObjectVo item=null;
        List<CoctSalonclass> infoList=salonclassService.getAllRecords(loginUser.getAppCode());

        if(infoList!=null&&infoList.size()>0){
            for(CoctSalonclass info:infoList){
                item=new ItemObjectVo();
                item.setKey(info.getClassNo());
                item.setValue(info.getClassName());
                itemList.add(item);
            }
        }

        return itemList;
    }

    @PostMapping("/saveSalon")
    @ResponseBody
    public AjaxResult saveSalon(String name, String classNo, String startDate, String enditDate, String address, String poster, String content)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟

        try {
            CoctSalonsinfo salonsInfo = new CoctSalonsinfo();
            salonsInfo.setSalonNo(UuidUtils.shortUUID());
            salonsInfo.setSalonName(name);
            salonsInfo.setSalonPoster(poster);
            salonsInfo.setSalonDesc(name);
            salonsInfo.setSalonType("1");
            salonsInfo.setDataFrom("前台");
            salonsInfo.setViewType("1");
            salonsInfo.setClassNo(classNo);
            salonsInfo.setStartTime(sdf.parse(startDate));
            salonsInfo.setEnditTime(sdf.parse(enditDate));
            salonsInfo.setSalonAddress(address);
            salonsInfo.setSalonRusers("");
            salonsInfo.setSalonContent(content);
            salonsInfo.setSalonState("1");
            salonsInfo.setCheckState("1");
            salonsInfo.setCreateBy(loginUser.getUserNo());
            salonsInfo.setUpdateBy(loginUser.getUserNo());
            salonsInfo.setDeleteFlag("1");
            salonsInfo.setComments("");
            return toAjax(salonsinfoService.AddNewRecord(loginUser.getAppCode(),salonsInfo));
        }
        catch (Exception e){
            return error("添加失败!");
        }
    }
}
