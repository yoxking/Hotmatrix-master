package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CctRegistinfoMapper;
import com.benet.collect.domain.CctRegistinfo;
import com.benet.collect.service.ICctRegistinfoService;

/**
 * 预约信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-11-10
 */
@Service
public class CctRegistinfoServiceImpl implements ICctRegistinfoService 
{
    @Autowired
    private CctRegistinfoMapper cctRegistinfoMapper;

    /**
     * 查询所有预约信息列表
     *
     * @param appCode 应用编号
     * @return 预约信息集合
     */
    @Override
    public List<CctRegistinfo> getAllRecords(String appCode) {
        return cctRegistinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询预约信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 预约信息集合
     */
    @Override
    public List<CctRegistinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cctRegistinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询预约信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 预约信息集合
     */
    @Override
    public List<CctRegistinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cctRegistinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询预约信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 预约信息集合
     */
    @Override
    public List<CctRegistinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cctRegistinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询预约信息
     *
     * @param appCode 应用编号
     * @param no 预约信息ID
     * @return 预约信息
     */
    @Override
    public CctRegistinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctRegistinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询预约信息名称
     *
     * @param appCode 应用编号
     * @param no 预约信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctRegistinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询预约信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cctRegistinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增预约信息
     *
     * @param appCode 应用编号
     * @param info 预约信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CctRegistinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cctRegistinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新预约信息
     *
     * @param appCode 应用编号
     * @param info 预约信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CctRegistinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cctRegistinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除预约信息
     *
     * @param appCode 应用编号
     * @param no 预约信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctRegistinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除预约信息
     *
     * @param appCode 应用编号
     * @param nos 预约信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctRegistinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除预约信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cctRegistinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除预约信息
     *
     * @param appCode 应用编号
     * @param no 预约信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctRegistinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除预约信息
     *
     * @param appCode 应用编号
     * @param nos 预约信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctRegistinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除预约信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cctRegistinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
