package it.app18.verificavoucher;

/**
 * Exception to handle fault of the VerificaVoucher Web Service
 */
public class VoucherVerificationException extends Exception {
    private String id;
    private String message;

    @Override
    public String toString() {
        return "VoucherVerificationException{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

    public VoucherVerificationException(String id, String message) {
        super(message);
        this.id = id;
        this.message = message;
    }

}
