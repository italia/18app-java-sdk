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
     * @return
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
     * @param op
     * @param codVoucher
     * @return
     */
    private CheckResponse checkOperation(CheckOperation op, String codVoucher) throws SOAPFaultException {
        return checkOperation(op, codVoucher, null);
    }

    /**
     * Method which issues a Check operation.
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

    public CheckResponse checkOnlyOperation(String codVoucher, String partitaIva) throws SOAPFaultException {
        return checkOperation(CheckOperation.CHECK_ONLY_VOUCHER, codVoucher, partitaIva);
    }

    public CheckResponse checkOnlyOperation(String codVoucher) throws SOAPFaultException {
        return checkOperation(CheckOperation.CHECK_ONLY_VOUCHER, codVoucher);
    }

    public CheckResponse checkAndConsume(String codVoucher, String partitaIva) throws SOAPFaultException {
        return checkOperation(CheckOperation.CHECK_CONSUME_VOUCHER, codVoucher, partitaIva);
    }

    public CheckResponse checkAndConsume(String codVoucher) throws SOAPFaultException {
        return checkOperation(CheckOperation.CHECK_CONSUME_VOUCHER, codVoucher);
    }

    public CheckResponse checkAndFreeze(String codVoucher, String partitaIva) throws SOAPFaultException {
        return checkOperation(CheckOperation.CHECK_FREEZE_VOUCHER, codVoucher, partitaIva);
    }

    public CheckResponse checkAndFreeze(String codVoucher) throws SOAPFaultException {
        return checkOperation(CheckOperation.CHECK_FREEZE_VOUCHER, codVoucher);
    }

    public ConfirmResponse confirm(String codVoucher, double importo) throws SOAPFaultException {
        return confirmOperation(ConfirmOperation.CONFIRM, codVoucher, importo);
    }

}
