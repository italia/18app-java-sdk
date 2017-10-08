package it.italia.developers.exception;

import java.util.Iterator;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPFault;

import org.w3c.dom.NodeList;

/**
 * Made for <a href="https://hack.developers.italia.it/">hack.developers</a> '17 hackaton
 * @author Andrea, Francesco
 */
public class AppSOAPException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorDescription;

	public AppSOAPException(String errorCode, String errorDescription){
		this.setErrorCode(errorCode);
		this.setErrorDescription(errorDescription);
	}
   
	@SuppressWarnings("rawtypes")
	public AppSOAPException(SOAPFault fault){
		if (fault != null){
			Iterator det = fault.getDetail().getChildElements();
			while(det.hasNext()) {
				SOAPElement element = (SOAPElement)det.next();
				NodeList nl = element.getChildNodes();
	        	if (nl != null) {        		  
	        		this.setErrorCode(nl.item(0).getTextContent());
	        		this.setErrorDescription(nl.item(1).getTextContent());
	        	}
	        }
		}
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	public String toString(){
		return "ERRORE\nCodice Errore: " + this.errorCode + "\nDescrizione Errore: " + this.errorDescription;
	}


}
