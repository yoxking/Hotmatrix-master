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

/**
 *
 * @author 非也
 * @version 1.0
 * Created on Mar 21, 2009
 */
public class DefaultToolTaskInstanceCompletionEvaluator implements ITaskInstanceCompletionEvaluator{

    /* (non-Javadoc)
     * @see org.fireflow.engine.taskinstance.ITaskInstanceCompletionEvaluator#taskInstanceCanBeCompleted(org.fireflow.engine.IWorkflowSession, org.fireflow.engine.RuntimeContext, org.fireflow.engine.IProcessInstance, org.fireflow.engine.ITaskInstance)
     */
    public boolean taskInstanceCanBeCompleted(IWorkflowSession currentSession, RuntimeContext runtimeContext,
                                              IProcessInstance processInstance , IWorkTaskInstance taskInstance)throws EngineException ,KernelException {
        //Tool类型的TaskInstance在handler执行完后，都可以直接结束。
        return true;
    }

}
