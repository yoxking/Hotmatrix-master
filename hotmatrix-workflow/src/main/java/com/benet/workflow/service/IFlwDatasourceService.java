package com.benet.workflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.workflow.domain.FlwDatasource;

/**
 * 数据源信息Service接口
 * 
 * @author yoxking
 * @date 2020-05-23
 */
public interface IFlwDatasourceService 
{
    /**
     * 查询所有数据源信息列表
     *
     * @return 数据源信息集合
     */
    public List<FlwDatasource> getAllRecords();

    /**
     * 按分类查询数据源信息列表
     *
     * @param classNo 分类编号
     * @return 数据源信息集合
     */
    public List<FlwDatasource> getRecordsByClassNo(String classNo);

    /**
     * 分页查询数据源信息列表
     *
     * @param model 分页模型
     * @return 数据源信息集合
     */
    public List<FlwDatasource> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询数据源信息列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 数据源信息集合
     */
    public List<FlwDatasource> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询数据源信息
     *
     * @param no 数据源信息ID
     * @return 数据源信息
     */
    public FlwDatasource getRecordByNo(String no);

    /**
     * 查询数据源信息名称
     *
     * @param no 数据源信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询数据源信息计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增数据源信息
     *
     * @param info 数据源信息
     * @return 结果
     */
    public int AddNewRecord(FlwDatasource info);

    /**
     * 更新数据源信息
     *
     * @param info 数据源信息
     * @return 结果
     */
    public int UpdateRecord(FlwDatasource info);

    /**
     * 硬删除数据源信息
     *
     * @param no 数据源信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除数据源信息
     *
     * @param nos 数据源信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除数据源信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除数据源信息
     *
     * @param no 数据源信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除数据源信息
     *
     * @param nos 数据源信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除数据源信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
