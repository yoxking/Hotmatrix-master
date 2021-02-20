/*
 * Copyright 2007-2009 非也
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

 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.org/licenses.
 */
package com.benet.fireflow.engine.taskinstance;

import java.util.List;

import com.benet.fireflow.engine.EngineException;
import com.benet.fireflow.engine.IProcessInstance;
import com.benet.fireflow.engine.IWorkTaskInstance;
import com.benet.fireflow.engine.IWorkItemInstance;
import com.benet.fireflow.engine.IWorkflowSession;
import com.benet.fireflow.engine.RuntimeContext;
import com.benet.fireflow.engine.beanfactory.IBeanFactory;
import com.benet.fireflow.engine.impl.WorkflowSession;
import com.benet.fireflow.engine.persistence.IPersistenceService;
import com.benet.fireflow.kernel.KernelException;
import com.benet.fireflow.model.FormTask;
import com.benet.fireflow.model.Task;
import com.benet.fireflow.model.resource.Participant;

/**
 *
 * @author 非也
 * @version 1.0
 * Created on Mar 21, 2009
 */
public class DefaultFormTaskInstanceRunner implements ITaskInstanceRunner {

    /* (non-Javadoc)
     * @see org.fireflow.engine.taskinstance.ITaskInstanceRunner#run(org.fireflow.engine.IWorkflowSession, org.fireflow.engine.RuntimeContext, org.fireflow.engine.IProcessInstance, org.fireflow.engine.ITaskInstance)
     */
    public void run(IWorkflowSession currentSession, RuntimeContext runtimeContext, IProcessInstance processInstance,
            IWorkTaskInstance taskInstance) throws EngineException, KernelException {
                if (!Task.FORM.equals(taskInstance.getTaskType())){
                    throw new EngineException(processInstance,
                            taskInstance.getActivity(),
                            "DefaultFormTaskInstanceRunner：TaskInstance的任务类型错误，只能为FORM类型");
                }

                DynamicAssignmentHandler dynamicAssignmentHandler = ((WorkflowSession)currentSession).consumeCurrentDynamicAssignmentHandler();
                FormTask task = (FormTask)taskInstance.getTask();

                Participant performer = task.getPerformer();//获取到form的执行者
                if (performer == null || performer.getAssignmentHandler().trim().equals("")) {
                    throw new EngineException(processInstance,
                            taskInstance.getActivity(),
                            "流程定义错误，Form类型的 task必须指定performer及其AssignmentHandler");
                }
                assign(currentSession,processInstance,runtimeContext,taskInstance, task, performer, dynamicAssignmentHandler);
    }

    /**
     * 分配， 按照当前任务的参与者插入工单
     * @param currentSession
     * @param processInstance
     * @param runtimeContext
     * @param taskInstance
     * @param formTask
     * @param part
     * @param dynamicAssignmentHandler
     * @throws EngineException
     * @throws KernelException
     */
    protected void assign(IWorkflowSession currentSession, IProcessInstance processInstance, RuntimeContext runtimeContext, IWorkTaskInstance taskInstance, FormTask formTask, Participant part, DynamicAssignmentHandler dynamicAssignmentHandler) throws EngineException, KernelException {
        //如果有指定的Actor，则按照指定的Actor分配任务
        if (dynamicAssignmentHandler != null) {

            dynamicAssignmentHandler.assign((IAssignable) taskInstance, part.getName());

        } else {

        	IPersistenceService persistenceService = runtimeContext.getPersistenceService();
        	//从数据库中查询任务信息
        	List<IWorkTaskInstance> taskInstanceList = persistenceService.findTaskInstancesForProcessInstance(taskInstance.getProcessInstanceId(), taskInstance.getActivityId());
        	IWorkTaskInstance theLastCompletedTaskInstance = null;

        	for (int i=0;taskInstanceList!=null && i<taskInstanceList.size();i++){
        		IWorkTaskInstance tmp = taskInstanceList.get(i);
        		if (tmp.getId().equals(taskInstance.getId()))continue;
                if (!tmp.getTaskId().equals(taskInstance.getTaskId()))continue;
                if (tmp.getState().intValue()!= IWorkTaskInstance.COMPLETED)continue;
        		if ( theLastCompletedTaskInstance==null){
        			theLastCompletedTaskInstance = tmp;
        		}else{
        			if (theLastCompletedTaskInstance.getStepNumber().intValue()<tmp.getStepNumber().intValue()){
        				theLastCompletedTaskInstance=tmp;
        			}
        		}
        	}

        	//如果是循环且LoopStrategy==REDO，则分配个上次完成该工作的操作员
            if (theLastCompletedTaskInstance!=null && (FormTask.REDO.equals(formTask.getLoopStrategy()) || currentSession.isInWithdrawOrRejectOperation())) {
            	List<IWorkItemInstance> workItemList = persistenceService.findCompletedWorkItemsForTaskInstance(theLastCompletedTaskInstance.getId());
            	ITaskInstanceManager taskInstanceMgr = runtimeContext.getTaskInstanceManager();
                for (int k = 0; k < workItemList.size(); k++) {
                    IWorkItemInstance completedWorkItem = workItemList.get(k);

                    IWorkItemInstance newFromWorkItem = taskInstanceMgr.createWorkItem(currentSession,processInstance,taskInstance, completedWorkItem.getActorId());
                    newFromWorkItem.claim(); //并自动签收
                }
            } else {
                IBeanFactory beanFactory = runtimeContext.getBeanFactory();
                //从spring中获取到对应任务的Performer，创建工单
                IAssignmentHandler assignmentHandler = (IAssignmentHandler) beanFactory.getBean(part.getAssignmentHandler());
                //modified by wangmj 20090904
                assignmentHandler.assign((IAssignable) taskInstance, part.getName());
            }
        }


    }
}
