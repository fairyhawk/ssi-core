package com.yizhilu.os.ssicore.util;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.yizhilu.os.ssicore.service.ConfigService;

/**
 * Cookie的操作类
 * 
 * @author guoqiang.liu
 * 
 */
public class CookieHandler {
	private static String MYDOMAIN="127.0.0.1";
	private static ConfigService configurator;
	/**
	 * 创建cookie
	 * 
	 * @param response
	 *            回应
	 * @param nameValues
	 *            存入cookie的键值对
	 * @param days
	 *            设置cookie的有效期
	 */
	public static void createCookie(HttpServletResponse response,
			Hashtable<String, String> nameValues, int days) {
		Set<String> set = nameValues.keySet();
		Iterator<String> it = set.iterator();
		for (; it.hasNext();) {
			String name = (String) it.next();
			String value = (String) nameValues.get(name);
			// 生成新的cookie
			Cookie cookie = new Cookie(name, value);
			cookie.setDomain(configurator.getMyDomain());
			
			// 设置有效日期
			cookie.setMaxAge(days * 24 * 60 * 60);
			// 设置路径（默认）
			cookie.setPath("/");
			// 把cookie放入响应中
			response.addCookie(cookie);
		}
	}
	   /**
     * 创建cookie
     * 
     * @param response
     *            回应
     * @param nameValues
     *            存入cookie的键值对
     * @param days
     *            设置cookie的有效期
     */
    public static void createCookieDomain(HttpServletResponse response,
            Hashtable<String, String> nameValues, int days,String domain) {
        Set<String> set = nameValues.keySet();
        Iterator<String> it = set.iterator();
        for (; it.hasNext();) {
            String name = (String) it.next();
            String value = (String) nameValues.get(name);
            // 生成新的cookie
            Cookie cookie = new Cookie(name, value);
            if(StringUtils.isNotEmpty(domain)){
            	cookie.setDomain(domain);
            }else{
            	cookie.setDomain(configurator.getMyDomain());
            }
            cookie.setSecure(false);
            // 设置有效日期
            cookie.setMaxAge(days * 24 * 60 * 60);
            // 设置路径（默认）
            cookie.setPath("/");
            // 把cookie放入响应中
            response.addCookie(cookie);
        }
    }

	/**
	 * 读取Cookie
	 * 
	 * @param request
	 * @return Hashtable 返回cookie的键值对
	 */
	public static Hashtable<String, String> getCookies(
			HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Hashtable<String, String> cookieHt = new Hashtable<String, String>();
		if (cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				cookieHt.put(cookie.getName(), cookie.getValue());
			}
		}
		return cookieHt;
	}

	/**
	 * 修改cookie中指定键的值
	 * 
	 * @param request
	 * @param name
	 *            指定的键
	 * @param value
	 *            值
	 */
	public static void setCookieValueByName(HttpServletRequest request,
			String name, String value) {
		Cookie[] cookies = request.getCookies();
		if (cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (name.equalsIgnoreCase(cookies[i].getName())) {
					cookies[i].setValue(value);
					return;
				}
			}
		}
	}

	/**
	 * 增加或修改cookie
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param days
	 */
	public static void createOrUpdateCookie(HttpServletRequest request, HttpServletResponse response,
			String name, String value, int days) {
		Cookie cookie = new Cookie(name, value);
		cookie.setDomain(configurator.getMyDomain());
		// 设置有效日期
		cookie.setMaxAge(days * 24 * 60 * 60);
		// 设置路径（默认）
		cookie.setPath("/");
		// 把cookie放入响应中
		response.addCookie(cookie);
	}

	/**
	 * 增加或修改cookie
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param days
	 */
	public static void createCookie(HttpServletResponse response,
			String name, String value, int days) {
		Cookie cookie = new Cookie(name, value);
		cookie.setDomain(configurator.getMyDomain());
		// 设置有效日期
		cookie.setMaxAge(days * 24 * 60 * 60);
		// 设置路径（默认）
		cookie.setPath("/");
		// 把cookie放入响应中
		response.addCookie(cookie);
	}

	/**
	 * 得到指定键的值
	 * 
	 * @param request
	 * @param name
	 *            指定的键
	 * @return String 值
	 */
	public static String getCookieValueByName(HttpServletRequest request,
			String name) {
		Cookie[] cookies = request.getCookies();
		String resValue = "";
		if(cookies!=null){
			if (cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					if (name.equalsIgnoreCase(cookies[i].getName())) {
						resValue = cookies[i].getValue();
						if(resValue!=null && !"".equals(resValue)){
							return resValue;
						}
					}
				}
			}
		}
		return resValue;
	}

	/**
	 * 销毁所有cookie
	 * 
	 * @param request
	 * @param response
	 */
	public static void deleteCookie(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				// 销毁
				Cookie ck = new Cookie(cookie.getName(),null);
				cookie.setDomain(configurator.getMyDomain());
				ck.setPath("/");
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
		}
	}

	/**
	 * 根据name销毁cookie
	 * 
	 * @param request
	 * @param response
	 */
	public static void deleteCookieByName(HttpServletRequest request,
			HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			if (cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					if (name.equalsIgnoreCase(cookies[i].getName())) {
						Cookie cookie = cookies[i];
						// 销毁
						Cookie ck = new Cookie(cookie.getName(),null);
						cookie.setDomain(configurator.getMyDomain());
						ck.setPath("/");
						ck.setMaxAge(0);
						response.addCookie(ck);
						return;
					}
				}
			}
		}
	}
	
	   /**
     * @param request
     * @param response
     * @param name
     * @param value
     * @param Minutes 分钟
     */
    public static void createCookieMinute(HttpServletResponse response,
            String name, String value, int minuts) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(configurator.getMyDomain());
        // 设置有效日期
        cookie.setMaxAge(minuts * 60);
        // 设置路径（默认）
        cookie.setPath("/");
        // 把cookie放入响应中
        response.addCookie(cookie);
    }
	public static ConfigService getConfigurator() {
		return configurator;
	}
	public static void setConfigurator(ConfigService configurator) {
		CookieHandler.configurator = configurator;
	}
    
}
