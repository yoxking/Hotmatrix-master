package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysMessageinfoMapper;
import com.benet.system.domain.SysMessageinfo;
import com.benet.system.service.ISysMessageinfoService;

/**
 * 消息信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysMessageinfoServiceImpl implements ISysMessageinfoService 
{
    @Autowired
    private SysMessageinfoMapper sysMessageinfoMapper;

    /**
     * 查询所有消息信息列表
     *
     * @return 消息信息集合
     */
    @Override
    public List<SysMessageinfo> getAllRecords() {
        return sysMessageinfoMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询消息信息列表
     *
     * @param classNo 分类编号
     * @return 消息信息集合
     */
    @Override
    public List<SysMessageinfo> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysMessageinfoMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询消息信息列表
     *
     * @param model 分页模型
     * @return 消息信息集合
     */
    @Override
    public List<SysMessageinfo> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysMessageinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询消息信息列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 消息信息集合
     */
    public List<SysMessageinfo> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysMessageinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询消息信息
     *
     * @param no 消息信息ID
     * @return 消息信息
     */
    @Override
    public SysMessageinfo getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysMessageinfoMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询消息信息名称
     *
     * @param no 消息信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysMessageinfoMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询消息信息计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysMessageinfoMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增消息信息
     *
     * @param info 消息信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysMessageinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysMessageinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新消息信息
     *
     * @param info 消息信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysMessageinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysMessageinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除消息信息
     *
     * @param no 消息信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysMessageinfoMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除消息信息
     *
     * @param nos 消息信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysMessageinfoMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除消息信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysMessageinfoMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除消息信息
     *
     * @param no 消息信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysMessageinfoMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除消息信息
     *
     * @param nos 消息信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysMessageinfoMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除消息信息
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysMessageinfoMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
