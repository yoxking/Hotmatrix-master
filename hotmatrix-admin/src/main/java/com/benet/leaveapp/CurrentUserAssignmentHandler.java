package com.benet.leaveapp;

import com.benet.fireflow.engine.EngineException;
import com.benet.fireflow.engine.IWorkItemInstance;
import com.benet.fireflow.engine.taskinstance.IAssignable;
import com.benet.fireflow.engine.taskinstance.IAssignmentHandler;
import com.benet.fireflow.kernel.KernelException;

public class CurrentUserAssignmentHandler implements IAssignmentHandler {

    @Override
    public void assign(IAssignable arg0, String arg1) throws EngineException,
            KernelException {
        //取得当前系统用户，即请假申请人
        String currentUser ="s1";
        //为当前用户创建WorkItem
        IWorkItemInstance wi = arg0.assignToActor(currentUser);

        //签收新创建的WorkItem
        wi.claim();

        //结束新创建的WorkItem，触发流程实例往下流转
        wi.complete();
    }
}
