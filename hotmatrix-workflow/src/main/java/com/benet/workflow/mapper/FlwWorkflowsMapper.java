package com.benet.workflow.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.workflow.domain.FlwWorkflows;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 工作流Mapper接口
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Mapper
public interface FlwWorkflowsMapper
{
    /**
     * 查询所有工作流列表
     *
     * @param appCode 应用编号
     * @return 工作流集合
     */
    public List<FlwWorkflows> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询工作流列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 工作流集合
     */
    public List<FlwWorkflows> getRecordsByClassNo(@Param("appCode") String appCode, @Param("classNo") String classNo);

    /**
     * 分页查询工作流列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 工作流集合
     */
    public List<FlwWorkflows> getRecordsByPaging(@Param("appCode") String appCode, @Param("model") PagingModel model);

    /**
     * 查询工作流
     *
     * @param appCode 应用编号
     * @param no 工作流ID
     * @return 工作流
     */
    public FlwWorkflows getRecordByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询工作流名称
     *
     * @param appCode 应用编号
     * @param no 工作流ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询工作流计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 新增工作流
     *
     * @param info 工作流
     * @return 结果
     */
    public int AddNewRecord(@Param("info") FlwWorkflows info);

    /**
     * 更新工作流
     *
     * @param info 工作流
     * @return 结果
     */
    public int UpdateRecord(@Param("info") FlwWorkflows info);

    /**
     * 硬删除工作流
     *
     * @param appCode 应用编号
     * @param no 工作流ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量硬删除工作流
     *
     * @param appCode 应用编号
     * @param nos 工作流IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件硬删除工作流
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 软删除工作流
     *
     * @param appCode 应用编号
     * @param no 工作流ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量软删除工作流
     *
     * @param appCode 应用编号
     * @param nos 工作流IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件软删除工作流
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

}
