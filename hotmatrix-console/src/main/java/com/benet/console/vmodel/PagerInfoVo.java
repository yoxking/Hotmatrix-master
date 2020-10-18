package com.benet.console.vmodel;

import java.io.Serializable;
import java.util.List;

public class PagerInfoVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页
    private int pageIndex;
    //每页的数量
    private int pageSize;
    //总页数
    private int pageTotal;
    //总记录数
    private long rowsTotal;
    //结果集
    private List<T> list;
    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;


    public PagerInfoVo() {
    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PagerInfoVo(int pageIndex,int pageSize,int rowsTotal,List<T> list) {

        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.rowsTotal = rowsTotal;
        this.pageTotal = calcPageTotal();
        this.list = list;
        //判断页面边界
        judgeBoudary();
    }

    /**
     * 计算总页数
     * @return
     */
    private int calcPageTotal(){
        return (int)Math.ceil((double)this.rowsTotal/this.pageSize);
    }

    /**
     * 判定页面边界
     */
    private void judgeBoudary() {
        isFirstPage = (pageIndex == 1);
        isLastPage = (pageIndex == pageTotal);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public long getRowsTotal() {
        return rowsTotal;
    }

    public void setRowsTotal(long rowsTotal) {
        this.rowsTotal = rowsTotal;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageIndex=").append(pageIndex);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", pageTotal=").append(pageTotal);
        sb.append(", rowsTotal=").append(rowsTotal);
        sb.append(", list=").append(list);
        sb.append(", isFirstPage=").append(isFirstPage);
        sb.append(", isLastPage=").append(isLastPage);
        sb.append('}');
        return sb.toString();
    }
}
