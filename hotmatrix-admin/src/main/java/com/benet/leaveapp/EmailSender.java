package com.benet.leaveapp;

import com.benet.common.utils.date.DateUtils;
import com.benet.fireflow.engine.IProcessInstance;
import com.benet.fireflow.engine.IWorkTaskInstance;
import com.benet.fireflow.engine.impl.WorkTaskInstance;
import com.benet.fireflow.engine.taskinstance.IApplicationHandler;

public class EmailSender implements IApplicationHandler {


    @Override
    public void execute(IWorkTaskInstance arg0) {
        IProcessInstance processInstance = ((WorkTaskInstance)arg0).getAliveProcessInstance();
        String sn = (String)processInstance.getProcessInstanceVariable("sn");
        Boolean approvalFlag = (Boolean)processInstance.getProcessInstanceVariable("approvalFlag");

       System.out.println("您的请假申请：时间："+ DateUtils.dateTimeNow() +"，收到被"+(approvalFlag?"批准":"拒绝。"));
    }

}
