package com.benet.console.controller;

import com.alibaba.fastjson.JSONObject;
import com.benet.collect.domain.*;
import com.benet.collect.service.*;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.PageHelper;
import com.benet.console.utils.ShiroHelper;
import com.benet.console.vmodel.*;
import com.benet.system.domain.SysRuserinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测评
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/examsview")
public class ExamsViewController extends BaseViewController {

    private String prefix = "examsview";
    @Autowired
    private ICoctExamsflowsService examsflowsService;

    @Autowired
    private ICoctExamclassService examclassService;
    @Autowired
    private ICoctExamsinfoService examsinfoService;

    @Autowired
    private ICoctQuestflowsService questflowsService;

    @Autowired
    private ICoctPaperinfoService paperinfoService;

    @Autowired
    private ICoctQuestinfoService questinfoService;

    @Autowired
    private ICoctQuestoptsService questoptsService;


    /**
     * 测评主页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        List<ExamClassVo> mclassList=new ArrayList<>();
        ExamClassVo mclass=null;

        List<CoctExamclass> classList=examclassService.getAllRecords(loginUser.getAppCode());
        if(classList!=null&&classList.size()>0){
            for(CoctExamclass clazz:classList){
                mclass=new ExamClassVo();
                mclass.setClassNo(clazz.getClassNo());
                mclass.setClassName(clazz.getClassName());
                mclass.setClassDes(clazz.getComments());
                mclass.setExamsList(getExamsList(clazz.getClassNo()));

                mclassList.add(mclass);
            }
        }

        model.put("loginer",getLoginer());
        model.put("mclassList", PageHelper.getExamClasses());
        model.put("mitemList",mclassList);
        return prefix + "/index";
    }

    private List<ExamsInfoVo> getExamsList(String classNo)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        List<ExamsInfoVo> examsList=new ArrayList<>();
        ExamsInfoVo exams=null;

        String condition=" class_no='"+classNo+"' ";
        List<CoctExamsinfo> infoList=examsinfoService.getRecordsByPaging(loginUser.getAppCode(),1,4,condition,"id","desc");
        if(infoList!=null&&infoList.size()>0){
            for(CoctExamsinfo info:infoList){
                exams=new ExamsInfoVo();
                exams.setExamsNo(info.getExamsNo());
                exams.setExamsTitle(info.getExamsTitle());
                exams.setExamsPoster(info.getExamsPoster());
                exams.setExamsDesc(info.getExamsDesc());
                exams.setDataFrom(info.getDataFrom());
                exams.setClassNo(info.getClassNo());
                exams.setClassName(info.getClassNo());
                exams.setExamsPrice("免费");
                exams.setExamsQrcode(info.getExamsQrcode());
                exams.setExamsTimes(info.getExamsTimes().toString());
                exams.setExamsDruation(info.getExamsDuration().toString());
                exams.setExamsAdmin(info.getExamsAdmin());

                examsList.add(exams);
            }
        }

        return examsList;
    }

    /**
     * 分类列表
     */
    @GetMapping(value="/catelist")
    public String catelist(ModelMap model,@RequestParam(defaultValue = "000000",value = "classNo") String classNo,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                            @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        List<ExamsInfoVo> examsList=new ArrayList<>();
        ExamsInfoVo exams=null;

        String condition="";
        if(!classNo.equals("000000")){
            condition=" class_no='"+classNo+"' ";
        }
        int count=examsinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CoctExamsinfo> infoList=examsinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");
        if(infoList!=null&&infoList.size()>0){
            for(CoctExamsinfo info:infoList){
                exams=new ExamsInfoVo();
                exams.setExamsNo(info.getExamsNo());
                exams.setExamsTitle(info.getExamsTitle());
                exams.setExamsPoster(info.getExamsPoster());
                exams.setExamsDesc(info.getExamsDesc());
                exams.setDataFrom(info.getDataFrom());
                exams.setClassNo(info.getClassNo());
                exams.setClassName(info.getClassNo());
                exams.setExamsPrice("免费");
                exams.setExamsQrcode(info.getExamsQrcode());
                exams.setExamsTimes(info.getExamsTimes().toString());
                exams.setExamsDruation(info.getExamsDuration().toString());
                exams.setExamsAdmin(info.getExamsAdmin());

                examsList.add(exams);
            }
        }

        PagerInfoVo<ExamsInfoVo> pagerInfo=new PagerInfoVo<ExamsInfoVo>(pageIndex,pageSize,count,examsList);

        model.put("loginer",getLoginer());
        model.put("pagerInfo",pagerInfo);
        model.put("classNo",classNo);
        model.put("mclassList", PageHelper.getExamClasses());
        return prefix + "/catelist";
    }

    /**
     * 历史列表
     */
    @GetMapping(value="/history")
    public String history(ModelMap model,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                            @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        List<ExamsInfoVo> examsList=new ArrayList<>();
        ExamsInfoVo exams=null;

        String condition="";
        int count=paperinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CoctExamsinfo> infoList=examsinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");
        if(infoList!=null&&infoList.size()>0){
            for(CoctExamsinfo info:infoList){
                exams=new ExamsInfoVo();
                exams.setExamsNo(info.getExamsNo());
                exams.setExamsTitle(info.getExamsTitle());
                exams.setExamsPoster(info.getExamsPoster());
                exams.setExamsDesc(info.getExamsDesc());
                exams.setDataFrom(info.getDataFrom());
                exams.setClassNo(info.getClassNo());
                exams.setClassName(info.getClassNo());
                exams.setExamsPrice("免费");
                exams.setExamsQrcode(info.getExamsQrcode());
                exams.setExamsTimes(info.getExamsTimes().toString());
                exams.setExamsDruation(info.getExamsDuration().toString());
                exams.setExamsAdmin(info.getExamsAdmin());

                examsList.add(exams);
            }
        }

        PagerInfoVo<ExamsInfoVo> pagerInfo=new PagerInfoVo<ExamsInfoVo>(pageIndex,pageSize,count,examsList);

        model.put("loginer",getLoginer());
        model.put("pagerInfo",pagerInfo);
        model.put("mclassList", PageHelper.getExamClasses());
        return prefix + "/history";
    }

    /**
     * 详细
     */
    @GetMapping(value="/detail")
    public String detail(ModelMap model,@RequestParam("id") String id) {

        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        CoctExamsinfo info = examsinfoService.getRecordByNo(loginUser.getAppCode(), id);

        ExamsInfoVo exams = new ExamsInfoVo();
        exams.setExamsNo(info.getExamsNo());
        exams.setExamsTitle(info.getExamsTitle());
        exams.setExamsPoster(info.getExamsPoster());
        exams.setExamsDesc(info.getExamsDesc());
        exams.setDataFrom(info.getDataFrom());
        exams.setClassNo(info.getClassNo());
        exams.setClassName(info.getClassNo());
        exams.setExamsPrice("免费");
        exams.setExamsQrcode(info.getExamsQrcode());
        exams.setExamsTimes(info.getExamsTimes().toString());
        exams.setExamsDruation(info.getExamsDuration().toString());
        exams.setExamsAdmin(info.getExamsAdmin());

        model.put("examsInfo", exams);
        model.put("loginer", getLoginer());
        model.put("mclassList", PageHelper.getExamClasses());
        model.put("topExperts", PageHelper.getTopExperts(3));
        return prefix + "/detail";
    }

    /**
     * 生成测评
     */
    @PostMapping("/createExams")
    @ResponseBody
    public AjaxResult createExams(String examsNo){

        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        String condition=" exams_no='"+examsNo+"' And ruser_no='"+loginUser.getUserNo()+"' And mflow_state=0 ";
        List<CoctExamsflows> mflowsList=examsflowsService.getRecordsByPaging(loginUser.getAppCode(),1,10,condition,"id","asc");

        String mflowNo="";
        String paperNo="";
        if(mflowsList!=null&&mflowsList.size()>0){
            mflowNo=mflowsList.get(0).getMflowNo();
        }else{
            mflowNo= UuidUtils.shortUUID();

            paperNo=examsinfoService.getRecordByNo(loginUser.getAppCode(),examsNo).getPaperNo();

            CoctExamsflows pflowsInfo=new CoctExamsflows();
            pflowsInfo.setMflowNo(mflowNo);
            pflowsInfo.setExamsNo(examsNo);
            pflowsInfo.setPaperNo(paperNo);
            pflowsInfo.setRuserNo(loginUser.getUserNo());
            pflowsInfo.setStartTime(DateUtils.getNowDate());
            pflowsInfo.setEnditTime(DateUtils.getNowDate());
            pflowsInfo.setExamsDuration(0L);
            pflowsInfo.setExamsTscore(0L);
            pflowsInfo.setMflowState("0");
            pflowsInfo.setCheckState("0");
            pflowsInfo.setBranchNo("");
            pflowsInfo.setCreateBy(loginUser.getUserNo());
            pflowsInfo.setCreateTime(DateUtils.getNowDate());
            pflowsInfo.setUpdateBy(loginUser.getUserNo());
            pflowsInfo.setUpdateTime(DateUtils.getNowDate());
            pflowsInfo.setDeleteFlag("1");
            pflowsInfo.setComments("");
            pflowsInfo.setAppCode(loginUser.getAppCode());

            if(examsflowsService.AddNewRecord(loginUser.getAppCode(),pflowsInfo)>0){

                List<CoctQuestinfo> qinfoList=null;
                CoctQuestflows qflows=null;
                CoctPaperinfo paperInfo =paperinfoService.getRecordByNo(loginUser.getAppCode(),paperNo);
                //根据问卷类型生成问卷题目
                if(paperInfo.getPaperType().equals("1")){
                    qinfoList=getQuestsByStatic(paperInfo.getPaperQuests());//生成固定问卷
                }else{
                    qinfoList=getQuestsByRandom(paperInfo.getPaperQsets(),paperInfo.getPaperQrules()); //生成随机问卷
                }

                if(qinfoList!=null&&qinfoList.size()>0){
                    for(CoctQuestinfo qinfo:qinfoList){

                        qflows=new CoctQuestflows();
                        qflows.setQflowNo(UuidUtils.shortUUID());
                        qflows.setMflowNo(mflowNo);
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
            else{
                mflowNo="";
            }
        }
        if(StringUtils.isEmpty(mflowNo)){
            return error();
        }else {
            return success(mflowNo);
        }
    }

    private List<CoctQuestinfo> getQuestsByRandom(String paperQsets,String paperQrules){

        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        if(StringUtils.isNotEmpty(paperQsets)&&StringUtils.isNotEmpty(paperQrules)){
            String[] qsetNos=paperQsets.split(";");
            List<QuestRuleVo> ruleList= JSONObject.parseArray(paperQrules, QuestRuleVo.class);

            //从题库中分类抽取题目
            List<String> quest1List=new ArrayList<>();//1填空题
            List<String> quest2List=new ArrayList<>();//2单选题
            List<String> quest3List=new ArrayList<>();//3多选题
            List<CoctQuestinfo> tempList=null;
            for(String qsetNo:qsetNos){
                tempList=questinfoService.getRecordsByPaging(loginUser.getAppCode(),1,1000," qsets_no='"+qsetNo+"'","id","asc");
                if(tempList!=null&&tempList.size()>0){
                    for(CoctQuestinfo temp:tempList){
                        //1填空题 2单选题 3多选题
                        if(temp.getQuestType().equals("1")){
                            quest1List.add(temp.getQuestNo());
                        }else if(temp.getQuestType().equals("2")){
                            quest2List.add(temp.getQuestNo());
                        }else if(temp.getQuestType().equals("3")){
                            quest3List.add(temp.getQuestNo());
                        }
                    }
                }
            }

            //按抽题规则生成题目
            for(QuestRuleVo rule:ruleList){
                if(rule.getQuestType().equals("1")){
                    while(quest1List.size()>rule.getQuestLimit()){
                        quest1List.remove((new Random()).nextInt(quest1List.size()-1));
                    }
                }else if(rule.getQuestType().equals("2")){
                    while(quest2List.size()>rule.getQuestLimit()){
                        quest2List.remove((new Random()).nextInt(quest2List.size()-1));
                    }
                }else if(rule.getQuestType().equals("3")){
                    while(quest3List.size()>rule.getQuestLimit()){
                        quest3List.remove((new Random()).nextInt(quest3List.size()-1));
                    }
                }
            }

            //生成题目
            List<CoctQuestinfo> infoList=new ArrayList<>();
            CoctQuestinfo info=null;
            for(String questNo:quest1List){
                info=questinfoService.getRecordByNo(loginUser.getAppCode(),questNo);
                infoList.add(info);
            }
            for(String questNo:quest2List){
                info=questinfoService.getRecordByNo(loginUser.getAppCode(),questNo);
                infoList.add(info);
            }
            for(String questNo:quest3List){
                info=questinfoService.getRecordByNo(loginUser.getAppCode(),questNo);
                infoList.add(info);
            }
            return infoList;
        }
           return null;
    }

    private List<CoctQuestinfo> getQuestsByStatic(String paperQuests)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        if(StringUtils.isNotEmpty(paperQuests)) {
            List<CoctQuestinfo> infoList = new ArrayList<>();
            CoctQuestinfo info=null;
            String[] questNos=paperQuests.split(";");
            for (String questNo:questNos){
                info=questinfoService.getRecordByNo(loginUser.getAppCode(),questNo);
                infoList.add(info);
            }
            return infoList;
        }
        return null;
    }

    /**
     * 答题页
     */
    @GetMapping(value="/collect")
    public String collect(ModelMap model,@RequestParam("mflowNo") String mflowNo,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                          @RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        ExamsInfoVo exams=null;
        CoctExamsflows examsFlows=examsflowsService.getRecordByNo(loginUser.getAppCode(),mflowNo);
        if(examsFlows!=null){
            CoctExamsinfo info =examsinfoService.getRecordByNo(loginUser.getAppCode(),examsFlows.getExamsNo());

            exams=new ExamsInfoVo();
            exams.setExamsNo(info.getExamsNo());
            exams.setExamsTitle(info.getExamsTitle());
            exams.setExamsPoster(info.getExamsPoster());
            exams.setExamsDesc(info.getExamsDesc());
            exams.setDataFrom(info.getDataFrom());
            exams.setClassName(info.getClassNo());
            exams.setExamsPrice("免费");
            exams.setExamsQrcode(info.getExamsQrcode());
            exams.setExamsTimes(info.getExamsTimes().toString());
            exams.setExamsDruation(info.getExamsDuration().toString());
            exams.setExamsAdmin(info.getExamsAdmin());
        }

        List<QuestInfoVo> questList=new ArrayList<>();
        String condition=" mflow_no='"+mflowNo+"' ";
        int count=questflowsService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CoctQuestflows> qflowsList=questflowsService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","asc");
        if(qflowsList!=null&&qflowsList.size()>0) {

            CoctQuestflows questflow = qflowsList.get(0);
            CoctQuestinfo questInfo=questinfoService.getRecordByNo(loginUser.getAppCode(),questflow.getQuestNo());

            QuestInfoVo questInfoVo = new QuestInfoVo();
            questInfoVo.setQuestNo(questInfo.getQuestNo());
            questInfoVo.setQuestTitle(questInfo.getQuestTitle());
            questInfoVo.setQuestDesc(questInfo.getQuestDesc());
            questInfoVo.setQuestMust(questInfo.getQuestMust());
            questInfoVo.setQuestType(questInfo.getQuestType());

            List<CoctQuestopts> infoList = questoptsService.getRecordsByClassNo(loginUser.getAppCode(), questflow.getQuestNo());
            if (infoList != null && infoList.size() > 0) {

                List<QuestOptsVo> qoptsList = new ArrayList<>();
                QuestOptsVo qopts = null;
                for (CoctQuestopts info : infoList) {
                    qopts = new QuestOptsVo();
                    qopts.setOptsNo(info.getOptsNo());
                    qopts.setOptsTitle(info.getOptsTitle());
                    qopts.setOptsDesc(info.getOptsDesc());
                    qopts.setOptsScore(info.getOptsScore() + "");

                    qoptsList.add(qopts);
                }
                questInfoVo.setQuestOpts(qoptsList);
            } else {
                questInfoVo.setQuestOpts(null);
            }
            questList.add(questInfoVo);
        }

        PagerInfoVo<QuestInfoVo> pagerInfo=new PagerInfoVo<QuestInfoVo>(pageIndex,pageSize,count,questList);

        model.put("loginer", getLoginer());
        model.put("mflowNo", mflowNo);
        model.put("examsInfo", exams);
        model.put("pagerInfo",pagerInfo);
        return prefix + "/collect";
    }
}