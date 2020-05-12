package com.benet.job.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.job.domain.SysTaskinfo;

/**
 * 定时任务调度Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysTaskinfoService 
{
    /**
     * 查询所有定时任务调度列表
     *
     * @return 定时任务调度集合
     */
    public List<SysTaskinfo> getAllRecords();

    /**
     * 按分类查询定时任务调度列表
     *
     * @param classNo 分类编号
     * @return 定时任务调度集合
     */
    public List<SysTaskinfo> getRecordsByClassNo(String classNo);

    /**
     * 分页查询定时任务调度列表
     *
     * @param model 分页模型
     * @return 定时任务调度集合
     */
    public List<SysTaskinfo> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询定时任务调度列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 定时任务调度集合
     */
    public List<SysTaskinfo> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询定时任务调度
     *
     * @param no 定时任务调度ID
     * @return 定时任务调度
     */
    public SysTaskinfo getRecordByNo(String no);

    /**
     * 查询定时任务调度名称
     *
     * @param no 定时任务调度ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询定时任务调度计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增定时任务调度
     *
     * @param info 定时任务调度
     * @return 结果
     */
    public int AddNewRecord(SysTaskinfo info);

    /**
     * 更新定时任务调度
     *
     * @param info 定时任务调度
     * @return 结果
     */
    public int UpdateRecord(SysTaskinfo info);

    /**
     * 硬删除定时任务调度
     *
     * @param no 定时任务调度ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除定时任务调度
     *
     * @param nos 定时任务调度IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除定时任务调度
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除定时任务调度
     *
     * @param no 定时任务调度ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除定时任务调度
     *
     * @param nos 定时任务调度IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除定时任务调度
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);


    /**
     * 立即运行任务
     *
     * @param taskNo 调度信息
     * @return 结果
     */
    public void start(String taskNo);

    /**
     * 暂停任务
     *
     * @param taskNo 调度信息
     * @return 结果
     */
    public int pause(String taskNo) ;

    /**
     * 恢复任务
     *
     * @param taskNo 调度信息
     * @return 结果
     */
    public int resume(String taskNo);
    /**
     * 任务调度状态修改
     *
     * @param taskNo 调度信息编号
     * @param state 状态
     * @return 结果
     */
    public int changeState(String taskNo,String state);


    /**
     * 校验cron表达式是否有效
     *
     * @param expression 表达式
     * @return 结果
     */
    public boolean checkExpression(String expression);
}
