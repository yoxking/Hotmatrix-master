package com.benet.wkflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlowFlowarchivs;

/**
 * 归档信息Service接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public interface IFlowFlowarchivsService 
{
    /**
     * 查询所有归档信息列表
     *
     * @param appCode 应用编号
     * @return 归档信息集合
     */
    public List<FlowFlowarchivs> getAllRecords(String appCode);

    /**
     * 按分类查询归档信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 归档信息集合
     */
    public List<FlowFlowarchivs> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询归档信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 归档信息集合
     */
    public List<FlowFlowarchivs> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询归档信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 归档信息集合
     */
    public List<FlowFlowarchivs> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询归档信息
     *
     * @param appCode 应用编号
     * @param no 归档信息ID
     * @return 归档信息
     */
    public FlowFlowarchivs getRecordByNo(String appCode,String no);

    /**
     * 查询归档信息名称
     *
     * @param appCode 应用编号
     * @param no 归档信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询归档信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增归档信息
     *
     * @param appCode 应用编号
     * @param info 归档信息
     * @return 结果
     */
    public int AddNewRecord(String appCode,FlowFlowarchivs info);

    /**
     * 更新归档信息
     *
     * @param appCode 应用编号
     * @param info 归档信息
     * @return 结果
     */
    public int UpdateRecord(String appCode,FlowFlowarchivs info);

    /**
     * 硬删除归档信息
     *
     * @param appCode 应用编号
     * @param no 归档信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除归档信息
     *
     * @param appCode 应用编号
     * @param nos 归档信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除归档信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除归档信息
     *
     * @param appCode 应用编号
     * @param no 归档信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除归档信息
     *
     * @param appCode 应用编号
     * @param nos 归档信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除归档信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
