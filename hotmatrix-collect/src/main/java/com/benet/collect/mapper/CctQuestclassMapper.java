package com.benet.collect.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CctQuestclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 测题类型Mapper接口
 * 
 * @author yoxking
 * @date 2020-11-10
 */
@Mapper
public interface CctQuestclassMapper 
{
    /**
     * 查询所有测题类型列表
     *
     * @param appCode 应用编号
     * @return 测题类型集合
     */
    public List<CctQuestclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询测题类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 测题类型集合
     */
    public List<CctQuestclass> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询测题类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 测题类型集合
     */
    public List<CctQuestclass> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询测题类型
     *
     * @param appCode 应用编号
     * @param no 测题类型ID
     * @return 测题类型
     */
    public CctQuestclass getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询测题类型名称
     *
     * @param appCode 应用编号
     * @param no 测题类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询测题类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增测题类型
     *
     * @param info 测题类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CctQuestclass info);

    /**
     * 更新测题类型
     *
     * @param info 测题类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CctQuestclass info);

    /**
     * 硬删除测题类型
     *
     * @param appCode 应用编号
     * @param no 测题类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除测题类型
     *
     * @param appCode 应用编号
     * @param nos 测题类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除测题类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除测题类型
     *
     * @param appCode 应用编号
     * @param no 测题类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除测题类型
     *
     * @param appCode 应用编号
     * @param nos 测题类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除测题类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
