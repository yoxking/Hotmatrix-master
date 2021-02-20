package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CoctPaperclassMapper;
import com.benet.collect.domain.CoctPaperclass;
import com.benet.collect.service.ICoctPaperclassService;

/**
 * 问卷类型Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class CoctPaperclassServiceImpl implements ICoctPaperclassService 
{
    @Autowired
    private CoctPaperclassMapper coctPaperclassMapper;

    /**
     * 查询所有问卷类型列表
     *
     * @param appCode 应用编号
     * @return 问卷类型集合
     */
    @Override
    public List<CoctPaperclass> getAllRecords(String appCode) {
        return coctPaperclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询问卷类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 问卷类型集合
     */
    @Override
    public List<CoctPaperclass> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return coctPaperclassMapper.getRecordsByClassNo(appCode,classNo);
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
    public List<CoctPaperclass> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return coctPaperclassMapper.getRecordsByPaging(appCode,model);
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
    public List<CoctPaperclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return coctPaperclassMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询问卷类型
     *
     * @param appCode 应用编号
     * @param no 问卷类型ID
     * @return 问卷类型
     */
    @Override
    public CoctPaperclass getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctPaperclassMapper.getRecordByNo(appCode,no);
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
            return coctPaperclassMapper.getRecordNameByNo(appCode,no);
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
        return coctPaperclassMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增问卷类型
     *
     * @param appCode 应用编号
     * @param info 问卷类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CoctPaperclass info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return coctPaperclassMapper.AddNewRecord(info);
    }

    /**
     * 更新问卷类型
     *
     * @param appCode 应用编号
     * @param info 问卷类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CoctPaperclass info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return coctPaperclassMapper.UpdateRecord(info);
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
            return coctPaperclassMapper.HardDeleteByNo(appCode,no);
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
            return coctPaperclassMapper.HardDeleteByNos(appCode,nos);
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
        return coctPaperclassMapper.HardDeleteByCondition(appCode,condition);
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
            return coctPaperclassMapper.SoftDeleteByNo(appCode,no);
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
            return coctPaperclassMapper.SoftDeleteByNos(appCode,nos);
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
        return coctPaperclassMapper.SoftDeleteByCondition(appCode,condition);
    }
}
