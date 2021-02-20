package com.benet.collect.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CoctQuestflows;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 答题结果Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Mapper
public interface CoctQuestflowsMapper 
{
    /**
     * 查询所有答题结果列表
     *
     * @param appCode 应用编号
     * @return 答题结果集合
     */
    public List<CoctQuestflows> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询答题结果列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 答题结果集合
     */
    public List<CoctQuestflows> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询答题结果列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 答题结果集合
     */
    public List<CoctQuestflows> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询答题结果
     *
     * @param appCode 应用编号
     * @param no 答题结果ID
     * @return 答题结果
     */
    public CoctQuestflows getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询答题结果名称
     *
     * @param appCode 应用编号
     * @param no 答题结果ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询答题结果计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增答题结果
     *
     * @param info 答题结果
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CoctQuestflows info);

    /**
     * 更新答题结果
     *
     * @param info 答题结果
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CoctQuestflows info);

    /**
     * 硬删除答题结果
     *
     * @param appCode 应用编号
     * @param no 答题结果ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除答题结果
     *
     * @param appCode 应用编号
     * @param nos 答题结果IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除答题结果
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除答题结果
     *
     * @param appCode 应用编号
     * @param no 答题结果ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除答题结果
     *
     * @param appCode 应用编号
     * @param nos 答题结果IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除答题结果
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
