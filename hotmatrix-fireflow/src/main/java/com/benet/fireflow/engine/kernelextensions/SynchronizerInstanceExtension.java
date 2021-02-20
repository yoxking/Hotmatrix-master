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

import com.benet.fireflow.engine.EngineException;
import com.benet.fireflow.engine.IRuntimeContextAware;
import com.benet.fireflow.engine.RuntimeContext;
import com.benet.fireflow.engine.persistence.IPersistenceService;
import com.benet.fireflow.kernel.ISynchronizerInstance;
import com.benet.fireflow.kernel.KernelException;
import com.benet.fireflow.kernel.event.INodeInstanceEventListener;
import com.benet.fireflow.kernel.event.NodeInstanceEvent;
import com.benet.fireflow.kernel.impl.SynchronizerInstance;
import com.benet.fireflow.kernel.plugin.IKernelExtension;

/**
 * @author 非也
 *
 */
public class SynchronizerInstanceExtension implements IKernelExtension,
        INodeInstanceEventListener, IRuntimeContextAware {

    protected RuntimeContext rtCtx = null;

    public void setRuntimeContext(RuntimeContext ctx) {
        this.rtCtx = ctx;
    }

    public RuntimeContext getRuntimeContext() {
        return this.rtCtx;
    }

    /* (non-Javadoc)
     * @see org.fireflow.kenel.plugin.IKenelExtension#getExtentionPointName()
     */
    public String getExtentionPointName() {
        return SynchronizerInstance.Extension_Point_NodeInstanceEventListener;
    }

    /* (non-Javadoc)
     * @see org.fireflow.kenel.plugin.IKenelExtension#getExtentionTargetName()
     */
    public String getExtentionTargetName() {
        return SynchronizerInstance.Extension_Target_Name;
    }

    /* (non-Javadoc)
     * @see org.fireflow.kenel.event.INodeInstanceEventListener#onNodeInstanceEventFired(org.fireflow.kenel.event.NodeInstanceEvent)
     */
    public void onNodeInstanceEventFired(NodeInstanceEvent e)
            throws KernelException, EngineException {
    	//同步器节点的监听器触发条件，是在离开这个节点的时候
        if (e.getEventType() == NodeInstanceEvent.NODEINSTANCE_LEAVING) {
            ISynchronizerInstance syncInst = (ISynchronizerInstance) e.getSource();
            IPersistenceService persistenceService = this.rtCtx.getPersistenceService();
            //删除同步器节点的token
            persistenceService.deleteTokensForNode(e.getToken().getProcessInstanceId(), syncInst.getSynchronizer().getId());

        }
    }
}
