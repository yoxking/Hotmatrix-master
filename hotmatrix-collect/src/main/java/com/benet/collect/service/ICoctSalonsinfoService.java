package com.benet.collect.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CoctSalonsinfo;

/**
 * 沙龙信息Service接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public interface ICoctSalonsinfoService 
{
    /**
     * 查询所有沙龙信息列表
     *
     * @param appCode 应用编号
     * @return 沙龙信息集合
     */
    public List<CoctSalonsinfo> getAllRecords(String appCode);

    /**
     * 按分类查询沙龙信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 沙龙信息集合
     */
    public List<CoctSalonsinfo> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询沙龙信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 沙龙信息集合
     */
    public List<CoctSalonsinfo> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询沙龙信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 沙龙信息集合
     */
    public List<CoctSalonsinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询沙龙信息
     *
     * @param appCode 应用编号
     * @param no 沙龙信息ID
     * @return 沙龙信息
     */
    public CoctSalonsinfo getRecordByNo(String appCode,String no);

    /**
     * 查询沙龙信息名称
     *
     * @param appCode 应用编号
     * @param no 沙龙信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询沙龙信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增沙龙信息
     *
     * @param appCode 应用编号
     * @param info 沙龙信息
     * @return 结果
     */
    public int AddNewRecord(String appCode,CoctSalonsinfo info);

    /**
     * 更新沙龙信息
     *
     * @param appCode 应用编号
     * @param info 沙龙信息
     * @return 结果
     */
    public int UpdateRecord(String appCode,CoctSalonsinfo info);

    /**
     * 硬删除沙龙信息
     *
     * @param appCode 应用编号
     * @param no 沙龙信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除沙龙信息
     *
     * @param appCode 应用编号
     * @param nos 沙龙信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除沙龙信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除沙龙信息
     *
     * @param appCode 应用编号
     * @param no 沙龙信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除沙龙信息
     *
     * @param appCode 应用编号
     * @param nos 沙龙信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除沙龙信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
