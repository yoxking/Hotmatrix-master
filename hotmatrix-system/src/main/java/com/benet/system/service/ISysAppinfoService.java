package com.benet.system.service;

import java.util.List;
import com.benet.system.domain.SysAppinfo;

/**
 * 应用信息Service接口
 * 
 * @author yoxking
 * @date 2020-03-28
 */
public interface ISysAppinfoService 
{
    /**
     * 查询应用信息
     * 
     * @param id 应用信息ID
     * @return 应用信息
     */
    public SysAppinfo selectSysAppinfoById(Long id);

    /**
     * 查询应用信息列表
     * 
     * @param sysAppinfo 应用信息
     * @return 应用信息集合
     */
    public List<SysAppinfo> selectSysAppinfoList(SysAppinfo sysAppinfo);

    /**
     * 新增应用信息
     * 
     * @param sysAppinfo 应用信息
     * @return 结果
     */
    public int insertSysAppinfo(SysAppinfo sysAppinfo);

    /**
     * 修改应用信息
     * 
     * @param sysAppinfo 应用信息
     * @return 结果
     */
    public int updateSysAppinfo(SysAppinfo sysAppinfo);

    /**
     * 批量删除应用信息
     * 
     * @param ids 需要删除的应用信息ID
     * @return 结果
     */
    public int deleteSysAppinfoByIds(Long[] ids);

    /**
     * 删除应用信息信息
     * 
     * @param id 应用信息ID
     * @return 结果
     */
    public int deleteSysAppinfoById(Long id);
}
