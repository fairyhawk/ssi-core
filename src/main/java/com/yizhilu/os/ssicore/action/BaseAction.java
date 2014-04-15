package com.yizhilu.os.ssicore.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yizhilu.os.ssicore.domain.PageResult;
import com.yizhilu.os.ssicore.domain.Result;

/**
 * 
 * @ClassName com.yizhilu.os.ssicore.controller.BaseController
 * @description 通用的action.所有的Controller继承，公用的写到此方法中
 * @author : qinggang.liu 305050016@qq.com
 * @Create Date : 2013-12-13 下午2:30:00
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    /**
     * 
     */
    private static final long serialVersionUID = 7768906315160937362L;

    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");

    /** 分页结果对象 */
    @Getter
    @Setter
    private PageResult page;
    /** 分页页面地址及参数 */
    @Setter
    private String pageUrlParms;
    /** log对象 */
    @Getter
    private static final Logger logger = Logger.getLogger(BaseAction.class);
    /** request对象 */
    protected HttpServletRequest servletRequest;
    /** response对象 */
    private HttpServletResponse servletResponse;
    
    @Getter
    @Setter
    private Result<?> result;

    public String getUuid() {
        return UUID.randomUUID().toString();
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest != null ? servletRequest : ServletActionContext.getRequest();
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public HttpServletResponse getServletResponse() {
        return (servletResponse != null ? servletResponse : ServletActionContext.getResponse());
    }

    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    /**
     * 获取URL及参数
     * 
     * @return 类似于a.action?name=tx&age=20
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("rawtypes")
    public String getUrlParms() {
        StringBuffer sbUrlParms = servletRequest.getRequestURL();
        sbUrlParms.append("?");
        Enumeration parNames = servletRequest.getParameterNames();
        while (parNames.hasMoreElements()) {
            String parName = parNames.nextElement().toString();
            try {
                sbUrlParms.append(parName).append("=").append(URLEncoder.encode(servletRequest.getParameter(parName), "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return ERROR;
            }
        }
        return sbUrlParms.toString();
    }

    public String getUrlParms1() {
        return servletRequest.getQueryString();
    }

    public String getPageUrlParms() {
        return pageUrlParms;
    }

    public void setPageUrlParms() {
        this.pageUrlParms = getUrlParms();
    }

    public void setSession(String name, Object o) {
        ActionContext.getContext().getSession().put(name, o);
    }

    @SuppressWarnings("unchecked")
    public <T extends Object> T getSession(String name) {
        if (ActionContext.getContext().getSession().get(name) == null) {
            return null;
        } else {
            return (T) ActionContext.getContext().getSession().get(name);
        }
    }

    protected String getRealPath(String path) {
        return ServletActionContext.getServletContext().getRealPath(path);
    }

}
