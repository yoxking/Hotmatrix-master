package com.benet.collect.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CctPaperinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 问卷信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-08-27
 */
@Mapper
public interface CctPaperinfoMapper 
{
    /**
     * 查询所有问卷信息列表
     *
     * @param appCode 应用编号
     * @return 问卷信息集合
     */
    public List<CctPaperinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询问卷信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 问卷信息集合
     */
    public List<CctPaperinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询问卷信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 问卷信息集合
     */
    public List<CctPaperinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询问卷信息
     *
     * @param appCode 应用编号
     * @param no 问卷信息ID
     * @return 问卷信息
     */
    public CctPaperinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询问卷信息名称
     *
     * @param appCode 应用编号
     * @param no 问卷信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询问卷信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增问卷信息
     *
     * @param info 问卷信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CctPaperinfo info);

    /**
     * 更新问卷信息
     *
     * @param info 问卷信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CctPaperinfo info);

    /**
     * 硬删除问卷信息
     *
     * @param appCode 应用编号
     * @param no 问卷信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除问卷信息
     *
     * @param appCode 应用编号
     * @param nos 问卷信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除问卷信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除问卷信息
     *
     * @param appCode 应用编号
     * @param no 问卷信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除问卷信息
     *
     * @param appCode 应用编号
     * @param nos 问卷信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除问卷信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
