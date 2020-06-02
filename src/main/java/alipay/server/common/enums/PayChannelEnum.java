package alipay.server.common.enums;

public enum PayChannelEnum {
    ALIPAY("支付宝", 1),
    WEIXIN_PUBLIC("微信公众号", 2),
    CCB("建行支付", 3),
    ALIPAY_APP("支付宝APP支付", 4),
    WEIXIN_APP("微信APP支付", 5),
    WEIXIN_H5("微信H5支付", 6),
    HUAXIA("华夏支付", 7);
    private String name;
    private int index;

    PayChannelEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }


    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
