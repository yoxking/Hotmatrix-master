package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysDictinfoMapper;
import com.benet.system.domain.SysDictinfo;
import com.benet.system.service.ISysDictinfoService;

/**
 * 字典数据Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysDictinfoServiceImpl implements ISysDictinfoService 
{
    @Autowired
    private SysDictinfoMapper sysDictinfoMapper;

    /**
     * 查询所有字典数据列表
     *
     * @return 字典数据集合
     */
    @Override
    public List<SysDictinfo> getAllRecords() {
        return sysDictinfoMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询字典数据列表
     *
     * @param classNo 分类编号
     * @return 字典数据集合
     */
    @Override
    public List<SysDictinfo> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysDictinfoMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
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
    public List<SysDictinfo> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysDictinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
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
    public List<SysDictinfo> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysDictinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询字典数据
     *
     * @param no 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictinfo getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDictinfoMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
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
            return sysDictinfoMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
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
        return sysDictinfoMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增字典数据
     *
     * @param info 字典数据
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysDictinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysDictinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新字典数据
     *
     * @param info 字典数据
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysDictinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysDictinfoMapper.UpdateRecord(info);
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
            return sysDictinfoMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
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
            return sysDictinfoMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
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
        return sysDictinfoMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
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
            return sysDictinfoMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
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
            return sysDictinfoMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
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
        return sysDictinfoMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
