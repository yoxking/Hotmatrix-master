package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CctPaperclassMapper;
import com.benet.collect.domain.CctPaperclass;
import com.benet.collect.service.ICctPaperclassService;

/**
 * 问卷类型Service业务层处理
 * 
 * @author yoxking
 * @date 2020-11-10
 */
@Service
public class CctPaperclassServiceImpl implements ICctPaperclassService 
{
    @Autowired
    private CctPaperclassMapper cctPaperclassMapper;

    /**
     * 查询所有问卷类型列表
     *
     * @param appCode 应用编号
     * @return 问卷类型集合
     */
    @Override
    public List<CctPaperclass> getAllRecords(String appCode) {
        return cctPaperclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询问卷类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 问卷类型集合
     */
    @Override
    public List<CctPaperclass> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cctPaperclassMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询问卷类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 问卷类型集合
     */
    @Override
    public List<CctPaperclass> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cctPaperclassMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询问卷类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 问卷类型集合
     */
    @Override
    public List<CctPaperclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cctPaperclassMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询问卷类型
     *
     * @param appCode 应用编号
     * @param no 问卷类型ID
     * @return 问卷类型
     */
    @Override
    public CctPaperclass getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctPaperclassMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询问卷类型名称
     *
     * @param appCode 应用编号
     * @param no 问卷类型ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctPaperclassMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询问卷类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cctPaperclassMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增问卷类型
     *
     * @param appCode 应用编号
     * @param info 问卷类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CctPaperclass info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cctPaperclassMapper.AddNewRecord(info);
    }

    /**
     * 更新问卷类型
     *
     * @param appCode 应用编号
     * @param info 问卷类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CctPaperclass info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cctPaperclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除问卷类型
     *
     * @param appCode 应用编号
     * @param no 问卷类型ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctPaperclassMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除问卷类型
     *
     * @param appCode 应用编号
     * @param nos 问卷类型IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctPaperclassMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除问卷类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cctPaperclassMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除问卷类型
     *
     * @param appCode 应用编号
     * @param no 问卷类型ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctPaperclassMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除问卷类型
     *
     * @param appCode 应用编号
     * @param nos 问卷类型IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctPaperclassMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除问卷类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cctPaperclassMapper.SoftDeleteByCondition(appCode,condition);
    }
}
