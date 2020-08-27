package com.benet.wkflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlwTableform;

/**
 * 单设计Service接口
 * 
 * @author yoxking
 * @date 2020-05-23
 */
public interface IFlwTableformService 
{
    /**
     * 查询所有单设计列表
     *
     * @param appCode 应用编号
     * @return 单设计集合
     */
    public List<FlwTableform> getAllRecords(String appCode);

    /**
     * 按分类查询单设计列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 单设计集合
     */
    public List<FlwTableform> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询单设计列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 单设计集合
     */
    public List<FlwTableform> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询单设计列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 单设计集合
     */
    public List<FlwTableform> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询单设计
     *
     * @param appCode 应用编号
     * @param no 单设计ID
     * @return 单设计
     */
    public FlwTableform getRecordByNo(String appCode,String no);

    /**
     * 查询单设计名称
     *
     * @param appCode 应用编号
     * @param no 单设计ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询单设计计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增单设计
     *
     * @param appCode 应用编号
     * @param info 单设计
     * @return 结果
     */
    public int AddNewRecord(String appCode,FlwTableform info);

    /**
     * 更新单设计
     *
     * @param appCode 应用编号
     * @param info 单设计
     * @return 结果
     */
    public int UpdateRecord(String appCode,FlwTableform info);

    /**
     * 硬删除单设计
     *
     * @param appCode 应用编号
     * @param no 单设计ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除单设计
     *
     * @param appCode 应用编号
     * @param nos 单设计IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除单设计
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除单设计
     *
     * @param appCode 应用编号
     * @param no 单设计ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除单设计
     *
     * @param appCode 应用编号
     * @param nos 单设计IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除单设计
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
