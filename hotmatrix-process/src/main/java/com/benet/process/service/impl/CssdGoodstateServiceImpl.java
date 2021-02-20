package com.benet.process.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.process.mapper.CssdGoodstateMapper;
import com.benet.process.domain.CssdGoodstate;
import com.benet.process.service.ICssdGoodstateService;

/**
 * 物品状态Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdGoodstateServiceImpl implements ICssdGoodstateService 
{
    @Autowired
    private CssdGoodstateMapper cssdGoodstateMapper;

    /**
     * 查询所有物品状态列表
     *
     * @param appCode 应用编号
     * @return 物品状态集合
     */
    @Override
    public List<CssdGoodstate> getAllRecords(String appCode) {
        return cssdGoodstateMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询物品状态列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 物品状态集合
     */
    @Override
    public List<CssdGoodstate> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdGoodstateMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询物品状态列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 物品状态集合
     */
    @Override
    public List<CssdGoodstate> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdGoodstateMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询物品状态列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 物品状态集合
     */
    @Override
    public List<CssdGoodstate> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdGoodstateMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询物品状态
     *
     * @param appCode 应用编号
     * @param no 物品状态ID
     * @return 物品状态
     */
    @Override
    public CssdGoodstate getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdGoodstateMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询物品状态名称
     *
     * @param appCode 应用编号
     * @param no 物品状态ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdGoodstateMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询物品状态计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdGoodstateMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增物品状态
     *
     * @param appCode 应用编号
     * @param info 物品状态
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdGoodstate info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdGoodstateMapper.AddNewRecord(info);
    }

    /**
     * 更新物品状态
     *
     * @param appCode 应用编号
     * @param info 物品状态
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdGoodstate info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdGoodstateMapper.UpdateRecord(info);
    }

    /**
     * 硬删除物品状态
     *
     * @param appCode 应用编号
     * @param no 物品状态ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdGoodstateMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除物品状态
     *
     * @param appCode 应用编号
     * @param nos 物品状态IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdGoodstateMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除物品状态
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdGoodstateMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除物品状态
     *
     * @param appCode 应用编号
     * @param no 物品状态ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdGoodstateMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除物品状态
     *
     * @param appCode 应用编号
     * @param nos 物品状态IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdGoodstateMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除物品状态
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdGoodstateMapper.SoftDeleteByCondition(appCode,condition);
    }
}
