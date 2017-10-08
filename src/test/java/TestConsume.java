import it.mibact.bonus.verificavoucher.CheckResponse;
import it.app18.verificavoucher.CertificateException;
import it.app18.verificavoucher.MerchantService;
import it.app18.verificavoucher.VoucherVerificationException;

public class TestConsume {

    public static void main(String[] args) {

        String codVoucher = "iThHnbGY";

        try {

            MerchantService service = new MerchantService("AAAAAA00H01H501P.p12", "m3D0T4aM");
            // Does not consume the voucher and does not scale the amount from the purse
            // The merchant is now active

            CheckResponse checkResponse = service.checkAndConsumeOperation(codVoucher);

            System.out.println(checkResponse);

        } catch (VoucherVerificationException | CertificateException e) {
            e.printStackTrace();
        }
    }
}
