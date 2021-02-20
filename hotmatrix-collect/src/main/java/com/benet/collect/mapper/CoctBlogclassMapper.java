package com.benet.collect.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CoctBlogclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 日记类型Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Mapper
public interface CoctBlogclassMapper 
{
    /**
     * 查询所有日记类型列表
     *
     * @param appCode 应用编号
     * @return 日记类型集合
     */
    public List<CoctBlogclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询日记类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 日记类型集合
     */
    public List<CoctBlogclass> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询日记类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 日记类型集合
     */
    public List<CoctBlogclass> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询日记类型
     *
     * @param appCode 应用编号
     * @param no 日记类型ID
     * @return 日记类型
     */
    public CoctBlogclass getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询日记类型名称
     *
     * @param appCode 应用编号
     * @param no 日记类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询日记类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增日记类型
     *
     * @param info 日记类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CoctBlogclass info);

    /**
     * 更新日记类型
     *
     * @param info 日记类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CoctBlogclass info);

    /**
     * 硬删除日记类型
     *
     * @param appCode 应用编号
     * @param no 日记类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除日记类型
     *
     * @param appCode 应用编号
     * @param nos 日记类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除日记类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除日记类型
     *
     * @param appCode 应用编号
     * @param no 日记类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除日记类型
     *
     * @param appCode 应用编号
     * @param nos 日记类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除日记类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
