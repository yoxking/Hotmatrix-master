package com.benet.wkflow.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlowWorkitemflow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 工作项Mapper接口
 * 
 * @author yoxking
 * @date 2021-01-31
 */
@Mapper
public interface FlowWorkitemflowMapper 
{
    /**
     * 查询所有工作项列表
     *
     * @param appCode 应用编号
     * @return 工作项集合
     */
    public List<FlowWorkitemflow> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询工作项列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 工作项集合
     */
    public List<FlowWorkitemflow> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询工作项列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 工作项集合
     */
    public List<FlowWorkitemflow> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询工作项
     *
     * @param appCode 应用编号
     * @param no 工作项ID
     * @return 工作项
     */
    public FlowWorkitemflow getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询工作项名称
     *
     * @param appCode 应用编号
     * @param no 工作项ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询工作项计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增工作项
     *
     * @param info 工作项
     * @return 结果
     */
    public int AddNewRecord(@Param("info") FlowWorkitemflow info);

    /**
     * 更新工作项
     *
     * @param info 工作项
     * @return 结果
     */
    public int UpdateRecord(@Param("info") FlowWorkitemflow info);

    /**
     * 硬删除工作项
     *
     * @param appCode 应用编号
     * @param no 工作项ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除工作项
     *
     * @param appCode 应用编号
     * @param nos 工作项IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除工作项
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除工作项
     *
     * @param appCode 应用编号
     * @param no 工作项ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除工作项
     *
     * @param appCode 应用编号
     * @param nos 工作项IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除工作项
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
