package com.benet.wkflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlowDatasource;

/**
 * 数据源Service接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public interface IFlowDatasourceService 
{
    /**
     * 查询所有数据源列表
     *
     * @param appCode 应用编号
     * @return 数据源集合
     */
    public List<FlowDatasource> getAllRecords(String appCode);

    /**
     * 按分类查询数据源列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 数据源集合
     */
    public List<FlowDatasource> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询数据源列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 数据源集合
     */
    public List<FlowDatasource> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询数据源列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 数据源集合
     */
    public List<FlowDatasource> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询数据源
     *
     * @param appCode 应用编号
     * @param no 数据源ID
     * @return 数据源
     */
    public FlowDatasource getRecordByNo(String appCode,String no);

    /**
     * 查询数据源名称
     *
     * @param appCode 应用编号
     * @param no 数据源ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询数据源计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增数据源
     *
     * @param appCode 应用编号
     * @param info 数据源
     * @return 结果
     */
    public int AddNewRecord(String appCode,FlowDatasource info);

    /**
     * 更新数据源
     *
     * @param appCode 应用编号
     * @param info 数据源
     * @return 结果
     */
    public int UpdateRecord(String appCode,FlowDatasource info);

    /**
     * 硬删除数据源
     *
     * @param appCode 应用编号
     * @param no 数据源ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除数据源
     *
     * @param appCode 应用编号
     * @param nos 数据源IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除数据源
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除数据源
     *
     * @param appCode 应用编号
     * @param no 数据源ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除数据源
     *
     * @param appCode 应用编号
     * @param nos 数据源IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除数据源
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
