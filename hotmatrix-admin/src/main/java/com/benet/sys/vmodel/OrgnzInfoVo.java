package com.benet.sys.vmodel;

import java.util.List;

public class OrgnzInfoVo {

    /** id */
    private String id;

    /** 标准 */
    private String label;

    /** 机构id */
    private String organizNo;

    /** 机构名称 */
    private String organizName;

    /** 父机构id */
    private String parentNo;

    /** 显示顺序 */
    private Integer orderNo;

    /** 备注 */
    private String comments;

    private List<OrgnzInfoVo> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOrganizNo() {
        return organizNo;
    }

    public void setOrganizNo(String organizNo) {
        this.organizNo = organizNo;
    }

    public String getOrganizName() {
        return organizName;
    }

    public void setOrganizName(String organizName) {
        this.organizName = organizName;
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
