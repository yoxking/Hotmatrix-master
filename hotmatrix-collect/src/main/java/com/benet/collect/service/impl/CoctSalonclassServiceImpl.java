package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CoctSalonclassMapper;
import com.benet.collect.domain.CoctSalonclass;
import com.benet.collect.service.ICoctSalonclassService;

/**
 * 沙龙类型Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class CoctSalonclassServiceImpl implements ICoctSalonclassService 
{
    @Autowired
    private CoctSalonclassMapper coctSalonclassMapper;

    /**
     * 查询所有沙龙类型列表
     *
     * @param appCode 应用编号
     * @return 沙龙类型集合
     */
    @Override
    public List<CoctSalonclass> getAllRecords(String appCode) {
        return coctSalonclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询沙龙类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 沙龙类型集合
     */
    @Override
    public List<CoctSalonclass> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return coctSalonclassMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询沙龙类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 沙龙类型集合
     */
    @Override
    public List<CoctSalonclass> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return coctSalonclassMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询沙龙类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 沙龙类型集合
     */
    @Override
    public List<CoctSalonclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return coctSalonclassMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询沙龙类型
     *
     * @param appCode 应用编号
     * @param no 沙龙类型ID
     * @return 沙龙类型
     */
    @Override
    public CoctSalonclass getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctSalonclassMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询沙龙类型名称
     *
     * @param appCode 应用编号
     * @param no 沙龙类型ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctSalonclassMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询沙龙类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return coctSalonclassMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增沙龙类型
     *
     * @param appCode 应用编号
     * @param info 沙龙类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CoctSalonclass info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return coctSalonclassMapper.AddNewRecord(info);
    }

    /**
     * 更新沙龙类型
     *
     * @param appCode 应用编号
     * @param info 沙龙类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CoctSalonclass info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return coctSalonclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除沙龙类型
     *
     * @param appCode 应用编号
     * @param no 沙龙类型ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctSalonclassMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除沙龙类型
     *
     * @param appCode 应用编号
     * @param nos 沙龙类型IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctSalonclassMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除沙龙类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return coctSalonclassMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除沙龙类型
     *
     * @param appCode 应用编号
     * @param no 沙龙类型ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctSalonclassMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除沙龙类型
     *
     * @param appCode 应用编号
     * @param nos 沙龙类型IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctSalonclassMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除沙龙类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return coctSalonclassMapper.SoftDeleteByCondition(appCode,condition);
    }
}
