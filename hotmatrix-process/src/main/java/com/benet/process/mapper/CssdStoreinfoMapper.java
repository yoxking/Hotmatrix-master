package com.benet.process.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdStoreinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 库房信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface CssdStoreinfoMapper 
{
    /**
     * 查询所有库房信息列表
     *
     * @param appCode 应用编号
     * @return 库房信息集合
     */
    public List<CssdStoreinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询库房信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 库房信息集合
     */
    public List<CssdStoreinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询库房信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 库房信息集合
     */
    public List<CssdStoreinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询库房信息
     *
     * @param appCode 应用编号
     * @param no 库房信息ID
     * @return 库房信息
     */
    public CssdStoreinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询库房信息名称
     *
     * @param appCode 应用编号
     * @param no 库房信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询库房信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增库房信息
     *
     * @param info 库房信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdStoreinfo info);

    /**
     * 更新库房信息
     *
     * @param info 库房信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdStoreinfo info);

    /**
     * 硬删除库房信息
     *
     * @param appCode 应用编号
     * @param no 库房信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除库房信息
     *
     * @param appCode 应用编号
     * @param nos 库房信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除库房信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除库房信息
     *
     * @param appCode 应用编号
     * @param no 库房信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除库房信息
     *
     * @param appCode 应用编号
     * @param nos 库房信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除库房信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
