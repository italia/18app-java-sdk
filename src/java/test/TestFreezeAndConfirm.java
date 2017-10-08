package test;

import it.mibact.bonus.verificavoucher.CheckResponse;
import it.mibact.bonus.verificavoucher.ConfirmResponse;
import lib.CertificateException;
import lib.MerchantService;
import lib.VoucherVerificationException;

public class TestFreezeAndConfirm {

    public static void main(String[] args) {

        String nomeCliente = "";
        String codVoucher = "";
        String keystorePath = "";
        String password = "";
        try {

            MerchantService service = new MerchantService(keystorePath, password);
            CheckResponse checkResponse = service.activateCertificate();
            // Non consuma il voucher e non scala l'importo dal borsellino
            // Adesso l'esercente è attivo

            if (checkResponse.getNominativoBeneficiario() != nomeCliente) {
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
