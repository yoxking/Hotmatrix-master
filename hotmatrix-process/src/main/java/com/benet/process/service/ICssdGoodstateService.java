package com.benet.process.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdGoodstate;

/**
 * 物品状态Service接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
public interface ICssdGoodstateService 
{
    /**
     * 查询所有物品状态列表
     *
     * @param appCode 应用编号
     * @return 物品状态集合
     */
    public List<CssdGoodstate> getAllRecords(String appCode);

    /**
     * 按分类查询物品状态列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 物品状态集合
     */
    public List<CssdGoodstate> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询物品状态列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 物品状态集合
     */
    public List<CssdGoodstate> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询物品状态列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 物品状态集合
     */
    public List<CssdGoodstate> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询物品状态
     *
     * @param appCode 应用编号
     * @param no 物品状态ID
     * @return 物品状态
     */
    public CssdGoodstate getRecordByNo(String appCode,String no);

    /**
     * 查询物品状态名称
     *
     * @param appCode 应用编号
     * @param no 物品状态ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询物品状态计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增物品状态
     *
     * @param appCode 应用编号
     * @param info 物品状态
     * @return 结果
     */
    public int AddNewRecord(String appCode,CssdGoodstate info);

    /**
     * 更新物品状态
     *
     * @param appCode 应用编号
     * @param info 物品状态
     * @return 结果
     */
    public int UpdateRecord(String appCode,CssdGoodstate info);

    /**
     * 硬删除物品状态
     *
     * @param appCode 应用编号
     * @param no 物品状态ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除物品状态
     *
     * @param appCode 应用编号
     * @param nos 物品状态IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除物品状态
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除物品状态
     *
     * @param appCode 应用编号
     * @param no 物品状态ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除物品状态
     *
     * @param appCode 应用编号
     * @param nos 物品状态IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除物品状态
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
