package it.app18.verificavoucher;

import it.app18.verificavoucher.model.CheckOperation;
import it.app18.verificavoucher.model.ConfirmOperation;
import it.mibact.bonus.verificavoucher.*;

import javax.xml.ws.soap.SOAPFaultException;

/**
 * Library Interface
 */
public class MerchantService {

    // Constants
    private final static String ACTIVATION_VOUCHER_CODE = "11aa22bb";

    // Confirmation Codes
    public static final String SUCCESS_CONFIRMATION = "OK";
    public static final String FAILED_CONFIRMATION = "KO";

    // Internal WS Client
    private final VerificaVoucher_Service service;

    /**
     *
     */
    public MerchantService(String clientKeyPath, String clientKeyPass, String trustStorePath,
                           String trustStorePassword) {
        SSLSetup.init(clientKeyPath, clientKeyPass, trustStorePath, trustStorePassword);
        service = new VerificaVoucher_Service();
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

        } catch (SOAPFaultException failure) {
            throw handleFault(failure);
        } catch (Exception e) {
            throw new CertificateException("Problems with the 18App VerificaVoucher Web Service," +
                    " please contact the administrator. Error message: \n" + e.getMessage());
        }

    }

    /**
     * Method which maps generic {@link SOAPFaultException} to {@link VoucherVerificationException}.
     * @param failure {@link SOAPFaultException}
     * @return VoucherVerificationException
     */
    private VoucherVerificationException handleFault(SOAPFaultException failure) {

        String code, data;
        if (failure.getFault().getDetail() != null) {
            code = failure.getFault().getDetail().getFirstChild().getFirstChild().getFirstChild().getTextContent();
            data = failure.getFault().getDetail().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getTextContent();
        } else {
            code = FaultCodes.UNKNOWN_FAULT;
            data = failure.getLocalizedMessage();
        }


        switch (code){
            case FaultCodes.WRONG_PARAMETERS:
                return new VoucherVerificationException(FaultCodes.WRONG_PARAMETERS,"Error in the input parameters, check and try again");
            case FaultCodes.VOUCHER_NOT_FOUND:
                return new VoucherVerificationException(FaultCodes.VOUCHER_NOT_FOUND,"The requested voucher is not available on the system. It could be already\n" +
                        "collected or canceled");
            case FaultCodes.NOT_ACTIVE_USER:
                return new VoucherVerificationException(FaultCodes.NOT_ACTIVE_USER,"User inactive, voucher impossible to verify");
            case FaultCodes.FAILED_ACTIVATION_USER:
                return new VoucherVerificationException(FaultCodes.FAILED_ACTIVATION_USER,"Impossible to activate the user. Please verify input parameters and that the user\n" +
                        "has not been already activated.");
            case FaultCodes.WRONG_AMOUNT:
                return new VoucherVerificationException(FaultCodes.WRONG_AMOUNT,"The amount claimed is greater than the amount of the selected voucher");
            case FaultCodes.WRONG_CATEGORY:
                return new VoucherVerificationException(FaultCodes.WRONG_CATEGORY,"Category and type of this voucher are not aligned with category and type managed by the user.");
            default:
                return new VoucherVerificationException(FaultCodes.UNKNOWN_FAULT, "Unknown fault: " + data);
        }
    }


    /**
     * Overloading method of {@link #checkOperation(CheckOperation, String, String)}
     * @param op type of check operation
     * @param codVoucher voucher code of the customer
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
     * We assume activation is an idempotent operation.
     * activateCertificate();
     * Activate the merchant certificate using the protocol
     * (use Check Operation with following inputs -> type = 1, VoucherCode = 11aa22bb)
     * See https://www.18app.italia.it/static/Linee%20Guida%20Esercenti.pdf
     * @return partitaIva of the merchant
     */
    public String activateCertificate() throws VoucherVerificationException, CertificateException {

        CheckResponse checkResponse = checkOnlyOperation(ACTIVATION_VOUCHER_CODE);
        return checkResponse.getPartitaIvaEsercente();

    }

}
