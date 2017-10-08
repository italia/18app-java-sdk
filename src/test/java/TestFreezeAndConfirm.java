
import it.mibact.bonus.verificavoucher.CheckResponse;
import it.mibact.bonus.verificavoucher.ConfirmResponse;
import it.app18.verificavoucher.CertificateException;
import it.app18.verificavoucher.MerchantService;
import it.app18.verificavoucher.VoucherVerificationException;

public class TestFreezeAndConfirm {
    private static String codVoucher ;
    private static String keystorePath ;
    private static String password ;

    public TestFreezeAndConfirm(String code, String keyPath , String pass) {
        codVoucher = code;
        keystorePath = keyPath;
        password = pass;
    }

    public static void main(String[] args) {
        testMe();
    }

   public static void testMe(){
        try {

            MerchantService service = new MerchantService(keystorePath, password);
            // Does not consume the voucher and does not scale the amount from the purse
            // The merchant is now active

            CheckResponse checkResponse = service.checkAndFreezeOperation(codVoucher);
            System.out.println(checkResponse);

            ConfirmResponse confirmResponse = service.confirmOperation(codVoucher, checkResponse.getImporto());
            System.out.println(confirmResponse);

        } catch (VoucherVerificationException | CertificateException e) {
            e.printStackTrace();
        }
    }
}
