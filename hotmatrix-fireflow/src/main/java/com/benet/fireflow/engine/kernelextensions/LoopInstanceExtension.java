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
package com.benet.fireflow.engine.kernelextensions;

import java.util.Map;

import com.benet.fireflow.engine.EngineException;
import com.benet.fireflow.engine.IRuntimeContextAware;
import com.benet.fireflow.engine.RuntimeContext;
import com.benet.fireflow.engine.condition.IConditionResolver;
import com.benet.fireflow.engine.impl.ProcessInstanceTrace;
import com.benet.fireflow.kernel.ILoopInstance;
import com.benet.fireflow.kernel.ITokenInstance;
import com.benet.fireflow.kernel.KernelException;
import com.benet.fireflow.kernel.event.EdgeInstanceEvent;
import com.benet.fireflow.kernel.event.IEdgeInstanceEventListener;
import com.benet.fireflow.kernel.impl.LoopInstance;
import com.benet.fireflow.kernel.plugin.IKernelExtension;

/**
 * 
 * @author 非也
 * @version 1.0 Created on Mar 18, 2009
 */
public class LoopInstanceExtension implements IKernelExtension,
		IEdgeInstanceEventListener, IRuntimeContextAware {

	protected RuntimeContext rtCtx = null;

	public void setRuntimeContext(RuntimeContext ctx) {
		this.rtCtx = ctx;
	}

	public RuntimeContext getRuntimeContext() {
		return this.rtCtx;
	}

	public String getExtentionTargetName() {
		return LoopInstance.Extension_Target_Name;
	}

	public String getExtentionPointName() {
		return LoopInstance.Extension_Point_LoopInstanceEventListener;
	}

	/**
	 * @param vars
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	private boolean determineTheAliveOfToken(Map<String ,Object> vars, String condition)
			throws Exception {
		// 通过计算transition上的表达式来确定alive的值
		IConditionResolver elResolver = this.rtCtx.getConditionResolver();
		Boolean b = elResolver.resolveBooleanExpression(vars, condition);

		return b;
	}

	/**
	 * @param token
	 * @param condition
	 * @throws EngineException
	 */
	public void calculateTheAliveValue(ITokenInstance token, String condition)throws EngineException {
		if (!token.isAlive()) {
			return;// 如果token是dead状态，表明synchronizer的joinpoint是dead状态，不需要重新计算。
		}

		// 1、如果没有循环条件，默认为false
		if (condition == null || condition.trim().equals("")) {
			token.setAlive(false);
			return;
		}
		// 3、计算EL表达式
		try {
			boolean alive = determineTheAliveOfToken(token.getProcessInstance()
					.getProcessInstanceVariables(), condition);
			token.setAlive(alive);
		} catch (Exception ex) {
			throw new EngineException(token.getProcessInstanceId(), token
					.getProcessInstance().getWorkflowProcess(), token
					.getNodeId(), ex.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fireflow.kernel.event.IEdgeInstanceEventListener#onEdgeInstanceEventFired(org.fireflow.kernel.event.EdgeInstanceEvent)
	 */
	public void onEdgeInstanceEventFired(EdgeInstanceEvent e)
			throws KernelException {

		if (e.getEventType() == EdgeInstanceEvent.ON_TAKING_THE_TOKEN) {
			ITokenInstance token = e.getToken();
			// 计算token的alive值
			ILoopInstance transInst = (ILoopInstance) e.getSource();
			String condition = transInst.getLoop().getCondition();

			calculateTheAliveValue(token, condition);

			if (rtCtx.isEnableTrace() && token.isAlive()) {
				ProcessInstanceTrace trace = new ProcessInstanceTrace();
				trace.setProcessInstanceId(e.getToken().getProcessInstanceId());
				trace.setStepNumber(e.getToken().getStepNumber() + 1);
				trace.setType(ProcessInstanceTrace.LOOP_TYPE);
				trace.setFromNodeId(transInst.getLoop().getFromNode().getId());
				trace.setToNodeId(transInst.getLoop().getToNode().getId());
				trace.setEdgeId(transInst.getLoop().getId());
				//TODO wmj2003 一旦token从当前边上经过，那么就保存流程运行轨迹,这里应该是insert
				rtCtx.getPersistenceService().saveOrUpdateProcessInstanceTrace(
						trace);
			}
		}
	}
}
