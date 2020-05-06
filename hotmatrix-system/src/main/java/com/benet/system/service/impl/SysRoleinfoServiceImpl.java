package com.benet.system.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import com.benet.system.domain.SysRolepermit;
import com.benet.system.domain.SysSuserrole;
import com.benet.system.mapper.SysRolepermitMapper;
import com.benet.system.mapper.SysSuserroleMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysRoleinfoMapper;
import com.benet.system.domain.SysRoleinfo;
import com.benet.system.service.ISysRoleinfoService;

/**
 * 角色信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysRoleinfoServiceImpl implements ISysRoleinfoService 
{
    @Autowired
    private SysRoleinfoMapper sysRoleinfoMapper;

    @Autowired
    private SysRolepermitMapper sysRolepermitMapper;

    @Autowired
    private SysSuserroleMapper sysSuserroleMapper;

    /**
     * 查询所有角色信息列表
     *
     * @return 角色信息集合
     */
    @Override
    public List<SysRoleinfo> getAllRecords() {
        return sysRoleinfoMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询角色信息列表
     *
     * @param classNo 分类编号
     * @return 角色信息集合
     */
    @Override
    public List<SysRoleinfo> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysRoleinfoMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询角色信息列表
     *
     * @param model 分页模型
     * @return 角色信息集合
     */
    @Override
    public List<SysRoleinfo> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysRoleinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询角色信息列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 角色信息集合
     */
    @Override
    public List<SysRoleinfo> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysRoleinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询角色信息
     *
     * @param no 角色信息ID
     * @return 角色信息
     */
    @Override
    public SysRoleinfo getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRoleinfoMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询角色信息名称
     *
     * @param no 角色信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRoleinfoMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询角色信息计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysRoleinfoMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增角色信息
     *
     * @param info 角色信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysRoleinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysRoleinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新角色信息
     *
     * @param info 角色信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysRoleinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysRoleinfoMapper.UpdateRecord(info);
    }

    /**
     * 更新角色用户信息
     *
     * @param roleNo 角色编号
     * @param suerNos 用户列表信息
     * @return 结果
     */
    public int UpdateSusers(String roleNo,String[] suerNos){

        int count=0;
        sysSuserroleMapper.HardDeleteByRoleNo(GlobalConfig.getAppCode(),roleNo);
        SysSuserrole sr=null;
        for (String suerNo:suerNos) {
            sr=new SysSuserrole();
            sr.setUserNo(suerNo);
            sr.setRoleNo(roleNo);
            sr.setBranchNo(GlobalConfig.getBranchNo());
            sr.setCreateBy("");
            sr.setCreateTime(DateUtils.getNowDate());
            sr.setUpdateBy("");
            sr.setUpdateTime(DateUtils.getNowDate());
            sr.setDeleteFlag("1");
            sr.setComments("");
            sr.setAppCode(GlobalConfig.getAppCode());
            sr.setVersion(1L);

            count+=sysSuserroleMapper.AddNewRecord(sr);
        }
        return count;
    }

    /**
     * 更新角色权限信息
     *
     * @param roleNo 角色编号
     * @param permitNos 权限列表信息
     * @return 结果
     */
    public int UpdatePermits(String roleNo,String[] permitNos){

        int count=0;
        sysRolepermitMapper.HardDeleteByRoleNo(GlobalConfig.getAppCode(),roleNo);
        SysRolepermit rp=null;
        for (String permitNo:permitNos) {
            rp=new SysRolepermit();
            rp.setPermitNo(permitNo);
            rp.setRoleNo(roleNo);
            rp.setBranchNo(GlobalConfig.getBranchNo());
            rp.setCreateBy("");
            rp.setCreateTime(DateUtils.getNowDate());
            rp.setUpdateBy("");
            rp.setUpdateTime(DateUtils.getNowDate());
            rp.setDeleteFlag("1");
            rp.setComments("");
            rp.setAppCode(GlobalConfig.getAppCode());
            rp.setVersion(1L);

            count+=sysRolepermitMapper.AddNewRecord(rp);
        }
        return count;
    }

    /**
     * 硬删除角色信息
     *
     * @param no 角色信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRoleinfoMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除角色信息
     *
     * @param nos 角色信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRoleinfoMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除角色信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysRoleinfoMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除角色信息
     *
     * @param no 角色信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRoleinfoMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除角色信息
     *
     * @param nos 角色信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRoleinfoMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除角色信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysRoleinfoMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 根据用户ID查询角色
     *
     * @param userNo 用户ID
     * @return 角色列表
     */
    @Override
    public List<SysRoleinfo> getRecordsByUserNo(String userNo){
        return sysRoleinfoMapper.getRecordsByUserNo(GlobalConfig.getAppCode(),userNo);
    }

    /**
     * 根据用户ID查询角色
     *
     * @param userNo 用户ID
     * @return 权限列表
     */
    public Set<String> getRoleCodesByUserNo(String userNo){

        List<String> roles = sysRoleinfoMapper.getRoleCodesByUserNo(GlobalConfig.getAppCode(),userNo);
        Set<String> rolesSet = new HashSet<>();
        for (String role : roles)
        {
            if (StringUtils.isNotEmpty(role))
            {
                rolesSet.addAll(Arrays.asList(role.trim().split(",")));
            }
        }
        return rolesSet;
    }


    /**
     * 根据用户ID查询角色标识
     *
     * @param roleNo 角色ID
     * @return 用户列表
     */
    public List<String> getSuserNosByRoleNo(String roleNo){

        return sysRoleinfoMapper.getSuserNosByRoleNo(GlobalConfig.getAppCode(),roleNo);
    }


    /**
     * 根据用户ID查询角色标识
     *
     * @param roleNo 角色ID
     * @return 权限列表
     */
    public List<String> getPermitNosByRoleNo(String roleNo){

        return sysRoleinfoMapper.getPermitNosByRoleNo(GlobalConfig.getAppCode(),roleNo);
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName 角色名称
     * @return 角色信息
     */
    @Override
    public int checkRoleNameUnique(String roleName){
        return sysRoleinfoMapper.checkRoleNameUnique(GlobalConfig.getAppCode(),roleName);
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param roleCode 角色权限
     * @return 角色信息
     */
    @Override
    public int checkRoleCodeUnique(String roleCode){
        return sysRoleinfoMapper.checkRoleCodeUnique(GlobalConfig.getAppCode(),roleCode);
    }
}
