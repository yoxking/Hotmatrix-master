package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysDictclassMapper;
import com.benet.system.domain.SysDictclass;
import com.benet.system.service.ISysDictclassService;

/**
 * 字典类型Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysDictclassServiceImpl implements ISysDictclassService 
{
    @Autowired
    private SysDictclassMapper sysDictclassMapper;

    /**
     * 查询所有字典类型列表
     *
     * @return 字典类型集合
     */
    @Override
    public List<SysDictclass> getAllRecords() {
        return sysDictclassMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询字典类型列表
     *
     * @param classNo 分类编号
     * @return 字典类型集合
     */
    @Override
    public List<SysDictclass> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysDictclassMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询字典类型列表
     *
     * @param model 分页模型
     * @return 字典类型集合
     */
    @Override
    public List<SysDictclass> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysDictclassMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询字典类型列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 字典类型集合
     */
    public List<SysDictclass> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysDictclassMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询字典类型
     *
     * @param no 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictclass getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDictclassMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询字典类型名称
     *
     * @param no 字典类型ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDictclassMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询字典类型计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysDictclassMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增字典类型
     *
     * @param info 字典类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysDictclass info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysDictclassMapper.AddNewRecord(info);
    }

    /**
     * 更新字典类型
     *
     * @param info 字典类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysDictclass info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysDictclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除字典类型
     *
     * @param no 字典类型ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDictclassMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除字典类型
     *
     * @param nos 字典类型IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysDictclassMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除字典类型
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysDictclassMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除字典类型
     *
     * @param no 字典类型ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDictclassMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除字典类型
     *
     * @param nos 字典类型IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysDictclassMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除字典类型
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysDictclassMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
