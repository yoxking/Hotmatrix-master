package com.benet.collect.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CctExamsinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 测评信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-11-10
 */
@Mapper
public interface CctExamsinfoMapper 
{
    /**
     * 查询所有测评信息列表
     *
     * @param appCode 应用编号
     * @return 测评信息集合
     */
    public List<CctExamsinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询测评信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 测评信息集合
     */
    public List<CctExamsinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询测评信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 测评信息集合
     */
    public List<CctExamsinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询测评信息
     *
     * @param appCode 应用编号
     * @param no 测评信息ID
     * @return 测评信息
     */
    public CctExamsinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询测评信息名称
     *
     * @param appCode 应用编号
     * @param no 测评信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询测评信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增测评信息
     *
     * @param info 测评信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CctExamsinfo info);

    /**
     * 更新测评信息
     *
     * @param info 测评信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CctExamsinfo info);

    /**
     * 硬删除测评信息
     *
     * @param appCode 应用编号
     * @param no 测评信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除测评信息
     *
     * @param appCode 应用编号
     * @param nos 测评信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除测评信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除测评信息
     *
     * @param appCode 应用编号
     * @param no 测评信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除测评信息
     *
     * @param appCode 应用编号
     * @param nos 测评信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除测评信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
