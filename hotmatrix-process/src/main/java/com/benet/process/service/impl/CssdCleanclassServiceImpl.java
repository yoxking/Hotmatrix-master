package com.benet.process.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.process.mapper.CssdCleanclassMapper;
import com.benet.process.domain.CssdCleanclass;
import com.benet.process.service.ICssdCleanclassService;

/**
 * 清洗程序类型Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdCleanclassServiceImpl implements ICssdCleanclassService 
{
    @Autowired
    private CssdCleanclassMapper cssdCleanclassMapper;

    /**
     * 查询所有清洗程序类型列表
     *
     * @param appCode 应用编号
     * @return 清洗程序类型集合
     */
    @Override
    public List<CssdCleanclass> getAllRecords(String appCode) {
        return cssdCleanclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询清洗程序类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 清洗程序类型集合
     */
    @Override
    public List<CssdCleanclass> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdCleanclassMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询清洗程序类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 清洗程序类型集合
     */
    @Override
    public List<CssdCleanclass> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdCleanclassMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询清洗程序类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 清洗程序类型集合
     */
    @Override
    public List<CssdCleanclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdCleanclassMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询清洗程序类型
     *
     * @param appCode 应用编号
     * @param no 清洗程序类型ID
     * @return 清洗程序类型
     */
    @Override
    public CssdCleanclass getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdCleanclassMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询清洗程序类型名称
     *
     * @param appCode 应用编号
     * @param no 清洗程序类型ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdCleanclassMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询清洗程序类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdCleanclassMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增清洗程序类型
     *
     * @param appCode 应用编号
     * @param info 清洗程序类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdCleanclass info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdCleanclassMapper.AddNewRecord(info);
    }

    /**
     * 更新清洗程序类型
     *
     * @param appCode 应用编号
     * @param info 清洗程序类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdCleanclass info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdCleanclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除清洗程序类型
     *
     * @param appCode 应用编号
     * @param no 清洗程序类型ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdCleanclassMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除清洗程序类型
     *
     * @param appCode 应用编号
     * @param nos 清洗程序类型IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdCleanclassMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除清洗程序类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdCleanclassMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除清洗程序类型
     *
     * @param appCode 应用编号
     * @param no 清洗程序类型ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdCleanclassMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除清洗程序类型
     *
     * @param appCode 应用编号
     * @param nos 清洗程序类型IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdCleanclassMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除清洗程序类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdCleanclassMapper.SoftDeleteByCondition(appCode,condition);
    }
}
