package com.benet.collect.controller;

import java.util.ArrayList;
import java.util.List;

import com.benet.collect.domain.CoctQuestinfo;
import com.benet.collect.domain.CoctQuestopts;
import com.benet.collect.service.ICoctQuestinfoService;
import com.benet.collect.service.ICoctQuestoptsService;
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
public class CoctQuestinfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICoctQuestinfoService coctQuestinfoService;
    @Autowired
    private ICoctQuestoptsService coctQuestoptsService;

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
        int count = coctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(), pRequest.getCondition());
        List<CoctQuestinfo> list = coctQuestinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(), pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
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
        CoctQuestinfo coctQuestinfo = new CoctQuestinfo();

        coctQuestinfo.setQuestNo(UuidUtils.shortUUID());
        coctQuestinfo.setQuestTitle(questInfoVo.getQuestTitle());
        coctQuestinfo.setQuestImage(questInfoVo.getQuestImage());
        coctQuestinfo.setQuestType(questInfoVo.getQuestType());
        coctQuestinfo.setQuestDesc(questInfoVo.getQuestDesc());
        coctQuestinfo.setClassNo(questInfoVo.getClassNo());
        coctQuestinfo.setQsetsNo(questInfoVo.getQsetsNo());
        coctQuestinfo.setOrderNo(questInfoVo.getOrderNo());
        coctQuestinfo.setQuestMust(questInfoVo.getQuestMust());
        coctQuestinfo.setQuestTscore(questInfoVo.getQuestTscore());
        coctQuestinfo.setQuestAnswer(questInfoVo.getQuestAnswer());
        coctQuestinfo.setQuestKeyword(questInfoVo.getQuestKeyword());
        coctQuestinfo.setQuestExplain(questInfoVo.getQuestExplain());
        coctQuestinfo.setCheckState(questInfoVo.getCheckState());
        coctQuestinfo.setBranchNo("");
        coctQuestinfo.setCreateBy(loginUser.getUser().getUserNo());
        coctQuestinfo.setUpdateBy(loginUser.getUser().getUserNo());
        coctQuestinfo.setDeleteFlag("1");
        coctQuestinfo.setComments(questInfoVo.getComments());

        if (coctQuestinfoService.AddNewRecord(loginUser.getUser().getAppCode(), coctQuestinfo) > 0) {

            int i = 1;
            if((!questInfoVo.getQuestType().equals("1"))&&questInfoVo.getOptions()!=null&&questInfoVo.getOptions().length>0) {
                for (QuestOptsVo item : questInfoVo.getOptions()) {
                    CoctQuestopts questOpts = new CoctQuestopts();
                    questOpts.setOptsNo(UuidUtils.shortUUID());
                    questOpts.setOptsTitle(item.getOptsTitle());
                    questOpts.setOptsIndex(item.getOptsIndex());
                    questOpts.setOptsImage(item.getOptsImage());
                    questOpts.setOptsDesc(item.getOptsDesc());
                    questOpts.setQuestNo(coctQuestinfo.getQuestNo());
                    questOpts.setOrderNo(i++);
                    questOpts.setOptsScore(item.getOptsScore());
                    questOpts.setCheckState("1");
                    questOpts.setCreateBy(loginUser.getUser().getUserNo());
                    questOpts.setUpdateBy(loginUser.getUser().getUserNo());
                    questOpts.setDeleteFlag("1");
                    questOpts.setComments("");

                    coctQuestoptsService.AddNewRecord(loginUser.getUser().getAppCode(), questOpts);
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
        CoctQuestinfo coctQuestinfo = coctQuestinfoService.getRecordByNo(loginUser.getUser().getAppCode(), questInfoVo.getQuestNo());
        coctQuestinfo.setQuestTitle(questInfoVo.getQuestTitle());
        coctQuestinfo.setQuestImage(questInfoVo.getQuestImage());
        coctQuestinfo.setQuestType(questInfoVo.getQuestType());
        coctQuestinfo.setQuestDesc(questInfoVo.getQuestDesc());
        coctQuestinfo.setClassNo(questInfoVo.getClassNo());
        coctQuestinfo.setQsetsNo(questInfoVo.getQsetsNo());
        coctQuestinfo.setOrderNo(questInfoVo.getOrderNo());
        coctQuestinfo.setQuestMust(questInfoVo.getQuestMust());
        coctQuestinfo.setQuestTscore(questInfoVo.getQuestTscore());
        coctQuestinfo.setQuestAnswer(questInfoVo.getQuestAnswer());
        coctQuestinfo.setQuestKeyword(questInfoVo.getQuestKeyword());
        coctQuestinfo.setQuestExplain(questInfoVo.getQuestExplain());
        coctQuestinfo.setUpdateBy(loginUser.getUser().getUserNo());
        coctQuestinfo.setComments(questInfoVo.getComments());
        if (coctQuestinfoService.UpdateRecord(loginUser.getUser().getAppCode(), coctQuestinfo) > 0) {

            coctQuestoptsService.HardDeleteByCondition(loginUser.getUser().getAppCode(), " quest_no='"+questInfoVo.getQuestNo()+"' ");
            int i = 1;
            if(questInfoVo.getQuestType()!="1"&&questInfoVo.getOptions()!=null&&questInfoVo.getOptions().length>0) {
                for (QuestOptsVo item : questInfoVo.getOptions()) {
                    CoctQuestopts questOpts = new CoctQuestopts();
                    questOpts.setOptsNo(UuidUtils.shortUUID());
                    questOpts.setOptsTitle(item.getOptsTitle());
                    questOpts.setOptsIndex(item.getOptsIndex());
                    questOpts.setOptsImage(item.getOptsImage());
                    questOpts.setOptsDesc(item.getOptsDesc());
                    questOpts.setQuestNo(coctQuestinfo.getQuestNo());
                    questOpts.setOrderNo(i++);
                    questOpts.setOptsScore(item.getOptsScore());
                    questOpts.setCheckState("1");
                    questOpts.setCreateBy(loginUser.getUser().getUserNo());
                    questOpts.setUpdateBy(loginUser.getUser().getUserNo());
                    questOpts.setDeleteFlag("1");
                    questOpts.setComments("");

                    coctQuestoptsService.AddNewRecord(loginUser.getUser().getAppCode(), questOpts);
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
    public AjaxResult save(@RequestBody CoctQuestinfo coctQuestinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(coctQuestinfoService.getRecordByNo(loginUser.getUser().getAppCode(), coctQuestinfo.getQuestNo()))) {
            coctQuestinfo.setQuestNo(UuidUtils.shortUUID());
            coctQuestinfo.setCreateBy(loginUser.getUser().getUserNo());
            coctQuestinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctQuestinfoService.AddNewRecord(loginUser.getUser().getAppCode(), coctQuestinfo));
        } else {
            coctQuestinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctQuestinfoService.UpdateRecord(loginUser.getUser().getAppCode(), coctQuestinfo));
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
        return toAjax(coctQuestinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(), ids));
    }

    /**
     * 获取测评题库详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CoctQuestinfo coctQuestinfo =coctQuestinfoService.getRecordByNo(loginUser.getUser().getAppCode(), id);
        if(coctQuestinfo!=null){
            QuestInfoVo questInfo=new QuestInfoVo();
            questInfo.setQuestNo(coctQuestinfo.getQuestNo());
            questInfo.setQuestTitle(coctQuestinfo.getQuestTitle());
            questInfo.setQuestImage(coctQuestinfo.getQuestImage());
            questInfo.setQuestType(coctQuestinfo.getQuestType());
            questInfo.setQuestDesc(coctQuestinfo.getQuestDesc());
            questInfo.setClassNo(coctQuestinfo.getClassNo());
            questInfo.setQsetsNo(coctQuestinfo.getQsetsNo());
            questInfo.setOrderNo(coctQuestinfo.getOrderNo());
            questInfo.setQuestMust(coctQuestinfo.getQuestMust());
            questInfo.setQuestTscore(coctQuestinfo.getQuestTscore());
            questInfo.setQuestAnswer(coctQuestinfo.getQuestAnswer());
            questInfo.setQuestKeyword(coctQuestinfo.getQuestKeyword());
            questInfo.setQuestExplain(coctQuestinfo.getQuestExplain());
            questInfo.setCheckState(coctQuestinfo.getCheckState());
            questInfo.setComments(coctQuestinfo.getComments());

            List<QuestOptsVo> questOptVos=new ArrayList<>();
            QuestOptsVo questOpts=null;
            List<CoctQuestopts> questOptsList=coctQuestoptsService.getRecordsByClassNo(loginUser.getUser().getAppCode(),id);
            if(questOptsList!=null&&questOptsList.size()>0){
                for(CoctQuestopts item:questOptsList){
                    questOpts=new QuestOptsVo();
                    questOpts.setOptsNo(item.getOptsNo());
                    questOpts.setOptsTitle(item.getOptsTitle());
                    questOpts.setOptsIndex(item.getOptsIndex());
                    questOpts.setOptsImage(item.getOptsImage());
                    questOpts.setOptsDesc(item.getOptsDesc());
                    questOpts.setOptsScore(item.getOptsScore());

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
        int count = coctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(), pRequest.getCondition());

        List<CoctQuestinfo> list = coctQuestinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(), 1, count, pRequest.getCondition(), "id", "Asc");
        ExcelUtils<CoctQuestinfo> util = new ExcelUtils<CoctQuestinfo>(CoctQuestinfo.class);
        return util.exportExcel(list, "CoctQuestinfo");
    }

}
