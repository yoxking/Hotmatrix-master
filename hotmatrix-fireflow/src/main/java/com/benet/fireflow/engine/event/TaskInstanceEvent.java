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
package com.benet.fireflow.engine.event;

import com.benet.fireflow.engine.IProcessInstance;
import com.benet.fireflow.engine.IWorkTaskInstance;
import com.benet.fireflow.engine.IWorkItemInstance;
import com.benet.fireflow.engine.IWorkflowSession;

/**
 * 任务实例事件
 * @author 非也，nychen2000@163.com
 */
public class TaskInstanceEvent {
    /**
     * 在任务实例即将启动时触发的事件
     */
    public static final int BEFORE_TASK_INSTANCE_START = 2;
    
    /**
     * 当创建工作项之后
     */
    public static final int AFTER_WORKITEM_CREATED = 5;
    
    /**
     * 当工作项完成之后
     */
    public static final int AFTER_WORKITEM_COMPLETE = 6;
    
    /**
     * 在任务实例结束时触发的事件
     */
    public static final int AFTER_TASK_INSTANCE_COMPLETE = 7;
    
    
    
    int eventType = -1;
    IWorkTaskInstance source = null;
    IWorkflowSession workflowSession = null;
    IProcessInstance processInstance = null;
    IWorkItemInstance workItem = null;

    /**
     * 返回事件类型，取值为BEFORE_TASK_INSTANCE_START或者AFTER_TASK_INSTANCE_COMPLETE
     * @return
     */
    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    /**
     * 返回触发该事件的任务实例
     * @return
     */
    public IWorkTaskInstance getSource() {
        return source;
    }

    public void setSource(IWorkTaskInstance source) {
        this.source = source;
    }

	public IWorkItemInstance getWorkItem() {
		return workItem;
	}

	public void setWorkItem(IWorkItemInstance workItem) {
		this.workItem = workItem;
	}

	public IWorkflowSession getWorkflowSession() {
		return workflowSession;
	}

	public void setWorkflowSession(IWorkflowSession workflowSession) {
		this.workflowSession = workflowSession;
	}

	public IProcessInstance getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(IProcessInstance processInstance) {
		this.processInstance = processInstance;
	}
    
    
}
