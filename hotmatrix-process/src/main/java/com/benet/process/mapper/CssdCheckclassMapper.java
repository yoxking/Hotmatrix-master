package com.benet.process.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdCheckclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 签到类型Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface CssdCheckclassMapper 
{
    /**
     * 查询所有签到类型列表
     *
     * @param appCode 应用编号
     * @return 签到类型集合
     */
    public List<CssdCheckclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询签到类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 签到类型集合
     */
    public List<CssdCheckclass> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询签到类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 签到类型集合
     */
    public List<CssdCheckclass> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询签到类型
     *
     * @param appCode 应用编号
     * @param no 签到类型ID
     * @return 签到类型
     */
    public CssdCheckclass getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询签到类型名称
     *
     * @param appCode 应用编号
     * @param no 签到类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询签到类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增签到类型
     *
     * @param info 签到类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdCheckclass info);

    /**
     * 更新签到类型
     *
     * @param info 签到类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdCheckclass info);

    /**
     * 硬删除签到类型
     *
     * @param appCode 应用编号
     * @param no 签到类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除签到类型
     *
     * @param appCode 应用编号
     * @param nos 签到类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除签到类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除签到类型
     *
     * @param appCode 应用编号
     * @param no 签到类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除签到类型
     *
     * @param appCode 应用编号
     * @param nos 签到类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除签到类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
