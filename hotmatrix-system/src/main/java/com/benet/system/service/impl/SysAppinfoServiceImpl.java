package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysAppinfoMapper;
import com.benet.system.domain.SysAppinfo;
import com.benet.system.service.ISysAppinfoService;

/**
 * 应用信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-03-28
 */
@Service
public class SysAppinfoServiceImpl implements ISysAppinfoService {

    @Autowired
    private SysAppinfoMapper sysAppinfoMapper;

    /**
     * 查询所有信息列表
     *
     * @return 分支信息列表
     */
    @Override
    public List<SysAppinfo> getAllRecords() {
        return sysAppinfoMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询分支信息列表
     *
     * @param classNo 分类编号
     * @return 分支信息列表
     */
    @Override
    public List<SysAppinfo> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysAppinfoMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询分支信息列表
     *
     * @param model 分页模型
     * @return 分支信息列表
     */
    @Override
    public List<SysAppinfo> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            return sysAppinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }

    /**
     * 查询分支信息
     *
     * @param no 分支信息ID
     * @return 分支信息
     */
    @Override
    public SysAppinfo getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysAppinfoMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询分支信息名称
     *
     * @param no 分支信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysAppinfoMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询分支信息计数
     *
     * @param condition 查询条件
     * @return 计数
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysAppinfoMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增分支信息
     *
     * @param info 分支信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysAppinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysAppinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新分支信息
     *
     * @param info 分支信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysAppinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysAppinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除分支信息
     *
     * @param no 分支信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteRecord(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysAppinfoMapper.HardDeleteRecord(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 硬删除分支信息
     *
     * @param nos 分支信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysAppinfoMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 硬删除分支信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysAppinfoMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除分支信息
     *
     * @param no 分支信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteRecord(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysAppinfoMapper.SoftDeleteRecord(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 软删除分支信息
     *
     * @param nos 分支信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysAppinfoMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 软删除分支信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysAppinfoMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
