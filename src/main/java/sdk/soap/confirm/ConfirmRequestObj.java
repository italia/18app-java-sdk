package sdk.soap.confirm;

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
 *         &lt;element name="checkReq" type="{http://bonus.mibact.it/VerificaVoucher/}Confirm"/>
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
@XmlRootElement(name = "ConfirmRequestObj")
public class ConfirmRequestObj {

    @XmlElement(required = true)
    protected Confirm checkReq;

    /**
     * Recupera il valore della proprietà checkReq.
     *
     * @return possible object is
     * {@link Confirm }
     */
    public Confirm getCheckReq() {
        return checkReq;
    }

    /**
     * Imposta il valore della proprietà checkReq.
     *
     * @param value allowed object is
     *              {@link Confirm }
     */
    public void setCheckReq(Confirm value) {
        this.checkReq = value;
    }

}
