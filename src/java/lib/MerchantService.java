package lib;

import it.mibact.bonus.verificavoucher.*;
import lib.model.CheckOperation;
import lib.model.ConfirmOperation;

import javax.xml.ws.soap.SOAPFaultException;

/**
 * Library Interface
 */
public class MerchantService {

    private String keystorePath;
    private String password;
    // FIXME: 07/10/2017 public for sdk user
    private final static String WRONG_PARAMETERS = "01";
    private final static String VOUCHER_NOT_FOUND = "02";
    private final static String FAILED_ACTIVATION_USER = "03";
    private final static String WRONG_AMOUNT = "04";
    private final static String NOT_ACTIVE_USER = "05";
    private final static String WRONG_CATEGORY = "06";
    private final static String UNKNOWN_FAULT ="00";
    private VerificaVoucher_Service service;

    /**
     *
     * @param keystorePath to the client certificate.
     * @param password which belongs to the keystore.
     */
    public MerchantService(String keystorePath, String password) {
        this.keystorePath = keystorePath;
        this.password = password;
        service = new VerificaVoucher_Service(keystorePath, password);
    }

    /**
     * Method which issues a Check operation.
     * @param op type of operation requested.
     * @param codVoucher voucher code of the coupon.
     * @param partitaIva optional.
     * @return
     */
    private CheckResponse checkOperation(CheckOperation op, String codVoucher, String partitaIva) throws CheckRequestException {

        Check check = new Check();
        check.setTipoOperazione(op.getType());
        check.setCodiceVoucher(codVoucher);
        if (partitaIva != null)
            check.setPartitaIvaEsercente(partitaIva);

        CheckRequestObj checkRequestObj = new CheckRequestObj();
        checkRequestObj.setCheckReq(check);
        try {
            return service.getVerificaVoucherSOAP().check(checkRequestObj).getCheckResp();
        }catch (SOAPFaultException failure){
            handleFault(failure);
            return null;
        }

    }

    private void handleFault(SOAPFaultException failure) throws CheckRequestException {
        // TODO: 07/10/17 Navigare il DOM alla ricerca del codice di errore.
        System.out.println("Naviga dom");

        String code = failure.getFault().getDetail().getFirstChild().getFirstChild().getFirstChild().getTextContent();
        System.out.println("failure code() = " + code);

        String data =
                failure.getFault().getDetail().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getTextContent();
        System.out.println("failure data = " + data);

        switch (code){
            case WRONG_PARAMETERS:
                throw new CheckRequestException(WRONG_PARAMETERS,"Error in the input parameters, check and try again");
            case VOUCHER_NOT_FOUND:
                throw new CheckRequestException(VOUCHER_NOT_FOUND,"The requested voucher is not available on the system. It could be already\n" +
                        "collected or canceled");
            case NOT_ACTIVE_USER:
                throw new CheckRequestException(NOT_ACTIVE_USER,"User inactive, voucher impossible to verify");
            case FAILED_ACTIVATION_USER:
                throw new CheckRequestException(FAILED_ACTIVATION_USER,"Impossible to activate the user. Please verify input parameters and that the user\n" +
                        "has not been already activated.");
            case WRONG_AMOUNT:
                throw new CheckRequestException(WRONG_AMOUNT,"The amount claimed is greater than the amount of the selected voucher");
            case WRONG_CATEGORY:
                throw new CheckRequestException(WRONG_CATEGORY,"Category and type of this voucher are not aligned with category and type managed by the user.");
            default:
                throw new CheckRequestException(UNKNOWN_FAULT,"Unknown fault");


        }
    }


    /**
     * Overloading method of {@link #checkOperation(CheckOperation, String, String)}
     * @param op
     * @param codVoucher
     * @return
     */
    private CheckResponse checkOperation(CheckOperation op, String codVoucher) throws CheckRequestException {
        return checkOperation(op, codVoucher, null);
    }

    /**
     * Method which issue a check only operation (without spending the actual voucher).
     * @param codVoucher voucher code of the coupon.
     * @param partitaIva optional.
     * @return CheckResponse data structure filled with values
     * @throws SOAPFaultException
     */
    public CheckResponse checkOnlyOperation(String codVoucher, String partitaIva) throws CheckRequestException {
        return checkOperation(CheckOperation.CHECK_ONLY_VOUCHER, codVoucher, partitaIva);
    }

    /**
     * Overload method of {@link #checkOperation(CheckOperation, String)}
     * @param codVoucher voucher code of the coupon.
     * @return CheckResponse data structure filled with values
     * @throws SOAPFaultException
     */
    public CheckResponse checkOnlyOperation(String codVoucher) throws CheckRequestException {
        return checkOperation(CheckOperation.CHECK_ONLY_VOUCHER, codVoucher);
    }

    /**
     * Method which issue a check and consume operation
     * (spending the actual voucher).
     * @param codVoucher voucher code of the coupon.
     * @param partitaIva optional.
     * @return CheckResponse data structure filled with values
     * @throws SOAPFaultException
     */
    public CheckResponse checkAndConsumeOperation(String codVoucher, String partitaIva) throws CheckRequestException {
        return checkOperation(CheckOperation.CHECK_CONSUME_VOUCHER, codVoucher, partitaIva);
    }

    /**
     * Overloading method of {@link #checkAndConsumeOperation(String, String)}
     * @param codVoucher voucher code of the coupon.
     * @return CheckResponse data structure filled with values
     * @throws SOAPFaultException
     */
    public CheckResponse checkAndConsumeOperation(String codVoucher) throws CheckRequestException {
        return checkOperation(CheckOperation.CHECK_CONSUME_VOUCHER, codVoucher);
    }

    /**
     * Method which issue a check and freeze operation
     * (freezing it for as long as necessary to carry out an availability check in stock or for
     * other specific situations).
     * @param codVoucher voucher code of the coupon.
     * @param partitaIva optional.
     * @return CheckResponse data structure filled with values
     * @throws SOAPFaultException
     */
    public CheckResponse checkAndFreezeOperation(String codVoucher, String partitaIva) throws CheckRequestException {
        return checkOperation(CheckOperation.CHECK_FREEZE_VOUCHER, codVoucher, partitaIva);
    }

    /**
     * Overloading methood of {@link #checkAndFreezeOperation(String, String)}
     * @param codVoucher voucher code of the coupon.
     * @return CheckResponse data structure filled with values
     * @throws SOAPFaultException
     */
    public CheckResponse checkAndFreezeOperation(String codVoucher) throws CheckRequestException {
        return checkOperation(CheckOperation.CHECK_FREEZE_VOUCHER, codVoucher);
    }

    /**
     * Method which issues a Confirm operation.
     * @param op type of operation requested.
     * @param codVoucher voucher code of the coupon.
     * @param importo amount confirmed by the operator.
     * @return
     */
    private ConfirmResponse confirmOperation(ConfirmOperation op, String codVoucher, double importo) throws SOAPFaultException {

        return null;
    }

    public static void main(String[] args) {

        System.setProperty("javax.net.ssl.trustStore", "cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        MerchantService merchantService = new MerchantService("AAAAAA00H01H501P.p12", "m3D0T4aM");

        try {
            merchantService.checkAndConsumeOperation("tEWY2vxG");

        } catch (CheckRequestException e) {
            e.printStackTrace();
        }

        try {
            merchantService.checkAndFreezeOperation("NQSvkHaZ");
        } catch (CheckRequestException e) {
            e.printStackTrace();
        }

        try {
            merchantService.checkOnlyOperation("51YX0nbE");
        } catch (CheckRequestException e) {
            e.printStackTrace();
        }

    }
}
