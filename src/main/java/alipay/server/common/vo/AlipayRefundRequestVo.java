 package alipay.server.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

 /**
  * @Author: HMG
  * @Date: 2018/11/7
  * @Description: 支付宝退款server接参数vo
  */
 @Data
 public class AlipayRefundRequestVo {
      @ApiModelProperty("外部订单号")
      private String outTradeNo;
      @ApiModelProperty("退款金额")
      private String totalAmount;
      @ApiModelProperty("退款原因")
      private String reason;

 }
