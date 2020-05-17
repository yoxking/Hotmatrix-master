package com.benet.workflow.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.workflow.mapper.FlwWorkgroupMapper;
import com.benet.workflow.domain.FlwWorkgroup;
import com.benet.workflow.service.IFlwWorkgroupService;

/**
 * 工作组Service业务层处理
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Service
public class FlwWorkgroupServiceImpl implements IFlwWorkgroupService 
{
    @Autowired
    private FlwWorkgroupMapper flwWorkgroupMapper;

    /**
     * 查询所有工作组列表
     *
     * @return 工作组集合
     */
    @Override
    public List<FlwWorkgroup> getAllRecords() {
        return flwWorkgroupMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询工作组列表
     *
     * @param classNo 分类编号
     * @return 工作组集合
     */
    @Override
    public List<FlwWorkgroup> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flwWorkgroupMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询工作组列表
     *
     * @param model 分页模型
     * @return 工作组集合
     */
    @Override
    public List<FlwWorkgroup> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flwWorkgroupMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询工作组列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 工作组集合
     */
    @Override
    public List<FlwWorkgroup> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flwWorkgroupMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询工作组
     *
     * @param no 工作组ID
     * @return 工作组
     */
    @Override
    public FlwWorkgroup getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwWorkgroupMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询工作组名称
     *
     * @param no 工作组ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwWorkgroupMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询工作组计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return flwWorkgroupMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增工作组
     *
     * @param info 工作组
     * @return 结果
     */
    @Override
    public int AddNewRecord(FlwWorkgroup info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return flwWorkgroupMapper.AddNewRecord(info);
    }

    /**
     * 更新工作组
     *
     * @param info 工作组
     * @return 结果
     */
    @Override
    public int UpdateRecord(FlwWorkgroup info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return flwWorkgroupMapper.UpdateRecord(info);
    }

    /**
     * 硬删除工作组
     *
     * @param no 工作组ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwWorkgroupMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除工作组
     *
     * @param nos 工作组IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwWorkgroupMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除工作组
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return flwWorkgroupMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除工作组
     *
     * @param no 工作组ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwWorkgroupMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除工作组
     *
     * @param nos 工作组IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwWorkgroupMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除工作组
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return flwWorkgroupMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
