package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CctQuestflowsMapper;
import com.benet.collect.domain.CctQuestflows;
import com.benet.collect.service.ICctQuestflowsService;

/**
 * 答题结果Service业务层处理
 * 
 * @author yoxking
 * @date 2020-10-06
 */
@Service
public class CctQuestflowsServiceImpl implements ICctQuestflowsService 
{
    @Autowired
    private CctQuestflowsMapper cctQuestflowsMapper;

    /**
     * 查询所有答题结果列表
     *
     * @param appCode 应用编号
     * @return 答题结果集合
     */
    @Override
    public List<CctQuestflows> getAllRecords(String appCode) {
        return cctQuestflowsMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询答题结果列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 答题结果集合
     */
    @Override
    public List<CctQuestflows> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cctQuestflowsMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询答题结果列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 答题结果集合
     */
    @Override
    public List<CctQuestflows> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cctQuestflowsMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询答题结果列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 答题结果集合
     */
    @Override
    public List<CctQuestflows> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cctQuestflowsMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询答题结果
     *
     * @param appCode 应用编号
     * @param no 答题结果ID
     * @return 答题结果
     */
    @Override
    public CctQuestflows getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctQuestflowsMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询答题结果名称
     *
     * @param appCode 应用编号
     * @param no 答题结果ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctQuestflowsMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询答题结果计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cctQuestflowsMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增答题结果
     *
     * @param appCode 应用编号
     * @param info 答题结果
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CctQuestflows info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cctQuestflowsMapper.AddNewRecord(info);
    }

    /**
     * 更新答题结果
     *
     * @param appCode 应用编号
     * @param info 答题结果
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CctQuestflows info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cctQuestflowsMapper.UpdateRecord(info);
    }

    /**
     * 硬删除答题结果
     *
     * @param appCode 应用编号
     * @param no 答题结果ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctQuestflowsMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除答题结果
     *
     * @param appCode 应用编号
     * @param nos 答题结果IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctQuestflowsMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除答题结果
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cctQuestflowsMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除答题结果
     *
     * @param appCode 应用编号
     * @param no 答题结果ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctQuestflowsMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除答题结果
     *
     * @param appCode 应用编号
     * @param nos 答题结果IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctQuestflowsMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除答题结果
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cctQuestflowsMapper.SoftDeleteByCondition(appCode,condition);
    }
}
