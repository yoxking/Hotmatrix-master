package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysTablefield;

/**
 * 代码生成业务字段Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysTablefieldService 
{
    /**
     * 查询所有代码生成业务字段列表
     *
     * @return 代码生成业务字段集合
     */
    public List<SysTablefield> getAllRecords();

    /**
     * 按分类查询代码生成业务字段列表
     *
     * @param classNo 分类编号
     * @return 代码生成业务字段集合
     */
    public List<SysTablefield> getRecordsByClassNo(String classNo);

    /**
     * 分页查询代码生成业务字段列表
     *
     * @param model 分页模型
     * @return 代码生成业务字段集合
     */
    public List<SysTablefield> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询代码生成业务字段列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 代码生成业务字段集合
     */
    public List<SysTablefield> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询代码生成业务字段
     *
     * @param no 代码生成业务字段ID
     * @return 代码生成业务字段
     */
    public SysTablefield getRecordByNo(String no);

    /**
     * 查询代码生成业务字段名称
     *
     * @param no 代码生成业务字段ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询代码生成业务字段计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增代码生成业务字段
     *
     * @param info 代码生成业务字段
     * @return 结果
     */
    public int AddNewRecord(SysTablefield info);

    /**
     * 更新代码生成业务字段
     *
     * @param info 代码生成业务字段
     * @return 结果
     */
    public int UpdateRecord(SysTablefield info);

    /**
     * 硬删除代码生成业务字段
     *
     * @param no 代码生成业务字段ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除代码生成业务字段
     *
     * @param nos 代码生成业务字段IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除代码生成业务字段
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除代码生成业务字段
     *
     * @param no 代码生成业务字段ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除代码生成业务字段
     *
     * @param nos 代码生成业务字段IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除代码生成业务字段
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
