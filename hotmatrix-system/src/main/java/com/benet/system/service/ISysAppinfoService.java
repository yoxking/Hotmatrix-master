package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysAppinfo;

/**
 * 应用信息Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysAppinfoService 
{
    /**
     * 查询所有应用信息列表
     *
     * @return 应用信息集合
     */
    public List<SysAppinfo> getAllRecords();

    /**
     * 按分类查询应用信息列表
     *
     * @param classNo 分类编号
     * @return 应用信息集合
     */
    public List<SysAppinfo> getRecordsByClassNo(String classNo);

    /**
     * 分页查询应用信息列表
     *
     * @param model 分页模型
     * @return 应用信息集合
     */
    public List<SysAppinfo> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询应用信息列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 应用信息集合
     */
    public List<SysAppinfo> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询应用信息
     *
     * @param no 应用信息ID
     * @return 应用信息
     */
    public SysAppinfo getRecordByNo(String no);

    /**
     * 查询应用信息名称
     *
     * @param no 应用信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询应用信息计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增应用信息
     *
     * @param info 应用信息
     * @return 结果
     */
    public int AddNewRecord(SysAppinfo info);

    /**
     * 更新应用信息
     *
     * @param info 应用信息
     * @return 结果
     */
    public int UpdateRecord(SysAppinfo info);

    /**
     * 硬删除应用信息
     *
     * @param no 应用信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除应用信息
     *
     * @param nos 应用信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除应用信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除应用信息
     *
     * @param no 应用信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除应用信息
     *
     * @param nos 应用信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除应用信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
