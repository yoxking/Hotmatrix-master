package com.benet.collect.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CoctRemarkinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 评论信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Mapper
public interface CoctRemarkinfoMapper 
{
    /**
     * 查询所有评论信息列表
     *
     * @param appCode 应用编号
     * @return 评论信息集合
     */
    public List<CoctRemarkinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询评论信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 评论信息集合
     */
    public List<CoctRemarkinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询评论信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 评论信息集合
     */
    public List<CoctRemarkinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询评论信息
     *
     * @param appCode 应用编号
     * @param no 评论信息ID
     * @return 评论信息
     */
    public CoctRemarkinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询评论信息名称
     *
     * @param appCode 应用编号
     * @param no 评论信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询评论信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增评论信息
     *
     * @param info 评论信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CoctRemarkinfo info);

    /**
     * 更新评论信息
     *
     * @param info 评论信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CoctRemarkinfo info);

    /**
     * 硬删除评论信息
     *
     * @param appCode 应用编号
     * @param no 评论信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除评论信息
     *
     * @param appCode 应用编号
     * @param nos 评论信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除评论信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除评论信息
     *
     * @param appCode 应用编号
     * @param no 评论信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除评论信息
     *
     * @param appCode 应用编号
     * @param nos 评论信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除评论信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
