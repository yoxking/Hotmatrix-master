package com.benet.process.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdGoodsclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 物品包类型Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface CssdGoodsclassMapper 
{
    /**
     * 查询所有物品包类型列表
     *
     * @param appCode 应用编号
     * @return 物品包类型集合
     */
    public List<CssdGoodsclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询物品包类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 物品包类型集合
     */
    public List<CssdGoodsclass> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询物品包类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 物品包类型集合
     */
    public List<CssdGoodsclass> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询物品包类型
     *
     * @param appCode 应用编号
     * @param no 物品包类型ID
     * @return 物品包类型
     */
    public CssdGoodsclass getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询物品包类型名称
     *
     * @param appCode 应用编号
     * @param no 物品包类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询物品包类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增物品包类型
     *
     * @param info 物品包类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdGoodsclass info);

    /**
     * 更新物品包类型
     *
     * @param info 物品包类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdGoodsclass info);

    /**
     * 硬删除物品包类型
     *
     * @param appCode 应用编号
     * @param no 物品包类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除物品包类型
     *
     * @param appCode 应用编号
     * @param nos 物品包类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除物品包类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除物品包类型
     *
     * @param appCode 应用编号
     * @param no 物品包类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除物品包类型
     *
     * @param appCode 应用编号
     * @param nos 物品包类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除物品包类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
