package com.benet.collect.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CoctReportinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 报告信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Mapper
public interface CoctReportinfoMapper 
{
    /**
     * 查询所有报告信息列表
     *
     * @param appCode 应用编号
     * @return 报告信息集合
     */
    public List<CoctReportinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询报告信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 报告信息集合
     */
    public List<CoctReportinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询报告信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 报告信息集合
     */
    public List<CoctReportinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询报告信息
     *
     * @param appCode 应用编号
     * @param no 报告信息ID
     * @return 报告信息
     */
    public CoctReportinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询报告信息名称
     *
     * @param appCode 应用编号
     * @param no 报告信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询报告信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增报告信息
     *
     * @param info 报告信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CoctReportinfo info);

    /**
     * 更新报告信息
     *
     * @param info 报告信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CoctReportinfo info);

    /**
     * 硬删除报告信息
     *
     * @param appCode 应用编号
     * @param no 报告信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除报告信息
     *
     * @param appCode 应用编号
     * @param nos 报告信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除报告信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除报告信息
     *
     * @param appCode 应用编号
     * @param no 报告信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除报告信息
     *
     * @param appCode 应用编号
     * @param nos 报告信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除报告信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
