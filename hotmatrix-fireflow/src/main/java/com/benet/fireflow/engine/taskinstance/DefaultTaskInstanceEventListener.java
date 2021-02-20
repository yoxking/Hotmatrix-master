package com.benet.fireflow.engine.taskinstance;

import com.benet.fireflow.engine.EngineException;
import com.benet.fireflow.engine.IProcessInstance;
import com.benet.fireflow.engine.IWorkTaskInstance;
import com.benet.fireflow.engine.IWorkItemInstance;
import com.benet.fireflow.engine.IWorkflowSession;
import com.benet.fireflow.engine.event.ITaskInstanceEventListener;
import com.benet.fireflow.engine.event.TaskInstanceEvent;

public class DefaultTaskInstanceEventListener implements
		ITaskInstanceEventListener {


	/* (non-Javadoc)
	 * @see org.fireflow.engine.event.ITaskInstanceEventListener#onTaskInstanceEventFired(org.fireflow.engine.event.TaskInstanceEvent)
	 */
	public void onTaskInstanceEventFired(TaskInstanceEvent e) throws EngineException {
		IWorkflowSession session = e.getWorkflowSession();
		IProcessInstance proceInst = e.getProcessInstance();
		IWorkTaskInstance taskInst = (IWorkTaskInstance)e.getSource();
		IWorkItemInstance wi = e.getWorkItem();
		if (e.getEventType()==TaskInstanceEvent.BEFORE_TASK_INSTANCE_START){
			beforeTaskInstanceStart(session,proceInst,taskInst);
		}else if (e.getEventType()==TaskInstanceEvent.AFTER_TASK_INSTANCE_COMPLETE){
			afterTaskInstanceCompleted(session,proceInst,taskInst);
		}
		else if (e.getEventType()==TaskInstanceEvent.AFTER_WORKITEM_CREATED){
			afterWorkItemCreated(session,proceInst,taskInst,wi);
		}else if (e.getEventType()==TaskInstanceEvent.AFTER_WORKITEM_COMPLETE){
			afterWorkItemComplete(session,proceInst,taskInst,wi);
		}

	}
	
	protected void  beforeTaskInstanceStart(IWorkflowSession currentSession,
                                            IProcessInstance processInstance, IWorkTaskInstance taskInstance)throws EngineException{
		
	}
	protected void  afterTaskInstanceCompleted(IWorkflowSession currentSession,
                                               IProcessInstance processInstance, IWorkTaskInstance taskInstance)throws EngineException{
		
	}
    protected void afterWorkItemCreated(IWorkflowSession currentSession,
                                        IProcessInstance processInstance, IWorkTaskInstance taskInstance, IWorkItemInstance workItem)throws EngineException{
    	
    }
    
    protected void afterWorkItemComplete(IWorkflowSession currentSession,
                                         IProcessInstance processInstance, IWorkTaskInstance taskInstance, IWorkItemInstance workItem)throws EngineException{
//    	System.out.println("---------------------------------after workitem complete!!!!!!!!!!!!!!");
    }    
}
