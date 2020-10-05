package com.benet.collect.controller;

import java.util.ArrayList;
import java.util.List;

import com.benet.collect.domain.CctQuestopts;
import com.benet.collect.service.ICctQuestoptsService;
import com.benet.collect.vmodel.QuestInfoVo;
import com.benet.collect.vmodel.QuestOptsVo;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import io.swagger.annotations.Api;
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
import com.benet.collect.domain.CctQuestinfo;
import com.benet.collect.service.ICctQuestinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 测评题库Controller
 *
 * @author yoxking
 * @date 2020-08-27
 */
@Api(value = "collect/questinfo", tags = "测评题库控制器")
@RestController
@RequestMapping("/collect/questinfo")
public class CctQuestinfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctQuestinfoService cctQuestinfoService;
    @Autowired
    private ICctQuestoptsService cctQuestoptsService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询测评题库列表
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(), pRequest.getCondition());
        List<CctQuestinfo> list = cctQuestinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(), pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增测评题库
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:addnew')")
    @Oplog(title = "测评题库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody QuestInfoVo questInfoVo) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CctQuestinfo cctQuestinfo = new CctQuestinfo();

        cctQuestinfo.setQuestNo(UuidUtils.shortUUID());
        cctQuestinfo.setQuestTitle(questInfoVo.getQuestTitle());
        cctQuestinfo.setQuestType(questInfoVo.getQuestType());
        cctQuestinfo.setQuestDesc(questInfoVo.getQuestDesc());
        cctQuestinfo.setClassNo(questInfoVo.getClassNo());
        cctQuestinfo.setOrderNo(questInfoVo.getOrderNo());
        cctQuestinfo.setQuestMust(questInfoVo.getQuestMust());
        cctQuestinfo.setQuestScore(questInfoVo.getQuestScore());
        cctQuestinfo.setCheckState(questInfoVo.getCheckState());
        cctQuestinfo.setBranchNo("");
        cctQuestinfo.setCreateBy(loginUser.getUser().getUserNo());
        cctQuestinfo.setUpdateBy(loginUser.getUser().getUserNo());
        cctQuestinfo.setDeleteFlag("1");
        cctQuestinfo.setComments(questInfoVo.getComments());

        if (cctQuestinfoService.AddNewRecord(loginUser.getUser().getAppCode(), cctQuestinfo) > 0) {

            int i = 1;
            if(questInfoVo.getQuestType()!="1"&&questInfoVo.getOptions()!=null&&questInfoVo.getOptions().length>0) {
                for (QuestOptsVo item : questInfoVo.getOptions()) {
                    CctQuestopts questOpts = new CctQuestopts();
                    questOpts.setOptNo(UuidUtils.shortUUID());
                    questOpts.setOptTitle(item.getOptTitle());
                    questOpts.setOptDesc("");
                    questOpts.setQuestNo(cctQuestinfo.getQuestNo());
                    questOpts.setOrderNo(i++);
                    questOpts.setOptScore(item.getOptScore());
                    questOpts.setCheckState("1");
                    questOpts.setBranchNo("");
                    questOpts.setCreateBy(loginUser.getUser().getUserNo());
                    questOpts.setUpdateBy(loginUser.getUser().getUserNo());
                    questOpts.setDeleteFlag("1");
                    questOpts.setComments("");

                    cctQuestoptsService.AddNewRecord(loginUser.getUser().getAppCode(), questOpts);
                }
            }
            return toAjax(1);
        }

        return toAjax(0);
    }

    /**
     * 编辑测评题库
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:update')")
    @Oplog(title = "测评题库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody QuestInfoVo questInfoVo) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CctQuestinfo cctQuestinfo = cctQuestinfoService.getRecordByNo(loginUser.getUser().getAppCode(), questInfoVo.getQuestNo());
        cctQuestinfo.setQuestTitle(questInfoVo.getQuestTitle());
        cctQuestinfo.setQuestType(questInfoVo.getQuestType());
        cctQuestinfo.setQuestDesc(questInfoVo.getQuestDesc());
        cctQuestinfo.setClassNo(questInfoVo.getClassNo());
        cctQuestinfo.setOrderNo(questInfoVo.getOrderNo());
        cctQuestinfo.setQuestMust(questInfoVo.getQuestMust());
        cctQuestinfo.setQuestScore(questInfoVo.getQuestScore());
        cctQuestinfo.setUpdateBy(loginUser.getUser().getUserNo());
        cctQuestinfo.setComments(questInfoVo.getComments());
        if (cctQuestinfoService.UpdateRecord(loginUser.getUser().getAppCode(), cctQuestinfo) > 0) {

            cctQuestoptsService.HardDeleteByCondition(loginUser.getUser().getAppCode(), " quest_no='"+questInfoVo.getQuestNo()+"' ");
            int i = 1;
            if(questInfoVo.getQuestType()!="1"&&questInfoVo.getOptions()!=null&&questInfoVo.getOptions().length>0) {
                for (QuestOptsVo item : questInfoVo.getOptions()) {
                    CctQuestopts questOpts = new CctQuestopts();
                    questOpts.setOptNo(UuidUtils.shortUUID());
                    questOpts.setOptTitle(item.getOptTitle());
                    questOpts.setOptDesc("");
                    questOpts.setQuestNo(cctQuestinfo.getQuestNo());
                    questOpts.setOrderNo(i++);
                    questOpts.setOptScore(item.getOptScore());
                    questOpts.setCheckState("1");
                    questOpts.setBranchNo("");
                    questOpts.setCreateBy(loginUser.getUser().getUserNo());
                    questOpts.setUpdateBy(loginUser.getUser().getUserNo());
                    questOpts.setDeleteFlag("1");
                    questOpts.setComments("");

                    cctQuestoptsService.AddNewRecord(loginUser.getUser().getAppCode(), questOpts);
                }
            }
            return toAjax(1);
        }

        return toAjax(0);
    }

    /**
     * 保存测评题库
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:save')")
    @Oplog(title = "测评题库", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctQuestinfo cctQuestinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctQuestinfoService.getRecordByNo(loginUser.getUser().getAppCode(), cctQuestinfo.getQuestNo()))) {
            cctQuestinfo.setQuestNo(UuidUtils.shortUUID());
            cctQuestinfo.setCreateBy(loginUser.getUser().getUserNo());
            cctQuestinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestinfoService.AddNewRecord(loginUser.getUser().getAppCode(), cctQuestinfo));
        } else {
            cctQuestinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestinfoService.UpdateRecord(loginUser.getUser().getAppCode(), cctQuestinfo));
        }
    }

    /**
     * 删除测评题库
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:delete')")
    @Oplog(title = "测评题库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctQuestinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(), ids));
    }

    /**
     * 获取测评题库详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CctQuestinfo cctQuestinfo =cctQuestinfoService.getRecordByNo(loginUser.getUser().getAppCode(), id);
        if(cctQuestinfo!=null){
            QuestInfoVo questInfo=new QuestInfoVo();
            questInfo.setQuestNo(cctQuestinfo.getQuestNo());
            questInfo.setQuestTitle(cctQuestinfo.getQuestTitle());
            questInfo.setQuestDesc(cctQuestinfo.getQuestDesc());
            questInfo.setQuestType(cctQuestinfo.getQuestType());
            questInfo.setQuestMust(cctQuestinfo.getQuestMust());
            questInfo.setClassNo(cctQuestinfo.getClassNo());
            questInfo.setOrderNo(cctQuestinfo.getOrderNo());
            questInfo.setQuestScore(cctQuestinfo.getQuestScore());
            questInfo.setCheckState(cctQuestinfo.getCheckState());
            questInfo.setComments(cctQuestinfo.getComments());

            List<QuestOptsVo> questOptVos=new ArrayList<>();
            QuestOptsVo questOpts=null;
            List<CctQuestopts> questOptsList=cctQuestoptsService.getRecordsByClassNo(loginUser.getUser().getAppCode(),id);
            if(questOptsList!=null&&questOptsList.size()>0){
                for(CctQuestopts item:questOptsList){
                    questOpts=new QuestOptsVo();
                    questOpts.setOptNo(item.getOptNo());
                    questOpts.setOptTitle(item.getOptTitle());
                    questOpts.setOptScore(item.getOptScore());

                    questOptVos.add(questOpts);
                }
            }
            questInfo.setOptions((QuestOptsVo[])questOptVos.toArray(new QuestOptsVo[0]));
            return AjaxResult.success(questInfo);
        }
        return success();
    }

    /**
     * 导出测评题库列表
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:export')")
    @Oplog(title = "测评题库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(), pRequest.getCondition());

        List<CctQuestinfo> list = cctQuestinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(), 1, count, pRequest.getCondition(), "id", "Asc");
        ExcelUtils<CctQuestinfo> util = new ExcelUtils<CctQuestinfo>(CctQuestinfo.class);
        return util.exportExcel(list, "CctQuestinfo");
    }

}
