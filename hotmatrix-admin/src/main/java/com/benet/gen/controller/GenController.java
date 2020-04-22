package com.benet.gen.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.core.text.ConvertHelper;
import com.benet.common.enums.BusinessType;
import com.benet.gen.domain.SysTabcolumn;
import com.benet.gen.domain.SysTableinfo;
import com.benet.gen.service.ISysTabcolumnService;
import com.benet.gen.service.ISysTableinfoService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码生成 操作处理
 * 
 * @author yoxking
 */
@RestController
@RequestMapping("/gen")
public class GenController extends BaseController
{
    @Autowired
    private ISysTableinfoService tableInfoService;

    @Autowired
    private ISysTabcolumnService tabColumnService;

    /**
     * 查询代码生成列表
     */
    @GetMapping("/list")
    public TableDataInfo list()
    {
        List<SysTableinfo> list = tableInfoService.getRecordsByPaging(1,10,"","id","Asc");
        return getDataTable(list);
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping(value = "/{tableNo}")
    public AjaxResult detail(@PathVariable String tableNo)
    {
        SysTableinfo tableInfo = tableInfoService.getRecordByNo(tableNo);
        List<SysTabcolumn> list = tabColumnService.getRecordsByClassNo(tableNo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", tableInfo);
        map.put("rows", list);
        return AjaxResult.success(map);
    }

    /**
     * 查询数据库列表
     */
    @GetMapping("/table/list")
    public TableDataInfo tableList()
    {
        List<SysTableinfo> list = tableInfoService.getDbTableList("");
        return getDataTable(list);
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{tableNo}")
    public TableDataInfo columnList(@PathVariable String tableNo)
    {
        TableDataInfo dataInfo = new TableDataInfo();
        List<SysTabcolumn> list = tabColumnService.getRecordsByClassNo(tableNo);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构（保存）
     */
    @Oplog(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    public AjaxResult importTable(String tables)
    {
        String[] tableNames = ConvertHelper.toStrArray(tables);
        // 查询表信息
        List<SysTableinfo> tableList = tableInfoService.getDbTableListByNames(tableNames);
        tableInfoService.importTableInfo(tableList);
        return AjaxResult.success();
    }

    /**
     * 修改保存代码生成业务
     */
    @Oplog(title = "代码生成", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult save(@Validated @RequestBody SysTableinfo tableInfo)
    {
        System.out.println(tableInfo.getParams().size());
        tableInfoService.validateEdit(tableInfo);
        tableInfoService.UpdateRecord(tableInfo);
        return AjaxResult.success();
    }

    @Oplog(title = "代码生成", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tableNos}")
    public AjaxResult delete(@PathVariable String[] tableNos)
    {
        tableInfoService.HardDeleteByNos(tableNos);
        return AjaxResult.success();
    }

    /**
     * 预览代码
     */
    @GetMapping("/preview/{tableNo}")
    public AjaxResult preview(@PathVariable("tableNo") String tableNo) throws IOException
    {
        Map<String, String> dataMap = tableInfoService.previewCode(tableNo);
        return AjaxResult.success(dataMap);
    }

    /**
     * 生成代码
     */
    @Oplog(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    public void genCode(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException
    {
        byte[] data = tableInfoService.generateCode(tableName);
        genCode(response, data);
    }

    /**
     * 批量生成代码
     */
    @Oplog(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException
    {
        String[] tableNames = ConvertHelper.toStrArray(tables);
        byte[] data = tableInfoService.generateCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException
    {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"hotmatrix.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}