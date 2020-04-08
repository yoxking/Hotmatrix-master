package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysLogininforMapper;
import com.benet.system.domain.SysLogininfor;
import com.benet.system.service.ISysLogininforService;

/**
 * 系统访问记录Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysLogininforServiceImpl implements ISysLogininforService 
{
    @Autowired
    private SysLogininforMapper sysLogininforMapper;

    /**
     * 查询所有系统访问记录列表
     *
     * @return 系统访问记录集合
     */
    @Override
    public List<SysLogininfor> getAllRecords() {
        return sysLogininforMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询系统访问记录列表
     *
     * @param classNo 分类编号
     * @return 系统访问记录集合
     */
    @Override
    public List<SysLogininfor> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysLogininforMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询系统访问记录列表
     *
     * @param model 分页模型
     * @return 系统访问记录集合
     */
    @Override
    public List<SysLogininfor> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysLogininforMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询系统访问记录列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 系统访问记录集合
     */
    public List<SysLogininfor> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysLogininforMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询系统访问记录
     *
     * @param no 系统访问记录ID
     * @return 系统访问记录
     */
    @Override
    public SysLogininfor getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysLogininforMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询系统访问记录名称
     *
     * @param no 系统访问记录ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysLogininforMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询系统访问记录计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysLogininforMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增系统访问记录
     *
     * @param info 系统访问记录
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysLogininfor info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysLogininforMapper.AddNewRecord(info);
    }

    /**
     * 更新系统访问记录
     *
     * @param info 系统访问记录
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysLogininfor info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysLogininforMapper.UpdateRecord(info);
    }

    /**
     * 硬删除系统访问记录
     *
     * @param no 系统访问记录ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysLogininforMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除系统访问记录
     *
     * @param nos 系统访问记录IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysLogininforMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除系统访问记录
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysLogininforMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除系统访问记录
     *
     * @param no 系统访问记录ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysLogininforMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除系统访问记录
     *
     * @param nos 系统访问记录IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysLogininforMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除系统访问记录
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysLogininforMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
