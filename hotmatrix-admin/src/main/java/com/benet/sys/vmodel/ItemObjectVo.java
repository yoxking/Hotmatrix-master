package com.benet.sys.vmodel;

import java.util.List;

public class ItemObjectVo {
    private String id;
    private String label;

    private List<ItemObjectVo> children;

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

    public List<ItemObjectVo> getChildren() {
        return children;
    }

    public void setChildren(List<ItemObjectVo> children) {
        this.children = children;
    }
}
