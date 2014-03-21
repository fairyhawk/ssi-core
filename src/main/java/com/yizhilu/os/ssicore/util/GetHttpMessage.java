package com.yizhilu.os.ssicore.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
/**
 * 获得亿起发URL的返回值，接口1实现
 * 
 * @author liuqinggang
 */
public class GetHttpMessage {
	/**
	 * 获得亿起发接口的返回值，订单插入
	 * 
	 * @param param
	 *            url参数
	 * @return String(0=正确插入参数，1=缺少必要参数，2=参数格式错误)
	 */
	// 获得亿起发URL的返回值，接口1实现
	public static String getHttpMessageFromGet(String param) {

		String msg = "1";// 默认返回值1
		try {
			if ("".equals(param) || null == param) {
				return msg;
			}
			// URL格式
			String urlstr = "http://o.yiqifa.com/servlet/handleCpsIn";

			URL url = new URL(urlstr);
			// 获得连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			// 网页提交方式
			connection.setRequestMethod("GET");
			connection.connect();
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.writeBytes(param);
			out.close();
			// 取得inputstream.发送请求
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = br.readLine();
			if (line != null) {
				msg = new String(line.getBytes(), "utf-8");
			}
			br.close();
			connection.disconnect();
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return msg;
		}

	}
	
	/**
	 * 通过站点的代码和参数想第三方站点推送数据
	 * 1：返利网
	 * @param siteCode
	 * @param param
	 * @return
	 */
	public static String sendContractToOtherSiteFromGet(int siteCode,String param){
		
		String siteUrl = "";
		switch(siteCode){
			case 1:
				siteUrl = "http://data.51fanli.com/union/fanliorder.asp";
				break;
			case 2:
				siteUrl = "";
				break;
			default:
				siteUrl = "";
		}
		String flag = getHttpMessageFromGet(siteCode,siteUrl,param);
		return flag;
	}

	/**
	 * 通过提供的网站地址和路径将信息推送到第三方站点
	 * @param param
	 * @return
	 */
	public static String getHttpMessageFromGet(int siteCode,String urlStr,String param) {

		String msg = "0";
		if ("".equals(param) || null == param) {
			return msg;
		}
		return msg;
	}	
	
	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param queryString
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	public static String doGet(String url, String queryString) {
		String response = null;
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		try {
			if (StringUtils.isNotBlank(queryString))
				method.setQueryString(URIUtil.encodeQuery(queryString));
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
			}
		} catch (URIException e) {
			
		} catch (IOException e) {
		} finally {
			method.releaseConnection();
		}
		return response;
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	public static String doPost(String url, Map<String, String> params) {
		String response = null;
		HttpClient client = new HttpClient();
		HttpMethod method = new PostMethod(url);
		// 设置Http Post数据
		if (params != null) {
			HttpMethodParams p = new HttpMethodParams();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				p.setParameter(entry.getKey(), entry.getValue());
			}
			method.setParams(p);
		}
		try {
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
			}
		} catch (IOException e) {
		} finally {
			method.releaseConnection();
		}

		return response;
	}
	public static void doPostUrl(String url ,Map<String, String> params){
		 try {
			HttpClient client = new HttpClient();
			 PostMethod method = new PostMethod(url);
			 if (params != null) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					method.setParameter(entry.getKey(), entry.getValue());
				}
			}
			client.executeMethod(method);
			/*method.getResponseBodyAsStream();
			BufferedReader  reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"UTF-8"));
	        String str = null;  
	        while ((str = reader.readLine()) != null) {
	        	System.out.println("+++str"+str);
	        }*/
			method.abort();  
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}
