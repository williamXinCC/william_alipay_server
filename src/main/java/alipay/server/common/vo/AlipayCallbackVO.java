package alipay.server.common.vo;

import io.swagger.annotations.ApiModelProperty;

public class AlipayCallbackVO {
    @ApiModelProperty("通知时间")
    private String notify_time ;
    @ApiModelProperty("支付记录Id")
    private String payId;
    @ApiModelProperty("订单Id")
    private String orderId;
    @ApiModelProperty("通知类型")
    private String notify_type ;
    @ApiModelProperty("通知校验ID")
    private String notify_id ;
    @ApiModelProperty("开发者的app_id")
    private String app_id ;
    @ApiModelProperty("编码格式")
    private String charset ;
    @ApiModelProperty("接口版本")
    private String version ;
    @ApiModelProperty("签名类型")
    private String sign_type ;
    @ApiModelProperty("签名")
    private String sign ;
    @ApiModelProperty("支付宝交易号")
    private String trade_no ;
    @ApiModelProperty("商户订单号")
    private String out_trade_no ;
    @ApiModelProperty("商户业务号")
    private String out_biz_no ;
    @ApiModelProperty("买家支付宝用户号")
    private String buyer_id ;
    @ApiModelProperty("买家支付宝账号")
    private String buyer_logon_id ;
    @ApiModelProperty("卖家支付宝用户号")
    private String seller_id ;
    @ApiModelProperty("卖家支付宝账号")
    private String seller_email ;
    @ApiModelProperty("交易状态")
    private String trade_status ;
    @ApiModelProperty("订单金额")
    private String total_amount ;
    @ApiModelProperty("实收金额")
    private String receipt_amount ;
    @ApiModelProperty("开票金额")
    private String invoice_amount ;
    @ApiModelProperty("付款金额")
    private String buyer_pay_amount ;
    @ApiModelProperty("集分宝金额")
    private String point_amount ;
    @ApiModelProperty("总退款金额")
    private String refund_fee ;
    @ApiModelProperty("订单标题")
    private String subject ;
    @ApiModelProperty("商品描述")
    private String body ;
    @ApiModelProperty("交易创建时间")
    private String gmt_create ;
    @ApiModelProperty("交易付款时间")
    private String gmt_payment ;
    @ApiModelProperty("交易退款时间")
    private String gmt_refund ;
    @ApiModelProperty("交易结束时间")
    private String gmt_close ;
    @ApiModelProperty("支付金额信息")
    private String fund_bill_list ;
    @ApiModelProperty("回传参数")
    private String passback_params ;
    @ApiModelProperty("优惠券信息")
    private String voucher_detail_list ;

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    @Override
    public String toString() {
        return "AlipayCallbackVO{" +
                "notify_time='" + notify_time + '\'' +
                ", payId='" + payId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", notify_type='" + notify_type + '\'' +
                ", notify_id='" + notify_id + '\'' +
                ", app_id='" + app_id + '\'' +
                ", charset='" + charset + '\'' +
                ", version='" + version + '\'' +
                ", sign_type='" + sign_type + '\'' +
                ", sign='" + sign + '\'' +
                ", trade_no='" + trade_no + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", out_biz_no='" + out_biz_no + '\'' +
                ", buyer_id='" + buyer_id + '\'' +
                ", buyer_logon_id='" + buyer_logon_id + '\'' +
                ", seller_id='" + seller_id + '\'' +
                ", seller_email='" + seller_email + '\'' +
                ", trade_status='" + trade_status + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", receipt_amount='" + receipt_amount + '\'' +
                ", invoice_amount='" + invoice_amount + '\'' +
                ", buyer_pay_amount='" + buyer_pay_amount + '\'' +
                ", point_amount='" + point_amount + '\'' +
                ", refund_fee='" + refund_fee + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", gmt_create='" + gmt_create + '\'' +
                ", gmt_payment='" + gmt_payment + '\'' +
                ", gmt_refund='" + gmt_refund + '\'' +
                ", gmt_close='" + gmt_close + '\'' +
                ", fund_bill_list='" + fund_bill_list + '\'' +
                ", passback_params='" + passback_params + '\'' +
                ", voucher_detail_list='" + voucher_detail_list + '\'' +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_biz_no() {
        return out_biz_no;
    }

    public void setOut_biz_no(String out_biz_no) {
        this.out_biz_no = out_biz_no;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getBuyer_logon_id() {
        return buyer_logon_id;
    }

    public void setBuyer_logon_id(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getReceipt_amount() {
        return receipt_amount;
    }

    public void setReceipt_amount(String receipt_amount) {
        this.receipt_amount = receipt_amount;
    }

    public String getInvoice_amount() {
        return invoice_amount;
    }

    public void setInvoice_amount(String invoice_amount) {
        this.invoice_amount = invoice_amount;
    }

    public String getBuyer_pay_amount() {
        return buyer_pay_amount;
    }

    public void setBuyer_pay_amount(String buyer_pay_amount) {
        this.buyer_pay_amount = buyer_pay_amount;
    }

    public String getPoint_amount() {
        return point_amount;
    }

    public void setPoint_amount(String point_amount) {
        this.point_amount = point_amount;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getGmt_payment() {
        return gmt_payment;
    }

    public void setGmt_payment(String gmt_payment) {
        this.gmt_payment = gmt_payment;
    }

    public String getGmt_refund() {
        return gmt_refund;
    }

    public void setGmt_refund(String gmt_refund) {
        this.gmt_refund = gmt_refund;
    }

    public String getGmt_close() {
        return gmt_close;
    }

    public void setGmt_close(String gmt_close) {
        this.gmt_close = gmt_close;
    }

    public String getFund_bill_list() {
        return fund_bill_list;
    }

    public void setFund_bill_list(String fund_bill_list) {
        this.fund_bill_list = fund_bill_list;
    }

    public String getPassback_params() {
        return passback_params;
    }

    public void setPassback_params(String passback_params) {
        this.passback_params = passback_params;
    }

    public String getVoucher_detail_list() {
        return voucher_detail_list;
    }

    public void setVoucher_detail_list(String voucher_detail_list) {
        this.voucher_detail_list = voucher_detail_list;
    }
}
