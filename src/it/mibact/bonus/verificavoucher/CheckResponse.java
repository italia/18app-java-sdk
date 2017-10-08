
package it.mibact.bonus.verificavoucher;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per CheckResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="CheckResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nominativoBeneficiario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partitaIvaEsercente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ambito" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bene" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckResponse", propOrder = {
    "nominativoBeneficiario",
    "partitaIvaEsercente",
    "ambito",
    "bene",
    "importo"
})
public class CheckResponse {

    @XmlElement(required = true)
    protected String nominativoBeneficiario;
    @XmlElement(required = true)
    protected String partitaIvaEsercente;
    @XmlElement(required = true)
    protected String ambito;
    @XmlElement(required = true)
    protected String bene;
    protected double importo;

    /**
     * Recupera il valore della proprietà nominativoBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNominativoBeneficiario() {
        return nominativoBeneficiario;
    }

    /**
     * Imposta il valore della proprietà nominativoBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNominativoBeneficiario(String value) {
        this.nominativoBeneficiario = value;
    }

    /**
     * Recupera il valore della proprietà partitaIvaEsercente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartitaIvaEsercente() {
        return partitaIvaEsercente;
    }

    /**
     * Imposta il valore della proprietà partitaIvaEsercente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartitaIvaEsercente(String value) {
        this.partitaIvaEsercente = value;
    }

    /**
     * Recupera il valore della proprietà ambito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmbito() {
        return ambito;
    }

    /**
     * Imposta il valore della proprietà ambito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmbito(String value) {
        this.ambito = value;
    }

    /**
     * Recupera il valore della proprietà bene.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBene() {
        return bene;
    }

    /**
     * Imposta il valore della proprietà bene.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBene(String value) {
        this.bene = value;
    }

    /**
     * Recupera il valore della proprietà importo.
     * 
     */
    public double getImporto() {
        return importo;
    }

    /**
     * Imposta il valore della proprietà importo.
     * 
     */
    public void setImporto(double value) {
        this.importo = value;
    }

}
