package it.italia.developers;

public class Main {

	public static void main(String[] args) {
		
		// check
		System.out.println(new VoucherService().check("XXXXXXXX").getImporto());
		
		// consume
		System.out.println(new VoucherService().consume("XXXXXXXX", 13.37).getEsito());
		
		// invalidate
		System.out.println(new VoucherService().invalidate("XXXXXXXX").getAmbito());
		
	}

}
