package it.app18.verificavoucher;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;


public class SSLSetup {

    public static void init(String clientKeyPath, String clientKeyPassword, String trustStorePath,
                            String trustStorePassword) {
        try {
            // Carico il certificato client
            KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            try (FileInputStream fileInputStream = new FileInputStream(clientKeyPath)) {
                keyStore.load(fileInputStream, clientKeyPassword.toCharArray());
            }
            factory.init(keyStore, clientKeyPassword.toCharArray());

            // Carico il trust store per verificare il certificato server nelle sessioni SSL
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore ts = KeyStore.getInstance("JKS");
            ts.load(Files.newInputStream(Paths.get(trustStorePath)), trustStorePassword.toCharArray());
            instance.init(ts);

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(factory.getKeyManagers(), instance.getTrustManagers(), new java.security.SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}