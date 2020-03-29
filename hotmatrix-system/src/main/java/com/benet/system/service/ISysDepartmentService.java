package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysDepartment;

/**
 * 部门信息Service接口
 * 
 * @author yoxking
 * @date 2020-03-29
 */
public interface ISysDepartmentService 
{
    /**
     * 查询所有部门信息列表
     *
     * @return 部门信息集合
     */
    public List<SysDepartment> getAllRecords();

    /**
     * 按分类查询部门信息列表
     *
     * @param classNo 分类编号
     * @return 部门信息集合
     */
    public List<SysDepartment> getRecordsByClassNo(String classNo);

    /**
     * 分页查询部门信息列表
     *
     * @param model 分页模型
     * @return 部门信息集合
     */
    public List<SysDepartment> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询部门信息列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 部门信息集合
     */
    public List<SysDepartment> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询部门信息
     *
     * @param no 部门信息ID
     * @return 部门信息
     */
    public SysDepartment getRecordByNo(String no);

    /**
     * 查询部门信息名称
     *
     * @param no 部门信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询部门信息计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增部门信息
     *
     * @param info 部门信息
     * @return 结果
     */
    public int AddNewRecord(SysDepartment info);

    /**
     * 更新部门信息
     *
     * @param info 部门信息
     * @return 结果
     */
    public int UpdateRecord(SysDepartment info);

    /**
     * 硬删除部门信息
     *
     * @param no 部门信息ID
     * @return 结果
     */
    public int HardDeleteRecord(String no);

    /**
     * 批量硬删除部门信息
     *
     * @param nos 部门信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除部门信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除部门信息
     *
     * @param no 部门信息ID
     * @return 结果
     */
    public int SoftDeleteRecord(String no);

    /**
     * 批量软删除部门信息
     *
     * @param nos 部门信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除部门信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
