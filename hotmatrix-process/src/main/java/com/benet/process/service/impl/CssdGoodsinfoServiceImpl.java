package com.benet.process.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.process.mapper.CssdGoodsinfoMapper;
import com.benet.process.domain.CssdGoodsinfo;
import com.benet.process.service.ICssdGoodsinfoService;

/**
 * 物品包信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdGoodsinfoServiceImpl implements ICssdGoodsinfoService 
{
    @Autowired
    private CssdGoodsinfoMapper cssdGoodsinfoMapper;

    /**
     * 查询所有物品包信息列表
     *
     * @param appCode 应用编号
     * @return 物品包信息集合
     */
    @Override
    public List<CssdGoodsinfo> getAllRecords(String appCode) {
        return cssdGoodsinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询物品包信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 物品包信息集合
     */
    @Override
    public List<CssdGoodsinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdGoodsinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询物品包信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 物品包信息集合
     */
    @Override
    public List<CssdGoodsinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdGoodsinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询物品包信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 物品包信息集合
     */
    @Override
    public List<CssdGoodsinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdGoodsinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询物品包信息
     *
     * @param appCode 应用编号
     * @param no 物品包信息ID
     * @return 物品包信息
     */
    @Override
    public CssdGoodsinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdGoodsinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询物品包信息名称
     *
     * @param appCode 应用编号
     * @param no 物品包信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdGoodsinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询物品包信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdGoodsinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增物品包信息
     *
     * @param appCode 应用编号
     * @param info 物品包信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdGoodsinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdGoodsinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新物品包信息
     *
     * @param appCode 应用编号
     * @param info 物品包信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdGoodsinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdGoodsinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除物品包信息
     *
     * @param appCode 应用编号
     * @param no 物品包信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdGoodsinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除物品包信息
     *
     * @param appCode 应用编号
     * @param nos 物品包信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdGoodsinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除物品包信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdGoodsinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除物品包信息
     *
     * @param appCode 应用编号
     * @param no 物品包信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdGoodsinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除物品包信息
     *
     * @param appCode 应用编号
     * @param nos 物品包信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdGoodsinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除物品包信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdGoodsinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
