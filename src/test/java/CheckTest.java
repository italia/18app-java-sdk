import sdk.EighteenAppMerchantService;
import sdk.model.Certificate;
import sdk.soap.check.*;
import sdk.soap.fault.FaultErrorCode;

public class CheckTest {

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

    public static void main(String[] args) {
        // Getting the certificate
        System.setProperty("javax.net.ssl.trustStore", "cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        // Init certificate
        Certificate certificate = new Certificate.Builder()
                .setKeystorePath("/Users/riccardobusetti/Downloads/Progetto18app/AAAAAA00H01H501P.p12")
                .setPassword("m3D0T4aM")
                .build();
        // Call here the methods for testing
        checkWithoutConfirmTest(certificate, vouchers.length - 2);
    }

    private static void checkWithoutConfirmTest(Certificate certificate, int voucherIndex) {
        // Initiation of the check request object
        Check check = new Check();
        check.setTipoOperazione(CheckOperationType.CHECK);
        check.setCodiceVoucher(vouchers[voucherIndex]);
        // Initiation of the check request object wrapper
        CheckRequestObj checkRequestObj = new CheckRequestObj();
        checkRequestObj.setCheckReq(check);
        // Initiation of the service
        EighteenAppMerchantService service = new EighteenAppMerchantService(certificate);
        service.makeCheckRequest(checkRequestObj, new CheckResponseListener() {
            @Override
            public void onCheckSuccess(CheckResponse response) {
                System.out.print(response.getImporto());
            }

            @Override
            public void onCheckError(FaultErrorCode errorCode) {
                System.out.print(errorCode.getErrorDescription());
            }

            @Override
            public void onCheckRequestFailed(EighteenAppMerchantService.RequestFailType failType) {

            }

            @Override
            public void onCheckResponseFailed(EighteenAppMerchantService.ResponseFailType failType) {

            }
        });
    }

    private static void checkWithConfirmTest(Certificate certificate, int voucherIndex) {
        // Initiation of the check request object
        Check check = new Check();
        check.setTipoOperazione(CheckOperationType.CHECK_AND_PAY);
        check.setCodiceVoucher(vouchers[voucherIndex]);
        // Initiation of the check request object wrapper
        CheckRequestObj checkRequestObj = new CheckRequestObj();
        checkRequestObj.setCheckReq(check);
        // Initiation of the service
        EighteenAppMerchantService service = new EighteenAppMerchantService(certificate);
        service.makeCheckRequest(checkRequestObj, new CheckResponseListener() {
            @Override
            public void onCheckSuccess(CheckResponse response) {
                System.out.print(response.getBene());
            }

            @Override
            public void onCheckError(FaultErrorCode errorCode) {
                System.out.print(errorCode.getErrorDescription());
            }

            @Override
            public void onCheckRequestFailed(EighteenAppMerchantService.RequestFailType failType) {

            }

            @Override
            public void onCheckResponseFailed(EighteenAppMerchantService.ResponseFailType failType) {

            }
        });
    }

}
