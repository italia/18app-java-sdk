package test;

import com.sun.deploy.net.HttpResponse;
import it.mibact.bonus.verificavoucher.*;
import sun.net.www.http.HttpClient;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class TestHub {


    public static void main(String[] args) throws NoSuchAlgorithmException {

        System.setProperty("javax.net.ssl.trustStore", "cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        VerificaVoucher_Service verificaVoucher_service = new VerificaVoucher_Service();
        CheckRequestObj checkRequestObj = new CheckRequestObj();
        Check check = new Check();
        check.setCodiceVoucher("1");
        check.setPartitaIvaEsercente("1");
        check.setTipoOperazione("1");
        checkRequestObj.setCheckReq(check);
        CheckResponse checkResp = verificaVoucher_service.getVerificaVoucherSOAP().check(checkRequestObj).getCheckResp();


        System.out.println(checkResp);

    }

}
