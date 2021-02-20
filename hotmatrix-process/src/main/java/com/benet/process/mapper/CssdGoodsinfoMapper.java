package com.benet.process.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdGoodsinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 物品包信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface CssdGoodsinfoMapper 
{
    /**
     * 查询所有物品包信息列表
     *
     * @param appCode 应用编号
     * @return 物品包信息集合
     */
    public List<CssdGoodsinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询物品包信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 物品包信息集合
     */
    public List<CssdGoodsinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询物品包信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 物品包信息集合
     */
    public List<CssdGoodsinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询物品包信息
     *
     * @param appCode 应用编号
     * @param no 物品包信息ID
     * @return 物品包信息
     */
    public CssdGoodsinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询物品包信息名称
     *
     * @param appCode 应用编号
     * @param no 物品包信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询物品包信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增物品包信息
     *
     * @param info 物品包信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdGoodsinfo info);

    /**
     * 更新物品包信息
     *
     * @param info 物品包信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdGoodsinfo info);

    /**
     * 硬删除物品包信息
     *
     * @param appCode 应用编号
     * @param no 物品包信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除物品包信息
     *
     * @param appCode 应用编号
     * @param nos 物品包信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除物品包信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除物品包信息
     *
     * @param appCode 应用编号
     * @param no 物品包信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除物品包信息
     *
     * @param appCode 应用编号
     * @param nos 物品包信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除物品包信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
