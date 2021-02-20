package com.benet.wkflow.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlowDatasource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 数据源Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Mapper
public interface FlowDatasourceMapper 
{
    /**
     * 查询所有数据源列表
     *
     * @param appCode 应用编号
     * @return 数据源集合
     */
    public List<FlowDatasource> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询数据源列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 数据源集合
     */
    public List<FlowDatasource> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询数据源列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 数据源集合
     */
    public List<FlowDatasource> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询数据源
     *
     * @param appCode 应用编号
     * @param no 数据源ID
     * @return 数据源
     */
    public FlowDatasource getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询数据源名称
     *
     * @param appCode 应用编号
     * @param no 数据源ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询数据源计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增数据源
     *
     * @param info 数据源
     * @return 结果
     */
    public int AddNewRecord(@Param("info") FlowDatasource info);

    /**
     * 更新数据源
     *
     * @param info 数据源
     * @return 结果
     */
    public int UpdateRecord(@Param("info") FlowDatasource info);

    /**
     * 硬删除数据源
     *
     * @param appCode 应用编号
     * @param no 数据源ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除数据源
     *
     * @param appCode 应用编号
     * @param nos 数据源IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除数据源
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除数据源
     *
     * @param appCode 应用编号
     * @param no 数据源ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除数据源
     *
     * @param appCode 应用编号
     * @param nos 数据源IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除数据源
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
