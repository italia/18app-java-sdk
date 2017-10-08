

import it.mibact.bonus.verificavoucher.Check;
import it.mibact.bonus.verificavoucher.CheckRequestObj;
import it.mibact.bonus.verificavoucher.CheckResponse;
import it.mibact.bonus.verificavoucher.VerificaVoucher_Service;

import javax.xml.ws.soap.SOAPFaultException;
import java.security.NoSuchAlgorithmException;

public class TestCheck {


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
    public TestCheck(String code, String keyPath , String pass) {
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
        CheckRequestObj checkRequestObj = new CheckRequestObj();
        Check check = new Check();
        check.setCodiceVoucher(codVoucher);
        check.setTipoOperazione("1");
        checkRequestObj.setCheckReq(check);
        CheckResponse checkResp = null;
        try {
            checkResp = verificaVoucher_service.getVerificaVoucherSOAP().check(checkRequestObj).getCheckResp();
        } catch (SOAPFaultException failure) {


        }
    }

}
