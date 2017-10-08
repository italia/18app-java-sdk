

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

    private static String codVoucher ;
    private static String keystorePath ;
    private static String password ;
    private static boolean DEBUG_MODE = true;

    public static boolean isDebugMode() {
        return DEBUG_MODE;
    }

    public static void setDebugMode(boolean debugMode) {
        DEBUG_MODE = debugMode;
    }

    public TestConfirm(String code, String keyPath , String pass) {
        codVoucher = code;
        keystorePath = keyPath;
        password = pass;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        if (DEBUG_MODE){
            // Accept self-signed certificate of the testing server
            // You need to put the server self-signed certificate into the file cacerts
            System.setProperty("javax.net.ssl.trustStore", "cacerts");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        }
        testMe();
    }

    public static void testMe(){

        VerificaVoucher_Service verificaVoucher_service = new VerificaVoucher_Service(keystorePath, password);
        ConfirmRequestObj confirmRequestObj = new ConfirmRequestObj();
        Confirm confirm = new Confirm();
        confirm.setCodiceVoucher(codVoucher);
        confirm.setTipoOperazione("1");
        confirm.setImporto(12);
        confirmRequestObj.setCheckReq(confirm);
        ConfirmResponse confirmResponse = null;

        try {

            confirmResponse = verificaVoucher_service.getVerificaVoucherSOAP().confirm(confirmRequestObj).getCheckResp();
            System.out.println(confirmResponse);
        } catch (SOAPFaultException failure) {

        }



    }

}
