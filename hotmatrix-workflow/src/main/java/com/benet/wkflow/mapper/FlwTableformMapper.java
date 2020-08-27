package com.benet.wkflow.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlwTableform;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 单设计Mapper接口
 * 
 * @author yoxking
 * @date 2020-05-23
 */
@Mapper
public interface FlwTableformMapper 
{
    /**
     * 查询所有单设计列表
     *
     * @param appCode 应用编号
     * @return 单设计集合
     */
    public List<FlwTableform> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询单设计列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 单设计集合
     */
    public List<FlwTableform> getRecordsByClassNo(@Param("appCode") String appCode, @Param("classNo") String classNo);

    /**
     * 分页查询单设计列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 单设计集合
     */
    public List<FlwTableform> getRecordsByPaging(@Param("appCode") String appCode, @Param("model") PagingModel model);

    /**
     * 查询单设计
     *
     * @param appCode 应用编号
     * @param no 单设计ID
     * @return 单设计
     */
    public FlwTableform getRecordByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询单设计名称
     *
     * @param appCode 应用编号
     * @param no 单设计ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询单设计计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 新增单设计
     *
     * @param info 单设计
     * @return 结果
     */
    public int AddNewRecord(@Param("info") FlwTableform info);

    /**
     * 更新单设计
     *
     * @param info 单设计
     * @return 结果
     */
    public int UpdateRecord(@Param("info") FlwTableform info);

    /**
     * 硬删除单设计
     *
     * @param appCode 应用编号
     * @param no 单设计ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量硬删除单设计
     *
     * @param appCode 应用编号
     * @param nos 单设计IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件硬删除单设计
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 软删除单设计
     *
     * @param appCode 应用编号
     * @param no 单设计ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量软删除单设计
     *
     * @param appCode 应用编号
     * @param nos 单设计IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件软删除单设计
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

}
