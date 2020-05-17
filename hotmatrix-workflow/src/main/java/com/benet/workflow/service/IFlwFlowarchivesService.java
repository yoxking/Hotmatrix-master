package com.benet.workflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.workflow.domain.FlwFlowarchives;

/**
 * 流程归档Service接口
 * 
 * @author yoxking
 * @date 2020-05-17
 */
public interface IFlwFlowarchivesService 
{
    /**
     * 查询所有【请填写功能名称】列表
     *
     * @return 【请填写功能名称】集合
     */
    public List<FlwFlowarchives> getAllRecords();

    /**
     * 按分类查询【请填写功能名称】列表
     *
     * @param classNo 分类编号
     * @return 【请填写功能名称】集合
     */
    public List<FlwFlowarchives> getRecordsByClassNo(String classNo);

    /**
     * 分页查询【请填写功能名称】列表
     *
     * @param model 分页模型
     * @return 【请填写功能名称】集合
     */
    public List<FlwFlowarchives> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询【请填写功能名称】列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 【请填写功能名称】集合
     */
    public List<FlwFlowarchives> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询【请填写功能名称】
     *
     * @param no 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FlwFlowarchives getRecordByNo(String no);

    /**
     * 查询【请填写功能名称】名称
     *
     * @param no 【请填写功能名称】ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询【请填写功能名称】计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增【请填写功能名称】
     *
     * @param info 【请填写功能名称】
     * @return 结果
     */
    public int AddNewRecord(FlwFlowarchives info);

    /**
     * 更新【请填写功能名称】
     *
     * @param info 【请填写功能名称】
     * @return 结果
     */
    public int UpdateRecord(FlwFlowarchives info);

    /**
     * 硬删除【请填写功能名称】
     *
     * @param no 【请填写功能名称】ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除【请填写功能名称】
     *
     * @param nos 【请填写功能名称】IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除【请填写功能名称】
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除【请填写功能名称】
     *
     * @param no 【请填写功能名称】ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除【请填写功能名称】
     *
     * @param nos 【请填写功能名称】IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除【请填写功能名称】
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
