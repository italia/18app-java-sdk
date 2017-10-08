package test;

import it.mibact.bonus.verificavoucher.Confirm;
import it.mibact.bonus.verificavoucher.ConfirmRequestObj;
import it.mibact.bonus.verificavoucher.ConfirmResponse;
import it.mibact.bonus.verificavoucher.VerificaVoucher_Service;

import javax.xml.ws.soap.SOAPFaultException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by daniele on 07/10/17.
 */
public class TestConfirm {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        System.setProperty("javax.net.ssl.trustStore", "cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        VerificaVoucher_Service verificaVoucher_service = new VerificaVoucher_Service("AAAAAA00H01H501P.p12", "m3D0T4aM");
        ConfirmRequestObj confirmRequestObj = new ConfirmRequestObj();
        Confirm confirm = new Confirm();
        confirm.setCodiceVoucher("GLrkulZT");
        confirm.setTipoOperazione("1");
        confirm.setImporto(12);
        confirmRequestObj.setCheckReq(confirm);
        ConfirmResponse confirmResponse = null;
        try {
            confirmResponse = verificaVoucher_service.getVerificaVoucherSOAP().confirm(confirmRequestObj).getCheckResp();
        } catch (SOAPFaultException failure) {

        }

        System.out.println(confirmResponse);

    }


}
