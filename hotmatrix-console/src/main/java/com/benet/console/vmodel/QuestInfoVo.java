package com.benet.console.vmodel;

import java.util.List;

public class QuestInfoVo {

    private String questNo;
    private String questTitle;
    private String questType;
    private String questDesc;
    private String questMust;
    private List<QuestOptsVo> questOpts;

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

    public String getQuestMust() {
        return questMust;
    }

    public void setQuestMust(String questMust) {
        this.questMust = questMust;
    }

    public List<QuestOptsVo> getQuestOpts() {
        return questOpts;
    }

    public void setQuestOpts(List<QuestOptsVo> questOpts) {
        this.questOpts = questOpts;
    }
}
