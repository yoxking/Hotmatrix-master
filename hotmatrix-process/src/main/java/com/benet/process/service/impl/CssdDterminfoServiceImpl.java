package com.benet.process.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.process.mapper.CssdDterminfoMapper;
import com.benet.process.domain.CssdDterminfo;
import com.benet.process.service.ICssdDterminfoService;

/**
 * 有效期天数Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class CssdDterminfoServiceImpl implements ICssdDterminfoService 
{
    @Autowired
    private CssdDterminfoMapper cssdDterminfoMapper;

    /**
     * 查询所有有效期天数列表
     *
     * @param appCode 应用编号
     * @return 有效期天数集合
     */
    @Override
    public List<CssdDterminfo> getAllRecords(String appCode) {
        return cssdDterminfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询有效期天数列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 有效期天数集合
     */
    @Override
    public List<CssdDterminfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdDterminfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询有效期天数列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 有效期天数集合
     */
    @Override
    public List<CssdDterminfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdDterminfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询有效期天数列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 有效期天数集合
     */
    @Override
    public List<CssdDterminfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdDterminfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询有效期天数
     *
     * @param appCode 应用编号
     * @param no 有效期天数ID
     * @return 有效期天数
     */
    @Override
    public CssdDterminfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdDterminfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询有效期天数名称
     *
     * @param appCode 应用编号
     * @param no 有效期天数ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdDterminfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询有效期天数计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdDterminfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增有效期天数
     *
     * @param appCode 应用编号
     * @param info 有效期天数
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdDterminfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdDterminfoMapper.AddNewRecord(info);
    }

    /**
     * 更新有效期天数
     *
     * @param appCode 应用编号
     * @param info 有效期天数
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdDterminfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdDterminfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除有效期天数
     *
     * @param appCode 应用编号
     * @param no 有效期天数ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdDterminfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除有效期天数
     *
     * @param appCode 应用编号
     * @param nos 有效期天数IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdDterminfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除有效期天数
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdDterminfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除有效期天数
     *
     * @param appCode 应用编号
     * @param no 有效期天数ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdDterminfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除有效期天数
     *
     * @param appCode 应用编号
     * @param nos 有效期天数IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdDterminfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除有效期天数
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdDterminfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
