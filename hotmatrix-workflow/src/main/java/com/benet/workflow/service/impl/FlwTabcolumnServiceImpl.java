package com.benet.workflow.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.workflow.mapper.FlwTabcolumnMapper;
import com.benet.workflow.domain.FlwTabcolumn;
import com.benet.workflow.service.IFlwTabcolumnService;

/**
 * 单字段Service业务层处理
 * 
 * @author yoxking
 * @date 2020-05-23
 */
@Service
public class FlwTabcolumnServiceImpl implements IFlwTabcolumnService 
{
    @Autowired
    private FlwTabcolumnMapper flwTabcolumnMapper;

    /**
     * 查询所有单字段列表
     *
     * @return 单字段集合
     */
    @Override
    public List<FlwTabcolumn> getAllRecords() {
        return flwTabcolumnMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询单字段列表
     *
     * @param classNo 分类编号
     * @return 单字段集合
     */
    @Override
    public List<FlwTabcolumn> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return flwTabcolumnMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询单字段列表
     *
     * @param model 分页模型
     * @return 单字段集合
     */
    @Override
    public List<FlwTabcolumn> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return flwTabcolumnMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询单字段列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 单字段集合
     */
    @Override
    public List<FlwTabcolumn> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return flwTabcolumnMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询单字段
     *
     * @param no 单字段ID
     * @return 单字段
     */
    @Override
    public FlwTabcolumn getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwTabcolumnMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询单字段名称
     *
     * @param no 单字段ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwTabcolumnMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询单字段计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return flwTabcolumnMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增单字段
     *
     * @param info 单字段
     * @return 结果
     */
    @Override
    public int AddNewRecord(FlwTabcolumn info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return flwTabcolumnMapper.AddNewRecord(info);
    }

    /**
     * 更新单字段
     *
     * @param info 单字段
     * @return 结果
     */
    @Override
    public int UpdateRecord(FlwTabcolumn info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return flwTabcolumnMapper.UpdateRecord(info);
    }

    /**
     * 硬删除单字段
     *
     * @param no 单字段ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwTabcolumnMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除单字段
     *
     * @param nos 单字段IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwTabcolumnMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除单字段
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return flwTabcolumnMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除单字段
     *
     * @param no 单字段ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return flwTabcolumnMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除单字段
     *
     * @param nos 单字段IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return flwTabcolumnMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除单字段
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return flwTabcolumnMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
