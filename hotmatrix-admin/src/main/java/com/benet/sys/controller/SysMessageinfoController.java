package com.benet.sys.controller;

import java.util.ArrayList;
import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.sys.vmodel.MsgObjectVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.benet.system.domain.SysMessageinfo;
import com.benet.system.service.ISysMessageinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 消息信息Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/sys/messageinfo")
public class SysMessageinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysMessageinfoService sysMessageinfoService;
    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:messageinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询消息信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:messageinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysMessageinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<SysMessageinfo> list = sysMessageinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增消息信息
     */
    //@PreAuthorize("@ps.hasPermit('system:messageinfo:insert')")
    @Oplog(title = "消息信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysMessageinfo sysMessageinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysMessageinfo.setMssgNo(UuidUtils.shortUUID());
        sysMessageinfo.setCreateBy(loginUser.getUser().getUserNo());
        sysMessageinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysMessageinfoService.AddNewRecord(loginUser.getUser().getAppCode(),sysMessageinfo));
    }

    /**
     * 编辑消息信息
     */
    //@PreAuthorize("@ps.hasPermit('system:messageinfo:update')")
    @Oplog(title = "消息信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysMessageinfo sysMessageinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysMessageinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysMessageinfoService.UpdateRecord(loginUser.getUser().getAppCode(),sysMessageinfo));
        }

    /**
     * 保存消息信息
     */
    //@PreAuthorize("@ps.hasPermit('system:messageinfo:save')")
    @Oplog(title = "消息信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysMessageinfo sysMessageinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysMessageinfoService.getRecordByNo(loginUser.getUser().getAppCode(),sysMessageinfo.getMssgNo()))) {
            sysMessageinfo.setMssgNo(UuidUtils.shortUUID());
            sysMessageinfo.setCreateBy(loginUser.getUser().getUserNo());
            sysMessageinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysMessageinfoService.AddNewRecord(loginUser.getUser().getAppCode(),sysMessageinfo));
        } else {
            sysMessageinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysMessageinfoService.UpdateRecord(loginUser.getUser().getAppCode(),sysMessageinfo));
        }
    }

    /**
     * 删除消息信息
     */
    //@PreAuthorize("@ps.hasPermit('system:messageinfo:delete')")
    @Oplog(title = "消息信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysMessageinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取消息信息详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:messageinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysMessageinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出消息信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:messageinfo:export')")
    @Oplog(title = "消息信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysMessageinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<SysMessageinfo> list = sysMessageinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysMessageinfo> util = new ExcelUtils<SysMessageinfo>(SysMessageinfo.class);
        return util.exportExcel(list, "SysMessageinfo");
    }

    /**
     * 查询消息信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:messageinfo:list')")
    @GetMapping(value="/getMessages")
    public TableDataInfo getMessages()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SysMessageinfo> list = sysMessageinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1, 10, " read_state='0'", "id", "Desc");
        if(list!=null&&list.size()>0){
            List<MsgObjectVo> msgList=new ArrayList<>();
            MsgObjectVo msg=null;
            for(SysMessageinfo info : list){
                msg=new MsgObjectVo();
                msg.setId(info.getMssgNo());
                msg.setTitle(info.getMtitle());
                msg.setDate(DateUtils.dateTime(info.getSendTime()));
                msg.setDesc(DateUtils.getShortTime(info.getSendTime()));

                msgList.add(msg);
            }
            return getDataTable(msgList, msgList.size());
        }
        return getDataTable(null,0);
    }

}
