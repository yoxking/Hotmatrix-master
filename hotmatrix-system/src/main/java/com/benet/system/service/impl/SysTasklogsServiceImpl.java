package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysTasklogsMapper;
import com.benet.system.domain.SysTasklogs;
import com.benet.system.service.ISysTasklogsService;

/**
 * 定时任务调度日志Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysTasklogsServiceImpl implements ISysTasklogsService 
{
    @Autowired
    private SysTasklogsMapper sysTasklogsMapper;

    /**
     * 查询所有定时任务调度日志列表
     *
     * @return 定时任务调度日志集合
     */
    @Override
    public List<SysTasklogs> getAllRecords() {
        return sysTasklogsMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询定时任务调度日志列表
     *
     * @param classNo 分类编号
     * @return 定时任务调度日志集合
     */
    @Override
    public List<SysTasklogs> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysTasklogsMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询定时任务调度日志列表
     *
     * @param model 分页模型
     * @return 定时任务调度日志集合
     */
    @Override
    public List<SysTasklogs> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysTasklogsMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询定时任务调度日志列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 定时任务调度日志集合
     */
    @Override
    public List<SysTasklogs> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysTasklogsMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询定时任务调度日志
     *
     * @param no 定时任务调度日志ID
     * @return 定时任务调度日志
     */
    @Override
    public SysTasklogs getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTasklogsMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询定时任务调度日志名称
     *
     * @param no 定时任务调度日志ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTasklogsMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询定时任务调度日志计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysTasklogsMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增定时任务调度日志
     *
     * @param info 定时任务调度日志
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysTasklogs info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysTasklogsMapper.AddNewRecord(info);
    }

    /**
     * 更新定时任务调度日志
     *
     * @param info 定时任务调度日志
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysTasklogs info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysTasklogsMapper.UpdateRecord(info);
    }

    /**
     * 硬删除定时任务调度日志
     *
     * @param no 定时任务调度日志ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTasklogsMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除定时任务调度日志
     *
     * @param nos 定时任务调度日志IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysTasklogsMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除定时任务调度日志
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysTasklogsMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除定时任务调度日志
     *
     * @param no 定时任务调度日志ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTasklogsMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除定时任务调度日志
     *
     * @param nos 定时任务调度日志IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysTasklogsMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除定时任务调度日志
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysTasklogsMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
