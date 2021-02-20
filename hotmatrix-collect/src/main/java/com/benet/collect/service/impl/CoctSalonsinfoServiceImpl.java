package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CoctSalonsinfoMapper;
import com.benet.collect.domain.CoctSalonsinfo;
import com.benet.collect.service.ICoctSalonsinfoService;

/**
 * 沙龙信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class CoctSalonsinfoServiceImpl implements ICoctSalonsinfoService 
{
    @Autowired
    private CoctSalonsinfoMapper coctSalonsinfoMapper;

    /**
     * 查询所有沙龙信息列表
     *
     * @param appCode 应用编号
     * @return 沙龙信息集合
     */
    @Override
    public List<CoctSalonsinfo> getAllRecords(String appCode) {
        return coctSalonsinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询沙龙信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 沙龙信息集合
     */
    @Override
    public List<CoctSalonsinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return coctSalonsinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询沙龙信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 沙龙信息集合
     */
    @Override
    public List<CoctSalonsinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return coctSalonsinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询沙龙信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 沙龙信息集合
     */
    @Override
    public List<CoctSalonsinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return coctSalonsinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询沙龙信息
     *
     * @param appCode 应用编号
     * @param no 沙龙信息ID
     * @return 沙龙信息
     */
    @Override
    public CoctSalonsinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctSalonsinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询沙龙信息名称
     *
     * @param appCode 应用编号
     * @param no 沙龙信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctSalonsinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询沙龙信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return coctSalonsinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增沙龙信息
     *
     * @param appCode 应用编号
     * @param info 沙龙信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CoctSalonsinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return coctSalonsinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新沙龙信息
     *
     * @param appCode 应用编号
     * @param info 沙龙信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CoctSalonsinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return coctSalonsinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除沙龙信息
     *
     * @param appCode 应用编号
     * @param no 沙龙信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctSalonsinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除沙龙信息
     *
     * @param appCode 应用编号
     * @param nos 沙龙信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctSalonsinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除沙龙信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return coctSalonsinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除沙龙信息
     *
     * @param appCode 应用编号
     * @param no 沙龙信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctSalonsinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除沙龙信息
     *
     * @param appCode 应用编号
     * @param nos 沙龙信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctSalonsinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除沙龙信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return coctSalonsinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
