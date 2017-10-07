package it.finanze.verificavoucher.fault;

import javax.xml.ws.WebFault;

/**
 * Created by Lorenzo on 07/10/2017.
 */
@WebFault(name = "FaultVoucher", targetNamespace = "http://bonus.mibact.it/VerificaVoucher/")
public class FaultVoucher_Exception extends Exception {

    private FaultVoucher faultInfo;

    public FaultVoucher_Exception(String message, FaultVoucher faultInfo) { System.out.println("Prima Exception"); }
    public FaultVoucher_Exception(String message, FaultVoucher faultInfo,
                                 Throwable cause) {
        System.out.println("Seconda Exception");
    }
    public FaultVoucher getFaultInfo() {
        return faultInfo;
    }
}
