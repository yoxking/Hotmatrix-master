package com.benet.wkflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlwWorkgroup;

/**
 * 工作组Service接口
 * 
 * @author yoxking
 * @date 2020-05-17
 */
public interface IFlwWorkgroupService 
{
    /**
     * 查询所有工作组列表
     *
     * @param appCode 应用编号
     * @return 工作组集合
     */
    public List<FlwWorkgroup> getAllRecords(String appCode);

    /**
     * 按分类查询工作组列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 工作组集合
     */
    public List<FlwWorkgroup> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询工作组列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 工作组集合
     */
    public List<FlwWorkgroup> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询工作组列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 工作组集合
     */
    public List<FlwWorkgroup> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询工作组
     *
     * @param appCode 应用编号
     * @param no 工作组ID
     * @return 工作组
     */
    public FlwWorkgroup getRecordByNo(String appCode,String no);

    /**
     * 查询工作组名称
     *
     * @param appCode 应用编号
     * @param no 工作组ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询工作组计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增工作组
     *
     * @param appCode 应用编号
     * @param info 工作组
     * @return 结果
     */
    public int AddNewRecord(String appCode,FlwWorkgroup info);

    /**
     * 更新工作组
     *
     * @param appCode 应用编号
     * @param info 工作组
     * @return 结果
     */
    public int UpdateRecord(String appCode,FlwWorkgroup info);

    /**
     * 硬删除工作组
     *
     * @param appCode 应用编号
     * @param no 工作组ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除工作组
     *
     * @param appCode 应用编号
     * @param nos 工作组IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除工作组
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除工作组
     *
     * @param appCode 应用编号
     * @param no 工作组ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除工作组
     *
     * @param appCode 应用编号
     * @param nos 工作组IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除工作组
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
