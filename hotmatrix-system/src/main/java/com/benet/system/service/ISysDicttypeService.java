package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysDicttype;

/**
 * 字典类型Service接口
 * 
 * @author yoxking
 * @date 2020-04-23
 */
public interface ISysDicttypeService 
{
    /**
     * 查询所有字典类型列表
     *
     * @return 字典类型集合
     */
    public List<SysDicttype> getAllRecords();

    /**
     * 按分类查询字典类型列表
     *
     * @param classNo 分类编号
     * @return 字典类型集合
     */
    public List<SysDicttype> getRecordsByClassNo(String classNo);

    /**
     * 分页查询字典类型列表
     *
     * @param model 分页模型
     * @return 字典类型集合
     */
    public List<SysDicttype> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询字典类型列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 字典类型集合
     */
    public List<SysDicttype> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询字典类型
     *
     * @param no 字典类型ID
     * @return 字典类型
     */
    public SysDicttype getRecordByNo(String no);

    /**
     * 查询字典类型名称
     *
     * @param no 字典类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询字典类型计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增字典类型
     *
     * @param info 字典类型
     * @return 结果
     */
    public int AddNewRecord(SysDicttype info);

    /**
     * 更新字典类型
     *
     * @param info 字典类型
     * @return 结果
     */
    public int UpdateRecord(SysDicttype info);

    /**
     * 硬删除字典类型
     *
     * @param no 字典类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除字典类型
     *
     * @param nos 字典类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除字典类型
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除字典类型
     *
     * @param no 字典类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除字典类型
     *
     * @param nos 字典类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除字典类型
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
