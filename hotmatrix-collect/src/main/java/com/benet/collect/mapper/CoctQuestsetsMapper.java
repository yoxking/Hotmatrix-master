package com.benet.collect.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CoctQuestsets;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 题库信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Mapper
public interface CoctQuestsetsMapper 
{
    /**
     * 查询所有题库信息列表
     *
     * @param appCode 应用编号
     * @return 题库信息集合
     */
    public List<CoctQuestsets> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询题库信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 题库信息集合
     */
    public List<CoctQuestsets> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询题库信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 题库信息集合
     */
    public List<CoctQuestsets> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询题库信息
     *
     * @param appCode 应用编号
     * @param no 题库信息ID
     * @return 题库信息
     */
    public CoctQuestsets getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询题库信息名称
     *
     * @param appCode 应用编号
     * @param no 题库信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询题库信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增题库信息
     *
     * @param info 题库信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CoctQuestsets info);

    /**
     * 更新题库信息
     *
     * @param info 题库信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CoctQuestsets info);

    /**
     * 硬删除题库信息
     *
     * @param appCode 应用编号
     * @param no 题库信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除题库信息
     *
     * @param appCode 应用编号
     * @param nos 题库信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除题库信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除题库信息
     *
     * @param appCode 应用编号
     * @param no 题库信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除题库信息
     *
     * @param appCode 应用编号
     * @param nos 题库信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除题库信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
