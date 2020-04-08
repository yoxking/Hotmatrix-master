package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysPostinfo;

/**
 * 岗位信息Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysPostinfoService 
{
    /**
     * 查询所有岗位信息列表
     *
     * @return 岗位信息集合
     */
    public List<SysPostinfo> getAllRecords();

    /**
     * 按分类查询岗位信息列表
     *
     * @param classNo 分类编号
     * @return 岗位信息集合
     */
    public List<SysPostinfo> getRecordsByClassNo(String classNo);

    /**
     * 分页查询岗位信息列表
     *
     * @param model 分页模型
     * @return 岗位信息集合
     */
    public List<SysPostinfo> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询岗位信息列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 岗位信息集合
     */
    public List<SysPostinfo> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询岗位信息
     *
     * @param no 岗位信息ID
     * @return 岗位信息
     */
    public SysPostinfo getRecordByNo(String no);

    /**
     * 查询岗位信息名称
     *
     * @param no 岗位信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询岗位信息计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增岗位信息
     *
     * @param info 岗位信息
     * @return 结果
     */
    public int AddNewRecord(SysPostinfo info);

    /**
     * 更新岗位信息
     *
     * @param info 岗位信息
     * @return 结果
     */
    public int UpdateRecord(SysPostinfo info);

    /**
     * 硬删除岗位信息
     *
     * @param no 岗位信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除岗位信息
     *
     * @param nos 岗位信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除岗位信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除岗位信息
     *
     * @param no 岗位信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除岗位信息
     *
     * @param nos 岗位信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除岗位信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
