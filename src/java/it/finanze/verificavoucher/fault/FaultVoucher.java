package it.finanze.verificavoucher.fault;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Fault Voucher
 */
@XmlType(name = "FaultVoucher", propOrder = {
        "message"
})
public class FaultVoucher {

    protected String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

}
