package com.benet.process.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.process.mapper.CssdStockflowsMapper;
import com.benet.process.domain.CssdStockflows;
import com.benet.process.service.ICssdStockflowsService;

/**
 * 出入库操作Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdStockflowsServiceImpl implements ICssdStockflowsService 
{
    @Autowired
    private CssdStockflowsMapper cssdStockflowsMapper;

    /**
     * 查询所有出入库操作列表
     *
     * @param appCode 应用编号
     * @return 出入库操作集合
     */
    @Override
    public List<CssdStockflows> getAllRecords(String appCode) {
        return cssdStockflowsMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询出入库操作列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 出入库操作集合
     */
    @Override
    public List<CssdStockflows> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdStockflowsMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询出入库操作列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 出入库操作集合
     */
    @Override
    public List<CssdStockflows> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdStockflowsMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询出入库操作列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 出入库操作集合
     */
    @Override
    public List<CssdStockflows> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdStockflowsMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询出入库操作
     *
     * @param appCode 应用编号
     * @param no 出入库操作ID
     * @return 出入库操作
     */
    @Override
    public CssdStockflows getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStockflowsMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询出入库操作名称
     *
     * @param appCode 应用编号
     * @param no 出入库操作ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStockflowsMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询出入库操作计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdStockflowsMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增出入库操作
     *
     * @param appCode 应用编号
     * @param info 出入库操作
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdStockflows info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdStockflowsMapper.AddNewRecord(info);
    }

    /**
     * 更新出入库操作
     *
     * @param appCode 应用编号
     * @param info 出入库操作
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdStockflows info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdStockflowsMapper.UpdateRecord(info);
    }

    /**
     * 硬删除出入库操作
     *
     * @param appCode 应用编号
     * @param no 出入库操作ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStockflowsMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除出入库操作
     *
     * @param appCode 应用编号
     * @param nos 出入库操作IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdStockflowsMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除出入库操作
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdStockflowsMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除出入库操作
     *
     * @param appCode 应用编号
     * @param no 出入库操作ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStockflowsMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除出入库操作
     *
     * @param appCode 应用编号
     * @param nos 出入库操作IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdStockflowsMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除出入库操作
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdStockflowsMapper.SoftDeleteByCondition(appCode,condition);
    }
}
