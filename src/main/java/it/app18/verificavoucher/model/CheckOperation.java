package it.app18.verificavoucher.model;

/**
 * This enum lists possibile operation on Check operation in Verifica Voucher SOAP service.
 */
public enum CheckOperation {

    CHECK_ONLY_VOUCHER("1"),
    CHECK_CONSUME_VOUCHER("2"),
    CHECK_FREEZE_VOUCHER("3");

    String type;

    CheckOperation(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
