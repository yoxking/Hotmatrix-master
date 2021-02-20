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
import com.benet.fireflow.engine.impl.WorkTaskInstance;
import com.benet.fireflow.model.Task;
import com.benet.fireflow.model.net.Activity;

/**
 *
 * @author 非也
 * @version 1.0
 * Created on Mar 21, 2009
 */
public class DefaultTaskInstanceCreator implements ITaskInstanceCreator {

    /* (non-Javadoc)
     * @see org.fireflow.engine.taskinstance.ITaskInstanceCreator#createTaskInstance(org.fireflow.engine.IWorkflowSession, org.fireflow.engine.RuntimeContext, org.fireflow.engine.IProcessInstance, org.fireflow.model.Task, org.fireflow.model.net.Activity)
     */
    public IWorkTaskInstance createTaskInstance(IWorkflowSession currentSession,
                                                RuntimeContext runtimeContxt, IProcessInstance processInstance,
                                                Task task, Activity activity) throws EngineException{

        WorkTaskInstance taskInstance = new WorkTaskInstance();

        return taskInstance;

    }
}
