package com.benet.process.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.process.mapper.CssdSteryclassMapper;
import com.benet.process.domain.CssdSteryclass;
import com.benet.process.service.ICssdSteryclassService;

/**
 * 灭菌程序/类型Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class CssdSteryclassServiceImpl implements ICssdSteryclassService 
{
    @Autowired
    private CssdSteryclassMapper cssdSteryclassMapper;

    /**
     * 查询所有灭菌程序/类型列表
     *
     * @param appCode 应用编号
     * @return 灭菌程序/类型集合
     */
    @Override
    public List<CssdSteryclass> getAllRecords(String appCode) {
        return cssdSteryclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询灭菌程序/类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 灭菌程序/类型集合
     */
    @Override
    public List<CssdSteryclass> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdSteryclassMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询灭菌程序/类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 灭菌程序/类型集合
     */
    @Override
    public List<CssdSteryclass> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdSteryclassMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询灭菌程序/类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 灭菌程序/类型集合
     */
    @Override
    public List<CssdSteryclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdSteryclassMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param no 灭菌程序/类型ID
     * @return 灭菌程序/类型
     */
    @Override
    public CssdSteryclass getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdSteryclassMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询灭菌程序/类型名称
     *
     * @param appCode 应用编号
     * @param no 灭菌程序/类型ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdSteryclassMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询灭菌程序/类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdSteryclassMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param info 灭菌程序/类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdSteryclass info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdSteryclassMapper.AddNewRecord(info);
    }

    /**
     * 更新灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param info 灭菌程序/类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdSteryclass info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdSteryclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param no 灭菌程序/类型ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdSteryclassMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param nos 灭菌程序/类型IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdSteryclassMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdSteryclassMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param no 灭菌程序/类型ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdSteryclassMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param nos 灭菌程序/类型IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdSteryclassMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除灭菌程序/类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdSteryclassMapper.SoftDeleteByCondition(appCode,condition);
    }
}
