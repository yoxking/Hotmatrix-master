package com.benet.workflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.workflow.domain.FlwWorkflows;

/**
 * 工作流Service接口
 * 
 * @author yoxking
 * @date 2020-05-17
 */
public interface IFlwWorkflowsService
{
    /**
     * 查询所有工作流列表
     *
     * @return 工作流集合
     */
    public List<FlwWorkflows> getAllRecords();

    /**
     * 按分类查询工作流列表
     *
     * @param classNo 分类编号
     * @return 工作流集合
     */
    public List<FlwWorkflows> getRecordsByClassNo(String classNo);

    /**
     * 分页查询工作流列表
     *
     * @param model 分页模型
     * @return 工作流集合
     */
    public List<FlwWorkflows> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询工作流列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 工作流集合
     */
    public List<FlwWorkflows> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询工作流
     *
     * @param no 工作流ID
     * @return 工作流
     */
    public FlwWorkflows getRecordByNo(String no);

    /**
     * 查询工作流名称
     *
     * @param no 工作流ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询工作流计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增工作流
     *
     * @param info 工作流
     * @return 结果
     */
    public int AddNewRecord(FlwWorkflows info);

    /**
     * 更新工作流
     *
     * @param info 工作流
     * @return 结果
     */
    public int UpdateRecord(FlwWorkflows info);

    /**
     * 硬删除工作流
     *
     * @param no 工作流ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除工作流
     *
     * @param nos 工作流IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除工作流
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除工作流
     *
     * @param no 工作流ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除工作流
     *
     * @param nos 工作流IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除工作流
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
