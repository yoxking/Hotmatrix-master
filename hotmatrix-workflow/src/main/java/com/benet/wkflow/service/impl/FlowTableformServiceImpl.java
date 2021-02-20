package com.benet.wkflow.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.wkflow.mapper.FlowTableformMapper;
import com.benet.wkflow.domain.FlowTableform;
import com.benet.wkflow.service.IFlowTableformService;

/**
 * 单设计Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class FlowTableformServiceImpl implements IFlowTableformService 
{
    @Autowired
    private FlowTableformMapper flowTableformMapper;

    /**
     * 查询所有单设计列表
     *
     * @param appCode 应用编号
     * @return 单设计集合
     */
    @Override
    public List<FlowTableform> getAllRecords(String appCode) {
        return flowTableformMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询单设计列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 单设计集合
     */
    @Override
    public List<FlowTableform> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flowTableformMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询单设计列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 单设计集合
     */
    @Override
    public List<FlowTableform> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flowTableformMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询单设计列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 单设计集合
     */
    @Override
    public List<FlowTableform> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flowTableformMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询单设计
     *
     * @param appCode 应用编号
     * @param no 单设计ID
     * @return 单设计
     */
    @Override
    public FlowTableform getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowTableformMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询单设计名称
     *
     * @param appCode 应用编号
     * @param no 单设计ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowTableformMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询单设计计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return flowTableformMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增单设计
     *
     * @param appCode 应用编号
     * @param info 单设计
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,FlowTableform info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return flowTableformMapper.AddNewRecord(info);
    }

    /**
     * 更新单设计
     *
     * @param appCode 应用编号
     * @param info 单设计
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,FlowTableform info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return flowTableformMapper.UpdateRecord(info);
    }

    /**
     * 硬删除单设计
     *
     * @param appCode 应用编号
     * @param no 单设计ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowTableformMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除单设计
     *
     * @param appCode 应用编号
     * @param nos 单设计IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowTableformMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除单设计
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return flowTableformMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除单设计
     *
     * @param appCode 应用编号
     * @param no 单设计ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowTableformMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除单设计
     *
     * @param appCode 应用编号
     * @param nos 单设计IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowTableformMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除单设计
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return flowTableformMapper.SoftDeleteByCondition(appCode,condition);
    }
}
