package com.benet.process.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.process.mapper.CssdSterytypeMapper;
import com.benet.process.domain.CssdSterytype;
import com.benet.process.service.ICssdSterytypeService;

/**
 * 灭菌方式Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class CssdSterytypeServiceImpl implements ICssdSterytypeService 
{
    @Autowired
    private CssdSterytypeMapper cssdSterytypeMapper;

    /**
     * 查询所有灭菌方式列表
     *
     * @param appCode 应用编号
     * @return 灭菌方式集合
     */
    @Override
    public List<CssdSterytype> getAllRecords(String appCode) {
        return cssdSterytypeMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询灭菌方式列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 灭菌方式集合
     */
    @Override
    public List<CssdSterytype> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdSterytypeMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询灭菌方式列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 灭菌方式集合
     */
    @Override
    public List<CssdSterytype> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdSterytypeMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询灭菌方式列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 灭菌方式集合
     */
    @Override
    public List<CssdSterytype> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdSterytypeMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询灭菌方式
     *
     * @param appCode 应用编号
     * @param no 灭菌方式ID
     * @return 灭菌方式
     */
    @Override
    public CssdSterytype getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdSterytypeMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询灭菌方式名称
     *
     * @param appCode 应用编号
     * @param no 灭菌方式ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdSterytypeMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询灭菌方式计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdSterytypeMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增灭菌方式
     *
     * @param appCode 应用编号
     * @param info 灭菌方式
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdSterytype info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdSterytypeMapper.AddNewRecord(info);
    }

    /**
     * 更新灭菌方式
     *
     * @param appCode 应用编号
     * @param info 灭菌方式
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdSterytype info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdSterytypeMapper.UpdateRecord(info);
    }

    /**
     * 硬删除灭菌方式
     *
     * @param appCode 应用编号
     * @param no 灭菌方式ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdSterytypeMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除灭菌方式
     *
     * @param appCode 应用编号
     * @param nos 灭菌方式IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdSterytypeMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除灭菌方式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdSterytypeMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除灭菌方式
     *
     * @param appCode 应用编号
     * @param no 灭菌方式ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdSterytypeMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除灭菌方式
     *
     * @param appCode 应用编号
     * @param nos 灭菌方式IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdSterytypeMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除灭菌方式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdSterytypeMapper.SoftDeleteByCondition(appCode,condition);
    }
}
