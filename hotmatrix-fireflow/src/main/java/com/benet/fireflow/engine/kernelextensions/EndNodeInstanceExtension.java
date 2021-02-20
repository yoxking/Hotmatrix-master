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
package com.benet.fireflow.engine.kernelextensions;

import com.benet.fireflow.engine.impl.ProcessInstance;
import com.benet.fireflow.engine.persistence.IPersistenceService;
import com.benet.fireflow.kernel.ISynchronizerInstance;
import com.benet.fireflow.kernel.ITokenInstance;
import com.benet.fireflow.kernel.KernelException;
import com.benet.fireflow.kernel.event.NodeInstanceEvent;
import com.benet.fireflow.kernel.impl.EndNodeInstance;

/**
 * @author 非也,nychen2000@163.com
 *
 */
public class EndNodeInstanceExtension extends SynchronizerInstanceExtension {

    public String getExtentionPointName() {
        return EndNodeInstance.Extension_Point_NodeInstanceEventListener;
    }

    /* (non-Javadoc)
     * @see org.fireflow.kenel.plugin.IKenelExtension#getExtentionTargetName()
     */
    public String getExtentionTargetName() {
        return EndNodeInstance.Extension_Target_Name;
    }

    /* (non-Javadoc)
     * @see org.fireflow.engine.kernelextensions.SynchronizerInstanceExtension#onNodeInstanceEventFired(org.fireflow.kernel.event.NodeInstanceEvent)
     */
    public void onNodeInstanceEventFired(NodeInstanceEvent e)
            throws KernelException {
    	//同步器节点的监听器触发条件，是在离开这个节点的时候
        if (e.getEventType() == NodeInstanceEvent.NODEINSTANCE_LEAVING) {
            ISynchronizerInstance syncInst = (ISynchronizerInstance) e.getSource();
            IPersistenceService persistenceService = this.rtCtx.getPersistenceService();
            //删除同步器节点的token
            persistenceService.deleteTokensForNode(e.getToken().getProcessInstanceId(), syncInst.getSynchronizer().getId());

        }    	
    	//如果节点实例结束，就触发
        if (e.getEventType() == NodeInstanceEvent.NODEINSTANCE_COMPLETED) {
            // 执行ProcessInstance的complete操作

            ITokenInstance tk = e.getToken();
            
            ProcessInstance currentProcessInstance = (ProcessInstance) tk.getProcessInstance();
            currentProcessInstance.complete();
        }
    }
}
