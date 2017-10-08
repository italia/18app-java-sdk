
package it.mibact.bonus.verificavoucher;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="checkResp" type="{http://bonus.mibact.it/VerificaVoucher/}ConfirmResponse"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "checkResp"
})
@XmlRootElement(name = "ConfirmResponseObj")
public class ConfirmResponseObj {

    @XmlElement(required = true)
    protected ConfirmResponse checkResp;

    /**
     * Recupera il valore della proprietà checkResp.
     * 
     * @return
     *     possible object is
     *     {@link ConfirmResponse }
     *     
     */
    public ConfirmResponse getCheckResp() {
        return checkResp;
    }

    /**
     * Imposta il valore della proprietà checkResp.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfirmResponse }
     *     
     */
    public void setCheckResp(ConfirmResponse value) {
        this.checkResp = value;
    }

}
