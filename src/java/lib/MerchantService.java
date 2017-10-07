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
     * @return CheckResponse data structure filled with values
     */
    private CheckResponse checkOperation(CheckOperation op, String codVoucher, String partitaIva) throws SOAPFaultException {

        Check check = new Check();
        check.setTipoOperazione(op.getType());
        check.setCodiceVoucher(codVoucher);
        if (partitaIva != null)
            check.setPartitaIvaEsercente(partitaIva);

        CheckRequestObj checkRequestObj = new CheckRequestObj();
        checkRequestObj.setCheckReq(check);
        return service.getVerificaVoucherSOAP().check(checkRequestObj).getCheckResp();

    }

    /**
     * Overloading method of {@link #checkOperation(CheckOperation, String, String)}
     * @param op type of operation requested.
     * @param codVoucher voucher code of the coupon.
     * @return CheckResponse data structure filled with values
     */
    private CheckResponse checkOperation(CheckOperation op, String codVoucher) throws SOAPFaultException {
        return checkOperation(op, codVoucher, null);
    }

    /**
     * Method which issue a check only operation (without spending the actual voucher).
     * @param codVoucher voucher code of the coupon.
     * @param partitaIva optional.
     * @return CheckResponse data structure filled with values
     * @throws SOAPFaultException
     */
    public CheckResponse checkOnlyOperation(String codVoucher, String partitaIva) throws SOAPFaultException {
        return checkOperation(CheckOperation.CHECK_ONLY_VOUCHER, codVoucher, partitaIva);
    }

    /**
     * Overload method of {@link #checkOperation(CheckOperation, String)}
     * @param codVoucher voucher code of the coupon.
     * @return CheckResponse data structure filled with values
     * @throws SOAPFaultException
     */
    public CheckResponse checkOnlyOperation(String codVoucher) throws SOAPFaultException {
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
    public CheckResponse checkAndConsumeOperation(String codVoucher, String partitaIva) throws SOAPFaultException {
        return checkOperation(CheckOperation.CHECK_CONSUME_VOUCHER, codVoucher, partitaIva);
    }

    /**
     * Overloading method of {@link #checkAndConsumeOperation(String, String)}
     * @param codVoucher voucher code of the coupon.
     * @return CheckResponse data structure filled with values
     * @throws SOAPFaultException
     */
    public CheckResponse checkAndConsumeOperation(String codVoucher) throws SOAPFaultException {
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
    public CheckResponse checkAndFreezeOperation(String codVoucher, String partitaIva) throws SOAPFaultException {
        return checkOperation(CheckOperation.CHECK_FREEZE_VOUCHER, codVoucher, partitaIva);
    }

    /**
     * Overloading methood of {@link #checkAndFreezeOperation(String, String)}
     * @param codVoucher voucher code of the coupon.
     * @return CheckResponse data structure filled with values
     * @throws SOAPFaultException
     */
    public CheckResponse checkAndFreezeOperation(String codVoucher) throws SOAPFaultException {
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

        Confirm confirm = new Confirm();
        confirm.setTipoOperazione(op.getType());
        confirm.setCodiceVoucher(codVoucher);
        confirm.setImporto(importo);

        ConfirmRequestObj confirmRequestObj = new ConfirmRequestObj();
        confirmRequestObj.setCheckReq(confirm);
        return service.getVerificaVoucherSOAP().confirm(confirmRequestObj).getCheckResp();
    }

    /**
     * Method which issues a Confirm operation.
     * @param codVoucher voucher code of the coupon.
     * @param importo amount confirmed by the operator.
     * @return
     * @throws SOAPFaultException
     */
    public ConfirmResponse confirmOperation(String codVoucher, double importo) throws SOAPFaultException {
        return confirmOperation(ConfirmOperation.CONFIRM, codVoucher, importo);
    }

}
