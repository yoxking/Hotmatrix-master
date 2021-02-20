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

import com.benet.fireflow.engine.EngineException;
import com.benet.fireflow.engine.IProcessInstance;
import com.benet.fireflow.engine.IWorkTaskInstance;
import com.benet.fireflow.engine.IWorkflowSession;
import com.benet.fireflow.engine.RuntimeContext;
import com.benet.fireflow.kernel.KernelException;
import com.benet.fireflow.model.Task;
import com.benet.fireflow.model.ToolTask;
import com.benet.fireflow.model.WorkflowProcess;

/**
 *
 * @author 非也
 * @version 1.0
 * Created on Mar 21, 2009
 */
public class DefaultToolTaskInstanceRunner implements ITaskInstanceRunner{

    /* (non-Javadoc)
     * @see org.fireflow.engine.taskinstance.ITaskInstanceRunner#run(org.fireflow.engine.IWorkflowSession, org.fireflow.engine.RuntimeContext, org.fireflow.engine.IProcessInstance, org.fireflow.engine.ITaskInstance)
     */
    public void run(IWorkflowSession currentSession, RuntimeContext runtimeContext, IProcessInstance processInstance,
            IWorkTaskInstance taskInstance) throws EngineException, KernelException {
        if (!Task.TOOL.equals(taskInstance.getTaskType())) {
            throw new EngineException(processInstance,
                    taskInstance.getActivity(),
                    "DefaultToolTaskInstanceRunner：TaskInstance的任务类型错误，只能为TOOL类型");
        }
        Task task = taskInstance.getTask();
        if (task == null) {
            WorkflowProcess process = taskInstance.getWorkflowProcess();
            throw new EngineException(taskInstance.getProcessInstanceId(), process,
                    taskInstance.getTaskId(),
                    "The Task is null,can NOT start the taskinstance,");
        }
        if (((ToolTask) task).getApplication() == null || ((ToolTask) task).getApplication().getHandler() == null) {
            WorkflowProcess process = taskInstance.getWorkflowProcess();
            throw new EngineException(taskInstance.getProcessInstanceId(), process,
                    taskInstance.getTaskId(),
                    "The task.getApplication() is null or task.getApplication().getHandler() is null,can NOT start the taskinstance,");
        }

        Object obj = runtimeContext.getBeanByName(((ToolTask) task).getApplication().getHandler());

        if (obj==null || !(obj instanceof IApplicationHandler)){
            WorkflowProcess process = taskInstance.getWorkflowProcess();
            throw new EngineException(taskInstance.getProcessInstanceId(), process,
                    taskInstance.getTaskId(),
                    "Run tool task instance error! Not found the instance of "+((ToolTask) task).getApplication().getHandler()+" or the instance not implements IApplicationHandler");
            
        }

        try {
            ((IApplicationHandler) obj).execute(taskInstance);
        } catch (Exception e) {
            //TODO wmj2003 对tool类型的task抛出的错误应该怎么处理？ 这个时候引擎会如何？整个流程是否还可以继续？
        	e.printStackTrace();
            throw new EngineException(processInstance,
                    taskInstance.getActivity(),
                    "DefaultToolTaskInstanceRunner：TaskInstance的任务执行失败！");
        }

        ITaskInstanceManager taskInstanceManager = runtimeContext.getTaskInstanceManager();
        taskInstanceManager.completeTaskInstance(currentSession, processInstance, taskInstance, null);

    }

}
