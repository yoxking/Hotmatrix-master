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
package com.benet.fireflow.engine.definition;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.benet.fireflow.model.WorkflowProcess;
import com.benet.fireflow.model.io.Dom4JFPDLParser;
import com.benet.fireflow.model.io.Dom4JFPDLSerializer;
import com.benet.fireflow.model.io.FPDLParserException;
import com.benet.fireflow.model.io.FPDLSerializerException;

/**
 * 流程定义对象，
 * 映射到表T_FF_DF_WORKFLOWDEF
 * @author 非也,nychen2000@163.com
 */
public class WorkflowDefinition {

    public static final String FPDL_PROCESS = "FPDL";
    public static final String XPDL_PROCESS = "XPDL";//从未用到
    public static final String BPEL_PROCESS = "BPEL";//从未用到
    protected String id; //主键
    protected String processId;//流程id
    protected String name; //流程英文名称
    protected String displayName;//流程显示名称
    protected String description;//流程业务说明
    protected Integer version;//版本号
    protected Boolean state;//是否发布，1=已经发布,0未发布
    protected String uploadUser ;//上载到数据库的操作员
    protected Date uploadTime;//上载到数据库的时间
    protected String publishUser;//发布人
    protected Date publishTime;//发布时间
    protected String definitionType = FPDL_PROCESS;//定义文件的语言类型，fpdl,xpdl,bepl...

    protected transient WorkflowProcess workflowProcess;

    protected String processContent; //流程定义文件的内容

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(String publishUser) {
        this.publishUser = publishUser;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getDefinitionType() {
        return definitionType;
    }

    public void setDefinitionType(String definitionType) {
        this.definitionType = definitionType;
    }

    public String getProcessContent() {
        return processContent;
    }

    public void setProcessContent(String processContent) {
        this.processContent = processContent;
    }

    /**
     * 获取业务流程对象
     * @return
     * @throws RuntimeException
     */
    public WorkflowProcess getWorkflowProcess() throws RuntimeException{
        if (workflowProcess == null) {
            if (this.processContent != null && !this.processContent.trim().equals("")) {

                ByteArrayInputStream in = null;
                try {
                    Dom4JFPDLParser parser = new Dom4JFPDLParser();//采用dom4j来解析xml
                    in = new ByteArrayInputStream(this.processContent.getBytes("utf-8"));
                    this.workflowProcess = parser.parse(in);

                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(WorkflowDefinition.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex.getMessage());
                } catch (IOException ex) {
                    Logger.getLogger(WorkflowDefinition.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex.getMessage());
                } 
                catch(FPDLParserException ex){
                    Logger.getLogger(WorkflowDefinition.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex.getMessage());
                } finally {
                    try {
                        in.close();
                    } catch (IOException ex) {
                        Logger.getLogger(WorkflowDefinition.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
        return workflowProcess;
    }

    /**
     * @param process
     * @throws RuntimeException
     */
    public void setWorkflowProcess(WorkflowProcess process) throws  RuntimeException {
        try {
            this.workflowProcess = process;
            this.processId = workflowProcess.getId();
            this.name = workflowProcess.getName();
            this.displayName = workflowProcess.getDisplayName();
            this.description = workflowProcess.getDescription();

            Dom4JFPDLSerializer ser = new Dom4JFPDLSerializer();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            ser.serialize(workflowProcess, out);

            this.processContent = out.toString("utf-8");
        } catch (FPDLSerializerException ex) {
            Logger.getLogger(WorkflowDefinition.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.toString());
        } catch (IOException ex) {
            Logger.getLogger(WorkflowDefinition.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.toString());
        }
    }

}
