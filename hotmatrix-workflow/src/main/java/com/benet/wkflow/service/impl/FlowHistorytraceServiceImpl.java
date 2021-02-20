package com.benet.wkflow.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.wkflow.mapper.FlowHistorytraceMapper;
import com.benet.wkflow.domain.FlowHistorytrace;
import com.benet.wkflow.service.IFlowHistorytraceService;

/**
 * 流程执行跟踪Service业务层处理
 * 
 * @author yoxking
 * @date 2021-01-31
 */
@Service
public class FlowHistorytraceServiceImpl implements IFlowHistorytraceService 
{
    @Autowired
    private FlowHistorytraceMapper flowHistorytraceMapper;

    /**
     * 查询所有流程执行跟踪列表
     *
     * @param appCode 应用编号
     * @return 流程执行跟踪集合
     */
    @Override
    public List<FlowHistorytrace> getAllRecords(String appCode) {
        return flowHistorytraceMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询流程执行跟踪列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 流程执行跟踪集合
     */
    @Override
    public List<FlowHistorytrace> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flowHistorytraceMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询流程执行跟踪列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 流程执行跟踪集合
     */
    @Override
    public List<FlowHistorytrace> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flowHistorytraceMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询流程执行跟踪列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 流程执行跟踪集合
     */
    @Override
    public List<FlowHistorytrace> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flowHistorytraceMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询流程执行跟踪
     *
     * @param appCode 应用编号
     * @param no 流程执行跟踪ID
     * @return 流程执行跟踪
     */
    @Override
    public FlowHistorytrace getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowHistorytraceMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询流程执行跟踪名称
     *
     * @param appCode 应用编号
     * @param no 流程执行跟踪ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowHistorytraceMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询流程执行跟踪计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return flowHistorytraceMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增流程执行跟踪
     *
     * @param appCode 应用编号
     * @param info 流程执行跟踪
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,FlowHistorytrace info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return flowHistorytraceMapper.AddNewRecord(info);
    }

    /**
     * 更新流程执行跟踪
     *
     * @param appCode 应用编号
     * @param info 流程执行跟踪
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,FlowHistorytrace info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return flowHistorytraceMapper.UpdateRecord(info);
    }

    /**
     * 硬删除流程执行跟踪
     *
     * @param appCode 应用编号
     * @param no 流程执行跟踪ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowHistorytraceMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除流程执行跟踪
     *
     * @param appCode 应用编号
     * @param nos 流程执行跟踪IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowHistorytraceMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除流程执行跟踪
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return flowHistorytraceMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除流程执行跟踪
     *
     * @param appCode 应用编号
     * @param no 流程执行跟踪ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowHistorytraceMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除流程执行跟踪
     *
     * @param appCode 应用编号
     * @param nos 流程执行跟踪IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowHistorytraceMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除流程执行跟踪
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return flowHistorytraceMapper.SoftDeleteByCondition(appCode,condition);
    }
}
