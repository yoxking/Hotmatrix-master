package com.benet.system.service;

import java.util.List;
import java.util.Set;

import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysRoleinfo;
import org.apache.ibatis.annotations.Param;

/**
 * 角色信息Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysRoleinfoService 
{
    /**
     * 查询所有角色信息列表
     *
     * @return 角色信息集合
     */
    public List<SysRoleinfo> getAllRecords();

    /**
     * 按分类查询角色信息列表
     *
     * @param classNo 分类编号
     * @return 角色信息集合
     */
    public List<SysRoleinfo> getRecordsByClassNo(String classNo);

    /**
     * 分页查询角色信息列表
     *
     * @param model 分页模型
     * @return 角色信息集合
     */
    public List<SysRoleinfo> getRecordsByPaging(PagingModel model);

    /**
     * 分页查询角色信息列表
     *
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 角色信息集合
     */
    public List<SysRoleinfo> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询角色信息
     *
     * @param no 角色信息ID
     * @return 角色信息
     */
    public SysRoleinfo getRecordByNo(String no);

    /**
     * 查询角色信息名称
     *
     * @param no 角色信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String no);

    /**
     * 查询角色信息计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String condition);

    /**
     * 新增角色信息
     *
     * @param info 角色信息
     * @return 结果
     */
    public int AddNewRecord(SysRoleinfo info);

    /**
     * 更新角色信息
     *
     * @param info 角色信息
     * @return 结果
     */
    public int UpdateRecord(SysRoleinfo info);

    /**
     * 硬删除角色信息
     *
     * @param no 角色信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String no);

    /**
     * 批量硬删除角色信息
     *
     * @param nos 角色信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String[] nos);

    /**
     * 按条件硬删除角色信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String condition);

    /**
     * 软删除角色信息
     *
     * @param no 角色信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String no);

    /**
     * 批量软删除角色信息
     *
     * @param nos 角色信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String[] nos);

    /**
     * 按条件软删除角色信息
     *
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String condition);


    /**
     * 根据用户ID查询角色
     *
     * @param userNo 用户ID
     * @return 角色列表
     */
    public List<SysRoleinfo> getRecordsByUserNo(String userNo);


    /**
     * 根据用户ID查询角色标识
     *
     * @param userNo 用户ID
     * @return 权限列表
     */
    public Set<String> getRoleCodesByUserNo(String userNo);


    /**
     * 校验角色名称是否唯一
     *
     * @param roleName 角色名称
     * @return 角色信息
     */
    public int checkRoleNameUnique(String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleCode 角色权限
     * @return 角色信息
     */
    public int checkRoleCodeUnique(String roleCode);
}
