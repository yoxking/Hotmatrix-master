package com.benet.fireflow.engine.persistence;

import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.fireflow.engine.IProcessInstance;
import com.benet.fireflow.engine.IWorkTaskInstance;
import com.benet.fireflow.engine.IWorkItemInstance;
import com.benet.fireflow.engine.RuntimeContext;
import com.benet.fireflow.engine.definition.WorkflowDefinition;
import com.benet.fireflow.engine.impl.*;
import com.benet.fireflow.kernel.ITokenInstance;
import com.benet.fireflow.kernel.impl.TokenInstance;
import com.benet.wkflow.domain.*;
import com.benet.wkflow.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefaultPersistenceService implements IPersistenceService{


    protected RuntimeContext rtCtx = null;

    @Autowired
    private IFlowProcessinfoService processinfoService;
    @Autowired
    private IFlowProcessflowService processflowService;
    @Autowired
    private IFlowWorktaskflowService taskflowService;
    @Autowired
    private IFlowWorkitemflowService itemflowService;
    @Autowired
    private IFlowHistorytraceService histraceService;
    @Autowired
    private IFlowProcesstokenService processtokenService;
    @Autowired
    private IFlowProcessvarsService processvarsService;


    public void setRuntimeContext(RuntimeContext ctx) {
        this.rtCtx = ctx;
    }

    public RuntimeContext getRuntimeContext() {
        return this.rtCtx;
    }

    /**
     * 流程实例
     * Save processInstance
     * @param processInstance
     */
    public void saveOrUpdateProcessInstance(IProcessInstance processInstance) {

        FlowProcessflow processflow = processflowService.getRecordByNo(rtCtx.getAppCode(), processInstance.getId());

        if (processflow == null) {
            processflow = new FlowProcessflow();
            processflow.setPflowNo(processInstance.getId());
            processflow.setProcessNo(processInstance.getProcessId());
            processflow.setProcessName(processInstance.getName());
            processflow.setProcessVersion(processInstance.getVersion().longValue());
            processflow.setDisplayName(processInstance.getDisplayName());
            processflow.setPflowState(processInstance.getState() + "");
            processflow.setSuspended("0");
            processflow.setCreatorNo(processInstance.getCreatorId());
            processflow.setCreatedTime(DateUtils.getNowDate());
            processflow.setStartedTime(DateUtils.getNowDate());
            processflow.setExpiredTime(DateUtils.getNowDate());
            processflow.setEndTime(DateUtils.getNowDate());
            processflow.setParentPflowNo(processInstance.getParentProcessInstanceId());
            processflow.setParentTflowNo(processInstance.getParentTaskInstanceId());
            processflow.setCheckState("1");

            processflowService.AddNewRecord(rtCtx.getAppCode(), processflow);

        } else {
            processflow.setProcessNo(processInstance.getProcessId());
            processflow.setProcessName(processInstance.getName());
            processflow.setProcessVersion(processInstance.getVersion().longValue());
            processflow.setDisplayName(processInstance.getDisplayName());
            processflow.setPflowState(processInstance.getState() + "");
            processflow.setStartedTime(processInstance.getStartedTime());
            processflow.setExpiredTime(processInstance.getExpiredTime());
            processflow.setEndTime(processInstance.getEndTime());
            processflow.setParentPflowNo(processInstance.getParentProcessInstanceId());
            processflow.setParentTflowNo(processInstance.getParentTaskInstanceId());
            processflowService.UpdateRecord(rtCtx.getAppCode(), processflow);
        }
    }


    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#saveTaskInstance(org.fireflow.engine.ITaskInstance)
     */
    public void saveOrUpdateWorkTaskInstance(IWorkTaskInstance taskInstance) {

        FlowWorktaskflow taskflow = taskflowService.getRecordByNo(rtCtx.getAppCode(), taskInstance.getId());

        if (taskflow == null) {
            taskflow=new FlowWorktaskflow();
            taskflow.setTflowNo(taskInstance.getId());
            taskflow.setPflowNo(taskInstance.getProcessInstanceId());
            taskflow.setProcessNo(taskInstance.getProcessId());
            taskflow.setProcessVersion(taskInstance.getVersion().longValue());
            taskflow.setBizType(taskInstance.getTaskType());
            taskflow.setTaskId(taskInstance.getTaskId());
            taskflow.setActivityId(taskInstance.getActivityId());
            taskflow.setTaskName(taskInstance.getName());
            taskflow.setDisplayName(taskInstance.getDisplayName());
            taskflow.setTaskType(taskInstance.getTaskType());
            taskflow.setTaskState(taskInstance.getState().toString());
            taskflow.setSuspended("0");
            taskflow.setCreatedTime(taskInstance.getCreatedTime());
            taskflow.setStartedTime(taskInstance.getStartedTime());
            taskflow.setExpiredTime(taskInstance.getExpiredTime());
            taskflow.setEndTime(taskInstance.getEndTime());
            taskflow.setAssignStrategy(taskInstance.getAssignmentStrategy());
            taskflow.setFromActivityId("");
            taskflow.setToActivityId(taskInstance.getTargetActivityId());
            taskflow.setStepNumber(taskInstance.getStepNumber().longValue());
            taskflow.setCanWithdrawn("0");
            taskflow.setCheckState("1");
            taskflowService.AddNewRecord(rtCtx.getAppCode(), taskflow);

        } else {
            taskflow.setPflowNo(taskInstance.getProcessInstanceId());
            taskflow.setProcessNo(taskInstance.getProcessId());
            taskflow.setProcessVersion(taskInstance.getVersion().longValue());
            taskflow.setBizType(taskInstance.getTaskType());
            taskflow.setTaskId(taskInstance.getTaskId());
            taskflow.setActivityId(taskInstance.getActivityId());
            taskflow.setTaskName(taskInstance.getName());
            taskflow.setDisplayName(taskInstance.getDisplayName());
            taskflow.setTaskType(taskInstance.getTaskType());
            taskflow.setTaskState(taskInstance.getState().toString());
            taskflow.setStartedTime(taskInstance.getStartedTime());
            taskflow.setExpiredTime(taskInstance.getExpiredTime());
            taskflow.setEndTime(taskInstance.getEndTime());
            taskflow.setAssignStrategy(taskInstance.getAssignmentStrategy());
            taskflow.setToActivityId(taskInstance.getTargetActivityId());
            taskflow.setStepNumber(taskInstance.getStepNumber().longValue());
            taskflowService.UpdateRecord(rtCtx.getAppCode(), taskflow);
        }
    }


    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#saveOrUpdateWorkItem(org.fireflow.engine.IWorkItem)
     */
    public void saveOrUpdateWorkItemInstance(IWorkItemInstance itemInstance) {

        FlowWorkitemflow itemflow = itemflowService.getRecordByNo(rtCtx.getAppCode(), itemInstance.getId());

        if (itemflow == null) {
            itemflow = new FlowWorkitemflow();
            itemflow.setIflowNo(itemInstance.getId());
            itemflow.setTflowNo(itemInstance.getTaskInstance().getId());
            itemflow.setItemState(itemInstance.getState() + "");
            itemflow.setCreatedTime(itemInstance.getCreatedTime());
            itemflow.setClaimedTime(itemInstance.getClaimedTime());
            itemflow.setEndTime(itemInstance.getEndTime());
            itemflow.setActorNo(itemInstance.getActorId());
            itemflow.setCheckState("1");
            itemflowService.AddNewRecord(rtCtx.getAppCode(), itemflow);

        } else {
            itemflow.setTflowNo(itemInstance.getTaskInstance().getId());
            itemflow.setItemState(itemInstance.getState() + "");
            itemflow.setClaimedTime(itemInstance.getClaimedTime());
            itemflow.setEndTime(itemInstance.getEndTime());
            itemflow.setActorNo(itemInstance.getActorId());
            itemflowService.UpdateRecord(rtCtx.getAppCode(), itemflow);
        }
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#saveOrUpdateToken(org.fireflow.kernel.IToken)
     */
    public void saveOrUpdateTokenInstance(ITokenInstance tokenInstance) {

        FlowProcesstoken processToken = processtokenService.getRecordByNo(rtCtx.getAppCode(), tokenInstance.getId());

        if (processToken == null) {
            processToken = new FlowProcesstoken();
            processToken.setTokenNo(UuidUtils.shortUUID());
            processToken.setPflowNo(tokenInstance.getProcessInstanceId());
            processToken.setTokenAlive("1");
            processToken.setTokenValue(tokenInstance.getValue().longValue());
            processToken.setNodeId(tokenInstance.getNodeId());
            processToken.setStepNumber(tokenInstance.getStepNumber().longValue());
            processToken.setFromActivityId(tokenInstance.getFromActivityId());
            processToken.setCheckState("1");
            processtokenService.AddNewRecord(rtCtx.getAppCode(), processToken);

        } else {
            processToken.setPflowNo(tokenInstance.getProcessInstanceId());
            processToken.setTokenValue(tokenInstance.getValue().longValue());
            processToken.setNodeId(tokenInstance.getNodeId());
            processToken.setStepNumber(tokenInstance.getStepNumber().longValue());
            processToken.setFromActivityId(tokenInstance.getFromActivityId());
            processtokenService.UpdateRecord(rtCtx.getAppCode(), processToken);
        }
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#getAliveTokenCountForNode(java.lang.String, java.lang.String)
     */
    public Integer getAliveTokenCountForNode(String processInstanceId, String nodeId) {

        String condition=" pflow_no='"+processInstanceId+"' And node_id='"+nodeId+"' And token_alive='1' ";
        int count=processtokenService.getCountByCondition(rtCtx.getAppCode(),condition);
        return count;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#getCompletedTaskInstanceCountForTask(java.lang.String, java.lang.String)
     */
    public Integer getCompletedTaskInstanceCountForTask(String processInstanceId, String taskId) {

        String condition=" tflow_no='"+taskId+"' And pflow_no='"+processInstanceId+"' And task_state='"+ IWorkTaskInstance.COMPLETED+"'";
        int count=taskflowService.getCountByCondition(rtCtx.getAppCode(),condition);
        return count;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#getAliveTaskInstanceCountForActivity(java.lang.String, java.lang.String)
     */
    public Integer getAliveTaskInstanceCountForActivity(String processInstanceId, String activityId) {


        String condition=" activity_id='"+activityId+"' And pflow_no='"+processInstanceId+"' And  task_state in ('"+ IWorkTaskInstance.INITIALIZED+"','"+ IWorkTaskInstance.RUNNING+"')";
        int count=taskflowService.getCountByCondition(rtCtx.getAppCode(),condition);
        return count;
    }

    /**
     * 获得同一个Token的所有状态为Initialized的TaskInstance
     * @param processInstanceId
     * @param tokenId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<IWorkTaskInstance> findInitializedTaskInstancesListForToken(String processInstanceId, String tokenId) {

        String condition=" pflow_no='"+processInstanceId+"' And  task_state='0'";
        List<FlowWorktaskflow>  tflowsList=taskflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(tflowsList!=null&&tflowsList.size()>0){

            List<IWorkTaskInstance> resultList=new ArrayList<>();
            IWorkTaskInstance taskInstance=null;
            for(FlowWorktaskflow taskflow:tflowsList){
                taskInstance=toTaskInstance(taskflow);

                resultList.add(taskInstance);
            }
            return resultList;
        }else{
            return null;
        }
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findTaskInstancesForProcessInstance(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<IWorkTaskInstance> findTaskInstancesForProcessInstance(java.lang.String processInstanceId,
                                                                       String activityId) {


        String condition=" pflow_no='"+processInstanceId+"' And  activity_id='"+activityId+"'";
        List<FlowWorktaskflow>  tflowsList=taskflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(tflowsList!=null&&tflowsList.size()>0){

            List<IWorkTaskInstance> resultList=new ArrayList<>();
            IWorkTaskInstance taskInstance=null;
            for(FlowWorktaskflow taskflow:tflowsList){
                taskInstance=toTaskInstance(taskflow);

                resultList.add(taskInstance);
            }
            return resultList;
        }else{
            return null;
        }
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findTaskInstancesForProcessInstanceByStepNumber(java.lang.String, java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public List<IWorkTaskInstance> findTaskInstancesForProcessInstanceByStepNumber(String processInstanceId, Integer stepNumber) {


        String condition=" pflow_no='"+processInstanceId+"' And  step_number="+stepNumber;
        List<FlowWorktaskflow>  tflowsList=taskflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(tflowsList!=null&&tflowsList.size()>0){

            List<IWorkTaskInstance> resultList=new ArrayList<>();
            IWorkTaskInstance taskInstance=null;
            for(FlowWorktaskflow taskflow:tflowsList){
                taskInstance=toTaskInstance(taskflow);

                resultList.add(taskInstance);
            }
            return resultList;
        }else{
            return null;
        }
    }
    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#lockTaskInstance(java.lang.String)
     */
    public void lockTaskInstance(String taskInstanceId){
        /*this.getHibernateTemplate().get(TaskInstance.class, taskInstanceId,LockMode.UPGRADE);

        FlowWorktaskflow taskflow = taskflowService.getRecordByNo(rtCtx.getAppCode(), taskInstanceId);

        if (taskflow != null) {

            taskflow.setTaskState();
            taskflowService.UpdateRecord(rtCtx.getAppCode(), taskflow);
        }*/
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findTokenById(java.lang.String)
     */
    public ITokenInstance findTokenById(String tokenId) {
        FlowProcesstoken processToken=processtokenService.getRecordByNo(rtCtx.getAppCode(),tokenId);

        return toTokenInstance(processToken);
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#deleteTokensForNodes(java.lang.String, java.util.List)
     */
    public void deleteTokensForNodes(String processInstanceId, List<String> nodeIdsList) {

        String condition="pflow_no='"+processInstanceId+"' And node_id in ("+StringUtils.listToString(nodeIdsList)+")";
        processtokenService.HardDeleteByCondition(rtCtx.getAppCode(),condition);
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#deleteTokensForNode(java.lang.String, java.lang.String)
     */
    public void deleteTokensForNode(String processInstanceId, String nodeId) {

        String condition="pflow_no='"+processInstanceId+"' And node_id ='"+nodeId+"'";
        processtokenService.HardDeleteByCondition(rtCtx.getAppCode(),condition);
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#deleteToken(org.fireflow.kernel.IToken)
     */
    public void deleteToken(ITokenInstance tokenInfo) {
        processtokenService.HardDeleteByNo(rtCtx.getAppCode(),tokenInfo.getId());
    }


    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findTokensForProcessInstance(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<ITokenInstance> findTokensForProcessInstance(String processInstanceId, String nodeId) {


        String condition=" pflow_no='"+processInstanceId+"' And  node_id='"+nodeId+"'";
        List<FlowProcesstoken>  ptokenList=processtokenService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(ptokenList!=null&&ptokenList.size()>0){

            List<ITokenInstance> resultList=new ArrayList<>();
            ITokenInstance tokenInfo=null;
            for(FlowProcesstoken ptoken:ptokenList){
                tokenInfo=toTokenInstance(ptoken);

                resultList.add(tokenInfo);
            }
            return resultList;
        }else{
            return null;
        }
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findWorkItemById(java.lang.String)
     */
    public IWorkItemInstance findWorkItemById(String itemId) {
        FlowWorkitemflow itemFlow=itemflowService.getRecordByNo(rtCtx.getAppCode(),itemId);

        return toItemInstance(itemFlow);
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findAliveTaskInstanceById(java.lang.String)
     */
    public IWorkTaskInstance findAliveTaskInstanceById(String taskInstanceId) {

        String condition=" tflow_no='"+taskInstanceId+"' And  task_state in('"+ IWorkTaskInstance.INITIALIZED+"','"+ IWorkTaskInstance.RUNNING+"')";
        List<FlowWorktaskflow>  tflowsList=taskflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(tflowsList!=null&&tflowsList.size()>0){
            return toTaskInstance(tflowsList.get(0));
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findTaskInstanceById(java.lang.String)
     */
    public IWorkTaskInstance findTaskInstanceById(String taskInstanceId) {
        FlowWorktaskflow taskflow=taskflowService.getRecordByNo(rtCtx.getAppCode(),taskInstanceId);

        return toTaskInstance(taskflow);
    }


    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#abortTaskInstance(org.fireflow.engine.impl.TaskInstance)
     */
    public void abortTaskInstance(WorkTaskInstance taskInstance) {

        FlowWorktaskflow taskflow=taskflowService.getRecordByNo(rtCtx.getAppCode(),taskInstance.getId());
        if(taskflow!=null){

            Date now = rtCtx.getCalendarService().getSysDate();

            taskflow.setTaskState(IWorkTaskInstance.CANCELED+"");
            taskflow.setEndTime(now);
            taskflow.setCanWithdrawn("0");

            taskflowService.UpdateRecord(rtCtx.getAppCode(),taskflow);

            String condition="tflow_no='"+taskInstance.getId()+"' And item_state in ('0','1')";
            List<FlowWorkitemflow> itemFlows=itemflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");
            if(itemFlows!=null&&itemFlows.size()>0){
                for(FlowWorkitemflow itemflow:itemFlows){

                    itemflow.setItemState(IWorkItemInstance.CANCELED+"");
                    itemflow.setEndTime(now);
                    itemflow.setTflowNo(taskInstance.getId());

                    itemflowService.UpdateRecord(rtCtx.getAppCode(),itemflow);
                }
            }
        }
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#getAliveWorkItemCountForTaskInstance(java.lang.String)
     */
    public Integer getAliveWorkItemCountForTaskInstance(String taskInstanceId) {

        String condition="tflow_no='"+taskInstanceId+"' And item_state in ('0','1','3')";
        int count=itemflowService.getCountByCondition(rtCtx.getAppCode(),condition);
        return count;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findCompletedWorkItemsForTaskInstance(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<IWorkItemInstance> findCompletedWorkItemsForTaskInstance(String taskInstanceId) {

        String condition="tflow_no='"+taskInstanceId+"' And item_state ='"+ IWorkItemInstance.COMPLETED+"')";
        List<FlowWorkitemflow> itemFlows=itemflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");
        if(itemFlows!=null&&itemFlows.size()>0){
            List<IWorkItemInstance> itemList=new ArrayList<>();
            for(FlowWorkitemflow itemflow:itemFlows){

                itemList.add(toItemInstance(itemflow));
            }
            return itemList;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findWorkItemsForTaskInstance(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<IWorkItemInstance> findWorkItemsForTaskInstance(String taskInstanceId){

        List<FlowWorkitemflow> itemFlows=itemflowService.getRecordsByClassNo(rtCtx.getAppCode(),taskInstanceId);
        if(itemFlows!=null&&itemFlows.size()>0){
            List<IWorkItemInstance> itemList=new ArrayList<>();
            for(FlowWorkitemflow itemflow:itemFlows){

                itemList.add(toItemInstance(itemflow));
            }
            return itemList;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findWorkItemsForTask(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<IWorkItemInstance> findWorkItemsForTask(String taskid) {

        List<FlowWorkitemflow> itemFlows=itemflowService.getRecordsByClassNo(rtCtx.getAppCode(),taskid);
        if(itemFlows!=null&&itemFlows.size()>0){
            List<IWorkItemInstance> itemList=new ArrayList<>();
            for(FlowWorkitemflow itemflow:itemFlows){

                itemList.add(toItemInstance(itemflow));
            }
            return itemList;
        }

        return null;
    }


    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findProcessInstancesByProcessId(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<IProcessInstance> findProcessInstancesByProcessId(String processId) {

        List<FlowProcessflow> processflows=processflowService.getRecordsByClassNo(rtCtx.getAppCode(),processId);
        if(processflows!=null&&processflows.size()>0){
            List<IProcessInstance> processInstances=new ArrayList<>();
            for(FlowProcessflow processflow:processflows){

                processInstances.add(toProcessInstance(processflow));
            }
            return processInstances;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findProcessInstancesByProcessIdAndVersion(java.lang.String, java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public List<IProcessInstance> findProcessInstancesByProcessIdAndVersion(String processId, Integer version) {

        String condition=" process_no='"+processId+"' And process_version= "+version;
        List<FlowProcessflow> processflows=processflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"created_time","asc");

        if(processflows!=null&&processflows.size()>0){
            List<IProcessInstance> processInstances=new ArrayList<>();
            for(FlowProcessflow processflow:processflows){

                processInstances.add(toProcessInstance(processflow));
            }
            return processInstances;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findProcessInstanceById(java.lang.String)
     */
    public IProcessInstance findProcessInstanceById(String processInstanceId) {
        FlowProcessflow processflow=processflowService.getRecordByNo(rtCtx.getAppCode(),processInstanceId);

        return toProcessInstance(processflow);
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findAliveProcessInstanceById(java.lang.String)
     */
    public IProcessInstance findAliveProcessInstanceById(String processInstanceId) {


        String condition=" pflow_no='"+processInstanceId+"' And pflow_state in ('"+IProcessInstance.INITIALIZED+"','"+IProcessInstance.RUNNING+"') ";
        List<FlowProcessflow> processflows=processflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"created_time","asc");

        if(processflows!=null&&processflows.size()>0){

            return toProcessInstance(processflows.get(0));
        }

        return null;
    }


    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#saveOrUpdateWorkflowDefinition(org.fireflow.engine.definition.WorkflowDefinition)
     */
    public void saveOrUpdateWorkflowDefinition(WorkflowDefinition workflowDef) {

        int ver=this.findTheLatestVersionNumberIgnoreState(workflowDef.getName());

        FlowProcessinfo processInfo = processinfoService.getRecordByNo(rtCtx.getAppCode(), workflowDef.getId());

        if(processInfo==null) {
            processInfo = new FlowProcessinfo();
            processInfo.setProcessNo(UuidUtils.shortUUID());
            processInfo.setProcessName(workflowDef.getName());
            processInfo.setProcessVersion(workflowDef.getVersion().longValue());
            processInfo.setDisplayName(workflowDef.getDisplayName());
            processInfo.setProcessType(workflowDef.getDefinitionType());
            processInfo.setProcessContent(workflowDef.getProcessContent());
            processInfo.setDescription(workflowDef.getDescription());
            processInfo.setUploadUser(workflowDef.getUploadUser());
            processInfo.setUploadTime(workflowDef.getUploadTime());
            processInfo.setPublishUser(workflowDef.getPublishUser());
            processInfo.setPublishTime(workflowDef.getPublishTime());
            processInfo.setPublishState(workflowDef.getState() ? "1" : "0");
            processInfo.setCheckState("1");
            processInfo.setProcessVersion((long) (ver + 1));
            processinfoService.AddNewRecord(rtCtx.getAppCode(), processInfo);
        }
        else{
            processInfo.setProcessName(workflowDef.getName());
            processInfo.setProcessVersion(workflowDef.getVersion().longValue());
            processInfo.setDisplayName(workflowDef.getDisplayName());
            processInfo.setProcessType(workflowDef.getDefinitionType());
            processInfo.setProcessContent(workflowDef.getProcessContent());
            processInfo.setDescription(workflowDef.getDescription());
            processInfo.setUploadUser(workflowDef.getUploadUser());
            processInfo.setUploadTime(workflowDef.getUploadTime());
            processInfo.setPublishUser(workflowDef.getPublishUser());
            processInfo.setPublishTime(workflowDef.getPublishTime());
            processInfo.setPublishState(workflowDef.getState() ? "1" : "0");
            processInfo.setProcessVersion(workflowDef.getVersion().longValue());
            processinfoService.UpdateRecord(rtCtx.getAppCode(), processInfo);
        }

    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findTheLatestVersionNumber(java.lang.String)
     */
    public Integer findTheLatestVersionNumber(String processName) {

        String condition="process_name='"+processName+"' And publish_state='1' ";
        List<FlowProcessinfo> pinfoList=processinfoService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"process_version","desc");

        if(pinfoList!=null&&pinfoList.size()>0){
            return pinfoList.get(0).getProcessVersion().intValue();
        }

        return 0;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findTheLatestVersionNumberIgnoreState(java.lang.String)
     */
    public Integer findTheLatestVersionNumberIgnoreState(String processName){
        String condition="process_name='"+processName+"' ";
        List<FlowProcessinfo> pinfoList=processinfoService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"process_version","desc");

        if(pinfoList!=null&&pinfoList.size()>0){
            return pinfoList.get(0).getProcessVersion().intValue();
        }

        return 0;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findWorkflowDefinitionById(java.lang.String)
     */
    public WorkflowDefinition findWorkflowDefinitionByProcessName(String processName) {

        String condition="process_name='"+processName+"' ";
        List<FlowProcessinfo> pinfoList=processinfoService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"process_version","desc");

        if(pinfoList!=null&&pinfoList.size()>0){
            return toDefinitionInfo(pinfoList.get(0));
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findWorkflowDefinitionByProcessIdAndVersionNumber(java.lang.String, int)
     */
    public WorkflowDefinition findWorkflowDefinitionByProcessNameAndVersionNumber(String processName, int version) {

        String condition="process_name='"+processName+"' And process_version="+version;
        List<FlowProcessinfo> pinfoList=processinfoService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(pinfoList!=null&&pinfoList.size()>0){
            return toDefinitionInfo(pinfoList.get(0));
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findTheLatestVersionOfWorkflowDefinitionByProcessId(java.lang.String)
     */
    public WorkflowDefinition findTheLatestVersionOfWorkflowDefinitionByProcessName(String processName) {
        Integer latestVersion = this.findTheLatestVersionNumber(processName);
        return this.findWorkflowDefinitionByProcessNameAndVersionNumber(processName, latestVersion);
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findWorkflowDefinitionsByProcessId(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<WorkflowDefinition> findWorkflowDefinitionsByProcessName(String processName) {

        String condition="process_name='"+processName+"' ";
        List<FlowProcessinfo> pinfoList=processinfoService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(pinfoList!=null&&pinfoList.size()>0){
            List<WorkflowDefinition> definitionList=new ArrayList<>();
            for(FlowProcessinfo pinfo:pinfoList){
                definitionList.add(toDefinitionInfo(pinfo));
            }

            return definitionList;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findAllTheLatestVersionsOfWorkflowDefinition()
     */
    @SuppressWarnings("unchecked")
    public List<WorkflowDefinition> findAllTheLatestVersionsOfWorkflowDefinition() {

        String condition="";
        List<FlowProcessinfo> pinfoList=processinfoService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(pinfoList!=null&&pinfoList.size()>0){
            List<WorkflowDefinition> definitionList=new ArrayList<>();
            for(FlowProcessinfo pinfo:pinfoList){
                definitionList.add(toDefinitionInfo(pinfo));
            }

            return definitionList;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findTodoWorkItems(java.lang.String)
     */
    public List<IWorkItemInstance> findTodoWorkItems(String actorId) {
        return findTodoWorkItems(actorId, null);
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findTodoWorkItems(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<IWorkItemInstance> findTodoWorkItems(String actorId, String processInstanceId) {

        String condition=" actor_no='"+actorId+"' And item_state in ('"+ IWorkItemInstance.INITIALIZED+"','"+ IWorkItemInstance.RUNNING+"')";
        List<FlowWorkitemflow> itemflowsList=itemflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(itemflowsList!=null&&itemflowsList.size()>0){
            List<IWorkItemInstance> workitemList=new ArrayList<>();
            for(FlowWorkitemflow itemflow:itemflowsList){
                workitemList.add(toItemInstance(itemflow));
            }

            return workitemList;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findTodoWorkItems(java.lang.String, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<IWorkItemInstance> findTodoWorkItems(final String actorId, final String processId, final String taskId) {

        String condition=" actor_no='"+actorId+"' And tflow_no='"+taskId+"' And item_state in ('"+ IWorkItemInstance.INITIALIZED+"','"+ IWorkItemInstance.RUNNING+"')";
        List<FlowWorkitemflow> itemflowsList=itemflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(itemflowsList!=null&&itemflowsList.size()>0){
            List<IWorkItemInstance> workitemList=new ArrayList<>();
            for(FlowWorkitemflow itemflow:itemflowsList){
                workitemList.add(toItemInstance(itemflow));
            }

            return workitemList;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findHaveDoneWorkItems(java.lang.String)
     */
    public List<IWorkItemInstance> findHaveDoneWorkItems(final String actorId) {
        return findHaveDoneWorkItems(actorId, null);
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findHaveDoneWorkItems(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<IWorkItemInstance> findHaveDoneWorkItems(final String actorId, final String processInstanceId) {


        String condition=" actor_no='"+actorId+"' And item_state in ('"+ IWorkItemInstance.COMPLETED+"','"+ IWorkItemInstance.CANCELED+"')";
        List<FlowWorkitemflow> itemflowsList=itemflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(itemflowsList!=null&&itemflowsList.size()>0){
            List<IWorkItemInstance> workitemList=new ArrayList<>();
            for(FlowWorkitemflow itemflow:itemflowsList){
                workitemList.add(toItemInstance(itemflow));
            }

            return workitemList;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findHaveDoneWorkItems(java.lang.String, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<IWorkItemInstance> findHaveDoneWorkItems(final String actorId, final String processId, final String taskId) {


        String condition=" actor_no='"+actorId+"' And tflow_no='"+taskId+"' And item_state in ('"+ IWorkItemInstance.COMPLETED+"','"+ IWorkItemInstance.CANCELED+"')";
        List<FlowWorkitemflow> itemflowsList=itemflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(itemflowsList!=null&&itemflowsList.size()>0){
            List<IWorkItemInstance> workitemList=new ArrayList<>();
            for(FlowWorkitemflow itemflow:itemflowsList){
                workitemList.add(toItemInstance(itemflow));
            }

            return workitemList;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#deleteWorkItemsInInitializedState(java.lang.String)
     */
    public void deleteWorkItemsInInitializedState(final String taskInstanceId) {

        String condition=" tflow_no='"+taskInstanceId+"' and item_state='0'";
        itemflowService.HardDeleteByCondition(rtCtx.getAppCode(),condition);
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#getAliveProcessInstanceCountForParentTaskInstance(java.lang.String)
     */
    public Integer getAliveProcessInstanceCountForParentTaskInstance(final String taskInstanceId) {

        String condition=" parent_tflow_no='"+taskInstanceId+"' and pflow_state in ('"+IProcessInstance.INITIALIZED+"','"+IProcessInstance.RUNNING+"')";
        return processflowService.getCountByCondition(rtCtx.getAppCode(),condition);

    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#suspendProcessInstance(org.fireflow.engine.impl.ProcessInstance)
     */
    public void suspendProcessInstance(ProcessInstance processInstance) {

        String condition=" pflow_no='"+processInstance.getProcessId()+"' And pflow_state in ('0','1')";
        List<FlowProcessflow> processflowList=processflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(processflowList!=null&&processflowList.size()>0){

            for(FlowProcessflow processflow:processflowList){

                processflow.setSuspended("1");
                processflowService.UpdateRecord(rtCtx.getAppCode(),processflow);
            }
        }
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#restoreProcessInstance(org.fireflow.engine.impl.ProcessInstance)
     */
    public void restoreProcessInstance(ProcessInstance processInstance) {

        String condition=" pflow_no='"+processInstance.getProcessId()+"' And pflow_state in ('0','1')";
        List<FlowProcessflow> processflowList=processflowService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(processflowList!=null&&processflowList.size()>0){

            for(FlowProcessflow processflow:processflowList){

                processflow.setSuspended("0");
                processflowService.UpdateRecord(rtCtx.getAppCode(),processflow);
            }
        }
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#abortProcessInstance(org.fireflow.engine.impl.ProcessInstance)
     */
    public void abortProcessInstance(final ProcessInstance processInstance) {


        Date now = rtCtx.getCalendarService().getSysDate();

        String condition = " pflow_no='" + processInstance.getProcessId() + "' And pflow_state in ('0','1')";
        List<FlowProcessflow> processflowList = processflowService.getRecordsByPaging(rtCtx.getAppCode(), 1, 100, condition, "id", "asc");

        if (processflowList != null && processflowList.size() > 0) {

            for (FlowProcessflow processflow : processflowList) {

                processflow.setPflowState(IProcessInstance.CANCELED + "");
                processflow.setEndTime(now);
                processflowService.UpdateRecord(rtCtx.getAppCode(), processflow);
            }
        }

//更新所有的任务实例状态为canceled
        condition = " pflow_no='" + processInstance.getId() + "' And pflow_state in ('0','1')";
        List<FlowWorktaskflow> taskflowList = taskflowService.getRecordsByPaging(rtCtx.getAppCode(), 1, 100, condition, "id", "asc");

        if (taskflowList != null && taskflowList.size() > 0) {

            for (FlowWorktaskflow taskflow : taskflowList) {

                taskflow.setTaskState(IWorkTaskInstance.CANCELED + "");
                taskflow.setEndTime(now);
                taskflow.setCanWithdrawn("0");
                taskflow.setPflowNo(processInstance.getId());
                taskflowService.UpdateRecord(rtCtx.getAppCode(), taskflow);
            }
        }

//更新所有工作项的状态为canceled
        condition = "  pflow_state in ('0','1')";
        List<FlowWorkitemflow> itemflowList = itemflowService.getRecordsByPaging(rtCtx.getAppCode(), 1, 100, condition, "id", "asc");

        if (itemflowList != null && itemflowList.size() > 0) {

            for (FlowWorkitemflow itemflow : itemflowList) {

                itemflow.setItemState(IWorkItemInstance.CANCELED + "");
                itemflow.setEndTime(now);
                itemflowService.UpdateRecord(rtCtx.getAppCode(), itemflow);
            }
        }

//删除所有的token
        condition = " pflow_no='" + processInstance.getId() + "'";
        processtokenService.HardDeleteByCondition(rtCtx.getAppCode(), condition);

    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#saveOrUpdateProcessInstanceTrace(org.fireflow.engine.impl.ProcessInstanceTrace)
     */
    public void saveOrUpdateProcessInstanceTrace(ProcessInstanceTrace instanceTrace) {

        FlowHistorytrace historyTrace = histraceService.getRecordByNo(rtCtx.getAppCode(), instanceTrace.getId());

        if (historyTrace == null) {
            historyTrace = new FlowHistorytrace();
            historyTrace.setTraceNo(UuidUtils.shortUUID());
            historyTrace.setPflowNo(instanceTrace.getProcessInstanceId());
            historyTrace.setStepNumber(instanceTrace.getStepNumber().longValue());
            historyTrace.setMinorNumber(instanceTrace.getMinorNumber().longValue());
            historyTrace.setTraceType(instanceTrace.getType());
            historyTrace.setEdgeId(instanceTrace.getEdgeId());
            historyTrace.setFromNodeId(instanceTrace.getFromNodeId());
            historyTrace.setToNodeId(instanceTrace.getToNodeId());
            historyTrace.setCheckState("1");
            histraceService.AddNewRecord(rtCtx.getAppCode(), historyTrace);
        } else {
            historyTrace.setPflowNo(instanceTrace.getProcessInstanceId());
            historyTrace.setStepNumber(instanceTrace.getStepNumber().longValue());
            historyTrace.setMinorNumber(instanceTrace.getMinorNumber().longValue());
            historyTrace.setTraceType(instanceTrace.getType());
            historyTrace.setEdgeId(instanceTrace.getEdgeId());
            historyTrace.setFromNodeId(instanceTrace.getFromNodeId());
            historyTrace.setToNodeId(instanceTrace.getToNodeId());
            histraceService.UpdateRecord(rtCtx.getAppCode(), historyTrace);
        }
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.persistence.IPersistenceService#findProcessInstanceTraces(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<ProcessInstanceTrace> findProcessInstanceTraces(String processInstanceId){

        String condition="  pflow_no ='"+processInstanceId+"'";
        List<FlowHistorytrace> histraceList=histraceService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(histraceList!=null&&histraceList.size()>0){

            List<ProcessInstanceTrace> instraceList=new ArrayList<>();
            for(FlowHistorytrace histrace:histraceList){

                instraceList.add(toTraceInstance(histrace));
            }
            return instraceList;
        }
        return null;
    }

    public List<ProcessInstanceVar> findProcessInstanceVariable(String processInstanceId){


        String condition="  pflow_no ='"+processInstanceId+"'";
        List<FlowProcessvars> processvarsList=processvarsService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(processvarsList!=null&&processvarsList.size()>0){

            List<ProcessInstanceVar> instvarsList=new ArrayList<>();
            for(FlowProcessvars processvar:processvarsList){

                instvarsList.add(toVariableInstance(processvar));
            }
            return instvarsList;
        }
        return null;
    }

    public ProcessInstanceVar findProcessInstanceVariable(String processInstanceId,String name){

        String condition="  pflow_no ='"+processInstanceId+"' And pvars_name='"+name+"'";
        List<FlowProcessvars> processvarsList=processvarsService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(processvarsList!=null&&processvarsList.size()>0){

            return toVariableInstance(processvarsList.get(0));
        }
        return null;
    }

    public void updateProcessInstanceVariable(ProcessInstanceVar instanceVar){


        String condition="  pflow_no ='"+instanceVar.getProcessInstanceId()+"' And pvars_name='"+instanceVar.getName()+"'";
        List<FlowProcessvars> processvarsList=processvarsService.getRecordsByPaging(rtCtx.getAppCode(),1,100,condition,"id","asc");

        if(processvarsList!=null&&processvarsList.size()>0){

            for(FlowProcessvars processvar:processvarsList){
                processvar.setPvarsValue(instanceVar.getValue().toString());
                processvar.setValueType(instanceVar.getValueType());
                processvarsService.UpdateRecord(rtCtx.getAppCode(),processvar);
            }
        }
    }

    public void saveProcessInstanceVariable(ProcessInstanceVar instanceVar){

        FlowProcessvars processVar=new FlowProcessvars();
        processVar.setPvarsNo(UuidUtils.shortUUID());
        processVar.setPflowNo(instanceVar.getProcessInstanceId());
        processVar.setPvarsName(instanceVar.getName());
        processVar.setPvarsValue(instanceVar.getValue().toString());
        processVar.setValueType(instanceVar.getValueType());
        processVar.setCheckState("1");

        processvarsService.AddNewRecord(rtCtx.getAppCode(),processVar);
    }

    public List<IWorkItemInstance> findHaveDoneWorkItems(String actorId, String publishUser, int pageSize, int pageNumber)
            throws RuntimeException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public List<IProcessInstance> findProcessInstanceListByCreatorId(String creatorId, String publishUser,
                                                                     int pageSize, int pageNumber) throws RuntimeException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public List<IProcessInstance> findProcessInstanceListByPublishUser(String publishUser, int pageSize, int pageNumber)
            throws RuntimeException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public List<IWorkItemInstance> findTodoWorkItems(String actorId, String publishUser, int pageSize, int pageNumber)
            throws RuntimeException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer getHaveDoneWorkItemsCount(String actorId, String publishUser) throws RuntimeException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer getProcessInstanceCountByCreatorId(String creatorId, String publishUser) throws RuntimeException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer getProcessInstanceCountByPublishUser(String publishUser) throws RuntimeException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer getTodoWorkItemsCount(String actorId, String publishUser) throws RuntimeException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /***
     * 实体与数据表转换方法
     */
    private FlowProcessflow toProcessFlow1(IProcessInstance processInstance){

        FlowProcessflow processflow=new FlowProcessflow();
        processflow.setPflowNo(processInstance.getId());
        if(StringUtils.isEmpty(processflow.getPflowNo())) {
            processflow.setPflowNo(UuidUtils.shortUUID());
        }
        processflow.setProcessNo(processInstance.getProcessId());
        processflow.setProcessName(processInstance.getName());
        processflow.setProcessVersion(processInstance.getVersion().longValue());
        processflow.setDisplayName(processInstance.getDisplayName());
        processflow.setPflowState(processInstance.getState()+"");
        processflow.setSuspended("0");
        processflow.setCreatorNo(processInstance.getCreatorId());
        processflow.setCreatedTime(DateUtils.getNowDate());
        processflow.setStartedTime(DateUtils.getNowDate());
        processflow.setExpiredTime(DateUtils.getNowDate());
        processflow.setEndTime(DateUtils.getNowDate());
        processflow.setParentPflowNo(processInstance.getParentProcessInstanceId());
        processflow.setParentTflowNo(processInstance.getParentTaskInstanceId());
        processflow.setCheckState("1");

        return processflow;
    }
    private IProcessInstance toProcessInstance(FlowProcessflow processFlow){
        ProcessInstance processInstance=new ProcessInstance();

        processInstance.setId(processFlow.getPflowNo());
        processInstance.setProcessId(processFlow.getProcessNo());
        processInstance.setVersion(processFlow.getProcessVersion().intValue());
        processInstance.setName(processFlow.getProcessName());
        processInstance.setDisplayName(processFlow.getDisplayName());
        processInstance.setState(Integer.parseInt(processFlow.getPflowState()));
        processInstance.setSuspended(processFlow.getSuspended().equals("1")?true:false);
        processInstance.setCreatorId(processFlow.getCreatorNo());
        processInstance.setCreatedTime(processFlow.getCreatedTime());
        processInstance.setStartedTime(processFlow.getStartedTime());
        processInstance.setEndTime(processFlow.getEndTime());
        processInstance.setExpiredTime(processFlow.getExpiredTime());
        processInstance.setParentProcessInstanceId(processFlow.getParentPflowNo());
        processInstance.setParentTaskInstanceId(processFlow.getParentTflowNo());

        return processInstance;
    }
    private FlowWorktaskflow toTaskFlow1(IWorkTaskInstance taskInstance){

        FlowWorktaskflow taskflow=new FlowWorktaskflow();
        taskflow.setTflowNo(taskInstance.getId());
        if(StringUtils.isEmpty(taskflow.getTflowNo())) {
            taskflow.setTflowNo(UuidUtils.shortUUID());
        }
        taskflow.setPflowNo(taskInstance.getProcessInstanceId());
        taskflow.setProcessNo(taskInstance.getProcessId());
        taskflow.setProcessVersion(taskInstance.getVersion().longValue());
        taskflow.setBizType(taskInstance.getTaskType());
        taskflow.setActivityId(taskInstance.getActivityId());
        taskflow.setTaskName(taskInstance.getName());
        taskflow.setDisplayName(taskInstance.getDisplayName());
        taskflow.setTaskType(taskInstance.getTaskType());
        taskflow.setTaskState(taskInstance.getState().toString());
        taskflow.setSuspended("0");
        taskflow.setCreatedTime(taskInstance.getCreatedTime());
        taskflow.setStartedTime(taskInstance.getStartedTime());
        taskflow.setExpiredTime(taskInstance.getExpiredTime());
        taskflow.setEndTime(taskInstance.getEndTime());
        taskflow.setAssignStrategy(taskInstance.getAssignmentStrategy());
        taskflow.setFromActivityId("");
        taskflow.setToActivityId(taskInstance.getTargetActivityId());
        taskflow.setStepNumber(taskInstance.getStepNumber().longValue());
        taskflow.setCanWithdrawn("0");
        taskflow.setCheckState("1");

        return taskflow;
    }
    private IWorkTaskInstance toTaskInstance(FlowWorktaskflow taskFlow){
        WorkTaskInstance taskInstance=new WorkTaskInstance();

        taskInstance.setId(taskFlow.getTflowNo());
        taskInstance.setBizType(taskFlow.getBizType());
        taskInstance.setTaskId(taskFlow.getTaskId());
        taskInstance.setActivityId(taskFlow.getActivityId());
        taskInstance.setName(taskFlow.getTaskName());
        taskInstance.setDisplayName(taskFlow.getDisplayName());
        taskInstance.setState(Integer.parseInt(taskFlow.getTaskState()));
        taskInstance.setSuspended(taskFlow.getSuspended().equals("1")?true:false);
        taskInstance.setCreatedTime(taskFlow.getCreatedTime());
        taskInstance.setStartedTime(taskFlow.getStartedTime());
        taskInstance.setEndTime(taskFlow.getEndTime());
        taskInstance.setExpiredTime(taskFlow.getExpiredTime());
        taskInstance.setAssignmentStrategy(taskFlow.getAssignStrategy());
        taskInstance.setProcessInstanceId(taskFlow.getPflowNo());
        taskInstance.setProcessId(taskFlow.getProcessNo());
        taskInstance.setVersion(taskFlow.getProcessVersion().intValue());
        taskInstance.setTaskType(taskFlow.getTaskType());
        taskInstance.setTargetActivityId(taskFlow.getToActivityId());
        taskInstance.setFromActivityId(taskFlow.getFromActivityId());
        taskInstance.setStepNumber(taskFlow.getStepNumber().intValue());
        taskInstance.setCanBeWithdrawn(taskFlow.getCanWithdrawn().equals("0")?false:true);

        return taskInstance;
    }
    private FlowWorkitemflow toItemFlow2(IWorkItemInstance workItem){

        FlowWorkitemflow itemflow=new FlowWorkitemflow();
        itemflow.setIflowNo(workItem.getId());
        if(StringUtils.isEmpty(itemflow.getIflowNo())) {
            itemflow.setIflowNo(UuidUtils.shortUUID());
        }
        itemflow.setTflowNo(workItem.getTaskInstance().getId());
        itemflow.setItemState(workItem.getState()+"");
        itemflow.setCreatedTime(workItem.getCreatedTime());
        itemflow.setClaimedTime(workItem.getClaimedTime());
        itemflow.setEndTime(workItem.getEndTime());
        itemflow.setActorNo(workItem.getActorId());
        itemflow.setCheckState("1");

        return itemflow;
    }
    private IWorkItemInstance toItemInstance(FlowWorkitemflow itemFlow){
        WorkItemInstance workItem=new WorkItemInstance();

        workItem.setId(itemFlow.getIflowNo());
        workItem.setActorId(itemFlow.getActorNo());
        workItem.setState(Integer.parseInt(itemFlow.getItemState()));
        workItem.setCreatedTime(itemFlow.getCreatedTime());
        workItem.setClaimedTime(itemFlow.getClaimedTime());
        workItem.setEndTime(itemFlow.getEndTime());
        workItem.setTaskInstance(toTaskInstance(taskflowService.getRecordByNo(itemFlow.getAppCode(),itemFlow.getTflowNo())));
        workItem.setTaskInstanceId(itemFlow.getTflowNo());

        return workItem;
    }
    private FlowProcessinfo toProcessInfo1(WorkflowDefinition definitionInfo){

        FlowProcessinfo processInfo=new FlowProcessinfo();
        processInfo.setProcessNo(definitionInfo.getId());
        if(StringUtils.isEmpty(processInfo.getProcessNo())) {
            processInfo.setProcessNo(UuidUtils.shortUUID());
        }
        processInfo.setProcessName(definitionInfo.getName());
        processInfo.setProcessVersion(definitionInfo.getVersion().longValue());
        processInfo.setDisplayName(definitionInfo.getDisplayName());
        processInfo.setProcessType(definitionInfo.getDefinitionType());
        processInfo.setProcessContent(definitionInfo.getProcessContent());
        processInfo.setDescription(definitionInfo.getDescription());
        processInfo.setUploadUser(definitionInfo.getUploadUser());
        processInfo.setUploadTime(definitionInfo.getUploadTime());
        processInfo.setPublishUser(definitionInfo.getPublishUser());
        processInfo.setPublishTime(definitionInfo.getPublishTime());
        processInfo.setPublishState(definitionInfo.getState()?"1":"0");
        processInfo.setCheckState("1");

        return processInfo;
    }
    private WorkflowDefinition toDefinitionInfo(FlowProcessinfo processInfo){
        WorkflowDefinition definitionInfo=new WorkflowDefinition();

        definitionInfo.setId(processInfo.getProcessNo());
        definitionInfo.setProcessId(processInfo.getProcessNo());
        definitionInfo.setName(processInfo.getProcessName());
        definitionInfo.setDisplayName(processInfo.getDisplayName());
        definitionInfo.setDefinitionType(processInfo.getProcessType());
        definitionInfo.setProcessContent(processInfo.getProcessContent());
        definitionInfo.setVersion(processInfo.getProcessVersion().intValue());
        definitionInfo.setDescription(processInfo.getDescription());
        definitionInfo.setState(processInfo.getPublishState().equals("1")?true:false);
        definitionInfo.setUploadUser(processInfo.getUploadUser());
        definitionInfo.setUploadTime(processInfo.getUploadTime());
        definitionInfo.setPublishUser(processInfo.getPublishUser());
        definitionInfo.setPublishTime(processInfo.getPublishTime());

        return definitionInfo;
    }
    private FlowProcesstoken toProcessToken1(ITokenInstance tokenInstance){

        FlowProcesstoken processToken=new FlowProcesstoken();
        processToken.setTokenNo(tokenInstance.getId());
        if(StringUtils.isEmpty(processToken.getTokenNo())) {
            processToken.setTokenNo(UuidUtils.shortUUID());
        }
        processToken.setPflowNo(tokenInstance.getProcessInstanceId());
        processToken.setTokenAlive("");
        processToken.setTokenValue(tokenInstance.getValue().longValue());
        processToken.setNodeId(tokenInstance.getNodeId());
        processToken.setStepNumber(tokenInstance.getStepNumber().longValue());
        processToken.setFromActivityId(tokenInstance.getFromActivityId());
        processToken.setCheckState("1");

        return processToken;
    }
    private ITokenInstance toTokenInstance(FlowProcesstoken processToken){

        TokenInstance tokenInstance=new TokenInstance();

        tokenInstance.setId(processToken.getTokenNo());
        tokenInstance.setAlive(processToken.getTokenAlive().equals("1")?true:false);
        tokenInstance.setValue(processToken.getTokenValue().intValue());
        tokenInstance.setNodeId(processToken.getNodeId());
        tokenInstance.setProcessInstanceId(processToken.getPflowNo());
        tokenInstance.setStepNumber(processToken.getStepNumber().intValue());
        tokenInstance.setFromActivityId(processToken.getFromActivityId());

        return tokenInstance;
    }
    private FlowProcessvars toProcessVars1(ProcessInstanceVar instanceVar){

        FlowProcessvars processVar=new FlowProcessvars();
        processVar.setPvarsNo(UuidUtils.shortUUID());
        processVar.setPflowNo(instanceVar.getProcessInstanceId());
        processVar.setPvarsName(instanceVar.getName());
        processVar.setPvarsValue(instanceVar.getValue().toString());
        processVar.setCheckState("1");

        return processVar;
    }
    private ProcessInstanceVar toVariableInstance(FlowProcessvars processVar){

        ProcessInstanceVar instanceVar=new ProcessInstanceVar();

        instanceVar.setProcessInstanceId(processVar.getPflowNo());
        instanceVar.setName(processVar.getPvarsName());
        instanceVar.setValue(processVar.getPvarsValue());
        instanceVar.setValueType(processVar.getValueType());

        return instanceVar;
    }
    private FlowHistorytrace toHistoryTrace1(ProcessInstanceTrace instanceTrace){

        FlowHistorytrace historyTrace=new FlowHistorytrace();
        historyTrace.setTraceNo(instanceTrace.getId());
        if(StringUtils.isEmpty(historyTrace.getTraceNo())) {
            historyTrace.setTraceNo(UuidUtils.shortUUID());
        }
        historyTrace.setPflowNo(instanceTrace.getProcessInstanceId());
        historyTrace.setStepNumber(instanceTrace.getStepNumber().longValue());
        historyTrace.setMinorNumber(instanceTrace.getMinorNumber().longValue());
        historyTrace.setTraceType(instanceTrace.getType());
        historyTrace.setEdgeId(instanceTrace.getEdgeId());
        historyTrace.setFromNodeId(instanceTrace.getFromNodeId());
        historyTrace.setToNodeId(instanceTrace.getToNodeId());
        historyTrace.setCheckState("1");

        return historyTrace;
    }
    private ProcessInstanceTrace toTraceInstance(FlowHistorytrace historyTrace){

        ProcessInstanceTrace instanceTrace=new ProcessInstanceTrace();

        instanceTrace.setId(historyTrace.getTraceNo());
        instanceTrace.setProcessInstanceId(historyTrace.getPflowNo());
        instanceTrace.setStepNumber(historyTrace.getStepNumber().intValue());
        instanceTrace.setMinorNumber(historyTrace.getMinorNumber().intValue());
        instanceTrace.setType(historyTrace.getTraceType());
        instanceTrace.setEdgeId(historyTrace.getEdgeId());
        instanceTrace.setFromNodeId(historyTrace.getFromNodeId());
        instanceTrace.setToNodeId(historyTrace.getToNodeId());

        return instanceTrace;
    }
}
