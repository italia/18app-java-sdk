package it.italia.developers;

import it.mibact.bonus.verificavoucher.Check;
import it.mibact.bonus.verificavoucher.CheckRequestObj;
import it.mibact.bonus.verificavoucher.CheckResponse;
import it.mibact.bonus.verificavoucher.Confirm;
import it.mibact.bonus.verificavoucher.ConfirmRequestObj;
import it.mibact.bonus.verificavoucher.ConfirmResponse;
import it.mibact.bonus.verificavoucher.ObjectFactory;
import it.mibact.bonus.verificavoucher.VerificaVoucher;
import it.mibact.bonus.verificavoucher.VerificaVoucher_Service;

/**
 * Made for <a href="https://hack.developers.italia.it/">hack.developers</a> '17 hackaton
 * @author Andrea, Francesco
 *
 */
public class VoucherService {

	private VerificaVoucher verificaVoucher;
	private ObjectFactory objectFactory;

	/**
	 * Create a new {@link VoucherService}, used to check and consume a given voucher.
	 */
	public VoucherService() {
		verificaVoucher = new VerificaVoucher_Service().getVerificaVoucherSOAP();
		objectFactory = new ObjectFactory();
	}

	/**
	 * Check a given voucher.
	 * @param voucher The voucher code to check
	 * @return The {@link CheckResponse} representing the response. 
	 */
	public CheckResponse check(String voucher) {
		return check(voucher, "1", null);
	}

	/**
	 * Invalidate a given voucher.
	 * @param voucher The voucher code to invalidate
	 * @return The {@link CheckResponse} representing the response. 
	 */
	public CheckResponse invalidate(String voucher) {
		return check(voucher, "1", null);
	}
	
	/**
	 * Check a given voucher, using a partitaIVA.
	 * @param voucher The voucher code to check
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
	
	/**
	 * Check and consume an amount from a given voucher.
	 * @param voucher The voucher code to consume from
	 * @param amount The amount to consume
	 * @return The {@link ConfirmResponse} representing the response. 
	 */
	public ConfirmResponse consume(String voucher, double amount) {
		ConfirmRequestObj confirmRequest = objectFactory.createConfirmRequestObj();
		Confirm confirm = objectFactory.createConfirm();
		
		confirm.setCodiceVoucher(voucher);
		confirm.setImporto(amount);
		confirm.setTipoOperazione("1");
		
		confirmRequest.setCheckReq(confirm);
		return verificaVoucher.confirm(confirmRequest).getCheckResp();
	}

}
