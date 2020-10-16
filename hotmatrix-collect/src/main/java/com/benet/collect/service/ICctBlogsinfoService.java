package com.benet.collect.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CctBlogsinfo;

/**
 * 日记信息Service接口
 * 
 * @author yoxking
 * @date 2020-10-14
 */
public interface ICctBlogsinfoService 
{
    /**
     * 查询所有日记信息列表
     *
     * @param appCode 应用编号
     * @return 日记信息集合
     */
    public List<CctBlogsinfo> getAllRecords(String appCode);

    /**
     * 按分类查询日记信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 日记信息集合
     */
    public List<CctBlogsinfo> getRecordsByClassNo(String appCode, String classNo);

    /**
     * 分页查询日记信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 日记信息集合
     */
    public List<CctBlogsinfo> getRecordsByPaging(String appCode, PagingModel model);

    /**
     * 分页查询日记信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 日记信息集合
     */
    public List<CctBlogsinfo> getRecordsByPaging(String appCode, int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询日记信息
     *
     * @param appCode 应用编号
     * @param no 日记信息ID
     * @return 日记信息
     */
    public CctBlogsinfo getRecordByNo(String appCode, String no);

    /**
     * 查询日记信息名称
     *
     * @param appCode 应用编号
     * @param no 日记信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode, String no);

    /**
     * 查询日记信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode, String condition);

    /**
     * 新增日记信息
     *
     * @param appCode 应用编号
     * @param info 日记信息
     * @return 结果
     */
    public int AddNewRecord(String appCode, CctBlogsinfo info);

    /**
     * 更新日记信息
     *
     * @param appCode 应用编号
     * @param info 日记信息
     * @return 结果
     */
    public int UpdateRecord(String appCode, CctBlogsinfo info);

    /**
     * 硬删除日记信息
     *
     * @param appCode 应用编号
     * @param no 日记信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode, String no);

    /**
     * 批量硬删除日记信息
     *
     * @param appCode 应用编号
     * @param nos 日记信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode, String[] nos);

    /**
     * 按条件硬删除日记信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode, String condition);

    /**
     * 软删除日记信息
     *
     * @param appCode 应用编号
     * @param no 日记信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode, String no);

    /**
     * 批量软删除日记信息
     *
     * @param appCode 应用编号
     * @param nos 日记信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode, String[] nos);

    /**
     * 按条件软删除日记信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode, String condition);
}
