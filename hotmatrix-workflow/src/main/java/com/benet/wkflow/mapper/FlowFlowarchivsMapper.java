package com.benet.wkflow.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlowFlowarchivs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 归档信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Mapper
public interface FlowFlowarchivsMapper 
{
    /**
     * 查询所有归档信息列表
     *
     * @param appCode 应用编号
     * @return 归档信息集合
     */
    public List<FlowFlowarchivs> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询归档信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 归档信息集合
     */
    public List<FlowFlowarchivs> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询归档信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 归档信息集合
     */
    public List<FlowFlowarchivs> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询归档信息
     *
     * @param appCode 应用编号
     * @param no 归档信息ID
     * @return 归档信息
     */
    public FlowFlowarchivs getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询归档信息名称
     *
     * @param appCode 应用编号
     * @param no 归档信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询归档信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增归档信息
     *
     * @param info 归档信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") FlowFlowarchivs info);

    /**
     * 更新归档信息
     *
     * @param info 归档信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") FlowFlowarchivs info);

    /**
     * 硬删除归档信息
     *
     * @param appCode 应用编号
     * @param no 归档信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除归档信息
     *
     * @param appCode 应用编号
     * @param nos 归档信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除归档信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除归档信息
     *
     * @param appCode 应用编号
     * @param no 归档信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除归档信息
     *
     * @param appCode 应用编号
     * @param nos 归档信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除归档信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
