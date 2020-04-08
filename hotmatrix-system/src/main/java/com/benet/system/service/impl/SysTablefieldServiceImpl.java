package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysTablefieldMapper;
import com.benet.system.domain.SysTablefield;
import com.benet.system.service.ISysTablefieldService;

/**
 * 代码生成业务字段Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysTablefieldServiceImpl implements ISysTablefieldService 
{
    @Autowired
    private SysTablefieldMapper sysTablefieldMapper;

    /**
     * 查询所有代码生成业务字段列表
     *
     * @return 代码生成业务字段集合
     */
    @Override
    public List<SysTablefield> getAllRecords() {
        return sysTablefieldMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询代码生成业务字段列表
     *
     * @param classNo 分类编号
     * @return 代码生成业务字段集合
     */
    @Override
    public List<SysTablefield> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysTablefieldMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询代码生成业务字段列表
     *
     * @param model 分页模型
     * @return 代码生成业务字段集合
     */
    @Override
    public List<SysTablefield> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysTablefieldMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询代码生成业务字段列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 代码生成业务字段集合
     */
    public List<SysTablefield> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysTablefieldMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询代码生成业务字段
     *
     * @param no 代码生成业务字段ID
     * @return 代码生成业务字段
     */
    @Override
    public SysTablefield getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTablefieldMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询代码生成业务字段名称
     *
     * @param no 代码生成业务字段ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTablefieldMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询代码生成业务字段计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysTablefieldMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增代码生成业务字段
     *
     * @param info 代码生成业务字段
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysTablefield info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysTablefieldMapper.AddNewRecord(info);
    }

    /**
     * 更新代码生成业务字段
     *
     * @param info 代码生成业务字段
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysTablefield info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysTablefieldMapper.UpdateRecord(info);
    }

    /**
     * 硬删除代码生成业务字段
     *
     * @param no 代码生成业务字段ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTablefieldMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除代码生成业务字段
     *
     * @param nos 代码生成业务字段IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysTablefieldMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除代码生成业务字段
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysTablefieldMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除代码生成业务字段
     *
     * @param no 代码生成业务字段ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTablefieldMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除代码生成业务字段
     *
     * @param nos 代码生成业务字段IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysTablefieldMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除代码生成业务字段
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysTablefieldMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
