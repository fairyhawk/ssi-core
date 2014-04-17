package com.yizhilu.os.ssicore.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName com.supergenius.sns.util.StringUtils
 * @description 字符串工具类
 * @author : qinggang.liu 305050016@qq.com
 * @Create Date : 2013-12-13 下午2:23:56
 */
public class StringUtils {

    /**
     * 手机号中奖4位加星号
     * 
     * @author liuqinggang
     * @param mobile
     * @return String
     * 
     */
    public static String starMobile(String mobile) {
        if (mobile.length() == 11) {
            String starmobile = String.valueOf(mobile.charAt(0)) + String.valueOf(mobile.charAt(1)) + String.valueOf(mobile.charAt(2)) + "****" + String.valueOf(mobile.charAt(7))
                    + String.valueOf(mobile.charAt(8)) + String.valueOf(mobile.charAt(9)) + String.valueOf(mobile.charAt(10));
            return starmobile;
        }
        return mobile;
    }

    /**
     * 生成指定长度的随机字符串
     * 
     * @author liuqinggang
     * @param strLength
     * @return
     */
    public static String getRandomString(int strLength) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < strLength; i++) {
            int charInt;
            char c;
            if (random.nextBoolean()) {
                charInt = 48 + random.nextInt(10);
                c = (char) charInt;
                buffer.append(c);
                continue;
            }
            charInt = 65;
            if (random.nextBoolean())
                charInt = 65 + random.nextInt(26);
            else
                charInt = 97 + random.nextInt(26);
            if (charInt == 79)
                charInt = 111;
            c = (char) charInt;
            buffer.append(c);
        }

        return buffer.toString();
    }

    /**
     * MD5加密方法
     * 
     * @author liuqinggang
     * @param str
     *            String
     * @return String
     */
    public static String md5(String str) {
        if (str == null) {
            return null;
        }
        byte newByte1[] = str.getBytes();
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            byte newByte2[] = messagedigest.digest(newByte1);
            String cryptograph = "";
            for (int i = 0; i < newByte2.length; i++) {
                String temp = Integer.toHexString(newByte2[i] & 0x000000ff);
                if (temp.length() < 2)
                    temp = "0" + temp;
                cryptograph += temp;
            }
            return cryptograph;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证Email地址是否有效
     * 
     * @author liuqinggang
     * @param sEmail
     * @return boolean
     */
    public static boolean validEmail(String sEmail) {
        String pattern = "^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return sEmail.matches(pattern);
    }

    /**
     * 验证字符是否大长
     * 
     * @author liuqinggang
     * @param str
     * @return
     */
    public static boolean validMaxLen(String str, int length) {
        if (str == null || str.equals("")) {
            return false;
        }
        if (str.length() > length) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证字符是否长度太小
     * 
     * @author liuqinggang
     * @param str
     * @return boolean
     */
    public static boolean validMinLen(String str, int length) {
        if (str == null || str.equals("")) {
            return false;
        }
        if (str.length() < length) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证两个字符串是否相等且不能为空
     * 
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null || str1.equals("") || str2 == null || str2.equals("")) {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * 将字符型转为Int
     * 
     * @param str
     * @return
     */
    public static int toInt(String str) {
        int value = 0;
        if (str == null || str.equals("")) {
            return 0;
        }
        try {
            value = Integer.parseInt(str);
        } catch (Exception ex) {
            ex.printStackTrace();
            value = 0;
        }
        return value;
    }

    /**
     * 把数组转换成String
     * 
     * @param array
     * @return
     */
    public static String arrayToString(Object[] array, String split) {
        if (array == null) {
            return "";
        }
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                str.append(array[i].toString()).append(split);
            } else {
                str.append(array[i].toString());
            }
        }
        return str.toString();
    }

    /**
     * 得到WEB-INF的绝对路�?
     * 
     * @return
     */
    public static String getWebInfPath() {
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString();
        if (filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }
        if (filePath.toLowerCase().indexOf("classes") > -1) {
            filePath = filePath.replaceAll("/classes", "");
        }
        if (System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }
        if (!filePath.endsWith("/"))
            filePath += "/";
        return filePath;
    }

    /**
     * 得到根目录绝对路�?(不包含WEB-INF)
     * 
     * @return
     */
    public static String getRootPath() {
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString();
        if (filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }
        if (filePath.toLowerCase().indexOf("classes") > -1) {
            filePath = filePath.replaceAll("/classes", "");
        }
        if (filePath.toLowerCase().indexOf("web-inf") > -1) {
            filePath = filePath.substring(0, filePath.length() - 9);
        }
        if (System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }

        if (filePath.endsWith("/"))
            filePath = filePath.substring(0, filePath.length() - 1);

        return filePath;
    }

    /**
     * 格式化页�?
     * 
     * @param page
     * @return
     */
    public static int formatPage(String page) {
        int iPage = 1;
        if (page == null || page.equals("")) {
            return iPage;
        }
        try {
            iPage = Integer.parseInt(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            iPage = 1;
        }
        return iPage;
    }

    /**
     * 将计量单位字节转换为相应单位
     * 
     * @param size
     * @return
     */
    public static String getFileSize(String fileSize) {
        String temp = "";
        DecimalFormat df = new DecimalFormat("0.00");
        double dbFileSize = Double.parseDouble(fileSize);
        if (dbFileSize >= 1024) {
            if (dbFileSize >= 1048576) {
                if (dbFileSize >= 1073741824) {
                    temp = df.format(dbFileSize / 1024 / 1024 / 1024) + " GB";
                } else {
                    temp = df.format(dbFileSize / 1024 / 1024) + " MB";
                }
            } else {
                temp = df.format(dbFileSize / 1024) + " KB";
            }
        } else {
            temp = df.format(dbFileSize / 1024) + " KB";
        }
        return temp;
    }

    /**
     * 得到32位随机字
     * 
     * @return
     */
    public static String getEntry() {
        Random random = new Random(100);
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(new String("yyyyMMddHHmmssS"));
        return md5(formatter.format(now) + random.nextDouble());
    }

    /**
     * 将中文汉字转成UTF8编码
     * 
     * @param str
     * @return
     */
    public static String toUTF8(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        try {
            return new String(str.getBytes("ISO8859-1"), "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static String to(String str, String charset) {
        if (str == null || str.equals("")) {
            return "";
        }
        try {
            return new String(str.getBytes("ISO8859-1"), charset);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 得到10个数字的大写，0-9
     * 
     * @param num
     * @return
     */
    public static String getChineseNum(int num) {
        String[] chineseNum = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        return chineseNum[num];
    }

    public static String replaceEnter(String str) {
        if (str == null)
            return null;
        return str.replaceAll("\r", "").replaceAll("\n", "");
    }

    /**
     * 去除HTML 元素
     * 
     * @param element
     * @return
     */
    public static String getTxtWithoutHTMLElement(String element) {
        if (null == element) {
            return element;
        }
        Pattern pattern = Pattern.compile("<[^<|^>]*>");
        Matcher matcher = pattern.matcher(element);
        StringBuffer txt = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group();
            if (group.matches("<[\\s]*>")) {
                matcher.appendReplacement(txt, group);
            } else {
                matcher.appendReplacement(txt, "");
            }
        }
        matcher.appendTail(txt);
        String temp = txt.toString().replaceAll("[\r|\n]", "");
        // 多个连续空格替换为一个空格
        temp = temp.replaceAll("\\s+", " ");
        return temp;
    }

    /**
     * clear trim to String
     * 
     * @return
     */
    public static String toTrim(String strtrim) {
        if (null != strtrim && !strtrim.equals("")) {
            return strtrim.trim();
        }
        return "";
    }

    /**
     * UUID
     */
    public static String createUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 按传入字数截断并加结尾符号
     * 
     * @param sourceStr
     * @param length
     * @param charactor
     * @return
     */
    public static String cutffStr(String sourceStr, int length, String charactor) {
        String resultStr = sourceStr;
        if (sourceStr == null || "".equals(sourceStr)) {

            return "";
        }
        if (sourceStr.length() > length) {
            resultStr = sourceStr.substring(0, length);
            resultStr += charactor;

        }

        return resultStr;

    }

    public static boolean isNumber(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        boolean flag = false;
        try {
            Long.parseLong(str);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static String getLength(Object goodsName, int length) {
        if (goodsName == null) {
            return null;
        } else {
            String temp = String.valueOf(goodsName);
            if (temp.length() <= length) {
                return temp;
            } else {
                temp = temp.substring(0, length) + "...";
                return temp;
            }
        }
    }

    /**
     * 替换email中@符号前的一半字符为*号
     * 
     * @param email
     * @return
     */
    public static String handleEmail(String email) {
        if (email == null) {
            return "";
        } else {
            String[] aryEmail = email.split("@");
            if (aryEmail != null && aryEmail.length == 2) {
                if (aryEmail[0] != null) {
                    String firstPart = aryEmail[0].substring(aryEmail[0].length() / 2, aryEmail[0].length());
                    if (firstPart != null && !"".equals(firstPart)) {
                        char repeatChar[] = new char[firstPart.length()];
                        for (int i = 0; i < firstPart.length(); i++) {
                            repeatChar[i] = '*';
                        }
                        email = email.replaceFirst(firstPart + "@", new String(repeatChar) + "@");
                    }
                }
            }
        }
        return email;
    }

    private static final String regex_mobile = "^1\\d{10}$";

    /**
     * 
     * @author liuqinggang
     * @param tocheckNo
     * @return 手机号码校验
     * 
     */
    public static boolean isMobileNo(String tocheckNo) {
        return Pattern.matches(regex_mobile, tocheckNo);
    }

    private static final String regex_digital = "^[1-9]\\d{0,}";

    /**
     * 
     * @author liuqinggang
     * @param source
     * @param ingoreDigital
     *            忽略数字校验
     * @return
     * 
     */
    public static boolean neNullAndDigital(String source, boolean ingoreDigital, Integer length) {
        boolean isvalid = false;
        if (source != null && !"".equals(source.trim())) {
            isvalid = true;
        }
        if (!ingoreDigital && isvalid) {
            isvalid = Pattern.matches(regex_digital, source);
        }
        if (isvalid && length != null) {
            isvalid = source.trim().length() <= length;
        }
        return isvalid;
    }

    /**
     * 验证字符串是否为空
     * 
     * @author liuqinggang
     * @param str
     * @return true:不为空
     */
    public static boolean validNull(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @author liuqinggang
     * @param str
     * @return
     * 
     */
    public static boolean validNull(String... str) {
        for (int i = 0; i < str.length; i++) {
            if (str[i] == null || str[i].trim().equals("")) {
                return false;
            }
        }
        return true;
    }

    public static String getRandStr(int n) {
        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < n; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }
        return sRand;
    }

    /**
     * 序列号备用随机数
     * 
     * @return
     */
    public static String getSysTimeRandom() {
        return System.currentTimeMillis() + "" + new Random().nextInt(100);

    }

    /**
     * 商品订单序列号备用随机数--指定位数
     * 
     * @return
     */
    public static String getSysTimeRandom(int count) {

        String resultRandom = System.currentTimeMillis() + "" + new Random().nextInt(100);

        String resultRandomPro = "";
        int resultCount = resultRandom.length();
        if (count >= resultCount) {
            for (int i = 0; i < count - resultCount; i++) {

                resultRandomPro += "0";

            }
            return resultRandomPro + resultRandom;
        } else {

            return resultRandom.substring(resultCount - 1 - count, resultCount - 1);
        }

    }

    /**
     * 参数转换
     * 
     * @param source
     * @return
     */
    public static String[] parseParam(String source) {

        if (source == null || "".equals(source)) {
            throw new IllegalArgumentException("source is null");
        }
        String[] resultAry = source.split("&");
        return resultAry;
    }

    /**
     * 参数转换 renli.yu
     * 
     * @param source
     * @return
     */
    public static String[] parseParamArray(String source) {

        if (source == null || "".equals(source)) {
            throw new IllegalArgumentException("source is null");
        }
        String[] resultAry = source.split("\\|");
        return resultAry;
    }

    public static String convStrToHessian(String item, int count) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(URLEncoder.encode(item, "utf-8")).append("|");
        }
        if (sb != null && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static String convToHessian(String item, int count) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(URLEncoder.encode(item, "utf-8")).append("|");
        }

        return sb.toString();
    }

    public static String convAryToStr(String sourceStr, String sourceChar, String resultChar, boolean isTrans) {
        if (isTrans) {
            sourceChar = "\\" + sourceChar;
        }
        String[] sourceStrAry = sourceStr.split(sourceChar);

        int count = sourceStrAry.length;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            try {
                Long.parseLong(sourceStrAry[i]); // 如果不为数字，则抛异常

            } catch (Exception e) {
                e.printStackTrace();
            }

            sb.append(sourceStrAry[i]).append(resultChar);
        }
        if (sb != null && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static String convListToString(List<Map<String, Object>> list, String flag) {
        StringBuilder sb = new StringBuilder();
        int count = list.size();
        for (int i = 0; i < count; i++) {
            sb.append(list.get(i).get(flag));
            sb.append(",");
        }
        if (sb != null && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static String queryParam(String param, String queryParam) {
        if (validNull(param)) {
            return queryParam + "=" + param + "&";
        } else {
            return "";
        }
    }

    /**
     * Description : 讲字符串类型转换为java.sql.Timestamp
     * 
     * @param time
     * @return
     */
    public static Timestamp convertToTimestamp(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date myDate = null;
        Timestamp myTimestamp = null;
        try {
            myDate = sdf.parse(time);
            myTimestamp = new Timestamp(myDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myTimestamp;
    }

    /**
     * 随机取模
     * 
     * @return
     */
    public static String randomBase() {

        String result = String.valueOf(System.currentTimeMillis() % 10);

        return result;

    }

    /**
     * 根据用户账户ID取模
     * 
     * @param id
     * @return
     */

    public static Long getDeliveryIdBase(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }

        return id % 10;

    }

    /**
     * @param strIp1
     *            获取的分销商IP
     * @param StrIp2数据库白名单IP
     * @return
     */
    public static boolean checkIp(String strIp1, String StrIp2) {
        boolean boo = false;
        if ("".equals(StrIp2)) {
            return true;
        }
        boolean isOrderIpRule = strIp1
                .matches("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
        if (!isOrderIpRule) {
            return boo;
        }
        String ipArray[] = StrIp2.split(",");
        for (int i = 0; i < ipArray.length; i++) {
            String ipArr = ipArray[i];
            String ipay = "";
            if (ipArr.contains("*")) { // 如格式为192.168.1.*判断
                ipay = ipArr.substring(0, ipArr.lastIndexOf("."));
                boo = strIp1.substring(0, strIp1.lastIndexOf(".")).equals(ipay);
                if (boo) {
                    return boo;
                }
            } else if (ipArr.contains("-")) { // 如格式为192.168.1.1-155判断
                ipay = ipArr.substring(ipArr.lastIndexOf(".") + 1);
                String ipayArray[] = ipay.split("-");
                String ips = strIp1.substring(strIp1.lastIndexOf(".") + 1);
                if (Integer.parseInt(ipayArray[0]) <= Integer.parseInt(ips) && Integer.parseInt(ips) <= Integer.parseInt(ipayArray[1])) {
                    boo = true;
                    return boo;
                }
            } else { // 如格式为192.168.1.1判断
                boo = strIp1.equals(ipArr);
                if (boo) {
                    return boo;
                }
            }
        }
        return boo;
    }

    public static boolean isEmpty(String str) {
        if (null == str || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 组装字符串 为字符串添加前缀后缀 add by wangweijie
     * 
     * @param str
     * @param prefix
     *            前缀
     * @param suffix
     *            后缀
     * @return
     */
    public static String packagingString(String str, String prefix, String suffix) {
        if (StringUtils.isEmpty(str))
            str = "";
        if (StringUtils.isEmpty(prefix))
            prefix = "";
        if (StringUtils.isEmpty(suffix))
            suffix = "";
        return prefix + str + suffix;
    }

    /**
     * 字符串对以分号分隔的字符串转化为数组，并对数组按有小到大的排序 add by wangweijie 2012-11-16
     * 
     * @return
     */
    public static String[] sortArray(String[] array) {
        // 冒泡排序--有小到大顺序
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i].compareTo(array[j]) < 0) {
                    String temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 字符串折半查找(数组必须是由小到大排列的有序数组 -1代表未查到，否则返回查找的下标 add by wangweijie 2012-11-16
     */
    public static int bisearch(final String[] sourceArray, final String seek) {
        if (null == sourceArray || sourceArray.length == 0 || null == seek) {
            return -1;
        }

        int bottom = 0;
        int top = sourceArray.length - 1;
        int mid;

        while (bottom <= top) {
            mid = (bottom + top) / 2;
            int result = sourceArray[mid].compareTo(seek);
            if (0 == result) {
                return mid;
            } else if (result > 0) {
                top = mid - 1;
            } else {
                bottom = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 判断字符串是否为空
     * 
     * @param str
     * @return true(空); false(非空)
     */
    public static boolean isBlank(String str) {
        return null == str || str.trim().length() == 0;
    }

    public static boolean isBlank(Object obj) {
        return ObjectUtils.isNull(obj) ? true : isBlank(obj.toString());
    }


    public static int strToInt(String str) {

        int result = 0;

        try {

            result = Integer.parseInt(str);

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        return result;

    }

    public static String intToStr(int i) {

        return Integer.toString(i);

    }

    public static boolean inNumber(String str, String parent) {

        boolean is = true;

        int i;

        char c;

        if (str.length() == 0) {

            is = false;

        }

        else {

            for (i = 0; i < str.length(); i++) {

                c = str.charAt(i);

                int j = parent.indexOf(c);

                if (j < 0) {

                    is = false;

                    break;

                }

            }

        }

        return is;

    }

    public static boolean endsWith444(String str) {

        return (str.endsWith("444"));

    }

    public static boolean strSame(String str) {

        boolean is = true;

        char c;

        c = str.charAt(0);

        for (int i = 0; i < str.length(); i++) {

            if (c != str.charAt(i)) {

                is = false;

                break;

            }

        }

        return is;

    }

    public static boolean startSame(String str, int bit) {

        String substr = str.substring(0, bit);

        return (strSame(substr));

    }

    public static boolean endSame(String str, int bit) {

        int begin = str.length() - bit;

        int end = str.length();

        String substr = str.substring(begin, end);

        return (strSame(substr));

    }

    public static boolean sameSub6(String str) {

        return ((startSame(str, 6)) || (endSame(str, 6)));

    }

    public static boolean sameSub(String str, int bit) {

        boolean is = false;

        int i = 0;

        while ((i + bit) <= str.length()) {

            String substr = str.substring(i, bit + i);
            if (strSame(substr)) {

                is = true;

                break;

            }

            i++;

        }

        return is;

    }

    public static boolean sameSub5(String str) {

        return (sameSub(str, 5));

    }

    public static boolean sameSub4(String str) {

        return (sameSub(str, 4));

    }

    public static boolean end3Same(String str) {

        return (endSame(str, 3));

    }

    public static boolean start3Same(String str) {

        return (startSame(str, 3));

    }

    public static boolean continueNum(String str) {

        boolean is = true;

        int begin = 0;

        int end = begin + 1;

        int nextvalue = 0;

        int imme = 0;

        String sub = str.substring(begin, end);

        int value = Integer.parseInt(sub);

        while (end < str.length()) {

            begin++;

            end++;

            String sub1 = str.substring(begin, end);

            nextvalue = Integer.parseInt(sub1);

            imme = nextvalue - value;

            if (imme != 1) {


                is = false;

                break;

            }

            value = nextvalue;

        }

        return is;

    }

    public static String subEnds(String s, int bit) {

        return (s.substring(s.length() - bit, s.length()));

    }

    public static String subStarts(String s, int bit) {

        return (s.substring(0, bit));

    }

    public static boolean serEnd3(String str) {
        String sub = subEnds(str, 3);
        return (continueNum(sub));
    }

    public static boolean serStart3(String str) {

        String sub = subStarts(str, 3);
        return (continueNum(sub));

    }

    public static boolean start588(String str) {

        return (subStarts(str, 3).equals("588"));

    }

    public static boolean start555(String str) {

        return (subStarts(str, 3).equals("555"));

    }

    public static boolean isSequence(List ls) {

        boolean is = true;

        if (ls != null) {

            int count = ls.size();

            for (int i = 0; i < count - 1; i++) {

                String current = (String) ls.get(i);

                int cur = strToInt(current);

                int j = i + 1;

                String next = (String) ls.get(j);

                int ne = strToInt(next);

                int interval = ne - cur;

                if (interval != 1) {

                    is = false;
                    break;
                }
                else {
                    is = true;

                }

            }

        }

        return is;

    }

    // by zhaojing 20041016 start

    /**
     * 
     * nvl
     * 
     * @param sIn
     *            String
     * 
     * @return String
     * 
     */

    public static String nvl(String sIn) {

        if (sIn == null) {

            return "";

        }

        else {

            return sIn;

        }

    }

    /**
     * 
     * isNullString
     * 
     * @param str
     *            String
     * 
     * @return boolean
     * 
     */

    public static boolean isNullString(Object str) {

        return (str == null || "".equals(str.toString().trim()));

    }

    public static String transNullString(Object str) {

        if (isNullString(str))
            return "";
        else
            return String.valueOf(str);

    }

    /**
     * 
     * replaceStr
     * 
     * @param src
     *            String
     * 
     * @param sFnd
     *            String
     * 
     * @param sRep
     *            String
     * 
     * @return String
     * 
     */

    public static String replaceStr(String src, String sFnd, String sRep) {

        String sTemp = "";

        int endIndex = 0;

        int beginIndex = 0;

        do {

            endIndex = src.indexOf(sFnd, beginIndex);

            if (endIndex >= 0) { // mean found it.

                sTemp = sTemp + src.substring(beginIndex, endIndex) + sRep;

                beginIndex = endIndex + sFnd.length();

            }

            else if (beginIndex >= 0) {

                sTemp = sTemp + src.substring(beginIndex);

                break;

            }

        }

        while (endIndex >= 0);

        return sTemp;

    }

    /**
     * 
     * toHTMLOutStr
     * 
     * @param sIn
     *            String
     * 
     * @return String
     * 
     */

    public static String toHTMLOutStr(String sIn) {

        if (isNullString(sIn)) {

            return "&nbsp;";

        }

        char[] content = new char[sIn.length()];

        sIn.getChars(0, sIn.length(), content, 0);

        final int size = 50;

        StringBuffer result = new StringBuffer(content.length + size);

        for (int i = 0; i < content.length; i++) {

            switch (content[i]) {

            case '<':

                result.append("&lt;");

                break;

            case '>':

                result.append("&gt;");

                break;

            case '&':

                result.append("&amp;");

                break;

            case '"':

                result.append("&quot;");

                break;

            default:

                result.append(content[i]);

            }

        }

        String retStr = replaceStr(result.toString(), "\n", "<br>");

        return retStr;

    }

    // by zhaojing 20041016 end

    public static String convertString2FixedLength(String str, int strLen) {

        if (str.length() == strLen) {

            return str;

        }

        if (str.length() > strLen) {

            return str.substring(0, strLen);

        }

        StringBuffer sb = new StringBuffer(str);

        while (sb.length() < strLen) {

            sb.append(" ");

        }

        return sb.toString();

    }

    public static String convertInt2FixedLength(int intValue, int strLen) {

        String str = String.valueOf(intValue);

        return convertString2FixedLength(str, strLen);

    }

    /**
     * 
     * 鍦ㄧ幇鏈夊瓧绗︿覆鐨勫乏杈硅ˉ瓒虫寚瀹氱殑瀛楃
     * 
     * @param strSrc
     *            String 婧愬瓧绗︿覆
     * 
     * @param length
     *            int 琛ヨ冻鍚庣殑闀垮害
     * 
     * @param pad
     *            char 濉厖鐨勫瓧绗?
     * 
     * @return String 琛ヨ冻浠ュ悗鐨勫瓧绗︿覆
     * 
     */

    public static String lpad(String strSrc, int length, char pad) {

        if (strSrc.length() >= length) {

            return strSrc;

        }

        int diff = length - strSrc.length();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < diff; i++) {

            sb.append(pad);

        }

        return sb.toString() + strSrc;

    }

    /**
     * 
     * 鍦ㄧ幇鏈夊瓧绗︿覆鐨勫彸杈硅ˉ瓒虫寚瀹氱殑瀛楃
     * 
     * @param strSrc
     *            String 婧愬瓧绗︿覆
     * 
     * @param length
     *            int 琛ヨ冻鍚庣殑闀垮害
     * 
     * @param pad
     *            char 濉厖鐨勫瓧绗?
     * 
     * @return String 琛ヨ冻浠ュ悗鐨勫瓧绗︿覆
     * 
     */

    public static String rpad(String strSrc, int length, char pad) {

        if (strSrc.length() >= length) {

            return strSrc;

        }

        int diff = length - strSrc.length();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < diff; i++) {

            sb.append(pad);

        }

        return strSrc + sb.toString();

    }

    /**
     * 
     * 鍒嗗壊瀛楃涓?
     * 
     * @param s
     *            婧愬瓧绗︿覆
     * 
     * @param s1
     *            鍒嗗壊瀛椾覆鏍囪瘑瀛楃
     * 
     * @return 鍒嗗壊鍚庣殑瀛楃鏁扮粍
     * 
     */

    public static String[] seperates(String s, String s1) {

        String s2 = "";

        int i = 0;

        int j = 0;

        int k = 0;

        i = s.length();

        for (int l = 0; l < i; l++)

            if (s.substring(l, l + 1).equals(s1))

                k++;

        String as[] = new String[k + 1];

        try {

            for (int i1 = 0; i1 < i; i1++)

                if (s.substring(i1, i1 + 1).equals(s1)) {

                    as[j] = s2;

                    j++;

                    s2 = "";

                }

                else {

                    s2 = s2 + s.substring(i1, i1 + 1);

                }

            as[j] = s2;

        }

        catch (Exception exception) {

        }

        return as;

    }

    /**
     * 鏍规嵁鍘熶环鍜屾姌鎵ｈ绠楀埌鎶樻墸鍚庣殑浠锋牸銆備富瑕佹槸涓轰簡澶勭悊绮惧害闂
     * 
     * @param price
     * @param off
     *            鎶樻墸銆備緥濡?8鎶橈紝鍒欎紶鍏ョ殑鍙傛暟涓?0.8锛?.571鎶樺垯浼犲叆鐨勫弬鏁颁负0.3571
     * @return
     */
    public static double doubleMutil(double price, double off) {
        BigDecimal aa = new BigDecimal(price * off * 100);
        BigDecimal a100 = new BigDecimal(100);
        BigDecimal bb = aa.divide(a100, 4, BigDecimal.ROUND_HALF_UP);
        return bb.doubleValue();
    }

    /**
     * 鐢熸垚鎸囧畾闀垮害鐨勯殢鏈哄瘑鐮?
     * 
     * @param pwd_len
     * @return
     */
    public static String genRandomNum(int pwd_len) {
        // 35鏄洜涓烘暟缁勬槸浠?寮?鐨勶紝26涓瓧姣?10涓暟瀛?
        final int maxNum = 36;
        int i; // 鐢熸垚鐨勯殢鏈烘暟
        int count = 0; // 鐢熸垚鐨勫瘑鐮佺殑闀垮害
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0',
                '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            // 鐢熸垚闅忔満鏁帮紝鍙栫粷瀵瑰?锛岄槻姝㈢敓鎴愯礋鏁帮紝
            i = Math.abs(r.nextInt(maxNum)); // 鐢熸垚鐨勬暟鏈?ぇ涓?6-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    /**
     * 灏忓啓杞ぇ鍐?
     * 
     * @param upperStr
     * @return
     * @throws Exception
     */
    public static String toUpperStr(String lowStr) throws Exception {
        String upperStr = new String();
        try {
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < lowStr.length(); i++) {
                if ((int) lowStr.charAt(i) >= 97 && (int) lowStr.charAt(i) <= 122) {
                    sb.append((char) ((int) lowStr.charAt(i) - 32));
                } else
                    sb.append(lowStr.charAt(i));
            }
            upperStr = sb.toString();
            return upperStr;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new Exception("瀛楃涓茶浆澶у啓閿欒");
        }
    }

    /**
     * 澶у啓杞皬鍐?
     * 
     * @param upperStr
     * @return
     * @throws Exception
     */
    public static String toLowerStr(String upperStr) throws Exception {
        try {
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < upperStr.length(); i++) {
                if ((int) upperStr.charAt(i) >= 65 && (int) upperStr.charAt(i) <= 90) {
                    sb.append((char) ((int) upperStr.charAt(i) + 32));
                } else
                    sb.append(upperStr.charAt(i));
            }
            return sb.toString();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new Exception("瀛楃涓茶浆灏忓啓閿欒");
        }
    }

    /**
     * 灏嗗瓧绗︿覆鎴煭锛屽彇鍓峮涓瓧绗︼紝鑻辨枃绠楀崐涓瓧绗︺?
     * 
     * @param orignalString
     *            鍘熷瓧绗︿覆
     * @param length
     *            闀垮害
     * @param chopedString
     *            瓒呰繃閮ㄥ垎鐨勮〃绀哄瓧绗︿覆
     * @return 鎴彇鐨勫瓧绗︿覆
     */
    public static String chop(String orignalString, int length, String chopedString) {
        if (orignalString == null || orignalString.length() == 0) {
            return orignalString;
        }
        orignalString = orignalString.replaceAll("   ", "   ");
        if (orignalString.length() < length) {
            return orignalString;
        }
        StringBuffer buffer = new StringBuffer(length);
        length = length * 2;
        int count = 0;
        int stringLength = orignalString.length();
        int i = 0;
        for (; count < length && i < stringLength; i++) {
            char c = orignalString.charAt(i);
            if (c < '\u00ff') {
                count++;
            } else {
                count += 2;
            }
            buffer.append(c);
        }
        if (i < stringLength) {
            buffer.append(chopedString);
        }
        return buffer.toString();
    }

    public static boolean StringValidate(String str) {
        if (str != null && !str.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符转Long
     */
    public static Long StringToLong(List<Integer> bitNumber) {
        Long tl = 0l;
        for (int ti : bitNumber) {
            tl = tl ^ (1l << ti);
        }
        return tl;
    }

    /**
     * Long转字符
     * 
     * @param value
     * @return
     */
    public static Map<Integer, Integer> LongToString(Long value) {
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        char[] c = Long.toBinaryString(value).toCharArray();
        int index = c.length - 1;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '1') {
                int x = index - i;
                m.put(x, x);
            }
        }
        return m;
    }

    /**
     * 根据正则查找匹配内容
     * 
     * @param oldStr
     * @param regex
     * @param i
     * @return
     */
    public static String returnRegexStr(String oldStr, String regex, int i) {
        if (oldStr == null) {
            return "";
        }
        String str = "";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(oldStr);
        if (m.find() && i <= m.groupCount()) {
            str = m.group(i);
        }
        return str;
    }

    /**
     * 标签处理功能，获取参数
     * 
     * @param parm
     * @return
     */
    public static Map<String, Object> getParm(String parm) {
        Map<String, Object> parmMap = new HashMap<String, Object>();
        if (stringIsNull(parm)) {
            return parmMap;
        }
        String[] parmArray = parm.split(",");

        for (int i = 0; i < parmArray.length; i++) {
            String[] s = parmArray[i].split("::");
            parmMap.put(s[0], s[1]);
        }
        return parmMap;
    }

    /**
     * 检查字符串是否为空
     * 
     * @param str
     * @return
     */
    public static boolean stringIsNull(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 过滤HTML标签
     * 
     * @param args
     */
    public static String filterHTML(String str) {
        if (str == null) {
            return str;
        }
        String regexForHTML = "<([^>]*)>|&(.+);";
        Pattern pattern = Pattern.compile(regexForHTML);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = matcher.find();
        while (result) {
            matcher.appendReplacement(sb, "");
            result = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}