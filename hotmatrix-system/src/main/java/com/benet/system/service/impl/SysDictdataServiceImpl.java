package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysDictdataMapper;
import com.benet.system.domain.SysDictdata;
import com.benet.system.service.ISysDictdataService;

/**
 * 字典数据Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-23
 */
@Service
public class SysDictdataServiceImpl implements ISysDictdataService 
{
    @Autowired
    private SysDictdataMapper sysDictdataMapper;

    /**
     * 查询所有字典数据列表
     *
     * @return 字典数据集合
     */
    @Override
    public List<SysDictdata> getAllRecords() {
        return sysDictdataMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询字典数据列表
     *
     * @param classNo 分类编号
     * @return 字典数据集合
     */
    @Override
    public List<SysDictdata> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysDictdataMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询字典数据列表
     *
     * @param model 分页模型
     * @return 字典数据集合
     */
    @Override
    public List<SysDictdata> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysDictdataMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询字典数据列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 字典数据集合
     */
    public List<SysDictdata> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysDictdataMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询字典数据
     *
     * @param no 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictdata getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDictdataMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询字典数据名称
     *
     * @param no 字典数据ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDictdataMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询字典数据计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysDictdataMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增字典数据
     *
     * @param info 字典数据
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysDictdata info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysDictdataMapper.AddNewRecord(info);
    }

    /**
     * 更新字典数据
     *
     * @param info 字典数据
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysDictdata info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysDictdataMapper.UpdateRecord(info);
    }

    /**
     * 硬删除字典数据
     *
     * @param no 字典数据ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDictdataMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除字典数据
     *
     * @param nos 字典数据IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysDictdataMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除字典数据
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysDictdataMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除字典数据
     *
     * @param no 字典数据ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDictdataMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除字典数据
     *
     * @param nos 字典数据IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysDictdataMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除字典数据
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysDictdataMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
