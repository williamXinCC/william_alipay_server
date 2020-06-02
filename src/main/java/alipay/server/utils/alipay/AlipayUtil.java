package alipay.server.utils.alipay;

import alipay.server.common.constant.Constant;
import alipay.server.common.vo.AlipayRefundRequestVo;
import alipay.server.config.AlipayConfig;
import alipay.server.exception.BaseException;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class AlipayUtil {
    private Logger logger = LoggerFactory.getLogger("errorLog");

    private static AlipayUtil alipayUtil = new AlipayUtil();

    private AlipayUtil() {
    }

    public static AlipayUtil getInstance() {
        if (alipayUtil == null) {
            alipayUtil = new AlipayUtil();
        }
        return alipayUtil;
    }

    private static AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

    /**
     * @Author: HMG
     * @Date: 2018/11/7
     * @Description: 支付宝h5支付
     */
    public String pay(String out_trade_no, String subject, String total_amount, String body, String returnRrl) {
        AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(body);
        model.setTimeoutExpress(AlipayConfig.TIMEOUT_EXPRESS);
        model.setProductCode(AlipayConfig.PRODUCT_CODE);
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(AlipayConfig.NOTIFY_URL);
        // 设置同步地址(由前端指定)
        alipay_request.setReturnUrl(returnRrl);
        AlipayResponse alipayResponse;
        try {
            // 请求支付宝
            alipayResponse = client.pageExecute(alipay_request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.error("errorMessage", e);
            throw new BaseException(8801, "请求支付宝失败");
        }
        return alipayResponse.getBody();
    }

    /**
     * @Author: HMG
     * @Date: 2018/11/7
     * @Description: 提交APP支付请求给支付宝
     */
    public String appPay(String out_trade_no, String subject, String total_amount, String body, String returnRrl) {
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setSubject(subject);
        model.setOutTradeNo(out_trade_no);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(total_amount);
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(returnRrl);
        request.setNotifyUrl(AlipayConfig.NOTIFY_URL);
        AlipayTradeAppPayResponse response;
        try {
            response = client.sdkExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.error("errorMessage", e);
            throw new BaseException(8801, "请求支付宝失败");
        }
        return response.getBody();
    }

    /**
     * @Author: HMG
     * @Date: 2018/11/8
     * @Description: 支付宝验签
     */
    public boolean verifyCheck(Map<String, String> params) throws AlipayApiException {
        return AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
    }

    /**
     * @Author: HMG
     * @Date: 2018/12/7
     * @Description: 查询支付宝支付结果[HMG]
     */
    public boolean getPayStatus(String outTradeNo) {
        AlipayTradeQueryRequest alipay_request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(outTradeNo);
        alipay_request.setBizModel(model);

        AlipayTradeQueryResponse alipay_response = null;
        try {
            alipay_response = client.execute(alipay_request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.error("errorMessage", e);
            throw new BaseException(8801, "请求支付宝失败");
        }
        if (alipay_response.getTradeStatus() != null && alipay_response.getTradeStatus().equals("TRADE_SUCCESS")) {
            return true;
        }
        return false;
    }

    /**
     * @Author: HMG
     * @Date: 2019/9/29
     * @Description: 支付宝退款
     */
    public void refund(AlipayRefundRequestVo alipayRefundRequestVo) {
        AlipayTradeRefundRequest alipay_request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(alipayRefundRequestVo.getOutTradeNo());
        model.setRefundAmount(alipayRefundRequestVo.getTotalAmount());
        model.setRefundReason(alipayRefundRequestVo.getReason());
        model.setOutRequestNo(alipayRefundRequestVo.getOutTradeNo());
        alipay_request.setBizModel(model);

        AlipayTradeRefundResponse alipay_response = null;
        try {
            alipay_response = client.execute(alipay_request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.error("errorMessage", e);
            throw new BaseException(Constant.ERROR_CODE, "请求支付宝失败");
        }
        logger.info(alipay_response.getBody());
        if (alipay_response.getCode() != null && !alipay_response.getCode().equals("10000")) {
            String returnMsg = String.format("支付宝退款操作失败code:{%s}, msg:{%s},subMsg:{%s}", alipay_response.getCode(), alipay_response.getMsg(), alipay_response.getSubMsg());
            throw new BaseException(Constant.ERROR_CODE, returnMsg);
        }
    }


    public static void main(String[] args) throws AlipayApiException {
//        AlipayUtil.getInstance().refund();
//        String app_id = "2018110662007347";
//        String biz_content = "{\"body\":\"test\", \"subject\":\"test\", \"out_trade_no\":\"huo_test\", \"timeout_express\":\"90m\", \"total_amount\":\"9.00\", \"goods_type\":\"0\", \"product_code\":\"QUICK_MSECURITY_PAY \" }";
//        String charset = "utf-8";
//        String format = "json";
//        String method = "alipay.trade.app.pay";
//        String notify_url = " http://22091c69i0.iok.la/callback/alipayCallback";
//        String sign_type = "RSA2";
//        String timestamp = "2019-08-25 20:26:31";
//        String version = "1.0";
//        String content = "app_id=" + app_id + "&biz_content=" + biz_content + "&charset=" + charset + "&format=" + format + "&method=" + method + "&notify_url=" + notify_url + "&sign_type=" + sign_type + "&timestamp=" + timestamp + "&version=" + version;
//        System.out.println("content : " + content);
//        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCvBrIsaAz2AZPx3JeYjg5bonA4NcEMpsaUylGdM09JZnRbh3Vir7arIqHkfGzAesnHBs1YQqLgmS6bedSz/pWqW4mwtgCreDdfRWI3wDUggIBAoPkIQIuEVZT2GGHnVpFSRZjDwLRB2RkQei3fPODy7LbfWJaRa2E5hRI0OxviyAlN49v4FgCoEjbf1r+IGz9zboaaje1FFrpm5ohjcacWn+3M5MjMfa3sTrbtZUcy5S04bBV7ObBYAskd4XN+wWyOc5QmEc7utBiGrE4hKEZXdogIn05uBiCxjNcSe6aGToU5ywU7mk0DX7yLgfqo+cLYBQ+xMXbjpoo1FefKIuTfAgMBAAECggEAVOslxT/SkgUf96f0cVLzCmick9kYyf+ct5IV2PSNEmrAJ6xoOrJQKWYLMCViSl0IyyS0uo7SIbCQmWSZ2564lMgG3RLg7lDMZM/wIGaEIDnYgALZ8LQIPPOc623XCnvI1CbqQIedrZkcfQ8adIZACfzpmSGNJA76RAMZWvgWxodB+Hg/FVWCaGpBGG5e2G/ItZ8MydliTxafTGWB2ipwRxmqpY3QRng2VJ3d3GxBMVxgTy2gQVxUaiLGYvxx+DMjGUaQ6zr76EphkxjagWta0nvZtTnGnwTvRR6LVx99pR/yaMu6LpV4AtGmyB7Sz7Ak/ZDw8ttSv0msRTFy5ERYkQKBgQDzgGLusgcxbNZ8npnGvckojhDtNR5+xFMMd4mqOnouH871VTWobAsqHJB20XrVdg/8hbx9Grof+tnh7CCII2GzSLXIMXrFoW1qq9id6NIekCEc9HvxWqa1rK9yMumaDEvY+8aAOvBrXqa/1vs8DBfTg7bN5isQ42YHceO3VpqxeQKBgQC4AorXYMx6kR/4rWaNrB2zLh334FuRgVg3+HQ402fUaXS7QOBw0BBAzcThc0LO08YUvX2w8N2N0OaGnZdNwICqk23MtsAeruzcx9jaMChkIXvy/PzGqLtVtyY4aPB1HpgCBT2JlMYiylkWAOTaqJ0DIRXWx6IgIPv5vgmehRLLFwKBgAyCkRKgVNxGMjLO1ymtwHLz8YyuSPXMUlP2pDz5qiNKgRB7ozkW6+/q7cUc+AmVejbIDCoFQIC6G42O2xEDeFaEqsoxOJSEuaFcWNGsD3eAgiMBsIhy1tPInm7ojLXcYd0w+j7GSGnLwBdlDR1Fpce5IaAsbhP9CtXhFQjrSyFJAoGAOEOWvop7m5KaawhiTjltj2cvhKaWeSRKnDqdFu8VVW61YbhRqE0GWzpP0/vRVrqMW7UhJKRRqlZQ1qKDLCQLc5zGuwy6K+eix1NYLEcDYrKWWtMeW4QmW+w3hJIZzEYBhsShb1sNRoAGw2Kcl3ttHxLTwaujGB3dzhxchpr8BxsCgYEApGUjMAChpqF+C6XhBxlDxXzfWz1CyUnVYCT5oOS12UajhQs4sAGdTkp+xwLYDw5ZIYy9SJRrcvLA+XGGs/q8JDGhCZGdDOFiIwBlnOKnjbsrVkGJzQ29CDVXQemAfgCOkRTh7A9XbwINc9PwJ1Ry6/ggYP++pVtGD3iUITPWFmU=";
//        String sign = AlipaySignature.rsa256Sign(content, privateKey, "utf-8");
//        System.out.println("sign : " + sign);
//        String url = "app_id=" + app_id + "&biz_content=" + URLEncoder.encode(biz_content) + "&charset=" + charset + "&format=" + format + "&method=" + method + "&notify_url=" + URLEncoder.encode(notify_url) + "&sign_type=" + sign_type + "&timestamp=" + URLEncoder.encode(timestamp) + "&version=" + version + "&sign=" + URLEncoder.encode(sign);
//        System.out.println("url : " + url);
    }

}
