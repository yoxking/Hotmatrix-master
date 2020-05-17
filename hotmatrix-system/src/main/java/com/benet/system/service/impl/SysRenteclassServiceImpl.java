package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysRenteclassMapper;
import com.benet.system.domain.SysRenteclass;
import com.benet.system.service.ISysRenteclassService;

/**
 * 租户类型Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysRenteclassServiceImpl implements ISysRenteclassService 
{
    @Autowired
    private SysRenteclassMapper sysRenteclassMapper;

    /**
     * 查询所有租户类型列表
     *
     * @return 租户类型集合
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public List<SysRenteclass> getAllRecords() {
        return sysRenteclassMapper.getAllRecords();
    }

    /**
     * 按分类查询租户类型列表
     *
     * @param classNo 分类编号
     * @return 租户类型集合
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public List<SysRenteclass> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysRenteclassMapper.getRecordsByClassNo(classNo);
        }
        return null;
    }

    /**
     * 分页查询租户类型列表
     *
     * @param model 分页模型
     * @return 租户类型集合
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public List<SysRenteclass> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysRenteclassMapper.getRecordsByPaging(model);
        }
        return null;
    }


    /**
     * 分页查询租户类型列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 租户类型集合
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public List<SysRenteclass> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType) {

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
        return sysRenteclassMapper.getRecordsByPaging(model);
    }

    /**
     * 查询租户类型
     *
     * @param no 租户类型ID
     * @return 租户类型
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public SysRenteclass getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRenteclassMapper.getRecordByNo(no);
        }
        return null;
    }

    /**
     * 查询租户类型名称
     *
     * @param no 租户类型ID
     * @return 名称
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRenteclassMapper.getRecordNameByNo(no);
        }
        return null;
    }

    /**
     * 查询租户类型计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int getCountByCondition(String condition) {
        return sysRenteclassMapper.getCountByCondition(condition);
    }

    /**
     * 新增租户类型
     *
     * @param info 租户类型
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int AddNewRecord(SysRenteclass info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setVersion(1L);
        return sysRenteclassMapper.AddNewRecord(info);
    }

    /**
     * 更新租户类型
     *
     * @param info 租户类型
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int UpdateRecord(SysRenteclass info) {
        info.setUpdateTime(DateUtils.getNowDate());
        return sysRenteclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除租户类型
     *
     * @param no 租户类型ID
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRenteclassMapper.HardDeleteByNo(no);
        }
        return 0;
    }

    /**
     * 批量硬删除租户类型
     *
     * @param nos 租户类型IDs
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRenteclassMapper.HardDeleteByNos(nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除租户类型
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int HardDeleteByCondition(String condition) {
        return sysRenteclassMapper.HardDeleteByCondition(condition);
    }

    /**
     * 软删除租户类型
     *
     * @param no 租户类型ID
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRenteclassMapper.SoftDeleteByNo(no);
        }
        return 0;
    }

    /**
     * 批量软删除租户类型
     *
     * @param nos 租户类型IDs
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRenteclassMapper.SoftDeleteByNos(nos);
        }
        return 0;
    }

    /**
     * 按条件软删除租户类型
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public int SoftDeleteByCondition(String condition) {
        return sysRenteclassMapper.SoftDeleteByCondition(condition);
    }
}
