package com.benet.workflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.workflow.domain.FlwFlowbutton;

/**
 * 工作流程按钮Service接口
 * 
 * @author yoxking
 * @date 2020-05-17
 */
public interface IFlwFlowbuttonService 
{
    /**
     * 查询所有工作流程按钮列表
     *
     * @return 工作流程按钮集合
     */
    public List<FlwFlowbutton> getAllRecords();

    /**
     * 按分类查询工作流程按钮列表
     *
     * @param classNo 分类编号
     * @return 工作流程按钮集合
     */
    public List<FlwFlowbutton> getRecordsByClassNo(String classNo);

    /**
     * 分页查询工作流程按钮列表
     *
     * @param model 分页模型
     * @return 工作流程按钮集合
     */
    public List<FlwFlowbutton> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询工作流程按钮列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 工作流程按钮集合
     */
    public List<FlwFlowbutton> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询工作流程按钮
     *
     * @param no 工作流程按钮ID
     * @return 工作流程按钮
     */
    public FlwFlowbutton getRecordByNo(String no);

    /**
     * 查询工作流程按钮名称
     *
     * @param no 工作流程按钮ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询工作流程按钮计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增工作流程按钮
     *
     * @param info 工作流程按钮
     * @return 结果
     */
    public int AddNewRecord(FlwFlowbutton info);

    /**
     * 更新工作流程按钮
     *
     * @param info 工作流程按钮
     * @return 结果
     */
    public int UpdateRecord(FlwFlowbutton info);

    /**
     * 硬删除工作流程按钮
     *
     * @param no 工作流程按钮ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除工作流程按钮
     *
     * @param nos 工作流程按钮IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除工作流程按钮
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除工作流程按钮
     *
     * @param no 工作流程按钮ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除工作流程按钮
     *
     * @param nos 工作流程按钮IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除工作流程按钮
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);
}
