package com.benet.workflow.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.workflow.mapper.FlwFlownotesMapper;
import com.benet.workflow.domain.FlwFlownotes;
import com.benet.workflow.service.IFlwFlownotesService;

/**
 * 流程处理意见Service业务层处理
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Service
public class FlwFlownotesServiceImpl implements IFlwFlownotesService 
{
    @Autowired
    private FlwFlownotesMapper flwFlownotesMapper;

    /**
     * 查询所有流程处理意见列表
     *
     * @param appCode 应用编号
     * @return 流程处理意见集合
     */
    @Override
    public List<FlwFlownotes> getAllRecords(String appCode) {
        return flwFlownotesMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询流程处理意见列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 流程处理意见集合
     */
    @Override
    public List<FlwFlownotes> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flwFlownotesMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询流程处理意见列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 流程处理意见集合
     */
    @Override
    public List<FlwFlownotes> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flwFlownotesMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询流程处理意见列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 流程处理意见集合
     */
    @Override
    public List<FlwFlownotes> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flwFlownotesMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询流程处理意见
     *
     * @param appCode 应用编号
     * @param no 流程处理意见ID
     * @return 流程处理意见
     */
    @Override
    public FlwFlownotes getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlownotesMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询流程处理意见名称
     *
     * @param appCode 应用编号
     * @param no 流程处理意见ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlownotesMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询流程处理意见计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return flwFlownotesMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增流程处理意见
     *
     * @param appCode 应用编号
     * @param info 流程处理意见
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,FlwFlownotes info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return flwFlownotesMapper.AddNewRecord(info);
    }

    /**
     * 更新流程处理意见
     *
     * @param appCode 应用编号
     * @param info 流程处理意见
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,FlwFlownotes info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return flwFlownotesMapper.UpdateRecord(info);
    }

    /**
     * 硬删除流程处理意见
     *
     * @param appCode 应用编号
     * @param no 流程处理意见ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlownotesMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除流程处理意见
     *
     * @param appCode 应用编号
     * @param nos 流程处理意见IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwFlownotesMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除流程处理意见
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return flwFlownotesMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除流程处理意见
     *
     * @param appCode 应用编号
     * @param no 流程处理意见ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlownotesMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除流程处理意见
     *
     * @param appCode 应用编号
     * @param nos 流程处理意见IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwFlownotesMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除流程处理意见
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return flwFlownotesMapper.SoftDeleteByCondition(appCode,condition);
    }
}
