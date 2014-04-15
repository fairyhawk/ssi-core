package com.yizhilu.os.ssicore.common.intercepter;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @ClassName  com.yizhilu.common.intercepter.LoggerIntercepter
 * @description
 * @author : qinggang.liu 305050016@qq.com
 * @Create Date : 2014-3-29 下午6:00:37
 */
@SuppressWarnings("serial")
public class LoggerIntercepter  extends AbstractInterceptor  {

    private static Logger logger = Logger.getLogger(LoggerIntercepter.class);
    
    @SuppressWarnings({ "unchecked" })
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        
        ActionContext actionContext = actionInvocation.getInvocationContext();   
        HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        String  invokeUrl=  request.getContextPath() + request.getServletPath();
        
        StringBuffer buffer = new StringBuffer("");
        Enumeration<String> enume = request.getParameterNames();
        while (enume.hasMoreElements()) {
            String key = enume.nextElement();
            String[] value = request.getParameterValues(key);
            if (buffer.toString().length() > 0) {
                buffer.append(";");
            }
            buffer.append(key).append(":").append(Arrays.toString(value));
        }
        logger.info("+++user_access_log ,url=" + invokeUrl + ",parameter=" + buffer);
        return actionInvocation.invoke();
    }

}
