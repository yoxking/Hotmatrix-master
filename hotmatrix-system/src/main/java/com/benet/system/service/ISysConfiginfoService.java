package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysConfiginfo;

/**
 * 参数配置Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysConfiginfoService 
{
    /**
     * 查询所有参数配置列表
     *
     * @return 参数配置集合
     */
    public List<SysConfiginfo> getAllRecords();

    /**
     * 按分类查询参数配置列表
     *
     * @param classNo 分类编号
     * @return 参数配置集合
     */
    public List<SysConfiginfo> getRecordsByClassNo(String classNo);

    /**
     * 分页查询参数配置列表
     *
     * @param model 分页模型
     * @return 参数配置集合
     */
    public List<SysConfiginfo> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询参数配置列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 参数配置集合
     */
    public List<SysConfiginfo> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询参数配置
     *
     * @param no 参数配置ID
     * @return 参数配置
     */
    public SysConfiginfo getRecordByNo(String no);

    /**
     * 查询参数配置名称
     *
     * @param no 参数配置ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询参数配置计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增参数配置
     *
     * @param info 参数配置
     * @return 结果
     */
    public int AddNewRecord(SysConfiginfo info);

    /**
     * 更新参数配置
     *
     * @param info 参数配置
     * @return 结果
     */
    public int UpdateRecord(SysConfiginfo info);

    /**
     * 硬删除参数配置
     *
     * @param no 参数配置ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除参数配置
     *
     * @param nos 参数配置IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除参数配置
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除参数配置
     *
     * @param no 参数配置ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除参数配置
     *
     * @param nos 参数配置IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除参数配置
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
