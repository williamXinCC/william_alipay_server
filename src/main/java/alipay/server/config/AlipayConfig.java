package alipay.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AlipayConfig {

    // 商户appid
    public static String APPID ;
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY;
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String NOTIFY_URL;
    // 请求网关地址
    public static String URL ;
    // 编码
    public static String CHARSET;
    // 返回格式
    public static String FORMAT ;
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY ;
    // RSA2
    public static String SIGNTYPE ;
    // 销售产品码，商家和支付宝签约的产品码。该产品请填写固定值：QUICK_WAP_WAY
    public static String PRODUCT_CODE;
    // 支付超时时间 该笔订单允许的最晚付款时间，逾期将关闭交易。 m-分钟，h-小时，d-天，1c-当天
    public static String TIMEOUT_EXPRESS ;

    @Value("${APPID}")
    private void setAPPID(String APPID) {
        AlipayConfig.APPID = APPID;
    }
    @Value("${RSA_PRIVATE_KEY}")
    private void setRsaPrivateKey(String rsaPrivateKey) {
        RSA_PRIVATE_KEY = rsaPrivateKey;
    }
    @Value("${NOTIFY_URL}")
    private void setNotifyUrl(String notifyUrl) {
        NOTIFY_URL = notifyUrl;
    }
    @Value("${URL}")
    private void setURL(String URL) {
        AlipayConfig.URL = URL;
    }
    @Value("${CHARSET}")
    private void setCHARSET(String CHARSET) {
        AlipayConfig.CHARSET = CHARSET;
    }
    @Value("${FORMAT}")
    private void setFORMAT(String FORMAT) {
        AlipayConfig.FORMAT = FORMAT;
    }
    @Value("${ALIPAY_PUBLIC_KEY}")
    private void setAlipayPublicKey(String alipayPublicKey) {
        ALIPAY_PUBLIC_KEY = alipayPublicKey;
    }
    @Value("${SIGNTYPE}")
    private void setSIGNTYPE(String SIGNTYPE) {
        AlipayConfig.SIGNTYPE = SIGNTYPE;
    }
    @Value("${PRODUCT_CODE}")
    private void setProductCode(String productCode) {
        PRODUCT_CODE = productCode;
    }
    @Value("${TIMEOUT_EXPRESS}")
    private void setTimeoutExpress(String timeoutExpress) {
        TIMEOUT_EXPRESS = timeoutExpress;
    }
}
