package com.benet.collect.vmodel;

import com.benet.collect.domain.CoctQuestinfo;

import java.util.List;

public class QuestDataVo {

    private int qType1Nums;
    private int qType2Nums;
    private int qType3Nums;
    private int qTotalNums;
    private int qTotalScore;
    private List<CoctQuestinfo> questsList;

    public int getqType1Nums() {
        return qType1Nums;
    }

    public void setqType1Nums(int qType1Nums) {
        this.qType1Nums = qType1Nums;
    }

    public int getqType2Nums() {
        return qType2Nums;
    }

    public void setqType2Nums(int qType2Nums) {
        this.qType2Nums = qType2Nums;
    }

    public int getqType3Nums() {
        return qType3Nums;
    }

    public void setqType3Nums(int qType3Nums) {
        this.qType3Nums = qType3Nums;
    }

    public int getqTotalNums() {
        return qTotalNums;
    }

    public void setqTotalNums(int qTotalNums) {
        this.qTotalNums = qTotalNums;
    }

    public int getqTotalScore() {
        return qTotalScore;
    }

    public void setqTotalScore(int qTotalScore) {
        this.qTotalScore = qTotalScore;
    }

    public List<CoctQuestinfo> getQuestsList() {
        return questsList;
    }

    public void setQuestsList(List<CoctQuestinfo> questsList) {
        this.questsList = questsList;
    }
}
