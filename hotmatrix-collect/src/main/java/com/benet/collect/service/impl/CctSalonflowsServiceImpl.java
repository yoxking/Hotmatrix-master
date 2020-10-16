package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CctSalonflowsMapper;
import com.benet.collect.domain.CctSalonflows;
import com.benet.collect.service.ICctSalonflowsService;

/**
 * 活动过程Service业务层处理
 * 
 * @author yoxking
 * @date 2020-10-14
 */
@Service
public class CctSalonflowsServiceImpl implements ICctSalonflowsService 
{
    @Autowired
    private CctSalonflowsMapper cctSalonflowsMapper;

    /**
     * 查询所有活动过程列表
     *
     * @param appCode 应用编号
     * @return 活动过程集合
     */
    @Override
    public List<CctSalonflows> getAllRecords(String appCode) {
        return cctSalonflowsMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询活动过程列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 活动过程集合
     */
    @Override
    public List<CctSalonflows> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cctSalonflowsMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询活动过程列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 活动过程集合
     */
    @Override
    public List<CctSalonflows> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cctSalonflowsMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询活动过程列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 活动过程集合
     */
    @Override
    public List<CctSalonflows> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cctSalonflowsMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询活动过程
     *
     * @param appCode 应用编号
     * @param no 活动过程ID
     * @return 活动过程
     */
    @Override
    public CctSalonflows getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctSalonflowsMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询活动过程名称
     *
     * @param appCode 应用编号
     * @param no 活动过程ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctSalonflowsMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询活动过程计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cctSalonflowsMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增活动过程
     *
     * @param appCode 应用编号
     * @param info 活动过程
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CctSalonflows info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cctSalonflowsMapper.AddNewRecord(info);
    }

    /**
     * 更新活动过程
     *
     * @param appCode 应用编号
     * @param info 活动过程
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CctSalonflows info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cctSalonflowsMapper.UpdateRecord(info);
    }

    /**
     * 硬删除活动过程
     *
     * @param appCode 应用编号
     * @param no 活动过程ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctSalonflowsMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除活动过程
     *
     * @param appCode 应用编号
     * @param nos 活动过程IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctSalonflowsMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除活动过程
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cctSalonflowsMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除活动过程
     *
     * @param appCode 应用编号
     * @param no 活动过程ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctSalonflowsMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除活动过程
     *
     * @param appCode 应用编号
     * @param nos 活动过程IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctSalonflowsMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除活动过程
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cctSalonflowsMapper.SoftDeleteByCondition(appCode,condition);
    }
}
