package com.benet.leaveapp;

import com.benet.fireflow.engine.EngineException;
import com.benet.fireflow.engine.IProcessInstance;
import com.benet.fireflow.engine.IWorkItemInstance;
import com.benet.fireflow.engine.impl.WorkTaskInstance;
import com.benet.fireflow.engine.taskinstance.IAssignable;
import com.benet.fireflow.engine.taskinstance.IAssignmentHandler;
import com.benet.fireflow.kernel.KernelException;

import java.util.ArrayList;
import java.util.List;

public class RoleDepartmentBasedAssignmentHandler implements IAssignmentHandler {

    @Override
    public void assign(IAssignable arg0, String arg1) throws EngineException,
            KernelException {
        //通过processInstance.getCreatorId()找到请假申请人
        WorkTaskInstance taskInstance = (WorkTaskInstance)arg0;
        IProcessInstance processInstance = taskInstance.getAliveProcessInstance();
        String applicantId = processInstance.getCreatorId();


        List<String> usersForRoleAndDepartment = new ArrayList<>();//部门领导
        //从所有的部门领导中找到和申请人同一个部门的领导
        usersForRoleAndDepartment.add("m1");
        usersForRoleAndDepartment.add("m2");

        if (usersForRoleAndDepartment.size()==0){
            throw new EngineException(processInstance,taskInstance.getTask(),"没有符合条件的操作者，无法分配WorkItem！");
        }

        //分配Workitem
        arg0.assignToActors(usersForRoleAndDepartment);

    }
}
