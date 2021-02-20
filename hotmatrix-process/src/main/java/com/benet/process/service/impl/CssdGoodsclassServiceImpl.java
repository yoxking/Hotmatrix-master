package com.benet.process.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.process.mapper.CssdGoodsclassMapper;
import com.benet.process.domain.CssdGoodsclass;
import com.benet.process.service.ICssdGoodsclassService;

/**
 * 物品包类型Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdGoodsclassServiceImpl implements ICssdGoodsclassService 
{
    @Autowired
    private CssdGoodsclassMapper cssdGoodsclassMapper;

    /**
     * 查询所有物品包类型列表
     *
     * @param appCode 应用编号
     * @return 物品包类型集合
     */
    @Override
    public List<CssdGoodsclass> getAllRecords(String appCode) {
        return cssdGoodsclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询物品包类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 物品包类型集合
     */
    @Override
    public List<CssdGoodsclass> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdGoodsclassMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询物品包类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 物品包类型集合
     */
    @Override
    public List<CssdGoodsclass> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdGoodsclassMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询物品包类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 物品包类型集合
     */
    @Override
    public List<CssdGoodsclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdGoodsclassMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询物品包类型
     *
     * @param appCode 应用编号
     * @param no 物品包类型ID
     * @return 物品包类型
     */
    @Override
    public CssdGoodsclass getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdGoodsclassMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询物品包类型名称
     *
     * @param appCode 应用编号
     * @param no 物品包类型ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdGoodsclassMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询物品包类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdGoodsclassMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增物品包类型
     *
     * @param appCode 应用编号
     * @param info 物品包类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdGoodsclass info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdGoodsclassMapper.AddNewRecord(info);
    }

    /**
     * 更新物品包类型
     *
     * @param appCode 应用编号
     * @param info 物品包类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdGoodsclass info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdGoodsclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除物品包类型
     *
     * @param appCode 应用编号
     * @param no 物品包类型ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdGoodsclassMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除物品包类型
     *
     * @param appCode 应用编号
     * @param nos 物品包类型IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdGoodsclassMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除物品包类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdGoodsclassMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除物品包类型
     *
     * @param appCode 应用编号
     * @param no 物品包类型ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdGoodsclassMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除物品包类型
     *
     * @param appCode 应用编号
     * @param nos 物品包类型IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdGoodsclassMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除物品包类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdGoodsclassMapper.SoftDeleteByCondition(appCode,condition);
    }
}
