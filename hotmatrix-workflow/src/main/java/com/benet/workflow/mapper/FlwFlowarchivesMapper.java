package com.benet.workflow.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.workflow.domain.FlwFlowarchives;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 流程归档Mapper接口
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Mapper
public interface FlwFlowarchivesMapper 
{
    /**
     * 查询所有【请填写功能名称】列表
     *
     * @param appCode 应用编号
     * @return 【请填写功能名称】集合
     */
    public List<FlwFlowarchives> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询【请填写功能名称】列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 【请填写功能名称】集合
     */
    public List<FlwFlowarchives> getRecordsByClassNo(@Param("appCode") String appCode, @Param("classNo") String classNo);

    /**
     * 分页查询【请填写功能名称】列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 【请填写功能名称】集合
     */
    public List<FlwFlowarchives> getRecordsByPaging(@Param("appCode") String appCode, @Param("model") PagingModel model);

    /**
     * 查询【请填写功能名称】
     *
     * @param appCode 应用编号
     * @param no 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FlwFlowarchives getRecordByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询【请填写功能名称】名称
     *
     * @param appCode 应用编号
     * @param no 【请填写功能名称】ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询【请填写功能名称】计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 新增【请填写功能名称】
     *
     * @param info 【请填写功能名称】
     * @return 结果
     */
    public int AddNewRecord(@Param("info") FlwFlowarchives info);

    /**
     * 更新【请填写功能名称】
     *
     * @param info 【请填写功能名称】
     * @return 结果
     */
    public int UpdateRecord(@Param("info") FlwFlowarchives info);

    /**
     * 硬删除【请填写功能名称】
     *
     * @param appCode 应用编号
     * @param no 【请填写功能名称】ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量硬删除【请填写功能名称】
     *
     * @param appCode 应用编号
     * @param nos 【请填写功能名称】IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件硬删除【请填写功能名称】
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 软删除【请填写功能名称】
     *
     * @param appCode 应用编号
     * @param no 【请填写功能名称】ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量软删除【请填写功能名称】
     *
     * @param appCode 应用编号
     * @param nos 【请填写功能名称】IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件软删除【请填写功能名称】
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

}
