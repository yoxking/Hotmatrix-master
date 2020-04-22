package com.benet.sys.vmodel;

import java.util.List;

public class OrgnzInfoVo {

    /** 机构id */
    private String orgnzNo;

    /** 机构名称 */
    private String orgnzName;

    /** 父机构id */
    private String parentNo;

    /** 显示顺序 */
    private Integer orderNo;

    /** 备注 */
    private String comments;

    private List<OrgnzInfoVo> children;

    public String getOrgnzNo() {
        return orgnzNo;
    }

    public void setOrgnzNo(String orgnzNo) {
        this.orgnzNo = orgnzNo;
    }

    public String getOrgnzName() {
        return orgnzName;
    }

    public void setOrgnzName(String orgnzName) {
        this.orgnzName = orgnzName;
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<OrgnzInfoVo> getChildren() {
        return children;
    }

    public void setChildren(List<OrgnzInfoVo> children) {
        this.children = children;
    }
}
