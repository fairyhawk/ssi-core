package com.yizhilu.os.ssicore.vo;

import java.io.Serializable;

public class TrueUInfo implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private String merchantID = "0010000007";// 合作商户ID
    /*
     * 可以为xml，json两种格式（小写），默认为xml格式，输出字符统一为utf-8
     */
    private String format = "json";
    private String version = "1.0";// 网关版本
    private String inputCharset = "1";// 参数编码 1为utf-8，2为gbk; 默认值为1
    private String signType = "1";// 签名类型 固定值：1，MD5加密签名
    private String method = "gateway.pay.direct";
    private String pageUrl;// 跳转页面
    private String noticeUrl;// 通知地址
    private String payerName;// 收货人姓名
    private String payerAddress;// 收货人地址
    private String payerZip;// 收货人邮编
    private String payerContact;// 收货人电话
    private String payerEmail;// 收货人邮件
    private String payerUid;// 联合登陆编号
    private String userIP;
    private String orderId;// 商户订单号
    private String orderAmount;// 商户订单金额
    private String orderTime;// 商户订单时间
    private String productName = "268学课程";// 商品名称
    private String productNum = "1";// 商品数量
    private String productDesc = "268学课程";// 商品描述
    private String unionUid;// 联名账户编号
    private String unionDiscount;// 联名优惠折扣
    private String ext1;// 扩展字段1
    private String ext2;// 扩展字段2
    private String payType = "10";// 支付方式 全额付款：10（固定值）；
    private String sign;// 数字签名
    /*
     * 真友返回值
     */
    // private String merchantID;
    // private String version;
    // private String signType;
    // private String orderId;
    // private String orderAmount;
    // private String orderTime;
    private String dealId;// 18位数字串，该交易在TrueU系统中对应的交易号
    private String dealTime;// 14位数字串
    private String loanAmount;// 用户贷款金额，单位分
    private String fee;// 收取商户的手续费，单位为分
    // private String ext1;
    // private String ext2;;
    private String payResult;// 10 支付成功；其他编号为支付失败错误代码
    private String errMsg;
    private String key = "DWHYQX24ZBBL67X7";
    // private String sign;
    private String compareSign;//比较返回校验加密串

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerAddress() {
        return payerAddress;
    }

    public void setPayerAddress(String payerAddress) {
        this.payerAddress = payerAddress;
    }

    public String getPayerZip() {
        return payerZip;
    }

    public void setPayerZip(String payerZip) {
        this.payerZip = payerZip;
    }

    public String getPayerContact() {
        return payerContact;
    }

    public void setPayerContact(String payerContact) {
        this.payerContact = payerContact;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayerUid() {
        return payerUid;
    }

    public void setPayerUid(String payerUid) {
        this.payerUid = payerUid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getUnionUid() {
        return unionUid;
    }

    public void setUnionUid(String unionUid) {
        this.unionUid = unionUid;
    }

    public String getUnionDiscount() {
        return unionDiscount;
    }

    public void setUnionDiscount(String unionDiscount) {
        this.unionDiscount = unionDiscount;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCompareSign() {
        return compareSign;
    }

    public void setCompareSign(String compareSign) {
        this.compareSign = compareSign;
    }

}
