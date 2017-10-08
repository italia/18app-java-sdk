import sdk.EighteenAppMerchantService;
import sdk.model.Certificate;
import sdk.soap.confirm.Confirm;
import sdk.soap.confirm.ConfirmOperationType;
import sdk.soap.confirm.ConfirmRequestObj;
import sdk.soap.confirm.ConfirmResponseListener;
import sdk.soap.fault.FaultErrorCode;

public class ConfirmTest {

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
        confirmTest(certificate, vouchers.length - 1, 2);
    }

    private static void confirmTest(Certificate certificate, int voucherIndex, int amount) {
        // Initiation of the confirm request object
        Confirm confirm = new Confirm();
        confirm.setTipoOperazione(ConfirmOperationType.CONFIRM);
        confirm.setCodiceVoucher(vouchers[voucherIndex]);
        confirm.setImporto(amount);
        // Initiation of the confirm request object wrapper
        ConfirmRequestObj confirmRequestObj = new ConfirmRequestObj();
        confirmRequestObj.setCheckReq(confirm);
        // Initiation of the service
        EighteenAppMerchantService service = new EighteenAppMerchantService(certificate);
        service.makeConfirmRequest(confirmRequestObj, new ConfirmResponseListener() {
            @Override
            public void onConfirmSuccess(boolean isOperationResultOk) {
                if (isOperationResultOk) System.out.print("Operazione andata a buon fine!");
                else System.out.print("Operazione non andata a buon fine!");
            }

            @Override
            public void onConfirmError(FaultErrorCode errorCode) {
                System.out.print(errorCode.getErrorDescription());
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
