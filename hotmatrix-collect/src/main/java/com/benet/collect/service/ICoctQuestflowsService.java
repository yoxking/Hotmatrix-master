package com.benet.collect.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CoctQuestflows;

/**
 * 答题结果Service接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public interface ICoctQuestflowsService 
{
    /**
     * 查询所有答题结果列表
     *
     * @param appCode 应用编号
     * @return 答题结果集合
     */
    public List<CoctQuestflows> getAllRecords(String appCode);

    /**
     * 按分类查询答题结果列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 答题结果集合
     */
    public List<CoctQuestflows> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询答题结果列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 答题结果集合
     */
    public List<CoctQuestflows> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询答题结果列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 答题结果集合
     */
    public List<CoctQuestflows> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询答题结果
     *
     * @param appCode 应用编号
     * @param no 答题结果ID
     * @return 答题结果
     */
    public CoctQuestflows getRecordByNo(String appCode,String no);

    /**
     * 查询答题结果名称
     *
     * @param appCode 应用编号
     * @param no 答题结果ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询答题结果计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增答题结果
     *
     * @param appCode 应用编号
     * @param info 答题结果
     * @return 结果
     */
    public int AddNewRecord(String appCode,CoctQuestflows info);

    /**
     * 更新答题结果
     *
     * @param appCode 应用编号
     * @param info 答题结果
     * @return 结果
     */
    public int UpdateRecord(String appCode,CoctQuestflows info);

    /**
     * 硬删除答题结果
     *
     * @param appCode 应用编号
     * @param no 答题结果ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除答题结果
     *
     * @param appCode 应用编号
     * @param nos 答题结果IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除答题结果
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除答题结果
     *
     * @param appCode 应用编号
     * @param no 答题结果ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除答题结果
     *
     * @param appCode 应用编号
     * @param nos 答题结果IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除答题结果
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
