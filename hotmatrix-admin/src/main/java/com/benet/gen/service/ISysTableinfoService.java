package com.benet.gen.service;

import java.util.List;
import java.util.Map;

import com.benet.common.core.pager.PagingModel;
import com.benet.gen.domain.SysTableinfo;
import org.apache.ibatis.annotations.Param;

/**
 * 代码生成业务Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysTableinfoService 
{
    /**
     * 查询所有代码生成业务列表
     *
     * @return 代码生成业务集合
     */
    public List<SysTableinfo> getAllRecords();

    /**
     * 按分类查询代码生成业务列表
     *
     * @param classNo 分类编号
     * @return 代码生成业务集合
     */
    public List<SysTableinfo> getRecordsByClassNo(String classNo);

    /**
     * 分页查询代码生成业务列表
     *
     * @param model 分页模型
     * @return 代码生成业务集合
     */
    public List<SysTableinfo> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询代码生成业务列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 代码生成业务集合
     */
    public List<SysTableinfo> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询代码生成业务
     *
     * @param no 代码生成业务ID
     * @return 代码生成业务
     */
    public SysTableinfo getRecordByNo(String no);

    /**
     * 查询代码生成业务
     *
     * @param tableName 表名
     * @return 代码生成业务
     */
    public SysTableinfo getRecordByName(String tableName);

    /**
     * 查询代码生成业务名称
     *
     * @param no 代码生成业务ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询代码生成业务计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增代码生成业务
     *
     * @param info 代码生成业务
     * @return 结果
     */
    public int AddNewRecord(SysTableinfo info);

    /**
     * 更新代码生成业务
     *
     * @param info 代码生成业务
     * @return 结果
     */
    public int UpdateRecord(SysTableinfo info);

    /**
     * 硬删除代码生成业务
     *
     * @param no 代码生成业务ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除代码生成业务
     *
     * @param nos 代码生成业务IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除代码生成业务
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除代码生成业务
     *
     * @param no 代码生成业务ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除代码生成业务
     *
     * @param nos 代码生成业务IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除代码生成业务
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);


    /**
     * 查询据库列表
     *
     * @param condition 业务信息
     * @return 数据库表集合
     */
    public List<SysTableinfo> getDbTableList(String condition);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    public List<SysTableinfo> getDbTableListByNames(String[] tableNames);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    public SysTableinfo getDbTableByName(String tableName);


    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     */
    public void importTableInfo(List<SysTableinfo> tableList);

    /**
     * 预览代码
     *
     * @param tableNo 表编号
     * @return 预览数据列表
     */
    public Map<String, String> previewCode(String tableNo);

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    public byte[] generateCode(String tableName);

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    public byte[] generateCode(String[] tableNames);

    /**
     * 修改保存参数校验
     *
     * @param tableInfo 业务信息
     */
    public void validateEdit(SysTableinfo tableInfo);
}
