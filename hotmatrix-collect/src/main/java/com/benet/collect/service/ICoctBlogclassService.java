package com.benet.collect.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.collect.domain.CoctBlogclass;

/**
 * 日记类型Service接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public interface ICoctBlogclassService 
{
    /**
     * 查询所有日记类型列表
     *
     * @param appCode 应用编号
     * @return 日记类型集合
     */
    public List<CoctBlogclass> getAllRecords(String appCode);

    /**
     * 按分类查询日记类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 日记类型集合
     */
    public List<CoctBlogclass> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询日记类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 日记类型集合
     */
    public List<CoctBlogclass> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询日记类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 日记类型集合
     */
    public List<CoctBlogclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询日记类型
     *
     * @param appCode 应用编号
     * @param no 日记类型ID
     * @return 日记类型
     */
    public CoctBlogclass getRecordByNo(String appCode,String no);

    /**
     * 查询日记类型名称
     *
     * @param appCode 应用编号
     * @param no 日记类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询日记类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增日记类型
     *
     * @param appCode 应用编号
     * @param info 日记类型
     * @return 结果
     */
    public int AddNewRecord(String appCode,CoctBlogclass info);

    /**
     * 更新日记类型
     *
     * @param appCode 应用编号
     * @param info 日记类型
     * @return 结果
     */
    public int UpdateRecord(String appCode,CoctBlogclass info);

    /**
     * 硬删除日记类型
     *
     * @param appCode 应用编号
     * @param no 日记类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除日记类型
     *
     * @param appCode 应用编号
     * @param nos 日记类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除日记类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除日记类型
     *
     * @param appCode 应用编号
     * @param no 日记类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除日记类型
     *
     * @param appCode 应用编号
     * @param nos 日记类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除日记类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
