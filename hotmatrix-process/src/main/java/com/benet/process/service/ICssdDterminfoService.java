package com.benet.process.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdDterminfo;

/**
 * 有效期天数Service接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public interface ICssdDterminfoService 
{
    /**
     * 查询所有有效期天数列表
     *
     * @param appCode 应用编号
     * @return 有效期天数集合
     */
    public List<CssdDterminfo> getAllRecords(String appCode);

    /**
     * 按分类查询有效期天数列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 有效期天数集合
     */
    public List<CssdDterminfo> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询有效期天数列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 有效期天数集合
     */
    public List<CssdDterminfo> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询有效期天数列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 有效期天数集合
     */
    public List<CssdDterminfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询有效期天数
     *
     * @param appCode 应用编号
     * @param no 有效期天数ID
     * @return 有效期天数
     */
    public CssdDterminfo getRecordByNo(String appCode,String no);

    /**
     * 查询有效期天数名称
     *
     * @param appCode 应用编号
     * @param no 有效期天数ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询有效期天数计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增有效期天数
     *
     * @param appCode 应用编号
     * @param info 有效期天数
     * @return 结果
     */
    public int AddNewRecord(String appCode,CssdDterminfo info);

    /**
     * 更新有效期天数
     *
     * @param appCode 应用编号
     * @param info 有效期天数
     * @return 结果
     */
    public int UpdateRecord(String appCode,CssdDterminfo info);

    /**
     * 硬删除有效期天数
     *
     * @param appCode 应用编号
     * @param no 有效期天数ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除有效期天数
     *
     * @param appCode 应用编号
     * @param nos 有效期天数IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除有效期天数
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除有效期天数
     *
     * @param appCode 应用编号
     * @param no 有效期天数ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除有效期天数
     *
     * @param appCode 应用编号
     * @param nos 有效期天数IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除有效期天数
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
