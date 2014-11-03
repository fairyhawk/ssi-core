package com.yizhilu.os.ssicore.util.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @ClassName com.yizhilu.os.ssicore.util.WebUtils
 * @description cookie和web一些工具类
 * @author : qinggang.liu 305050016@qq.com
 * @Create Date : 2013-12-13 下午2:26:49
 */
public class WebUtils {

    // 存储主站域名
    public static String MYDOMAIN = "";

    /**
     * 设置cookie分钟
     * 
     * @param response
     * @param key
     * @param value
     * @param minuts
     *            分钟
     */
    public static void setCookieMinute(HttpServletResponse response, String key, String value, int minuts) {
        setCookieMinuteDomain(response, key, value, minuts, MYDOMAIN);
    }

    /**
     * 增加或修改cookie
     * 
     * @param response
     * @param key
     * @param value
     * @param days
     */
    public static void setCookieMinuteDomain(HttpServletResponse response, String key, String value, int minuts, String domain) {
        if (key != null && value != null) {
            Cookie cookie = new Cookie(key, value);
            // 设置有效日期
            cookie.setMaxAge(minuts * 60);
            // 设置路径（默认）
            cookie.setPath("/");
            if (StringUtils.isNotEmpty(domain)) {// domain != null
                cookie.setDomain(domain);
            }
            // 把cookie放入响应中
            response.addCookie(cookie);
        }
    }

    /**
     * 增加或修改cookie
     * 
     * @param response
     * @param key
     * @param value
     * @param days
     */
    public static void setCookie(HttpServletResponse response, String key, String value, int days) {
        setCookie(response, key, value, days, MYDOMAIN);
    }

    /**
     * 增加或修改cookie
     * 
     * @param response
     * @param key
     * @param value
     * @param days
     */
    public static void setCookie(HttpServletResponse response, String key, String value, int days, String domain) {

        if (key != null && value != null) {
            Cookie cookie = new Cookie(key, value);
            // 设置有效日期
            cookie.setMaxAge(days * 24 * 60 * 60);
            // 设置路径（默认）
            cookie.setPath("/");
            if (StringUtils.isNotEmpty(domain)) {// domain != null
                cookie.setDomain(domain);
            }
            // 把cookie放入响应中
            response.addCookie(cookie);
        }

    }
    /**
     * 增加或修改cookie Session
     * 
     * @param response
     * @param key
     * @param value
     * @param days
     */
    public static void setCookieSessionTime(HttpServletResponse response, String key, String value) {
        setCookieSessionTime(response, key, value, MYDOMAIN);
    }

    /**
     * 增加或修改cookie Session
     * 
     * @param response
     * @param key
     * @param value
     * @param days
     */
    public static void setCookieSessionTime(HttpServletResponse response, String key, String value, String domain) {

        if (key != null && value != null) {
            Cookie cookie = new Cookie(key, value);
            // 设置有效日期
            cookie.setMaxAge(-1);
            // 设置路径（默认）
            cookie.setPath("/");
            if (StringUtils.isNotEmpty(domain)) {// domain != null
                cookie.setDomain(domain);
            }
            // 把cookie放入响应中
            response.addCookie(cookie);
        }

    }
    /**
     * 得到指定键的值
     * 
     * @param request
     * @param name
     *            指定的键
     * @return String 值
     */
    public static String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        String resValue = "";
        if (cookies != null) {
            if (cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    if (key.equalsIgnoreCase(cookies[i].getName())) {
                        if (StringUtils.isNotEmpty(cookies[i].getValue())) {
                            resValue = cookies[i].getValue();
                        }
                    }
                }
            }
        }
        return resValue;
    }

    /**
     * 根据name销毁cookie
     * 
     * @param request
     * @param response
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        deleteCookieDomain(request, response, name, MYDOMAIN);

    }

    /**
     * 根据域名和name销毁cookie
     * 
     * @param request
     * @param response
     * @param
     */
    public static void deleteCookieDomain(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            if (cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    if (name.equalsIgnoreCase(cookies[i].getName())) {
                        // 销毁
                        Cookie ck = new Cookie(cookies[i].getName(), null);
                        ck.setPath("/");
                        if (StringUtils.isNotEmpty(domain)) {// domain != null
                            ck.setDomain(domain);
                        }
                        ck.setMaxAge(0);
                        response.addCookie(ck);
                        return;
                    }
                }
            }
        }
    }

    /**
     * 创建cookie
     * 
     * @param response
     *            回应
     * @param nameValues
     *            Hashtable<String, String> 存入cookie的键值对
     * @param days
     *            设置cookie的有效期
     */
    public static void createCookieFromMap(HttpServletResponse response, Hashtable<String, String> nameValues, int days) {
        createCookieFromMapDomain(response, nameValues, days, MYDOMAIN);
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
     * @param domain
     *            设置的域名
     */
    public static void createCookieFromMapDomain(HttpServletResponse response, Hashtable<String, String> nameValues, int days, String domain) {
        Set<String> set = nameValues.keySet();
        Iterator<String> it = set.iterator();
        for (; it.hasNext();) {
            String name = (String) it.next();
            String value = (String) nameValues.get(name);
            // 生成新的cookie
            Cookie cookie = new Cookie(name, value);
            if (StringUtils.isNotEmpty(domain)) {// domain != null
                cookie.setDomain(domain);
            }
            // 设置有效日期
            cookie.setMaxAge(days * 24 * 60 * 60);
            // 设置路径（默认）
            cookie.setPath("/");
            // 把cookie放入响应中
            response.addCookie(cookie);
        }
    }

    /**
     * 读取所有Cookie
     * 
     * @param request
     * @return Hashtable 返回cookie的键值对
     */
    public static Hashtable<String, String> getCookiesForMap(HttpServletRequest request) {
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
    public static void updateCookie(HttpServletRequest request, String name, String value) {
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
     * 销毁所有cookie
     * 
     * @param request
     * @param response
     */
    public static void deleteAllCookie(HttpServletRequest request, HttpServletResponse response) {
        deleteAllCookieDomain(request, response, MYDOMAIN);
    }

    public static void deleteAllCookieDomain(HttpServletRequest request, HttpServletResponse response, String domain) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                // 销毁
                Cookie ck = new Cookie(cookie.getName(), null);
                ck.setPath("/");
                if (StringUtils.isNotEmpty(domain)) {
                    ck.setDomain(domain);
                }
                ck.setMaxAge(0);
                response.addCookie(ck);
            }
        }
    }

    // 获得IP地址
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }

    /**
     * 获得用户浏览器ua
     * 
     * @param request
     * @return String 浏览器类型
     */
    public static String getUserAgent(HttpServletRequest request) {

        String IE9 = "MSIE 9.0";
        String IE8 = "MSIE 8.0";
        String IE7 = "MSIE 7.0";
        String IE6 = "MSIE 6.0";
        String MAXTHON = "Maxthon";
        String QQ = "QQBrowser";
        String GREEN = "GreenBrowser";
        String SE360 = "360SE";
        String FIREFOX = "Firefox";
        String OPERA = "Opera";
        String CHROME = "Chrome";
        String SAFARI = "Safari";
        String OTHER = "其它";
        String iPad = "iPad";
        String Android = "Android";
        String iPhone = "iPhone";
        String iPod = "iPod";
        String WindowsPhone = "Windows Phone";
        String Macintosh = "Macintosh";// Mac

        String userAgent = request.getHeader("User-Agent");
        userAgent = userAgent.toLowerCase();
        if (regex(OPERA, userAgent))
            return OPERA;
        if (regex(CHROME, userAgent))
            return CHROME;
        if (regex(FIREFOX, userAgent))
            return FIREFOX;
        if (regex(SAFARI, userAgent))
            return SAFARI;
        if (regex(SE360, userAgent))
            return SE360;
        if (regex(GREEN, userAgent))
            return GREEN;
        if (regex(QQ, userAgent))
            return QQ;
        if (regex(MAXTHON, userAgent))
            return MAXTHON;
        if (regex(IE9, userAgent))
            return IE9;
        if (regex(IE8, userAgent))
            return IE8;
        if (regex(IE7, userAgent))
            return IE7;
        if (regex(IE6, userAgent))
            return IE6;
        if (regex(iPad, userAgent))
            return iPad;
        if (regex(Android, userAgent))
            return Android;
        if (regex(iPhone, userAgent))
            return iPhone;
        if (regex(iPod, userAgent))
            return iPod;
        if (regex(WindowsPhone, userAgent))
            return WindowsPhone;
        if (regex(Macintosh, userAgent))
            return Macintosh;
        return OTHER;
    }

    public static boolean regex(String regex, String str) {
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher m = p.matcher(str);
        return m.find();
    }

    // encodeURL
    public static String encodeURL(String url, String encode) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        StringBuilder noAsciiPart = new StringBuilder();
        for (int i = 0; i < url.length(); i++) {
            char c = url.charAt(i);
            if (c > 255) {
                noAsciiPart.append(c);
            } else {
                if (noAsciiPart.length() != 0) {
                    sb.append(URLEncoder.encode(noAsciiPart.toString(), encode));
                    noAsciiPart.delete(0, noAsciiPart.length());
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // IPUTIL********

    public static String getAddressByIP(String ip) {
        String res = "未知";
        try {
            String js = visitWeb("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=" + ip);
            if (com.yizhilu.os.ssicore.util.StringUtils.isEmpty(js)) {
                return res;
            }
            js=js.trim();
            JSONObject jo = JSONObject.fromObject(js.substring(21, js.length() - 1));
            String province = "";
            String city = "";
            try {
                province = jo.get("province") == null ? "" : URLDecoder.decode(jo.get("province").toString(), "UTF-8");
                city = jo.get("city") == null ? "" : URLDecoder.decode(jo.get("city").toString(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            res = (province.equals("") || province.equals(city)) ? city : province + " " + city;
            if (com.yizhilu.os.ssicore.util.StringUtils.isEmpty(res)) {
                res = "未知";
            }
        } catch (Exception e) {
        }
        return res;
    }

    public static String visitWeb(String urlStr) {
        URL url = null;
        HttpURLConnection httpConn = null;
        InputStream in = null;
        try {
            url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows 2000)");
            in = httpConn.getInputStream();
            return convertStreamToString(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                httpConn.disconnect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static String convertStreamToString(InputStream is) throws IOException {
        if (is != null) {

            StringBuilder sb = new StringBuilder();
            String line;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                is.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    // IPUTIL********

    /**
     * 获取URL及参数
     * 
     * @return 类似于a.action?name=tx&age=20
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("rawtypes")
    public String getServletRequestUrlParms(HttpServletRequest request) {
        // 获得的地址参数，如果没有为空 ，有时是以&结束的
        StringBuffer sbUrlParms = request.getRequestURL();
        sbUrlParms.append("?");
        Enumeration parNames = request.getParameterNames();
        while (parNames.hasMoreElements()) {
            String parName = parNames.nextElement().toString();
            try {
                sbUrlParms.append(parName).append("=").append(URLEncoder.encode(request.getParameter(parName), "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }
        return sbUrlParms.toString();
    }

    /**
     * 替换掉html内容<>
     * 
     * @param src
     * @return
     */
    public static String replaceTagHTML(String src) {
        String regex = "\\<(.+?)\\>";
        if (StringUtils.isNotEmpty(src)) {
            return src.replaceAll(regex, "");
        }
        return "";
    }

    public static String clearXSS(String code) {
        code = code.replaceAll("(?i)<script[^>]*>([\\s\\S]*?)</script>", "");
        code = code.replaceAll("(?i)<script[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<applet[^>]*>([\\s\\S]*?)</applet>", "");
        code = code.replaceAll("(?i)<base[^>]*>([\\s\\S]*?)</base>", "");
        code = code.replaceAll("(?i)<base[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<head[^>]*>([\\s\\S]*?)</head>", "");
        code = code.replaceAll("(?i)<style[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<style[^>]*>([\\s\\S]*?)</style>", "");
        code = code.replaceAll("(?i)<link[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<link[^>]*>([\\s\\S]*?)</link>", "");
        code = code.replaceAll("(?i)<meta[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<meta[^>]*>([\\s\\S]*?)</meta>", "");
        code = code.replaceAll("(?i)<title[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<title[^>]*>([\\s\\S]*?)</title>", "");
        code = code.replaceAll("(?i)<object[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<object[^>]*>([\\s\\S]*?)</object>", "");
        code = code.replaceAll("(?i)<embed[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<embed[^>]*>([\\s\\S]*?)</embed>", "");
        code = code.replaceAll("(?i)<frame[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<frame[^>]*>([\\s\\S]*?)</frame>", "");
        code = code.replaceAll("(?i)<frameset[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<frameset[^>]*>([\\s\\S]*?)</frameset>", "");
        code = code.replaceAll("(?i)<iframe[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<iframe[^>]*>([\\s\\S]*?)</iframe>", "");
        code = code.replaceAll("(?i)<!--([\\s\\S]*?)-->", "");
        code = code.replaceAll("(?i)^!--(.*)--$", "");
        code = code.replaceAll("(?i)javascript:", "");
        code = code.replaceAll("(?i)vbscript:", "");
        code = code.replaceAll("(?i)data:", "");
        code = code.replaceAll("(?i)mhtml:", "");
        code = code.replaceAll("(?i)ms-its:", "");
        code = code.replaceAll("(?i)firefoxurl:", "");
        code = code.replaceAll("(?i)mocha:", "");
        code = code.replaceAll("(?i)livescript:", "");
        code = code.replaceAll("(?i)mocha:", "");
        code = code.replaceAll("(?i)eval\\(([\\s\\S]*?)\\)", "");
        code = code.replaceAll("(?i)expression\\(([\\s\\S]*?)\\)", "");
        code = code.replaceAll("(?i)url\\(([\\s\\S]*?)\\)", "");
        code = code.replaceAll("(?i) on([^>]*?)=", " ");
        code = code.replaceAll("(?i)style([\\s\\S]*?)=([\\s\\S]*?)/\\*([\\s\\S]*?)\\*/[^>]*", "");
        return code;
    }

    public static boolean isJointMobileNumber(String mobileNumber) {
        String pattern = "^(1([0-9]{10}))$";
        return mobileNumber.matches(pattern);
    }

    /**
     * 判断手机号
     */
    public static boolean isJointUserLoginName(String mobileNumber) {
        // 判断该用户是否是 手机用户
        return isJointMobileNumber(mobileNumber);
    }

    /**
     * 验证邮箱
     * 
     * @param value
     * @param length
     *            邮箱长度 默认不超过40
     * @return
     */
    public static boolean checkEmail(String value, int length) {
        if (length == 0) {
            length = 40;
        }
        return value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*") && value.length() <= length;
    }

    /**
     * 验证字符是否为6-16为字符、数字或下划线组成
     * 
     * @param password
     * @return
     */
    public static boolean isPasswordAvailable(String password) {
        String partten = "^[_0-9a-zA-Z]{3,}$";
        boolean flag = password.matches(partten) && password.length() >= 6 && password.length() <= 16;
        return flag;
    }
    public static void main(String[] args) {
        System.out.println(getAddressByIP("111.204.252.208"));
    }
    /**
     * 是否是ajax请求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request){
        String her=  request.getHeader("x-requested-with");
        if(StringUtils.isNotEmpty(her)){
            return true;
        }
        return false;
     }
    
    public static boolean isNotAjaxRequest(HttpServletRequest request){
        return !isAjaxRequest(request);
        
     }
    /**
     * 获取web项目的路径
     */
    public static String getWebRootPath(){
        String s= System.getProperty("user.dir");
        if(s.indexOf("classes")>0){
            s=s.replace("WEB-INF", "").replace("classes", "").replace(File.separator+File.separator, File.separator);
        }
        return s;
    }
    
}
