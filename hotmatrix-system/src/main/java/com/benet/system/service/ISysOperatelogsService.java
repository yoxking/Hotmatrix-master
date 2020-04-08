package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysOperatelogs;

/**
 * 操作日志记录Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysOperatelogsService 
{
    /**
     * 查询所有操作日志记录列表
     *
     * @return 操作日志记录集合
     */
    public List<SysOperatelogs> getAllRecords();

    /**
     * 按分类查询操作日志记录列表
     *
     * @param classNo 分类编号
     * @return 操作日志记录集合
     */
    public List<SysOperatelogs> getRecordsByClassNo(String classNo);

    /**
     * 分页查询操作日志记录列表
     *
     * @param model 分页模型
     * @return 操作日志记录集合
     */
    public List<SysOperatelogs> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询操作日志记录列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 操作日志记录集合
     */
    public List<SysOperatelogs> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询操作日志记录
     *
     * @param no 操作日志记录ID
     * @return 操作日志记录
     */
    public SysOperatelogs getRecordByNo(String no);

    /**
     * 查询操作日志记录名称
     *
     * @param no 操作日志记录ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询操作日志记录计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增操作日志记录
     *
     * @param info 操作日志记录
     * @return 结果
     */
    public int AddNewRecord(SysOperatelogs info);

    /**
     * 更新操作日志记录
     *
     * @param info 操作日志记录
     * @return 结果
     */
    public int UpdateRecord(SysOperatelogs info);

    /**
     * 硬删除操作日志记录
     *
     * @param no 操作日志记录ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除操作日志记录
     *
     * @param nos 操作日志记录IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除操作日志记录
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除操作日志记录
     *
     * @param no 操作日志记录ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除操作日志记录
     *
     * @param nos 操作日志记录IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除操作日志记录
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
