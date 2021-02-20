package com.benet.process.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdSteryclass;

/**
 * 灭菌程序/类型Service接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public interface ICssdSteryclassService 
{
    /**
     * 查询所有灭菌程序/类型列表
     *
     * @param appCode 应用编号
     * @return 灭菌程序/类型集合
     */
    public List<CssdSteryclass> getAllRecords(String appCode);

    /**
     * 按分类查询灭菌程序/类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 灭菌程序/类型集合
     */
    public List<CssdSteryclass> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询灭菌程序/类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 灭菌程序/类型集合
     */
    public List<CssdSteryclass> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询灭菌程序/类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 灭菌程序/类型集合
     */
    public List<CssdSteryclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param no 灭菌程序/类型ID
     * @return 灭菌程序/类型
     */
    public CssdSteryclass getRecordByNo(String appCode,String no);

    /**
     * 查询灭菌程序/类型名称
     *
     * @param appCode 应用编号
     * @param no 灭菌程序/类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询灭菌程序/类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param info 灭菌程序/类型
     * @return 结果
     */
    public int AddNewRecord(String appCode,CssdSteryclass info);

    /**
     * 更新灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param info 灭菌程序/类型
     * @return 结果
     */
    public int UpdateRecord(String appCode,CssdSteryclass info);

    /**
     * 硬删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param no 灭菌程序/类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param nos 灭菌程序/类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param no 灭菌程序/类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param nos 灭菌程序/类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
