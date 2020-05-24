package com.benet.workflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.workflow.domain.FlwTabcolumn;

/**
 * 单字段Service接口
 * 
 * @author yoxking
 * @date 2020-05-23
 */
public interface IFlwTabcolumnService 
{
    /**
     * 查询所有单字段列表
     *
     * @return 单字段集合
     */
    public List<FlwTabcolumn> getAllRecords();

    /**
     * 按分类查询单字段列表
     *
     * @param classNo 分类编号
     * @return 单字段集合
     */
    public List<FlwTabcolumn> getRecordsByClassNo(String classNo);

    /**
     * 分页查询单字段列表
     *
     * @param model 分页模型
     * @return 单字段集合
     */
    public List<FlwTabcolumn> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询单字段列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 单字段集合
     */
    public List<FlwTabcolumn> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询单字段
     *
     * @param no 单字段ID
     * @return 单字段
     */
    public FlwTabcolumn getRecordByNo(String no);

    /**
     * 查询单字段名称
     *
     * @param no 单字段ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询单字段计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增单字段
     *
     * @param info 单字段
     * @return 结果
     */
    public int AddNewRecord(FlwTabcolumn info);

    /**
     * 更新单字段
     *
     * @param info 单字段
     * @return 结果
     */
    public int UpdateRecord(FlwTabcolumn info);

    /**
     * 硬删除单字段
     *
     * @param no 单字段ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除单字段
     *
     * @param nos 单字段IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除单字段
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除单字段
     *
     * @param no 单字段ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除单字段
     *
     * @param nos 单字段IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除单字段
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
