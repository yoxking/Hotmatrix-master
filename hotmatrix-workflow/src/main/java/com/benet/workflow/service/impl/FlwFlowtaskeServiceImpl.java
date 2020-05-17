package com.benet.workflow.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.workflow.mapper.FlwFlowtaskeMapper;
import com.benet.workflow.domain.FlwFlowtaske;
import com.benet.workflow.service.IFlwFlowtaskeService;

/**
 * 流程任务Service业务层处理
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Service
public class FlwFlowtaskeServiceImpl implements IFlwFlowtaskeService 
{
    @Autowired
    private FlwFlowtaskeMapper flwFlowtaskeMapper;

    /**
     * 查询所有流程任务列表
     *
     * @return 流程任务集合
     */
    @Override
    public List<FlwFlowtaske> getAllRecords() {
        return flwFlowtaskeMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询流程任务列表
     *
     * @param classNo 分类编号
     * @return 流程任务集合
     */
    @Override
    public List<FlwFlowtaske> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flwFlowtaskeMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询流程任务列表
     *
     * @param model 分页模型
     * @return 流程任务集合
     */
    @Override
    public List<FlwFlowtaske> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flwFlowtaskeMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询流程任务列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 流程任务集合
     */
    @Override
    public List<FlwFlowtaske> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flwFlowtaskeMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询流程任务
     *
     * @param no 流程任务ID
     * @return 流程任务
     */
    @Override
    public FlwFlowtaske getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlowtaskeMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询流程任务名称
     *
     * @param no 流程任务ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlowtaskeMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询流程任务计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return flwFlowtaskeMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增流程任务
     *
     * @param info 流程任务
     * @return 结果
     */
    @Override
    public int AddNewRecord(FlwFlowtaske info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return flwFlowtaskeMapper.AddNewRecord(info);
    }

    /**
     * 更新流程任务
     *
     * @param info 流程任务
     * @return 结果
     */
    @Override
    public int UpdateRecord(FlwFlowtaske info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return flwFlowtaskeMapper.UpdateRecord(info);
    }

    /**
     * 硬删除流程任务
     *
     * @param no 流程任务ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlowtaskeMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除流程任务
     *
     * @param nos 流程任务IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwFlowtaskeMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除流程任务
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return flwFlowtaskeMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除流程任务
     *
     * @param no 流程任务ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwFlowtaskeMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除流程任务
     *
     * @param nos 流程任务IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwFlowtaskeMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除流程任务
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return flwFlowtaskeMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
