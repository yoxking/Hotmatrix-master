/**
 * Copyright 2007-2008 陈乜云（非也,Chen Nieyun）
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
package com.benet.fireflow.engine.taskinstance;

import com.benet.fireflow.engine.EngineException;
import com.benet.fireflow.engine.IProcessInstance;
import com.benet.fireflow.engine.IRuntimeContextAware;
import com.benet.fireflow.engine.IWorkTaskInstance;
import com.benet.fireflow.engine.IWorkItemInstance;
import com.benet.fireflow.engine.IWorkflowSession;
import com.benet.fireflow.engine.impl.WorkItemInstance;
import com.benet.fireflow.kernel.IActivityInstance;
import com.benet.fireflow.kernel.ITokenInstance;
import com.benet.fireflow.kernel.KernelException;

/**
 * 任务实例管理器
 * @author chennieyun
 *
 */
public interface ITaskInstanceManager extends IRuntimeContextAware {

    /**
     * 创建taskinstance实例
     * @param token
     * @param activityInstance
     * @throws EngineException
     */
    public void createTaskInstances(ITokenInstance token, IActivityInstance activityInstance) throws EngineException, KernelException;

    /**
     * 将已经完成的taskinstance实例转移到已办表<br>
     * （该方法保留在1.0中未使用，暂时保留，20090317）
     * @param activityInstance
     * @throws EngineException
     */
    public void archiveTaskInstances(IActivityInstance activityInstance) throws EngineException, KernelException;

    /**
     * 启动TaskInstance，其状态将从INITIALIZED变成STARTED状态。<br>
     * 对于Tool类型的TaskInstance,将直接调用外部应用程序。<br>
     * 对于Sbuflow类型的TaskInstance，将启动子流程。<br>
     * 对于Form类型的TaskInstance，仅改变其状态纪录启动时间。<br>
     * @param taskInstance
     * @throws EngineException
     * @throws KernelException
     */
    public void startTaskInstance(IWorkflowSession currentSession, IProcessInstance processInstance,
            IWorkTaskInstance taskInstance) throws EngineException, KernelException   ;

    /**
     * 结束TaskInstance以及当前的ActivityInstance，并执行targetActivityInstance环节实例。<br>
     * 如果targetActivityInstance为null表示由工作流引擎根据流程定义自动流转到下一个环节。
     * @param taskInstance
     * @param targetActivityInstance
     * @throws EngineException
     * @throws KernelException
     */
    public void completeTaskInstance(IWorkflowSession currentSession, IProcessInstance processInstance,
                                     IWorkTaskInstance taskInstance, IActivityInstance targetActivityInstance) throws EngineException, KernelException;


    /**
     * 中止task instance。
     * @param currentSession
     * @param processInstance
     * @param taskInstance
     * @param targetActivityId
     * @throws EngineException
     * @throws KernelException
     */
    public void abortTaskInstance(IWorkflowSession currentSession, IProcessInstance processInstance,
                                  IWorkTaskInstance taskInstance, String targetActivityId) throws EngineException, KernelException ;
    
    /**
     * 中止task instance。
     * @param currentSession
     * @param processInstance
     * @param taskInstance
     * @param targetActivityId
     * @throws EngineException
     * @throws KernelException
     */
    public void abortTaskInstanceEx(IWorkflowSession currentSession, IProcessInstance processInstance,
                                    IWorkTaskInstance taskInstance, String targetActivityId) throws EngineException, KernelException ;
    
    
    /**
     * 根据TaskInstance创建workItem。
     * @param taskInstance
     * @param actorId
     * @return
     * @throws EngineException
     */
    public WorkItemInstance createWorkItem(IWorkflowSession currentSession, IProcessInstance processInstance, IWorkTaskInstance taskInstance, String actorId) throws EngineException;

    /**
     * 签收WorkItem。
     * @param workItemId
     * @throws EngineException
     * @throws KernelException
     */
    public IWorkItemInstance claimWorkItem(String workItemId, String taskInstanceId)throws EngineException, KernelException ;

    /**
     * 结束WorkItem
     * @param workItem
     * @throws EngineException
     * @throws KernelException
     */
    public void completeWorkItem(IWorkItemInstance workItem, IActivityInstance targetActivityInstance, String comments)throws EngineException, KernelException ;

    /**
     * 结束工单并跳转
     * @param workItem
     * @param targetActivityId
     * @param comments
     * @throws EngineException
     * @throws KernelException
     */
    public void completeWorkItemAndJumpTo(IWorkItemInstance workItem, String targetActivityId, String comments) throws EngineException, KernelException ;

    /**
     * 结束工单并跳转（超级）
     * @param workItem
     * @param targetActivityId
     * @param comments
     * @throws EngineException
     * @throws KernelException
     */
    public void completeWorkItemAndJumpToEx(IWorkItemInstance workItem, String targetActivityId, String comments) throws EngineException, KernelException ;

    /**
     * 撤销刚才执行的Complete动作，系统将创建并返回一个新的Running状态的WorkItem
     * @param workItem
     * @return 新创建的工作项
     * @throws EngineException
     * @throws KernelException
     */
    public IWorkItemInstance withdrawWorkItem(IWorkItemInstance workItem) throws EngineException, KernelException ;

    /**
     * 拒收
     * @param workItem
     * @param comments
     * @throws EngineException
     * @throws KernelException
     */
    public void rejectWorkItem(IWorkItemInstance workItem, String comments) throws  EngineException, KernelException ;

    /**
     * 将工作项位派给其他人，自己的工作项变成CANCELED状态。返回新创建的WorkItem.
     * @param workItem 我的WorkItem
     * @param actorId 被委派的Actor的Id
     * @param comments 备注信息
     * @return 新创建的工作项
     */
    public IWorkItemInstance reassignWorkItemTo(IWorkItemInstance workItem, String actorId, String comments);
}
