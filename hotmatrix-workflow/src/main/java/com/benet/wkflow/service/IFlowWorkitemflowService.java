package com.benet.wkflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlowWorkitemflow;

/**
 * 工作项Service接口
 * 
 * @author yoxking
 * @date 2021-01-31
 */
public interface IFlowWorkitemflowService 
{
    /**
     * 查询所有工作项列表
     *
     * @param appCode 应用编号
     * @return 工作项集合
     */
    public List<FlowWorkitemflow> getAllRecords(String appCode);

    /**
     * 按分类查询工作项列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 工作项集合
     */
    public List<FlowWorkitemflow> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询工作项列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 工作项集合
     */
    public List<FlowWorkitemflow> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询工作项列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 工作项集合
     */
    public List<FlowWorkitemflow> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询工作项
     *
     * @param appCode 应用编号
     * @param no 工作项ID
     * @return 工作项
     */
    public FlowWorkitemflow getRecordByNo(String appCode,String no);

    /**
     * 查询工作项名称
     *
     * @param appCode 应用编号
     * @param no 工作项ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询工作项计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增工作项
     *
     * @param appCode 应用编号
     * @param info 工作项
     * @return 结果
     */
    public int AddNewRecord(String appCode,FlowWorkitemflow info);

    /**
     * 更新工作项
     *
     * @param appCode 应用编号
     * @param info 工作项
     * @return 结果
     */
    public int UpdateRecord(String appCode,FlowWorkitemflow info);

    /**
     * 硬删除工作项
     *
     * @param appCode 应用编号
     * @param no 工作项ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除工作项
     *
     * @param appCode 应用编号
     * @param nos 工作项IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除工作项
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除工作项
     *
     * @param appCode 应用编号
     * @param no 工作项ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除工作项
     *
     * @param appCode 应用编号
     * @param nos 工作项IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除工作项
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
