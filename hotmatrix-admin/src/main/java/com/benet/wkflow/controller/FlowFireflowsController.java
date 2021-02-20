package com.benet.wkflow.controller;

import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.fireflow.engine.*;
import com.benet.fireflow.engine.definition.WorkflowDefinition;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.wkflow.domain.FlowProcessinfo;
import com.benet.wkflow.service.IFlowProcessinfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 流程引擎Controller
 *
 * @author yoxking
 * @date 2020-05-23
 */
@Api(value = "wkflow/fireflows", tags = "流程引擎控制器")
@RestController
@RequestMapping("/wkflow/fireflows")
public class FlowFireflowsController extends BaseController {

    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlowProcessinfoService processinfoService;

    @Autowired
    private ConfigurableApplicationContext appContext;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 获取数据源信息详细信息
     */
    @GetMapping(value = "/test")
    public AjaxResult test()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        RuntimeContext rtCtx=(RuntimeContext)appContext.getBean("runtimeContext");
        rtCtx.setAppCode(loginUser.getUser().getAppCode());

        IWorkflowSession workflowSession=rtCtx.getWorkflowSession();

        try {

            // 1、创建流程实例
            IProcessInstance processInstance = workflowSession.createProcessInstance("LeaveApplicationProcess", "--");

            // 2、设置流程变量
            processInstance.setProcessInstanceVariable("sn","ss");
            processInstance.setProcessInstanceVariable("applicantId","ss");
            processInstance.setProcessInstanceVariable("leaveDays","2");

            // 3、启动流程实例
            processInstance.run();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return AjaxResult.success();
    }

    private  void testx(){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        FlowProcessinfo processInfo=new FlowProcessinfo();
        processInfo.setProcessNo(UuidUtils.shortUUID());
        processInfo.setProcessName("LeaveApplicationProcess");
        processInfo.setDisplayName("请假审批流程v1");
        processInfo.setProcessType(WorkflowDefinition.BPEL_PROCESS);
        processInfo.setProcessVersion(1L);
        processInfo.setProcessContent("");
        processInfo.setDescription("");
        processInfo.setUploadUser(loginUser.getUserno());
        processInfo.setUploadTime(DateUtils.getNowDate());
        processInfo.setPublishUser(loginUser.getUserno());
        processInfo.setPublishTime(DateUtils.getNowDate());
        processInfo.setPublishState("1");
        processInfo.setCheckState("1");

        processinfoService.AddNewRecord(loginUser.getUser().getAppCode(),processInfo);
    }
}
