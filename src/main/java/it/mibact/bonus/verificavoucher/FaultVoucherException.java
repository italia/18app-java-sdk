package it.mibact.bonus.verificavoucher;

import javax.xml.ws.WebFault;


@WebFault(name="FaultVoucher")
public class FaultVoucherException extends Exception {

    private FaultVoucher fault;

    public FaultVoucherException() {
    }

    protected FaultVoucherException(FaultVoucher fault) {
        super(fault.getFaultString());
        this.fault = fault;
    }

    public FaultVoucherException(String message, FaultVoucher faultInfo){
        super(message);
        this.fault = faultInfo;
    }

    public FaultVoucherException(String message, FaultVoucher faultInfo, Throwable cause){
        super(message,cause);
        this.fault = faultInfo;
    }

    public FaultVoucher getFaultInfo(){
        return fault;
    }

    public FaultVoucherException(String message) {
        super(message);
    }

    public FaultVoucherException(String code, String message) {
        super(message);
        this.fault = new FaultVoucher();
        this.fault.setFaultString(message);
        this.fault.setFaultCode(code);
    }

    public FaultVoucherException(Throwable cause) {
        super(cause);
    }

    public FaultVoucherException(String message, Throwable cause) {
        super(message, cause);
    }
}