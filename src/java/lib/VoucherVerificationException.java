package lib;

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

    public VoucherVerificationException(String id, String message) {
        super(message);
        this.id = id;
        this.message = message;
    }
}
