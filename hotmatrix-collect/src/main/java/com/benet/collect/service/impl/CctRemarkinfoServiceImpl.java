package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CctRemarkinfoMapper;
import com.benet.collect.domain.CctRemarkinfo;
import com.benet.collect.service.ICctRemarkinfoService;

/**
 * 评论信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-10-14
 */
@Service
public class CctRemarkinfoServiceImpl implements ICctRemarkinfoService 
{
    @Autowired
    private CctRemarkinfoMapper cctRemarkinfoMapper;

    /**
     * 查询所有评论信息列表
     *
     * @param appCode 应用编号
     * @return 评论信息集合
     */
    @Override
    public List<CctRemarkinfo> getAllRecords(String appCode) {
        return cctRemarkinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询评论信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 评论信息集合
     */
    @Override
    public List<CctRemarkinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cctRemarkinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询评论信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 评论信息集合
     */
    @Override
    public List<CctRemarkinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cctRemarkinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询评论信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 评论信息集合
     */
    @Override
    public List<CctRemarkinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cctRemarkinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询评论信息
     *
     * @param appCode 应用编号
     * @param no 评论信息ID
     * @return 评论信息
     */
    @Override
    public CctRemarkinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctRemarkinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询评论信息名称
     *
     * @param appCode 应用编号
     * @param no 评论信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctRemarkinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询评论信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cctRemarkinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增评论信息
     *
     * @param appCode 应用编号
     * @param info 评论信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CctRemarkinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cctRemarkinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新评论信息
     *
     * @param appCode 应用编号
     * @param info 评论信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CctRemarkinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cctRemarkinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除评论信息
     *
     * @param appCode 应用编号
     * @param no 评论信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctRemarkinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除评论信息
     *
     * @param appCode 应用编号
     * @param nos 评论信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctRemarkinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除评论信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cctRemarkinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除评论信息
     *
     * @param appCode 应用编号
     * @param no 评论信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cctRemarkinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除评论信息
     *
     * @param appCode 应用编号
     * @param nos 评论信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cctRemarkinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除评论信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cctRemarkinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
