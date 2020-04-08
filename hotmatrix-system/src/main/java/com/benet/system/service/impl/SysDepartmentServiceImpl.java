package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysDepartmentMapper;
import com.benet.system.domain.SysDepartment;
import com.benet.system.service.ISysDepartmentService;

/**
 * 部门信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysDepartmentServiceImpl implements ISysDepartmentService 
{
    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    /**
     * 查询所有部门信息列表
     *
     * @return 部门信息集合
     */
    @Override
    public List<SysDepartment> getAllRecords() {
        return sysDepartmentMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询部门信息列表
     *
     * @param classNo 分类编号
     * @return 部门信息集合
     */
    @Override
    public List<SysDepartment> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysDepartmentMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询部门信息列表
     *
     * @param model 分页模型
     * @return 部门信息集合
     */
    @Override
    public List<SysDepartment> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysDepartmentMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询部门信息列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 部门信息集合
     */
    public List<SysDepartment> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysDepartmentMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询部门信息
     *
     * @param no 部门信息ID
     * @return 部门信息
     */
    @Override
    public SysDepartment getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDepartmentMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询部门信息名称
     *
     * @param no 部门信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDepartmentMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询部门信息计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysDepartmentMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增部门信息
     *
     * @param info 部门信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysDepartment info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysDepartmentMapper.AddNewRecord(info);
    }

    /**
     * 更新部门信息
     *
     * @param info 部门信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysDepartment info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysDepartmentMapper.UpdateRecord(info);
    }

    /**
     * 硬删除部门信息
     *
     * @param no 部门信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDepartmentMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除部门信息
     *
     * @param nos 部门信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysDepartmentMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除部门信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysDepartmentMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除部门信息
     *
     * @param no 部门信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDepartmentMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除部门信息
     *
     * @param nos 部门信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysDepartmentMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除部门信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysDepartmentMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
