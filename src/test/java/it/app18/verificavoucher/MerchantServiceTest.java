package it.app18.verificavoucher;

import it.mibact.bonus.verificavoucher.CheckResponse;
import it.mibact.bonus.verificavoucher.ConfirmResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantServiceTest {
    private MerchantService service;
    private String keystorePath ="AAAAAA00H01H501P.p12";
    private String password = "m3D0T4aM";
    private static String[] codVoucher  = {"11aa22bb","PqCSZjFp","Ex7jYMDg","lTTeLX5v"};

    @BeforeEach
    void setUp() {
        try {
            service = new MerchantService(keystorePath, password);
            service.activateCertificate();
        } catch (VoucherVerificationException | CertificateException e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkOnlyOperation() {
        try {
            CheckResponse response = service.checkOnlyOperation(codVoucher[0]);
            assertNotEquals(response, null);
        } catch (VoucherVerificationException | CertificateException e) {
            e.printStackTrace();
        }

    }

    @Test
    void checkAndConsumeOperation() {
        try {
            CheckResponse checkResponse = service.checkAndConsumeOperation(codVoucher[2]);
            assertNotEquals(checkResponse, null);
        } catch (VoucherVerificationException | CertificateException e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkAndFreezeOperation() {
        CheckResponse checkResponse = null;
        try {
            checkResponse = service.checkAndFreezeOperation(codVoucher[3]);
            assertNotEquals(checkResponse, null);
        } catch (VoucherVerificationException e) {
            e.printStackTrace();
            assertEquals(e.getId(), MerchantService.FaultCodes.VOUCHER_NOT_FOUND);
            return;
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        try {
            ConfirmResponse confirmResponse = service.confirmOperation(codVoucher[3], checkResponse.getImporto());
            assertNotEquals(confirmResponse, null);
        } catch (VoucherVerificationException e) {
            e.printStackTrace();
            assertEquals(e.getId(), MerchantService.FaultCodes.VOUCHER_NOT_FOUND);
        }
    }

}