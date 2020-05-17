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
     * @return 流程处理意见集合
     */
    @Override
    public List<FlwFlownotes> getAllRecords() {
        return flwFlownotesMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询流程处理意见列表
     *
     * @param classNo 分类编号
     * @return 流程处理意见集合
     */
    @Override
    public List<FlwFlownotes> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flwFlownotesMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询流程处理意见列表
     *
     * @param model 分页模型
     * @return 流程处理意见集合
     */
    @Override
    public List<FlwFlownotes> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flwFlownotesMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询流程处理意见列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 流程处理意见集合
     */
    @Override
    public List<FlwFlownotes> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flwFlownotesMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询流程处理意见
     *
     * @param no 流程处理意见ID
     * @return 流程处理意见
     */
    @Override
    public FlwFlownotes getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlownotesMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询流程处理意见名称
     *
     * @param no 流程处理意见ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlownotesMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询流程处理意见计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return flwFlownotesMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增流程处理意见
     *
     * @param info 流程处理意见
     * @return 结果
     */
    @Override
    public int AddNewRecord(FlwFlownotes info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return flwFlownotesMapper.AddNewRecord(info);
    }

    /**
     * 更新流程处理意见
     *
     * @param info 流程处理意见
     * @return 结果
     */
    @Override
    public int UpdateRecord(FlwFlownotes info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return flwFlownotesMapper.UpdateRecord(info);
    }

    /**
     * 硬删除流程处理意见
     *
     * @param no 流程处理意见ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlownotesMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除流程处理意见
     *
     * @param nos 流程处理意见IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwFlownotesMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除流程处理意见
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return flwFlownotesMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除流程处理意见
     *
     * @param no 流程处理意见ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlownotesMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除流程处理意见
     *
     * @param nos 流程处理意见IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwFlownotesMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除流程处理意见
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return flwFlownotesMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
