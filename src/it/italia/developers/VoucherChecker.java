package it.italia.developers;

import it.mibact.bonus.verificavoucher.Check;
import it.mibact.bonus.verificavoucher.CheckRequestObj;
import it.mibact.bonus.verificavoucher.CheckResponse;
import it.mibact.bonus.verificavoucher.ObjectFactory;
import it.mibact.bonus.verificavoucher.VerificaVoucher;
import it.mibact.bonus.verificavoucher.VerificaVoucher_Service;

public class VoucherChecker {

	private VerificaVoucher verificaVoucher;
	private ObjectFactory objectFactory;

	/**
	 * Create a new {@link VoucherChecker}, used to verify and check the residual
	 * amount of a given voucher.
	 */
	public VoucherChecker() {
		verificaVoucher = new VerificaVoucher_Service().getVerificaVoucherSOAP();
		objectFactory = new ObjectFactory();
	}

	/**
	 * Check a given voucher.
	 * @param voucher The voucher code
	 * @return The {@link CheckResponse} representing the response. 
	 */
	public CheckResponse check(String voucher) {
		return check(voucher, "1", null);
	}

	/**
	 * Check a given voucher, using a partitaIVA.
	 * @param voucher The voucher code
	 * @param partitaIvaEsercente The partitaIVA number of the merchant
	 * @return The {@link CheckResponse} representing the response. 
	 */
	public CheckResponse check(String voucher, String partitaIvaEsercente) {
		return check(voucher, "1", partitaIvaEsercente);
	}
	
	private CheckResponse check(String voucher, String operation, String partitaIva) {
		CheckRequestObj checkRequest = objectFactory.createCheckRequestObj();
		Check check = objectFactory.createCheck();
		
		check.setCodiceVoucher(voucher);
		check.setTipoOperazione(operation);
		if (partitaIva != null)
			check.setPartitaIvaEsercente(partitaIva);
		
		checkRequest.setCheckReq(check);
		return verificaVoucher.check(checkRequest).getCheckResp();
	}
	
}
