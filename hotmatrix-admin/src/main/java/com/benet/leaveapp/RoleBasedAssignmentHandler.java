package com.benet.leaveapp;

import com.benet.fireflow.engine.EngineException;
import com.benet.fireflow.engine.IProcessInstance;
import com.benet.fireflow.engine.impl.WorkTaskInstance;
import com.benet.fireflow.engine.taskinstance.IAssignable;
import com.benet.fireflow.engine.taskinstance.IAssignmentHandler;
import com.benet.fireflow.kernel.KernelException;

import java.util.ArrayList;
import java.util.List;

public class RoleBasedAssignmentHandler implements IAssignmentHandler {

    @Override
    public void assign(IAssignable arg0, String arg1) throws EngineException,
            KernelException {
        WorkTaskInstance taskInstance = (WorkTaskInstance)arg0;
        IProcessInstance processInstance = taskInstance.getAliveProcessInstance();

        //通过arg1找到角色中的所有人
        List<String> actorIds = new ArrayList<>();
        actorIds.add("c1");
        actorIds.add("c2");
        actorIds.add("c3");

        if (actorIds.size()==0){
            throw new EngineException(processInstance,taskInstance.getTask(),"没有符合条件的操作者，无法分配WorkItem！");
        }

        arg0.assignToActors(actorIds);
    }
}
