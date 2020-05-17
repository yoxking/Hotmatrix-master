package com.benet.workflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.workflow.domain.FlwTableform;

/**
 * 单设计Service接口
 * 
 * @author yoxking
 * @date 2020-05-17
 */
public interface IFlwTableformService 
{
    /**
     * 查询所有单设计列表
     *
     * @return 单设计集合
     */
    public List<FlwTableform> getAllRecords();

    /**
     * 按分类查询单设计列表
     *
     * @param classNo 分类编号
     * @return 单设计集合
     */
    public List<FlwTableform> getRecordsByClassNo(String classNo);

    /**
     * 分页查询单设计列表
     *
     * @param model 分页模型
     * @return 单设计集合
     */
    public List<FlwTableform> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询单设计列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 单设计集合
     */
    public List<FlwTableform> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询单设计
     *
     * @param no 单设计ID
     * @return 单设计
     */
    public FlwTableform getRecordByNo(String no);

    /**
     * 查询单设计名称
     *
     * @param no 单设计ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询单设计计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增单设计
     *
     * @param info 单设计
     * @return 结果
     */
    public int AddNewRecord(FlwTableform info);

    /**
     * 更新单设计
     *
     * @param info 单设计
     * @return 结果
     */
    public int UpdateRecord(FlwTableform info);

    /**
     * 硬删除单设计
     *
     * @param no 单设计ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除单设计
     *
     * @param nos 单设计IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除单设计
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除单设计
     *
     * @param no 单设计ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除单设计
     *
     * @param nos 单设计IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除单设计
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
