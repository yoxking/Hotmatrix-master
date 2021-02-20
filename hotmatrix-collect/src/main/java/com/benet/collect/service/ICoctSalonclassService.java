package com.benet.collect.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CoctSalonclass;

/**
 * 沙龙类型Service接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public interface ICoctSalonclassService 
{
    /**
     * 查询所有沙龙类型列表
     *
     * @param appCode 应用编号
     * @return 沙龙类型集合
     */
    public List<CoctSalonclass> getAllRecords(String appCode);

    /**
     * 按分类查询沙龙类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 沙龙类型集合
     */
    public List<CoctSalonclass> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询沙龙类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 沙龙类型集合
     */
    public List<CoctSalonclass> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询沙龙类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 沙龙类型集合
     */
    public List<CoctSalonclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询沙龙类型
     *
     * @param appCode 应用编号
     * @param no 沙龙类型ID
     * @return 沙龙类型
     */
    public CoctSalonclass getRecordByNo(String appCode,String no);

    /**
     * 查询沙龙类型名称
     *
     * @param appCode 应用编号
     * @param no 沙龙类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询沙龙类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增沙龙类型
     *
     * @param appCode 应用编号
     * @param info 沙龙类型
     * @return 结果
     */
    public int AddNewRecord(String appCode,CoctSalonclass info);

    /**
     * 更新沙龙类型
     *
     * @param appCode 应用编号
     * @param info 沙龙类型
     * @return 结果
     */
    public int UpdateRecord(String appCode,CoctSalonclass info);

    /**
     * 硬删除沙龙类型
     *
     * @param appCode 应用编号
     * @param no 沙龙类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除沙龙类型
     *
     * @param appCode 应用编号
     * @param nos 沙龙类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除沙龙类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除沙龙类型
     *
     * @param appCode 应用编号
     * @param no 沙龙类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除沙龙类型
     *
     * @param appCode 应用编号
     * @param nos 沙龙类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除沙龙类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
