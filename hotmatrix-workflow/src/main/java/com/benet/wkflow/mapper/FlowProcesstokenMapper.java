package com.benet.wkflow.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlowProcesstoken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 流程TokenMapper接口
 * 
 * @author yoxking
 * @date 2021-01-31
 */
@Mapper
public interface FlowProcesstokenMapper 
{
    /**
     * 查询所有流程Token列表
     *
     * @param appCode 应用编号
     * @return 流程Token集合
     */
    public List<FlowProcesstoken> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询流程Token列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 流程Token集合
     */
    public List<FlowProcesstoken> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询流程Token列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 流程Token集合
     */
    public List<FlowProcesstoken> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询流程Token
     *
     * @param appCode 应用编号
     * @param no 流程TokenID
     * @return 流程Token
     */
    public FlowProcesstoken getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询流程Token名称
     *
     * @param appCode 应用编号
     * @param no 流程TokenID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询流程Token计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增流程Token
     *
     * @param info 流程Token
     * @return 结果
     */
    public int AddNewRecord(@Param("info") FlowProcesstoken info);

    /**
     * 更新流程Token
     *
     * @param info 流程Token
     * @return 结果
     */
    public int UpdateRecord(@Param("info") FlowProcesstoken info);

    /**
     * 硬删除流程Token
     *
     * @param appCode 应用编号
     * @param no 流程TokenID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除流程Token
     *
     * @param appCode 应用编号
     * @param nos 流程TokenIDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除流程Token
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除流程Token
     *
     * @param appCode 应用编号
     * @param no 流程TokenID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除流程Token
     *
     * @param appCode 应用编号
     * @param nos 流程TokenIDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除流程Token
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
