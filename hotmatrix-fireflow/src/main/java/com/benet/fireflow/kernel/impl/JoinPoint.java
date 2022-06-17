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
package com.benet.fireflow.kernel.impl;

// Generated Feb 23, 2008 12:04:21 AM by Hibernate Tools 3.2.0.b9

import com.benet.fireflow.engine.IProcessInstance;
import com.benet.fireflow.engine.impl.ProcessInstance;
import com.benet.fireflow.kernel.IJoinPoint;

/**
 * JoinPoint generated by hbm2java
 */
@SuppressWarnings("serial")
public class JoinPoint implements IJoinPoint,
		java.io.Serializable {

	private String id; //TODO wmj2003 这个id有用吗？
	private String synchronizerId;
	private Integer value;
	private Boolean alive = Boolean.FALSE;
	private String processInstanceId = null;

	private transient IProcessInstance processInstance;

	private Integer stepNumber = null;

	private String fromActivityId = null;

	public JoinPoint() {
	}

	public JoinPoint(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSynchronizerId() {
		return this.synchronizerId;
	}

	public void setSynchronizerId(String synchronizerId) {
		this.synchronizerId = synchronizerId;
	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public void addValue(Integer v) {
		if (this.value == null) {
			this.value = v;
		} else {
			this.value = new Integer(this.value.intValue() + v.intValue());
		}
	}

	public Boolean getAlive() {
		return this.alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public IProcessInstance getProcessInstance() {
		return this.processInstance;
	}

	public void setProcessInstance(IProcessInstance processInstance) {
		this.processInstance = processInstance;
		if (this.processInstance != null) {
			this.processInstanceId = processInstance.getId();
		} else {
			this.processInstanceId = null;
		}
	}

	public String getProcessInstanceId() {
		return this.processInstanceId;
	}

	public void setProcessInstanceId(String id) {
		this.processInstanceId = id;
	}

	public Integer getStepNumber() {
		return this.stepNumber;
	}

	public void setStepNumber(Integer i) {
		this.stepNumber = i;
	}

	public String getFromActivityId() {
		return this.fromActivityId;
	}

	public void setFromActivityId(String s) {
		this.fromActivityId = s;
	}
}