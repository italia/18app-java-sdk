package test;

import it.mibact.bonus.verificavoucher.CheckResponse;
import it.mibact.bonus.verificavoucher.ConfirmResponse;
import lib.CertificateException;
import lib.MerchantService;
import lib.VoucherVerificationException;

public class TestFreezeAndConfirm {

    public static void main(String[] args) {

        String nomeCliente = "Attivazione effettuata";
        String codVoucher = "Zirfsz0d";
        String keystorePath = "";
        String password = "";
        try {

            MerchantService service = new MerchantService("AAAAAA00H01H501P.p12", "m3D0T4aM");
            CheckResponse checkResponse = service.activateCertificate();
            // Does not consume the voucher and does not scale the amount from the purse
            // The merchant is now active

            if (!checkResponse.getNominativoBeneficiario().equals(nomeCliente)) {
                System.out.println("nome del cliente non corrisponde con il noominativo del beneficiario del voucher");
                return;
            }

            checkResponse = service.checkAndFreezeOperation(codVoucher);
            System.out.println(checkResponse);

            ConfirmResponse confirmResponse = service.confirmOperation(codVoucher, checkResponse.getImporto());
            System.out.println(confirmResponse);

        } catch (VoucherVerificationException | CertificateException e) {
            e.printStackTrace();
        }
    }
}
