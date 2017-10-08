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
    MerchantService service = new MerchantService("\path\to\merchant\certificate.p12","certificate_password")
    String partitaIva = service.activateCertificate();
```

##### Check Operations. Pass customer voucher code
```
    try {       
        service.checkOnlyOperation(voucherCode);
    } catch (CertificateException e){
        // Problems with web service certificate
    } catch (VoucherVerificationException vve){
        if (vve.getId() == FaultCodes.WRONG_PARAMETERS) {
            // Handle wrong parameters
        } else {
            // Handle other FaultCodes ...
        }
        
    }
```

CheckAndFreeze Operation: freezes the voucher as long as necessary to carry out an availability check in stock or for
     other specific situations
```
    service.checkAndFreeze();
```  
Confirm Operation. To be called after CheckAndFreeze to confirm the consumption of all the voucher 
amount or only a part of the amount

```
    service.confirm();
```

CheckAndConsume Operation: issues a check and consume operation (spending the actual voucher)
```
    service.checkAndConsume();
``` 
CheckOnly Operation: issues only the check of the voucher
```
    service.checkOnly();
``` 


### Installing

A step by step series of examples that tell you have to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

List of Contributors who participated in this project.
* **Agosti Daniele**
* **Baglini Lorenzo**
* **Di Rienzo Francesco**
* **Rossi Lorenzo**

## Versioning


## License

## Acknowledgments


