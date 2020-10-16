package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CctSalonsinfoMapper;
import com.benet.collect.domain.CctSalonsinfo;
import com.benet.collect.service.ICctSalonsinfoService;

/**
 * 活动信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-10-14
 */
@Service
public class CctSalonsinfoServiceImpl implements ICctSalonsinfoService 
{
    @Autowired
    private CctSalonsinfoMapper cctSalonsinfoMapper;

    /**
     * 查询所有活动信息列表
     *
     * @param appCode 应用编号
     * @return 活动信息集合
     */
    @Override
    public List<CctSalonsinfo> getAllRecords(String appCode) {
        return cctSalonsinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询活动信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 活动信息集合
     */
    @Override
    public List<CctSalonsinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cctSalonsinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询活动信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 活动信息集合
     */
    @Override
    public List<CctSalonsinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cctSalonsinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询活动信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 活动信息集合
     */
    @Override
    public List<CctSalonsinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cctSalonsinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询活动信息
     *
     * @param appCode 应用编号
     * @param no 活动信息ID
     * @return 活动信息
     */
    @Override
    public CctSalonsinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctSalonsinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询活动信息名称
     *
     * @param appCode 应用编号
     * @param no 活动信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctSalonsinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询活动信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cctSalonsinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增活动信息
     *
     * @param appCode 应用编号
     * @param info 活动信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CctSalonsinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cctSalonsinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新活动信息
     *
     * @param appCode 应用编号
     * @param info 活动信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CctSalonsinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cctSalonsinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除活动信息
     *
     * @param appCode 应用编号
     * @param no 活动信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctSalonsinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除活动信息
     *
     * @param appCode 应用编号
     * @param nos 活动信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctSalonsinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除活动信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cctSalonsinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除活动信息
     *
     * @param appCode 应用编号
     * @param no 活动信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctSalonsinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除活动信息
     *
     * @param appCode 应用编号
     * @param nos 活动信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctSalonsinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除活动信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cctSalonsinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
