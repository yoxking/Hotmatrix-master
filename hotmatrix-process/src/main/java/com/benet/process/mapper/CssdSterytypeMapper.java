package com.benet.process.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdSterytype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 灭菌方式Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Mapper
public interface CssdSterytypeMapper 
{
    /**
     * 查询所有灭菌方式列表
     *
     * @param appCode 应用编号
     * @return 灭菌方式集合
     */
    public List<CssdSterytype> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询灭菌方式列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 灭菌方式集合
     */
    public List<CssdSterytype> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询灭菌方式列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 灭菌方式集合
     */
    public List<CssdSterytype> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询灭菌方式
     *
     * @param appCode 应用编号
     * @param no 灭菌方式ID
     * @return 灭菌方式
     */
    public CssdSterytype getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询灭菌方式名称
     *
     * @param appCode 应用编号
     * @param no 灭菌方式ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询灭菌方式计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增灭菌方式
     *
     * @param info 灭菌方式
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdSterytype info);

    /**
     * 更新灭菌方式
     *
     * @param info 灭菌方式
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdSterytype info);

    /**
     * 硬删除灭菌方式
     *
     * @param appCode 应用编号
     * @param no 灭菌方式ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除灭菌方式
     *
     * @param appCode 应用编号
     * @param nos 灭菌方式IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除灭菌方式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除灭菌方式
     *
     * @param appCode 应用编号
     * @param no 灭菌方式ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除灭菌方式
     *
     * @param appCode 应用编号
     * @param nos 灭菌方式IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除灭菌方式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
