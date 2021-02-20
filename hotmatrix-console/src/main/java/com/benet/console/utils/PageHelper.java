package com.benet.console.utils;

import com.benet.collect.domain.CoctExamclass;
import com.benet.collect.service.ICoctExamclassService;
import com.benet.common.utils.date.DateUtils;
import com.benet.console.vmodel.ContentInfoVo;
import com.benet.console.vmodel.ExamClassVo;
import com.benet.console.vmodel.ExpertInfoVo;
import com.benet.system.domain.SysContentinfo;
import com.benet.system.domain.SysRuserinfo;
import com.benet.system.service.ISysContentinfoService;
import com.benet.system.service.ISysRuserinfoService;

import java.util.ArrayList;
import java.util.List;

/**
 * page 帮助类
 *
 * @author yoxking
 */
public class PageHelper {


    /**
     * 测评类型列表
     */
    public static List<ExamClassVo> getExamClasses() {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        ICoctExamclassService examclassService = BeanHelper.getBean(ICoctExamclassService.class);
        List<ExamClassVo> mclassList = new ArrayList<>();
        ExamClassVo mclass = null;

        List<CoctExamclass> infoList = examclassService.getAllRecords(loginUser.getAppCode());
        if (infoList != null && infoList.size() > 0) {
            for (CoctExamclass info : infoList) {
                mclass = new ExamClassVo();
                mclass.setClassNo(info.getClassNo());
                mclass.setClassName(info.getClassName());
                mclass.setClassDes(info.getComments());
                mclass.setExamsList(null);

                mclassList.add(mclass);
            }
        }
        return mclassList;
    }

    /**
     * 热门资讯列表
     */
    public static List<ContentInfoVo> getHotopReads(int top)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        ISysContentinfoService contentinfoService=BeanHelper.getBean(ISysContentinfoService.class);
        List<ContentInfoVo> contzList=new ArrayList<>();
        ContentInfoVo contz=null;

        String condition="";
        List<SysContentinfo> infoList=contentinfoService.getRecordsByPaging(loginUser.getAppCode(),1,top,condition,"pubdate","desc");
        if(infoList!=null&&infoList.size()>0){
            for(SysContentinfo info:infoList){
                contz=new ContentInfoVo();
                contz.setCntzNo(info.getContzNo());
                contz.setTitle(info.getTitle());
                contz.setClassName(info.getClassNo());
                contz.setAuthor(info.getAuthor());
                contz.setPoster(info.getPoster());
                contz.setPubdate(DateUtils.dateTime(info.getPubdate()));
                contz.setAbstracts(info.getAbstracts());
                contz.setNcontents(info.getNcontents());
                contz.setHitCount(info.getHitCount()+"");
                contz.setCheckState(info.getCheckState());
                contzList.add(contz);
            }
        }
        return contzList;
    }

    /**
     * 推荐咨询师列表
     */
    public static List<ExpertInfoVo> getTopExperts(int top) {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        ISysRuserinfoService ruserinfoService = BeanHelper.getBean(ISysRuserinfoService.class);
        List<ExpertInfoVo> expertList = new ArrayList<>();
        ExpertInfoVo expert = null;

        String condition = "";
        List<SysRuserinfo> infoList = ruserinfoService.getRecordsByPaging(loginUser.getAppCode(), 1, top, condition, "id", "desc");
        if (infoList != null && infoList.size() > 0) {
            for (SysRuserinfo info : infoList) {
                expert = new ExpertInfoVo();
                expert.setRuserNo(info.getUserNo());
                expert.setRuserName(info.getLoginName());
                expert.setRnickName(info.getUserEnname());
                expert.setRuserType(info.getUserType());
                expert.setSex(info.getSex());
                expert.setTelephone(info.getTelephone());
                expert.setEmail(info.getEmail());
                expert.setAvatar(info.getAvatar());
                expert.setProfile(info.getComments());

                expertList.add(expert);
            }
        }
        return expertList;
    }
}
