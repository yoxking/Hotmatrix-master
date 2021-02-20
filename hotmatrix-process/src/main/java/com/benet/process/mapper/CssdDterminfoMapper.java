package com.benet.process.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdDterminfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 有效期天数Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Mapper
public interface CssdDterminfoMapper 
{
    /**
     * 查询所有有效期天数列表
     *
     * @param appCode 应用编号
     * @return 有效期天数集合
     */
    public List<CssdDterminfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询有效期天数列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 有效期天数集合
     */
    public List<CssdDterminfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询有效期天数列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 有效期天数集合
     */
    public List<CssdDterminfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询有效期天数
     *
     * @param appCode 应用编号
     * @param no 有效期天数ID
     * @return 有效期天数
     */
    public CssdDterminfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询有效期天数名称
     *
     * @param appCode 应用编号
     * @param no 有效期天数ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询有效期天数计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增有效期天数
     *
     * @param info 有效期天数
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdDterminfo info);

    /**
     * 更新有效期天数
     *
     * @param info 有效期天数
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdDterminfo info);

    /**
     * 硬删除有效期天数
     *
     * @param appCode 应用编号
     * @param no 有效期天数ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除有效期天数
     *
     * @param appCode 应用编号
     * @param nos 有效期天数IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除有效期天数
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除有效期天数
     *
     * @param appCode 应用编号
     * @param no 有效期天数ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除有效期天数
     *
     * @param appCode 应用编号
     * @param nos 有效期天数IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除有效期天数
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
