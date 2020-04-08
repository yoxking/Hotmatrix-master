package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysOperatelogsMapper;
import com.benet.system.domain.SysOperatelogs;
import com.benet.system.service.ISysOperatelogsService;

/**
 * 操作日志记录Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysOperatelogsServiceImpl implements ISysOperatelogsService 
{
    @Autowired
    private SysOperatelogsMapper sysOperatelogsMapper;

    /**
     * 查询所有操作日志记录列表
     *
     * @return 操作日志记录集合
     */
    @Override
    public List<SysOperatelogs> getAllRecords() {
        return sysOperatelogsMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询操作日志记录列表
     *
     * @param classNo 分类编号
     * @return 操作日志记录集合
     */
    @Override
    public List<SysOperatelogs> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysOperatelogsMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询操作日志记录列表
     *
     * @param model 分页模型
     * @return 操作日志记录集合
     */
    @Override
    public List<SysOperatelogs> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysOperatelogsMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询操作日志记录列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 操作日志记录集合
     */
    public List<SysOperatelogs> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysOperatelogsMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询操作日志记录
     *
     * @param no 操作日志记录ID
     * @return 操作日志记录
     */
    @Override
    public SysOperatelogs getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOperatelogsMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询操作日志记录名称
     *
     * @param no 操作日志记录ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOperatelogsMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询操作日志记录计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysOperatelogsMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增操作日志记录
     *
     * @param info 操作日志记录
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysOperatelogs info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysOperatelogsMapper.AddNewRecord(info);
    }

    /**
     * 更新操作日志记录
     *
     * @param info 操作日志记录
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysOperatelogs info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysOperatelogsMapper.UpdateRecord(info);
    }

    /**
     * 硬删除操作日志记录
     *
     * @param no 操作日志记录ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOperatelogsMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除操作日志记录
     *
     * @param nos 操作日志记录IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysOperatelogsMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除操作日志记录
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysOperatelogsMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除操作日志记录
     *
     * @param no 操作日志记录ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOperatelogsMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除操作日志记录
     *
     * @param nos 操作日志记录IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysOperatelogsMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除操作日志记录
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysOperatelogsMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
