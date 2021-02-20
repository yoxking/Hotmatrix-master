/**
 * Copyright 2007-2008 非也
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation。
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.org/licenses. *
 */
package com.benet.fireflow.engine.impl;

// Generated Feb 23, 2008 12:04:21 AM by Hibernate Tools 3.2.0.b9
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.benet.common.utils.uuid.UuidUtils;
import com.benet.fireflow.engine.EngineException;
import com.benet.fireflow.engine.IProcessInstance;
import com.benet.fireflow.engine.IRuntimeContextAware;
import com.benet.fireflow.engine.IWorkTaskInstance;
import com.benet.fireflow.engine.IWorkItemInstance;
import com.benet.fireflow.engine.IWorkflowSession;
import com.benet.fireflow.engine.IWorkflowSessionAware;
import com.benet.fireflow.engine.RuntimeContext;
import com.benet.fireflow.engine.definition.IDefinitionService;
import com.benet.fireflow.engine.definition.WorkflowDefinition;
import com.benet.fireflow.engine.impl.ProcessInstance;
import com.benet.fireflow.engine.persistence.IPersistenceService;
import com.benet.fireflow.engine.taskinstance.DynamicAssignmentHandler;
import com.benet.fireflow.engine.taskinstance.IAssignable;
import com.benet.fireflow.engine.taskinstance.ITaskInstanceManager;
import com.benet.fireflow.kernel.IActivityInstance;
import com.benet.fireflow.kernel.KernelException;
import com.benet.fireflow.model.Task;
import com.benet.fireflow.model.WorkflowProcess;
import com.benet.fireflow.model.net.Activity;

/**
 * TaskInstance generated by hbm2java
 */
@SuppressWarnings("serial")
public class WorkTaskInstance implements IWorkTaskInstance, IAssignable, IRuntimeContextAware, IWorkflowSessionAware, java.io.Serializable {

    private String id = null;
    private String bizType=null;
    private String taskId = null;
    private String activityId = null;
    private String name = null;
    private String displayName = null;
    private Integer state = null;
    private Boolean suspended = null;
    private Date createdTime = null;
    private Date startedTime = null;
    private Date expiredTime = null;
    private Date endTime = null;
    private String assignmentStrategy = null;
    private String processInstanceId = null;
    private String processId = null;
    private Integer version = null;

    private String taskType = null;
    private String targetActivityId = null;
    private String fromActivityId = null;

    private Integer stepNumber = null;

    private Boolean canBeWithdrawn = true;

    protected transient RuntimeContext rtCtx = null;
    protected transient IWorkflowSession workflowSession = null;
    private transient IProcessInstance processInsatance = null;

    public void setRuntimeContext(RuntimeContext ctx) {
        this.rtCtx = ctx;
    }

    public RuntimeContext getRuntimeContext() {
        return this.rtCtx;
    }

    public WorkTaskInstance() {
        this.id= UuidUtils.shortUUID();
        this.state = IWorkTaskInstance.INITIALIZED;
        this.suspended = false;
    }

    public WorkTaskInstance(ProcessInstance processInsatnce) {
        this.id= UuidUtils.shortUUID();
        this.state = IWorkTaskInstance.INITIALIZED;
        this.suspended = false;
        this.processInsatance = processInsatnce;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }


    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String label) {
        this.displayName = label;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getStartedTime() {
        return this.startedTime;
    }

    public void setStartedTime(Date startedTime) {
        this.startedTime = startedTime;
    }

    public Date getExpiredTime() {
        return this.expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAssignmentStrategy() {
        return assignmentStrategy;
    }

    public void setAssignmentStrategy(String completionStrategy) {
        this.assignmentStrategy = completionStrategy;
    }

    public Boolean isSuspended() {
        return suspended;
    }

    public Boolean getSuspended(){
        return suspended;
    }
    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.ITaskInstance#getAliveProcessInstance()
     */
    public IProcessInstance getAliveProcessInstance() {
        if (this.processInsatance==null){
            if (this.rtCtx!=null){
                IPersistenceService persistenceService = rtCtx.getPersistenceService();
                this.processInsatance = persistenceService.findAliveProcessInstanceById(this.processInstanceId);
            }
        }
        if (this.processInsatance != null) {
            if (this.workflowSession!=null){
                ((IWorkflowSessionAware) this.processInsatance).setCurrentWorkflowSession(this.workflowSession);
            }
            if (this.rtCtx!=null){
                ((IRuntimeContextAware) this.processInsatance).setRuntimeContext(this.rtCtx);
            }

        }
        return this.processInsatance;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.taskinstance.IAssignable#assignToActor(java.lang.String)
     */
    public IWorkItemInstance assignToActor(String id) throws EngineException, KernelException {
        ITaskInstanceManager taskInstanceMgr = this.rtCtx.getTaskInstanceManager();
        WorkItemInstance wi = taskInstanceMgr.createWorkItem(this.workflowSession,this.getAliveProcessInstance(),this, id);
        return wi;
    }


    /* (non-Javadoc)
     * @see org.fireflow.engine.taskinstance.IAssignable#assignToActors(java.util.List)
     */
    public List<IWorkItemInstance> assignToActors(List<String> ids) throws EngineException, KernelException {
        //task应该有一个标志(asignToEveryone)，表明asign的规则 (?)
        List<IWorkItemInstance> workItemList = new ArrayList<IWorkItemInstance>();
        for (int i = 0; ids != null && i < ids.size(); i++) {
            ITaskInstanceManager taskInstanceMgr = this.rtCtx.getTaskInstanceManager();
            WorkItemInstance wi = taskInstanceMgr.createWorkItem(this.workflowSession,this.getAliveProcessInstance(),this, ids.get(i));
            wi.setCurrentWorkflowSession(workflowSession);
            workItemList.add(wi);
        }
        return workItemList;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.ITaskInstance#getTask()
     */
    public Task getTask() throws EngineException {
        if (rtCtx==null)System.out.println("====Inside taskInstance rtCtx is null");
        IDefinitionService definitionService = rtCtx.getDefinitionService();
        if (definitionService==null)System.out.println("====Inside taskInstance definitionService is null");
        WorkflowDefinition workflowDef = definitionService.getWorkflowDefinitionByProcessNameAndVersionNumber(this.getProcessId(), this.getVersion());
        if (workflowDef == null) {
            return null;
        }

        return (Task) workflowDef.getWorkflowProcess().findWFElementById(this.getTaskId());

    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.ITaskInstance#getActivity()
     */
    public Activity getActivity() throws EngineException {
        WorkflowDefinition workflowDef = rtCtx.getDefinitionService().getWorkflowDefinitionByProcessNameAndVersionNumber(this.getProcessId(), this.getVersion());
        if (workflowDef == null) {
            return null;
        }
        return (Activity) workflowDef.getWorkflowProcess().findWFElementById(this.getActivityId());
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.ITaskInstance#getWorkflowProcess()
     */
    public WorkflowProcess getWorkflowProcess() throws EngineException {
        WorkflowDefinition workflowDef = rtCtx.getDefinitionService().getWorkflowDefinitionByProcessNameAndVersionNumber(this.getProcessId(), this.getVersion());
        if (workflowDef == null) {
            return null;
        }

        return workflowDef.getWorkflowProcess();
    }

    /**
     *
     * @throws EngineException
     * @throws KernelException
     */
    public final void start() throws EngineException, KernelException {
        ITaskInstanceManager taskInstanceMgr = this.rtCtx.getTaskInstanceManager();
        taskInstanceMgr.startTaskInstance(workflowSession, this.getAliveProcessInstance(), this);
//        taskInstanceMgr.startTaskInstance(this);
    }

    /**
     *
     * @param targetActivityInstance
     * @throws EngineException
     * @throws KernelException
     */
    public void complete(IActivityInstance targetActivityInstance) throws EngineException, KernelException {
        ITaskInstanceManager taskInstanceMgr = this.rtCtx.getTaskInstanceManager();
        taskInstanceMgr.completeTaskInstance(workflowSession,  this.getAliveProcessInstance(), this, targetActivityInstance);
//        taskInstanceMgr.completeTaskInstance(this, targetActivityInstance);
    }

    public IWorkflowSession getCurrentWorkflowSession() {
        return this.workflowSession;
    }

    public void setCurrentWorkflowSession(IWorkflowSession session) {
        this.workflowSession = session;
    }

    public String getProcessInstanceId() {
        return this.processInstanceId;
    }

    public void setProcessInstanceId(String s) {
        this.processInstanceId = s;
    }

    public String getProcessId() {
        return this.processId;
    }

    public void setProcessId(String s) {
        this.processId = s;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer v) {
        this.version = v;
    }

    public String getTargetActivityId() {
        return this.targetActivityId;
    }

    public void setTargetActivityId(String s) {
        this.targetActivityId = s;
    }

    public Integer getStepNumber(){
        return this.stepNumber;
    }

    public void setStepNumber(Integer i){
        this.stepNumber = i;
    }

    public Boolean getCanBeWithdrawn() {
        return canBeWithdrawn;
    }

    public void setCanBeWithdrawn(Boolean canBeWithdrawn) {
        this.canBeWithdrawn = canBeWithdrawn;
    }

    public String getFromActivityId() {
        return fromActivityId;
    }

    public void setFromActivityId(String fromActivityId) {
        this.fromActivityId = fromActivityId;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.ITaskInstance#suspend()
     */
    public void suspend() throws EngineException {
        if (this.state==IWorkTaskInstance.COMPLETED || this.state==IWorkTaskInstance.CANCELED){
            throw new EngineException(this.getAliveProcessInstance(),this.getTask(),"The task instance can not be suspended,the state of this task instance is "+this.state);
        }
        if (this.isSuspended()){
            return;
        }
        this.setSuspended(Boolean.TRUE);
        IPersistenceService persistenceService = this.rtCtx.getPersistenceService();
        persistenceService.saveOrUpdateWorkTaskInstance(this);
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.ITaskInstance#restore()
     */
    public void restore() throws EngineException {
        if (this.state==IWorkTaskInstance.COMPLETED || this.state==IWorkTaskInstance.CANCELED){
            throw new EngineException(this.getAliveProcessInstance(),this.getTask(),"The task instance can not be restored,the state of this task instance is "+this.state);
        }
        if (!this.isSuspended()){
            return;
        }
        this.setSuspended(Boolean.FALSE);
        IPersistenceService persistenceService = this.rtCtx.getPersistenceService();
        persistenceService.saveOrUpdateWorkTaskInstance(this);
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.ITaskInstance#abort()
     */
    public void abort() throws EngineException, KernelException {
        abort(null);

    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.ITaskInstance#abort(java.lang.String)
     */
    public void abort(String targetActivityId)
            throws EngineException, KernelException {
        abort(targetActivityId,null);

    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.ITaskInstance#abort(java.lang.String, org.fireflow.engine.taskinstance.DynamicAssignmentHandler)
     */
    public void abort(String targetActivityId,
                      DynamicAssignmentHandler dynamicAssignmentHandler)
            throws EngineException, KernelException {

        if (this.workflowSession==null){
            new EngineException(this.getProcessInstanceId(),
                    this.getWorkflowProcess(),this.getTaskId(),
                    "The current workflow session is null.");
        }
        if (this.rtCtx==null){
            new EngineException(this.getProcessInstanceId(),
                    this.getWorkflowProcess(),this.getTaskId(),
                    "The current runtime context is null.");
        }

        if ((this.getState().intValue() == IWorkTaskInstance.COMPLETED ) ||
                (this.getState().intValue()==IWorkTaskInstance.CANCELED)) {
            throw new EngineException(this.getProcessInstanceId(), this.getWorkflowProcess(),
                    this.getTaskId(),
                    "Abort task instance failed . The state of the task instance [id=" + this.getId() + "] is " + this.getState());
        }

        if (dynamicAssignmentHandler!=null){
            this.workflowSession.setDynamicAssignmentHandler(dynamicAssignmentHandler);
        }


        ITaskInstanceManager taskInstanceMgr = this.rtCtx.getTaskInstanceManager();
        taskInstanceMgr.abortTaskInstance(this.workflowSession, this.getAliveProcessInstance(), this, targetActivityId);

    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.ITaskInstance#abortEx(java.lang.String, org.fireflow.engine.taskinstance.DynamicAssignmentHandler)
     */
    public void abortEx(String targetActivityId,DynamicAssignmentHandler dynamicAssignmentHandler) throws EngineException,KernelException{

        if (this.workflowSession==null){
            new EngineException(this.getProcessInstanceId(),
                    this.getWorkflowProcess(),this.getTaskId(),
                    "The current workflow session is null.");
        }
        if (this.rtCtx==null){
            new EngineException(this.getProcessInstanceId(),
                    this.getWorkflowProcess(),this.getTaskId(),
                    "The current runtime context is null.");
        }

        if ((this.getState().intValue() == IWorkTaskInstance.COMPLETED ) ||
                (this.getState().intValue()==IWorkTaskInstance.CANCELED)) {
            throw new EngineException(this.getProcessInstanceId(), this.getWorkflowProcess(),
                    this.getTaskId(),
                    "Abort task instance failed . The state of the task instance [id=" + this.getId() + "] is " + this.getState());
        }

        if (dynamicAssignmentHandler!=null){
            this.workflowSession.setDynamicAssignmentHandler(dynamicAssignmentHandler);
        }


        ITaskInstanceManager taskInstanceMgr = this.rtCtx.getTaskInstanceManager();
        taskInstanceMgr.abortTaskInstanceEx(this.workflowSession, this.getAliveProcessInstance(), this, targetActivityId);

    }
}
