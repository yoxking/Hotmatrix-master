package com.benet.collect.vmodel;

public class PaperInfoVo {
    private String paperNo;
    private String paperTitle;
    private String qsetsNo;
    private String paperQsets;
    private String questsNo;
    private String paperQuests;
    private String paperQrules;
    private int paperTscore;

    public String getPaperNo() {
        return paperNo;
    }

    public void setPaperNo(String paperNo) {
        this.paperNo = paperNo;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperQsets() {
        return paperQsets;
    }

    public String getQsetsNo() {
        return qsetsNo;
    }

    public void setQsetsNo(String qsetsNo) {
        this.qsetsNo = qsetsNo;
    }

    public void setPaperQsets(String paperQsets) {
        this.paperQsets = paperQsets;
    }

    public String getQuestsNo() {
        return questsNo;
    }

    public void setQuestsNo(String questsNo) {
        this.questsNo = questsNo;
    }

    public String getPaperQuests() {
        return paperQuests;
    }

    public void setPaperQuests(String paperQuests) {
        this.paperQuests = paperQuests;
    }

    public String getPaperQrules() {
        return paperQrules;
    }

    public void setPaperQrules(String paperQrules) {
        this.paperQrules = paperQrules;
    }

    public int getPaperTscore() {
        return paperTscore;
    }

    public void setPaperTscore(int paperTscore) {
        this.paperTscore = paperTscore;
    }
}
