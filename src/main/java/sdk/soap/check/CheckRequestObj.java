package sdk.soap.check;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java per anonymous complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="checkReq" type="{http://bonus.mibact.it/VerificaVoucher/}Check"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "checkReq"
})
@XmlRootElement(name = "CheckRequestObj")
public class CheckRequestObj {

    @XmlElement(required = true)
    protected Check checkReq;

    /**
     * Recupera il valore della proprietà checkReq.
     *
     * @return possible object is
     * {@link Check }
     */
    public Check getCheckReq() {
        return checkReq;
    }

    /**
     * Imposta il valore della proprietà checkReq.
     *
     * @param value allowed object is
     *              {@link Check }
     */
    public void setCheckReq(Check value) {
        this.checkReq = value;
    }

}
