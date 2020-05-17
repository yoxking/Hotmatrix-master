package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysRenterinfo;

/**
 * 租户信息Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysRenterinfoService 
{
    /**
     * 查询所有租户信息列表
     *
     * @return 租户信息集合
     */
    public List<SysRenterinfo> getAllRecords();

    /**
     * 按分类查询租户信息列表
     *
     * @param classNo 分类编号
     * @return 租户信息集合
     */
    public List<SysRenterinfo> getRecordsByClassNo(String classNo);

    /**
     * 分页查询租户信息列表
     *
     * @param model 分页模型
     * @return 租户信息集合
     */
    public List<SysRenterinfo> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询租户信息列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 租户信息集合
     */
    public List<SysRenterinfo> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询租户信息
     *
     * @param no 租户信息ID
     * @return 租户信息
     */
    public SysRenterinfo getRecordByNo(String no);

    /**
     * 查询租户信息名称
     *
     * @param no 租户信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询租户信息计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增租户信息
     *
     * @param info 租户信息
     * @return 结果
     */
    public int AddNewRecord(SysRenterinfo info);

    /**
     * 更新租户信息
     *
     * @param info 租户信息
     * @return 结果
     */
    public int UpdateRecord(SysRenterinfo info);

    /**
     * 硬删除租户信息
     *
     * @param no 租户信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除租户信息
     *
     * @param nos 租户信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除租户信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除租户信息
     *
     * @param no 租户信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除租户信息
     *
     * @param nos 租户信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除租户信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
