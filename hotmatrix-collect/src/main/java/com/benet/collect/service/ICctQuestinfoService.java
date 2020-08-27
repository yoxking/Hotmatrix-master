package com.benet.collect.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CctQuestinfo;

/**
 * 测评题库Service接口
 * 
 * @author yoxking
 * @date 2020-08-27
 */
public interface ICctQuestinfoService 
{
    /**
     * 查询所有测评题库列表
     *
     * @param appCode 应用编号
     * @return 测评题库集合
     */
    public List<CctQuestinfo> getAllRecords(String appCode);

    /**
     * 按分类查询测评题库列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 测评题库集合
     */
    public List<CctQuestinfo> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询测评题库列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 测评题库集合
     */
    public List<CctQuestinfo> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询测评题库列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 测评题库集合
     */
    public List<CctQuestinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询测评题库
     *
     * @param appCode 应用编号
     * @param no 测评题库ID
     * @return 测评题库
     */
    public CctQuestinfo getRecordByNo(String appCode,String no);

    /**
     * 查询测评题库名称
     *
     * @param appCode 应用编号
     * @param no 测评题库ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询测评题库计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增测评题库
     *
     * @param appCode 应用编号
     * @param info 测评题库
     * @return 结果
     */
    public int AddNewRecord(String appCode,CctQuestinfo info);

    /**
     * 更新测评题库
     *
     * @param appCode 应用编号
     * @param info 测评题库
     * @return 结果
     */
    public int UpdateRecord(String appCode,CctQuestinfo info);

    /**
     * 硬删除测评题库
     *
     * @param appCode 应用编号
     * @param no 测评题库ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除测评题库
     *
     * @param appCode 应用编号
     * @param nos 测评题库IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除测评题库
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除测评题库
     *
     * @param appCode 应用编号
     * @param no 测评题库ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除测评题库
     *
     * @param appCode 应用编号
     * @param nos 测评题库IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除测评题库
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
