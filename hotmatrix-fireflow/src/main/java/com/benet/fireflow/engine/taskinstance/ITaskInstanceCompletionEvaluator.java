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
 * 任务实例终止评价器
 * @author 非也
 * @version 1.0
 * Created on Mar 21, 2009
 */
public interface ITaskInstanceCompletionEvaluator {
    /**
     * 判断任务实例是否可以终止
     * @param currentSession
     * @param runtimeContext
     * @param processInstance
     * @param taskInstance
     * @return true表示可以终止，false不能终止
     * @throws EngineException
     * @throws KernelException
     */
    public boolean taskInstanceCanBeCompleted(IWorkflowSession currentSession, RuntimeContext runtimeContext,
                                              IProcessInstance processInstance , IWorkTaskInstance taskInstance)throws EngineException ,KernelException;
}
