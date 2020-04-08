package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysAppinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 应用信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysAppinfoMapper 
{
    /**
     * 查询所有应用信息列表
     *
     * @param appCode 应用编号
     * @return 应用信息集合
     */
    public List<SysAppinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询应用信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 应用信息集合
     */
    public List<SysAppinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询应用信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 应用信息集合
     */
    public List<SysAppinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询应用信息
     *
     * @param appCode 应用编号
     * @param no 应用信息ID
     * @return 应用信息
     */
    public SysAppinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询应用信息名称
     *
     * @param appCode 应用编号
     * @param no 应用信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询应用信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增应用信息
     *
     * @param info 应用信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysAppinfo info);

    /**
     * 更新应用信息
     *
     * @param info 应用信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysAppinfo info);

    /**
     * 硬删除应用信息
     *
     * @param appCode 应用编号
     * @param no 应用信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除应用信息
     *
     * @param appCode 应用编号
     * @param nos 应用信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除应用信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除应用信息
     *
     * @param appCode 应用编号
     * @param no 应用信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除应用信息
     *
     * @param appCode 应用编号
     * @param nos 应用信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除应用信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
