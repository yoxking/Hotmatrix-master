package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CoctSalonflowsMapper;
import com.benet.collect.domain.CoctSalonflows;
import com.benet.collect.service.ICoctSalonflowsService;

/**
 * 沙龙过程Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class CoctSalonflowsServiceImpl implements ICoctSalonflowsService 
{
    @Autowired
    private CoctSalonflowsMapper coctSalonflowsMapper;

    /**
     * 查询所有沙龙过程列表
     *
     * @param appCode 应用编号
     * @return 沙龙过程集合
     */
    @Override
    public List<CoctSalonflows> getAllRecords(String appCode) {
        return coctSalonflowsMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询沙龙过程列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 沙龙过程集合
     */
    @Override
    public List<CoctSalonflows> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return coctSalonflowsMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询沙龙过程列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 沙龙过程集合
     */
    @Override
    public List<CoctSalonflows> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return coctSalonflowsMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询沙龙过程列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 沙龙过程集合
     */
    @Override
    public List<CoctSalonflows> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return coctSalonflowsMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询沙龙过程
     *
     * @param appCode 应用编号
     * @param no 沙龙过程ID
     * @return 沙龙过程
     */
    @Override
    public CoctSalonflows getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctSalonflowsMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询沙龙过程名称
     *
     * @param appCode 应用编号
     * @param no 沙龙过程ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctSalonflowsMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询沙龙过程计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return coctSalonflowsMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增沙龙过程
     *
     * @param appCode 应用编号
     * @param info 沙龙过程
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CoctSalonflows info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return coctSalonflowsMapper.AddNewRecord(info);
    }

    /**
     * 更新沙龙过程
     *
     * @param appCode 应用编号
     * @param info 沙龙过程
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CoctSalonflows info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return coctSalonflowsMapper.UpdateRecord(info);
    }

    /**
     * 硬删除沙龙过程
     *
     * @param appCode 应用编号
     * @param no 沙龙过程ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctSalonflowsMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除沙龙过程
     *
     * @param appCode 应用编号
     * @param nos 沙龙过程IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctSalonflowsMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除沙龙过程
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return coctSalonflowsMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除沙龙过程
     *
     * @param appCode 应用编号
     * @param no 沙龙过程ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctSalonflowsMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除沙龙过程
     *
     * @param appCode 应用编号
     * @param nos 沙龙过程IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctSalonflowsMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除沙龙过程
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return coctSalonflowsMapper.SoftDeleteByCondition(appCode,condition);
    }
}
