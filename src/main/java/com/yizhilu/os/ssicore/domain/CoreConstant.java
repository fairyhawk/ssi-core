/**
 * ClassName  CoreConstant
 * package    com.supergenius.sns.common.util
 * description 
 * Create User: Administrator
 * Create Date: 2013-12-9
 */
package com.yizhilu.os.ssicore.domain;

import com.yizhilu.os.ssicore.util.PropertyUtil;

/**
 * 
 * @author Administrator
 */

public class CoreConstant {
    // 属性文件配置名称
    public static final String PROPERTY_FILE_NAME = "project";
    // 验证邮件地址
    public static final String EMAIL_VALIDATE_URL = "emailValidateUrl";

    // 验证码
    public static String RAND_CODE = "user_rand_code";
    // 后台用户存seesion用
    public static String SYS_USER_SESSION_NAME = "sys_user_ukey";
    // 读取配置文件类
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance("project");

    // 项目路径
    public static String contextPath = propertyUtil.getProperty("contextPath");
    // 图片、CSS、js 服务器地址
    public static String imagesPath = propertyUtil.getProperty("imagesPath");

    // 上传服务用服务器地址，访问时用imagesPath，数据库中不存储域名
    public static String uploadServerUrl = propertyUtil.getProperty("uploadServerUrl");
    /*
     * 上传图片后访问的地址，使用imagesPath或者uploadServerUrl防止项目部署到多台机器，单独定义此变量
     */
    public static String uploadStaticUrl = propertyUtil.getProperty("uploadStaticUrl");
    // 项目名称
    public static String projectName = propertyUtil.getProperty("projectName");

}
