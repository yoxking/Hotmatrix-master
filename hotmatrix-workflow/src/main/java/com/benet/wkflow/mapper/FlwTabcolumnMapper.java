package com.benet.wkflow.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlwTabcolumn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 单字段Mapper接口
 * 
 * @author yoxking
 * @date 2020-05-23
 */
@Mapper
public interface FlwTabcolumnMapper 
{
    /**
     * 查询所有单字段列表
     *
     * @param appCode 应用编号
     * @return 单字段集合
     */
    public List<FlwTabcolumn> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询单字段列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 单字段集合
     */
    public List<FlwTabcolumn> getRecordsByClassNo(@Param("appCode") String appCode, @Param("classNo") String classNo);

    /**
     * 分页查询单字段列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 单字段集合
     */
    public List<FlwTabcolumn> getRecordsByPaging(@Param("appCode") String appCode, @Param("model") PagingModel model);

    /**
     * 查询单字段
     *
     * @param appCode 应用编号
     * @param no 单字段ID
     * @return 单字段
     */
    public FlwTabcolumn getRecordByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询单字段名称
     *
     * @param appCode 应用编号
     * @param no 单字段ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询单字段计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 新增单字段
     *
     * @param info 单字段
     * @return 结果
     */
    public int AddNewRecord(@Param("info") FlwTabcolumn info);

    /**
     * 更新单字段
     *
     * @param info 单字段
     * @return 结果
     */
    public int UpdateRecord(@Param("info") FlwTabcolumn info);

    /**
     * 硬删除单字段
     *
     * @param appCode 应用编号
     * @param no 单字段ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量硬删除单字段
     *
     * @param appCode 应用编号
     * @param nos 单字段IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件硬删除单字段
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 软删除单字段
     *
     * @param appCode 应用编号
     * @param no 单字段ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量软删除单字段
     *
     * @param appCode 应用编号
     * @param nos 单字段IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件软删除单字段
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

}
