package com.benet.process.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdCleantype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 清洗机次号Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface CssdCleantypeMapper 
{
    /**
     * 查询所有清洗机次号列表
     *
     * @param appCode 应用编号
     * @return 清洗机次号集合
     */
    public List<CssdCleantype> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询清洗机次号列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 清洗机次号集合
     */
    public List<CssdCleantype> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询清洗机次号列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 清洗机次号集合
     */
    public List<CssdCleantype> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询清洗机次号
     *
     * @param appCode 应用编号
     * @param no 清洗机次号ID
     * @return 清洗机次号
     */
    public CssdCleantype getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询清洗机次号名称
     *
     * @param appCode 应用编号
     * @param no 清洗机次号ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询清洗机次号计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增清洗机次号
     *
     * @param info 清洗机次号
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdCleantype info);

    /**
     * 更新清洗机次号
     *
     * @param info 清洗机次号
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdCleantype info);

    /**
     * 硬删除清洗机次号
     *
     * @param appCode 应用编号
     * @param no 清洗机次号ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除清洗机次号
     *
     * @param appCode 应用编号
     * @param nos 清洗机次号IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除清洗机次号
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除清洗机次号
     *
     * @param appCode 应用编号
     * @param no 清洗机次号ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除清洗机次号
     *
     * @param appCode 应用编号
     * @param nos 清洗机次号IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除清洗机次号
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
