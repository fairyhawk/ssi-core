package com.yizhilu.os.ssicore.vo;
/**
 * Copyright (c)  Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:银联在线Vo
 *
 * @author		Yangning
 * @date		2011-12-23
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public class UnionPayInfo {
		//私匙
		private final String securityKey="FYEHFUD45FNRDFYHEHFHFDFYHEVS";
		//签名方式
		private  String signMethod="MD5";
		//币种156代表人民币
		private String orderCurrency = "156";
		//版本号
		private  String version="1.0.0";
		//编码方式
		private  String charset="UTF-8";
		//商户编号
		private  String merId = "898110148991288";
		//商户代码
		private  String merCode;
		//商户名称
		private String merAbbr = "北京易知路科技有限公司";
		//交易类型
		private  String transType = "01";
		//异步回调地址
		private String backEndUrl;
		//同步回调地址
		private String frontEndUrl;
		//交易开始日期时间
		private String orderTime;
		//交易金额
		private String orderAmount;
		//持卡人ip
		private String customerIp;
		//签名
		private String signature;
		//订单号码
		private String orderNumber;
		//收单机构代码(未使用)
		private String acqCode;
		//优惠信息(未使用)
		private String commodityDiscount;
		//商品名称单个(未使用)
		private String commodityName;
		//商品URL(未使用)
		private String commodityUrl;
		//商品单价(未使用)
		private String commodityUnitPrice;
		//商品数量(未使用)
		private String commodityQuantity;
		//运输费用(未使用)
		private String transferFee;
		//默认银行编码(未使用)
		private String defaultBankNumber;
		//默认支付方式(未使用)
		private String defaultPayType;
		//交易超时时间(未使用)
		private String transTimeout;
		//原始交易流水号(未使用)
		private String origQid;
		//商户保留域(未使用)
		private String merReserved;
		//持卡人姓名(未使用)
		private String customerName;
		
		//银联返回兑换日期
		private String exchangeDate;
		//银联返回清算汇率
		private String exchangeRate;
		//银联返回交易流水号
		private String qid;
		//银联返回响应码
		private String respCode;
		//银联返回响应信息
		private String respMsg;
		//银联返回交易完成时间
		private String respTime;
		//银联返回清算金额
		private String settleAmount;
		//银联返回清算币种
		private String settleCurrency;
		//银联返回清算日期
		private String settleDate;
		//银联返回系统跟踪号
		private String traceNumber;
		//银联返回系统跟踪时间
		private String traceTime;
		//银联返回生成签名
		private String returnSign;
		//银联返回系统保留域
		private String cupReserved;
		
		public String getCupReserved() {
			return cupReserved;
		}
		public void setCupReserved(String cupReserved) {
			this.cupReserved = cupReserved;
		}
		public String getOrderCurrency() {
			return orderCurrency;
		}
		public void setOrderCurrency(String orderCurrency) {
			this.orderCurrency = orderCurrency;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public String getCharset() {
			return charset;
		}
		public void setCharset(String charset) {
			this.charset = charset;
		}
		public String getMerId() {
			return merId;
		}
		public void setMerId(String merId) {
			this.merId = merId;
		}
		public String getMerCode() {
			return merCode;
		}
		public void setMerCode(String merCode) {
			this.merCode = merCode;
		}
		public String getMerAbbr() {
			return merAbbr;
		}
		public void setMerAbbr(String merAbbr) {
			this.merAbbr = merAbbr;
		}
		public String getTransType() {
			return transType;
		}
		public void setTransType(String transType) {
			this.transType = transType;
		}
		public String getBackEndUrl() {
			return backEndUrl;
		}
		public void setBackEndUrl(String backEndUrl) {
			this.backEndUrl = backEndUrl;
		}
		public String getFrontEndUrl() {
			return frontEndUrl;
		}
		public void setFrontEndUrl(String frontEndUrl) {
			this.frontEndUrl = frontEndUrl;
		}
		public String getOrderTime() {
			return orderTime;
		}
		public void setOrderTime(String orderTime) {
			this.orderTime = orderTime;
		}
		public String getOrderAmount() {
			return orderAmount;
		}
		public void setOrderAmount(String orderAmount) {
			this.orderAmount = orderAmount;
		}
		public String getCustomerIp() {
			return customerIp;
		}
		public void setCustomerIp(String customerIp) {
			this.customerIp = customerIp;
		}
		public String getSignature() {
			return signature;
		}
		public void setSignature(String signature) {
			this.signature = signature;
		}
		public String getOrderNumber() {
			return orderNumber;
		}
		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}
		public String getExchangeDate() {
			return exchangeDate;
		}
		public void setExchangeDate(String exchangeDate) {
			this.exchangeDate = exchangeDate;
		}
		public String getExchangeRate() {
			return exchangeRate;
		}
		public void setExchangeRate(String exchangeRate) {
			this.exchangeRate = exchangeRate;
		}
		public String getQid() {
			return qid;
		}
		public void setQid(String qid) {
			this.qid = qid;
		}
		public String getRespCode() {
			return respCode;
		}
		public void setRespCode(String respCode) {
			this.respCode = respCode;
		}
		public String getRespMsg() {
			return respMsg;
		}
		public void setRespMsg(String respMsg) {
			this.respMsg = respMsg;
		}
		public String getRespTime() {
			return respTime;
		}
		public void setRespTime(String respTime) {
			this.respTime = respTime;
		}
		public String getSettleAmount() {
			return settleAmount;
		}
		public void setSettleAmount(String settleAmount) {
			this.settleAmount = settleAmount;
		}
		public String getSettleCurrency() {
			return settleCurrency;
		}
		public void setSettleCurrency(String settleCurrency) {
			this.settleCurrency = settleCurrency;
		}
		public String getSettleDate() {
			return settleDate;
		}
		public void setSettleDate(String settleDate) {
			this.settleDate = settleDate;
		}
		public String getTraceNumber() {
			return traceNumber;
		}
		public void setTraceNumber(String traceNumber) {
			this.traceNumber = traceNumber;
		}
		public String getTraceTime() {
			return traceTime;
		}
		public void setTraceTime(String traceTime) {
			this.traceTime = traceTime;
		}
		public String getReturnSign() {
			return returnSign;
		}
		public void setReturnSign(String returnSign) {
			this.returnSign = returnSign;
		}
		public String getSecurityKey() {
			return securityKey;
		}
		public String getSignMethod() {
			return signMethod;
		}
		public String getAcqCode() {
			return acqCode;
		}
		public void setAcqCode(String acqCode) {
			this.acqCode = acqCode;
		}
		public String getCommodityDiscount() {
			return commodityDiscount;
		}
		public void setCommodityDiscount(String commodityDiscount) {
			this.commodityDiscount = commodityDiscount;
		}
		public String getCommodityName() {
			return commodityName;
		}
		public void setCommodityName(String commodityName) {
			this.commodityName = commodityName;
		}
		public String getCommodityUrl() {
			return commodityUrl;
		}
		public void setCommodityUrl(String commodityUrl) {
			this.commodityUrl = commodityUrl;
		}
		public String getCommodityUnitPrice() {
			return commodityUnitPrice;
		}
		public void setCommodityUnitPrice(String commodityUnitPrice) {
			this.commodityUnitPrice = commodityUnitPrice;
		}
		public String getCommodityQuantity() {
			return commodityQuantity;
		}
		public void setCommodityQuantity(String commodityQuantity) {
			this.commodityQuantity = commodityQuantity;
		}
		public String getTransferFee() {
			return transferFee;
		}
		public void setTransferFee(String transferFee) {
			this.transferFee = transferFee;
		}
		public String getDefaultBankNumber() {
			return defaultBankNumber;
		}
		public void setDefaultBankNumber(String defaultBankNumber) {
			this.defaultBankNumber = defaultBankNumber;
		}
		public String getDefaultPayType() {
			return defaultPayType;
		}
		public void setDefaultPayType(String defaultPayType) {
			this.defaultPayType = defaultPayType;
		}
		public String getTransTimeout() {
			return transTimeout;
		}
		public void setTransTimeout(String transTimeout) {
			this.transTimeout = transTimeout;
		}
		public String getOrigQid() {
			return origQid;
		}
		public void setOrigQid(String origQid) {
			this.origQid = origQid;
		}
		public String getMerReserved() {
			return merReserved;
		}
		public void setMerReserved(String merReserved) {
			this.merReserved = merReserved;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setSignMethod(String signMethod) {
			this.signMethod = signMethod;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
}
