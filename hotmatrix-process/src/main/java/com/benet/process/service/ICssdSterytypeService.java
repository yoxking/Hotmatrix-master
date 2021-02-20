package com.benet.process.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.process.domain.CssdSterytype;

/**
 * 灭菌方式Service接口
 * 
 * @author yoxking
 * @date 2020-12-19
 */
public interface ICssdSterytypeService 
{
    /**
     * 查询所有灭菌方式列表
     *
     * @param appCode 应用编号
     * @return 灭菌方式集合
     */
    public List<CssdSterytype> getAllRecords(String appCode);

    /**
     * 按分类查询灭菌方式列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 灭菌方式集合
     */
    public List<CssdSterytype> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询灭菌方式列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 灭菌方式集合
     */
    public List<CssdSterytype> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询灭菌方式列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 灭菌方式集合
     */
    public List<CssdSterytype> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询灭菌方式
     *
     * @param appCode 应用编号
     * @param no 灭菌方式ID
     * @return 灭菌方式
     */
    public CssdSterytype getRecordByNo(String appCode,String no);

    /**
     * 查询灭菌方式名称
     *
     * @param appCode 应用编号
     * @param no 灭菌方式ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询灭菌方式计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增灭菌方式
     *
     * @param appCode 应用编号
     * @param info 灭菌方式
     * @return 结果
     */
    public int AddNewRecord(String appCode,CssdSterytype info);

    /**
     * 更新灭菌方式
     *
     * @param appCode 应用编号
     * @param info 灭菌方式
     * @return 结果
     */
    public int UpdateRecord(String appCode,CssdSterytype info);

    /**
     * 硬删除灭菌方式
     *
     * @param appCode 应用编号
     * @param no 灭菌方式ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除灭菌方式
     *
     * @param appCode 应用编号
     * @param nos 灭菌方式IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除灭菌方式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除灭菌方式
     *
     * @param appCode 应用编号
     * @param no 灭菌方式ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除灭菌方式
     *
     * @param appCode 应用编号
     * @param nos 灭菌方式IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除灭菌方式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
