package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysRenterinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 租户信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysRenterinfoMapper 
{
    /**
     * 查询所有租户信息列表
     * 
     * @return 租户信息集合
     */
    public List<SysRenterinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询租户信息列表
     *
     * @param classNo 分类编号
     * @return 租户信息集合
     */
    public List<SysRenterinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询租户信息列表
     *
     * @param model 分页模型
     * @return 租户信息集合
     */
    public List<SysRenterinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询租户信息
     *
     * @param no 租户信息ID
     * @return 租户信息
     */
    public SysRenterinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询租户信息名称
     *
     * @param no 租户信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询租户信息计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增租户信息
     *
     * @param info 租户信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysRenterinfo info);

    /**
     * 更新租户信息
     *
     * @param info 租户信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysRenterinfo info);

    /**
     * 硬删除租户信息
     *
     * @param no 租户信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除租户信息
     *
     * @param nos 租户信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除租户信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除租户信息
     *
     * @param no 租户信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除租户信息
     *
     * @param nos 租户信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除租户信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
