package com.benet.sys.vmodel;

import java.util.List;

public class PermitInfoVo {

    /** id */
    private String id;

    /** 标准 */
    private String label;

    /** 权限id */
    private String permitNo;

    /** 权限名称 */
    private String permitName;

    /** 父权限id */
    private String parentNo;

    /** 显示顺序 */
    private Integer orderNo;

    /** 备注 */
    private String comments;

    private List<PermitInfoVo> children;

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

    public String getPermitNo() {
        return permitNo;
    }

    public void setPermitNo(String permitNo) {
        this.permitNo = permitNo;
    }

    public String getPermitName() {
        return permitName;
    }

    public void setPermitName(String permitName) {
        this.permitName = permitName;
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

    public List<PermitInfoVo> getChildren() {
        return children;
    }

    public void setChildren(List<PermitInfoVo> children) {
        this.children = children;
    }
}
