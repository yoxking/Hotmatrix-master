package com.benet.system.mapper;

import java.util.List;
import com.benet.system.domain.SysAppinfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 应用信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-03-28
 */
@Mapper
public interface SysAppinfoMapper 
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
     * 删除应用信息
     * 
     * @param id 应用信息ID
     * @return 结果
     */
    public int deleteSysAppinfoById(Long id);

    /**
     * 批量删除应用信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysAppinfoByIds(Long[] ids);
}
