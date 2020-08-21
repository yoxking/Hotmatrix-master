package com.benet.gen.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.core.text.ConvertHelper;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.gen.domain.SysTabcolumn;
import com.benet.gen.domain.SysTableinfo;
import com.benet.gen.service.ISysTabcolumnService;
import com.benet.gen.service.ISysTableinfoService;
import com.benet.system.domain.SysBranchinfo;
import com.benet.system.domain.SysDictdata;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 代码生成 操作处理
 * 
 * @author yoxking
 */
@RestController
@RequestMapping("/gen/tableinfo")
public class GenTableinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysTableinfoService tableInfoService;

    @Autowired
    private ISysTabcolumnService tabColumnService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('generate:tableinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询代码生成列表
     */
    //@PreAuthorize("@ps.hasPermit('generate:tableinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = tableInfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<SysTableinfo> list = tableInfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询数据库列表
     */
    @GetMapping("/dbTableList")
    public AjaxResult dbTableList()
    {
        List<SysTableinfo> list = tableInfoService.getDbTableList("");
        String result="";
        if(list!=null&&list.size()>0){
            for(SysTableinfo table :list){
                result+=table.getTableName()+",";
            }
            result=result.substring(0, result.length()-1);
        }
        return AjaxResult.success(result);
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{tableNo}")
    public TableDataInfo columnList(@PathVariable String tableNo)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        TableDataInfo dataInfo = new TableDataInfo();
        List<SysTabcolumn> list = tabColumnService.getRecordsByClassNo(loginUser.getUser().getAppCode(),tableNo);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构（保存）
     */
    @Oplog(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    public AjaxResult importTable(@RequestBody String tables)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String[] tableNames = ConvertHelper.toStrArray(tables);
        // 查询表信息
        List<SysTableinfo> tableList = tableInfoService.getDbTableListByNames(tableNames);
        tableInfoService.importTableInfo(loginUser.getUser().getAppCode(),tableList);
        return AjaxResult.success();
    }

    /**
     * 保存代码生成业务
     */
    @PreAuthorize("@ps.hasPermit('generate:tableinfo:save')")
    @Oplog(title = "代码生成", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysTableinfo sysTableInfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(tableInfoService.getRecordByNo(loginUser.getUser().getAppCode(),sysTableInfo.getTableNo()))) {
            sysTableInfo.setTableNo(UuidUtils.shortUUID());
            sysTableInfo.setCreateBy(loginUser.getUser().getUserNo());
            sysTableInfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(tableInfoService.AddNewRecord(loginUser.getUser().getAppCode(),sysTableInfo));
        } else {
            sysTableInfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(tableInfoService.UpdateRecord(loginUser.getUser().getAppCode(),sysTableInfo));
        }
    }

    /**
     * 删除表格信息
     */
    //@PreAuthorize("@ps.hasPermit('generate:tableinfo:delete')")
    @Oplog(title = "代码生成", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(tableInfoService.HardDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 代码生成信息详细信息
     */
    //@PreAuthorize("@ps.hasPermit('generate:tableinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(tableInfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出表格信息列表
     */
    //@PreAuthorize("@ps.hasPermit('generate:tableinfo:export')")
    @Oplog(title = "代码生成", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = tableInfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<SysTableinfo> list = tableInfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysTableinfo> util = new ExcelUtils<SysTableinfo>(SysTableinfo.class);
        return util.exportExcel(list, "SysTableinfo");
    }

    /**
     * 预览代码
     */
    @GetMapping("/preview/{tableNo}")
    public AjaxResult preview(@PathVariable("tableNo") String tableNo) throws IOException
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Map<String, String> dataMap = tableInfoService.previewCode(loginUser.getUser().getAppCode(),tableNo);
        return AjaxResult.success(dataMap);
    }

    /**
     * 生成代码
     */
    @Oplog(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    public void genCode(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        byte[] data = tableInfoService.generateCode(loginUser.getUser().getAppCode(),tableName);
        genCode(response, data);
    }

    /**
     * 批量生成代码
     */
    @Oplog(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String[] tableNames = ConvertHelper.toStrArray(tables);
        byte[] data = tableInfoService.generateCode(loginUser.getUser().getAppCode(),tableNames);
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