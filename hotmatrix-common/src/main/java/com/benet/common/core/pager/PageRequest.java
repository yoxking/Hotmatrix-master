package com.benet.common.core.pager;

import com.benet.common.utils.string.StringUtils;

/**
 * 分页请求数据
 *
 * @author yoxking
 */
public class PageRequest
{
    /** 当前记录起始索引 */
    private Integer pageIndex;
    /** 每页显示记录数 */
    private Integer pageSize;
    /** 查询条件 */
    private String condition;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}