package test;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Lorenzo on 07/10/2017.
 */
public class FaultHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        try{
            SOAPMessage soapMsg = context.getMessage();
            SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
            SOAPHeader soapHeader = soapEnv.getHeader();

            //if no header, add one
            if (soapHeader == null){
                soapHeader = soapEnv.addHeader();
            }

            soapMsg.saveChanges();
            System.out.println(soapMsg.getSOAPHeader());
            //tracking
            soapMsg.writeTo(System.out);


        }catch(SOAPException e){
            System.err.println(e);
        }catch(IOException e){
            System.err.println(e);
        }
        return true;
    }

    @Override
    public void close(MessageContext context) {
        System.out.println("Client : close()......");
    }

    @Override
    public Set<QName> getHeaders() {
        System.out.println("Client : getHeaders()......");
        return null;
    }
}
