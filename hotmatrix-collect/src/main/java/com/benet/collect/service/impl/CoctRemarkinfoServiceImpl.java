package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CoctRemarkinfoMapper;
import com.benet.collect.domain.CoctRemarkinfo;
import com.benet.collect.service.ICoctRemarkinfoService;

/**
 * 评论信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class CoctRemarkinfoServiceImpl implements ICoctRemarkinfoService 
{
    @Autowired
    private CoctRemarkinfoMapper coctRemarkinfoMapper;

    /**
     * 查询所有评论信息列表
     *
     * @param appCode 应用编号
     * @return 评论信息集合
     */
    @Override
    public List<CoctRemarkinfo> getAllRecords(String appCode) {
        return coctRemarkinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询评论信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 评论信息集合
     */
    @Override
    public List<CoctRemarkinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return coctRemarkinfoMapper.getRecordsByClassNo(appCode,classNo);
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
    public List<CoctRemarkinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return coctRemarkinfoMapper.getRecordsByPaging(appCode,model);
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
    public List<CoctRemarkinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return coctRemarkinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询评论信息
     *
     * @param appCode 应用编号
     * @param no 评论信息ID
     * @return 评论信息
     */
    @Override
    public CoctRemarkinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctRemarkinfoMapper.getRecordByNo(appCode,no);
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
            return coctRemarkinfoMapper.getRecordNameByNo(appCode,no);
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
        return coctRemarkinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增评论信息
     *
     * @param appCode 应用编号
     * @param info 评论信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CoctRemarkinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return coctRemarkinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新评论信息
     *
     * @param appCode 应用编号
     * @param info 评论信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CoctRemarkinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return coctRemarkinfoMapper.UpdateRecord(info);
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
            return coctRemarkinfoMapper.HardDeleteByNo(appCode,no);
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
            return coctRemarkinfoMapper.HardDeleteByNos(appCode,nos);
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
        return coctRemarkinfoMapper.HardDeleteByCondition(appCode,condition);
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
            return coctRemarkinfoMapper.SoftDeleteByNo(appCode,no);
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
            return coctRemarkinfoMapper.SoftDeleteByNos(appCode,nos);
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
        return coctRemarkinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
