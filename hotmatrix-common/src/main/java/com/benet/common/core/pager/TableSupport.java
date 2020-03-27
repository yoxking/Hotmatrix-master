package com.benet.common.core.pager;


import com.benet.common.constant.PubConstants;
import com.benet.common.utils.web.ServletUtils;

/**
 * 表格数据处理
 *
 * @author yoxking
 */
public class TableSupport
{
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(PubConstants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(PubConstants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(PubConstants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(PubConstants.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }
}