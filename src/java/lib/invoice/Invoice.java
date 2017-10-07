package lib.invoice;

import it.mibact.bonus.verificavoucher.CheckResponse;

import java.io.File;

/**
 * Invoice Compiler in format www.fatturapa.gov.it
 * categoria di prodotto
  dettagli del prodotto acquistato (tipologia ed esercente/punto vendita)
  identificativo del buono
  identificativo della fattura elettronica in cui è stato indicato il bene acquistato
 * // FIXME Inserire reference all'xsd corretto
 */
public class Invoice {

    public static void init(){
        // FIXME Aprire il file csv per la lista di fatture
    }

    public static boolean createInvoiceLine(String voucherId, CheckResponse checkResponse){

        String invoiceFileName = Invoice.getInvoiceFileName(checkResponse);

        String category = checkResponse.getAmbito();
        String detail = checkResponse.getBene();
        String voucher = voucherId;

        // FIXME Creare la entry per la lista


        return true;
    }

    private static String getInvoiceFileName(CheckResponse checkResponse) {
        return null;
    }
}
