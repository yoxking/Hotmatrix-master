package com.benet.process.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdCleanclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 清洗程序类型Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface CssdCleanclassMapper 
{
    /**
     * 查询所有清洗程序类型列表
     *
     * @param appCode 应用编号
     * @return 清洗程序类型集合
     */
    public List<CssdCleanclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询清洗程序类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 清洗程序类型集合
     */
    public List<CssdCleanclass> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询清洗程序类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 清洗程序类型集合
     */
    public List<CssdCleanclass> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询清洗程序类型
     *
     * @param appCode 应用编号
     * @param no 清洗程序类型ID
     * @return 清洗程序类型
     */
    public CssdCleanclass getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询清洗程序类型名称
     *
     * @param appCode 应用编号
     * @param no 清洗程序类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询清洗程序类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增清洗程序类型
     *
     * @param info 清洗程序类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdCleanclass info);

    /**
     * 更新清洗程序类型
     *
     * @param info 清洗程序类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdCleanclass info);

    /**
     * 硬删除清洗程序类型
     *
     * @param appCode 应用编号
     * @param no 清洗程序类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除清洗程序类型
     *
     * @param appCode 应用编号
     * @param nos 清洗程序类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除清洗程序类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除清洗程序类型
     *
     * @param appCode 应用编号
     * @param no 清洗程序类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除清洗程序类型
     *
     * @param appCode 应用编号
     * @param nos 清洗程序类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除清洗程序类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
