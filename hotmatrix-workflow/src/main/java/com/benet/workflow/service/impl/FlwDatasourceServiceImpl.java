package com.benet.workflow.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.workflow.mapper.FlwDatasourceMapper;
import com.benet.workflow.domain.FlwDatasource;
import com.benet.workflow.service.IFlwDatasourceService;

/**
 * 数据源信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-05-23
 */
@Service
public class FlwDatasourceServiceImpl implements IFlwDatasourceService 
{
    @Autowired
    private FlwDatasourceMapper flwDatasourceMapper;

    /**
     * 查询所有数据源信息列表
     *
     * @param appCode 应用编号
     * @return 数据源信息集合
     */
    @Override
    public List<FlwDatasource> getAllRecords(String appCode) {
        return flwDatasourceMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询数据源信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 数据源信息集合
     */
    @Override
    public List<FlwDatasource> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flwDatasourceMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询数据源信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 数据源信息集合
     */
    @Override
    public List<FlwDatasource> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flwDatasourceMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询数据源信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 数据源信息集合
     */
    @Override
    public List<FlwDatasource> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flwDatasourceMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询数据源信息
     *
     * @param appCode 应用编号
     * @param no 数据源信息ID
     * @return 数据源信息
     */
    @Override
    public FlwDatasource getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwDatasourceMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询数据源信息名称
     *
     * @param appCode 应用编号
     * @param no 数据源信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwDatasourceMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询数据源信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return flwDatasourceMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增数据源信息
     *
     * @param appCode 应用编号
     * @param info 数据源信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,FlwDatasource info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return flwDatasourceMapper.AddNewRecord(info);
    }

    /**
     * 更新数据源信息
     *
     * @param appCode 应用编号
     * @param info 数据源信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,FlwDatasource info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return flwDatasourceMapper.UpdateRecord(info);
    }

    /**
     * 硬删除数据源信息
     *
     * @param appCode 应用编号
     * @param no 数据源信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwDatasourceMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除数据源信息
     *
     * @param appCode 应用编号
     * @param nos 数据源信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwDatasourceMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除数据源信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return flwDatasourceMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除数据源信息
     *
     * @param appCode 应用编号
     * @param no 数据源信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwDatasourceMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除数据源信息
     *
     * @param appCode 应用编号
     * @param nos 数据源信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwDatasourceMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除数据源信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return flwDatasourceMapper.SoftDeleteByCondition(appCode,condition);
    }
}
