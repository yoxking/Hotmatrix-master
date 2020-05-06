package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysOrganizinfoMapper;
import com.benet.system.domain.SysOrganizinfo;
import com.benet.system.service.ISysOrganizinfoService;

/**
 * 机构信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysOrganizinfoServiceImpl implements ISysOrganizinfoService 
{
    @Autowired
    private SysOrganizinfoMapper sysOrganizinfoMapper;

    /**
     * 查询所有机构信息列表
     *
     * @return 机构信息集合
     */
    @Override
    public List<SysOrganizinfo> getAllRecords() {
        return sysOrganizinfoMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询机构信息列表
     *
     * @param classNo 分类编号
     * @return 机构信息集合
     */
    @Override
    public List<SysOrganizinfo> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysOrganizinfoMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询机构信息列表
     *
     * @param model 分页模型
     * @return 机构信息集合
     */
    @Override
    public List<SysOrganizinfo> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysOrganizinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询机构信息列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 机构信息集合
     */
    @Override
    public List<SysOrganizinfo> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysOrganizinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询机构信息
     *
     * @param no 机构信息ID
     * @return 机构信息
     */
    @Override
    public SysOrganizinfo getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOrganizinfoMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询机构信息名称
     *
     * @param no 机构信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOrganizinfoMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询机构信息计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysOrganizinfoMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增机构信息
     *
     * @param info 机构信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysOrganizinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysOrganizinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新机构信息
     *
     * @param info 机构信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysOrganizinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysOrganizinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除机构信息
     *
     * @param no 机构信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOrganizinfoMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除机构信息
     *
     * @param nos 机构信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysOrganizinfoMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除机构信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysOrganizinfoMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除机构信息
     *
     * @param no 机构信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOrganizinfoMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除机构信息
     *
     * @param nos 机构信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysOrganizinfoMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除机构信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysOrganizinfoMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
