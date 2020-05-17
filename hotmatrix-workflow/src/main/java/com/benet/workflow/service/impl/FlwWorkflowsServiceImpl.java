package com.benet.workflow.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.workflow.mapper.FlwWorkflowsMapper;
import com.benet.workflow.domain.FlwWorkflows;
import com.benet.workflow.service.IFlwWorkflowsService;

/**
 * 工作流Service业务层处理
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Service
public class FlwWorkflowsServiceImpl implements IFlwWorkflowsService 
{
    @Autowired
    private FlwWorkflowsMapper flwWorkflowsMapper;

    /**
     * 查询所有工作流列表
     *
     * @return 工作流集合
     */
    @Override
    public List<FlwWorkflows> getAllRecords() {
        return flwWorkflowsMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询工作流列表
     *
     * @param classNo 分类编号
     * @return 工作流集合
     */
    @Override
    public List<FlwWorkflows> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flwWorkflowsMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询工作流列表
     *
     * @param model 分页模型
     * @return 工作流集合
     */
    @Override
    public List<FlwWorkflows> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flwWorkflowsMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询工作流列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 工作流集合
     */
    @Override
    public List<FlwWorkflows> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType) {

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
        return flwWorkflowsMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询工作流
     *
     * @param no 工作流ID
     * @return 工作流
     */
    @Override
    public FlwWorkflows getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwWorkflowsMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询工作流名称
     *
     * @param no 工作流ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwWorkflowsMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询工作流计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return flwWorkflowsMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增工作流
     *
     * @param info 工作流
     * @return 结果
     */
    @Override
    public int AddNewRecord(FlwWorkflows info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return flwWorkflowsMapper.AddNewRecord(info);
    }

    /**
     * 更新工作流
     *
     * @param info 工作流
     * @return 结果
     */
    @Override
    public int UpdateRecord(FlwWorkflows info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return flwWorkflowsMapper.UpdateRecord(info);
    }

    /**
     * 硬删除工作流
     *
     * @param no 工作流ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwWorkflowsMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除工作流
     *
     * @param nos 工作流IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwWorkflowsMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除工作流
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return flwWorkflowsMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除工作流
     *
     * @param no 工作流ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwWorkflowsMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除工作流
     *
     * @param nos 工作流IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwWorkflowsMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除工作流
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return flwWorkflowsMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
