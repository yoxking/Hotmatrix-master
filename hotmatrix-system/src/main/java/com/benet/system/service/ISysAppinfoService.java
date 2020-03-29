package com.benet.system.service;

import java.util.List;

import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysAppinfo;

/**
 * 应用信息Service接口
 * 
 * @author yoxking
 * @date 2020-03-28
 */
public interface ISysAppinfoService 
{
    /**
     * 查询所有信息列表
     *
     * @return 分支信息列表
     */
    public List<SysAppinfo> getAllRecords();

    /**
     * 按分类查询分支信息列表
     *
     * @param classNo 分类编号
     * @return 分支信息列表
     */
    public List<SysAppinfo> getRecordsByClassNo(String classNo);

    /**
     * 分页查询分支信息列表
     *
     * @param model 分页模型
     * @return 分支信息列表
     */
    public List<SysAppinfo> getRecordsByPaging(PagingModel model);

    /**
     * 查询分支信息
     *
     * @param no 分支信息ID
     * @return 分支信息
     */
    public SysAppinfo getRecordByNo(String no);

    /**
     * 查询分支信息名称
     *
     * @param no 分支信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询分支信息计数
     *
     * @param condition 查询条件
     * @return 计数
     */
    public int getCountByCondition(String condition);

    /**
     * 新增分支信息
     *
     * @param info 分支信息
     * @return 结果
     */
    public int AddNewRecord(SysAppinfo info);

    /**
     * 更新分支信息
     *
     * @param info 分支信息
     * @return 结果
     */
    public int UpdateRecord(SysAppinfo info);

    /**
     * 硬删除分支信息
     *
     * @param no 分支信息ID
     * @return 结果
     */
    public int HardDeleteRecord(String no);

    /**
     * 硬删除分支信息
     *
     * @param nos 分支信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 硬删除分支信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除分支信息
     *
     * @param no 分支信息ID
     * @return 结果
     */
    public int SoftDeleteRecord(String no);

    /**
     * 软删除分支信息
     *
     * @param nos 分支信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 软删除分支信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
