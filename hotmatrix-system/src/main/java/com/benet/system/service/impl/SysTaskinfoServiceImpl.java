package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysTaskinfoMapper;
import com.benet.system.domain.SysTaskinfo;
import com.benet.system.service.ISysTaskinfoService;

/**
 * 定时任务调度Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysTaskinfoServiceImpl implements ISysTaskinfoService 
{
    @Autowired
    private SysTaskinfoMapper sysTaskinfoMapper;

    /**
     * 查询所有定时任务调度列表
     *
     * @return 定时任务调度集合
     */
    @Override
    public List<SysTaskinfo> getAllRecords() {
        return sysTaskinfoMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询定时任务调度列表
     *
     * @param classNo 分类编号
     * @return 定时任务调度集合
     */
    @Override
    public List<SysTaskinfo> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysTaskinfoMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询定时任务调度列表
     *
     * @param model 分页模型
     * @return 定时任务调度集合
     */
    @Override
    public List<SysTaskinfo> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysTaskinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询定时任务调度列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 定时任务调度集合
     */
    public List<SysTaskinfo> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysTaskinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询定时任务调度
     *
     * @param no 定时任务调度ID
     * @return 定时任务调度
     */
    @Override
    public SysTaskinfo getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTaskinfoMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询定时任务调度名称
     *
     * @param no 定时任务调度ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTaskinfoMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询定时任务调度计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysTaskinfoMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增定时任务调度
     *
     * @param info 定时任务调度
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysTaskinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysTaskinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新定时任务调度
     *
     * @param info 定时任务调度
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysTaskinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysTaskinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除定时任务调度
     *
     * @param no 定时任务调度ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTaskinfoMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除定时任务调度
     *
     * @param nos 定时任务调度IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysTaskinfoMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除定时任务调度
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysTaskinfoMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除定时任务调度
     *
     * @param no 定时任务调度ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTaskinfoMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除定时任务调度
     *
     * @param nos 定时任务调度IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysTaskinfoMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除定时任务调度
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysTaskinfoMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
