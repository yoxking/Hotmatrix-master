package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysAppclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 应用类型Mapper接口
 * 
 * @author yoxking
 * @date 2020-03-29
 */
@Mapper
public interface SysAppclassMapper 
{
    /**
     * 查询所有应用类型列表
     *
     * @param appCode 应用编号
     * @return 应用类型集合
     */
    public List<SysAppclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询应用类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 应用类型集合
     */
    public List<SysAppclass> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询应用类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 应用类型集合
     */
    public List<SysAppclass> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询应用类型
     *
     * @param appCode 应用编号
     * @param no 应用类型ID
     * @return 应用类型
     */
    public SysAppclass getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询应用类型名称
     *
     * @param appCode 应用编号
     * @param no 应用类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询应用类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增应用类型
     *
     * @param info 应用类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysAppclass info);

    /**
     * 更新应用类型
     *
     * @param info 应用类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysAppclass info);

    /**
     * 硬删除应用类型
     *
     * @param appCode 应用编号
     * @param no 应用类型ID
     * @return 结果
     */
    public int HardDeleteRecord(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除应用类型
     *
     * @param appCode 应用编号
     * @param nos 应用类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除应用类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除应用类型
     *
     * @param appCode 应用编号
     * @param no 应用类型ID
     * @return 结果
     */
    public int SoftDeleteRecord(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除应用类型
     *
     * @param appCode 应用编号
     * @param nos 应用类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除应用类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
