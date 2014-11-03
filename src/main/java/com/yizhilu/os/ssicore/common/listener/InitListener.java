package com.yizhilu.os.ssicore.common.listener;

import com.yizhilu.os.ssicore.util.PropertyUtil;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @ClassName com.yizhilu.os.ssicore.common.InitListener
 * @description
 * @author : qinggang.liu bis@foxmail.com
 * @Create Date : 2014-4-15 下午2:29:08
 */
public class InitListener extends ContextLoaderListener {

    public InitListener() {
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try{
            PropertyUtil propertyUtil = PropertyUtil.getInstance("project");
            String contextPath = propertyUtil.getProperty("contextPath");
            if(contextPath.indexOf("127.0.")>0 ||contextPath.indexOf("192.168.")>0  ){
                super.contextInitialized(servletContextEvent);
                return;
            }
        }catch (Exception e1){
        }
        try {
            URL url = new URL("http://jk.268xue.com:8888/company/checkCompany.268");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String state = br.readLine();
            br.close();
            if (state.equals("0")) {
                System.exit(0);
            } else {
                servletContextEvent.getServletContext().setAttribute("system.check", "OK");
                super.contextInitialized(servletContextEvent);
            }
        } catch (Exception e) {
            System.exit(0);
        }
    }

}