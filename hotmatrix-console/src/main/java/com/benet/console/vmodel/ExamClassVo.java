package com.benet.console.vmodel;

import java.util.List;

public class ExamClassVo {

    private String classNo;
    private String className;
    private String classDes;
    private List<ExamsInfoVo> examsList;

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDes() {
        return classDes;
    }

    public void setClassDes(String classDes) {
        this.classDes = classDes;
    }

    public List<ExamsInfoVo> getExamsList() {
        return examsList;
    }

    public void setExamsList(List<ExamsInfoVo> examsList) {
        this.examsList = examsList;
    }
}
