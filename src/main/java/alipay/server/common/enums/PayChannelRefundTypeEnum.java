package alipay.server.common.enums;

public enum PayChannelRefundTypeEnum {
    AUTO_REFUND("自动退款", 1),
    HAND_REFUND("手动退款", 2);

    private String name;
    private int index;

    PayChannelRefundTypeEnum(String name, int index) {
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
