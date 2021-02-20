package com.benet.collect.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CoctSalonsinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 沙龙信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Mapper
public interface CoctSalonsinfoMapper 
{
    /**
     * 查询所有沙龙信息列表
     *
     * @param appCode 应用编号
     * @return 沙龙信息集合
     */
    public List<CoctSalonsinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询沙龙信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 沙龙信息集合
     */
    public List<CoctSalonsinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询沙龙信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 沙龙信息集合
     */
    public List<CoctSalonsinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询沙龙信息
     *
     * @param appCode 应用编号
     * @param no 沙龙信息ID
     * @return 沙龙信息
     */
    public CoctSalonsinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询沙龙信息名称
     *
     * @param appCode 应用编号
     * @param no 沙龙信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询沙龙信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增沙龙信息
     *
     * @param info 沙龙信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CoctSalonsinfo info);

    /**
     * 更新沙龙信息
     *
     * @param info 沙龙信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CoctSalonsinfo info);

    /**
     * 硬删除沙龙信息
     *
     * @param appCode 应用编号
     * @param no 沙龙信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除沙龙信息
     *
     * @param appCode 应用编号
     * @param nos 沙龙信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除沙龙信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除沙龙信息
     *
     * @param appCode 应用编号
     * @param no 沙龙信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除沙龙信息
     *
     * @param appCode 应用编号
     * @param nos 沙龙信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除沙龙信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
