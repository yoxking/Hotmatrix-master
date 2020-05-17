package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysConteeclass;

/**
 * 内容类型Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysConteeclassService
{
    /**
     * 查询所有内容类型列表
     *
     * @return 内容类型集合
     */
    public List<SysConteeclass> getAllRecords();

    /**
     * 按分类查询内容类型列表
     *
     * @param classNo 分类编号
     * @return 内容类型集合
     */
    public List<SysConteeclass> getRecordsByClassNo(String classNo);

    /**
     * 分页查询内容类型列表
     *
     * @param model 分页模型
     * @return 内容类型集合
     */
    public List<SysConteeclass> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询内容类型列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 内容类型集合
     */
    public List<SysConteeclass> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询内容类型
     *
     * @param no 内容类型ID
     * @return 内容类型
     */
    public SysConteeclass getRecordByNo(String no);

    /**
     * 查询内容类型名称
     *
     * @param no 内容类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询内容类型计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增内容类型
     *
     * @param info 内容类型
     * @return 结果
     */
    public int AddNewRecord(SysConteeclass info);

    /**
     * 更新内容类型
     *
     * @param info 内容类型
     * @return 结果
     */
    public int UpdateRecord(SysConteeclass info);

    /**
     * 硬删除内容类型
     *
     * @param no 内容类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除内容类型
     *
     * @param nos 内容类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除内容类型
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除内容类型
     *
     * @param no 内容类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除内容类型
     *
     * @param nos 内容类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除内容类型
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
