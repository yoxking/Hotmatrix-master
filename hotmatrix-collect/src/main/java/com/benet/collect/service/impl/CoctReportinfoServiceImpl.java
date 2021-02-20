package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CoctReportinfoMapper;
import com.benet.collect.domain.CoctReportinfo;
import com.benet.collect.service.ICoctReportinfoService;

/**
 * 报告信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class CoctReportinfoServiceImpl implements ICoctReportinfoService 
{
    @Autowired
    private CoctReportinfoMapper coctReportinfoMapper;

    /**
     * 查询所有报告信息列表
     *
     * @param appCode 应用编号
     * @return 报告信息集合
     */
    @Override
    public List<CoctReportinfo> getAllRecords(String appCode) {
        return coctReportinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询报告信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 报告信息集合
     */
    @Override
    public List<CoctReportinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return coctReportinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询报告信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 报告信息集合
     */
    @Override
    public List<CoctReportinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return coctReportinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询报告信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 报告信息集合
     */
    @Override
    public List<CoctReportinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return coctReportinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询报告信息
     *
     * @param appCode 应用编号
     * @param no 报告信息ID
     * @return 报告信息
     */
    @Override
    public CoctReportinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctReportinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询报告信息名称
     *
     * @param appCode 应用编号
     * @param no 报告信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctReportinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询报告信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return coctReportinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增报告信息
     *
     * @param appCode 应用编号
     * @param info 报告信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CoctReportinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return coctReportinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新报告信息
     *
     * @param appCode 应用编号
     * @param info 报告信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CoctReportinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return coctReportinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除报告信息
     *
     * @param appCode 应用编号
     * @param no 报告信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctReportinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除报告信息
     *
     * @param appCode 应用编号
     * @param nos 报告信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctReportinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除报告信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return coctReportinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除报告信息
     *
     * @param appCode 应用编号
     * @param no 报告信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctReportinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除报告信息
     *
     * @param appCode 应用编号
     * @param nos 报告信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctReportinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除报告信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return coctReportinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
