package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.uuid.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysConfiginfoMapper;
import com.benet.system.domain.SysConfiginfo;
import com.benet.system.service.ISysConfiginfoService;

/**
 * 参数配置Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysConfiginfoServiceImpl implements ISysConfiginfoService 
{
    @Autowired
    private SysConfiginfoMapper sysConfiginfoMapper;

    /**
     * 查询所有参数配置列表
     *
     * @return 参数配置集合
     */
    @Override
    public List<SysConfiginfo> getAllRecords() {
        return sysConfiginfoMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询参数配置列表
     *
     * @param classNo 分类编号
     * @return 参数配置集合
     */
    @Override
    public List<SysConfiginfo> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysConfiginfoMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询参数配置列表
     *
     * @param model 分页模型
     * @return 参数配置集合
     */
    @Override
    public List<SysConfiginfo> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysConfiginfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询参数配置列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 参数配置集合
     */
    @Override
    public List<SysConfiginfo> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysConfiginfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询参数配置
     *
     * @param no 参数配置ID
     * @return 参数配置
     */
    @Override
    public SysConfiginfo getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysConfiginfoMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询参数配置
     *
     * @param configKey 参数配置Key
     * @return 参数配置
     */
    @Override
    public SysConfiginfo getRecordByConfigKey(String configKey) {
        if (StringUtils.isNotEmpty(configKey)) {
            return sysConfiginfoMapper.getRecordByConfigKey(GlobalConfig.getAppCode(),configKey);
        }
        return null;
    }

    /**
     * 查询参数配置名称
     *
     * @param no 参数配置ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysConfiginfoMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询参数配置计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysConfiginfoMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增参数配置
     *
     * @param info 参数配置
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysConfiginfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysConfiginfoMapper.AddNewRecord(info);
    }

    /**
     * 更新参数配置
     *
     * @param info 参数配置
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysConfiginfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysConfiginfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除参数配置
     *
     * @param no 参数配置ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysConfiginfoMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除参数配置
     *
     * @param nos 参数配置IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysConfiginfoMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除参数配置
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysConfiginfoMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除参数配置
     *
     * @param no 参数配置ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysConfiginfoMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除参数配置
     *
     * @param nos 参数配置IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysConfiginfoMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除参数配置
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysConfiginfoMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }


    /**
     * 校验参数键名是否唯一
     *
     * @param configKey 参数键名
     * @return 结果
     */
    @Override
    public int checkConfigKeyUnique(String configKey){
        return sysConfiginfoMapper.checkConfigKeyUnique(GlobalConfig.getAppCode(),configKey);
    }

    /**
     * 根据键名返回键值
     *
     * @param configKey 参数键名
     * @return 参数键值
     */
    @Override
    public String getConfigValueByKey(String configKey){
        return sysConfiginfoMapper.getConfigValueByKey(GlobalConfig.getAppCode(),configKey);
    }


    /**
     * 保存参数配置
     *
     * @param configKey 参数配置
     * @param configValue 参数配置
     * @param configType 参数配置
     * @return 结果
     */
    public int saveConfigValueByKey(String configKey,String configValue,String configType){
        SysConfiginfo info=sysConfiginfoMapper.getRecordByConfigKey(GlobalConfig.getAppCode(),configKey);
        if(info==null) {
            info=new SysConfiginfo();
            info.setConfigNo(UuidUtils.shortUUID());
            info.setConfigName(configKey);
            info.setConfigKey(configKey);
            info.setConfigValue(configValue);
            info.setConfigType(configType);
            info.setCheckState("1");
            info.setBranchNo(GlobalConfig.getBranchNo());
            info.setCreateBy("admin");
            info.setCreateTime(DateUtils.getNowDate());
            info.setUpdateBy("admin");
            info.setUpdateTime(DateUtils.getNowDate());
            info.setDeleteFlag("1");
            info.setComments("");
            info.setAppCode(GlobalConfig.getAppCode());
            info.setVersion(1L);

            return sysConfiginfoMapper.AddNewRecord(info);
        }else{
            info.setConfigValue(configValue);
            info.setUpdateTime(DateUtils.getNowDate());
            return sysConfiginfoMapper.UpdateRecord(info);
        }
    }
}
