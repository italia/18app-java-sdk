# 18app-java-sdk
Java SDK for developing applications which accept 18app vouchers (retails and stores)

## Getting Started

To use the voucher verification service, the merchant must have one
authentication certificate to be passed to the library to authenticate.The service
"18app" can only be used by merchant with access credentials to "Servizi Telematici dell’Agenzia delle Entrate".
Merchants must therefore register to the web application in order to sell their products.

### Prerequisites

This certificate X509 will be generated and downloadable in .cer format directly via
the dedicated web application for traders, in an authenticated area.

### How to use it
Initialize the service with your merchant certificate (currently in format PKCS12) and its password.
Activate the certificate and verify that partitaIva code matches the user's one.
```
    MerchantService service = new MerchantService("\path\to\merchant\certificate.p12","certificate_password");
    String partitaIva = service.activateCertificate();
    // Check partitaIva
```

##### CheckOnly Operation. Pass customer voucher code and get name of the customer. Use it to check the customer personal details
```
    try {       
        CheckResponse response = service.checkOnlyOperation(voucherCode);
        
        // Check personal details
        String customerName = response.getNominativoBeneficiario();
        
    } catch (CertificateException e){
        // Problems with web service certificate
    } catch (VoucherVerificationException vve){
        if (vve.getId().equals(FaultCodes.WRONG_PARAMETERS)) {
            // Handle wrong parameters
        } else {
            // Handle other FaultCodes ...
        }
        
    }
```
#### Pre-Check and deferred Confirm for goods availability check
##### CheckAndFreeze Operation: freezes the voucher as long as necessary to carry out an availability check in stock or for other specific situations
```
    CheckResponse response = service.checkAndFreezeOperation(voucherCode);
    
    // e.g. Goods availability Check
    
    double requiredAmount = getRequiredAmount();
    ConfirmResponse confirmResponse = service.confirmOperation(codVoucher, requiredAmount);
   
```  
##### Confirm Operation. To be called after CheckAndFreeze to confirm the consumption of all the voucher amount or only a part of the amount

```
    ConfirmResponse response = service.confirmOperation(codVoucher, requiredAmount);
    if(response.getEsito().equals(MerchantService.SUCCESS_CONFIRMATION)) {
        // Success
    } else {
        // Failure
    }
    
```

##### CheckAndConsume Operation: issues a check and consume operation (spending the actual voucher)
```
    service.checkAndConsumeOperation(codVoucher);
``` 

## Running the tests

Run MerchantServiceTest.java with the available test cases.

Please note that the library is configured for the [test endpoint](https://wstest.18app.italia.it/VerificaVoucherWEB/VerificaVoucher).
The server certificate is self-signed so we have provided a keystore (cacerts) with the certificate already 
added as a trusted one.
## Contributing

List of Contributors who participated in this project.
* **Agosti Daniele**
* **Baglini Lorenzo**
* **Di Rienzo Francesco**
* **Rossi Lorenzo**

## Versioning

1.0

## License

## Acknowledgments


