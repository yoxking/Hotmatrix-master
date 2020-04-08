package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysContzclassMapper;
import com.benet.system.domain.SysContzclass;
import com.benet.system.service.ISysContzclassService;

/**
 * 内容类型Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysContzclassServiceImpl implements ISysContzclassService 
{
    @Autowired
    private SysContzclassMapper sysContzclassMapper;

    /**
     * 查询所有内容类型列表
     *
     * @return 内容类型集合
     */
    @Override
    public List<SysContzclass> getAllRecords() {
        return sysContzclassMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询内容类型列表
     *
     * @param classNo 分类编号
     * @return 内容类型集合
     */
    @Override
    public List<SysContzclass> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysContzclassMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询内容类型列表
     *
     * @param model 分页模型
     * @return 内容类型集合
     */
    @Override
    public List<SysContzclass> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysContzclassMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询内容类型列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 内容类型集合
     */
    public List<SysContzclass> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysContzclassMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询内容类型
     *
     * @param no 内容类型ID
     * @return 内容类型
     */
    @Override
    public SysContzclass getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysContzclassMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询内容类型名称
     *
     * @param no 内容类型ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysContzclassMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询内容类型计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysContzclassMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增内容类型
     *
     * @param info 内容类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysContzclass info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysContzclassMapper.AddNewRecord(info);
    }

    /**
     * 更新内容类型
     *
     * @param info 内容类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysContzclass info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysContzclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除内容类型
     *
     * @param no 内容类型ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysContzclassMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除内容类型
     *
     * @param nos 内容类型IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysContzclassMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除内容类型
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysContzclassMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除内容类型
     *
     * @param no 内容类型ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysContzclassMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除内容类型
     *
     * @param nos 内容类型IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysContzclassMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除内容类型
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysContzclassMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
