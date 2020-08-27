package com.benet.wkflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlwTabcolumn;

/**
 * 单字段Service接口
 * 
 * @author yoxking
 * @date 2020-05-23
 */
public interface IFlwTabcolumnService 
{
    /**
     * 查询所有单字段列表
     *
     * @param appCode 应用编号
     * @return 单字段集合
     */
    public List<FlwTabcolumn> getAllRecords(String appCode);

    /**
     * 按分类查询单字段列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 单字段集合
     */
    public List<FlwTabcolumn> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询单字段列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 单字段集合
     */
    public List<FlwTabcolumn> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询单字段列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 单字段集合
     */
    public List<FlwTabcolumn> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询单字段
     *
     * @param appCode 应用编号
     * @param no 单字段ID
     * @return 单字段
     */
    public FlwTabcolumn getRecordByNo(String appCode,String no);

    /**
     * 查询单字段名称
     *
     * @param appCode 应用编号
     * @param no 单字段ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询单字段计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增单字段
     *
     * @param appCode 应用编号
     * @param info 单字段
     * @return 结果
     */
    public int AddNewRecord(String appCode,FlwTabcolumn info);

    /**
     * 更新单字段
     *
     * @param appCode 应用编号
     * @param info 单字段
     * @return 结果
     */
    public int UpdateRecord(String appCode,FlwTabcolumn info);

    /**
     * 硬删除单字段
     *
     * @param appCode 应用编号
     * @param no 单字段ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除单字段
     *
     * @param appCode 应用编号
     * @param nos 单字段IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除单字段
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除单字段
     *
     * @param appCode 应用编号
     * @param no 单字段ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除单字段
     *
     * @param appCode 应用编号
     * @param nos 单字段IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除单字段
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
