package com.benet.workflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.workflow.domain.FlwFlowtaske;

/**
 * 流程任务Service接口
 * 
 * @author yoxking
 * @date 2020-05-17
 */
public interface IFlwFlowtaskeService 
{
    /**
     * 查询所有流程任务列表
     *
     * @return 流程任务集合
     */
    public List<FlwFlowtaske> getAllRecords();

    /**
     * 按分类查询流程任务列表
     *
     * @param classNo 分类编号
     * @return 流程任务集合
     */
    public List<FlwFlowtaske> getRecordsByClassNo(String classNo);

    /**
     * 分页查询流程任务列表
     *
     * @param model 分页模型
     * @return 流程任务集合
     */
    public List<FlwFlowtaske> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询流程任务列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 流程任务集合
     */
    public List<FlwFlowtaske> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询流程任务
     *
     * @param no 流程任务ID
     * @return 流程任务
     */
    public FlwFlowtaske getRecordByNo(String no);

    /**
     * 查询流程任务名称
     *
     * @param no 流程任务ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询流程任务计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增流程任务
     *
     * @param info 流程任务
     * @return 结果
     */
    public int AddNewRecord(FlwFlowtaske info);

    /**
     * 更新流程任务
     *
     * @param info 流程任务
     * @return 结果
     */
    public int UpdateRecord(FlwFlowtaske info);

    /**
     * 硬删除流程任务
     *
     * @param no 流程任务ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除流程任务
     *
     * @param nos 流程任务IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除流程任务
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除流程任务
     *
     * @param no 流程任务ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除流程任务
     *
     * @param nos 流程任务IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除流程任务
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
