package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.utils.date.DateUtils;
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
public class SysAppinfoServiceImpl implements ISysAppinfoService 
{
    @Autowired
    private SysAppinfoMapper sysAppinfoMapper;

    /**
     * 查询应用信息
     * 
     * @param id 应用信息ID
     * @return 应用信息
     */
    @Override
    public SysAppinfo selectSysAppinfoById(Long id)
    {
        return sysAppinfoMapper.selectSysAppinfoById(id);
    }

    /**
     * 查询应用信息列表
     * 
     * @param sysAppinfo 应用信息
     * @return 应用信息
     */
    @Override
    public List<SysAppinfo> selectSysAppinfoList(SysAppinfo sysAppinfo)
    {
        return sysAppinfoMapper.selectSysAppinfoList(sysAppinfo);
    }

    /**
     * 新增应用信息
     * 
     * @param sysAppinfo 应用信息
     * @return 结果
     */
    @Override
    public int insertSysAppinfo(SysAppinfo sysAppinfo)
    {
        sysAppinfo.setCreateTime(DateUtils.getNowDate());
        return sysAppinfoMapper.insertSysAppinfo(sysAppinfo);
    }

    /**
     * 修改应用信息
     * 
     * @param sysAppinfo 应用信息
     * @return 结果
     */
    @Override
    public int updateSysAppinfo(SysAppinfo sysAppinfo)
    {
        sysAppinfo.setUpdateTime(DateUtils.getNowDate());
        return sysAppinfoMapper.updateSysAppinfo(sysAppinfo);
    }

    /**
     * 批量删除应用信息
     * 
     * @param ids 需要删除的应用信息ID
     * @return 结果
     */
    @Override
    public int deleteSysAppinfoByIds(Long[] ids)
    {
        return sysAppinfoMapper.deleteSysAppinfoByIds(ids);
    }

    /**
     * 删除应用信息信息
     * 
     * @param id 应用信息ID
     * @return 结果
     */
    @Override
    public int deleteSysAppinfoById(Long id)
    {
        return sysAppinfoMapper.deleteSysAppinfoById(id);
    }
}
