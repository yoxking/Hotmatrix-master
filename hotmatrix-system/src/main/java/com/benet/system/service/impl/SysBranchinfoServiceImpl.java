package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysBranchinfoMapper;
import com.benet.system.domain.SysBranchinfo;
import com.benet.system.service.ISysBranchinfoService;

/**
 * 分支信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-03-28
 */
@Service
public class SysBranchinfoServiceImpl implements ISysBranchinfoService 
{
    @Autowired
    private SysBranchinfoMapper sysBranchinfoMapper;

    /**
     * 查询分支信息
     * 
     * @param id 分支信息ID
     * @return 分支信息
     */
    @Override
    public SysBranchinfo selectSysBranchinfoById(Long id)
    {
        return sysBranchinfoMapper.selectSysBranchinfoById(id);
    }

    /**
     * 查询分支信息列表
     * 
     * @param sysBranchinfo 分支信息
     * @return 分支信息
     */
    @Override
    public List<SysBranchinfo> selectSysBranchinfoList(SysBranchinfo sysBranchinfo)
    {
        return sysBranchinfoMapper.selectSysBranchinfoList(sysBranchinfo);
    }

    /**
     * 新增分支信息
     * 
     * @param sysBranchinfo 分支信息
     * @return 结果
     */
    @Override
    public int insertSysBranchinfo(SysBranchinfo sysBranchinfo)
    {
        sysBranchinfo.setCreateTime(DateUtils.getNowDate());
        return sysBranchinfoMapper.insertSysBranchinfo(sysBranchinfo);
    }

    /**
     * 修改分支信息
     * 
     * @param sysBranchinfo 分支信息
     * @return 结果
     */
    @Override
    public int updateSysBranchinfo(SysBranchinfo sysBranchinfo)
    {
        sysBranchinfo.setUpdateTime(DateUtils.getNowDate());
        return sysBranchinfoMapper.updateSysBranchinfo(sysBranchinfo);
    }

    /**
     * 批量删除分支信息
     * 
     * @param ids 需要删除的分支信息ID
     * @return 结果
     */
    @Override
    public int deleteSysBranchinfoByIds(Long[] ids)
    {
        return sysBranchinfoMapper.deleteSysBranchinfoByIds(ids);
    }

    /**
     * 删除分支信息信息
     * 
     * @param id 分支信息ID
     * @return 结果
     */
    @Override
    public int deleteSysBranchinfoById(Long id)
    {
        return sysBranchinfoMapper.deleteSysBranchinfoById(id);
    }
}
