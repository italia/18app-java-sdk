package lib.model;

/**
 * This enum lists possibile operation on Confirm operation in Verifica Voucher SOAP service.
 */
public enum ConfirmOperation {

    CONFIRM("1");

    String type;

    ConfirmOperation(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
