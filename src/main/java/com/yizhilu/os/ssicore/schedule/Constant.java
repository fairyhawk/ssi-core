package com.yizhilu.os.ssicore.schedule;

import java.util.HashMap;
import java.util.Map;

import com.yizhilu.os.ssicore.service.ConfigService;
import com.yizhilu.os.ssicore.util.PropertiesReader;

/**
 * 公用常量
 * 
 * @author Basil
 *
 */
public class Constant {
	public static final String TRIGGERNAME = "triggerName";
	public static final String TRIGGERGROUP = "triggerGroup";
	public static final String STARTTIME = "startTime";
	public static final String ENDTIME = "endTime";
	public static final String REPEATCOUNT = "repeatCount";
	public static final String REPEATINTERVEL = "repeatInterval";
	public static  String r = "";
	public static  String l = "";
	public static  String w = "";
	public static final Map<String, String> status = new HashMap<String, String>();
	private static ConfigService configurator;
	/**
	 * @return the configurator
	 */
	public ConfigService getConfigurator() {
		return configurator;
	}
	/**
	 * @param configurator the configurator to set
	 */
	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}
	static {
		status.put("ACQUIRED", "运行中");
		status.put("PAUSED", "暂停中");
		status.put("WAITING", "等待中");
		 r = PropertiesReader.getValue("memcache", "r");
		 l = PropertiesReader.getValue("memcache", "l");
		 w = PropertiesReader.getValue("memcache", "w");
		
	}

	
	
}
