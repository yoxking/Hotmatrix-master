package com.benet.wkflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlowWorktaskflow;

/**
 * 任务实例Service接口
 * 
 * @author yoxking
 * @date 2021-01-31
 */
public interface IFlowWorktaskflowService 
{
    /**
     * 查询所有任务实例列表
     *
     * @param appCode 应用编号
     * @return 任务实例集合
     */
    public List<FlowWorktaskflow> getAllRecords(String appCode);

    /**
     * 按分类查询任务实例列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 任务实例集合
     */
    public List<FlowWorktaskflow> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询任务实例列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 任务实例集合
     */
    public List<FlowWorktaskflow> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询任务实例列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 任务实例集合
     */
    public List<FlowWorktaskflow> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询任务实例
     *
     * @param appCode 应用编号
     * @param no 任务实例ID
     * @return 任务实例
     */
    public FlowWorktaskflow getRecordByNo(String appCode,String no);

    /**
     * 查询任务实例名称
     *
     * @param appCode 应用编号
     * @param no 任务实例ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询任务实例计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增任务实例
     *
     * @param appCode 应用编号
     * @param info 任务实例
     * @return 结果
     */
    public int AddNewRecord(String appCode,FlowWorktaskflow info);

    /**
     * 更新任务实例
     *
     * @param appCode 应用编号
     * @param info 任务实例
     * @return 结果
     */
    public int UpdateRecord(String appCode,FlowWorktaskflow info);

    /**
     * 硬删除任务实例
     *
     * @param appCode 应用编号
     * @param no 任务实例ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除任务实例
     *
     * @param appCode 应用编号
     * @param nos 任务实例IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除任务实例
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除任务实例
     *
     * @param appCode 应用编号
     * @param no 任务实例ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除任务实例
     *
     * @param appCode 应用编号
     * @param nos 任务实例IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除任务实例
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
