package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CctExamsinfoMapper;
import com.benet.collect.domain.CctExamsinfo;
import com.benet.collect.service.ICctExamsinfoService;

/**
 * 测评信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-11-10
 */
@Service
public class CctExamsinfoServiceImpl implements ICctExamsinfoService 
{
    @Autowired
    private CctExamsinfoMapper cctExamsinfoMapper;

    /**
     * 查询所有测评信息列表
     *
     * @param appCode 应用编号
     * @return 测评信息集合
     */
    @Override
    public List<CctExamsinfo> getAllRecords(String appCode) {
        return cctExamsinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询测评信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 测评信息集合
     */
    @Override
    public List<CctExamsinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cctExamsinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询测评信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 测评信息集合
     */
    @Override
    public List<CctExamsinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cctExamsinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询测评信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 测评信息集合
     */
    @Override
    public List<CctExamsinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cctExamsinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询测评信息
     *
     * @param appCode 应用编号
     * @param no 测评信息ID
     * @return 测评信息
     */
    @Override
    public CctExamsinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctExamsinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询测评信息名称
     *
     * @param appCode 应用编号
     * @param no 测评信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctExamsinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询测评信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cctExamsinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增测评信息
     *
     * @param appCode 应用编号
     * @param info 测评信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CctExamsinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cctExamsinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新测评信息
     *
     * @param appCode 应用编号
     * @param info 测评信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CctExamsinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cctExamsinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除测评信息
     *
     * @param appCode 应用编号
     * @param no 测评信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctExamsinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除测评信息
     *
     * @param appCode 应用编号
     * @param nos 测评信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctExamsinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除测评信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cctExamsinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除测评信息
     *
     * @param appCode 应用编号
     * @param no 测评信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctExamsinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除测评信息
     *
     * @param appCode 应用编号
     * @param nos 测评信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctExamsinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除测评信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cctExamsinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
