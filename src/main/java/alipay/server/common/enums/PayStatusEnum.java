package alipay.server.common.enums;

public enum PayStatusEnum {
    UN_PAY("未支付", 0), PAY("已支付", 1), REFUND("已退款", 2);
    private String name;
    private int index;

    PayStatusEnum(String name, int index) {
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
