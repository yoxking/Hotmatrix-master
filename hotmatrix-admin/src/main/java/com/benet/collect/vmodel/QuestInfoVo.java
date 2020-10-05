package com.benet.collect.vmodel;

public class QuestInfoVo {
    private String questNo;
    private String questTitle;
    private String questDesc;
    private String questType;
    private String questMust;
    private String classNo;
    private int orderNo;
    private Long questScore;
    private String checkState;
    private String comments;
    private QuestOptsVo[] options;

    public String getQuestNo() {
        return questNo;
    }

    public void setQuestNo(String questNo) {
        this.questNo = questNo;
    }

    public String getQuestTitle() {
        return questTitle;
    }

    public void setQuestTitle(String questTitle) {
        this.questTitle = questTitle;
    }

    public String getQuestDesc() {
        return questDesc;
    }

    public void setQuestDesc(String questDesc) {
        this.questDesc = questDesc;
    }

    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType;
    }

    public String getQuestMust() {
        return questMust;
    }

    public void setQuestMust(String questMust) {
        this.questMust = questMust;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public Long getQuestScore() {
        return questScore;
    }

    public void setQuestScore(Long questScore) {
        this.questScore = questScore;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public QuestOptsVo[] getOptions() {
        return options;
    }

    public void setOptions(QuestOptsVo[] options) {
        this.options = options;
    }
}
