 package alipay.server.common.vo;

 import io.swagger.annotations.ApiModelProperty;

 /**
  * @Author: HMG
  * @Date: 2018/11/7
  * @Description: 支付宝支付server接参数vo
  */
  public class AlipayPayVo {

      @ApiModelProperty("自定义的外部订单号")
      private String outTradeNo;
      @ApiModelProperty("商品名称")
      private String subject;
      @ApiModelProperty("付款金额")
      private String totalAmount;
      @ApiModelProperty("商品描述")
      private String body;
     @ApiModelProperty("支付宝返回地址")
     private String returnUrl;

     public String getReturnUrl() {
         return returnUrl;
     }

     public void setReturnUrl(String returnUrl) {
         this.returnUrl = returnUrl;
     }

     public String getOutTradeNo() {
         return outTradeNo;
     }

     public void setOutTradeNo(String outTradeNo) {
         this.outTradeNo = outTradeNo;
     }

     public String getSubject() {
         return subject;
     }

     public void setSubject(String subject) {
         this.subject = subject;
     }

     public String getTotalAmount() {
         return totalAmount;
     }

     public void setTotalAmount(String totalAmount) {
         this.totalAmount = totalAmount;
     }

     public String getBody() {
         return body;
     }

     public void setBody(String body) {
         this.body = body;
     }

     @Override
     public String toString() {
         return "AlipayPayVo{" +
                 "outTradeNo='" + outTradeNo + '\'' +
                 ", subject='" + subject + '\'' +
                 ", totalAmount='" + totalAmount + '\'' +
                 ", body='" + body + '\'' +
                 ", returnUrl='" + returnUrl + '\'' +
                 '}';
     }
 }
