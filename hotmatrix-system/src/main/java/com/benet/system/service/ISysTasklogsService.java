package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysTasklogs;

/**
 * 定时任务调度日志Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysTasklogsService 
{
    /**
     * 查询所有定时任务调度日志列表
     *
     * @return 定时任务调度日志集合
     */
    public List<SysTasklogs> getAllRecords();

    /**
     * 按分类查询定时任务调度日志列表
     *
     * @param classNo 分类编号
     * @return 定时任务调度日志集合
     */
    public List<SysTasklogs> getRecordsByClassNo(String classNo);

    /**
     * 分页查询定时任务调度日志列表
     *
     * @param model 分页模型
     * @return 定时任务调度日志集合
     */
    public List<SysTasklogs> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询定时任务调度日志列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 定时任务调度日志集合
     */
    public List<SysTasklogs> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询定时任务调度日志
     *
     * @param no 定时任务调度日志ID
     * @return 定时任务调度日志
     */
    public SysTasklogs getRecordByNo(String no);

    /**
     * 查询定时任务调度日志名称
     *
     * @param no 定时任务调度日志ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询定时任务调度日志计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增定时任务调度日志
     *
     * @param info 定时任务调度日志
     * @return 结果
     */
    public int AddNewRecord(SysTasklogs info);

    /**
     * 更新定时任务调度日志
     *
     * @param info 定时任务调度日志
     * @return 结果
     */
    public int UpdateRecord(SysTasklogs info);

    /**
     * 硬删除定时任务调度日志
     *
     * @param no 定时任务调度日志ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除定时任务调度日志
     *
     * @param nos 定时任务调度日志IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除定时任务调度日志
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除定时任务调度日志
     *
     * @param no 定时任务调度日志ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除定时任务调度日志
     *
     * @param nos 定时任务调度日志IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除定时任务调度日志
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
