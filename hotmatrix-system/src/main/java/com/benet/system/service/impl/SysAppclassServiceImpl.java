package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysAppclassMapper;
import com.benet.system.domain.SysAppclass;
import com.benet.system.service.ISysAppclassService;

/**
 * 应用类型Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysAppclassServiceImpl implements ISysAppclassService 
{
    @Autowired
    private SysAppclassMapper sysAppclassMapper;

    /**
     * 查询所有应用类型列表
     *
     * @return 应用类型集合
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public List<SysAppclass> getAllRecords() {
        return sysAppclassMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询应用类型列表
     *
     * @param classNo 分类编号
     * @return 应用类型集合
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public List<SysAppclass> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysAppclassMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询应用类型列表
     *
     * @param model 分页模型
     * @return 应用类型集合
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public List<SysAppclass> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysAppclassMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询应用类型列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 应用类型集合
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public List<SysAppclass> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysAppclassMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询应用类型
     *
     * @param no 应用类型ID
     * @return 应用类型
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public SysAppclass getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysAppclassMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询应用类型名称
     *
     * @param no 应用类型ID
     * @return 名称
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysAppclassMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询应用类型计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int getCountByCondition(String condition) {
        return sysAppclassMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增应用类型
     *
     * @param info 应用类型
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int AddNewRecord(SysAppclass info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysAppclassMapper.AddNewRecord(info);
    }

    /**
     * 更新应用类型
     *
     * @param info 应用类型
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int UpdateRecord(SysAppclass info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysAppclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除应用类型
     *
     * @param no 应用类型ID
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysAppclassMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除应用类型
     *
     * @param nos 应用类型IDs
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysAppclassMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除应用类型
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int HardDeleteByCondition(String condition) {
        return sysAppclassMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除应用类型
     *
     * @param no 应用类型ID
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysAppclassMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除应用类型
     *
     * @param nos 应用类型IDs
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysAppclassMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除应用类型
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int SoftDeleteByCondition(String condition) {
        return sysAppclassMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
