package com.benet.process.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdSteryclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 灭菌程序/类型Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Mapper
public interface CssdSteryclassMapper 
{
    /**
     * 查询所有灭菌程序/类型列表
     *
     * @param appCode 应用编号
     * @return 灭菌程序/类型集合
     */
    public List<CssdSteryclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询灭菌程序/类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 灭菌程序/类型集合
     */
    public List<CssdSteryclass> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询灭菌程序/类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 灭菌程序/类型集合
     */
    public List<CssdSteryclass> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param no 灭菌程序/类型ID
     * @return 灭菌程序/类型
     */
    public CssdSteryclass getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询灭菌程序/类型名称
     *
     * @param appCode 应用编号
     * @param no 灭菌程序/类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询灭菌程序/类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增灭菌程序/类型
     *
     * @param info 灭菌程序/类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdSteryclass info);

    /**
     * 更新灭菌程序/类型
     *
     * @param info 灭菌程序/类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdSteryclass info);

    /**
     * 硬删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param no 灭菌程序/类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param nos 灭菌程序/类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param no 灭菌程序/类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param nos 灭菌程序/类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
