package com.group.integrate.util;

import com.baomidou.mybatisplus.plugins.Page;
import com.group.integrate.util.dto.SortDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lichao on 2017/8/16.
 */
public class PageUtil {

    public static String PAGE_FORM_LOAN_APPLICATION_AUDIT_APPROVAL = "借款单审核";
    public static String PAGE_FORM_APPROVAL = "单据审批";
    public static String PAGE_FORM_FINANCE_SEARCH = "单据查看";
    public static String PAGE_FORM_FINANCE_SEARCH_APPLICATION = "财务查看费用申请";

    /**
     * 封装分页参数
     * @param page mybatis分页
     * @param baseUrl 接口url
     * @return
     * @throws URISyntaxException
     */
    public static HttpHeaders generatePaginationHttpHeaders(Page<?> page, String baseUrl)
        throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", "" + page.getTotal());
        String urlConnector="?";
        if(baseUrl.contains("?")){
            urlConnector="&";
        }
        String link = "";
        //下一页
        if ((page.getCurrent() + 1) < page.getPages()) {
            link = "<" + (new URI(baseUrl +urlConnector+"page=" + (page.getCurrent() + 1) + "&size=" + page.getSize())).toString() + ">; rel=\"next\",";
        }
        // 上一页
        if ((page.getCurrent()) > 0) {
            link += "<" + (new URI(baseUrl +urlConnector+"page=" + (page.getCurrent() - 1) + "&size=" + page.getSize())).toString() + ">; rel=\"prev\",";
        }
        // 最后一页和第一页
        int lastPage = 0;
        if (page.getPages() > 0) {
            lastPage = page.getPages() - 1;
        }
        link += "<" + (new URI(baseUrl +urlConnector+"page=" + lastPage + "&size=" + page.getSize())).toString() + ">; rel=\"last\",";
        link += "<" + (new URI(baseUrl +urlConnector+"page=" + 0 + "&size=" + page.getSize())).toString() + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }

    /**
     * 转换jpa page参数为mybatis
     * @param pageable jpa分页参数 第一页默认为 0
     * @return mybatis page：默认第一页为 1
     */
    public static Page getMyBatisPage(Pageable pageable){
        return  new Page(pageable.getPageNumber()+1,pageable.getPageSize());
    }

    public static List<SortDTO> getSorts(Pageable pageable, String methodName) {
        List<SortDTO> sortDTOS = new ArrayList<>();
        if (pageable.getSort() != null) {
            for (Iterator<Sort.Order> it = pageable.getSort().iterator(); it.hasNext(); ) {
                Sort.Order order = it.next();
                SortDTO sortDTO = new SortDTO();
                String property = null;
                if ("applicantName".equals(order.getProperty())) {
                    property = "full_name";
                } else if ("employeeID".equals(order.getProperty())) {
                    property = "employee_id";
                } else if ("totalAmount".equals(order.getProperty())) {
                    property = "total_amount";
                } else if("totalAmount".equals(order.getProperty()) && (PAGE_FORM_FINANCE_SEARCH_APPLICATION.equals(methodName))){
                    //申请单所选币种对应金额
                    property = "origin_currency_total_amount";
                } else if ("submittedDate".equals(order.getProperty()) && "".equals(methodName)) {
                    property = "last_submitted_date";
                } else if ("submittedDate".equals(order.getProperty()) && (PAGE_FORM_LOAN_APPLICATION_AUDIT_APPROVAL.equals(methodName) || PAGE_FORM_APPROVAL.equals(methodName))) {
                    property = "submitted_date";
                } else if ("lastSubmittedDate".equals(order.getProperty()) && (PAGE_FORM_FINANCE_SEARCH.equals(methodName) || PAGE_FORM_FINANCE_SEARCH_APPLICATION.equals(methodName))) {
                    property = "last_modified_date";
                } else if ("baseCurrencyRealPaymentAmount".equals(order.getProperty())) {
                    property = "real_payment_base_amount";
                } else if ("paymentAmount".equals(order.getProperty())) {
                    property = "total_amount";
                } else if ("formName".equals(order.getProperty())) {
                    property = "form_name";
                } else if ("childBusinessCode".equals(order.getProperty())) {
                    property = "business_code";
                } else if ("parentBusinessCode".equals(order.getProperty())) {
                    property = "parent_business_code";
                } else if ("businessCode".equals(order.getProperty())) {
                    property = "business_code";
                } else {
                    continue;
                }
                sortDTO.setProperty(property);
                sortDTO.setDirection(order.getDirection().name());
                sortDTOS.add(sortDTO);
            }

        }
        return sortDTOS;
    }

    public static List<SortDTO> getSortsV2(List<SortDTO> sortDTOS, String methodName) {
        List<SortDTO> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sortDTOS)) {
            for (SortDTO order : sortDTOS) {
                SortDTO sortDTO = new SortDTO();
                String property = null;
                if ("applicantName".equals(order.getProperty())) {
                    property = "full_name";
                } else if ("employeeID".equals(order.getProperty())) {
                    property = "employee_id";
                } else if ("totalAmount".equals(order.getProperty())) {
                    property = "total_amount";
                } else if ("totalAmount".equals(order.getProperty()) && (PAGE_FORM_FINANCE_SEARCH_APPLICATION.equals(methodName))) {
                    //申请单所选币种对应金额
                    property = "origin_currency_total_amount";
                } else if ("submittedDate".equals(order.getProperty()) && "".equals(methodName)) {
                    property = "last_submitted_date";
                } else if ("submittedDate".equals(order.getProperty()) && (PAGE_FORM_LOAN_APPLICATION_AUDIT_APPROVAL.equals(methodName) || PAGE_FORM_APPROVAL.equals(methodName))) {
                    property = "submitted_date";
                } else if ("lastSubmittedDate".equals(order.getProperty()) && (PAGE_FORM_FINANCE_SEARCH.equals(methodName) || PAGE_FORM_FINANCE_SEARCH_APPLICATION.equals(methodName))) {
                    property = "last_modified_date";
                } else if ("baseCurrencyRealPaymentAmount".equals(order.getProperty())) {
                    property = "real_payment_base_amount";
                } else if ("paymentAmount".equals(order.getProperty())) {
                    property = "total_amount";
                } else if ("formName".equals(order.getProperty())) {
                    property = "form_name";
                } else if ("childBusinessCode".equals(order.getProperty())) {
                    property = "business_code";
                } else if ("parentBusinessCode".equals(order.getProperty())) {
                    property = "parent_business_code";
                } else if ("businessCode".equals(order.getProperty())) {
                    property = "business_code";
                } else {
                    continue;
                }
                sortDTO.setProperty(property);
                sortDTO.setDirection(order.getDirection());
                result.add(sortDTO);
            }
        } else {
            SortDTO sortDTO = new SortDTO();
            sortDTO.setProperty("last_modified_date");
            sortDTO.setDirection("desc");
            result.add(sortDTO);
        }
        return result;
    }

    public static HttpHeaders generatePaginationHttpHeadersWithStatistics(Page<?> page, String baseUrl, int disabledNum, int enabledNum) throws URISyntaxException {
        HttpHeaders httpHeaders = generatePaginationHttpHeaders(page, baseUrl);
        httpHeaders.add("X-Enabled-Count", String.valueOf(enabledNum));
        httpHeaders.add("X-Disabled-Count", String.valueOf(disabledNum));
        return httpHeaders;
    }
}
