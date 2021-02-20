package com.benet.process.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdStoreinfo;

/**
 * 库房信息Service接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
public interface ICssdStoreinfoService 
{
    /**
     * 查询所有库房信息列表
     *
     * @param appCode 应用编号
     * @return 库房信息集合
     */
    public List<CssdStoreinfo> getAllRecords(String appCode);

    /**
     * 按分类查询库房信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 库房信息集合
     */
    public List<CssdStoreinfo> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询库房信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 库房信息集合
     */
    public List<CssdStoreinfo> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询库房信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 库房信息集合
     */
    public List<CssdStoreinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询库房信息
     *
     * @param appCode 应用编号
     * @param no 库房信息ID
     * @return 库房信息
     */
    public CssdStoreinfo getRecordByNo(String appCode,String no);

    /**
     * 查询库房信息名称
     *
     * @param appCode 应用编号
     * @param no 库房信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询库房信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增库房信息
     *
     * @param appCode 应用编号
     * @param info 库房信息
     * @return 结果
     */
    public int AddNewRecord(String appCode,CssdStoreinfo info);

    /**
     * 更新库房信息
     *
     * @param appCode 应用编号
     * @param info 库房信息
     * @return 结果
     */
    public int UpdateRecord(String appCode,CssdStoreinfo info);

    /**
     * 硬删除库房信息
     *
     * @param appCode 应用编号
     * @param no 库房信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除库房信息
     *
     * @param appCode 应用编号
     * @param nos 库房信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除库房信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除库房信息
     *
     * @param appCode 应用编号
     * @param no 库房信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除库房信息
     *
     * @param appCode 应用编号
     * @param nos 库房信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除库房信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
