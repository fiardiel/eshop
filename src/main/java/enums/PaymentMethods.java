package enums;

import lombok.Getter;

@Getter
public enum PaymentMethods {
    VOUCHERCODE("Voucher Code"),
    BANKTRANSFER("Bank Transfer");

    private final String value;
    private PaymentMethods(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentMethods paymentMethods : PaymentMethods.values()) {
            if (paymentMethods.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}