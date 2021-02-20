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
import com.benet.fireflow.model.Task;
import com.benet.fireflow.model.net.Activity;

/**
 * 任务实例创建器
 * @author 非也
 * @version 1.0
 * Created on Mar 21, 2009
 */
public interface ITaskInstanceCreator {

	/**
	 * 创建任务实例
	 * @param currentSession
	 * @param runtimeContxt
	 * @param processInstance
	 * @param task
	 * @param activity
	 * @return
	 * @throws EngineException
	 */
    public IWorkTaskInstance createTaskInstance(IWorkflowSession currentSession, RuntimeContext runtimeContxt,
                                                IProcessInstance processInstance, Task task, Activity activity) throws EngineException;
}
