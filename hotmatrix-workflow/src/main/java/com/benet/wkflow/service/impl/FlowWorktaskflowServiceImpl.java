package com.benet.wkflow.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.wkflow.mapper.FlowWorktaskflowMapper;
import com.benet.wkflow.domain.FlowWorktaskflow;
import com.benet.wkflow.service.IFlowWorktaskflowService;

/**
 * 任务实例Service业务层处理
 * 
 * @author yoxking
 * @date 2021-01-31
 */
@Service
public class FlowWorktaskflowServiceImpl implements IFlowWorktaskflowService 
{
    @Autowired
    private FlowWorktaskflowMapper flowWorktaskflowMapper;

    /**
     * 查询所有任务实例列表
     *
     * @param appCode 应用编号
     * @return 任务实例集合
     */
    @Override
    public List<FlowWorktaskflow> getAllRecords(String appCode) {
        return flowWorktaskflowMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询任务实例列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 任务实例集合
     */
    @Override
    public List<FlowWorktaskflow> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flowWorktaskflowMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询任务实例列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 任务实例集合
     */
    @Override
    public List<FlowWorktaskflow> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flowWorktaskflowMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询任务实例列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 任务实例集合
     */
    @Override
    public List<FlowWorktaskflow> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flowWorktaskflowMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询任务实例
     *
     * @param appCode 应用编号
     * @param no 任务实例ID
     * @return 任务实例
     */
    @Override
    public FlowWorktaskflow getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowWorktaskflowMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询任务实例名称
     *
     * @param appCode 应用编号
     * @param no 任务实例ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowWorktaskflowMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询任务实例计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return flowWorktaskflowMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增任务实例
     *
     * @param appCode 应用编号
     * @param info 任务实例
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,FlowWorktaskflow info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return flowWorktaskflowMapper.AddNewRecord(info);
    }

    /**
     * 更新任务实例
     *
     * @param appCode 应用编号
     * @param info 任务实例
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,FlowWorktaskflow info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return flowWorktaskflowMapper.UpdateRecord(info);
    }

    /**
     * 硬删除任务实例
     *
     * @param appCode 应用编号
     * @param no 任务实例ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowWorktaskflowMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除任务实例
     *
     * @param appCode 应用编号
     * @param nos 任务实例IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowWorktaskflowMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除任务实例
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return flowWorktaskflowMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除任务实例
     *
     * @param appCode 应用编号
     * @param no 任务实例ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowWorktaskflowMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除任务实例
     *
     * @param appCode 应用编号
     * @param nos 任务实例IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowWorktaskflowMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除任务实例
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return flowWorktaskflowMapper.SoftDeleteByCondition(appCode,condition);
    }
}
