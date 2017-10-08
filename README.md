# 18app-java-sdk
Java SDK for developing applications which accept 18app vouchers (retails and stores)

Info about *18App*: [18app.italia.it](https://www.18app.italia.it)

## About
This sdk can be used by merchants to:

* Check the validity of a given vaucher
* Check the residual amount of money of a given vaucher
* Consume a given vaucher for a specific amount

The sdk has no external dependencies.

## Usage
These are the operations that can be done usign the sdk:

* Voucher check
To check the validity and the residual amount of a given voucher create a `VoucherService`, invoke the `check` method passing the voucher code and finally get the amount with `getImporto()`
```
    new VoucherService().check("XXXXXXXX").getImporto();
```

* Voucher consume
To consume a specific amount from a given voucher create a `VoucherService`, invoke the `confirm` method passing the voucher code and the amount and finally get the result of the operation with `getEsito()`
```
    new VoucherService().consume("XXXXXXXX", 0.5).getEsito()
```
* Voucher invalidate
To consume a specific amount from a given voucher create a `VoucherService`, invoke the `confirm` method passing the voucher code and the amount and finally get the result of the operation with `getEsito()`
```
    new VoucherService().invalidate("XXXXXXXX").getImporto()
```

## Debug
To use the sdk with the staging environment, you need to properly set the server certificate.
To do this you should:
* Get the server certificate from
    https://s3.eu-central-1.amazonaws.com/sklivvz-misc/VerificaVoucher.wsdl
* Generate the keystore from it, using keytool:
    keytool -import -keystore wstest18appitaliait.jks -file wstest18appitaliait.crt -keypass password -storepass password -alias trust
* Pass the following arguments to the JVM:
```
    -Djavax.net.debug=ssl
    -Djavax.net.ssl.keyStore=AAAAAA00H01H501P.p12
    -Djavax.net.ssl.keyStorePassword=m3D0T4aM
    -Djavax.net.ssl.keyStoreType=PKCS12
    -Djavax.net.ssl.trustStoreType=jks
    -Djavax.net.ssl.trustStore=wstest18appitaliait.jks
    -Djavax.net.ssl.trustStorePassword=123456
```
## Error codes
Those are the error codes returned by the server:
* `01`: Error in the input parameters, check and try again.
* `02`: The requested voucher is not available on the system. It could be alreadycollected or canceled.
* `03`: Impossible to activate the user. Please verify input parameters and that the userhas not been already activated.
* `04`: The amount claimed is greater than the amount of the selected voucher.
* `05`: User inactive, voucher impossible to verify.
* `06`: Category and type of this voucher are not aligned with category and typemanaged by the user.


## Test

TODO _¯\\_(ツ)_/¯_


