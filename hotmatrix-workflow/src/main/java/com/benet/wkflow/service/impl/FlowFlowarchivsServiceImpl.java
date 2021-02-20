package com.benet.wkflow.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.wkflow.mapper.FlowFlowarchivsMapper;
import com.benet.wkflow.domain.FlowFlowarchivs;
import com.benet.wkflow.service.IFlowFlowarchivsService;

/**
 * 归档信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class FlowFlowarchivsServiceImpl implements IFlowFlowarchivsService 
{
    @Autowired
    private FlowFlowarchivsMapper flowFlowarchivsMapper;

    /**
     * 查询所有归档信息列表
     *
     * @param appCode 应用编号
     * @return 归档信息集合
     */
    @Override
    public List<FlowFlowarchivs> getAllRecords(String appCode) {
        return flowFlowarchivsMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询归档信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 归档信息集合
     */
    @Override
    public List<FlowFlowarchivs> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flowFlowarchivsMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询归档信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 归档信息集合
     */
    @Override
    public List<FlowFlowarchivs> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flowFlowarchivsMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询归档信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 归档信息集合
     */
    @Override
    public List<FlowFlowarchivs> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flowFlowarchivsMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询归档信息
     *
     * @param appCode 应用编号
     * @param no 归档信息ID
     * @return 归档信息
     */
    @Override
    public FlowFlowarchivs getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowFlowarchivsMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询归档信息名称
     *
     * @param appCode 应用编号
     * @param no 归档信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowFlowarchivsMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询归档信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return flowFlowarchivsMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增归档信息
     *
     * @param appCode 应用编号
     * @param info 归档信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,FlowFlowarchivs info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return flowFlowarchivsMapper.AddNewRecord(info);
    }

    /**
     * 更新归档信息
     *
     * @param appCode 应用编号
     * @param info 归档信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,FlowFlowarchivs info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return flowFlowarchivsMapper.UpdateRecord(info);
    }

    /**
     * 硬删除归档信息
     *
     * @param appCode 应用编号
     * @param no 归档信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowFlowarchivsMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除归档信息
     *
     * @param appCode 应用编号
     * @param nos 归档信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowFlowarchivsMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除归档信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return flowFlowarchivsMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除归档信息
     *
     * @param appCode 应用编号
     * @param no 归档信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flowFlowarchivsMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除归档信息
     *
     * @param appCode 应用编号
     * @param nos 归档信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flowFlowarchivsMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除归档信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return flowFlowarchivsMapper.SoftDeleteByCondition(appCode,condition);
    }
}
