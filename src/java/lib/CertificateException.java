package lib;

/**
 * Exception to signal problems with the Secure Communication with the VerificaVoucher WebService
 */
public class CertificateException extends Exception {

    private String id;
    private String message;

    @Override
    public String toString() {
        return "VoucherVerificationException{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    CertificateException(String message) {
        super(message);
        this.message = message;
    }

}
