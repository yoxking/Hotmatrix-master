package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CoctQuestinfoMapper;
import com.benet.collect.domain.CoctQuestinfo;
import com.benet.collect.service.ICoctQuestinfoService;

/**
 * 测评题库Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class CoctQuestinfoServiceImpl implements ICoctQuestinfoService 
{
    @Autowired
    private CoctQuestinfoMapper coctQuestinfoMapper;

    /**
     * 查询所有测评题库列表
     *
     * @param appCode 应用编号
     * @return 测评题库集合
     */
    @Override
    public List<CoctQuestinfo> getAllRecords(String appCode) {
        return coctQuestinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询测评题库列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 测评题库集合
     */
    @Override
    public List<CoctQuestinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return coctQuestinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询测评题库列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 测评题库集合
     */
    @Override
    public List<CoctQuestinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return coctQuestinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询测评题库列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 测评题库集合
     */
    @Override
    public List<CoctQuestinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

        PagingModel model = new PagingModel();
        model.setPageIndex((pageIndex-1) * pageSize);
        model.setPageSize(pageSize);
        model.setCondition(condition);
        if (StringUtils.isEmpty(orderField)) {
            model.setOrderField("id");
        } else {
            model.setOrderField(orderField);
        }
        if (StringUtils.isEmpty(orderType)) {
            model.setOrderType("Asc");
        } else {
            model.setOrderType(orderType);
        }
        return coctQuestinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询测评题库
     *
     * @param appCode 应用编号
     * @param no 测评题库ID
     * @return 测评题库
     */
    @Override
    public CoctQuestinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctQuestinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询测评题库名称
     *
     * @param appCode 应用编号
     * @param no 测评题库ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctQuestinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询测评题库计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return coctQuestinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增测评题库
     *
     * @param appCode 应用编号
     * @param info 测评题库
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CoctQuestinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return coctQuestinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新测评题库
     *
     * @param appCode 应用编号
     * @param info 测评题库
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CoctQuestinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return coctQuestinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除测评题库
     *
     * @param appCode 应用编号
     * @param no 测评题库ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctQuestinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除测评题库
     *
     * @param appCode 应用编号
     * @param nos 测评题库IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctQuestinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除测评题库
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return coctQuestinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除测评题库
     *
     * @param appCode 应用编号
     * @param no 测评题库ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctQuestinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除测评题库
     *
     * @param appCode 应用编号
     * @param nos 测评题库IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctQuestinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除测评题库
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return coctQuestinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
