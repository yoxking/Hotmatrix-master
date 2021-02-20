package com.benet.process.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdPackclass;

/**
 * 包装方式Service接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
public interface ICssdPackclassService 
{
    /**
     * 查询所有包装方式列表
     *
     * @param appCode 应用编号
     * @return 包装方式集合
     */
    public List<CssdPackclass> getAllRecords(String appCode);

    /**
     * 按分类查询包装方式列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 包装方式集合
     */
    public List<CssdPackclass> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询包装方式列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 包装方式集合
     */
    public List<CssdPackclass> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询包装方式列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 包装方式集合
     */
    public List<CssdPackclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询包装方式
     *
     * @param appCode 应用编号
     * @param no 包装方式ID
     * @return 包装方式
     */
    public CssdPackclass getRecordByNo(String appCode,String no);

    /**
     * 查询包装方式名称
     *
     * @param appCode 应用编号
     * @param no 包装方式ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询包装方式计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增包装方式
     *
     * @param appCode 应用编号
     * @param info 包装方式
     * @return 结果
     */
    public int AddNewRecord(String appCode,CssdPackclass info);

    /**
     * 更新包装方式
     *
     * @param appCode 应用编号
     * @param info 包装方式
     * @return 结果
     */
    public int UpdateRecord(String appCode,CssdPackclass info);

    /**
     * 硬删除包装方式
     *
     * @param appCode 应用编号
     * @param no 包装方式ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除包装方式
     *
     * @param appCode 应用编号
     * @param nos 包装方式IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除包装方式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除包装方式
     *
     * @param appCode 应用编号
     * @param no 包装方式ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除包装方式
     *
     * @param appCode 应用编号
     * @param nos 包装方式IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除包装方式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
