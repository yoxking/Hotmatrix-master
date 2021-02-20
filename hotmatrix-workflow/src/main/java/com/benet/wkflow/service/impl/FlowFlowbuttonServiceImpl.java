package com.benet.wkflow.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.wkflow.mapper.FlowFlowbuttonMapper;
import com.benet.wkflow.domain.FlowFlowbutton;
import com.benet.wkflow.service.IFlowFlowbuttonService;

/**
 * 工作流程按钮Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class FlowFlowbuttonServiceImpl implements IFlowFlowbuttonService 
{
    @Autowired
    private FlowFlowbuttonMapper flowFlowbuttonMapper;

    /**
     * 查询所有工作流程按钮列表
     *
     * @param appCode 应用编号
     * @return 工作流程按钮集合
     */
    @Override
    public List<FlowFlowbutton> getAllRecords(String appCode) {
        return flowFlowbuttonMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询工作流程按钮列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 工作流程按钮集合
     */
    @Override
    public List<FlowFlowbutton> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flowFlowbuttonMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询工作流程按钮列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 工作流程按钮集合
     */
    @Override
    public List<FlowFlowbutton> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flowFlowbuttonMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询工作流程按钮列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 工作流程按钮集合
     */
    @Override
    public List<FlowFlowbutton> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flowFlowbuttonMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询工作流程按钮
     *
     * @param appCode 应用编号
     * @param no 工作流程按钮ID
     * @return 工作流程按钮
     */
    @Override
    public FlowFlowbutton getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowFlowbuttonMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询工作流程按钮名称
     *
     * @param appCode 应用编号
     * @param no 工作流程按钮ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowFlowbuttonMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询工作流程按钮计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return flowFlowbuttonMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增工作流程按钮
     *
     * @param appCode 应用编号
     * @param info 工作流程按钮
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,FlowFlowbutton info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return flowFlowbuttonMapper.AddNewRecord(info);
    }

    /**
     * 更新工作流程按钮
     *
     * @param appCode 应用编号
     * @param info 工作流程按钮
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,FlowFlowbutton info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return flowFlowbuttonMapper.UpdateRecord(info);
    }

    /**
     * 硬删除工作流程按钮
     *
     * @param appCode 应用编号
     * @param no 工作流程按钮ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowFlowbuttonMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除工作流程按钮
     *
     * @param appCode 应用编号
     * @param nos 工作流程按钮IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowFlowbuttonMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除工作流程按钮
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return flowFlowbuttonMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除工作流程按钮
     *
     * @param appCode 应用编号
     * @param no 工作流程按钮ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowFlowbuttonMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除工作流程按钮
     *
     * @param appCode 应用编号
     * @param nos 工作流程按钮IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowFlowbuttonMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除工作流程按钮
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return flowFlowbuttonMapper.SoftDeleteByCondition(appCode,condition);
    }
}
