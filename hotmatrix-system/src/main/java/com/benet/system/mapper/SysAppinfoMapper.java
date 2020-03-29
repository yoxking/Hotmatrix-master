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
 * @date 2020-03-28
 */
@Mapper
public interface SysAppinfoMapper 
{
    /**
     * 查询所有信息列表
     *
     * @return 分支信息列表
     */
    public List<SysAppinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询分支信息列表
     *
     * @param classNo 分类编号
     * @return 分支信息列表
     */
    public List<SysAppinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询分支信息列表
     *
     * @param model 分页模型
     * @return 分支信息列表
     */
    public List<SysAppinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询分支信息
     *
     * @param no 分支信息ID
     * @return 分支信息
     */
    public SysAppinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询分支信息名称
     *
     * @param no 分支信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询分支信息计数
     *
     * @param condition 查询条件
     * @return 计数
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增分支信息
     *
     * @param info 分支信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysAppinfo info);

    /**
     * 更新分支信息
     *
     * @param info 分支信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysAppinfo info);

    /**
     * 硬删除分支信息
     *
     * @param no 分支信息ID
     * @return 结果
     */
    public int HardDeleteRecord(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 硬删除分支信息
     *
     * @param nos 分支信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 硬删除分支信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除分支信息
     *
     * @param no 分支信息ID
     * @return 结果
     */
    public int SoftDeleteRecord(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 软删除分支信息
     *
     * @param nos 分支信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 软删除分支信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);
}
