package com.yizhilu.os.ssicore.vo;
/**
 * 存mem缓存时key常量定义，避免不同人员之间用到重复的key
 * @author liuqinggang
 *
 */
public class MemConstans {
	//时间小时
    /**
     * 1个小时
     */
	public static int HOUR_1=60*60;
	public static int HOUR_3=3*60*60;
	public static int HOUR_6=6*60*60;
	public static int HOUR_12=12*60*60;
	//时间天
	public static int DAY_1=24*60*60;
	public static int DAY_3=24*60*60;
	public static int DAY_7=24*60*60;
	public static int DAY_30=24*60*60;
	//存储用户登录信息CUSID_加用户的cusId
	public static String CUS_LOGIN_INFO="CUSID_";
	//存储用户观看视频记录
	public static String CUS_VIDEO_INFO="VIDEO_";
	
	//存储用户观看视频记录 --专业
	public static String CUS_VIDEO_S_INFO="VIDEO_S_";
	
	//存储课程知识点观看次数
	public static final String COURSE_COUNT = "COU_STA_COUNT";
	
	//存用户购买过的商品。支付成功时要删掉
	public static final String CUS_BUY_SELLWAY_LIST="CUS_BUY_SELLWAYLIST_";
	
	//课程的信息。每个商品下
	public static final String COU_COULIST_BY_SELLID="COU_COULIST_BY_SELLID_";
	
	//单个课程的节点信息
	public static final String COU_KPOINT_LIST_BY_COUID="COU_KPOINT_LIST_BY_COUID_";
	

	//个人中心推荐课程 最新课程
	public static final String UCENTER_TUIJIAN_NEW_SELLWAY="UCENTER_TUIJIAN_NEW_SELLWAY";
	
	//个人中心推荐课程 编者推荐
	public static final String UCENTER_TUIJIAN_BZ_SELLWAY="UCENTER_TUIJIAN_BZ_SELLWAY";
	//所有专业
	public static final String ALL_SUBJECT_LIST="ALL_SUBJECT_LIST";
	
	//所有会员类型
	public static final String ALL_MEMBER_TYPES="ALL_MEMBER_TYPES";
	
	//会员信息
    public static final String MEMBER_BY_CUSID="MEMBER_BY_CUSID_";
    
    
}
