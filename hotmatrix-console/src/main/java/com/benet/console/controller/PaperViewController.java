package com.benet.console.controller;

import com.benet.collect.domain.*;
import com.benet.collect.service.*;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.ShiroUtils;
import com.benet.console.vmodel.*;
import com.benet.system.domain.SysRuserinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 测评
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/paperview")
public class PaperViewController extends BaseViewController {

    private String prefix = "paperview";

    @Autowired
    private ICctPaperflowsService paperflowsService;

    @Autowired
    private ICctQuestflowsService questflowsService;

    @Autowired
    private ICctPaperinfoService paperinfoService;

    @Autowired
    private ICctQuestinfoService questinfoService;

    @Autowired
    private ICctQuestoptsService questoptsService;


    /**
     * 测评主页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model)
    {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();
        List<PaperInfoVo> paperList=new ArrayList<>();
        PaperInfoVo paper=null;
        List<CctPaperinfo> infoList=paperinfoService.getAllRecords(loginUser.getAppCode());
        if(infoList!=null&&infoList.size()>0){
            for(CctPaperinfo info:infoList){
                paper=new PaperInfoVo();
                paper.setPaperNo(info.getPaperNo());
                paper.setPaperTitle(info.getPaperTitle());
                paper.setPaperPoster(info.getPaperPoster());
                paper.setPaperDesc(info.getPaperDesc());
                paper.setPaperPrice("免费");
                paper.setExamTimes("0");
                paper.setExDruation(info.getExDuration()+"分钟");

                paperList.add(paper);
            }
        }

        model.put("loginer",getLoginer());
        model.put("paperList",paperList);
        return prefix + "/index";
    }

    /**
     * 分类列表
     */
    @GetMapping(value="/classlist")
    public String classlist(ModelMap model,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                            @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();
        List<PaperInfoVo> paperList=new ArrayList<>();
        PaperInfoVo paper=null;

        String condition="";
        int count=paperinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CctPaperinfo> infoList=paperinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");
        if(infoList!=null&&infoList.size()>0){
            for(CctPaperinfo info:infoList){
                paper=new PaperInfoVo();
                paper.setPaperNo(info.getPaperNo());
                paper.setPaperTitle(info.getPaperTitle());
                paper.setPaperPoster(info.getPaperPoster());
                paper.setPaperDesc(info.getPaperDesc());
                paper.setPaperPrice("免费");
                paper.setExamTimes("0");
                paper.setExDruation(info.getExDuration()+"分钟");
                paperList.add(paper);
            }
        }

        PagerInfoVo<PaperInfoVo> pagerInfo=new PagerInfoVo<PaperInfoVo>(pageIndex,pageSize,count,paperList);

        model.put("loginer",getLoginer());
        model.put("pagerInfo",pagerInfo);
        return prefix + "/classlist";
    }

    /**
     * 详细
     */
    @GetMapping(value="/detail")
    public String detail(ModelMap model,@RequestParam("id") String id) {

        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();
        CctPaperinfo info = paperinfoService.getRecordByNo(loginUser.getAppCode(), id);
        PaperInfoVo paper = new PaperInfoVo();
        paper.setPaperNo(info.getPaperNo());
        paper.setPaperTitle(info.getPaperTitle());
        paper.setPaperPoster(info.getPaperPoster());
        paper.setPaperDesc(info.getPaperDesc());
        paper.setPaperPrice("免费");
        paper.setExamTimes("0");
        paper.setExDruation(info.getExDuration() + "分钟");

        model.put("paperInfo", paper);
        model.put("loginer", getLoginer());
        return prefix + "/detail";
    }

    /**
     * 答题页
     */
    @GetMapping(value="/collect")
    public String collect(ModelMap model,@RequestParam("id") String id)
    {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();

        String pflowNo="";
        String condition=" paper_no='"+id+"' And ruser_no='"+loginUser.getUserNo()+"' And pflow_state=0 ";
        List<CctPaperflows> pflowsList=paperflowsService.getRecordsByPaging(loginUser.getAppCode(),1,10,condition,"id","asc");

        CctPaperinfo paperInfo =paperinfoService.getRecordByNo(loginUser.getAppCode(),id);
        if(pflowsList!=null&&pflowsList.size()>0){

        }else{
            pflowNo=UuidUtils.shortUUID();
            CctPaperflows pflowsInfo=new CctPaperflows();
            pflowsInfo.setPflowNo(pflowNo);
            pflowsInfo.setPaperNo(id);
            pflowsInfo.setRuserNo(loginUser.getUserNo());
            pflowsInfo.setStartTime(DateUtils.getNowDate());
            pflowsInfo.setEnditTime(DateUtils.getNowDate());
            pflowsInfo.setPflowTime("0");
            pflowsInfo.setPflowScore(0L);
            pflowsInfo.setPflowState("0");
            pflowsInfo.setBranchNo("");
            pflowsInfo.setCreateBy(loginUser.getUserNo());
            pflowsInfo.setCreateTime(DateUtils.getNowDate());
            pflowsInfo.setUpdateBy(loginUser.getUserNo());
            pflowsInfo.setUpdateTime(DateUtils.getNowDate());
            pflowsInfo.setDeleteFlag("1");
            pflowsInfo.setComments("");
            pflowsInfo.setAppCode(loginUser.getAppCode());

            if(paperflowsService.AddNewRecord(loginUser.getAppCode(),pflowsInfo)>0){

                CctQuestflows qflows=null;
                List<CctQuestinfo> qinfoList=questinfoService.getRecordsByClassNo(loginUser.getAppCode(),paperInfo.getQuestClass());
                if(qinfoList!=null&&qinfoList.size()>0){
                    for(CctQuestinfo qinfo:qinfoList){
                        qflows=new CctQuestflows();
                        qflows.setQflowNo(UuidUtils.shortUUID());
                        qflows.setPflowNo(pflowNo);
                        qflows.setQuestNo(qinfo.getQuestNo());
                        qflows.setQtoptValue("");
                        qflows.setQtoptScore(0L);
                        qflows.setQtoptFlag("0");
                        qflows.setQtoptView("");
                        qflows.setCheckState("0");
                        qflows.setBranchNo("");
                        qflows.setCreateBy(loginUser.getUserNo());
                        qflows.setCreateTime(DateUtils.getNowDate());
                        qflows.setUpdateBy(loginUser.getUserNo());
                        qflows.setUpdateTime(DateUtils.getNowDate());
                        qflows.setDeleteFlag("1");
                        qflows.setComments("");
                        qflows.setAppCode(loginUser.getAppCode());

                        questflowsService.AddNewRecord(loginUser.getAppCode(),qflows);

                    }
                }
            }

        }

        PaperInfoVo paperInfoVo=new PaperInfoVo();
        paperInfoVo.setPaperNo(pflowNo);
        paperInfoVo.setPaperTitle(paperInfo.getPaperTitle());
        paperInfoVo.setPaperPoster(paperInfo.getPaperPoster());
        paperInfoVo.setPaperDesc(paperInfo.getPaperDesc());
        paperInfoVo.setPaperPrice("0");
        paperInfoVo.setExamTimes("0");
        paperInfoVo.setExDruation(paperInfo.getExDuration()+"分钟");

        model.put("loginer",getLoginer());
        model.put("paperInfo",paperInfo);
        return prefix + "/collect";
    }

    @PostMapping("/getQuestData")
    @ResponseBody
    public AjaxResult getQuestData(String pflowNo, String pageIndex)
    {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();

        String condition=" pflow_no='"+pflowNo+"' ";
        List<CctQuestflows> qflowsList=questflowsService.getRecordsByPaging(loginUser.getAppCode(),Integer.parseInt(pageIndex),1,condition,"id","asc");

        if(qflowsList!=null&&qflowsList.size()>0) {

            CctQuestflows questflow=qflowsList.get(0);

            QuestInfoVo questInfo=new QuestInfoVo();
            questInfo.setQuestNo(questflow.getQuestNo());
            questInfo.setQuestTitle(questflow.getQuestNo());
            questInfo.setQuestDesc(questflow.getQuestNo());
            questInfo.setQuestMust(questflow.getQuestNo());
            questInfo.setQuestType(questflow.getQuestNo());

            List<CctQuestopts> qoptsList=questoptsService.getRecordsByClassNo(loginUser.getAppCode(),questflow.getQuestNo());
            if(qoptsList!=null&&qoptsList.size()>0){

                List<QuestOptVo> questoptsList=new ArrayList<>();
                QuestOptVo questOpt=null;
                for(CctQuestopts qopt:qoptsList){
                    questOpt=new QuestOptVo();
                    questOpt.setOptNo(qopt.getOptNo());
                    questOpt.setOptTitle(qopt.getOptTitle());
                    questOpt.setOptDesc(qopt.getOptDesc());
                    questOpt.setOptScore(qopt.getOptScore()+"");

                    questoptsList.add(questOpt);
                }
                questInfo.setQuestOpts(questoptsList);
            }else{
                questInfo.setQuestOpts(null);
            }

            return AjaxResult.success(questInfo);
        }
        else{
            return error();
        }
    }
}
