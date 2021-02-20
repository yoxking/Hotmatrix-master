package com.benet.process.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.process.mapper.CssdPackclassMapper;
import com.benet.process.domain.CssdPackclass;
import com.benet.process.service.ICssdPackclassService;

/**
 * 包装方式Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdPackclassServiceImpl implements ICssdPackclassService 
{
    @Autowired
    private CssdPackclassMapper cssdPackclassMapper;

    /**
     * 查询所有包装方式列表
     *
     * @param appCode 应用编号
     * @return 包装方式集合
     */
    @Override
    public List<CssdPackclass> getAllRecords(String appCode) {
        return cssdPackclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询包装方式列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 包装方式集合
     */
    @Override
    public List<CssdPackclass> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdPackclassMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询包装方式列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 包装方式集合
     */
    @Override
    public List<CssdPackclass> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdPackclassMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询包装方式列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 包装方式集合
     */
    @Override
    public List<CssdPackclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdPackclassMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询包装方式
     *
     * @param appCode 应用编号
     * @param no 包装方式ID
     * @return 包装方式
     */
    @Override
    public CssdPackclass getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdPackclassMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询包装方式名称
     *
     * @param appCode 应用编号
     * @param no 包装方式ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdPackclassMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询包装方式计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdPackclassMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增包装方式
     *
     * @param appCode 应用编号
     * @param info 包装方式
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdPackclass info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdPackclassMapper.AddNewRecord(info);
    }

    /**
     * 更新包装方式
     *
     * @param appCode 应用编号
     * @param info 包装方式
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdPackclass info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdPackclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除包装方式
     *
     * @param appCode 应用编号
     * @param no 包装方式ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdPackclassMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除包装方式
     *
     * @param appCode 应用编号
     * @param nos 包装方式IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdPackclassMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除包装方式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdPackclassMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除包装方式
     *
     * @param appCode 应用编号
     * @param no 包装方式ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdPackclassMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除包装方式
     *
     * @param appCode 应用编号
     * @param nos 包装方式IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdPackclassMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除包装方式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdPackclassMapper.SoftDeleteByCondition(appCode,condition);
    }
}
