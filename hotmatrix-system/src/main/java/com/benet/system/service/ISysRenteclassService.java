package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysRenteclass;

/**
 * 租户类型Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysRenteclassService 
{
    /**
     * 查询所有租户类型列表
     *
     * @return 租户类型集合
     */
    public List<SysRenteclass> getAllRecords();

    /**
     * 按分类查询租户类型列表
     *
     * @param classNo 分类编号
     * @return 租户类型集合
     */
    public List<SysRenteclass> getRecordsByClassNo(String classNo);

    /**
     * 分页查询租户类型列表
     *
     * @param model 分页模型
     * @return 租户类型集合
     */
    public List<SysRenteclass> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询租户类型列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 租户类型集合
     */
    public List<SysRenteclass> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询租户类型
     *
     * @param no 租户类型ID
     * @return 租户类型
     */
    public SysRenteclass getRecordByNo(String no);

    /**
     * 查询租户类型名称
     *
     * @param no 租户类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询租户类型计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增租户类型
     *
     * @param info 租户类型
     * @return 结果
     */
    public int AddNewRecord(SysRenteclass info);

    /**
     * 更新租户类型
     *
     * @param info 租户类型
     * @return 结果
     */
    public int UpdateRecord(SysRenteclass info);

    /**
     * 硬删除租户类型
     *
     * @param no 租户类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除租户类型
     *
     * @param nos 租户类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除租户类型
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除租户类型
     *
     * @param no 租户类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除租户类型
     *
     * @param nos 租户类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除租户类型
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
