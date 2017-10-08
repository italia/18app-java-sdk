package it.italia.developers;

import it.italia.developers.exception.AppSOAPException;

public class Main {

	public static void main(String[] args) throws AppSOAPException {
		
		// check
		System.out.println(new VoucherService().check("XXXXXXXX").getImporto());
		
		// consume
		System.out.println(new VoucherService().consume("XXXXXXXX", 13.37).getEsito());
		
		// invalidate
		System.out.println(new VoucherService().invalidate("XXXXXXXX").getAmbito());
		
	}

}
