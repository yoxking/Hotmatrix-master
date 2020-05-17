package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysRenterinfoMapper;
import com.benet.system.domain.SysRenterinfo;
import com.benet.system.service.ISysRenterinfoService;

/**
 * 租户信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysRenterinfoServiceImpl implements ISysRenterinfoService 
{
    @Autowired
    private SysRenterinfoMapper sysRenterinfoMapper;

    /**
     * 查询所有租户信息列表
     *
     * @return 租户信息集合
     */
    @Override
    public List<SysRenterinfo> getAllRecords() {
        return sysRenterinfoMapper.getAllRecords();
    }

    /**
     * 按分类查询租户信息列表
     *
     * @param classNo 分类编号
     * @return 租户信息集合
     */
    @Override
    public List<SysRenterinfo> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysRenterinfoMapper.getRecordsByClassNo(classNo);
        }
        return null;
    }

    /**
     * 分页查询租户信息列表
     *
     * @param model 分页模型
     * @return 租户信息集合
     */
    @Override
    public List<SysRenterinfo> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysRenterinfoMapper.getRecordsByPaging(model);
        }
        return null;
    }


    /**
     * 分页查询租户信息列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 租户信息集合
     */
    @Override
    public List<SysRenterinfo> getRecordsByPaging(int pageIndex, int pageSize, String condition, String orderField, String orderType) {

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
        return sysRenterinfoMapper.getRecordsByPaging(model);
    }

    /**
     * 查询租户信息
     *
     * @param no 租户信息ID
     * @return 租户信息
     */
    @Override
    public SysRenterinfo getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRenterinfoMapper.getRecordByNo(no);
        }
        return null;
    }

    /**
     * 查询租户信息名称
     *
     * @param no 租户信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRenterinfoMapper.getRecordNameByNo(no);
        }
        return null;
    }

    /**
     * 查询租户信息计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysRenterinfoMapper.getCountByCondition(condition);
    }

    /**
     * 新增租户信息
     *
     * @param info 租户信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysRenterinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysRenterinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新租户信息
     *
     * @param info 租户信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysRenterinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysRenterinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除租户信息
     *
     * @param no 租户信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRenterinfoMapper.HardDeleteByNo(no);
        }
        return 0;
    }

    /**
     * 批量硬删除租户信息
     *
     * @param nos 租户信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRenterinfoMapper.HardDeleteByNos(nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除租户信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysRenterinfoMapper.HardDeleteByCondition(condition);
    }

    /**
     * 软删除租户信息
     *
     * @param no 租户信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRenterinfoMapper.SoftDeleteByNo(no);
        }
        return 0;
    }

    /**
     * 批量软删除租户信息
     *
     * @param nos 租户信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRenterinfoMapper.SoftDeleteByNos(nos);
        }
        return 0;
    }

    /**
     * 按条件软删除租户信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysRenterinfoMapper.SoftDeleteByCondition(condition);
    }
}
