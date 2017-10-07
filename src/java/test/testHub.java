package test;

import com.sun.deploy.net.HttpResponse;
import it.mibact.bonus.verificavoucher.Check;
import it.mibact.bonus.verificavoucher.CheckRequestObj;
import it.mibact.bonus.verificavoucher.CheckResponseObj;
import it.mibact.bonus.verificavoucher.VerificaVoucher_Service;
import sun.net.www.http.HttpClient;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class testHub {


    public static void main(String[] args) throws NoSuchAlgorithmException {


        VerificaVoucher_Service verificaVoucher_service = new VerificaVoucher_Service();
        CheckResponseObj check = verificaVoucher_service.getVerificaVoucherSOAP().check(new CheckRequestObj());

    }

}
