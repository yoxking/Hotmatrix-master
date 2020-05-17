package com.benet.workflow.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.workflow.mapper.FlwFlowarchivsMapper;
import com.benet.workflow.domain.FlwFlowarchivs;
import com.benet.workflow.service.IFlwFlowarchivsService;

/**
 * 流程归档Service业务层处理
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Service
public class FlwFlowarchivsServiceImpl implements IFlwFlowarchivsService 
{
    @Autowired
    private FlwFlowarchivsMapper flwFlowarchivsMapper;

    /**
     * 查询所有【请填写功能名称】列表
     *
     * @return 【请填写功能名称】集合
     */
    @Override
    public List<FlwFlowarchivs> getAllRecords() {
        return flwFlowarchivsMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询【请填写功能名称】列表
     *
     * @param classNo 分类编号
     * @return 【请填写功能名称】集合
     */
    @Override
    public List<FlwFlowarchivs> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flwFlowarchivsMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
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
    public List<FlwFlowarchivs> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flwFlowarchivsMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
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
    public List<FlwFlowarchivs> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType) {

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
        return flwFlowarchivsMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询【请填写功能名称】
     *
     * @param no 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FlwFlowarchivs getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlowarchivsMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
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
            return flwFlowarchivsMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
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
        return flwFlowarchivsMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param info 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int AddNewRecord(FlwFlowarchivs info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return flwFlowarchivsMapper.AddNewRecord(info);
    }

    /**
     * 更新【请填写功能名称】
     *
     * @param info 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int UpdateRecord(FlwFlowarchivs info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return flwFlowarchivsMapper.UpdateRecord(info);
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
            return flwFlowarchivsMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
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
            return flwFlowarchivsMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
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
        return flwFlowarchivsMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
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
            return flwFlowarchivsMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
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
            return flwFlowarchivsMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
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
        return flwFlowarchivsMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
