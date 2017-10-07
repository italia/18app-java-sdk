package test;

import com.sun.deploy.net.HttpResponse;
import it.mibact.bonus.verificavoucher.*;
import sun.net.www.http.HttpClient;

import javax.net.ssl.SSLContext;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

public class TestHub {


    public static void main(String[] args) throws NoSuchAlgorithmException {

        System.setProperty("javax.net.ssl.trustStore", "cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        VerificaVoucher_Service verificaVoucher_service = new VerificaVoucher_Service();

        // Register handler for fault
        verificaVoucher_service.setHandlerResolver(portInfo -> {
            List<Handler> handlerChain = new ArrayList<>();
            handlerChain.add(new FaultHandler());
            return handlerChain;
        });

        CheckRequestObj checkRequestObj = new CheckRequestObj();
        Check check = new Check();
        check.setCodiceVoucher("jJmyKTKL");
        check.setTipoOperazione("1");
        checkRequestObj.setCheckReq(check);
        CheckResponse checkResp = verificaVoucher_service.getVerificaVoucherSOAP().check(checkRequestObj).getCheckResp();


        System.out.println(checkResp);

    }

}
