package com.benet.wkflow.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlowWorktaskflow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 任务实例Mapper接口
 * 
 * @author yoxking
 * @date 2021-01-31
 */
@Mapper
public interface FlowWorktaskflowMapper 
{
    /**
     * 查询所有任务实例列表
     *
     * @param appCode 应用编号
     * @return 任务实例集合
     */
    public List<FlowWorktaskflow> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询任务实例列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 任务实例集合
     */
    public List<FlowWorktaskflow> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询任务实例列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 任务实例集合
     */
    public List<FlowWorktaskflow> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询任务实例
     *
     * @param appCode 应用编号
     * @param no 任务实例ID
     * @return 任务实例
     */
    public FlowWorktaskflow getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询任务实例名称
     *
     * @param appCode 应用编号
     * @param no 任务实例ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询任务实例计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增任务实例
     *
     * @param info 任务实例
     * @return 结果
     */
    public int AddNewRecord(@Param("info") FlowWorktaskflow info);

    /**
     * 更新任务实例
     *
     * @param info 任务实例
     * @return 结果
     */
    public int UpdateRecord(@Param("info") FlowWorktaskflow info);

    /**
     * 硬删除任务实例
     *
     * @param appCode 应用编号
     * @param no 任务实例ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除任务实例
     *
     * @param appCode 应用编号
     * @param nos 任务实例IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除任务实例
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除任务实例
     *
     * @param appCode 应用编号
     * @param no 任务实例ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除任务实例
     *
     * @param appCode 应用编号
     * @param nos 任务实例IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除任务实例
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
