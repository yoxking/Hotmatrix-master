package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CoctRegistinfoMapper;
import com.benet.collect.domain.CoctRegistinfo;
import com.benet.collect.service.ICoctRegistinfoService;

/**
 * 预约信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class CoctRegistinfoServiceImpl implements ICoctRegistinfoService 
{
    @Autowired
    private CoctRegistinfoMapper coctRegistinfoMapper;

    /**
     * 查询所有预约信息列表
     *
     * @param appCode 应用编号
     * @return 预约信息集合
     */
    @Override
    public List<CoctRegistinfo> getAllRecords(String appCode) {
        return coctRegistinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询预约信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 预约信息集合
     */
    @Override
    public List<CoctRegistinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return coctRegistinfoMapper.getRecordsByClassNo(appCode,classNo);
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
    public List<CoctRegistinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return coctRegistinfoMapper.getRecordsByPaging(appCode,model);
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
    public List<CoctRegistinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return coctRegistinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询预约信息
     *
     * @param appCode 应用编号
     * @param no 预约信息ID
     * @return 预约信息
     */
    @Override
    public CoctRegistinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctRegistinfoMapper.getRecordByNo(appCode,no);
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
            return coctRegistinfoMapper.getRecordNameByNo(appCode,no);
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
        return coctRegistinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增预约信息
     *
     * @param appCode 应用编号
     * @param info 预约信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CoctRegistinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return coctRegistinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新预约信息
     *
     * @param appCode 应用编号
     * @param info 预约信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CoctRegistinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return coctRegistinfoMapper.UpdateRecord(info);
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
            return coctRegistinfoMapper.HardDeleteByNo(appCode,no);
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
            return coctRegistinfoMapper.HardDeleteByNos(appCode,nos);
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
        return coctRegistinfoMapper.HardDeleteByCondition(appCode,condition);
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
            return coctRegistinfoMapper.SoftDeleteByNo(appCode,no);
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
            return coctRegistinfoMapper.SoftDeleteByNos(appCode,nos);
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
        return coctRegistinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
