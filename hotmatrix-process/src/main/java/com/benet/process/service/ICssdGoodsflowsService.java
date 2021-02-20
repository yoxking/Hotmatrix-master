package com.benet.process.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdGoodsflows;

/**
 * 物品操作Service接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
public interface ICssdGoodsflowsService 
{
    /**
     * 查询所有物品操作列表
     *
     * @param appCode 应用编号
     * @return 物品操作集合
     */
    public List<CssdGoodsflows> getAllRecords(String appCode);

    /**
     * 按分类查询物品操作列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 物品操作集合
     */
    public List<CssdGoodsflows> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询物品操作列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 物品操作集合
     */
    public List<CssdGoodsflows> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询物品操作列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 物品操作集合
     */
    public List<CssdGoodsflows> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询物品操作
     *
     * @param appCode 应用编号
     * @param no 物品操作ID
     * @return 物品操作
     */
    public CssdGoodsflows getRecordByNo(String appCode,String no);

    /**
     * 查询物品操作名称
     *
     * @param appCode 应用编号
     * @param no 物品操作ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询物品操作计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增物品操作
     *
     * @param appCode 应用编号
     * @param info 物品操作
     * @return 结果
     */
    public int AddNewRecord(String appCode,CssdGoodsflows info);

    /**
     * 更新物品操作
     *
     * @param appCode 应用编号
     * @param info 物品操作
     * @return 结果
     */
    public int UpdateRecord(String appCode,CssdGoodsflows info);

    /**
     * 硬删除物品操作
     *
     * @param appCode 应用编号
     * @param no 物品操作ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除物品操作
     *
     * @param appCode 应用编号
     * @param nos 物品操作IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除物品操作
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除物品操作
     *
     * @param appCode 应用编号
     * @param no 物品操作ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除物品操作
     *
     * @param appCode 应用编号
     * @param nos 物品操作IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除物品操作
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
