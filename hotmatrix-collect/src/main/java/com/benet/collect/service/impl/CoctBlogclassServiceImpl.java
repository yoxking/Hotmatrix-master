package com.benet.collect.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.collect.mapper.CoctBlogclassMapper;
import com.benet.collect.domain.CoctBlogclass;
import com.benet.collect.service.ICoctBlogclassService;

/**
 * 日记类型Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Service
public class CoctBlogclassServiceImpl implements ICoctBlogclassService 
{
    @Autowired
    private CoctBlogclassMapper coctBlogclassMapper;

    /**
     * 查询所有日记类型列表
     *
     * @param appCode 应用编号
     * @return 日记类型集合
     */
    @Override
    public List<CoctBlogclass> getAllRecords(String appCode) {
        return coctBlogclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询日记类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 日记类型集合
     */
    @Override
    public List<CoctBlogclass> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return coctBlogclassMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询日记类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 日记类型集合
     */
    @Override
    public List<CoctBlogclass> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return coctBlogclassMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询日记类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 日记类型集合
     */
    @Override
    public List<CoctBlogclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return coctBlogclassMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询日记类型
     *
     * @param appCode 应用编号
     * @param no 日记类型ID
     * @return 日记类型
     */
    @Override
    public CoctBlogclass getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctBlogclassMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询日记类型名称
     *
     * @param appCode 应用编号
     * @param no 日记类型ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctBlogclassMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询日记类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return coctBlogclassMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增日记类型
     *
     * @param appCode 应用编号
     * @param info 日记类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CoctBlogclass info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return coctBlogclassMapper.AddNewRecord(info);
    }

    /**
     * 更新日记类型
     *
     * @param appCode 应用编号
     * @param info 日记类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CoctBlogclass info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return coctBlogclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除日记类型
     *
     * @param appCode 应用编号
     * @param no 日记类型ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctBlogclassMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除日记类型
     *
     * @param appCode 应用编号
     * @param nos 日记类型IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctBlogclassMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除日记类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return coctBlogclassMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除日记类型
     *
     * @param appCode 应用编号
     * @param no 日记类型ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return coctBlogclassMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除日记类型
     *
     * @param appCode 应用编号
     * @param nos 日记类型IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return coctBlogclassMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除日记类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return coctBlogclassMapper.SoftDeleteByCondition(appCode,condition);
    }
}
