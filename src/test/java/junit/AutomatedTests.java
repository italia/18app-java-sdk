package junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sdk.EighteenAppMerchantService;
import sdk.model.Certificate;
import sdk.soap.check.*;
import sdk.soap.confirm.Confirm;
import sdk.soap.confirm.ConfirmOperationType;
import sdk.soap.confirm.ConfirmRequestObj;
import sdk.soap.confirm.ConfirmResponseListener;
import sdk.soap.fault.FaultErrorCode;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AutomatedTests {

    private static String[] vouchers = {
            "fakeVoucher",
            "92TJQYfP",
            "BQxQiias",
            "lccP58nr",
            "0ClXd6IO",
            "LhFrESFL",
            "AQp934Ga",
            "GRtNn3OE",
            "m9heng9B",
            "NNh17UgV"

    };

    private EighteenAppMerchantService service;
    private Certificate certificate;

    @BeforeEach
    void setUpService() {
        // Getting the certificate
        System.setProperty("javax.net.ssl.trustStore", "cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        certificate = new Certificate.Builder()
                .setKeystorePath("/Users/riccardobusetti/Downloads/Progetto18app/AAAAAA00H01H501P.p12")
                .setPassword("m3D0T4aM")
                .build();
        service = new EighteenAppMerchantService(certificate);
    }

    @Test
    void checkWithoutConfirmTest() {
        Check check = new Check();
        check.setTipoOperazione(CheckOperationType.CHECK);
        check.setCodiceVoucher(vouchers[vouchers.length - 1]);
        // Initiation of the check request object wrapper
        CheckRequestObj checkRequestObj = new CheckRequestObj();
        checkRequestObj.setCheckReq(check);
        // Initiation of the service
        EighteenAppMerchantService service = new EighteenAppMerchantService(certificate);
        service.makeCheckRequest(checkRequestObj, new CheckResponseListener() {
            @Override
            public void onCheckSuccess(CheckResponse response) {
                assertNotEquals(response, null);
            }

            @Override
            public void onCheckError(FaultErrorCode errorCode) {
                assertNotEquals(errorCode, null);
            }

            @Override
            public void onCheckRequestFailed(EighteenAppMerchantService.RequestFailType failType) {

            }

            @Override
            public void onCheckResponseFailed(EighteenAppMerchantService.ResponseFailType failType) {

            }
        });
    }

    @Test
    void checkWithConfirmationTest() {
        // Initiation of the check request object
        Check check = new Check();
        check.setTipoOperazione(CheckOperationType.CHECK_AND_PAY);
        check.setCodiceVoucher(vouchers[vouchers.length - 1]);
        // Initiation of the check request object wrapper
        CheckRequestObj checkRequestObj = new CheckRequestObj();
        checkRequestObj.setCheckReq(check);
        // Initiation of the service
        EighteenAppMerchantService service = new EighteenAppMerchantService(certificate);
        service.makeCheckRequest(checkRequestObj, new CheckResponseListener() {
            @Override
            public void onCheckSuccess(CheckResponse response) {
                assertNotEquals(response, null);
            }

            @Override
            public void onCheckError(FaultErrorCode errorCode) {
                assertNotEquals(errorCode, null);
            }

            @Override
            public void onCheckRequestFailed(EighteenAppMerchantService.RequestFailType failType) {

            }

            @Override
            public void onCheckResponseFailed(EighteenAppMerchantService.ResponseFailType failType) {

            }
        });
    }

    @Test
    void confirmTest() {
        // Initiation of the confirm request object
        Confirm confirm = new Confirm();
        confirm.setTipoOperazione(ConfirmOperationType.CONFIRM);
        confirm.setCodiceVoucher(vouchers[vouchers.length - 4]);
        confirm.setImporto(10);
        // Initiation of the confirm request object wrapper
        ConfirmRequestObj confirmRequestObj = new ConfirmRequestObj();
        confirmRequestObj.setCheckReq(confirm);
        // Initiation of the service
        EighteenAppMerchantService service = new EighteenAppMerchantService(certificate);
        service.makeConfirmRequest(confirmRequestObj, new ConfirmResponseListener() {
            @Override
            public void onConfirmSuccess(boolean isOperationResultOk) {

            }

            @Override
            public void onConfirmError(FaultErrorCode errorCode) {
                assertNotEquals(errorCode, null);
            }

            @Override
            public void onConfirmRequestFailed(EighteenAppMerchantService.RequestFailType failType) {

            }

            @Override
            public void onConfirmResponseFailed(EighteenAppMerchantService.ResponseFailType failType) {

            }
        });
    }

}
