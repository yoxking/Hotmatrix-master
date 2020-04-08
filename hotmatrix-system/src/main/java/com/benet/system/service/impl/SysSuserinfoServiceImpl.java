package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysSuserinfoMapper;
import com.benet.system.domain.SysSuserinfo;
import com.benet.system.service.ISysSuserinfoService;

/**
 * 系统用户信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysSuserinfoServiceImpl implements ISysSuserinfoService 
{
    @Autowired
    private SysSuserinfoMapper sysSuserinfoMapper;

    /**
     * 查询所有系统用户信息列表
     *
     * @return 系统用户信息集合
     */
    @Override
    public List<SysSuserinfo> getAllRecords() {
        return sysSuserinfoMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询系统用户信息列表
     *
     * @param classNo 分类编号
     * @return 系统用户信息集合
     */
    @Override
    public List<SysSuserinfo> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysSuserinfoMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询系统用户信息列表
     *
     * @param model 分页模型
     * @return 系统用户信息集合
     */
    @Override
    public List<SysSuserinfo> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysSuserinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询系统用户信息列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 系统用户信息集合
     */
    public List<SysSuserinfo> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysSuserinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询系统用户信息
     *
     * @param no 系统用户信息ID
     * @return 系统用户信息
     */
    @Override
    public SysSuserinfo getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysSuserinfoMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询系统用户信息名称
     *
     * @param no 系统用户信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysSuserinfoMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询系统用户信息计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysSuserinfoMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增系统用户信息
     *
     * @param info 系统用户信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysSuserinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysSuserinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新系统用户信息
     *
     * @param info 系统用户信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysSuserinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysSuserinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除系统用户信息
     *
     * @param no 系统用户信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysSuserinfoMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除系统用户信息
     *
     * @param nos 系统用户信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysSuserinfoMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除系统用户信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysSuserinfoMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除系统用户信息
     *
     * @param no 系统用户信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysSuserinfoMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除系统用户信息
     *
     * @param nos 系统用户信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysSuserinfoMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除系统用户信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysSuserinfoMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }


    /**
     * 通过用户名查询用户
     *
     * @param loginName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysSuserinfo getRecordByLoginName(String loginName){
        return sysSuserinfoMapper.getRecordByLoginName(GlobalConfig.getAppCode(),loginName);
    }


    /**
     * 通过手机号码查询用户
     *
     * @param telephone 手机号码
     * @return 用户对象信息
     */
    @Override
    public SysSuserinfo getRecordByTelephone(String telephone){
        return sysSuserinfoMapper.getRecordByTelephone(GlobalConfig.getAppCode(),telephone);
    }


    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    public SysSuserinfo getRecordByEmail(String email){
        return sysSuserinfoMapper.getRecordByEmail(GlobalConfig.getAppCode(),email);
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    @Override
    public int checkLoginNameUnique(String loginName){
        return sysSuserinfoMapper.checkLoginNameUnique(GlobalConfig.getAppCode(),loginName);
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param telephone 手机号码
     * @return 结果
     */
    @Override
    public int checkTelephoneUnique(String telephone){
        return sysSuserinfoMapper.checkTelephoneUnique(GlobalConfig.getAppCode(),telephone);
    }

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    @Override
    public int checkEmailUnique(String email){
        return sysSuserinfoMapper.checkEmailUnique(GlobalConfig.getAppCode(),email);
    }

    /**
     * 修改用户密码信息
     *
     * @param userNo 用户Id
     * @param password 密码
     * @return 结果
     */
    @Override
    public int ResetUserPassword(String userNo,String password){
        // return sysSuserinfoMapper.checkEmailUnique(GlobalConfig.getAppCode(),email);
        return 0;
    }
}
