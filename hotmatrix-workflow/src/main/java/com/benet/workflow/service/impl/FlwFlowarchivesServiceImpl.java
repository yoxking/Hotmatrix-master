package com.benet.workflow.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.workflow.mapper.FlwFlowarchivesMapper;
import com.benet.workflow.domain.FlwFlowarchives;
import com.benet.workflow.service.IFlwFlowarchivesService;

/**
 * 流程归档Service业务层处理
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Service
public class FlwFlowarchivesServiceImpl implements IFlwFlowarchivesService 
{
    @Autowired
    private FlwFlowarchivesMapper flwFlowarchivesMapper;

    /**
     * 查询所有【请填写功能名称】列表
     *
     * @return 【请填写功能名称】集合
     */
    @Override
    public List<FlwFlowarchives> getAllRecords() {
        return flwFlowarchivesMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询【请填写功能名称】列表
     *
     * @param classNo 分类编号
     * @return 【请填写功能名称】集合
     */
    @Override
    public List<FlwFlowarchives> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flwFlowarchivesMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询【请填写功能名称】列表
     *
     * @param model 分页模型
     * @return 【请填写功能名称】集合
     */
    @Override
    public List<FlwFlowarchives> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flwFlowarchivesMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询【请填写功能名称】列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 【请填写功能名称】集合
     */
    @Override
    public List<FlwFlowarchives> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flwFlowarchivesMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询【请填写功能名称】
     *
     * @param no 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FlwFlowarchives getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlowarchivesMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询【请填写功能名称】名称
     *
     * @param no 【请填写功能名称】ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlowarchivesMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询【请填写功能名称】计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return flwFlowarchivesMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param info 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int AddNewRecord(FlwFlowarchives info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return flwFlowarchivesMapper.AddNewRecord(info);
    }

    /**
     * 更新【请填写功能名称】
     *
     * @param info 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int UpdateRecord(FlwFlowarchives info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return flwFlowarchivesMapper.UpdateRecord(info);
    }

    /**
     * 硬删除【请填写功能名称】
     *
     * @param no 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlowarchivesMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除【请填写功能名称】
     *
     * @param nos 【请填写功能名称】IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwFlowarchivesMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除【请填写功能名称】
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return flwFlowarchivesMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除【请填写功能名称】
     *
     * @param no 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlowarchivesMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除【请填写功能名称】
     *
     * @param nos 【请填写功能名称】IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwFlowarchivesMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除【请填写功能名称】
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return flwFlowarchivesMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
