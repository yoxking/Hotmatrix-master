package com.benet.collect.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CctSalonclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 活动类型Mapper接口
 * 
 * @author yoxking
 * @date 2020-10-14
 */
@Mapper
public interface CctSalonclassMapper 
{
    /**
     * 查询所有活动类型列表
     *
     * @param appCode 应用编号
     * @return 活动类型集合
     */
    public List<CctSalonclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询活动类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 活动类型集合
     */
    public List<CctSalonclass> getRecordsByClassNo(@Param("appCode") String appCode, @Param("classNo") String classNo);

    /**
     * 分页查询活动类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 活动类型集合
     */
    public List<CctSalonclass> getRecordsByPaging(@Param("appCode") String appCode, @Param("model") PagingModel model);

    /**
     * 查询活动类型
     *
     * @param appCode 应用编号
     * @param no 活动类型ID
     * @return 活动类型
     */
    public CctSalonclass getRecordByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询活动类型名称
     *
     * @param appCode 应用编号
     * @param no 活动类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询活动类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 新增活动类型
     *
     * @param info 活动类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CctSalonclass info);

    /**
     * 更新活动类型
     *
     * @param info 活动类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CctSalonclass info);

    /**
     * 硬删除活动类型
     *
     * @param appCode 应用编号
     * @param no 活动类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量硬删除活动类型
     *
     * @param appCode 应用编号
     * @param nos 活动类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件硬删除活动类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 软删除活动类型
     *
     * @param appCode 应用编号
     * @param no 活动类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量软删除活动类型
     *
     * @param appCode 应用编号
     * @param nos 活动类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件软删除活动类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

}
