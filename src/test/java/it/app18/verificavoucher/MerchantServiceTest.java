package it.app18.verificavoucher;

import it.mibact.bonus.verificavoucher.CheckResponse;
import it.mibact.bonus.verificavoucher.ConfirmResponse;
import it.mibact.bonus.verificavoucher.VerificaVoucher_Service;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class MerchantServiceTest {

    private static final String[] codVoucher  = {"11aa22bb", "PqCSZjFp", "Ex7jYMDg", "lTTeLX5v"};

    private static MerchantService service;

    @BeforeAll
    static void setUp() throws Exception {
        URL keyFile = MerchantServiceTest.class.getClassLoader().getResource("AAAAAA00H01H501P.P12");
        assertNotNull(keyFile);
        URL trustStoreFile = MerchantServiceTest.class.getClassLoader().getResource("cacerts");
        assertNotNull(trustStoreFile);
        service = new MerchantService(keyFile.getFile(), "m3D0T4aM", trustStoreFile.getFile(), "changeit");
        String partitaIva = assertDoesNotThrow(() -> service.activateCertificate());
        assertNotNull(partitaIva);
    }

    @Test
    void checkOnlyOperation() {
        try {
            CheckResponse response = service.checkOnlyOperation(codVoucher[0]);
            assertNotNull(response);
        } catch (VoucherVerificationException | CertificateException e) {
            log.warn("attenzione", e);
        }

    }

    @Test
    void checkAndConsumeOperation() {
        try {
            CheckResponse checkResponse = service.checkAndConsumeOperation(codVoucher[2]);
            assertNotNull(checkResponse);
        } catch (VoucherVerificationException | CertificateException e) {
            log.warn("attenzione", e);
        }
    }

    @Test
    void checkAndFreezeOperation() {
        CheckResponse checkResponse = null;
        try {
            checkResponse = service.checkAndFreezeOperation(codVoucher[3]);
            assertNotNull(checkResponse);
        } catch (VoucherVerificationException e) {
            log.warn("attenzione", e);
            assertEquals(e.getId(), FaultCodes.VOUCHER_NOT_FOUND);
            return;
        } catch (CertificateException e) {
            log.warn("attenzione", e);
        }

        try {
            ConfirmResponse confirmResponse = service.confirmOperation(codVoucher[3], checkResponse.getImporto());
            assertNotNull(confirmResponse);
        } catch (VoucherVerificationException e) {
            log.warn("attenzione", e);
            assertEquals(e.getId(), FaultCodes.VOUCHER_NOT_FOUND);
        }
    }

}