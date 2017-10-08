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

To check the validity and the residual amount of a given voucher create a `VoucherChecker`, invoke the `check` method passing the voucher code and finally get the amount with `getImporto()`
    new VoucherChecker().check("XXX").getImporto();

To consume a specific amount from a given voucher create a `VoucherConfirmer`, invoke the `confirm` method passing the voucher code and the amount and finally get the result of the operation with `getEsito()`
    new VoucherConfirmer().consume("y75Wx7xj", 0.5).getEsito()

### Debug
To use the sdk with the staging environment, you need to properly set the server certificate.
To do this you should:
* Get the server certificate from
    https://s3.eu-central-1.amazonaws.com/sklivvz-misc/VerificaVoucher.wsdl
* Generate the keystore from it, using keytool:
    keytool -import -keystore wstest18appitaliait.jks -file wstest18appitaliait.crt -keypass password -storepass password -alias trust
* Pass the following arguments to the JVM:
    -Djavax.net.debug=ssl
    -Djavax.net.ssl.keyStore=AAAAAA00H01H501P.p12
    -Djavax.net.ssl.keyStorePassword=m3D0T4aM
    -Djavax.net.ssl.keyStoreType=PKCS12
    -Djavax.net.ssl.trustStoreType=jks
    -Djavax.net.ssl.trustStore=wstest18appitaliait.jks
    -Djavax.net.ssl.trustStorePassword=123456


## Test

_¯\\_(ツ)_/¯_ for now


