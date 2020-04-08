package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysTablefield;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 代码生成业务字段Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysTablefieldMapper 
{
    /**
     * 查询所有代码生成业务字段列表
     *
     * @param appCode 应用编号
     * @return 代码生成业务字段集合
     */
    public List<SysTablefield> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询代码生成业务字段列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 代码生成业务字段集合
     */
    public List<SysTablefield> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询代码生成业务字段列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 代码生成业务字段集合
     */
    public List<SysTablefield> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询代码生成业务字段
     *
     * @param appCode 应用编号
     * @param no 代码生成业务字段ID
     * @return 代码生成业务字段
     */
    public SysTablefield getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询代码生成业务字段名称
     *
     * @param appCode 应用编号
     * @param no 代码生成业务字段ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询代码生成业务字段计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增代码生成业务字段
     *
     * @param info 代码生成业务字段
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysTablefield info);

    /**
     * 更新代码生成业务字段
     *
     * @param info 代码生成业务字段
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysTablefield info);

    /**
     * 硬删除代码生成业务字段
     *
     * @param appCode 应用编号
     * @param no 代码生成业务字段ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除代码生成业务字段
     *
     * @param appCode 应用编号
     * @param nos 代码生成业务字段IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除代码生成业务字段
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除代码生成业务字段
     *
     * @param appCode 应用编号
     * @param no 代码生成业务字段ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除代码生成业务字段
     *
     * @param appCode 应用编号
     * @param nos 代码生成业务字段IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除代码生成业务字段
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
