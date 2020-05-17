package com.benet.workflow.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.workflow.mapper.FlwTableformMapper;
import com.benet.workflow.domain.FlwTableform;
import com.benet.workflow.service.IFlwTableformService;

/**
 * 单设计Service业务层处理
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Service
public class FlwTableformServiceImpl implements IFlwTableformService 
{
    @Autowired
    private FlwTableformMapper flwTableformMapper;

    /**
     * 查询所有单设计列表
     *
     * @return 单设计集合
     */
    @Override
    public List<FlwTableform> getAllRecords() {
        return flwTableformMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询单设计列表
     *
     * @param classNo 分类编号
     * @return 单设计集合
     */
    @Override
    public List<FlwTableform> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flwTableformMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询单设计列表
     *
     * @param model 分页模型
     * @return 单设计集合
     */
    @Override
    public List<FlwTableform> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flwTableformMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询单设计列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 单设计集合
     */
    @Override
    public List<FlwTableform> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flwTableformMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询单设计
     *
     * @param no 单设计ID
     * @return 单设计
     */
    @Override
    public FlwTableform getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwTableformMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询单设计名称
     *
     * @param no 单设计ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwTableformMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询单设计计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return flwTableformMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增单设计
     *
     * @param info 单设计
     * @return 结果
     */
    @Override
    public int AddNewRecord(FlwTableform info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return flwTableformMapper.AddNewRecord(info);
    }

    /**
     * 更新单设计
     *
     * @param info 单设计
     * @return 结果
     */
    @Override
    public int UpdateRecord(FlwTableform info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return flwTableformMapper.UpdateRecord(info);
    }

    /**
     * 硬删除单设计
     *
     * @param no 单设计ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwTableformMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除单设计
     *
     * @param nos 单设计IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwTableformMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除单设计
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return flwTableformMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除单设计
     *
     * @param no 单设计ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwTableformMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除单设计
     *
     * @param nos 单设计IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwTableformMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除单设计
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return flwTableformMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
