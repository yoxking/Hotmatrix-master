package com.benet.collect.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CctSalonflows;

/**
 * 活动过程Service接口
 * 
 * @author yoxking
 * @date 2020-10-14
 */
public interface ICctSalonflowsService 
{
    /**
     * 查询所有活动过程列表
     *
     * @param appCode 应用编号
     * @return 活动过程集合
     */
    public List<CctSalonflows> getAllRecords(String appCode);

    /**
     * 按分类查询活动过程列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 活动过程集合
     */
    public List<CctSalonflows> getRecordsByClassNo(String appCode, String classNo);

    /**
     * 分页查询活动过程列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 活动过程集合
     */
    public List<CctSalonflows> getRecordsByPaging(String appCode, PagingModel model);

    /**
     * 分页查询活动过程列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 活动过程集合
     */
    public List<CctSalonflows> getRecordsByPaging(String appCode, int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询活动过程
     *
     * @param appCode 应用编号
     * @param no 活动过程ID
     * @return 活动过程
     */
    public CctSalonflows getRecordByNo(String appCode, String no);

    /**
     * 查询活动过程名称
     *
     * @param appCode 应用编号
     * @param no 活动过程ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode, String no);

    /**
     * 查询活动过程计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode, String condition);

    /**
     * 新增活动过程
     *
     * @param appCode 应用编号
     * @param info 活动过程
     * @return 结果
     */
    public int AddNewRecord(String appCode, CctSalonflows info);

    /**
     * 更新活动过程
     *
     * @param appCode 应用编号
     * @param info 活动过程
     * @return 结果
     */
    public int UpdateRecord(String appCode, CctSalonflows info);

    /**
     * 硬删除活动过程
     *
     * @param appCode 应用编号
     * @param no 活动过程ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode, String no);

    /**
     * 批量硬删除活动过程
     *
     * @param appCode 应用编号
     * @param nos 活动过程IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode, String[] nos);

    /**
     * 按条件硬删除活动过程
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode, String condition);

    /**
     * 软删除活动过程
     *
     * @param appCode 应用编号
     * @param no 活动过程ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode, String no);

    /**
     * 批量软删除活动过程
     *
     * @param appCode 应用编号
     * @param nos 活动过程IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode, String[] nos);

    /**
     * 按条件软删除活动过程
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode, String condition);
}
