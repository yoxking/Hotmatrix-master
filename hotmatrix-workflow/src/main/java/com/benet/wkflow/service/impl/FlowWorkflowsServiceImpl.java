package com.benet.wkflow.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.wkflow.mapper.FlowWorkflowsMapper;
import com.benet.wkflow.domain.FlowWorkflows;
import com.benet.wkflow.service.IFlowWorkflowsService;

/**
 * 工作流Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class FlowWorkflowsServiceImpl implements IFlowWorkflowsService 
{
    @Autowired
    private FlowWorkflowsMapper flowWorkflowsMapper;

    /**
     * 查询所有工作流列表
     *
     * @param appCode 应用编号
     * @return 工作流集合
     */
    @Override
    public List<FlowWorkflows> getAllRecords(String appCode) {
        return flowWorkflowsMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询工作流列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 工作流集合
     */
    @Override
    public List<FlowWorkflows> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flowWorkflowsMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询工作流列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 工作流集合
     */
    @Override
    public List<FlowWorkflows> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flowWorkflowsMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询工作流列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 工作流集合
     */
    @Override
    public List<FlowWorkflows> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

        PagingModel model = new PagingModel();
        model.setPageIndex((pageIndex-1) * pageSize);
        model.setPageSize(pageSize);
        model.setCondition(condition);
        if (StringUtils.isEmpty(orderField)) {
            model.setOrderField("id");
        } else {
            model.setOrderField(orderField);
        }
        if (StringUtils.isEmpty(orderType)) {
            model.setOrderType("Asc");
        } else {
            model.setOrderType(orderType);
        }
        return flowWorkflowsMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询工作流
     *
     * @param appCode 应用编号
     * @param no 工作流ID
     * @return 工作流
     */
    @Override
    public FlowWorkflows getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowWorkflowsMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询工作流名称
     *
     * @param appCode 应用编号
     * @param no 工作流ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowWorkflowsMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询工作流计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return flowWorkflowsMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增工作流
     *
     * @param appCode 应用编号
     * @param info 工作流
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,FlowWorkflows info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return flowWorkflowsMapper.AddNewRecord(info);
    }

    /**
     * 更新工作流
     *
     * @param appCode 应用编号
     * @param info 工作流
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,FlowWorkflows info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return flowWorkflowsMapper.UpdateRecord(info);
    }

    /**
     * 硬删除工作流
     *
     * @param appCode 应用编号
     * @param no 工作流ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowWorkflowsMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除工作流
     *
     * @param appCode 应用编号
     * @param nos 工作流IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowWorkflowsMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除工作流
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return flowWorkflowsMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除工作流
     *
     * @param appCode 应用编号
     * @param no 工作流ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowWorkflowsMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除工作流
     *
     * @param appCode 应用编号
     * @param nos 工作流IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowWorkflowsMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除工作流
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return flowWorkflowsMapper.SoftDeleteByCondition(appCode,condition);
    }
}
