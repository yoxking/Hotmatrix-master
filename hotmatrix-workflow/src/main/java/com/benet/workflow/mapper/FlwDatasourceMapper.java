package com.benet.workflow.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.workflow.domain.FlwDatasource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 数据源信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-05-23
 */
@Mapper
public interface FlwDatasourceMapper 
{
    /**
     * 查询所有数据源信息列表
     *
     * @param appCode 应用编号
     * @return 数据源信息集合
     */
    public List<FlwDatasource> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询数据源信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 数据源信息集合
     */
    public List<FlwDatasource> getRecordsByClassNo(@Param("appCode") String appCode, @Param("classNo") String classNo);

    /**
     * 分页查询数据源信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 数据源信息集合
     */
    public List<FlwDatasource> getRecordsByPaging(@Param("appCode") String appCode, @Param("model") PagingModel model);

    /**
     * 查询数据源信息
     *
     * @param appCode 应用编号
     * @param no 数据源信息ID
     * @return 数据源信息
     */
    public FlwDatasource getRecordByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询数据源信息名称
     *
     * @param appCode 应用编号
     * @param no 数据源信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询数据源信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 新增数据源信息
     *
     * @param info 数据源信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") FlwDatasource info);

    /**
     * 更新数据源信息
     *
     * @param info 数据源信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") FlwDatasource info);

    /**
     * 硬删除数据源信息
     *
     * @param appCode 应用编号
     * @param no 数据源信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量硬删除数据源信息
     *
     * @param appCode 应用编号
     * @param nos 数据源信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件硬删除数据源信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 软删除数据源信息
     *
     * @param appCode 应用编号
     * @param no 数据源信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量软删除数据源信息
     *
     * @param appCode 应用编号
     * @param nos 数据源信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件软删除数据源信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

}
