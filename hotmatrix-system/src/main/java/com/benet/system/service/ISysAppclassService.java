package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysAppclass;

/**
 * 应用类型Service接口
 * 
 * @author yoxking
 * @date 2020-03-29
 */
public interface ISysAppclassService 
{
    /**
     * 查询所有应用类型列表
     *
     * @return 应用类型集合
     */
    public List<SysAppclass> getAllRecords();

    /**
     * 按分类查询应用类型列表
     *
     * @param classNo 分类编号
     * @return 应用类型集合
     */
    public List<SysAppclass> getRecordsByClassNo(String classNo);

    /**
     * 分页查询应用类型列表
     *
     * @param model 分页模型
     * @return 应用类型集合
     */
    public List<SysAppclass> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询应用类型列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 应用类型集合
     */
    public List<SysAppclass> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询应用类型
     *
     * @param no 应用类型ID
     * @return 应用类型
     */
    public SysAppclass getRecordByNo(String no);

    /**
     * 查询应用类型名称
     *
     * @param no 应用类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询应用类型计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增应用类型
     *
     * @param info 应用类型
     * @return 结果
     */
    public int AddNewRecord(SysAppclass info);

    /**
     * 更新应用类型
     *
     * @param info 应用类型
     * @return 结果
     */
    public int UpdateRecord(SysAppclass info);

    /**
     * 硬删除应用类型
     *
     * @param no 应用类型ID
     * @return 结果
     */
    public int HardDeleteRecord(String no);

    /**
     * 批量硬删除应用类型
     *
     * @param nos 应用类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除应用类型
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除应用类型
     *
     * @param no 应用类型ID
     * @return 结果
     */
    public int SoftDeleteRecord(String no);

    /**
     * 批量软删除应用类型
     *
     * @param nos 应用类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除应用类型
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
