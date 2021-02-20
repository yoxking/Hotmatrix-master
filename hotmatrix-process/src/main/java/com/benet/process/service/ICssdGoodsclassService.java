package com.benet.process.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdGoodsclass;

/**
 * 物品包类型Service接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
public interface ICssdGoodsclassService 
{
    /**
     * 查询所有物品包类型列表
     *
     * @param appCode 应用编号
     * @return 物品包类型集合
     */
    public List<CssdGoodsclass> getAllRecords(String appCode);

    /**
     * 按分类查询物品包类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 物品包类型集合
     */
    public List<CssdGoodsclass> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询物品包类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 物品包类型集合
     */
    public List<CssdGoodsclass> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询物品包类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 物品包类型集合
     */
    public List<CssdGoodsclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询物品包类型
     *
     * @param appCode 应用编号
     * @param no 物品包类型ID
     * @return 物品包类型
     */
    public CssdGoodsclass getRecordByNo(String appCode,String no);

    /**
     * 查询物品包类型名称
     *
     * @param appCode 应用编号
     * @param no 物品包类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询物品包类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增物品包类型
     *
     * @param appCode 应用编号
     * @param info 物品包类型
     * @return 结果
     */
    public int AddNewRecord(String appCode,CssdGoodsclass info);

    /**
     * 更新物品包类型
     *
     * @param appCode 应用编号
     * @param info 物品包类型
     * @return 结果
     */
    public int UpdateRecord(String appCode,CssdGoodsclass info);

    /**
     * 硬删除物品包类型
     *
     * @param appCode 应用编号
     * @param no 物品包类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除物品包类型
     *
     * @param appCode 应用编号
     * @param nos 物品包类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除物品包类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除物品包类型
     *
     * @param appCode 应用编号
     * @param no 物品包类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除物品包类型
     *
     * @param appCode 应用编号
     * @param nos 物品包类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除物品包类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
