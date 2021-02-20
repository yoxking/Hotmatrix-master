package com.benet.console.vmodel;

public class LoginerVo {

    private String ruserNo;
    private String ruserName;
    private String rnickName;
    private String ruserAvatar;
    private String ruserType;
    private String ruserSex;
    private String telephone;
    private String ruserMail;
    private String loginDate;
    private String suserNo;//关联的系统账号

    public String getRuserNo() {
        return ruserNo;
    }

    public void setRuserNo(String ruserNo) {
        this.ruserNo = ruserNo;
    }

    public String getRuserName() {
        return ruserName;
    }

    public void setRuserName(String ruserName) {
        this.ruserName = ruserName;
    }

    public String getRnickName() {
        return rnickName;
    }

    public void setRnickName(String rnickName) {
        this.rnickName = rnickName;
    }

    public String getRuserAvatar() {
        return ruserAvatar;
    }

    public void setRuserAvatar(String ruserAvatar) {
        this.ruserAvatar = ruserAvatar;
    }

    public String getRuserType() {
        return ruserType;
    }

    public void setRuserType(String ruserType) {
        this.ruserType = ruserType;
    }

    public String getRuserSex() {
        return ruserSex;
    }

    public void setRuserSex(String ruserSex) {
        this.ruserSex = ruserSex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRuserMail() {
        return ruserMail;
    }

    public void setRuserMail(String ruserMail) {
        this.ruserMail = ruserMail;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getSuserNo() {
        return suserNo;
    }

    public void setSuserNo(String suserNo) {
        this.suserNo = suserNo;
    }
}
