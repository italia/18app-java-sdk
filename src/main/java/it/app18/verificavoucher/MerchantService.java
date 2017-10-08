package it.app18.verificavoucher;

import com.sun.xml.internal.ws.client.ClientTransportException;
import it.mibact.bonus.verificavoucher.*;
import it.app18.verificavoucher.model.CheckOperation;
import it.app18.verificavoucher.model.ConfirmOperation;

import javax.xml.ws.soap.SOAPFaultException;

/**
 * Library Interface
 */
public class MerchantService {

    // Constants
    private final static boolean DEBUG_MODE = true;
    private final static String ACTIVATION_VOUCHER_CODE = "11aa22bb";

    // Fault Codes
    public class FaultCodes {

        public final static String WRONG_PARAMETERS = "01";
        public final static String VOUCHER_NOT_FOUND = "02";
        public final static String FAILED_ACTIVATION_USER = "03";
        public final static String WRONG_AMOUNT = "04";
        public final static String NOT_ACTIVE_USER = "05";
        public final static String WRONG_CATEGORY = "06";
        public final static String UNKNOWN_FAULT = "00";

    }

    // Internal WS Client
    private VerificaVoucher_Service service;

    /**
     *
     * @param keystorePath to the client certificate.
     * @param password which belongs to the keystore.
     */
    public MerchantService(String keystorePath, String password) throws VoucherVerificationException, CertificateException {

        if (DEBUG_MODE){
            // Accept self-signed certificate of the testing server
            // You need to put the server self-signed certificate into the file cacerts
            System.setProperty("javax.net.ssl.trustStore", "cacerts");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        }

        service = new VerificaVoucher_Service(keystorePath, password);
    }

    /**
     * Method which issues a Check operation.
     * @param op type of operation requested.
     * @param codVoucher voucher code of the coupon.
     * @param partitaIva optional.
     * @return
     */
    private CheckResponse checkOperation(CheckOperation op, String codVoucher, String partitaIva) throws VoucherVerificationException, CertificateException {

        Check check = new Check();
        check.setTipoOperazione(op.getType());
        check.setCodiceVoucher(codVoucher);
        if (partitaIva != null)
            check.setPartitaIvaEsercente(partitaIva);

        CheckRequestObj checkRequestObj = new CheckRequestObj();
        checkRequestObj.setCheckReq(check);
        try {

            return service.getVerificaVoucherSOAP().check(checkRequestObj).getCheckResp();
        } catch (SOAPFaultException failure){

            handleFault(failure);
            return null;
        } catch (ClientTransportException e){

            throw new CertificateException("Problems with the 18App VerificaVoucher Web Service Certificate," +
                    " please contact the administrator");
        }

    }

    private void handleFault(SOAPFaultException failure) throws VoucherVerificationException {

        String code = failure.getFault().getDetail().getFirstChild().getFirstChild().getFirstChild().getTextContent();
        System.out.println("failure code() = " + code);

        String data =
                failure.getFault().getDetail().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getTextContent();
        System.out.println("failure data = " + data);

        switch (code){
            case FaultCodes.WRONG_PARAMETERS:
                throw new VoucherVerificationException(FaultCodes.WRONG_PARAMETERS,"Error in the input parameters, check and try again");
            case FaultCodes.VOUCHER_NOT_FOUND:
                throw new VoucherVerificationException(FaultCodes.VOUCHER_NOT_FOUND,"The requested voucher is not available on the system. It could be already\n" +
                        "collected or canceled");
            case FaultCodes.NOT_ACTIVE_USER:
                throw new VoucherVerificationException(FaultCodes.NOT_ACTIVE_USER,"User inactive, voucher impossible to verify");
            case FaultCodes.FAILED_ACTIVATION_USER:
                throw new VoucherVerificationException(FaultCodes.FAILED_ACTIVATION_USER,"Impossible to activate the user. Please verify input parameters and that the user\n" +
                        "has not been already activated.");
            case FaultCodes.WRONG_AMOUNT:
                throw new VoucherVerificationException(FaultCodes.WRONG_AMOUNT,"The amount claimed is greater than the amount of the selected voucher");
            case FaultCodes.WRONG_CATEGORY:
                throw new VoucherVerificationException(FaultCodes.WRONG_CATEGORY,"Category and type of this voucher are not aligned with category and type managed by the user.");
            default:
                throw new VoucherVerificationException(FaultCodes.UNKNOWN_FAULT,"Unknown fault");


        }
    }


    /**
     * Overloading method of {@link #checkOperation(CheckOperation, String, String)}
     * @param op
     * @param codVoucher
     * @return
     */
    private CheckResponse checkOperation(CheckOperation op, String codVoucher) throws VoucherVerificationException, CertificateException {
        return checkOperation(op, codVoucher, null);
    }

    /**
     * Method which issues a Confirm operation.
     * @param op type of operation requested.
     * @param codVoucher voucher code of the coupon.
     * @param importo amount confirmed by the operator.
     * @return
     */
    private ConfirmResponse confirmOperation(ConfirmOperation op, String codVoucher, double importo) throws VoucherVerificationException {

        Confirm confirm = new Confirm();
        confirm.setTipoOperazione(op.getType());
        confirm.setCodiceVoucher(codVoucher);
        confirm.setImporto(importo);

        ConfirmRequestObj confirmRequestObj = new ConfirmRequestObj();
        confirmRequestObj.setCheckReq(confirm);

        try {

            return service.getVerificaVoucherSOAP().confirm(confirmRequestObj).getCheckResp();

        } catch (SOAPFaultException failure){
            handleFault(failure);
            return null;
        }
    }

    /**
     * Method which issue a check only operation (without spending the actual voucher).
     * @param codVoucher voucher code of the coupon.
     * @param partitaIva optional.
     * @return CheckResponse data structure filled with values
     * @throws VoucherVerificationException
     */
    public CheckResponse checkOnlyOperation(String codVoucher, String partitaIva) throws VoucherVerificationException, CertificateException {
        return checkOperation(CheckOperation.CHECK_ONLY_VOUCHER, codVoucher, partitaIva);
    }

    /**
     * Overload method of {@link #checkOperation(CheckOperation, String)}
     * @param codVoucher voucher code of the coupon.
     * @return CheckResponse data structure filled with values
     * @throws VoucherVerificationException
     */
    public CheckResponse checkOnlyOperation(String codVoucher) throws VoucherVerificationException, CertificateException {
        return checkOperation(CheckOperation.CHECK_ONLY_VOUCHER, codVoucher);
    }

    /**
     * Method which issue a check and consume operation
     * (spending the actual voucher).
     * @param codVoucher voucher code of the coupon.
     * @param partitaIva optional.
     * @return CheckResponse data structure filled with values
     * @throws VoucherVerificationException
     */
    public CheckResponse checkAndConsumeOperation(String codVoucher, String partitaIva) throws VoucherVerificationException, CertificateException {
        return checkOperation(CheckOperation.CHECK_CONSUME_VOUCHER, codVoucher, partitaIva);
    }

    public CheckResponse checkAndConsume(String codVoucher) throws CertificateException, VoucherVerificationException {

        return checkOperation(CheckOperation.CHECK_CONSUME_VOUCHER, codVoucher);
    }

    /**
     * Overloading method of {@link #checkAndConsumeOperation(String, String)}
     * @param codVoucher voucher code of the coupon.
     * @return CheckResponse data structure filled with values
     * @throws VoucherVerificationException
     */
    public CheckResponse checkAndConsumeOperation(String codVoucher) throws VoucherVerificationException, CertificateException {
        return checkOperation(CheckOperation.CHECK_CONSUME_VOUCHER, codVoucher);
    }

    /**
     * Method which issue a check and freeze operation
     * (freezing it for as long as necessary to carry out an availability check in stock or for
     * other specific situations).
     * @param codVoucher voucher code of the coupon.
     * @param partitaIva optional.
     * @return CheckResponse data structure filled with values
     * @throws VoucherVerificationException
     */
    public CheckResponse checkAndFreezeOperation(String codVoucher, String partitaIva) throws VoucherVerificationException, CertificateException {
        return checkOperation(CheckOperation.CHECK_FREEZE_VOUCHER, codVoucher, partitaIva);
    }

    /**
     * Overloading methood of {@link #checkAndFreezeOperation(String, String)}
     * @param codVoucher voucher code of the coupon.
     * @return CheckResponse data structure filled with values
     * @throws VoucherVerificationException
     */
    public CheckResponse checkAndFreezeOperation(String codVoucher) throws VoucherVerificationException, CertificateException {
        return checkOperation(CheckOperation.CHECK_FREEZE_VOUCHER, codVoucher);
    }

    /**
     * Method which issues a Confirm operation.
     * @param codVoucher voucher code of the coupon.
     * @param importo amount confirmed by the operator.
     * @return
     */
    public ConfirmResponse confirmOperation(String codVoucher, double importo) throws VoucherVerificationException {
        return confirmOperation(ConfirmOperation.CONFIRM, codVoucher, importo);
    }

    /**
     * Always activate certificate before using VerificaVoucher service.
     * We assume activation is an idem-potent operation.
     * activateCertificate();
     * Activate the merchant certificate using the protocol
     * (use Check Operation with following inputs -> type = 1, VoucherCode = 11aa22bb)
     * See https://www.18app.italia.it/static/Linee%20Guida%20Esercenti.pdf
     * @return partitaIva of the merchant
     */
    public String activateCertificate() throws VoucherVerificationException, CertificateException {

        CheckResponse checkResponse = checkOnlyOperation(ACTIVATION_VOUCHER_CODE);
        System.out.println(checkResponse.toString());
        return checkResponse.getPartitaIvaEsercente();

    }

    public static void main(String[] args) throws VoucherVerificationException {

        /*System.setProperty("javax.net.ssl.trustStore", "cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");*/


        MerchantService merchantService = null;
        try {
            merchantService = new MerchantService("AAAAAA00H01H501P.p12", "m3D0T4aM");
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        /*
        try {
            merchantService.confirmOperation(ConfirmOperation.CONFIRM, "ORTEV4F0", 600000);

        } catch (VoucherVerificationException e) {
            e.printStackTrace();
        }

        try {
            merchantService.checkAndFreezeOperation("NQSvkHaZ");
        } catch (VoucherVerificationException e) {
            e.printStackTrace();
        }
        */

        try {
            merchantService.checkOnlyOperation("51YX0nbE");
        } catch (VoucherVerificationException | CertificateException e) {
            e.printStackTrace();
        }

    }
}
