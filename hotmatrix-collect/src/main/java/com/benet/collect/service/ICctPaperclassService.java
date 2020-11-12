package com.benet.collect.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CctPaperclass;

/**
 * 问卷类型Service接口
 * 
 * @author yoxking
 * @date 2020-11-10
 */
public interface ICctPaperclassService 
{
    /**
     * 查询所有问卷类型列表
     *
     * @param appCode 应用编号
     * @return 问卷类型集合
     */
    public List<CctPaperclass> getAllRecords(String appCode);

    /**
     * 按分类查询问卷类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 问卷类型集合
     */
    public List<CctPaperclass> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询问卷类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 问卷类型集合
     */
    public List<CctPaperclass> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询问卷类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 问卷类型集合
     */
    public List<CctPaperclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询问卷类型
     *
     * @param appCode 应用编号
     * @param no 问卷类型ID
     * @return 问卷类型
     */
    public CctPaperclass getRecordByNo(String appCode,String no);

    /**
     * 查询问卷类型名称
     *
     * @param appCode 应用编号
     * @param no 问卷类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询问卷类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增问卷类型
     *
     * @param appCode 应用编号
     * @param info 问卷类型
     * @return 结果
     */
    public int AddNewRecord(String appCode,CctPaperclass info);

    /**
     * 更新问卷类型
     *
     * @param appCode 应用编号
     * @param info 问卷类型
     * @return 结果
     */
    public int UpdateRecord(String appCode,CctPaperclass info);

    /**
     * 硬删除问卷类型
     *
     * @param appCode 应用编号
     * @param no 问卷类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除问卷类型
     *
     * @param appCode 应用编号
     * @param nos 问卷类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除问卷类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除问卷类型
     *
     * @param appCode 应用编号
     * @param no 问卷类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除问卷类型
     *
     * @param appCode 应用编号
     * @param nos 问卷类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除问卷类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
