package com.benet.collect.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.benet.collect.domain.CoctPaperinfo;
import com.benet.collect.domain.CoctQuestinfo;
import com.benet.collect.domain.CoctQuestopts;
import com.benet.collect.service.ICoctPaperinfoService;
import com.benet.collect.service.ICoctQuestinfoService;
import com.benet.collect.service.ICoctQuestoptsService;
import com.benet.collect.vmodel.*;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.vmodel.ItemObjectVo;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 问卷信息Controller
 * 
 * @author yoxking
 * @date 2020-08-27
 */
@Api(value = "collect/paperinfo", tags = "问卷信息控制器")
@RestController
@RequestMapping("/collect/paperinfo")
public class CoctPaperinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICoctPaperinfoService coctPaperinfoService;

    @Autowired
    private ICoctQuestinfoService coctQuestinfoService;

    @Autowired
    private ICoctQuestoptsService coctQuestoptsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询问卷信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctPaperinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CoctPaperinfo> list = coctPaperinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:paperclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctPaperinfoService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CoctPaperinfo> infoList = coctPaperinfoService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CoctPaperinfo info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getPaperNo());
                item.setKey(info.getPaperNo());
                item.setTitle(info.getPaperTitle());
                item.setValue(info.getPaperNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 查询问卷题库集列表
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:list')")
    @GetMapping(value = "/paperQuests/{id}")
    public AjaxResult paperQuests(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CoctPaperinfo paperInfo=coctPaperinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id);

        List<QuestInfoVo> questVoList=new ArrayList<>();
        List<QuestOptsVo> optsVoList=new ArrayList<>();
        QuestInfoVo questVo=null;
        QuestOptsVo optsVo=null;

        List<CoctQuestinfo> questList = coctQuestinfoService.getRecordsByClassNo(loginUser.getUser().getAppCode(),paperInfo.getClassNo());
        if(questList!=null&&questList.size()>0){
            for(CoctQuestinfo questItem:questList){
                questVo=new QuestInfoVo();
                questVo.setQuestNo(questItem.getQuestNo());
                questVo.setQuestTitle(questItem.getQuestTitle());
                questVo.setQuestDesc(questItem.getQuestDesc());
                questVo.setQuestType(questItem.getQuestType());
                questVo.setQuestMust(questItem.getQuestMust());
                questVo.setClassNo(questItem.getClassNo());
                questVo.setOrderNo(questItem.getOrderNo());
                questVo.setQuestTscore(questItem.getQuestTscore());
                questVo.setCheckState(questItem.getCheckState());
                questVo.setComments(questItem.getComments());

                List<CoctQuestopts> optsList=coctQuestoptsService.getRecordsByClassNo(loginUser.getUser().getAppCode(),questItem.getQuestNo());
                if(optsList!=null&&optsList.size()>0){
                    optsVoList.clear();
                    for(CoctQuestopts optItem:optsList){
                        optsVo=new QuestOptsVo();
                        optsVo.setOptsNo(optItem.getOptsNo());
                        optsVo.setOptsTitle(optItem.getOptsTitle());
                        optsVo.setOptsIndex(optItem.getOptsIndex());
                        optsVo.setOptsImage(optItem.getOptsImage());
                        optsVo.setOptsDesc(optItem.getOptsDesc());
                        optsVo.setOptsScore(optItem.getOptsScore());

                        optsVoList.add(optsVo);
                    }
                }
                questVo.setOptions((QuestOptsVo[])optsVoList.toArray(new QuestOptsVo[0]));
                questVoList.add(questVo);
            }
            return AjaxResult.success(questVoList);
        }

        return AjaxResult.success();
    }

    /**
     * 新增问卷信息
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:addnew')")
    @Oplog(title = "问卷信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CoctPaperinfo coctPaperinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctPaperinfo.setPaperNo(UuidUtils.shortUUID());
        coctPaperinfo.setCreateBy(loginUser.getUser().getUserNo());
        coctPaperinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(coctPaperinfoService.AddNewRecord(loginUser.getUser().getAppCode(),coctPaperinfo));
    }

    /**
     * 编辑问卷信息
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:update')")
    @Oplog(title = "问卷信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CoctPaperinfo coctPaperinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            coctPaperinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctPaperinfoService.UpdateRecord(loginUser.getUser().getAppCode(),coctPaperinfo));
        }

    /**
     * 保存问卷信息
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:save')")
    @Oplog(title = "问卷信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CoctPaperinfo coctPaperinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(coctPaperinfoService.getRecordByNo(loginUser.getUser().getAppCode(),coctPaperinfo.getPaperNo()))) {
            coctPaperinfo.setPaperNo(UuidUtils.shortUUID());
            coctPaperinfo.setCreateBy(loginUser.getUser().getUserNo());
            coctPaperinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctPaperinfoService.AddNewRecord(loginUser.getUser().getAppCode(),coctPaperinfo));
        } else {
            coctPaperinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctPaperinfoService.UpdateRecord(loginUser.getUser().getAppCode(),coctPaperinfo));
        }
    }

    /**
     * 删除问卷信息
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:delete')")
    @Oplog(title = "问卷信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(coctPaperinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取问卷信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(coctPaperinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出问卷信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:export')")
    @Oplog(title = "问卷信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctPaperinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CoctPaperinfo> list = coctPaperinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CoctPaperinfo> util = new ExcelUtils<CoctPaperinfo>(CoctPaperinfo.class);
        return util.exportExcel(list, "CoctPaperinfo");
    }

    /**
     * 获取问卷题库信息
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:detail')")
    @GetMapping(value = "getPaperqsets/{id}")
    public AjaxResult getPaperqsets(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CoctPaperinfo paperinfo = coctPaperinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id);
        String[] qsetsNos=paperinfo.getPaperQsets().split(";");
        List<PItemObject> psetsList=new ArrayList<>();
        if(qsetsNos!=null&&qsetsNos.length>0){
            for(String qsetsNo:qsetsNos){
                PItemObject item=new PItemObject();
                item.setId(qsetsNo);
                item.setName(qsetsNo);
                psetsList.add(item);
            }
        }

        String temp=paperinfo.getPaperQrules();
        PSetsDataVo psetsData = new PSetsDataVo();
        if(StringUtils.isNotEmpty(temp)) {
            List<QuestRuleVo> qrulesList= JSONArray.parseArray(temp, QuestRuleVo.class);

            psetsData.setqType1TNums(coctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(), "quest_type=1"));
            psetsData.setqType1RNums(qrulesList.get(0).getQuestLimit());
            psetsData.setqType1Score(qrulesList.get(0).getQuestScore());
            psetsData.setqType2TNums(coctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(), "quest_type=2"));
            psetsData.setqType2RNums(qrulesList.get(1).getQuestLimit());
            psetsData.setqType2Score(qrulesList.get(1).getQuestScore());
            psetsData.setqType3TNums(coctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(), "quest_type=3"));
            psetsData.setqType3RNums(qrulesList.get(2).getQuestLimit());
            psetsData.setqType3Score(qrulesList.get(2).getQuestScore());
            psetsData.setqTotalNums(coctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(), ""));
            psetsData.setqTotalScore(0);
            psetsData.setQsetsNums(qsetsNos != null ? qsetsNos.length : 0);
            psetsData.setQsetsList(psetsList);
        }
        else{
            psetsData.setqType1TNums(coctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(), "quest_type=1"));
            psetsData.setqType1RNums(0);
            psetsData.setqType1Score(0);
            psetsData.setqType2TNums(coctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(), "quest_type=2"));
            psetsData.setqType2RNums(0);
            psetsData.setqType2Score(0);
            psetsData.setqType3TNums(coctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(), "quest_type=3"));
            psetsData.setqType3RNums(0);
            psetsData.setqType3Score(0);
            psetsData.setqTotalNums(coctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(), ""));
            psetsData.setqTotalScore(0);
            psetsData.setQsetsNums(qsetsNos != null ? qsetsNos.length : 0);
            psetsData.setQsetsList(psetsList);
        }

        return AjaxResult.success(psetsData);
    }

    /**
     * 获取问卷题目信息
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:detail')")
    @GetMapping(value = "getPaperquests/{id}")
    public AjaxResult getPaperquests(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CoctPaperinfo paperinfo = coctPaperinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id);
        String[] questsNos=paperinfo.getPaperQuests().split(";");

        List<CoctQuestinfo> questsList=new ArrayList<>();
        if(questsNos!=null&&questsNos.length>0){
            for(String questNo:questsNos){
                questsList.add(coctQuestinfoService.getRecordByNo(loginUser.getUser().getAppCode(),questNo));
            }
        }

        QuestDataVo questData=new QuestDataVo();
        questData.setqType1Nums(coctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(),"quest_type=1"));
        questData.setqType2Nums(coctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(),"quest_type=2"));
        questData.setqType3Nums(coctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(),"quest_type=3"));
        questData.setqTotalNums(questsList.size());
        questData.setqTotalScore(0);
        questData.setQuestsList(questsList);

        return AjaxResult.success(questData);
    }

    /**
     * 更新问卷题库
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:update')")
    @Oplog(title = "问卷信息", businessType = BusinessType.UPDATE)
    @PostMapping("/uptPaperqsets")
    public AjaxResult uptPaperqsets(@RequestBody PaperInfoVo paperInfo)
    {
        String id=paperInfo.getPaperNo();
        String[] nos=paperInfo.getPaperQsets().split(",");
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CoctPaperinfo paperinfo = coctPaperinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id);
        if(paperinfo!=null) {
            String temp = paperinfo.getPaperQsets();
            for (String qsetsNo : nos) {
                temp=StringUtils.addSplitStr(temp,qsetsNo,";");
            }
            paperinfo.setPaperQsets(temp);
            paperinfo.setUpdateBy(loginUser.getUser().getUserNo());

            return toAjax(coctPaperinfoService.UpdateRecord(loginUser.getUser().getAppCode(), paperinfo));
        }
        return success();
    }

    /**
     * 删除问卷题库
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:update')")
    @Oplog(title = "问卷信息", businessType = BusinessType.UPDATE)
    @PostMapping("/delPaperqsets")
    public AjaxResult delPaperqsets(@RequestBody PaperInfoVo paperInfo)
    {
        String id=paperInfo.getPaperNo();
        String qsetsNo=paperInfo.getQsetsNo();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CoctPaperinfo paperinfo = coctPaperinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id);
        if(paperinfo!=null) {
            String temp = paperinfo.getPaperQsets();
            temp=StringUtils.delSplitStr(temp,qsetsNo,";");
            paperinfo.setPaperQsets(temp);
            paperinfo.setUpdateBy(loginUser.getUser().getUserNo());

            return toAjax(coctPaperinfoService.UpdateRecord(loginUser.getUser().getAppCode(), paperinfo));
        }
        return success();
    }

    /**
     * 更新问卷题目
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:update')")
    @Oplog(title = "问卷信息", businessType = BusinessType.UPDATE)
    @PostMapping("/uptPaperquests")
    public AjaxResult uptPaperquests(@RequestBody PaperInfoVo paperInfo)
    {
        String id=paperInfo.getPaperNo();
        String[] nos=paperInfo.getPaperQuests().split(",");
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CoctPaperinfo paperinfo = coctPaperinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id);
        if(paperinfo!=null) {
            String temp = paperinfo.getPaperQuests();
            for (String questsNo : nos) {
                temp=StringUtils.addSplitStr(temp,questsNo,";");
            }
            paperinfo.setPaperQuests(temp);
            paperinfo.setUpdateBy(loginUser.getUser().getUserNo());

            return toAjax(coctPaperinfoService.UpdateRecord(loginUser.getUser().getAppCode(), paperinfo));
        }
        return success();
    }

    /**
     * 删除问卷题目
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:update')")
    @Oplog(title = "问卷信息", businessType = BusinessType.UPDATE)
    @PostMapping("/delPaperquests")
    public AjaxResult delPaperquests(@RequestBody PaperInfoVo paperInfo)
    {
        String id=paperInfo.getPaperNo();
        String questsNo=paperInfo.getQuestsNo();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CoctPaperinfo paperinfo = coctPaperinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id);
        if(paperinfo!=null) {
            String temp = paperinfo.getPaperQuests();
            temp=StringUtils.delSplitStr(temp,questsNo,";");
            paperinfo.setPaperQuests(temp);
            paperinfo.setUpdateBy(loginUser.getUser().getUserNo());

            return toAjax(coctPaperinfoService.UpdateRecord(loginUser.getUser().getAppCode(), paperinfo));
        }
        return success();
    }



    /**
     * 更新问卷规则
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:update')")
    @Oplog(title = "问卷信息", businessType = BusinessType.UPDATE)
    @PostMapping("/uptPaperqrules")
    public AjaxResult uptPaperqrules(@RequestBody PaperInfoVo paperInfo)
    {
        String id=paperInfo.getPaperNo();
        String qrules=paperInfo.getPaperQrules();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CoctPaperinfo paperinfo = coctPaperinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id);
        if(paperinfo!=null) {
            paperinfo.setPaperQrules(qrules);
            paperinfo.setUpdateBy(loginUser.getUser().getUserNo());

            return toAjax(coctPaperinfoService.UpdateRecord(loginUser.getUser().getAppCode(), paperinfo));
        }
        return success();
    }


    /**
     * 更新问卷分数
     */
    @PreAuthorize("@ps.hasPermit('collect:paperinfo:update')")
    @Oplog(title = "问卷信息", businessType = BusinessType.UPDATE)
    @PostMapping("/uptPapertscore")
    public AjaxResult uptPapertscore(@RequestBody PaperInfoVo paperInfo)
    {
        String id=paperInfo.getPaperNo();
        int tscore=paperInfo.getPaperTscore();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        CoctPaperinfo paperinfo = coctPaperinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id);
        if(paperinfo!=null) {
            paperinfo.setPaperTscore(new Long(tscore));
            paperinfo.setUpdateBy(loginUser.getUser().getUserNo());

            return toAjax(coctPaperinfoService.UpdateRecord(loginUser.getUser().getAppCode(), paperinfo));
        }
        return success();
    }
}
