
import it.mibact.bonus.verificavoucher.CheckResponse;
import it.mibact.bonus.verificavoucher.ConfirmResponse;
import it.app18.verificavoucher.CertificateException;
import it.app18.verificavoucher.MerchantService;
import it.app18.verificavoucher.VoucherVerificationException;

public class TestFreezeAndConfirm {

    public static void main(String[] args) {

        String codVoucher = "Zirfsz0d";

        try {

            MerchantService service = new MerchantService("AAAAAA00H01H501P.p12", "m3D0T4aM");
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
