package com.benet.console.vmodel;

public class BlogInfoVo {

    private String blogNo;
    private String blogTitle;
    private String className;
    private String blogContent;
    private int dolikeHit;
    private int repostHit;
    private String checkState;
    private String pubTime;

    public String getBlogNo() {
        return blogNo;
    }

    public void setBlogNo(String blogNo) {
        this.blogNo = blogNo;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public int getDolikeHit() {
        return dolikeHit;
    }

    public void setDolikeHit(int dolikeHit) {
        this.dolikeHit = dolikeHit;
    }

    public int getRepostHit() {
        return repostHit;
    }

    public void setRepostHit(int repostHit) {
        this.repostHit = repostHit;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }
}
