package com.benet.collect.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CctQuestopts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 测题选项Mapper接口
 * 
 * @author yoxking
 * @date 2020-11-10
 */
@Mapper
public interface CctQuestoptsMapper 
{
    /**
     * 查询所有测题选项列表
     *
     * @param appCode 应用编号
     * @return 测题选项集合
     */
    public List<CctQuestopts> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询测题选项列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 测题选项集合
     */
    public List<CctQuestopts> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询测题选项列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 测题选项集合
     */
    public List<CctQuestopts> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询测题选项
     *
     * @param appCode 应用编号
     * @param no 测题选项ID
     * @return 测题选项
     */
    public CctQuestopts getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询测题选项名称
     *
     * @param appCode 应用编号
     * @param no 测题选项ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询测题选项计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增测题选项
     *
     * @param info 测题选项
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CctQuestopts info);

    /**
     * 更新测题选项
     *
     * @param info 测题选项
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CctQuestopts info);

    /**
     * 硬删除测题选项
     *
     * @param appCode 应用编号
     * @param no 测题选项ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除测题选项
     *
     * @param appCode 应用编号
     * @param nos 测题选项IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除测题选项
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除测题选项
     *
     * @param appCode 应用编号
     * @param no 测题选项ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除测题选项
     *
     * @param appCode 应用编号
     * @param nos 测题选项IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除测题选项
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
