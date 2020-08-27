package com.benet.wkflow.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.wkflow.domain.FlwFlowbutton;

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
     * @param appCode 应用编号
     * @return 工作流程按钮集合
     */
    public List<FlwFlowbutton> getAllRecords(String appCode);

    /**
     * 按分类查询工作流程按钮列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 工作流程按钮集合
     */
    public List<FlwFlowbutton> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询工作流程按钮列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 工作流程按钮集合
     */
    public List<FlwFlowbutton> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询工作流程按钮列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 工作流程按钮集合
     */
    public List<FlwFlowbutton> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询工作流程按钮
     *
     * @param appCode 应用编号
     * @param no 工作流程按钮ID
     * @return 工作流程按钮
     */
    public FlwFlowbutton getRecordByNo(String appCode,String no);

    /**
     * 查询工作流程按钮名称
     *
     * @param appCode 应用编号
     * @param no 工作流程按钮ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询工作流程按钮计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增工作流程按钮
     *
     * @param appCode 应用编号
     * @param info 工作流程按钮
     * @return 结果
     */
    public int AddNewRecord(String appCode,FlwFlowbutton info);

    /**
     * 更新工作流程按钮
     *
     * @param appCode 应用编号
     * @param info 工作流程按钮
     * @return 结果
     */
    public int UpdateRecord(String appCode,FlwFlowbutton info);

    /**
     * 硬删除工作流程按钮
     *
     * @param appCode 应用编号
     * @param no 工作流程按钮ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除工作流程按钮
     *
     * @param appCode 应用编号
     * @param nos 工作流程按钮IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除工作流程按钮
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除工作流程按钮
     *
     * @param appCode 应用编号
     * @param no 工作流程按钮ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除工作流程按钮
     *
     * @param appCode 应用编号
     * @param nos 工作流程按钮IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除工作流程按钮
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
