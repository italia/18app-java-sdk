# 18app-java-sdk
Java SDK for developing applications which accept 18app vouchers (retails and stores)
## Getting started
To use the voucher verification service, the merchant must have one authentication 
certificate to be passed to the library to authenticate.The service "18app" can only 
be used by merchant with access credentials to "Servizi Telematici dell’Agenzia delle 
Entrate". Merchants must therefore register to the web application in order to sell 
their products.
## SDK Tutorial
### Prerequisites
You must have the SSL .p12 certificate with the related password.
### Initialize the SDK
In order to use this SDK you must put this lines of code in your main, to be able to access
cacerts.
```java
    System.setProperty("javax.net.ssl.trustStore", "cacerts");
    System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
```
After that you need to add a Certificate with the proper object.
```java
   Certificate certificate = new Certificate.Builder()
                   .setKeystorePath("path")
                   .setPassword("password")
                   .build(); 
```
Once you created the certificate you can initiate the service.
```java
    EighteenAppMerchantService service = new EighteenAppMerchantService(certificate);
```
### SDK calls types
In order to use this SDK you must understand 3 different type of calls.
#### Check only voucher
This operation checks if a voucher exists and retrieve it's informations.
**You can add the partitaIva as optional parameter in the check object**
Code to use this:
```java
    // Initiation of the check request object
    Check check = new Check();
    // In order to check ONLY the voucher you need to specify the operationtype
    // to CHECK
    check.setTipoOperazione(CheckOperationType.CHECK);
    check.setCodiceVoucher("voucherCode");
    
    // Initiation of the check request object wrapper
    CheckRequestObj checkRequestObj = new CheckRequestObj();
    checkRequestObj.setCheckReq(check);
    
    // Make the call through the service
    service = new EighteenAppMerchantService(certificate);
            service.makeCheckRequest(checkRequestObj, new CheckResponseListener() {
                @Override
                public void onCheckSuccess(CheckResponse response) {
                    // Called when the response was successfull with all data
                }
    
                @Override
                public void onCheckError(FaultErrorCode errorCode) {
                    // Called when you got some errors from the server
                }
    
                @Override
                public void onCheckRequestFailed(EighteenAppMerchantService.RequestFailType failType) {
                    // Called when there is a problem sending the request
                }
    
                @Override
                public void onCheckResponseFailed(EighteenAppMerchantService.ResponseFailType failType) {
                    // Called when there is a problem on the response
                }
            });
```
#### Check and pay voucher
This operation checks if the voucher exists and removes from it all the import. 
**You can add the partitaIva as optional parameter in the check object**
Code to use this:
```java
    // Initiation of the check request object
    Check check = new Check();
    // In order to check and pay the voucher you need to specify the operationtype
    // to CHECK_AND_PAY
    check.setTipoOperazione(CheckOperationType.CHECK_AND_PAY);
    check.setCodiceVoucher("voucherCode");
    
    // Initiation of the check request object wrapper
    CheckRequestObj checkRequestObj = new CheckRequestObj();
    checkRequestObj.setCheckReq(check);
    
    // Make the call through the service
    service = new EighteenAppMerchantService(certificate);
            service.makeCheckRequest(checkRequestObj, new CheckResponseListener() {
                @Override
                public void onCheckSuccess(CheckResponse response) {
                    // Called when the response was successfull with all data
                }
    
                @Override
                public void onCheckError(FaultErrorCode errorCode) {
                    // Called when you got some errors from the server
                }
    
                @Override
                public void onCheckRequestFailed(EighteenAppMerchantService.RequestFailType failType) {
                    // Called when there is a problem sending the request
                }
    
                @Override
                public void onCheckResponseFailed(EighteenAppMerchantService.ResponseFailType failType) {
                    // Called when there is a problem on the response
                }
            });
```
#### Confirm
This action is similar to check and pay, but if you have a voucher that has 10$ on it and you
pay an item that costs 5$, on your profile the difference will be refunded.
Code to use this:
```java
   // Initiation of the confirm request object
        Confirm confirm = new Confirm();
           confirm.setTipoOperazione(ConfirmOperationType.CONFIRM);
           confirm.setCodiceVoucher(vouchers[voucherIndex]);
           confirm.setImporto(amount);
           // Initiation of the confirm request object wrapper
           ConfirmRequestObj confirmRequestObj = new ConfirmRequestObj();
           confirmRequestObj.setCheckReq(confirm);
           // Initiation of the service
           EighteenAppMerchantService service = new EighteenAppMerchantService(certificate);
           service.makeConfirmRequest(confirmRequestObj, new ConfirmResponseListener() {
               @Override
               public void onConfirmSuccess(boolean isOperationResultOk) {
                   // Called when the response was successfull and gives the result
               }
   
               @Override
               public void onConfirmError(FaultErrorCode errorCode) {
                   // Called when you got some errors from the server
               }
   
               @Override
               public void onConfirmRequestFailed(EighteenAppMerchantService.RequestFailType failType) {
                   // Called when there is a problem sending the request
               }
   
               @Override
               public void onConfirmResponseFailed(EighteenAppMerchantService.ResponseFailType failType) {
                   // Called when there is a problem on the response
               }
           }); 
```
