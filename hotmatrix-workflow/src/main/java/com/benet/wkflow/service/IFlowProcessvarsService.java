package com.benet.wkflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlowProcessvars;

/**
 * 流程变量Service接口
 * 
 * @author yoxking
 * @date 2021-01-31
 */
public interface IFlowProcessvarsService 
{
    /**
     * 查询所有流程变量列表
     *
     * @param appCode 应用编号
     * @return 流程变量集合
     */
    public List<FlowProcessvars> getAllRecords(String appCode);

    /**
     * 按分类查询流程变量列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 流程变量集合
     */
    public List<FlowProcessvars> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询流程变量列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 流程变量集合
     */
    public List<FlowProcessvars> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询流程变量列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 流程变量集合
     */
    public List<FlowProcessvars> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询流程变量
     *
     * @param appCode 应用编号
     * @param no 流程变量ID
     * @return 流程变量
     */
    public FlowProcessvars getRecordByNo(String appCode,String no);

    /**
     * 查询流程变量名称
     *
     * @param appCode 应用编号
     * @param no 流程变量ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询流程变量计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增流程变量
     *
     * @param appCode 应用编号
     * @param info 流程变量
     * @return 结果
     */
    public int AddNewRecord(String appCode,FlowProcessvars info);

    /**
     * 更新流程变量
     *
     * @param appCode 应用编号
     * @param info 流程变量
     * @return 结果
     */
    public int UpdateRecord(String appCode,FlowProcessvars info);

    /**
     * 硬删除流程变量
     *
     * @param appCode 应用编号
     * @param no 流程变量ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除流程变量
     *
     * @param appCode 应用编号
     * @param nos 流程变量IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除流程变量
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除流程变量
     *
     * @param appCode 应用编号
     * @param no 流程变量ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除流程变量
     *
     * @param appCode 应用编号
     * @param nos 流程变量IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除流程变量
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
