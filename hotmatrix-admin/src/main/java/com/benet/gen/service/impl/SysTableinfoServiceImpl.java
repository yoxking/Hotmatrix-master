package com.benet.gen.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.constant.GenConstants;
import com.benet.common.constant.PubConstants;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.exception.base.BaseException;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateUtils;
import com.benet.framework.utils.SecurityUtils;
import com.benet.gen.domain.SysTabcolumn;
import com.benet.gen.domain.SysTableinfo;
import com.benet.gen.mapper.SysTabcolumnMapper;
import com.benet.gen.mapper.SysTableinfoMapper;
import com.benet.gen.service.ISysTableinfoService;
import com.benet.gen.utils.GeneratUtils;
import com.benet.gen.utils.VelocityInitializer;
import com.benet.gen.utils.VelocityUtils;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 代码生成业务Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysTableinfoServiceImpl implements ISysTableinfoService
{
    private static final Logger log = LoggerFactory.getLogger(SysTableinfoServiceImpl.class);

    @Autowired
    private SysTableinfoMapper sysTableinfoMapper;

    @Autowired
    private SysTabcolumnServiceImpl sysTabcolumnService;

    /**
     * 查询所有代码生成业务列表
     *
     * @return 代码生成业务集合
     */
    @Override
    public List<SysTableinfo> getAllRecords() {
        return sysTableinfoMapper.getAllRecords();
    }

    /**
     * 按分类查询代码生成业务列表
     *
     * @param classNo 分类编号
     * @return 代码生成业务集合
     */
    @Override
    public List<SysTableinfo> getRecordsByClassNo(String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysTableinfoMapper.getRecordsByClassNo(classNo);
        }
        return null;
    }

    /**
     * 分页查询代码生成业务列表
     *
     * @param model 分页模型
     * @return 代码生成业务集合
     */
    @Override
    public List<SysTableinfo> getRecordsByPaging(PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysTableinfoMapper.getRecordsByPaging(model);
        }
        return null;
    }


    /**
     * 分页查询代码生成业务列表
     *
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 代码生成业务集合
     */
    public List<SysTableinfo> getRecordsByPaging(int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysTableinfoMapper.getRecordsByPaging(model);
    }

    /**
     * 查询代码生成业务
     *
     * @param no 代码生成业务ID
     * @return 代码生成业务
     */
    @Override
    public SysTableinfo getRecordByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTableinfoMapper.getRecordByNo(no);
        }
        return null;
    }

    /**
     * 查询代码生成业务
     *
     * @param tableName 表名
     * @return 代码生成业务
     */
    @Override
    public SysTableinfo getRecordByName(@Param("tableName" ) String tableName) {
        if (StringUtils.isNotEmpty(tableName)) {
            return sysTableinfoMapper.getRecordByName(tableName);
        }
        return null;
    }

    /**
     * 查询代码生成业务名称
     *
     * @param no 代码生成业务ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTableinfoMapper.getRecordNameByNo(no);
        }
        return null;
    }

    /**
     * 查询代码生成业务计数
     *
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String condition) {
        return sysTableinfoMapper.getCountByCondition(condition);
    }

    /**
     * 新增代码生成业务
     *
     * @param info 代码生成业务
     * @return 结果
     */
    @Override
    public int AddNewRecord(SysTableinfo info) {
        info.setCreateTime(DateUtils.getNowDate());
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysTableinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新代码生成业务
     *
     * @param info 代码生成业务
     * @return 结果
     */
    @Override
    public int UpdateRecord(SysTableinfo info) {
        info.setUpdateTime(DateUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysTableinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除代码生成业务
     *
     * @param no 代码生成业务ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTableinfoMapper.HardDeleteByNo(no);
        }
        return 0;
    }

    /**
     * 批量硬删除代码生成业务
     *
     * @param nos 代码生成业务IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysTableinfoMapper.HardDeleteByNos(nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除代码生成业务
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String condition) {
        return sysTableinfoMapper.HardDeleteByCondition(condition);
    }

    /**
     * 软删除代码生成业务
     *
     * @param no 代码生成业务ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTableinfoMapper.SoftDeleteByNo(no);
        }
        return 0;
    }

    /**
     * 批量软删除代码生成业务
     *
     * @param nos 代码生成业务IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysTableinfoMapper.SoftDeleteByNos(nos);
        }
        return 0;
    }

    /**
     * 按条件软删除代码生成业务
     *
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String condition) {
        return sysTableinfoMapper.SoftDeleteByCondition(condition);
    }


    /**
     * 查询据库列表
     *
     * @param condition 条件
     * @return 数据库表集合
     */
    @Override
    public List<SysTableinfo> getDbTableList(String condition)
    {
        return sysTableinfoMapper.getDbTableList(condition);
    }

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    @Override
    public List<SysTableinfo> getDbTableListByNames(String[] tableNames)
    {
        return sysTableinfoMapper.getDbTableListByNames(tableNames);
    }

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    @Override
    public SysTableinfo getDbTableByName(String tableName)
    {
        return sysTableinfoMapper.getDbTableByName(tableName);
    }

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     */
    @Override
    @Transactional
    public void importTableInfo(List<SysTableinfo> tableList)
    {
        String opertName = SecurityUtils.getUsername();
        for (SysTableinfo table : tableList)
        {
            try
            {
                String tableName = table.getTableName();
                GeneratUtils.initTable(table, opertName);
                int row = AddNewRecord(table);
                if (row > 0)
                {
                    // 保存列信息
                    List<SysTabcolumn> sysTabcolumns = sysTabcolumnService.getDbTableColumnsByName(tableName);
                    for (SysTabcolumn column : sysTabcolumns)
                    {
                        GeneratUtils.initColumnField(column, table);
                        sysTabcolumnService.AddNewRecord(column);
                    }
                }
            }
            catch (Exception e)
            {
                log.error("表名 " + table.getTableName() + " 导入失败：", e);
            }
        }
    }

    /**
     * 预览代码
     *
     * @param tableNo 表编号
     * @return 预览数据列表
     */
    @Override
    public Map<String, String> previewCode(String tableNo)
    {
        Map<String, String> dataMap = new LinkedHashMap<>();
        // 查询表信息
        SysTableinfo table = sysTableinfoMapper.getRecordByNo(tableNo);
        // 查询列信息
        List<SysTabcolumn> columns = table.getColumns();
        setPkColumn(table, columns);
        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates)
        {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, PubConstants.UTF8);
            tpl.merge(context, sw);
            dataMap.put(template, sw.toString());
        }
        return dataMap;
    }

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public byte[] generateCode(String tableName)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        generateCode(tableName, zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    @Override
    public byte[] generateCode(String[] tableNames)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames)
        {
            generateCode(tableName, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 查询表信息并生成代码
     */
    private void generateCode(String tableName, ZipOutputStream zip)
    {
        // 查询表信息
        SysTableinfo table = sysTableinfoMapper.getRecordByName(tableName);
        // 查询列信息
        List<SysTabcolumn> columns = table.getColumns();
        setPkColumn(table, columns);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates)
        {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, PubConstants.UTF8);
            tpl.merge(context, sw);
            try
            {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, PubConstants.UTF8);
                IOUtils.closeQuietly(sw);
                zip.flush();
                zip.closeEntry();
            }
            catch (IOException e)
            {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }

    /**
     * 修改保存参数校验
     *
     * @param table 业务信息
     */
    @Override
    public void validateEdit(SysTableinfo table)
    {
        if (GenConstants.TPL_TREE.equals(table.getTplCategory()))
        {
            String options = JSON.toJSONString(table.getParams());
            JSONObject paramsObj = JSONObject.parseObject(options);
            if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_CODE)))
            {
                throw new BaseException("树编码字段不能为空");
            }
            else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_PARENT_CODE)))
            {
                throw new BaseException("树父编码字段不能为空");
            }
            else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_NAME)))
            {
                throw new BaseException("树名称字段不能为空");
            }
        }
    }

    /**
     * 设置主键列信息
     *
     * @param table 业务表信息
     * @param columns 业务字段列表
     */
    public void setPkColumn(SysTableinfo table, List<SysTabcolumn> columns)
    {
        for (SysTabcolumn column : columns)
        {
            if (column.isPk())
            {
                table.setPkColumn(column);
                break;
            }
        }
        if (StringUtils.isNull(table.getPkColumn()))
        {
            table.setPkColumn(columns.get(0));
        }
    }

    /**
     * 设置代码生成其他选项值
     *
     * @param table 设置后的生成对象
     */
    public void setTableFromOptions(SysTableinfo table)
    {
        JSONObject paramsObj = JSONObject.parseObject(table.getOptions());
        if (StringUtils.isNotNull(paramsObj))
        {
            String treeCode = paramsObj.getString(GenConstants.TREE_CODE);
            String treeParentCode = paramsObj.getString(GenConstants.TREE_PARENT_CODE);
            String treeName = paramsObj.getString(GenConstants.TREE_NAME);
            table.setTreeCode(treeCode);
            table.setTreeParentCode(treeParentCode);
            table.setTreeName(treeName);
        }
    }
}
