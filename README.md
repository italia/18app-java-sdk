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
A certificate in PKCS12 format is provided for testing purpoeses.
### How to use it
Initialize the MerchantService and after certificate activation, verify that partitaIva code matches the user's one.
```
    MerchantService service = new MerchantService();
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

Run MerchantServiceTest.java with the available test cases running the command:
```
mvn test
```

Please note that the library is configured for the [test endpoint](https://wstest.18app.italia.it/VerificaVoucherWEB/VerificaVoucher).

## Contributing

List of Contributors who participated in this project.
* **Agosti Daniele**
* **Baglini Lorenzo**
* **Di Rienzo Francesco**
* **Rossi Lorenzo**

## Versioning

1.0.0

## License

## Acknowledgments


