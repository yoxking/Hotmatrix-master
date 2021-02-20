package com.benet.wkflow.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.wkflow.mapper.FlowWorkitemflowMapper;
import com.benet.wkflow.domain.FlowWorkitemflow;
import com.benet.wkflow.service.IFlowWorkitemflowService;

/**
 * 工作项Service业务层处理
 * 
 * @author yoxking
 * @date 2021-01-31
 */
@Service
public class FlowWorkitemflowServiceImpl implements IFlowWorkitemflowService 
{
    @Autowired
    private FlowWorkitemflowMapper flowWorkitemflowMapper;

    /**
     * 查询所有工作项列表
     *
     * @param appCode 应用编号
     * @return 工作项集合
     */
    @Override
    public List<FlowWorkitemflow> getAllRecords(String appCode) {
        return flowWorkitemflowMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询工作项列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 工作项集合
     */
    @Override
    public List<FlowWorkitemflow> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flowWorkitemflowMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询工作项列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 工作项集合
     */
    @Override
    public List<FlowWorkitemflow> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flowWorkitemflowMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询工作项列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 工作项集合
     */
    @Override
    public List<FlowWorkitemflow> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flowWorkitemflowMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询工作项
     *
     * @param appCode 应用编号
     * @param no 工作项ID
     * @return 工作项
     */
    @Override
    public FlowWorkitemflow getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowWorkitemflowMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询工作项名称
     *
     * @param appCode 应用编号
     * @param no 工作项ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowWorkitemflowMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询工作项计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return flowWorkitemflowMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增工作项
     *
     * @param appCode 应用编号
     * @param info 工作项
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,FlowWorkitemflow info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return flowWorkitemflowMapper.AddNewRecord(info);
    }

    /**
     * 更新工作项
     *
     * @param appCode 应用编号
     * @param info 工作项
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,FlowWorkitemflow info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return flowWorkitemflowMapper.UpdateRecord(info);
    }

    /**
     * 硬删除工作项
     *
     * @param appCode 应用编号
     * @param no 工作项ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowWorkitemflowMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除工作项
     *
     * @param appCode 应用编号
     * @param nos 工作项IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowWorkitemflowMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除工作项
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return flowWorkitemflowMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除工作项
     *
     * @param appCode 应用编号
     * @param no 工作项ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowWorkitemflowMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除工作项
     *
     * @param appCode 应用编号
     * @param nos 工作项IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowWorkitemflowMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除工作项
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return flowWorkitemflowMapper.SoftDeleteByCondition(appCode,condition);
    }
}
