package it.app18.verificavoucher;

/**
 * Exception to signal problems with the Secure Communication with the VerificaVoucher WebService
 */
public class CertificateException extends Exception {

    private String message;

    @Override
    public String toString() {
        return "CertificateException{" +
                "message='" + message + '\'' +
                '}';
    }

    CertificateException(String message) {
        super(message);
        this.message = message;
    }

}
