package test;

import com.sun.org.apache.xerces.internal.dom.ElementImpl;
import com.sun.org.apache.xerces.internal.dom.TextImpl;
import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Detail1_1Impl;
import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Fault1_1Impl;
import it.mibact.bonus.verificavoucher.Check;
import it.mibact.bonus.verificavoucher.CheckRequestObj;
import it.mibact.bonus.verificavoucher.CheckResponse;
import it.mibact.bonus.verificavoucher.VerificaVoucher_Service;

import javax.xml.ws.soap.SOAPFaultException;
import java.security.NoSuchAlgorithmException;

public class TestCheck {


    public static void main(String[] args) throws NoSuchAlgorithmException {

        System.setProperty("javax.net.ssl.trustStore", "cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        VerificaVoucher_Service verificaVoucher_service = new VerificaVoucher_Service("AAAAAA00H01H501P.p12", "m3D0T4aM");
        CheckRequestObj checkRequestObj = new CheckRequestObj();
        Check check = new Check();
        check.setCodiceVoucher("2a75f266");
        check.setTipoOperazione("1");
        checkRequestObj.setCheckReq(check);
        CheckResponse checkResp = null;
        try {
            checkResp = verificaVoucher_service.getVerificaVoucherSOAP().check(checkRequestObj).getCheckResp();
        } catch (SOAPFaultException failure) {
            // TODO: 07/10/17 Navigare il DOM alla ricerca del codice di errore.
            System.out.println("Naviga dom");

            String code = failure.getFault().getDetail().getFirstChild().getFirstChild().getFirstChild().getTextContent();
            System.out.println("failure code() = " + code);

            String data =
                    failure.getFault().getDetail().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getTextContent();
            System.out.println("failure data = " + data);


        }


    }

}
