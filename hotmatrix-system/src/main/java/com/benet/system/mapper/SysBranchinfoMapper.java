package com.benet.system.mapper;

import java.util.List;
import com.benet.system.domain.SysBranchinfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分支信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-03-28
 */
@Mapper
public interface SysBranchinfoMapper 
{
    /**
     * 查询分支信息
     * 
     * @param id 分支信息ID
     * @return 分支信息
     */
    public SysBranchinfo selectSysBranchinfoById(Long id);

    /**
     * 查询分支信息列表
     * 
     * @param sysBranchinfo 分支信息
     * @return 分支信息集合
     */
    public List<SysBranchinfo> selectSysBranchinfoList(SysBranchinfo sysBranchinfo);

    /**
     * 新增分支信息
     * 
     * @param sysBranchinfo 分支信息
     * @return 结果
     */
    public int insertSysBranchinfo(SysBranchinfo sysBranchinfo);

    /**
     * 修改分支信息
     * 
     * @param sysBranchinfo 分支信息
     * @return 结果
     */
    public int updateSysBranchinfo(SysBranchinfo sysBranchinfo);

    /**
     * 删除分支信息
     * 
     * @param id 分支信息ID
     * @return 结果
     */
    public int deleteSysBranchinfoById(Long id);

    /**
     * 批量删除分支信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysBranchinfoByIds(Long[] ids);
}
