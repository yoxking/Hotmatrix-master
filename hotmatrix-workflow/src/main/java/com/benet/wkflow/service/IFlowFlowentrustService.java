package com.benet.wkflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlowFlowentrust;

/**
 * 流程委托Service接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public interface IFlowFlowentrustService 
{
    /**
     * 查询所有流程委托列表
     *
     * @param appCode 应用编号
     * @return 流程委托集合
     */
    public List<FlowFlowentrust> getAllRecords(String appCode);

    /**
     * 按分类查询流程委托列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 流程委托集合
     */
    public List<FlowFlowentrust> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询流程委托列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 流程委托集合
     */
    public List<FlowFlowentrust> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询流程委托列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 流程委托集合
     */
    public List<FlowFlowentrust> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询流程委托
     *
     * @param appCode 应用编号
     * @param no 流程委托ID
     * @return 流程委托
     */
    public FlowFlowentrust getRecordByNo(String appCode,String no);

    /**
     * 查询流程委托名称
     *
     * @param appCode 应用编号
     * @param no 流程委托ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询流程委托计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增流程委托
     *
     * @param appCode 应用编号
     * @param info 流程委托
     * @return 结果
     */
    public int AddNewRecord(String appCode,FlowFlowentrust info);

    /**
     * 更新流程委托
     *
     * @param appCode 应用编号
     * @param info 流程委托
     * @return 结果
     */
    public int UpdateRecord(String appCode,FlowFlowentrust info);

    /**
     * 硬删除流程委托
     *
     * @param appCode 应用编号
     * @param no 流程委托ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除流程委托
     *
     * @param appCode 应用编号
     * @param nos 流程委托IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除流程委托
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除流程委托
     *
     * @param appCode 应用编号
     * @param no 流程委托ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除流程委托
     *
     * @param appCode 应用编号
     * @param nos 流程委托IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除流程委托
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
