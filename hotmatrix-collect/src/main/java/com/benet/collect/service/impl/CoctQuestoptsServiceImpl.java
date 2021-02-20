package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CoctQuestoptsMapper;
import com.benet.collect.domain.CoctQuestopts;
import com.benet.collect.service.ICoctQuestoptsService;

/**
 * 测题选项Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class CoctQuestoptsServiceImpl implements ICoctQuestoptsService 
{
    @Autowired
    private CoctQuestoptsMapper coctQuestoptsMapper;

    /**
     * 查询所有测题选项列表
     *
     * @param appCode 应用编号
     * @return 测题选项集合
     */
    @Override
    public List<CoctQuestopts> getAllRecords(String appCode) {
        return coctQuestoptsMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询测题选项列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 测题选项集合
     */
    @Override
    public List<CoctQuestopts> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return coctQuestoptsMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询测题选项列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 测题选项集合
     */
    @Override
    public List<CoctQuestopts> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return coctQuestoptsMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询测题选项列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 测题选项集合
     */
    @Override
    public List<CoctQuestopts> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return coctQuestoptsMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询测题选项
     *
     * @param appCode 应用编号
     * @param no 测题选项ID
     * @return 测题选项
     */
    @Override
    public CoctQuestopts getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctQuestoptsMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询测题选项名称
     *
     * @param appCode 应用编号
     * @param no 测题选项ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctQuestoptsMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询测题选项计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return coctQuestoptsMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增测题选项
     *
     * @param appCode 应用编号
     * @param info 测题选项
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CoctQuestopts info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return coctQuestoptsMapper.AddNewRecord(info);
    }

    /**
     * 更新测题选项
     *
     * @param appCode 应用编号
     * @param info 测题选项
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CoctQuestopts info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return coctQuestoptsMapper.UpdateRecord(info);
    }

    /**
     * 硬删除测题选项
     *
     * @param appCode 应用编号
     * @param no 测题选项ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctQuestoptsMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除测题选项
     *
     * @param appCode 应用编号
     * @param nos 测题选项IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctQuestoptsMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除测题选项
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return coctQuestoptsMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除测题选项
     *
     * @param appCode 应用编号
     * @param no 测题选项ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctQuestoptsMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除测题选项
     *
     * @param appCode 应用编号
     * @param nos 测题选项IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctQuestoptsMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除测题选项
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return coctQuestoptsMapper.SoftDeleteByCondition(appCode,condition);
    }
}
