package com.benet.collect.vmodel;

public class QuestInfoVo {
    private String questNo;
    private String questTitle;
    private String questImage;
    private String questType;
    private String questDesc;
    private String classNo;
    private String qsetsNo;
    private int orderNo;
    private String questMust;
    private Long questTscore;
    private String questAnswer;
    private String questKeyword;
    private String questExplain;
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

    public String getQuestImage() {
        return questImage;
    }

    public void setQuestImage(String questImage) {
        this.questImage = questImage;
    }

    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType;
    }

    public String getQuestDesc() {
        return questDesc;
    }

    public void setQuestDesc(String questDesc) {
        this.questDesc = questDesc;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getQsetsNo() {
        return qsetsNo;
    }

    public void setQsetsNo(String qsetsNo) {
        this.qsetsNo = qsetsNo;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getQuestMust() {
        return questMust;
    }

    public void setQuestMust(String questMust) {
        this.questMust = questMust;
    }

    public Long getQuestTscore() {
        return questTscore;
    }

    public void setQuestTscore(Long questTscore) {
        this.questTscore = questTscore;
    }

    public String getQuestAnswer() {
        return questAnswer;
    }

    public void setQuestAnswer(String questAnswer) {
        this.questAnswer = questAnswer;
    }

    public String getQuestKeyword() {
        return questKeyword;
    }

    public void setQuestKeyword(String questKeyword) {
        this.questKeyword = questKeyword;
    }

    public String getQuestExplain() {
        return questExplain;
    }

    public void setQuestExplain(String questExplain) {
        this.questExplain = questExplain;
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
