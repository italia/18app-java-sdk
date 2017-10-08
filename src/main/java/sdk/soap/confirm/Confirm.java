package sdk.soap.confirm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per Confirm complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="Confirm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoOperazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codiceVoucher" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Confirm", propOrder = {
        "tipoOperazione",
        "codiceVoucher",
        "importo"
})
public class Confirm {

    @XmlElement(required = true)
    protected String tipoOperazione;
    @XmlElement(required = true)
    protected String codiceVoucher;
    protected double importo;

    /**
     * Recupera il valore della proprietà tipoOperazione.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTipoOperazione() {
        return tipoOperazione;
    }

    /**
     * Imposta il valore della proprietà tipoOperazione.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTipoOperazione(String value) {
        this.tipoOperazione = value;
    }

    /**
     * Recupera il valore della proprietà codiceVoucher.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCodiceVoucher() {
        return codiceVoucher;
    }

    /**
     * Imposta il valore della proprietà codiceVoucher.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCodiceVoucher(String value) {
        this.codiceVoucher = value;
    }

    /**
     * Recupera il valore della proprietà importo.
     */
    public double getImporto() {
        return importo;
    }

    /**
     * Imposta il valore della proprietà importo.
     */
    public void setImporto(double value) {
        this.importo = value;
    }

}
